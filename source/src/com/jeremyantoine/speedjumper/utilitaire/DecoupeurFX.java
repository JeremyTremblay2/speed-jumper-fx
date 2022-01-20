package com.jeremyantoine.speedjumper.utilitaire;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant de découper les images
 */
public class DecoupeurFX {

    /**
     * Methode pour découper des images fonction de la largeur et de la heuteur
     * @param cheminImage chemin de l'image
     * @param largeur largeur voulu
     * @param hauteur hauteur voulu
     * @return liste d'images découpées
     */
    public List<Image> decoupe(String cheminImage, int largeur, int hauteur) {
        List<Image> lesImages = new ArrayList<>();
        Image image = new Image(cheminImage);
        PixelReader lecteur = image.getPixelReader();
        WritableImage imageDecoupe;
        double largeurImage = image.getWidth() / largeur;
        double hauteurImage = image.getHeight() / hauteur;

        for (int y = 0; y < hauteurImage; y++){
            for (int x = 0; x < largeurImage; x++) {
                imageDecoupe = new WritableImage(lecteur, x * largeur, y * hauteur, largeur, hauteur);
                lesImages.add(imageDecoupe);
            }
        }
        return lesImages;
    }
}
