package com.jeremyantoine.speedjumper.utilitaire;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DecoupeurFX {
    public static List<Image> decoupe(URL cheminImage, int largeur, int hauteur) {
        List<Image> lesImages = new ArrayList<>();
        Image image = new Image(cheminImage.toString());
        PixelReader lecteur = image.getPixelReader();
        WritableImage imageDecoupe;
        double largeurImage = image.getWidth() / largeur;
        double hauteurImage = image.getHeight() / hauteur;

        for (int x = 0; x < largeurImage; x++){
            for (int y = 0; y < hauteurImage; y++) {
                imageDecoupe = new WritableImage(lecteur, y * largeur, x * hauteur, largeur, hauteur);
                lesImages.add(imageDecoupe);
            }
        }
        return lesImages;
    }
}
