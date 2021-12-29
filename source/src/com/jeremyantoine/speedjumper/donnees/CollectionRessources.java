package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionRessources {
    private static List<String> lesCartes = new ArrayList<>();
    static {
        lesCartes.add("../../../../../ressources/cartes/carteTest.csv");
    }

    private static Map<String, Dimension> lesJeuxDeTuiles = new HashMap<>();
    static {
        lesJeuxDeTuiles.put("../../../../../ressources/tilesets/tileset_1.txt", new Dimension(512, 512));
    }

    private static List<String> lesEntites = new ArrayList<>();
    static {
        lesEntites.add("../../../../../ressources/tilesets/perso.png");
    }

    public static List<String> getLesCartes() {
        return lesCartes;
    }

    public static Map<String, Dimension> getLesJeuxDeTuiles() {
        return lesJeuxDeTuiles;
    }

    public static List<String> getLesEnnemis() {
        return lesEntites;
    }
}
