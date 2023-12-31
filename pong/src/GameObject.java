import java.awt.*;

//klass gameobjekt definerar alla spelobjekt
public abstract class GameObject {

    protected int x,y;
    protected ID id;
    protected int HP;
    protected int velX, velY;

    public GameObject(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    //ger spelobjekt(klasserna) metoder som behövs
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public void setX(int x){
        this.x = x;
    }
    public void setY(int u){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setVelX(int velX){
        this.velX = velX;
    }
    public void setVelY(int velY){
        this.velY = velY;
    }
    public int getVelX(){
        return velX;
    }
    public int getVelY(){
        return velY;
    }
    public void setId(ID id){this.id = id;}
    public ID getId(){ return id; }
}
