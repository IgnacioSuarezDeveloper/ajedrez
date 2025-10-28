import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import tablero;

public class GamePanel extends JPanel implements Runnable {
    int ancho;
    int alto;
    JFrame ventana;
    tablero tabel;
    piezaBase [][]pieza = new piezaBase[8][8];
    Thread gameThread; // Hilo del juego

    public GamePanel(int ancho, int alto, JFrame ventana, ventana win){
        this.ancho = ancho;
        this.alto = alto;
        this.ventana = ventana;
        this.tabel = new tablero(win);
        String [] filaNegra = {"torreNegra.png", "caballoNegro.png", "alfilNegro.png", "reyNegro.png", "reinaNegra.png", "alfilNegro.png", "caballoNegro.png", "torreNegra.png", "peonNegro.png"};
        String [] filaBlanca = {"torreBlanca.png", "caballoBlanco.png","alfilBlanco.png","reyBlanco.png", "reinaBlanca.png","alfilBlanco.png","caballoBlanco.png","torreBlanca.png", "peonBlanco.png"};

        for(int i = 0; i < filaNegra.length; i++){
                switch(filaNegra[i]){
                        case "torreNegra.png":
                                this.pieza[0][i] = new torre(i * tablero.anchoCasilla, 0, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaNegra[i]);
                                this.pieza[0][i].blanco = false;
                                this.pieza[0][i].nombre = "torreNegra";
                                break;
                        case "caballoNegro.png":
                                this.pieza[0][i] = new caballo(i * tablero.anchoCasilla, 0, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaNegra[i]);
                                this.pieza[0][i].blanco = false;
                                this.pieza[0][i].nombre = "caballoNegro";
                                break;
                        case "alfilNegro.png":
                                this.pieza[0][i] = new alfil(i * tablero.anchoCasilla, 0, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaNegra[i]);
                                this.pieza[0][i].blanco = false;
                                this.pieza[0][i].nombre = "alfilNegro";
                                break;
                        case "reyNegro.png":
                                this.pieza[0][i] = new rey(i * tablero.anchoCasilla, 0, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaNegra[i]);
                                this.pieza[0][i].blanco = false;
                                this.pieza[0][i].nombre = "reyNegro";
                                break;
                        case "reinaNegra.png":
                                this.pieza[0][i] = new reina(i * tablero.anchoCasilla, 0, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaNegra[i]);
                                this.pieza[0][i].blanco = false;
                                this.pieza[0][i].nombre = "reinaNegra";
                                break;
                        case "peonNegro.png":
                                for(int x = 0; x < 8; x++){this.pieza[1][x] = new peon(x  * tablero.anchoCasilla, 1 * tablero.altoCasilla, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaNegra[i]);
                                this.pieza[1][x].blanco = false;
                                this.pieza[1][x].nombre = "peonNegro";
                        }
                                
                                
                }
        }
         for(int i = 0; i < filaBlanca.length; i++){
                switch(filaBlanca[i]){
                        case "torreBlanca.png":
                                this.pieza[7][i] = new torre(i * tablero.anchoCasilla, 7 * tablero.altoCasilla,tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaBlanca[i]);
                                this.pieza[7][i].blanco = true;
                                this.pieza[7][i].nombre = "torreBlanca";
                                break;
                        case "caballoBlanco.png":
                                this.pieza[7][i] = new caballo(i * tablero.anchoCasilla, 7 * tablero.altoCasilla, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaBlanca[i]);
                                this.pieza[7][i].blanco = true;
                                this.pieza[7][i].nombre = "caballoBlanco";
                                break;
                        case "alfilBlanco.png":
                                this.pieza[7][i] = new alfil(i * tablero.anchoCasilla, 7 * tablero.altoCasilla, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaBlanca[i]);
                                this.pieza[7][i].blanco = true;
                                this.pieza[7][i].nombre = "alfilBlanco";
                                break;
                        case "reyBlanco.png":
                                this.pieza[7][i] = new rey(i * tablero.anchoCasilla, 7 * tablero.altoCasilla, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaBlanca[i]);
                                this.pieza[7][i].blanco = true;
                                this.pieza[7][i].nombre = "reyBlanco";
                                break;
                        case "reinaBlanca.png":
                                this.pieza[7][i] = new reina(i * tablero.anchoCasilla, 7 * tablero.altoCasilla, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaBlanca[i]);
                                this.pieza[7][i].blanco = true;
                                this.pieza[7][i].nombre = "reinaBlanca";
                                break;
                        case "peonBlanco.png":
                                for(int x = 0; x < 8; x++){this.pieza[6][x] = new peon(x  * tablero.anchoCasilla, 6 * tablero.altoCasilla, tablero.anchoCasilla,tablero.altoCasilla,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/img/" + filaBlanca[i]);
                                this.pieza[6][x].blanco = true;
                                this.pieza[6][x].nombre = "peonBlanco";
                        }
                }       
        }
        
        //this.pieza = new piezaBase(100,100,100,100,"C:/Users/ignac/Downloads/chess-main/chess-main/ajedrez/caballo.png");
        this.setBounds(0,0,ventana.getWidth(),ventana.getHeight());
        this.setLayout(null);
        this.setBackground(Color.CYAN);
        ventana.add(this);
        // Iniciar el hilo del juego
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.tabel.dibujarTablero(g);
      // g.drawImage(this.pieza.imagen, pieza.posx, pieza.posy, pieza.ancho,pieza.alto, this);
      for(int i = 0; i < 8; i++){
        for(int x = 0; x < 8; x++){
            if(this.pieza[i][x] != null){
                g.drawImage(this.pieza[i][x].imagen,this.pieza[i][x].posx ,this.pieza[i][x].posy ,this.pieza[i][x].ancho,this.pieza[i][x].alto, this);
            }
        }
      }
       
    }

    @Override
    public void run(){
        // Bucle principal del "juego"
        while(true){
            // Aquí podrías actualizar la lógica de tus piezas/tablero
            // Por ahora solo repintamos
           
            for(int i = 0; i < 8 ; i++){
                for(int x = 0; x < 8; x++){
                        if(this.pieza[i][x] != null){
                                this.pieza[i][x].mover(this);
                        }
                }
            }
            //System.out.println(" x :  " + (Math.abs(MouseEvents.mouseX / 100)) + " y : " + (Math.abs(MouseEvents.mouseY/100)));
            try {
                Thread.sleep(16); // 16 ms ≈ 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             repaint();
        }
    }
}