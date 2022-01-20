package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.monde.Tuile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class ChargeNiveau implements ChargeurNiveau {


    @Override
    public List<Tuile> charge(String chemin) {
        int cpt=0;
        List<Tuile> listeTuile = null;

        try {
            FileReader lecteur = new FileReader(String.valueOf((CollectionRessources.class.getResource(chemin))));
            BufferedReader lecteurDeTuile = new BufferedReader(lecteur);
            String ligne;
            String seperation = " ";
            ligne = lecteurDeTuile.readLine();
            String[] uneTuile = ligne.split(seperation);
            Dimension dimension = new Dimension(Integer.parseInt(uneTuile[0]),Integer.parseInt(uneTuile[1]));
            while ((ligne = lecteurDeTuile.readLine()) != null) {
                uneTuile = ligne.split(seperation);

                if(Integer.parseInt(uneTuile[0]) == cpt){
                    listeTuile.add(new Tuile(new Rectangle(Integer.parseInt(uneTuile[1]),Integer.parseInt(uneTuile[2]),Integer.parseInt(uneTuile[3]),Integer.parseInt(uneTuile[4])),dimension));
                }else{

                    listeTuile.add(new Tuile(null,dimension));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listeTuile;
    }
}
