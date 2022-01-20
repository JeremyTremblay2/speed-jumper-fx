package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Score;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SauveurScoreTexte implements SauveurScore {

    @Override
    public void sauvegarde(List<Niveau> lesNiveaux, String chemin) {
        try (FileWriter writer = new FileWriter(chemin)) {
            BufferedWriter buffer = new BufferedWriter(writer);
            for(Niveau niv : lesNiveaux){
                buffer.write("N : " + niv.getNumeroNiveau());
                for(Score score : niv.lesScoresProperty()){
                    buffer.write(score.getPseudo() + " : " + score.getScore());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
