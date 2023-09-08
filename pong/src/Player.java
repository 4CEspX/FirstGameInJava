import java.awt.*;
import java.util.Random;

//spelare klass
public class Player extends GameObject {
    Handler handler;

    //definerar objektet
    public Player(int x, int y, ID id, Handler handler){

        super(x,y,id);
        this.handler = handler;
    }
    public static int HP = 100;
    public static int MaxHP = 100;
    public static boolean PlayerMovelock = false;

    //Inventory Items
    public static int BandageAmt = 0;
    public static int MedkitAmt = 0;

    //metod getbounds ger klassen en rektangel (används för collision)
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    //metod tick updaterar klassens värden (hastighet, trail)
    public void tick() {

        if(HP >= MaxHP) {
            HP=MaxHP;
        }
        //position
        x += velX;
        y += velY;


        //max position och min position(boundries)
        x = Game.clamp(x,0, Game.WIDTH-35);
        y = Game.clamp(y, 0, Game.HEIGHT-70);


        //upprepar kollsion metod varje tick
        collision();

        if (HP <= 0) {
            System.exit(1);
        }
    }

    //metod collision kollar efter objekt genom att sortera efter ID, sedan kollar om deras rektanglar kolliderar med klass Player.
    private void collision(){

        //kollar efter spelobjekt
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId()==ID.Enemy1){

            }
        }
    }

    // Metoden render uppdaterar klassens utseende (färg, form)
    public void render(Graphics g){
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //Spelare 1
            if (tempObject.getId() == ID.Player1) {

                g.setColor(Color.green);
                g.fillRect(tempObject.x,tempObject.y,32,32);
            }
        }
    }
}

