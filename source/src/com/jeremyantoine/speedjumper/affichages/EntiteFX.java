package com.jeremyantoine.speedjumper.affichages;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.observateurs.Observateur;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * classe gerant l'affichage des entités
 */
public class EntiteFX implements Observateur {
    private Entite entite;
    private List<Image> lesImages;
    private Image imageCourante;
    private int indexImage;

    /**
     * Constructeur de EntiteFX
     * @param entite
     * @param lesImages
     */
    public EntiteFX(Entite entite, List<Image> lesImages) {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité passée en paramètre ne peut pas être nulle.");
        }
        this.entite = entite;
        this.lesImages = lesImages == null ? new ArrayList<>() : lesImages;
        imageCourante = lesImages != null ? lesImages.get(0) : null;
    }

    /**
     * retourne l'image courrante
     * @return
     */
    public Image getImageCourante() {
        return imageCourante;
    }

    /**
     * retourne l'entite
     * @return
     */
    public Entite getEntite() {
        return entite;
    }

    /**
     * retourne la liste d'images de lentite
     * @return
     */
    public List<Image> getLesImages() {
        return lesImages;
    }

    /**
     * Met a jour l'affichage de l'entite
     */
    @Override
    public void miseAjour() {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntiteFX entiteFX = (EntiteFX) o;
        return equals(entiteFX);
    }

    public boolean equals(EntiteFX entiteFX) {
        return entite.equals(entiteFX.getEntite())
                && lesImages.equals(entiteFX.getLesImages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(entite, lesImages);
    }

    @Override
    public String toString() {
        return "Version graphique de Entite : " + entite.toString()
                + "\nIndex de l'image courante ["
                + imageCourante + "] : " + indexImage;
    }
}
