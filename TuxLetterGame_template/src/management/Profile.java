/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package management;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;



/**
 *
 * @author varinier
 */
public class Profile {
    private String nom;
    private String dateDeNaissance;
    private String avatar;
    private ArrayList<Partie> parties = new ArrayList<Partie>();

    public Profile(String nom, String dateDeNaissance) {
        this.nom = nom;
        this.dateDeNaissance = dateDeNaissance;
    }
    
    public Profile(String filename){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder p = dbFactory.newDocumentBuilder();
            Document doc = p.parse(filename);
            Element profile = (Element) doc.getDocumentElement();
            Element games = (Element) profile.getElementsByTagName("games").item(0);
            
            for(int i = 0 ; i < games.getElementsByTagName("game").getLength() ; i++){
                this.parties.add(new Partie((Element) games.getElementsByTagName("game").item(i)));
                
            }
            this.avatar = ((Element) profile.getElementsByTagName("avatar").item(0)).getTextContent();
            this.dateDeNaissance = this.xmlDateToProfileDate(((Element) profile.getElementsByTagName("birthday").item(0)).getTextContent());
            this.nom = ((Element) profile.getElementsByTagName("name").item(0)).getTextContent();
            
        }
         catch (Exception e) {System.out.println("erreur : "+ e);}
    }
    
    public void ajouterPartie(Partie p){
        this.parties.add(p);
    }
    
    public int getLastLevel(){
        if(this.parties.size() != 0)
            return this.parties.get(this.parties.size()-1).getNiveau();
        return 1;
    }
    
    @Override
    public String toString() {
        return "Profile{" + "nom=" + nom + ", dateDeNaissance=" + dateDeNaissance + ", avatar=" + avatar + ", parties=" + parties + '}';
    }
    
    public void save(String filename){
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder p = dbFactory.newDocumentBuilder();
            Document doc = p.newDocument();
            Element profile = doc.createElementNS("Profile", "profile");
            Element name = doc.createElementNS("Profile", "name");
            name.setTextContent(this.nom);
            Element avatar = doc.createElementNS("Profile", "avatar");
            avatar.setTextContent(this.avatar);
            Element birthday = doc.createElementNS("Profile", "birthday");
            birthday.setTextContent(profileDateToXmlDate(this.dateDeNaissance));
            Element games = doc.createElementNS("Profile", "games");
            for(int i = 0; i < this.parties.size(); i++)
                games.appendChild(this.parties.get(i).getDomElement(doc));
            profile.appendChild(name);
            profile.appendChild(avatar);
            profile.appendChild(birthday);
            profile.appendChild(games);
            doc.appendChild(profile);
            File file = new File(filename);
            file.createNewFile();
            
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            Writer out = new StringWriter();
            tf.transform(new DOMSource(doc), new StreamResult(out));
            
            FileWriter writer = new FileWriter(file);
            writer.write(out.toString());
            writer.close();
          }
        catch(Exception e){System.out.println("erreur : "+ e);}
    }
    
    /// Takes a date in XML format (i.e. ????-??-??) and returns a date
    /// in profile format: dd/mm/yyyy
    public static String xmlDateToProfileDate(String xmlDate) {
        String date;
        // récupérer le jour
        date = xmlDate.substring(xmlDate.lastIndexOf("-") + 1, xmlDate.length());
        date += "/";
        // récupérer le mois
        date += xmlDate.substring(xmlDate.indexOf("-") + 1, xmlDate.lastIndexOf("-"));
        date += "/";
        // récupérer l'année
        date += xmlDate.substring(0, xmlDate.indexOf("-"));

        return date;
    }

    public static String profileDateToXmlDate(String profileDate) {
        String date;
        // Récupérer l'année
        date = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());
        date += "-";
        // Récupérer  le mois
        date += profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));
        date += "-";
        // Récupérer le jour
        date += profileDate.substring(0, profileDate.indexOf("/"));

        return date;
    }
    
}
