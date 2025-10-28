import ventana;
import java.awt.Graphics;
import java.awt.Color;
public class tablero{
    public static int anchoCasilla;
    public static int altoCasilla;
    public tablero(ventana ventana){
        tablero.anchoCasilla = ventana.ancho / 8;
        tablero.altoCasilla = ventana.alto / 8;
    }
   public void dibujarTablero(Graphics g) {
    for (int i = 0; i < 8; i++) {
        for (int x = 0; x < 8; x++) {
            // Use the sum of the row and column indices to determine the color
            if ((i + x) % 2 == 0) {
                g.setColor(Color.CYAN); // White squares
            } else {
                g.setColor(Color.GREEN);  // Green squares
            }
            g.fillRect(x * tablero.anchoCasilla, i * tablero.altoCasilla, tablero.anchoCasilla, tablero.altoCasilla);
        }
    }
}
}