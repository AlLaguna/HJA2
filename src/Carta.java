
public class Carta implements Comparable<Carta>{

	private int valor;
	private char palo;
        public boolean isMesa;
        private int posicion;
	
	/*Carta(String cartaString, boolean isMesa, int posicion) {
		
		this.valor = parseValor(cartaString.charAt(0));
		this.palo = cartaString.charAt(1);
                this.isMesa = isMesa;
                this.posicion = posicion;
	}*/
        
        Carta(String cartaString) {
		
		this.valor = parseValor(cartaString.charAt(0));
		this.palo = cartaString.charAt(1);
                //this.isMesa = isMesa;
                //this.posicion = posicion;
	}
	
	/*public Carta(int valor, char palo) {		//Constructor
		this.valor = valor;
		this.palo = palo;
	}*/
	
	public int compareTo(Carta otraCarta)
        {
            return Integer.compare(this.getValor(),otraCarta.getValor()); //Tienen el mismo valor (< -1; = 0; > 1)
        }
	
	public boolean samePalo(Carta auxCarta) {
		if (this.getPalo() != auxCarta.getPalo())
			return false;
		else
			return true;
	}
        
        public int getValor()
        {
            return this.valor;
        }
        
        public char getPalo()
        {
            return this.palo;
        }
        
        public void setIsMesa(boolean isMesa)
        {
            this.isMesa = isMesa;
        }
        
        public void setPosMesa(int posMesa)
        {
            this.posicion = posMesa;
        }
                    
	private int parseValor(char valorChar) {		
		
            if (valorChar == 'T') {
			return 10;
		}
		else if (valorChar == 'J') {
			return 11;
		}
		else if (valorChar == 'Q') {
			return 12;
		}
		else if (valorChar == 'K') {
			return 13;
		}
		else if (valorChar == 'A') {
			return 14;
		}
        else{
			return Character.getNumericValue(valorChar);
            }
        }
	
         private String valorToString()
        {
            if (this.valor == 10) {
			return "T";
			}
			else if (this.valor == 11) {
				return "J";
			}
			else if (this.valor == 12) {
				return "Q";
			}
			else if (this.valor == 13) {
				return "K";
			}
			else if (this.valor == 14) {
				return "A";
			}
	        else{
				return String.valueOf(this.valor);
	        }
        }
         
        public String printNombre()
        {
            switch(this.valor)
            {
                case 2:
                    return "Two";
                case 3:
                    return "Three";
                case 4:
                    return "Four";
                case 5:
                    return "Five";
                case 6:
                    return "Six";
                case 7:
                    return "Seven";
                case 8:
                    return "Eight";
                case 9:
                    return "Nine";
                case 10:
                    return "Ten";
                case 11:
                    return "Jack";
                case 12:
                    return "Queen";
                case 13:
                    return "King";
                case 14:
                    return "Ace";
            }
            
            return "";
        }
            
	public String toString() 
        {
            return  valorToString() + this.palo;
	}
}
