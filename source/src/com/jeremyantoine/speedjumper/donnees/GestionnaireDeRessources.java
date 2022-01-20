package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.entites.Ennemi;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Score;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.monde.Tuile;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionnaireDeRessources {
    private CollectionRessources ressources;
    private ChargeurDeCarteTiled chargeurDeCartes;
    private ChargeurDeJeuxDeTuiles chargeurDeTuiles;

    private List<String> lesCartesChemins;
    private Map<String, Dimension> lesJeuxDeTuiles;

    private List<Carte2D> lesCartes;
    private List<Tuile> lesTuiles;
    private List<List<Score>> lesScores;

    public GestionnaireDeRessources(ChargeurDeCarteTiled chargeurDeCartes, ChargeurDeJeuxDeTuiles chargeurDeTuiles) throws IllegalArgumentException {
        if (chargeurDeCartes == null || chargeurDeTuiles == null) {
            throw new IllegalArgumentException("Les chargeurs fournis en paramètre du gestionnaire de ressources ne "
                    + "peuvent pas être null.");
        }
        this.chargeurDeCartes = chargeurDeCartes;
        this.chargeurDeTuiles = chargeurDeTuiles;

        lesCartes = new ArrayList<>();
        lesTuiles = new ArrayList<>();
        lesScores = new ArrayList<>();

        ressources = CollectionRessources.getInstance();
        lesCartesChemins = ressources.getLesCartes();
        lesJeuxDeTuiles = ressources.getLesJeuxDeTuiles();
    }

    public List<Carte2D> getLesCartes() {
        return lesCartes;
    }

    public List<Tuile> getLesTuiles() {
        return lesTuiles;
    }

    public List<List<Score>> getLesScores() {
        return lesScores;
    }

    public void charge() throws FileNotFoundException, ParseException, InvalidFormatException {
        lesTuiles = chargeTuiles();
        lesCartes = chargeCartes();
        lesScores = chargeScores();
    }

    private List<Carte2D> chargeCartes() throws FileNotFoundException, ParseException,
            InvalidFormatException {
        Carte2D carte;
        for (String chemin : lesCartesChemins) {
            carte = chargeurDeCartes.charge(chemin, lesTuiles);
            lesCartes.add(carte);
        }
        return lesCartes;
    }

    private List<Tuile> chargeTuiles() throws FileNotFoundException, ParseException,
            InvalidFormatException {
        List<Tuile> tuiles;
        for (Map.Entry<String, Dimension> paire : lesJeuxDeTuiles.entrySet()) {
            tuiles = chargeurDeTuiles.charge(paire.getKey());
            lesTuiles.addAll(tuiles);
        }
        return lesTuiles;
    }

    private List<List<Score>> chargeScores() {
        List<Score> lesScores = new ArrayList<>();
        return null;
    }

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

}
