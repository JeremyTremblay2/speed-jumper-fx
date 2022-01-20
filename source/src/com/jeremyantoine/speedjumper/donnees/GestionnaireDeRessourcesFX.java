package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.DecoupeurFX;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionnaireDeRessourcesFX {
    private DecoupeurFX decoupeur = new DecoupeurFX();
    private CollectionRessources ressources;

    private Map<String, Dimension> lesJeuxDeTuiles;
    private List<Image> lesTuilesImagees;

    private List<String> lesPersonnagesChemins;
    private List<Image> lesPersonnagesImages;

    private List<String> lesEnnemisChemins;
    private List<Image> lesEnnemisImages;

    public GestionnaireDeRessourcesFX() {
        ressources = CollectionRessources.getInstance();

        lesJeuxDeTuiles = ressources.getLesJeuxDeTuiles();
        lesTuilesImagees = new ArrayList<>();

        lesPersonnagesChemins = ressources.getLesJoueurs();
        lesPersonnagesImages = new ArrayList<>();

        lesEnnemisChemins = ressources.getLesEntites();
        lesEnnemisImages = new ArrayList<>();
    }

    public List<Image> getLesEnnemisImages() {
        return lesEnnemisImages;
    }

    public List<Image> getLesPersonnagesImages() {
        return lesPersonnagesImages ;
    }

    public List<Image> getLesTuilesImagees() {
        return lesTuilesImagees;
    }

    public void charge() throws Exception {
        chargeImagesEntites();
        chargeImagesPersonnages();
        decoupeTuiles();
    }

    private void chargeImagesEntites() {
        for (String chemin : lesEnnemisChemins) {
            lesEnnemisImages.add(new Image(chemin));
        }
    }


    private void chargeImagesPersonnages() {
        for (String chemin : lesPersonnagesChemins) {
            lesPersonnagesImages.add(new Image(chemin));
        }
    }

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
