package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.logique.Score;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChargeurScoreTextuel implements ChargeurScore {
    private static final String SEPARATEUR = ":";
    private static final String DEBUT_NUMERO_NIVEAU = "#";

    @Override
    public List<List<Score>> charge(String chemin) {
        List<List<Score>> lesScores = new ArrayList<>();

        try (BufferedReader lecteur = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            List<Score> scores = new ArrayList<>();

            while ((ligne = lecteur.readLine()) != null) {
                String[] score = ligne.split(SEPARATEUR);

                if (score[0].trim().equals(DEBUT_NUMERO_NIVEAU)) {
                    lesScores.add(scores);
                    scores = new ArrayList<>();
                }
                else {
                    scores.add(new Score(score[0], Integer.parseInt(score[1].trim())));
                }
            }
            lesScores.remove(0);
            lesScores.add(scores);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return lesScores;
    }
}