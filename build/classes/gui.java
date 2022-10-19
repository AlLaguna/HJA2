
import java.awt.Color;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class gui
{
    private static final int LADO = 13;
    private static final int SEPARACION = 50;
    private static final int OFFSET = 10;
    private static final int TAMAÑO = 45;
    public static JLabel[][] cuadricula = new JLabel[LADO][LADO];
    public static JFrame frame = new JFrame("Prueba JFrame");
    public static TextField inputRangos = new TextField(100);
    public static JButton botonRangos = new JButton("Evaluar");
    public static JSlider porcentaje = new JSlider();
    public static JLabel textoPorcentaje = new JLabel();
    
    private static String[] cartas =  {"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};
    /*
    
        if(fila = columna) NADA/PAREJAS -VERDE
        if(fila > columna) Ofsuites - Gris
        if(colummna > fila) Suited - Rojo
        SELECCION - AMRILLO
    */
    
    public static void setupGUI()
    {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080,1080);
        
        inputRangos.setBounds(OFFSET, SEPARACION*LADO + OFFSET *2, 400, 30);
        frame.add(inputRangos);
        
        botonRangos.setBounds(450, SEPARACION*LADO + OFFSET *2, 200 , 30);
        frame.add(botonRangos);
        
        porcentaje.setBounds(OFFSET, SEPARACION*LADO + OFFSET *6, 400, 30);
        frame.add(porcentaje);
        
        textoPorcentaje.setBounds(450, SEPARACION*LADO + OFFSET *7, 100, 30);
        textoPorcentaje.setBackground(Color.WHITE);
        textoPorcentaje.setOpaque(true);
        frame.add(textoPorcentaje);
        
        for(int fila = 0; fila < LADO;fila++)
        {
            for(int columna = 0; columna < LADO; columna++)
            {
                if(columna > fila )
                {
                    cuadricula[fila][columna] = new JLabel(cartas[fila]+cartas[columna] + "o", SwingConstants.CENTER);
                }
                else if (columna == fila)
                {
                    cuadricula[fila][columna] = new JLabel(cartas[fila]+cartas[columna], SwingConstants.CENTER);
                }
                else
                {
                    cuadricula[fila][columna] = new JLabel(cartas[columna] + cartas[fila] + "s", SwingConstants.CENTER);
                }
                  
                  cuadricula[fila][columna].setOpaque(true);
                  cuadricula[fila][columna].setBackground(Color.WHITE);
                  cuadricula[fila][columna].setSize(TAMAÑO,TAMAÑO);
                  cuadricula[fila][columna].setBounds(SEPARACION * fila + OFFSET, SEPARACION * columna +OFFSET, cuadricula[fila][columna].size().width, cuadricula[fila][columna].size().height);
                  frame.add(cuadricula[fila][columna]);
            }
        }

        frame.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                setupGUI();
            }
        });
    }
    
    

    
    
     
    
}

