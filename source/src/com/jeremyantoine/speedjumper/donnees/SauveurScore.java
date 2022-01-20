package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.List;

/**
 * Interface permettant de sauvegarder les scores
 */
public interface SauveurScore {
    /**
     * Methode pour sauvegarder
     * @param lesNiveaux liste des niveaux a sauvegarder
     * @param chemin chemin du fichier ou sauvegarder
     */
    void sauvegarde(List<Niveau> lesNiveaux, String chemin);
}
