package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.controleurs.NomFenetre;
import com.jeremyantoine.speedjumper.logique.Dimension;

import java.net.URL;
import java.util.*;

/**
 * Classe permettant de gerer les différentes collection de ressources
 */
public class CollectionRessources {
    private static CollectionRessources instance;

    private final List<String> lesCartes;
    private final Map<String, Dimension> lesJeuxDeTuiles;
    private final Map<NomFenetre, URL> lesCheminsDesVues;
    private final Map<NomFenetre, String> lesCheminsDesStyles;
    private final List<String> lesJoueurs;
    private final List<String> lesEntites;
    private final List<String> lesEntitesChemins;
    private final List<String> lesJeuxDeTuilesCollisions;

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

    /**
     * methode permettant d'ajouter les données dans les lites
     */
    private void ajouterDonnees() {
        fichierConfigurationTouches = Objects.requireNonNull(CollectionRessources.class.getResource("/touches.txt")).getPath();
        fichierScores = Objects.requireNonNull(CollectionRessources.class.getResource("/scores.txt")).getPath();

        lesCartes.add(Objects.requireNonNull(CollectionRessources.class.getResource("/cartes/carteTest.csv")).getPath());

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

        lesEntites.add(Objects.requireNonNull(CollectionRessources.class.getResource("/images/ennemis/slime.png")).toExternalForm());

        lesEntitesChemins.add(null);
    }
}
