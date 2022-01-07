package com.jeremyantoine.speedjumper.Jeu;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireDeTouchesFX implements GestionnaireDeTouches {

    private List<Touche> lesTouches;

    public GestionnaireDeTouchesFX() {
        lesTouches = new ArrayList<Touche>();
    }

    @Override
    public List<Touche> detectionEntrees() {
        return lesTouches;
    }
}
