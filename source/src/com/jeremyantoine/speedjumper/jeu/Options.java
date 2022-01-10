package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.monde.ArrierePlan;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.Objects;

public class Options {
    private boolean homme;
    private int niveauSon;
    private int niveauMusique;

    public Options(boolean sexe, int niveauSon, int niveauMusique) {
        homme = sexe;
        this.niveauSon = niveauSon;
        this.niveauMusique = niveauMusique;
    }

    public int getNiveauSon() {
        return niveauSon;
    }

    public int getNiveauMusique() {
        return niveauMusique;
    }

    public boolean isHomme() {
        return homme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Options options = (Options) o;
        return equals(options);
    }

    public boolean equals(Options options) {
        return homme == options.isHomme()
                && niveauSon == options.getNiveauSon()
                && niveauMusique == options.getNiveauMusique();
    }

    @Override
    public int hashCode() {
        return Objects.hash(homme, niveauSon, niveauMusique);
    }

    @Override
    public String toString() {
        if (homme) {
            return "Sexe : Homme, " + niveauSon + "% son, " + niveauMusique + "% musique";
        }
        return "Sexe : Femme, " + niveauSon + "% son, " + niveauMusique + "% musique";
    }
}
