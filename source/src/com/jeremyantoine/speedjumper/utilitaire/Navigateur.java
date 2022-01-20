package com.jeremyantoine.speedjumper.utilitaire;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.controleurs.NomFenetre;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Stack;

/**
 * Classe permettant la navigation entre les vues
 */
public class Navigateur {
    private Stage stage;
    private Scene sceneCourante;
    private Stack<Scene> laPileDeScenes;
    private Map<NomFenetre, URL> vues;

    /**
     * Constructeur du Navigateur
     * @param stage stage d'origine
     * @throws IllegalArgumentException
     */
    public Navigateur(Stage stage) throws IllegalArgumentException {
        if (stage == null) {
            throw new IllegalArgumentException("Le stage donné en paramètre ne peut pas être null.");
        }
        this.stage = stage;
        CollectionRessources ressources = CollectionRessources.getInstance();
        vues = ressources.getLesCheminsDesVues();
        laPileDeScenes = new Stack<>();
    }

    /**
     * Methode permettant la navigation
     * @param fenetre Nom de la fenetre vers ou naviguer
     * @param controleur controleur de la vue
     */
    public void naviguerVers(NomFenetre fenetre, Object controleur) {
        Scene scene = null;
        FXMLLoader chargeur = new FXMLLoader();
        if (!vues.containsKey(fenetre)) {
            throw new IllegalArgumentException("La fenetre spécifiée ne possède pas de fichier FXML");
        }

        chargeur.setController(controleur);
        chargeur.setLocation(vues.get(fenetre));

        try {
            Parent racine = chargeur.load();
            scene = new Scene(racine);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        naviguerVers(scene);
    }

    /**
     * Methode pour la navigation
     * @param fenetre fenetre vers laquelle naviguer
     * @throws IllegalArgumentException
     */
    public void naviguerVers(NomFenetre fenetre) throws IllegalArgumentException {
        Scene scene = null;
        if (!vues.containsKey(fenetre)) {
            throw new IllegalArgumentException("La fenetre spécifiée ne possède pas de fichier FXML");
        }

        try {
            Parent racine = FXMLLoader.load(vues.get(fenetre));
            scene = new Scene(racine);
        } catch (IOException e) {
            e.printStackTrace();
        }

        naviguerVers(scene);
    }

    /**
     * Methode pour la navigation
     * @param scene scene vers laquelle naviguer
     */
    public void naviguerVers(Scene scene) {
        if (scene != null) {
            laPileDeScenes.add(scene);
            miseAJourScene();
        }
    }

    /**
     * Methode pour revenir a la vue précédente
     */
    public void faireDemiTour() {
        if (laPileDeScenes.size() != 0) {
            laPileDeScenes.pop();
            miseAJourScene();
        }
    }

    /**
     * Methode pour retourner la scene courante
     * @return la scene courante
     */
    public Scene getSceneCourante() {
        return sceneCourante;
    }

    /**
     * Methode pour recuperer le stage
     * @return le stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Methode mettant a jour la scene
     */
    private void miseAJourScene() {
        sceneCourante = laPileDeScenes.peek();
        stage.setScene(sceneCourante);
    }
}
