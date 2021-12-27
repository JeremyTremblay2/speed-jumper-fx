package com.jeremyantoine.speedjumper.donnees;

import com.jeremyantoine.speedjumper.monde.Carte;
import com.jeremyantoine.speedjumper.utilitaire.InvalidFormatException;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;

public class AdaptateurChargeurDeCarteTiledCSV implements ChargeurDeCarteTiled {
    private static final ChargeurDeCalqueTiledCSV chargeur = new ChargeurDeCalqueTiledCSV();
    private String separateur;

    public AdaptateurChargeurDeCarteTiledCSV(String separateur) {
        this.separateur = separateur;
    }

    @Override
    public Carte charge(URL cheminFichier) throws FileNotFoundException, ParseException, InvalidFormatException {
        chargeur.charge(cheminFichier, separateur);
    }
}
