package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;

import java.net.URL;
import java.util.*;

public class CollectionRessources {
    private static CollectionRessources instance;

    private List<String> lesCartes;
    private Map<String, Dimension> lesJeuxDeTuiles;
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

    private void ajouterDonnees() {
        fichierConfigurationTouches = Objects.requireNonNull(CollectionRessources.class.getResource("/touches.txt")).getPath();

        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carteTest.csv")).getPath());

        lesJeuxDeTuiles.put(Objects.requireNonNull(CollectionRessources.class.getResource("/images/tilesets/caverne_moussue.png")).toExternalForm(),
                new Dimension(64, 64));

        lesEntites.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/perso.png")).toExternalForm());

        lesCheminsDesVues.put(NomFenetre.MENU_JOUER, Objects.requireNonNull(CollectionRessources.class.getResource("/menuJouer.fxml")));
        lesCheminsDesVues.put(NomFenetre.MENU_PRINCIPAL, Objects.requireNonNull(CollectionRessources.class.getResource("/menuPrincipal.fxml")));
        lesCheminsDesVues.put(NomFenetre.MENU_OPTIONS, Objects.requireNonNull(CollectionRessources.class.getResource("/option.fxml")));
        lesCheminsDesVues.put(NomFenetre.MENU_PAUSE, Objects.requireNonNull(CollectionRessources.class.getResource("/pause.fxml")));
        lesCheminsDesVues.put(NomFenetre.UC_BOUTON_MENU_JEU, Objects.requireNonNull(CollectionRessources.class.getResource("/UCboutonMenuJeu.fxml")));

        lesCheminsDesStyles.put(NomFenetre.MENU_PRINCIPAL, Objects.requireNonNull(CollectionRessources.class.getResource("/style/styleMenuPrincipal.css")).toExternalForm());
        lesCheminsDesStyles.put(NomFenetre.MENU_JOUER, Objects.requireNonNull(CollectionRessources.class.getResource("/style/styleChoixNiveau.css")).toExternalForm());
        lesCheminsDesStyles.put(NomFenetre.MENU_OPTIONS, Objects.requireNonNull(CollectionRessources.class.getResource("/style/styleOption.css")).toExternalForm());
    }
}
