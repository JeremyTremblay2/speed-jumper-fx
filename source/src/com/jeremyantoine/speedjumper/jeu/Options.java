package com.jeremyantoine.speedjumper.jeu;

import java.util.Objects;

/**
 * Classe option pour les niveaux audios et le sexe du personnage
 */
public class Options {
    private static final int NIVEAU_MINIMUM_AUDIO = 0;
    private static final int NIVEAU_MAXIMUM_AUDIO = 100;
    private boolean homme;
    private int niveauSon;
    private int niveauMusique;

    /**
     * Constructeur de la classe options.
     * @param sexe valeur du bouton switch pour le choix du sexe du personnage
     * @param niveauSon Niveau du son/effets sonores
     * @param niveauMusique Niveau
     */
    public Options(boolean sexe, int niveauSon, int niveauMusique) {
        homme = sexe;
        niveauSon = Math.max(niveauSon, NIVEAU_MINIMUM_AUDIO);
        niveauSon = Math.min(niveauSon, NIVEAU_MAXIMUM_AUDIO);
        niveauMusique = Math.max(niveauMusique, NIVEAU_MINIMUM_AUDIO);
        niveauMusique = Math.min(niveauMusique, NIVEAU_MAXIMUM_AUDIO);
        this.niveauSon = niveauSon;
        this.niveauMusique = niveauMusique;
    }

    /**
     * Retourne le niveau de son
     * @return
     */
    public int getNiveauSon() {
        return niveauSon;
    }

    /**
     * retourne le niveau de la musique
     * @return
     */
    public int getNiveauMusique() {
        return niveauMusique;
    }

    /**
     * retourne si le choix est homme
     * @return
     */
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
