import java.awt.*;
import java.util.Random;

//spelare klass
public class Projectile extends GameObject {
    Handler handler;

    //definerar objektet
    public Projectile(int x, int y, ID id, Handler handler){

        super(x,y,id);
        this.handler = handler;
        if(KeyInput.projectileDirection == 1){velX=-10; velY=0;}
        if(KeyInput.projectileDirection == 2){velX=10; velY=0;}
        if(KeyInput.projectileDirection == 3){velX=0; velY=10;}
        if(KeyInput.projectileDirection == 4){velX=0; velY=-10;}


    }

    //metod getbounds ger klassen en rektangel (används för collision)
    public Rectangle getBounds() {
        return new Rectangle(x,y,5,5);
    }

    //metod tick updaterar klassens värden (hastighet, trail)
    public void tick() {

        //position
        x += velX;
        y += velY;

        if (x<0||x>640||y<0||y>640){
            handler.removeObject(this);
        }
        if (this.velX == 0 && this.velY == 0){
            handler.removeObject(this);
        }


        //upprepar kollsion metod varje tick
        collision();
    }

    //metod collision kollar efter objekt genom att sortera efter ID, sedan kollar om deras rektanglar kolliderar med klass Player.
    private void collision(){

        //kollar efter spelobjekt
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId()==ID.Enemy1){
                if (getBounds().intersects(tempObject.getBounds())){
                    System.out.println("You hit an easy enemy");
                    tempObject.HP-=Game.Damage;
                    handler.removeObject(this);
                }
            }
            if (tempObject.getId()==ID.Enemy2){
                if (getBounds().intersects(tempObject.getBounds())){
                    System.out.println("You hit a medium enemy");
                    tempObject.HP-=Game.Damage;
                    handler.removeObject(this);
                }
            }
            if (tempObject.getId()==ID.Enemy3){
                if (getBounds().intersects(tempObject.getBounds())){
                    System.out.println("You hit a difficult enemy");
                    tempObject.HP-=Game.Damage;
                    handler.removeObject(this);
                }
            }
        }

    }

    // Metoden render uppdaterar klassens utseende (färg, form)
    public void render(Graphics g){
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            //Spelare 1
            if (tempObject.getId() == ID.PP) {

                g.setColor(Color.WHITE);
                g.fillRect(tempObject.x,tempObject.y,5,5);
            }
        }
    }
}

