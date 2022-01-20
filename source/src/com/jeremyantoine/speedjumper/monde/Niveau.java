package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.comportement.ComportementNull;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Fantome;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.logique.Score;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Niveau {
    private static final int NOMBRE_SCORE_MAXIMUM = 10;
    private static int nombreNiveaux = 0;
    private final int numeroNiveau;
    private final Carte2D carte;
    private final List<ArrierePlan> lesArrieresPlans;
    private final List<Entite> lesEntites;
    private final Fantome fantome;
    private final Position2D pointsDepart;

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
                  Position2D pointsDepart) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte est requise lors de la création d'un niveau "
                    + "et ne peut être nulle.");
        }
        if (pointsDepart == null) {
            throw new IllegalArgumentException("La position de départ est requise lors de la création d'un niveau "
                    + "et ne peut être nulle.");
        }

        this.carte = carte;
        lesArrieresPlans = arrierePlans;
        lesScoresObservables.addAll(lesScores);
        this.lesEntites = lesEntites;
        this.pointsDepart = pointsDepart;
        numeroNiveau = nombreNiveaux;
        nombreNiveaux++;
        fantome = new Fantome(pointsDepart,
                new Rectangle(pointsDepart.getX(), pointsDepart.getY(), 30, 60),
                new Dimension(50, 100),
                new ComportementNull(),
                2.3,
                1000);
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

    public Fantome getOmbre() {
        return fantome;
    }

    public Position2D getPointsDepart() {
        return pointsDepart;
    }

    public void ajouterScore(Score score) {
        lesScores.add(score);
        if (lesScores.size() > NOMBRE_SCORE_MAXIMUM) {
            lesScores.remove(NOMBRE_SCORE_MAXIMUM);
        }
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
        chaine.append("\nFantome : ");
        chaine.append(fantome.toString());
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
