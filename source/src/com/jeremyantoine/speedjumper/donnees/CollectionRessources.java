package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

public class CollectionRessources {
    private static final List<String> lesCartes = new ArrayList<>();
    static {
        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carteTest.csv")).getPath());
    }

    private static final Map<String, Dimension> lesJeuxDeTuiles = new HashMap<>();
    static {
        lesJeuxDeTuiles.put(Objects.requireNonNull(CollectionRessources.class.getResource("/images/tilesets/caverne_moussue.png")).toExternalForm(),
                new Dimension(64, 64));
    }



    private static final List<String> leJoueurF = new ArrayList<>();
    static {
        File[] f = new File[0];
        try {
            f = new File(Objects.requireNonNull(CollectionRessources.class.getResource("/images/personnages/perso1/")).toURI()).listFiles();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //voir ce que fais assert
        assert f != null;
        for (File file : f ){
            leJoueurF.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/personnages/perso1/"+file.getName())).toExternalForm());

        }
    }


    private static final List<List<String>> leJoueur = new ArrayList<>();
    static {
        leJoueur.add(leJoueurF);
    }



    private static final String recuperateurDeTouches = Objects.requireNonNull(CollectionRessources.class.getResource("/touches.txt")).getPath();

    public static List<String> getLesCartes() {
        return lesCartes;
    }

    public static Map<String, Dimension> getLesJeuxDeTuiles() {
        return lesJeuxDeTuiles;
    }

    /*public static List<String> getLesEnnemis() {
        return lesEntites;
    }*/

    public  static  List<List<String>> getLeJoueur(){
        //A voir si c'est bon
        return leJoueur;
    }

    public static String getRecuperateurDeTouches() {
        return recuperateurDeTouches;
    }

    }
