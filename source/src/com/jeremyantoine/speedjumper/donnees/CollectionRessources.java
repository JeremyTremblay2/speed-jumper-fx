package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionRessources {
    private static final List<String> lesCartes = new ArrayList<>();
    static {
        lesCartes.add(CollectionRessources.class.getResource("/cartes/carteTest.csv").getPath());
    }

    private static final Map<String, Dimension> lesJeuxDeTuiles = new HashMap<>();
    static {
        lesJeuxDeTuiles.put(CollectionRessources.class.getResource("/images/tilesets/caverne_moussue.png").toExternalForm(),
                new Dimension(64, 64));
    }

    private static final List<String> lesEntites = new ArrayList<>();
    static {
        lesEntites.add(CollectionRessources.class.getResource("/images/perso.png").toExternalForm());
    }

    private static final String recuperateurDeTouches = CollectionRessources.class.getResource("/touches.txt").getPath();

    public static List<String> getLesCartes() {
        return lesCartes;
    }

    public static Map<String, Dimension> getLesJeuxDeTuiles() {
        return lesJeuxDeTuiles;
    }

    public static List<String> getLesEnnemis() {
        return lesEntites;
    }

    public static String getRecuperateurDeTouches() {
        return recuperateurDeTouches;
    }
}
