package game;
import env3d.Env;

public class Room {
    private int depth;
    private int height;
    private int width;
    private String textureBottom;
    private String textureNorth;
    private String textureEast;
    private String textureWest;
    private String textureTop;
    private String textureSouth;
    
    public Room(){
        this.textureBottom = "textures/skybox/default/bottom.png";
        this.textureNorth = "textures/skybox/default/north.png";
        this.textureEast = "textures/skybox/default/east.png";
        this.textureWest = "textures/skybox/default/west.png";
        this.depth = 50;
        this.width = 40;
        this.height = 50;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
}
