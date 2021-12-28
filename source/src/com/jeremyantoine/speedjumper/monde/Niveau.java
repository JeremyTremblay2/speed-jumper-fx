package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.comportement.ComportementNull;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.Fantome;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.List;

public class Niveau {
    private static int nombreNiveaux = 0;
    private final int numeroNiveau;
    private Carte2D carte;
    private List<ArrierePlan> lesArrieresPlans;
    private List<Entite> lesEntites;
    private Fantome fantome;
    private Position2D pointsDepart;

    public Niveau(Carte2D carte, List<ArrierePlan> arrierePlans, List<Entite> lesEntites, Position2D pointsDepart)
            throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte est requise lors de la création d'un niveau "
                    + "et ne peut être nulle.");
        }
        this.carte = carte;
        lesArrieresPlans = arrierePlans;
        this.lesEntites = lesEntites;
        fantome = new Fantome(pointsDepart,
                new Rectangle(pointsDepart.getX(), pointsDepart.getY(), 30, 60),
                new ComportementNull(),
                1000);
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

    public Fantome getOmbre() {
        return fantome;
    }

    public Position2D getPointsDepart() {
        return pointsDepart;
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
        return "Niveau [" + numeroNiveau + "] : "
                + "Carte : " + carte
                + "ArrieresPlans : " + lesArrieresPlans
                + "Entités : " + lesEntites
                + "Position départ : " + pointsDepart;
    }
}
