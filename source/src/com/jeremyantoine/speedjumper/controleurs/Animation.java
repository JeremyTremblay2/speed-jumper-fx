package com.jeremyantoine.speedjumper.fenetres;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.util.List;


public class Animation {

    private List<Image> images;
    private int[] etats;
    private int imageCourrante;
    private int nbImages;

    private int cpt;
    private int delais;

    private int nbJoue;

    public Animation(List<Image> images){
        nbJoue = 0;
        setImages(0,images);
        etats = new int[10];
    }

    public Animation(){
        nbJoue = 0;
        etats = new int[10];
    }

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

    public void setDelais(int delais) {
        this.delais = delais;
    }

    public void setNbImages(int nbImages) {
        this.nbImages = nbImages;
    }

    public void setImageCourrante(int imageCourrante) {
        this.imageCourrante = imageCourrante;
    }

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

    public int getDelais() {
        return delais;
    }

    public int getCpt() {
        return cpt;
    }

    public List<Image> getImages() {
        return images;
    }

    public int getImageCourrante() {
        return imageCourrante;
    }
}
