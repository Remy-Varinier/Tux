/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author varinier
 */
public class Partie {
    
    private String date;
    private String mot;
    private int niveau;
    private int trouve = -2;
    private int temps = -2;
    
    public Partie(String date, String mot, int niveau){
        this.date = date;
        this.mot = mot;
        this.niveau = niveau;
    }
    public Partie(Element domPartie){
        Element word = (Element) domPartie.getElementsByTagName("word").item(0);
        Element time = (Element) domPartie.getElementsByTagName("time").item(0);
        

        this.date = domPartie.getAttribute("date");
        this.mot = word.getTextContent();
        this.niveau = parseInt(word.getAttribute("level"));
        if(domPartie.getAttribute("found") != ""){
            String t = domPartie.getAttribute("found");
            t = t.substring(0, t.length()-1);
            this.trouve = parseInt(t);
        }
        if(time != null){        
            this.temps = parseInt(time.getTextContent());
        }
    }
    
    public Element getDomElement(Document doc){
        
        Element game = doc.createElement( "game");
        game.setAttribute("date", this.date);
        if(this.trouve != -2){
            game.setAttribute("found", Integer.toString(this.trouve)+"%");
        }
        Element word = doc.createElement("word");
            word.setTextContent(this.mot);
            word.setAttribute("level", Integer.toString(this.niveau));
        if(this.temps != -2){
            Element time = doc.createElement("time");
            time.setTextContent(Integer.toString(this.temps));
            game.appendChild(time);
        }
        game.appendChild(word);
        return game;
    }

    public int getNiveau() {
        return niveau;
    }
    public int getTemps() {
        return temps;
    }
    public int getTrouve() {
        return trouve;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    public void setTrouve(int nbLettreRestantes) { 
        DecimalFormat df = new DecimalFormat("###");
        int longueur = this.mot.length();
        if(nbLettreRestantes == longueur)
            this.trouve = 0;
        else{
            Double l = new Double(longueur);
            double res = ((l-nbLettreRestantes)/l)*100;
            this.trouve = parseInt(df.format(res));
        }
    }

    @Override
    public String toString() {
        return "Partie{" + "date=" + date + ", mot=" + mot + ", niveau=" + niveau + ", trouve=" + trouve + ", temps=" + temps + '}';
    }
    
    
    
}
