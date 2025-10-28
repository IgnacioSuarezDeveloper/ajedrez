import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import GamePanel;




public class piezaBase{//clase de la que heredan las piezas
    String nombre;
    int posx;
    int posy;
    int alto;
    int ancho; 
    int nmovimientos = 0;
    boolean blanco = false;
    static boolean piezaSelccionada = false;
    static int xind = -1;
    static int yind = -1;
    static int xinds = 0;
    static int yinds = 0;
    static int indiceX = 0;
    static int indiceY = 0;
    static int piezaSeleccionadaX;
    static int piezaSeleccionadaY;
    static boolean inicializado = false;
    static boolean actualizarPosicionalSoltar;
    static boolean turnoBlancas = true;
 
    String ruta;
    BufferedImage imagen;
    public piezaBase(int posx, int posy, int alto, int ancho, String ruta){
        this.posx = posx;
        this.posy = posy;
        this.alto = alto;
        this.ancho = ancho;
        this.ruta = ruta;

        try{
            imagen = ImageIO.read(new File(this.ruta));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void mover(GamePanel gamePanel){
        
    };
}//class piezaBase();
class caballo extends piezaBase{
    public caballo(int posx, int posy, int alto, int ancho, String ruta){
        super( posx, posy, alto, ancho, ruta);
    }
    @Override
    public void mover(GamePanel gamePanel){
        if(!MouseEvents.leftClick && actualizarPosicionalSoltar){//logica cuando soltamos el click

            if(gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "caballoBlanco" 
            &&piezaSeleccionadaX + 1 <= 7 && piezaSeleccionadaY - 2 >= 0
            && gamePanel.pieza[piezaSeleccionadaY - 2][piezaSeleccionadaX + 1] == null 
            && Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY - 2 
            && Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaX + 1 ){//si caballo blanco se suelta en la casilla de delante a la derecha
                System.out.println("eureca");
                gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY - 2) * 100;
                gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX + 1) * 100; 
                gamePanel.pieza[piezaSeleccionadaY - 2][piezaSeleccionadaX + 1] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                piezaSeleccionadaY = piezaSeleccionadaY - 2;
                piezaSeleccionadaX = piezaSeleccionadaX + 1;
                if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos
                actualizarPosicionalSoltar = false;
                piezaSelccionada = false;
            }//if
        }//ifsoltar
    }

}//class Caballo();
class peon extends piezaBase{//clase para el peon;
    public peon(int posx, int posy, int alto, int ancho, String ruta){
        super(posx,posy,alto,ancho,ruta);
    }
    public void mover(GamePanel gamePanel){ //movimiento del peon;+
        
            if(MouseEvents.leftClick){//logica cuando mantenemos el click presionado 
              
                indiceX = Math.abs(MouseEvents.mouseX / 100);  //transformando la posicion en X del mouse en un indice.
                indiceY = Math.abs(MouseEvents.mouseY / 100);  //transformando la posicion en Y del mouse en un indice.
               
                if(!piezaSelccionada && gamePanel.pieza[indiceY][indiceX] != null && gamePanel.pieza[indiceY][indiceX].blanco == true && turnoBlancas == true||
                !piezaSelccionada && gamePanel.pieza[indiceY][indiceX] != null && gamePanel.pieza[indiceY][indiceX].blanco == false && turnoBlancas == false){// consiguiendo el indice de la pieza seleccionada.
                    piezaSeleccionadaX = indiceX;
                    piezaSeleccionadaY = indiceY;
                    piezaSelccionada = true;
                    inicializado = true;
                }
               
                if(turnoBlancas && gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].blanco || //la pieza sigue el raton
                !turnoBlancas && !gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].blanco){
                    gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = MouseEvents.mouseX - 50;
                    gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = MouseEvents.mouseY - 50;
                   // System.out.println(gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre);
                }
                actualizarPosicionalSoltar = true;
            }//ifclick


            if(!MouseEvents.leftClick && actualizarPosicionalSoltar){//logica para cuando soltamos el click

                
               
                if(Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY - 1 
                    && gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX] == null
                    && Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.anchoCasilla / 2) / 100) == piezaSeleccionadaX 
                    && gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].blanco
                    &&gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonBlanco"){//cuando soltamos el peon blanco y este esta en la casilla siguinte y no hay pieza de ningun color
                
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY - 1) * 100;
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX * 100); 
                        gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                        piezaSeleccionadaY = piezaSeleccionadaY - 1;
                        if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos
                        actualizarPosicionalSoltar = false;
                        piezaSelccionada = false;

                }else if(gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nmovimientos == 0
                    &&Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY - 2 
                    && gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX] == null 
                    && gamePanel.pieza[piezaSeleccionadaY - 2][piezaSeleccionadaX] == null
                    && Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.anchoCasilla / 2) / 100) == piezaSeleccionadaX 
                    && gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].blanco
                    &&gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonBlanco" && gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nmovimientos == 0){//cuando soltamos el peon blanco dos casillas mas alante y este esta en la casilla siguinte y no hay pieza de ningun color
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nmovimientos++;
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY - 2) * 100;
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX * 100); 
                        gamePanel.pieza[piezaSeleccionadaY - 2][piezaSeleccionadaX] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                        piezaSeleccionadaY = piezaSeleccionadaY - 2;
                        if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos
                        actualizarPosicionalSoltar = false;
                        piezaSelccionada = false;
                }else if(Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY + 1 
                        && gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX] == null
                        && Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.anchoCasilla / 2) / 100) == piezaSeleccionadaX 
                        && !gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].blanco
                        && gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonNegro"  ){//cuando soltamos el peon negro y este esta en la casilla siguinte y no hay pieza de ningun color
                
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY + 1) * 100;
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX * 100); 
                            gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                            piezaSeleccionadaY = piezaSeleccionadaY + 1;
                            if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos
                            actualizarPosicionalSoltar = false;
                            piezaSelccionada = false;

                }else if(gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nmovimientos == 0
                    &&Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY + 2 
                    && gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX] == null 
                    && gamePanel.pieza[piezaSeleccionadaY + 2][piezaSeleccionadaX] == null
                    && Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.anchoCasilla / 2) / 100) == piezaSeleccionadaX 
                    && !gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].blanco
                    &&gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonNegro" && gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nmovimientos == 0){//cuando soltamos el peon blanco dos casillas mas alante y este esta en la casilla siguinte y no hay pieza de ningun color
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nmovimientos++;
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY + 2) * 100;
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX * 100); 
                        gamePanel.pieza[piezaSeleccionadaY + 2][piezaSeleccionadaX] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                        gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                        piezaSeleccionadaY = piezaSeleccionadaY + 2;
                        if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos
                        actualizarPosicionalSoltar = false;
                        piezaSelccionada = false;
                }else if(Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY + 1
                        &&Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaX - 1 
                        &&gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX - 1] != null 
                        &&gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX - 1].blanco 
                        &&gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonNegro"){ // cuando el peon negro se va a comer al peon blanco de la izquierda
      
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY + 1) * 100;
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX - 1) * 100; 
                            gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX - 1] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                            piezaSeleccionadaY = piezaSeleccionadaY + 1;
                            piezaSeleccionadaX = piezaSeleccionadaX - 1;
                            if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos

                }else if(Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY + 1
                        &&Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaX +1 
                        &&gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX + 1] != null 
                        && gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX + 1].blanco
                        &&gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonNegro"){ // cuando el peon negro se va a comer al peon blanco de la derecha
      
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY + 1) * 100;
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX + 1) * 100; 
                            gamePanel.pieza[piezaSeleccionadaY + 1][piezaSeleccionadaX + 1] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                            piezaSeleccionadaY = piezaSeleccionadaY + 1;
                            piezaSeleccionadaX = piezaSeleccionadaX + 1;
                            if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos

                }else if(Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY - 1
                        &&Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaX - 1 
                        &&gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX - 1] != null 
                        &&!gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX - 1].blanco
                        &&gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonBlanco"){ // cuando el peon blanco se va a comer al peon negro de la izquierda.
      
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY - 1) * 100;
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX - 1) * 100; 
                            gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX - 1] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                            piezaSeleccionadaY = piezaSeleccionadaY - 1;
                            piezaSeleccionadaX = piezaSeleccionadaX - 1;
                            if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos

                }else if(Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaY - 1
                        &&Math.abs((gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx + tablero.altoCasilla / 2) / 100) == piezaSeleccionadaX + 1 
                        &&gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX + 1] != null 
                        &&!gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX + 1].blanco
                        &&gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].nombre == "peonBlanco"){ // cuando el peon blanco se va a comer al peon negro.de la derecha
      
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = (piezaSeleccionadaY -1) * 100;
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = (piezaSeleccionadaX +1) * 100; 
                            gamePanel.pieza[piezaSeleccionadaY - 1][piezaSeleccionadaX + 1] = gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX];
                            gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX] = null;
                            piezaSeleccionadaY = piezaSeleccionadaY - 1;
                            piezaSeleccionadaX = piezaSeleccionadaX + 1;
                            if(turnoBlancas){turnoBlancas = false;}else{turnoBlancas = true;}//actualizando los turnos

                }else{//si soltamos el peon en cualquier otra casilla que no sea valida
                    gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posx = piezaSeleccionadaX * 100;
                    gamePanel.pieza[piezaSeleccionadaY][piezaSeleccionadaX].posy = piezaSeleccionadaY * 100;
                    piezaSelccionada  = false;
                }              
            }//ifsoltar;
        
        }//mover();
}//class peon;
class torre extends piezaBase{//clase para la torre;
    public torre(int posx,int posy, int alto, int ancho, String ruta){
        super(posx, posy,alto,ancho,ruta);

    }
}//class torre;
class alfil extends piezaBase{//clase para el alfil
    public alfil(int posx,int posy, int alto, int ancho,String ruta){
        super(posx,posy,alto,ancho,ruta);

    }
}//class alfil;
class rey extends piezaBase{//clase para el rey;
    public rey(int posx,int posy, int alto, int ancho,String ruta){
        super(posx,posy,alto,ancho,ruta);

    }
}//class rey;
class reina extends piezaBase{//clase para la reina;
    public reina(int posx,int posy, int alto, int ancho,String ruta){
        super(posx,posy,alto,ancho,ruta);

    }
}//class reina;


