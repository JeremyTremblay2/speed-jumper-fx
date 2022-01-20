package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.controleurs.NomFenetre;
import com.jeremyantoine.speedjumper.logique.Dimension;

import java.net.URL;
import java.util.*;

public class CollectionRessources {
    private static CollectionRessources instance;

    private List<String> lesCartes;
    private Map<String, Dimension> lesJeuxDeTuiles;
    private Map<NomFenetre, URL> lesCheminsDesVues;
    private Map<NomFenetre, String> lesCheminsDesStyles;
    private List<String> lesJoueurs;
    private List<String> lesEntites;
    private List<String> lesEntitesChemins;

    private String fichierConfigurationTouches;
    private String fichierScores;

    private CollectionRessources() {
        lesJeuxDeTuiles = new HashMap<>();
        lesEntites = new ArrayList<>();
        lesJoueurs = new ArrayList<>();
        lesCartes = new ArrayList<>();
        lesCheminsDesVues = new HashMap<>();
        lesCheminsDesStyles = new HashMap<>();
        lesEntitesChemins = new ArrayList<>();
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

    public Map<String, Dimension> getLesJeuxDeTuiles() {
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

    public List<String> getLesJoueurs() {
        return lesJoueurs;
    }

    public List<String> getLesEntitesChemins() {
        return lesEntitesChemins;
    }

    public String getFichierScores() {
        return fichierScores;
    }

    private void ajouterDonnees() {
        fichierConfigurationTouches = Objects.requireNonNull(CollectionRessources.class.getResource("/touches.txt")).getPath();
        fichierScores = Objects.requireNonNull(CollectionRessources.class.getResource("/scores.txt")).getPath();

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

        lesJoueurs.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/personnages/femme.png")).toExternalForm());

        lesEntites.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/ennemis/slime.png")).toExternalForm());

        lesEntitesChemins.add(null);
    }
}
