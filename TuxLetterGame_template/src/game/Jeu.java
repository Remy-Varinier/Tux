package game;

import env3d.Env;
import java.text.SimpleDateFormat;
import java.util.Date;
import management.DevineLeMot;
import management.Dico;
import static management.LectureClavier.lireChaine;
import static management.LectureClavier.lireEntier;
import static management.LectureClavier.lireOuiNon;
import management.Letter;
import management.Partie;
import management.Profile;

public class Jeu {

    private Env env = null;
    private boolean finished;
    private Tux tux = null;
    private DevineLeMot devineLeMot;
    private int level;
    private Dico dico;
    private Room room;
    private Profile profile;

    public Jeu() {
        this.profile = new Profile("src/management/profile.xml");
        this.level = this.profile.getLastLevel();
        System.out.println(this.level);
        this.dico = new Dico("filename");
        this.dico.addWordToDico(1, "hey");
        this.dico.addWordToDico(2, "matin");
        this.dico.addWordToDico(2, "soir");
        this.dico.addWordToDico(3, "musique");
        this.dico.addWordToDico(2, "joie");
        this.dico.addWordToDico(4, "heureux");
        this.dico.addWordToDico(3, "amour");
        this.dico.addWordToDico(3, "famille");
        this.dico.addWordToDico(1, "fin");
    }

    void jouer() {
        // Create the new environment.  Must be done in the same
        // method as the game loop
        Partie p = null;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String mot= "";
        do {
            this.level = lireEntier("Niveau");
            mot = this.dico.getWordFromListLevel(level);
            if(this.env == null){
                this.env = new Env();
            }
            else{
                this.env.restart();
            }
            p = new Partie(simpleDateFormat.format(new Date()),mot,this.level);
            // Instanciate a room 
            this.room = new Room();
            this.env.setRoom(this.room);

            //initialize
            this.tux = new Tux(10, 1, 10);

            this.devineLeMot = new DevineLeMot(mot, this.env, this.room, this.tux, level);
            this.devineLeMot.jouer();
            p.setTemps(this.devineLeMot.temps);
            p.setTrouve(this.devineLeMot.nbLettresRestantes);
            this.profile.ajouterPartie(p);
            System.out.println("Vous avez joué avec le mot "+mot+" de niveau "+this.level+"\n Vous avez trouvé "+p.getTrouve()+"% en "+p.getTemps()+" secondes");
        }while(lireOuiNon("Veux tu rejouer?"));
        
        this.profile.save("src/management/profile.xml");
    }

}
