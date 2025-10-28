import javax.swing.*;
import java.awt.*;
public class Main{
    public static void main(String[] args){
        ventana ventana = new ventana(800, 800, "ajedrez");
        GamePanel canvas = new GamePanel(ventana.ancho, ventana.alto, ventana.ventana,ventana);
        MouseEvents mouseMoves = new MouseEvents(canvas);
        ventana.ventana.setVisible(true);
    }
}