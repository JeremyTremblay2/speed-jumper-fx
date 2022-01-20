package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.utilitaire.DecoupeurFX;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe permettant de gerer les ressources visuel
 */
public class GestionnaireDeRessourcesFX {
    private final DecoupeurFX decoupeur = new DecoupeurFX();

    private final Map<String, Dimension> lesJeuxDeTuiles;
    private final List<Image> lesTuilesImagees;

    private final List<String> lesPersonnagesChemins;
    private final List<Image> lesPersonnagesImages;

    private final List<String> lesEnnemisChemins;
    private final List<Image> lesEnnemisImages;

    /**
     * Constructeur de la classe
     */
    public GestionnaireDeRessourcesFX() {
        CollectionRessources ressources = CollectionRessources.getInstance();

        lesJeuxDeTuiles = ressources.getLesJeuxDeTuiles();
        lesTuilesImagees = new ArrayList<>();

        lesPersonnagesChemins = ressources.getLesJoueurs();
        lesPersonnagesImages = new ArrayList<>();

        lesEnnemisChemins = ressources.getLesEntites();
        lesEnnemisImages = new ArrayList<>();
    }

    /**
     * retourne la liste d'images des ennemis
     * @return
     */
    public List<Image> getLesEnnemisImages() {
        return lesEnnemisImages;
    }

    /**
     * retourne la liste d'images des personnages
     * @return
     */
    public List<Image> getLesPersonnagesImages() {
        return lesPersonnagesImages ;
    }

    /**
     * retourne la liste d'images des tuiles
     * @return
     */
    public List<Image> getLesTuilesImagees() {
        return lesTuilesImagees;
    }

    /**
     * Methode appelant les différents chargements
     * @throws Exception
     */
    public void charge() throws Exception {
        chargeImagesEntites();
        chargeImagesPersonnages();
        decoupeTuiles();
    }

    /**
     * charge les images des enemis dans la liste
     */
    private void chargeImagesEntites() {
        for (String chemin : lesEnnemisChemins) {
            lesEnnemisImages.add(new Image(chemin));
        }
    }

    /**
     * charges les images pour le joueur dans la liste
     */
    private void chargeImagesPersonnages() {
        for (String chemin : lesPersonnagesChemins) {
            lesPersonnagesImages.add(new Image(chemin));
        }
    }

    /**
     * charge et decoupe les images pour les tuiles dans la liste
     * @throws Exception
     */
    private void decoupeTuiles() throws Exception {
        List<Image> images;

        for (Map.Entry<String, Dimension> entry : lesJeuxDeTuiles.entrySet()) {
            String chemin = entry.getKey();
            Dimension dimension = entry.getValue();
            if (chemin == null || dimension == null) {
                throw new Exception("La dimension ou le chemin de l'image d'un jeu de tuile est null.");
            }
            images = decoupeur.decoupe(chemin, (int) dimension.getLargeur(), (int) dimension.getHauteur());
            lesTuilesImagees.addAll(images);
        }
    }
}
