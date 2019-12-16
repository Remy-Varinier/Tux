package Test;

import management.Dico;

public class TestDico {
    
    public static void main(String[] args) {
        Dico d = new Dico("hey");
        d.addWordToDico(1, "Coucou");
        d.addWordToDico(1, "blablabla");
        System.out.println(d.getWordFromListLevel(1));
        System.out.println(d.getWordFromListLevel(1));
        System.out.println(d.getWordFromListLevel(1));
        System.out.println(d.getWordFromListLevel(1));
    }
}
