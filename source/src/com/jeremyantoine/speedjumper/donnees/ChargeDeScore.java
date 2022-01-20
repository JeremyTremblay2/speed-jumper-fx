package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Score;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class ChargeDeScore implements ChargeurDeScore{

    private void lecture(List<Niveau> lesNiveau){
        int numNiv=0;

        try {
            FileReader lecteur = new FileReader(String.valueOf((CollectionRessources.class.getResource("/scores/scores.yml"))));
            BufferedReader lecteurDeScore = new BufferedReader(lecteur);
            String ligne;
            String seperation = ":";
            while ((ligne = lecteurDeScore.readLine()) != null) {

                String[] unScore = ligne.split(seperation);
                if(unScore[0] == "niveau"){
                    numNiv = Integer.parseInt(unScore[1]);
                }else{
                    lesNiveau.get(numNiv-1).ajouterScore(new Score(unScore[0],Integer.parseInt(unScore[1])));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void charge(List<Niveau> lesNiveaux) {
        lecture(lesNiveaux);
    }
}
