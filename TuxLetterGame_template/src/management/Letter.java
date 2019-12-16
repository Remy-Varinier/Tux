package management;
import env3d.EnvObject;

public class Letter extends EnvObject {
    
    private char letter;
    
    public Letter(char l, double x, double y){
        setX(x);
        setY(1);
        setZ(y);
        setScale(1);
        if(l != ' ')
            setTexture("models/letter/"+l+".png");
        else{
            setTexture("models/letter/cube.png");
        }
        setModel("models/letter/cube.obj");
        this.letter = l;
    }
}
