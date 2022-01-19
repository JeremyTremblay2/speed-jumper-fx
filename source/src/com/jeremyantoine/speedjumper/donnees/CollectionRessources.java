package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class CollectionRessources {
    private static CollectionRessources instance;

    private List<String> lesCartes;
    private static Map<String, Dimension> lesJeuxDeTuiles;
    private Map<NomFenetre, URL> lesCheminsDesVues;
    private Map<NomFenetre, String> lesCheminsDesStyles;

    private List<String> lesEntites;
    private String fichierConfigurationTouches;

    private CollectionRessources() {
        lesJeuxDeTuiles = new HashMap<>();
        lesEntites = new ArrayList<>();
        lesCartes = new ArrayList<>();
        lesCheminsDesVues = new HashMap<>();
        lesCheminsDesStyles = new HashMap<>();
        ajouterDonnees();
    }

    public static CollectionRessources getInstance() {
        if (instance == null) {
            instance = new CollectionRessources();
        }
        return instance;
    }

    public List<String> getLesCartes() {
        return lesCartes;
    }

    public static Map<String, Dimension> getLesJeuxDeTuiles() {
        return lesJeuxDeTuiles;
    }

    public List<String> getLesEntites() {
        return lesEntites;
    }

    public String getFichierConfigurationTouches() {
        return fichierConfigurationTouches;
    }

    public Map<NomFenetre, URL> getLesCheminsDesVues() {
        return lesCheminsDesVues;
    }

    public Map<NomFenetre, String> getLesCheminsDesStyles() {
        return lesCheminsDesStyles;
    }

    private void ajouterDonnees() {
        fichierConfigurationTouches = Objects.requireNonNull(CollectionRessources.class.getResource("/touches.txt")).getPath();

        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carteTest.csv")).getPath());

        lesJeuxDeTuiles.put(Objects.requireNonNull(CollectionRessources.class.getResource("/images/tilesets/caverne_moussue.png")).toExternalForm(),
                new Dimension(64, 64));

        lesEntites.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/perso.png")).toExternalForm());

        lesCheminsDesVues.put(NomFenetre.MENU_PRINCIPAL, Objects.requireNonNull(CollectionRessources.class.getResource("/menuPrincipal.fxml")));
        lesCheminsDesVues.put(NomFenetre.MENU_OPTIONS, Objects.requireNonNull(CollectionRessources.class.getResource("/option.fxml")));
        lesCheminsDesVues.put(NomFenetre.MENU_DEBUT_NIVEAU, Objects.requireNonNull(CollectionRessources.class.getResource("/menuDebutNiveau.fxml")));
        lesCheminsDesVues.put(NomFenetre.MENU_JOUER, Objects.requireNonNull(CollectionRessources.class.getResource("/menuJouer.fxml")));
        lesCheminsDesVues.put(NomFenetre.MENU_PAUSE, Objects.requireNonNull(CollectionRessources.class.getResource("/pause.fxml")));

        lesCheminsDesStyles.put(NomFenetre.MENU_PRINCIPAL, Objects.requireNonNull(CollectionRessources.class.getResource("/style/styleMenuPrincipal.css")).toExternalForm());
        lesCheminsDesStyles.put(NomFenetre.MENU_JOUER, Objects.requireNonNull(CollectionRessources.class.getResource("/style/styleChoixNiveau.css")).toExternalForm());
        lesCheminsDesStyles.put(NomFenetre.MENU_OPTIONS, Objects.requireNonNull(CollectionRessources.class.getResource("/style/styleOption.css")).toExternalForm());
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

    private static final String recuperateurDeTouches = Objects.requireNonNull(CollectionRessources.class.getResource("/touches.txt")).getPath();

    private static final List<List<String>> leJoueur = new ArrayList<>();
    static {
        leJoueur.add(leJoueurF);
    }

    public  static  List<List<String>> getLeJoueur(){
        //A voir si c'est bon
        return leJoueur;
    }

    public static String getRecuperateurDeTouches() {
        return recuperateurDeTouches;
    }



}
