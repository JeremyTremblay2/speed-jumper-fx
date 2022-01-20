package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.comportement.ComportementNull;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.logique.Score;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Niveau {
    private static final int NOMBRE_SCORE_MAXIMUM = 10;
    private static int nombreNiveaux = 0;
    private int numeroNiveau;
    private Carte2D carte;
    private List<ArrierePlan> lesArrieresPlans;
    private List<Entite> lesEntites;
    private Position2D pointsDepart;
    private Position2D pointArrivee;

    private final ObservableList<Score> lesScoresObservables = FXCollections.observableArrayList();

    private final ListProperty<Score> lesScores = new SimpleListProperty<>(lesScoresObservables);
        public final ObservableList<Score> getLesScores() {
            return lesScores.get();
        }
        public final void setLesEtudiants(ObservableList<Score> value) {
            lesScores.set(value);
        }
        public final ListProperty<Score> lesScoresProperty() {
            return lesScores;
        }

    public Niveau(Carte2D carte, List<ArrierePlan> arrierePlans, List<Entite> lesEntites, List<Score> lesScores,
                  Position2D pointsDepart, Position2D pointsArrivee) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte est requise lors de la création d'un niveau "
                    + "et ne peut être nulle.");
        }
        if (pointsDepart == null || pointsArrivee == null) {
            throw new IllegalArgumentException("La position de départ et d'arrivée est requise lors de la création d'un "
                    + "niveau et ne peut être nulle.");
        }

        this.carte = carte;
        lesScores = lesScores == null ? new ArrayList<>() : lesScores;
        lesScoresObservables.addAll(lesScores);
        lesArrieresPlans = arrierePlans == null ? new ArrayList<>() : arrierePlans;
        this.lesEntites = lesEntites == null ? new ArrayList<>() : lesEntites;
        this.pointsDepart = pointsDepart;
        this.pointArrivee = pointsArrivee;
        numeroNiveau = nombreNiveaux;
        nombreNiveaux++;
    }

    public static int getNombreNiveaux() {
        return nombreNiveaux;
    }

    public int getNumeroNiveau() {
        return numeroNiveau;
    }

    public Carte2D getCarte() {
        return carte;
    }

    public List<ArrierePlan> getLesArrieresPlans() {
        return lesArrieresPlans;
    }

    public List<Entite> getLesEntites() {
        return lesEntites;
    }

    public Position2D getPointsDepart() {
        return pointsDepart;
    }

    public Position2D getPointArrivee() {
        return pointArrivee;
    }

    public void ajouterScore(Score score) {
        lesScores.add(score);
        if (lesScores.size() > NOMBRE_SCORE_MAXIMUM) {
            lesScores.remove(NOMBRE_SCORE_MAXIMUM);
        }
    }

    public void ajouterEntites(List<Entite> lesEntites) {
            this.lesEntites.addAll(lesEntites);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Niveau niveau = (Niveau) o;
        return equals(niveau);
    }

    public boolean equals(Niveau niveau) {
        return numeroNiveau == niveau.getNumeroNiveau();
    }

    @Override
    public int hashCode() {
        return 7 * numeroNiveau;
    }


    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder("Niveau [");
        chaine.append(numeroNiveau);
        chaine.append("] : ");
        chaine.append("\nCarte : ");
        chaine.append(carte.toString());
        chaine.append("\nArrieresPlans : ");
        for (ArrierePlan arrierePlan : lesArrieresPlans) {
            chaine.append(arrierePlan.toString());
            chaine.append(", ");
        }
        chaine.append("\nEntites : ");
        for (Entite entite : lesEntites) {
            chaine.append(entite.toString());
            chaine.append(", ");
        }
        chaine.append("\nMeilleurs scores : ");
        for (Score score : lesScores) {
            chaine.append(score.toString());
            chaine.append(", ");
        }
        chaine.append("\nPosition de départ : ");
        chaine.append(pointsDepart.toString());
        return chaine.toString();
    }
}
