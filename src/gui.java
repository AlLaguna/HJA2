
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class gui
{
    private static final int LADO = 13;
    private static final int SEPARACION = 50;
    private static final int OFFSET = 10;
    private static final int TAMAÑO = 45;
    public static JLabel[][] cuadricula = new JLabel[LADO][LADO];
    public static JFrame frame = new JFrame("Prueba JFrame");
    
    /*
    
        if(fila = columna) NADA
        if(fila > columna) Ofsuites
        if(colummna > fila) Suited
    
    */
    
    public static void setupGUI()
    {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080,1080);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        for(int fila = 0; fila < LADO;fila++)
        {
            for(int columna = 0; columna < LADO; columna++)
            {
                  cuadricula[fila][columna] = new JLabel("AA", SwingConstants.CENTER);
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

