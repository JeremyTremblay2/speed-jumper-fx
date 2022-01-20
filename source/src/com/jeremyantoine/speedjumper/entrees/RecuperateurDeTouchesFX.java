package com.jeremyantoine.speedjumper.entrees;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.Map;

/**
 * Classe permettant de gerer les actions en rapport avec la recuperation des touches
 */
public class RecuperateurDeTouchesFX extends RecuperateurDeTouches {
    private Map<KeyCode, Touche> dicoTouches;
    private Scene sceneCourante;

    /**
     * Constructeur de la classe. Charge le dictionnaire de touche disponible
     * @param fichier fichier ou sont stocké les touches
     * @param scene scene courrante
     */
    public RecuperateurDeTouchesFX(String fichier, Scene scene) {
        sceneCourante = scene;
        ChargeurDeTouchesFX chargeur = new ChargeurDeTouchesFX();
        dicoTouches = chargeur.recupereTouches(fichier);
        if (scene != null) {
            detection();
        }
    }

    /**
     * retourne la map de touche
     * @return
     */
    public Map<KeyCode, Touche> getDicoTouches() {
        return dicoTouches;
    }

    /**
     * retourne la scene courante
     * @return
     */
    public Scene getSceneCourante() {
        return sceneCourante;
    }

    /**
     * set la scene courante et relance la détection des touches sur cette scene
     * @param sceneCourante
     */
    public void setSceneCourante(Scene sceneCourante) {
        this.sceneCourante = sceneCourante;
        detection();
    }

    /**
     * ajouter des touches dans le dictionnaire de touche
     * @param toucheFX la touche a ajouté ( son code )
     * @param touche equivalence dans l'enumeration
     */
    public void ajouteToucheFX(KeyCode toucheFX, Touche touche) {
        dicoTouches.put(toucheFX, touche);
    }

    /**
     * Methode qui renvois la liste des touches detectés
     * @return
     */
    @Override
    public List<Touche> detecte() {
        return super.getLesTouchesPressees();
    }

    /**
     *
     * @param entree
     * @return
     */
    private Touche compareEntree(KeyCode entree) {
        return dicoTouches.get(entree);
    }

    /**
     * Attache des events pour pouvoir recuperer les touches préssées pendant le jeu
     */
    public void detection() {
        sceneCourante.addEventHandler(KeyEvent.KEY_PRESSED, (cle) -> {
            KeyCode touche = cle.getCode();
            if (dicoTouches.containsKey(touche) && !lesTouchesPressees.contains(dicoTouches.get(touche))){
                lesTouchesPressees.add(dicoTouches.get(touche));
            }
        });

        sceneCourante.addEventHandler(KeyEvent.KEY_RELEASED, (cle) -> {
            KeyCode touche = cle.getCode();
            lesTouchesPressees.remove(dicoTouches.get(touche));
        });
    }
}
