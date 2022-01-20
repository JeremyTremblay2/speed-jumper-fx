package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Score;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SauvDeScore implements SauvegardeDeScore {

    private void ecriture(List<Niveau> lesNiveau) throws IOException {
        FileWriter writer = new FileWriter(String.valueOf((CollectionRessources.class.getResource("/scores/scores.yml"))));
        BufferedWriter buffer = new BufferedWriter(writer);
        for(Niveau niv : lesNiveau){
            buffer.write("niveau:"+niv.getNumeroNiveau());
            for(Score score : niv.lesScoresProperty()){
                buffer.write(score.getPseudo()+":"+score.getScore());
            }
        }
        buffer.close();
    }

    @Override
    public void sauvegarde(List<Niveau> lesniveaux) throws IOException {
        ecriture(lesniveaux);
    }
}
