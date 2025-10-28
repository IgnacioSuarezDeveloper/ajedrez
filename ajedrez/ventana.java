import javax.swing.JFrame;
public class ventana{
    int ancho;
    int alto;
    String titulo;
    JFrame ventana;
    public ventana(int ancho, int alto, String titulo){
        this.ancho = ancho;
        this.alto = alto;
        this.titulo = titulo;
        this.ventana = new JFrame(titulo);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setSize(this.ancho,this.alto);
        this.ventana.setVisible(true);
    }

}