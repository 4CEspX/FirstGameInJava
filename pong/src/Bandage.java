import java.awt.*;
import java.util.Random;

//spelare klass
public class Bandage extends GameObject {
    Handler handler;

    //definerar objektet
    public Bandage(int x, int y, ID id, Handler handler){

        super(x,y,id);
        this.handler = handler;


    }

    public static int width = 32;
    public static int height = 32;



    //metod getbounds ger klassen en rektangel (används för collision)
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }

    //metod tick updaterar klassens värden (hastighet, trail)
    public void tick() {

        //position
        x += velX;
        y += velY;




        //upprepar kollsion metod varje tick
        collision();
    }

    //metod collision kollar efter objekt genom att sortera efter ID, sedan kollar om deras rektanglar kolliderar med klass Player.
    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId()==ID.Player1){
                if (getBounds().intersects(tempObject.getBounds())){

                    Player.BandageAmt++;
                    handler.removeObject(this);
                    if (HUD.MedkitSlot == HUD.BandageSlot && HUD.MedkitSlot > 0){
                        HUD.BandageSlot++;}
                }
            }
        }

    }

    // Metoden render uppdaterar klassens utseende (färg, form)
    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRoundRect(x,y,10,20,5,5);
    }
}

