package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Score;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour un niveau
 */
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

    /**
     * Constructeur de la classe niveau
     * @param carte carte du niveau
     * @param arrierePlans liste des images de l'arriere plan
     * @param lesEntites liste des entitées présente sur le niveau
     * @param lesScores liste des scores réalisés sur le niveau
     * @param pointsDepart coordonées du point de départ
     * @throws IllegalArgumentException
     */
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

    /**
     * retourne le nombre de  niveau
     * @return
     */
    public static int getNombreNiveaux() {
        return nombreNiveaux;
    }

    /**
     * Retourne le numero d'un niveau
     * @return
     */
    public int getNumeroNiveau() {
        return numeroNiveau;
    }

    /**
     * retourne la carte 2D du niveau
     * @return
     */
    public Carte2D getCarte() {
        return carte;
    }

    /**
     * Retourne la liste des arrières plans du niveau
     * @return
     */
    public List<ArrierePlan> getLesArrieresPlans() {
        return lesArrieresPlans;
    }

    /**
     * Retourne la liste des entitées du niveau
     * @return
     */
    public List<Entite> getLesEntites() {
        return lesEntites;
    }


    /**
     * retourne la position du point de départ
     * @return
     */
    public Position2D getPointsDepart() {
        return pointsDepart;
    }

    public Position2D getPointArrivee() {
        return pointArrivee;
    }

    /**
     * Ajoute un nouveau score au niveau
     * @param score score a ajouter au niveau
     */
    public void ajouterScore(Score score) {
        lesScores.add(score);
        if (lesScores.size() > NOMBRE_SCORE_MAXIMUM) {
            lesScores.remove(NOMBRE_SCORE_MAXIMUM);
        }
    }

    public void ajouterEntites(List<Entite> lesEntites) {
            this.lesEntites.addAll(lesEntites);
    }

    /**
     * Compare au niveau objet deux niveau
     * @param o objet a comparé ici Niveau
     * @return un boolean selon la comparaison. True si identique , false si différent
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Niveau niveau = (Niveau) o;
        return equals(niveau);
    }

    /**
     * Compare un niveau avec le niveau actuel
     * @param niveau
     * @return
     */
    public boolean equals(Niveau niveau) {
        return numeroNiveau == niveau.getNumeroNiveau();
    }

    @Override
    public int hashCode() {
        return 7 * numeroNiveau;
    }


    /**
     * Affichage des données du Niveau
     * @return
     */
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
