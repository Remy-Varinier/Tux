package management;

import java.util.ArrayList;
import java.util.Random;

public class Dico {
    private ArrayList<String> listLevel1;
    private ArrayList<String> listLevel2;
    private ArrayList<String> listLevel3;
    private ArrayList<String> listLevel4;
    private ArrayList<String> listLevel5;
    private String pathToDicoFile;
    
    public Dico(String pathToDicoFile){
        this.pathToDicoFile = pathToDicoFile;
        this.listLevel1 = new ArrayList();
        this.listLevel2 = new ArrayList();
        this.listLevel3 = new ArrayList();
        this.listLevel4 = new ArrayList();
        this.listLevel5 = new ArrayList();
    }
    
    public String getWordFromListLevel(int level){
        Random r = new Random();
        switch(level){
            case 1:
                return this.listLevel1.get(r.nextInt(listLevel1.size()));
            case 2:
                return this.listLevel2.get(r.nextInt(listLevel1.size()));
            case 3:
                return this.listLevel3.get(r.nextInt(listLevel1.size()));
            case 4:
                return this.listLevel4.get(r.nextInt(listLevel1.size()));
            case 5:
                return this.listLevel5.get(r.nextInt(listLevel1.size()));
            default:
        }
        return null ;
    }
    
    public boolean addWordToDico(int level, String word){
        switch(level){
            case 1:
                this.listLevel1.add(word);
                break;
            case 2:
                this.listLevel2.add(word);
                break;
            case 3:
                this.listLevel3.add(word);
                break;
            case 4:
                this.listLevel4.add(word);
                break;
            case 5:
                this.listLevel5.add(word);
                break;
            default:
        }
        return true;
    }
    
    public String getPathToDicoFile(){
        return this.pathToDicoFile;
    }
    
}
