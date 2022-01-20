package com.jeremyantoine.speedjumper.controleurs;

import javafx.scene.image.Image;

import java.util.List;

/**
 * Classe permettant de creer des animations depuis une collection d'image
 */
public class Animation {

    private List<Image> images;
    private int[] etats;
    private int imageCourrante;
    private int nbImages;

    private int cpt;
    private int delais;

    private int nbJoue;

    /**
     * Constructeur de la classe
     * @param images
     */
    public Animation(List<Image> images){
        nbJoue = 0;
        setImages(0,images);
        etats = new int[10];
    }

    /**
     * Autre constructeur de la classe
     */
    public Animation(){
        nbJoue = 0;
        etats = new int[10];
    }

    /**
     * Methode permettant de set les images pour l'animation en fonction de l'etat
     * @param etat etat ( avance a droite etc... )
     * @param images
     */
    public  void setImages(int etat,List<Image> images){
        this.images =  images;
        nbImages = 0;
        cpt = 0;
        nbJoue = 0;
        delais = 2;
        if(etats[etat] == 0){
            nbImages = images.toArray().length;
        }
        else{
            nbImages = etats[etat];
        }
    }

    /**
     * Delais de l'animation
      * @param delais
     */
    public void setDelais(int delais) {
        this.delais = delais;
    }

    /**
     * Nombre d'image pour l'animation
     * @param nbImages
     */
    public void setNbImages(int nbImages) {
        this.nbImages = nbImages;
    }

    /**
     * Set l'image courrante dans l'animation
     * @param imageCourrante
     */
    public void setImageCourrante(int imageCourrante) {
        this.imageCourrante = imageCourrante;
    }

    /**
     * Met a jour l'animation
     */
    public void miseAJour(){
        if(delais == -1)return;
        cpt ++;
        if (cpt == delais){
            imageCourrante ++;
            cpt = 0;
        }
        if(imageCourrante == nbImages){
            imageCourrante = 0;
            nbJoue++;
        }
    }

    /**
     * retourne le delais de l'animation
     * @return
     */
    public int getDelais() {
        return delais;
    }

    /**
     * retourne le compteur d'image
     * @return
     */
    public int getCpt() {
        return cpt;
    }

    /**
     * retourne la liste d'images
     * @return
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * retourne l'image courrante
     * @return
     */
    public int getImageCourrante() {
        return imageCourrante;
    }
}
