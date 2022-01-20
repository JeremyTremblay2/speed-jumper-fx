package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.controleurs.NomFenetre;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;

import java.net.URL;
import java.util.*;

/**
 * Classe permettant de gerer les différentes collection de ressources
 */
public class CollectionRessources {
    private static CollectionRessources instance;

    private List<String> lesCartes;
    private Map<String, Dimension> lesJeuxDeTuiles;
    private Map<NomFenetre, URL> lesCheminsDesVues;
    private Map<NomFenetre, String> lesCheminsDesStyles;
    private List<String> lesJoueurs;
    private List<String> lesEntites;
    private List<String> lesEntitesChemins;
    private List<String> lesJeuxDeTuilesCollisions;
    private List<String> lesArrieresPlans;
    private List<Position2D> lesPointsDepart;
    private List<Position2D> lesPointsArrivee;

    private String fichierConfigurationTouches;
    private String fichierScores;

    /**
     * constructeur de la classe initialisant les différents listes
     */
    private CollectionRessources() {
        lesJeuxDeTuiles = new HashMap<>();
        lesEntites = new ArrayList<>();
        lesJoueurs = new ArrayList<>();
        lesCartes = new ArrayList<>();
        lesCheminsDesVues = new HashMap<>();
        lesCheminsDesStyles = new HashMap<>();
        lesEntitesChemins = new ArrayList<>();
        lesJeuxDeTuilesCollisions = new ArrayList<>();
        lesArrieresPlans = new ArrayList<>();
        lesPointsDepart = new ArrayList<>();
        lesPointsArrivee = new ArrayList<>();
        ajouterDonnees();
    }

    /**
     * methode retourant l'instance d'une collection de ressources
     * @return
     */
    public static CollectionRessources getInstance() {
        if (instance == null) {
            instance = new CollectionRessources();
        }
        return instance;
    }

    /**
     * retoune la liste des cartes
     * @return
     */
    public List<String> getLesCartes() {
        return lesCartes;
    }

    /**
     * retourne la map des jeux de tuiles
     * @return
     */
    public Map<String, Dimension> getLesJeuxDeTuiles() {
        return lesJeuxDeTuiles;
    }

    /**
     * retourne la liste des entites
     * @return
     */
    public List<String> getLesEntites() {
        return lesEntites;
    }

    /**
     * retounr le fichuier de config des touches
     * @return
     */
    public String getFichierConfigurationTouches() {
        return fichierConfigurationTouches;
    }

    /**
     * retourne la map des urls des vues
     * @return
     */
    public Map<NomFenetre, URL> getLesCheminsDesVues() {
        return lesCheminsDesVues;
    }

    /**
     * retourne les differents styles des vues
     * @return
     */
    public Map<NomFenetre, String> getLesCheminsDesStyles() {
        return lesCheminsDesStyles;
    }

    /**
     * retourne la liste des joueurs
     * @return
     */
    public List<String> getLesJoueurs() {
        return lesJoueurs;
    }

    /**
     * retournes les chemins des entitess
     * @return
     */
    public List<String> getLesEntitesChemins() {
        return lesEntitesChemins;
    }

    /**
     * retourne le chemin du fichier de score
     * @return
     */
    public String getFichierScores() {
        return fichierScores;
    }

    /**
     * retourne la liste des collision des tuiles
     * @return
     */
    public List<String> getLesJeuxDeTuilesCollisions() {
        return lesJeuxDeTuilesCollisions;
    }

    public List<String> getLesArrieresPlans() {
        return lesArrieresPlans;
    }

    /**
     * methode permettant d'ajouter les données dans les lites
     */
    public List<Position2D> getLesPointsDepart() {
        return lesPointsDepart;
    }

    public List<Position2D> getLesPointsArrivee() {
        return lesPointsArrivee;
    }

    private void ajouterDonnees() {
        fichierConfigurationTouches = Objects.requireNonNull(CollectionRessources.class.getResource("/touches.txt")).getPath();
        fichierScores = Objects.requireNonNull(CollectionRessources.class.getResource("/scores.txt")).getPath();

        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carte1.csv")).getPath());
        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carte2.csv")).getPath());
        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carte3.csv")).getPath());
        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carte4.csv")).getPath());

        lesJeuxDeTuiles.put(Objects.requireNonNull(CollectionRessources.class.getResource("/images/tilesets/caverne_moussue.png")).toExternalForm(),
                new Dimension(64, 64));

        lesJeuxDeTuilesCollisions.add(Objects.requireNonNull(CollectionRessources.class.getResource("/tilesets/caverne_moussue.txt")).getPath());

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

        lesEntites.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/personnages/slime.png")).toExternalForm());

        lesArrieresPlans.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/fonds/background.jpg")).toExternalForm());

        lesPointsArrivee.add(new Position2D(512, 320));
        lesPointsArrivee.add(new Position2D(128, 1792));
        lesPointsArrivee.add(new Position2D(128, 448));
        lesPointsArrivee.add(new Position2D(6336, 320));

        lesPointsDepart.add(new Position2D(128, 1664));
        lesPointsDepart.add(new Position2D(128, 128));
        lesPointsDepart.add(new Position2D(196, 5660));
        lesPointsDepart.add(new Position2D(256, 1344));

        lesEntitesChemins.add(null);
    }
}
