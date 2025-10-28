import GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class MouseEvents extends MouseAdapter{
    static boolean leftClick = false;
    static boolean leftClickReleased = false;
    static int mouseX;
    static int mouseY;
    public static boolean piezaSelccionada;
 
    public MouseEvents(GamePanel gamepanel){
        if(gamepanel != null){
            gamepanel.addMouseListener(this);
            gamepanel.addMouseMotionListener(this);
        }else{
            System.out.println("Error:GamePanel es null");
        }

        }
    @Override
    public void mousePressed(MouseEvent e){
        leftClick = true;
        //System.out.println("Click en el panel -> x=" + mouseX + " y=" + mouseY);

    }
    @Override
    public void mouseReleased(MouseEvent e){
       leftClick = false;
       leftClickReleased = true;
       
    }
    @Override
    public void mouseMoved(MouseEvent e){
        if(e.getX() < 800 && e.getX() > 0 && e.getY() > 0 && e.getY() < 800){
            MouseEvents.mouseX = e.getX();
            MouseEvents.mouseY = e.getY();
            
        }
        
     
    }
    @Override
    public void mouseDragged(MouseEvent e){
     if(e.getX() < 800 && e.getX() > 0 && e.getY() > 0 && e.getY() < 800){
        MouseEvents.mouseX = e.getX();
        MouseEvents.mouseY = e.getY();
    }
    }
    
}

