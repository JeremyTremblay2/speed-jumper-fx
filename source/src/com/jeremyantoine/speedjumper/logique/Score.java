package com.jeremyantoine.speedjumper.logique;

import com.jeremyantoine.speedjumper.entites.Piece;
import com.jeremyantoine.speedjumper.monde.Tuile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

/**
 * Classe du score d'un joueur
 */
public class Score implements Comparable<Score> {

    private static final String PSEUDO_PAR_DEFAUT = "Joueur";

    /**
     * Propriete du score
     */
    private final IntegerProperty score = new SimpleIntegerProperty();
        public final Integer getScore() {
            return score.get();
        }
        public final IntegerProperty scoreProperty() {
            return score;
        }
        public final void setScore(Integer score) {
            this.score.set(score);
        }

    /**
     * Propriete du pseudo
     */
    private final StringProperty pseudo = new SimpleStringProperty();
        public String getPseudo() {
            return pseudo.get();
        }
        public StringProperty pseudoProperty() {
            return pseudo;
        }
        public void setPseudo(String pseudo) {
            this.pseudo.set(pseudo);
        }

    /**
     * Constructeur du Score
     * @param pseudo pseudo de la personne ayant le score
     * @param score score du joueur
     */
    public Score(String pseudo, int score) {
        this.score.set(score);
        this.pseudo.set(pseudo);
    }

    /**
     * Autre constructeur du Score si le pseudo n'est pas donné
     * @param score Score du joueur
     */
    public Score(int score) {
        this(PSEUDO_PAR_DEFAUT, score);
    }

    /**
     * Autre constructeur du Score si no le pseudo ni le score n'est renseigné
     */
    public Score() {
        this(PSEUDO_PAR_DEFAUT, 0);
    }

    /**
     * Methode pour augmenter le score d'un joueur par rapport aux pièces
     * @param piece valeur du score a rajouter
     */
    public void augmenterScore(Piece piece) {
        score.set(score.get() + piece.getValeur());
    }

    /**
     * Methode pour augmenter le score d'un joueur par rapport au temps
     * @param temps temps final de réalisation du niveau
     */
    public void augmenterScore(double temps) {
        score.set((int) (score.get() + temps / 1000000000));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return equals(score);
    }

    public boolean equals(Score score) {
        return this.score.get() == score.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(score.get());
    }

    @Override
    public String toString() {
        return pseudo.get() + " : " + score.get() + " points.";
    }

    @Override
    public int compareTo(Score score) {
        int comp = 0;
        if (this.score.get() > score.getScore())
            comp = +1;
        else if (this.score.get() < score.getScore())
            comp = -1;
        return comp;
    }
}
