package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.comportement.ComportementNull;
import com.jeremyantoine.speedjumper.donnees.*;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;
import com.jeremyantoine.speedjumper.logique.Score;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.ArrayList;
import java.util.List;


public class TableauJeu {
    private GestionnaireDeRessources gestionnaireDeRessources;
    private List<Niveau> lesNiveaux = new ArrayList<>();
    private PersonnageJouable joueur;
    private Niveau niveauCourant;
    private Options options;

    public TableauJeu(RecuperateurDeTouches recuperateur) {
        gestionnaireDeRessources = new GestionnaireDeRessources(new AdaptateurChargeurDeCarteTiledCSV(","),
                new ChargeurDeJeuxDeTuilesTextuel(), new ChargeurScoreTextuel());
        initialisation();
    }

    public boolean isGameOver() {
        return joueur.getPointsDeVie() <= 0;
    }

    public Niveau getNiveauCourant() {
        return niveauCourant;
    }

    public void setNiveauCourant(int niveau) {
        niveauCourant = lesNiveaux.get(niveau);
        joueur.setPointsDeVie(joueur.getPointsDeViesInitiaux());
        joueur.setPosition(niveauCourant.getPointsDepart());
    }

    public PersonnageJouable getJoueur() {
        return joueur;
    }

    public GestionnaireDeRessources getGestionnaireDeRessources() {
        return gestionnaireDeRessources;
    }

    public List<Niveau> getLesNiveaux() {
        return lesNiveaux;
    }

    public Options getOptions() {
        return options;
    }

    private void initialisation() {
        List<Carte2D> lesCartes = new ArrayList<>();
        List<List<Score>> lesScores = new ArrayList<>();
        Niveau niveau;

        try {
            gestionnaireDeRessources.charge();
            lesCartes = gestionnaireDeRessources.getLesCartes();
            lesScores = gestionnaireDeRessources.getLesScores();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lesCartes.size(); i++) {
            niveau = new Niveau(lesCartes.get(i),
                    null,
                    null,
                    lesScores.get(i) == null ? null : lesScores.get(i),
                    new Position2D(200, 1400));
            lesNiveaux.add(niveau);
        }

        ChargeurEnnemis chargeurEnnemis = new ChargeurEnnemisStub(lesNiveaux);
        List<List<Entite>> lesEnnemis = chargeurEnnemis.charge(null);
        for (int i = 0; i < lesNiveaux.size(); i++) {
            lesNiveaux.get(i).ajouterEntites(lesEnnemis.get(i));
        }

        niveauCourant = lesNiveaux.get(0);

        options = new Options(true, 10, 10);

        joueur = new PersonnageJouable(new Position2D(0, 0),
                new Rectangle(22, 12, 41, 112),
                new Dimension(85, 128),
                new ComportementNull(),
                10,
                4,
                3);
    }
}
