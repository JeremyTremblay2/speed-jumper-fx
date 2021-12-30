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
    public List<Image> decoupe(String cheminImage, int largeur, int hauteur) {
        System.out.println("l : " + largeur + ", h : " + hauteur);
        List<Image> lesImages = new ArrayList<>();
        Image image = new Image(cheminImage);
        PixelReader lecteur = image.getPixelReader();
        WritableImage imageDecoupe;
        double largeurImage = image.getWidth() / largeur;
        double hauteurImage = image.getHeight() / hauteur;

        System.out.println(largeurImage);
        System.out.println(hauteurImage);

        for (int y = 0; y < largeurImage; y++){
            for (int x = 0; x < hauteurImage; x++) {
                System.out.println("x : " + x + ", y : " + y);
                imageDecoupe = new WritableImage(lecteur, y * largeur, x * hauteur, largeur, hauteur);
                lesImages.add(imageDecoupe);
            }
        }
        return lesImages;
    }
}
