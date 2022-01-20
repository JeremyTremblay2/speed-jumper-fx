package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.comportement.ComportementNull;
import com.jeremyantoine.speedjumper.donnees.AdaptateurChargeurDeCarteTiledCSV;
import com.jeremyantoine.speedjumper.donnees.ChargeurDeJeuxDeTuilesTextuel;
import com.jeremyantoine.speedjumper.donnees.GestionnaireDeRessources;
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
                new ChargeurDeJeuxDeTuilesTextuel());
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
        Niveau niveau;

        try {
            gestionnaireDeRessources.charge();
            lesCartes = gestionnaireDeRessources.getLesCartes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Carte2D carte : lesCartes) {
            niveau = new Niveau(carte, null, null, new ArrayList<>(), new Position2D(100, 1200));
            lesNiveaux.add(niveau);
        }

        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);
        lesNiveaux.add(null);

        niveauCourant = lesNiveaux.get(0);

        options = new Options(true, 10, 10);

        joueur = new PersonnageJouable(new Position2D(350, 300),
                new Rectangle(6, 3, 15, 33),
                new Dimension(24, 36),
                new ComportementNull(),
                0.2,
                4,
                3);

        niveauCourant.ajouterScore(new Score("Jean-Claude", 10));
        niveauCourant.ajouterScore(new Score("Jean-Marie", 8));
        niveauCourant.ajouterScore(new Score("Truc", 17));
        niveauCourant.ajouterScore(new Score("Bidule", 10));
        niveauCourant.ajouterScore(new Score("Jérémy", 1));
        niveauCourant.ajouterScore(new Score("Antoine", 22));
    }
}
