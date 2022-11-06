

import java.util.Arrays;

public class Mano implements Comparable<Mano>
{
    private final int numCartas;
    private final Carta mano[];
    
    //                   0       1        2       3       4       5      6      7           8           9
    //ARRAYJUGADAS: CartaAlta, Pareja, 2parejas, Trio, Escalera, Color, Full, Poker, EscaleraColor, EscaleraReal
    private int arrayJugadas[] = {0,0,0,0,0,0,0,0,0,0};
    
    //Indica la posicion donde empieza la jugada (Escaleras y color siempre empiezan en 0 dado que son las 5 cartas)
    //Se utiliza para mostras las jugadas
    private int posicionesJugadas[] = {-1,-1,-1,-1,0,0,-1,-1,0,0};
    
    private boolean puedeColor = false;
    
    private boolean puedeEscalera = false;
    //                     0 1 2 3
    //                     C D H S
    private int palos[] = {0,0,0,0};
    
    private int jugada = -1;
    
    //Constructor
    Mano(String cartas, int numCartas)
    {   
        this.numCartas = numCartas;
        
        this.mano = new Carta[numCartas]; //Puede haber 4 ases

               
        for(int i = 0; i < numCartas; i++)
        {
            this.mano[i] = new Carta(cartas.substring(i*2, i*2+2));
        }
    }
    
    Mano(Carta[] cartas, int numCartas)
    {
        this.numCartas = numCartas;
        this.mano = Arrays.copyOf(cartas, numCartas);
    }
    
    public void ordenar()
    {
        Arrays.sort(mano);
    }

    public String toString()
    {
        String manoString = "";
        for(int i = 0; i < this.numCartas; i++)
        {
            manoString = manoString + mano[i].toString();
        }
        return manoString;
    }
    
    public String mostrarJugada() {
    	for(int i = getArrayJugadas().length - 1; i >= 0 ; i--) {
        	if(getArrayJugadas()[i] > 0) {
        		return jugadaToString(i);
        	}
        }
		return "";
    }
    
    public void mejorJugada() {
    	for(int i = getArrayJugadas().length - 1; i >= 0 ; i--) {
        	if(getArrayJugadas()[i] > 0) {
        		jugada = i;
        		break;
        	}
        }
    }  

	public int compareTo(Mano otraMano)
    {
            if(this.jugada > otraMano.jugada) {
            	return -1;
            }
            else if(this.jugada < otraMano.jugada) {
            	return 1;
            }
            else {
            	if(this.posicionesJugadas[this.jugada] > otraMano.posicionesJugadas[jugada])
            		return -1;
            	else if (this.posicionesJugadas[this.jugada] < otraMano.posicionesJugadas[jugada])
            		return 1;
            	else {
            		if(this.posicionesJugadas[0] > otraMano.posicionesJugadas[0])
            			return -1;
		        	else if (this.posicionesJugadas[0] < otraMano.posicionesJugadas[0])
		        		return 1;
		        	else 
	            		return 0;
            	}
            	
            }                    	
    }

    
    //Ordena lo que tienes en la mano (pareja, trio...)
	public void evaluarMano()
	{
	    //ARRAYCARTAS:       0,0,2,3,4,5,6,7,8,9,T,J,Q,K,A(14) //HAY UN DESFASE DE 2 POSICIONES VALOR-POSICION ARRAY (2 '0's delante) 
	    int arrayCartas[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	    
	    
	    
	    //CARTA ALTA SE COGE AL FINAL
	    boolean isCartaEmparejada[] = new boolean[this.numCartas];
	    
	    int numSeguidas = 1;
	    
	    
	    for(int i = 0; i < this.numCartas; i++)
	    {
	        arrayCartas[this.mano[i].getValor()]++; //Se suma al contador en la posicion del valor de la carta
	        
	        switch (this.mano[i].getPalo()) 
	        {
	            case 'c':
	                palos[0]++;
	                break;
	            case 'd':
	                palos[1]++;
	                break;
	            case 'h':
	                palos[2]++;
	                break;
	            case 's':
	                palos[3]++;
	                break;
	            default:
	                break;
	        }
	        
	        if(arrayCartas[this.mano[i].getValor()] == 2) //Pareja
	        {
	            getArrayJugadas()[1]++;
	            
                    
                    if (getArrayJugadas()[2] == 2) //Y no hay trio ==> FULL
                    {    
                        if(i < this.numCartas - 1 && this.mano[i+1].getValor() != this.mano[i].getValor())
                        {
                            getArrayJugadas()[2] = getArrayJugadas()[2] - 2;
                            getArrayJugadas()[3]++;

                            /*if (this.mano[i].getValor() <= valorJugadas[2]) {
                                valorJugadas[3] = valorJugadas[2];
                                valorJugadas[2] = this.mano[i].getValor();
                            }*/
                        }
	    	    }
	    	    else
	    	    {
	    	    	//valorJugadas[1] = this.mano[i].getValor();
	    	    	//valorJugadas[2] = this.mano[i].getValor();
	    	    }
	            
	            isCartaEmparejada[i] = true;
	            isCartaEmparejada[i-1] = true;
	            
	        }
	        else if(arrayCartas[this.mano[i].getValor()] == 3) //Trio
	        {
	            isCartaEmparejada[i] = true;
	            
	            getArrayJugadas()[3]++;
	            posicionesJugadas[3] = i-2;
	            
	            //Se elimina la pareja anterior
	            getArrayJugadas()[1]--;
	            posicionesJugadas[1] = -1;
	
	        }
	        else if(arrayCartas[this.mano[i].getValor()] == 4) //Poker
	        {
	            isCartaEmparejada[i] = true;
	            
	            getArrayJugadas()[7]++;
	            posicionesJugadas[7] = i-3;
	            
	            //Se elimina el trio anterior
	            getArrayJugadas()[3]--;
	            posicionesJugadas[3] = -1;
	        }
	                 
	        //Comprobar escalera
	        if(i > 0) //no es la primera carta
	        {
	            if(this.mano[i].getValor() - this.mano[i-1].getValor() == 1) //Van seguidas
	            {
	                numSeguidas++;
	            }
                    else if(this.mano[i] == this.mano[i-1])
                    {
                        //Nada
                    }
	            else
	            {
	                numSeguidas = 1;
	            }
	            
	            if(numSeguidas == 4 && this.mano[i].getValor() == 5 && this.mano[this.numCartas-1].getValor() == 14) //Hay escalera A-5
	            {
	                this.puedeEscalera = true;
	            }
	            else if(numSeguidas == 5)
	            {
	                this.puedeEscalera = true;
	            }
	            /*if(puedeColor)
	            {
	                if(this.mano[i-1].getPalo() != this.mano[i].getPalo()) //No son del mismo palo
	                {
	                    puedeColor = false;
	                }
	            }*/
	            /*if (i == 4 && this.mano[i].getValor() == 14 && this.mano[i - 1].getValor() == 5) //Hay un As y una escalera 2-5
	            {
	                this.puedeEscalera = true;
	            } 
	            else 
	            {
	                //if (this.mano[i - 1] != this.mano[i]) //Es
	                if (this.mano[i].getValor() - this.mano[i - 1].getValor() != 1) //Hay un hueco
	                {
	                    this.puedeEscalera = false;
	                }
	            }*/
	            
	            
	        }
	    }
	    
	    //COLOR:
	    for(int i = 0; i < 4; i++)
	    {
	        if(this.palos[i] >= 5) //Hay color
	        {
	            getArrayJugadas()[5]++;
	            
	            char paloColor = 'a';
	            
	            switch (i) 
	            {
	                case 0:
	                    paloColor = 'c';
	                    break;
	                case 1:
	                    paloColor = 'd';
	                    break;
	                case 2:
	                    paloColor = 'h';
	                    break;
	                case 3:
	                    paloColor = 's';
	                    break;
	                default:
	                    break;
	            }
	            
	            for (int j = 0; j < this.numCartas; j++) 
	            {
	               if(this.mano[j].getPalo() == paloColor)
	               {
	                isCartaEmparejada[j] = true;
	               }
	            }
	        }
	        else if(this.palos[i] == 4) //Hay Draw: Flush (Color)
	        {
	            puedeColor = true;
	        }
	    }
	        
	    //Si hay 2 parejas ==> 1 doble pareja
	    if (getArrayJugadas()[1] == 2) 
            { //Y no hay trio ==> FULL
	        getArrayJugadas()[1] = getArrayJugadas()[1] - 2;
	        getArrayJugadas()[2]++;
	        
	        posicionesJugadas[2] = posicionesJugadas[1];
	        posicionesJugadas[1] = -1;
	    }
	
	    //Si hay pareja + trio ==> 1 full
	    if (getArrayJugadas()[1] == 1 && getArrayJugadas()[3] == 1) 
	    {
	        getArrayJugadas()[1]--;
	        getArrayJugadas()[3]--;
	        getArrayJugadas()[6]++;
	        
	        posicionesJugadas[6] = posicionesJugadas[1];
	        posicionesJugadas[1] = -1;
	        posicionesJugadas[3] = -1;
	    }
	
	    /*if (puedeColor) 
	    {
	        arrayJugadas[5]++;
	
	        for (int j = 0; j < this.numCartas; j++) 
	        {
	            isCartaEmparejada[j] = true;
	        }
	    }*/
	
	    //Comprobar escaleras
	    if (this.puedeEscalera) {
	        if (getArrayJugadas()[5] == 1) //Hay escalera de color
	        {
	            if (this.mano[this.numCartas-1].getValor() == 14) //Acaba en As
	            {
	                getArrayJugadas()[9]++;
	
	                for (int j = 0; j < this.numCartas; j++) {
	                    isCartaEmparejada[j] = true;
	                }
	            } else {
	                getArrayJugadas()[8]++;
	
	                for (int j = 0; j < this.numCartas; j++) {
	                    isCartaEmparejada[j] = true;
	                }
	            }
	        } else //Hay escalera
	        {
	            getArrayJugadas()[4]++;
	
	            for (int j = 0; j < this.numCartas; j++) {
	                isCartaEmparejada[j] = true;
	            }
	        }
	    }
	    
	    //Conseguir carta más alta no emparejada
	    int i = this.numCartas-1;
	    while(i>=0 && posicionesJugadas[0] == -1)
	    {
	         if(!isCartaEmparejada[i])
	         {
	             getArrayJugadas()[0]++;
	             posicionesJugadas[0] = i;
	         }
	         i--;
	    }
	
	    /*
	        ARRAYJUGADAS: CA,Parej,2parej,Trio,Esc,Color,Full,Poker,EscColor,EscReal
	    
	        for(numCartas)
	        {
	            arrayCartas[this.mano[i].valor]++
	    
	            if(arrayCartas[this.mano[i].valor] == 2) //Pareja
	            else if(arrayCartas[this.mano[i].valor] == 3) //Trio (quitar pareja)
	            else if(arrayCartas[this.mano[i].valor] == 4) //Poker (quitar trio)
	    
	    
	            //Contador con el numero de cartas de un coclor --> comprueba si hay color
	            //Mirar orden ascendente ---> comprueba si hay escalera
	
	            //Variable con la carta más alta que sobra (se usará en caso de empate) [CREAR ARRAY DE CARTAS SOBRANTES?????]
	        
	            //Proyecto de escalera: (buscar huecos de 1 carta)
	        }
	    
	        if(arrayJugadas[1] == 2) //2 PAREJAS == 1 2Parej
	        if( (arrayJugadas[1] == 1 %% arrayJugadas[3] == 1) || (arrayJugadas[2] == 1 %% arrayJugadas[3] == 1) ) //Parejas o doble pareja + trio == FULL **OJO con la 2Parej
	    
	    
	
	        //PARA ESCALERAS:
	            - Comprobar valor ascendente en la mano (arrayJugadas)
	            - Comprobar si hay color (en arrayJugadas)
	            - Comprobar si la carta más baja es un 10 (la1era de la mano) --> escalera real
	    
	        //VECTOR AUXILIAR DE JUGADAS PARA COMPARALAS: ALMACENAR POSICION
	            - Guarda la info de la posición de la primera carta de la jugada
	    */
	}

	public String jugadaToString(int pos) {
    	String resultado = "- Best Hand: ";

    	switch (pos) {
    		case 0:
    			resultado = resultado + "Carta Alta de " + this.mano[posicionesJugadas[pos]].printNombre()+ " con";
    			break;
    		case 1:
    			resultado = resultado + "Pareja de " + this.mano[posicionesJugadas[pos]].printNombre()+ " con";
    			break;
    		case 2:
    			resultado = resultado + "Doble pareja de " + this.mano[posicionesJugadas[pos]].printNombre() + "y" + this.mano[posicionesJugadas[pos]+2].printNombre() + " con";
    			break;
    		case 3:
    			resultado = resultado + "Trio de " + this.mano[posicionesJugadas[pos]].printNombre()+ " con";
    			break;
    		case 4:
    			resultado = resultado + "Escalera";
    			break;
    		case 5:
    			resultado = resultado + "Color";
    			break;
    		case 6:
    			resultado = resultado + "Full de " + this.mano[posicionesJugadas[pos]].printNombre() + "y" + this.mano[posicionesJugadas[pos]+2].printNombre() + " con";
    			break;
    		case 7:
    			resultado = resultado + "Poker de " + this.mano[posicionesJugadas[pos]].printNombre()+ " con";
    			break;
    		case 8:
    			resultado = resultado + "Escalera de color";
    			break;
    		case 9:
    			resultado = resultado + "Escalera real";
    			break;
    		default:
    	          break;
    		
    	}
    	
    	return resultado +": "+ this.toString();
    }
    
    //                   0       1        2       3       4       5      6      7           8           9
    //ARRAYJUGADAS: CartaAlta, Pareja, 2parejas, Trio, Escalera, Color, Full, Poker, EscaleraColor, EscaleraReal
    
    
    public String draws()
    {
        String resultadoDraws = "";

        if(this.getArrayJugadas()[6] >= 1 && this.getArrayJugadas()[7] == 0) //Full
        {
            resultadoDraws = resultadoDraws + "- Draw: Four of a kind\n";
        }
        
        if(this.getArrayJugadas()[3] >= 1 && !puedeColor && !puedeEscalera && resultadoDraws.isEmpty() && this.getArrayJugadas()[7] == 0) //Trio
        {
            resultadoDraws = resultadoDraws + "- Draw: Four of a kind\n";
            
            if(this.getArrayJugadas()[0] >= 1) //+1 Carta Alta
            {
                resultadoDraws = resultadoDraws + "- Draw: Full\n";
            }
        }
        
        if(this.getArrayJugadas()[1] >= 1 && !puedeColor && !puedeEscalera && resultadoDraws.isEmpty() && this.getArrayJugadas()[7] == 0)
        {
            resultadoDraws = resultadoDraws + "- Draw: Three of a kind\n";
            
            if(this.getArrayJugadas()[0] >= 1) //+1 Carta Alta
            {
                resultadoDraws = resultadoDraws + "- Draw: Two Pair\n";
            }
        }
        
        if(puedeColor)
        {
            resultadoDraws = resultadoDraws + "- Draw: Flush\n";
        }
        
        //DRAWS ESCALERA Y COLOR
        
        //CUATRO CARTAS SEGUIDAS CON MAXIMO 1 HUECO
        boolean drawEscalera = false;
        int i = 1;
        int numHuecos = 0;
        int numSeguidas = 1;
        while (!this.puedeEscalera && !drawEscalera && i < this.numCartas)
        {
            if(this.mano[i].getValor() - this.mano[i-1].getValor() == 1) //Van seguidas
            {
                numSeguidas++;
            }
            else //Hay un hueco de al menos 1 carta
            {
                numHuecos = numHuecos + this.mano[i].getValor() - this.mano[i-1].getValor() - 1;
                if(numHuecos > 1 || numHuecos < 0) //Max puede haber un hueco y si es negativo es que son iguales, tampoco puede haber escalera
                {
                    numSeguidas = 1;
                    numHuecos = 0;
                }
                else
                {
                    numSeguidas++;
                }   
            }
           
            if(numHuecos == 0 && numSeguidas == 4)
            {
                drawEscalera = true;
                
                if(numHuecos == 0)
                {
                    resultadoDraws = resultadoDraws + "- Draw: Straight Open-Ended\n";
                }
                else
                {
                    resultadoDraws = resultadoDraws + "- Draw: Straight Gutshot\n";
                }
            }
            else if(numHuecos == 1 && numSeguidas == 5)
            {
            	drawEscalera = true;
                
                if(numHuecos == 0)
                {
                    resultadoDraws = resultadoDraws + "- Draw: Straight Open-Ended\n";
                }
                else
                {
                    resultadoDraws = resultadoDraws + "- Draw: Straight Gutshot\n";
                }
            }
            
            i++;
        }
        
    
       
        if(this.getArrayJugadas()[0] >= 1 && resultadoDraws.isEmpty() && this.getArrayJugadas()[7] == 0 && !puedeEscalera) //Hay un Carta Alta y no se hay una jugada mayor
        {
            resultadoDraws = resultadoDraws + "- Draw: Pair\n";
        }
        
        
        return resultadoDraws;
    }

	public int[] getArrayJugadas() {
		return arrayJugadas;
	}

	public void setArrayJugadas(int arrayJugadas[]) {
		this.arrayJugadas = arrayJugadas;
	}
	
	
}
