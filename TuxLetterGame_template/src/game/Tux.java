package game;
import env3d.EnvObject;


public class Tux extends EnvObject {
    
    public Tux(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
        setScale(1);
        setTexture("models/tux/tux.png");
        setModel("models/tux/tux.obj");
    }

    
    public void move(int currentKey){
        if(currentKey == 200)
        {
            this.setRotateY(180);
            this.setZ(this.getZ() - 1);
        }
        if(currentKey == 208)
        {
            this.setRotateY(0);
            this.setZ(this.getZ() + 1);
        }
        if(currentKey == 203)
        {
            this.setRotateY(-90);
            this.setX(this.getX() - 1);
        }
        if(currentKey == 205)
        {
            this.setRotateY(90);
            this.setX(this.getX() + 1);
        }
    }
}
