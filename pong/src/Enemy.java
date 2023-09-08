import java.awt.*;
import java.util.Random;

//spelare klass
public class Enemy extends GameObject {
    Handler handler;
    Random CoinDrop = new Random();
    //definerar objektet
    public Enemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(this.getId()==ID.Enemy1) {
                HP = 500;
            }
            if(this.getId()==ID.Enemy2) {
                HP = 1500;
            }
            if(this.getId()==ID.Enemy3) {
                HP = 10000;
            }
        }
    }
    public static boolean EnemyMoveLock = true;
    public static boolean EnemyAttackLock = false;
    public static boolean EnemyDead = false;
    public static int EnemyDmg = 20;
    public static int EnemySpeed = 1;

    //metod getbounds ger klassen en rektangel (används för collision)
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    //metod tick updaterar klassens värden (hastighet, trail)
    public void tick() {

        //position
        x += velX;
        y += velY;

        //max position och min position(boundries)
        x = Game.clamp(x,-1, Game.WIDTH-34);
        y = Game.clamp(y, -1, Game.HEIGHT-69);


        //upprepar kollsion metod varje tick
        collision();
        tracker();

        //Enemy Dead & healing spawn

        if (this.HP <= 0) {
            Spawner.EnemyCount--;
            int RandomCoinDrop = CoinDrop.nextInt(5);
            Game.Coins = Game.Coins + RandomCoinDrop + 1;
            Random rand1 = new Random();
            int healnr = rand1.nextInt(100);


            if (healnr >= 0 && healnr < 90){
                healnr = 0;
            }
            if (healnr >= 90 && healnr < 98){
                handler.addObject(new Bandage(this.x,this.y,ID.Bandage,handler));
                healnr = 0;
            }
            if (healnr >= 98){
                handler.addObject(new Medkit(this.x,this.y,ID.Medkit,handler));
                healnr = 0;
            }


            //Används endast för testing
            /*if (healnr > 50){
                handler.addObject(new Medkit(this.x,this.y,ID.Medkit,handler));
                healnr = 0;
            }else if (healnr <= 50){
                handler.addObject(new Bandage(this.x,this.y,ID.Bandage,handler));
                healnr = 0;
            }
             */

            handler.removeObject(this);

        }
    }

    //metod collision kollar efter objekt genom att sortera efter ID, sedan kollar om deras rektanglar kolliderar med klass Player.
    private void collision(){

        //kollar efter spelobjekt
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId()==ID.Player1){
                if (getBounds().intersects(tempObject.getBounds())){
                    if (EnemyMoveLock == false) {
                        if (EnemyAttackLock == false) {
                            Player.HP -= EnemyDmg;
                            EnemyAttackLock = true;
                            new Thread(()->{
                                try {
                                    Thread.sleep(800);
                                    EnemyAttackLock = false;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                    }
                }
            }

        }
    }

    private void tracker() {
        if (EnemyAttackLock == false && EnemyMoveLock == false) {
            //kollar efter spelobjekt
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject tempObject2 = handler.object.get(j);
                    if (getBounds().intersects(tempObject.getBounds())) {
                        if (tempObject.getId() == ID.Enemy1 || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
                            if (tempObject.x < this.x) velX = 1;
                            if (tempObject.y < this.y) velY = 1;
                            if (tempObject.x > this.x) velX = -1;
                            if (tempObject.y > this.y) velY = -1;

                        }
                    }
                    if (tempObject.getId() == ID.Player1) {
                        if (tempObject2.getId() == ID.Enemy1) {
                            if (tempObject.x < this.x) velX = -2;
                            if (tempObject.y < this.y) velY = -2;
                            if (tempObject.x > this.x) velX = 2;
                            if (tempObject.y > this.y) velY = 2;
                            if (tempObject.x == this.x) velY = 0;
                            if (tempObject.y == this.y) velY = 0;
                            EnemyDmg = 40;
                        }
                        if (tempObject2.getId() == ID.Enemy2) {
                            if (tempObject.x < this.x) velX = -3;
                            if (tempObject.y < this.y) velY = -3;
                            if (tempObject.x > this.x) velX = 3;
                            if (tempObject.y > this.y) velY = 3;
                            if (tempObject.x == this.x) velY = 0;
                            if (tempObject.y == this.y) velY = 0;
                            EnemyDmg = 20;
                        }
                        if (tempObject2.getId() == ID.Enemy3) {
                            if (tempObject.x < this.x) velX = -2;
                            if (tempObject.y < this.y) velY = -2;
                            if (tempObject.x > this.x) velX = 2;
                            if (tempObject.y > this.y) velY = 2;
                            if (tempObject.x == this.x) velY = 0;
                            if (tempObject.y == this.y) velY = 0;
                            EnemyDmg = 50;
                        }
                    }
                }
            }
        }
    }



    // Metoden render uppdaterar klassens utseende (färg, form)
    public void render(Graphics g){
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            //Enemy 1
            if (tempObject.getId() == ID.Enemy1) {

                g.setColor(Color.red);
                g.fillRect(tempObject.x, tempObject.y, 32, 32);
                g.fillRect(tempObject.x, tempObject.y-10, (tempObject.HP*100)/1650+1, 5);
                g.setColor(Color.white);
                g.drawRect(tempObject.x-2, tempObject.y-2, 34, 34);
            }
            //Enemy 2
            if (tempObject.getId() == ID.Enemy2) {

                g.setColor(Color.BLUE);
                g.fillRect(tempObject.x, tempObject.y, 32, 32);
                g.setColor(Color.RED);
                g.fillRect(tempObject.x, tempObject.y-10, (tempObject.HP*100)/4800+1, 5);
                g.setColor(Color.white);
                g.drawRect(tempObject.x-2, tempObject.y-2, 34, 34);
            }
            //Enemy 3
            if (tempObject.getId() == ID.Enemy3) {

                g.setColor(Color.GRAY);
                g.fillRect(tempObject.x, tempObject.y, 32, 32);
                g.setColor(Color.RED);
                g.fillRect(tempObject.x, tempObject.y-10, (tempObject.HP*100)/32000+1, 5);
                g.setColor(Color.white);
                g.drawRect(tempObject.x-2, tempObject.y-2, 34, 34);
            }
        }
    }
}

