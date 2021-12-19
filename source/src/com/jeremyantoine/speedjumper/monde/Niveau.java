package com.jeremyantoine.speedjumper.monde;

import java.util.List;

public class Niveau {
    private static int nombreNiveaux = 0;
    private final int numeroNiveau;
    private String environnement;
    private Carte carte;
    private List<ArrierePlan> lesArrieresPlans;

    public Niveau(Carte carte, String environnement, List<ArrierePlan> arrierePlans)
            throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte est requise lors de la création d'un niveau "
                    + "et ne peut être nulle.");
        }
        this.carte = carte;
        this.environnement = environnement;
        lesArrieresPlans = arrierePlans;
        numeroNiveau = nombreNiveaux;
        nombreNiveaux++;
    }

    public static int getNombreNiveaux() {
        return nombreNiveaux;
    }

    public int getNumeroNiveau() {
        return numeroNiveau;
    }

    public String getEnvironnement() {
        return environnement;
    }

    public Carte getCarte() {
        return carte;
    }

    public List<ArrierePlan> getLesArrieresPlans() {
        return lesArrieresPlans;
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
        return "Niveau [" + numeroNiveau + "] : " + environnement +
                "Carte : " + carte +
                "ArrieresPlans : " + lesArrieresPlans;
    }
}
