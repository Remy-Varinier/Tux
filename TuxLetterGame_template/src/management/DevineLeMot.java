/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import env3d.Env;
import game.Jeu;
import game.Room;
import game.Tux;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author varinier
 */
public class DevineLeMot {
    private Env env;
    private Tux tux;
    private ArrayList<Letter> letters;
    public int nbLettresRestantes;
    public int temps = 120;
    private Chronometre chrono;
    private int level;
    
    public DevineLeMot(String mot, Env env, Room room, Tux tux, int level){
        this.level = level;
        this.letters = new ArrayList<Letter>();
        Random r = new Random();
        this.tux = tux;
        this.env = env;
        this.nbLettresRestantes = mot.length();
        Letter l;
        for(int i = 0;i < this.nbLettresRestantes;i++){
           l = new Letter(mot.charAt(i),r.nextInt(room.getWidth()-4)+2,r.nextInt(room.getHeight()-4)+2);
           this.letters.add(l);
        }
        
        this.chrono = new Chronometre(30*level);
        this.env.setCameraXYZ(20, 30, 75);
        this.env.setCameraPitch(-30);
        this.env.setDefaultControl(false);
    }

    
    public void jouer() {
        
        boolean finished = false;
        // Insert Tux
        this.env.addObject(this.tux);
        // Add the letters
        for(int i = 0;i < this.letters.size();i++){
           this.env.addObject(this.letters.get(i));
        }
    // Start chrono
       this.chrono.start();
        // The main game loop
        do {
            // Ask for user input, check if it collides and remove letters if necessary
            checkUserKey();
            if(this.tuxMeetsLetter()){
                this.env.removeObject(this.letters.get(this.letters.size()-this.nbLettresRestantes));
                this.letters.remove(this.letters.size()-this.nbLettresRestantes);
            }
               
            if (env.getKey() == 1) {
                finished = true;
            }
            this.nbLettresRestantes = this.letters.size();
            // Update display
            
        } while (this.chrono.remainsTime() && !finished && this.nbLettresRestantes != 0);
        this.chrono.stop();
        this.nbLettresRestantes = this.letters.size();
        this.temps = this.chrono.getRemainsTime();
        //Post-Process: game is finished
        //we have to keep the data to save our score (chrono, temps, nbLettresRestantes) 
        
    }
    
    public void checkUserKey(){
        env.advanceOneFrame();
        tux.move(env.getKey());
    }
    public double distance(Tux tux, Letter letter){
        return Math.sqrt((tux.getX()-letter.getX())*(tux.getX()-letter.getX())+(tux.getZ()-letter.getZ())*(tux.getZ()-letter.getZ()));
    }
    
    public boolean collision(Tux tux, Letter letter){
        if(this.distance(tux, letter)== 0){
            return true;
        }
        return false;
    }
    
    public boolean tuxMeetsLetter(){
        return collision(tux,this.letters.get(this.letters.size()-this.nbLettresRestantes));
    }
}
