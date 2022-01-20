package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.List;

public interface ChargeurDeScore {

    void charge(List<Niveau> lesNiveaux);
}
