package com.jeremyantoine.speedjumper.logique;

import com.jeremyantoine.speedjumper.entites.Piece;

import java.util.Objects;

public class Score {
    private int score;

    public Score() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void augmenterScore(Piece piece) {
        score += piece.getValeur();
    }

    public void augmenterScore(double temps) {
        score += temps / 1000000000;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return equals(score);
    }

    public boolean equals(Score score) {
        return this.score == score.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return "Score : " + score + " points.";
    }
}
