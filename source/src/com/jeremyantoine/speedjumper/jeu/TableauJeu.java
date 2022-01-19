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
import com.jeremyantoine.speedjumper.monde.CameraCarteTuiles;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.ArrayList;
import java.util.List;

public class TableauJeu {
    private static final Dimension DIMENSION_CAMERA_PAR_DEFAUT = new Dimension(24, 16);
    private GestionnaireDeRessources gestionnaireDeRessources;
    private List<Niveau> lesNiveaux = new ArrayList<>();
    private PersonnageJouable joueur;
    private Niveau niveauCourant;
    private CameraCarteTuiles camera;

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
        camera.changeCarte(niveauCourant.getCarte());
        joueur.setPointsDeVie(joueur.getPointsDeViesInitiaux());
        joueur.setPosition(niveauCourant.getPointsDepart());
    }

    public PersonnageJouable getJoueur() {
        return joueur;
    }

    public CameraCarteTuiles getCamera() {
        return camera;
    }

    public GestionnaireDeRessources getGestionnaireDeRessources() {
        return gestionnaireDeRessources;
    }

    public List<Niveau> getLesNiveaux() {
        return lesNiveaux;
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
            niveau = new Niveau(carte, null, null, new ArrayList<>(), new Position2D(100, 400));
            lesNiveaux.add(niveau);
        }

        niveauCourant = lesNiveaux.get(0);

        joueur = new PersonnageJouable(new Position2D(1600, 1600),
                new Rectangle(5, 60, 40, 40),
                new Dimension(50, 100),
                new ComportementNull(),
                2.4,
                1000,
                3);
        camera = new CameraCarteTuiles(niveauCourant.getCarte(), DIMENSION_CAMERA_PAR_DEFAUT);
        camera.centrerSurEntite(joueur);

        niveauCourant.ajouterScore(new Score("Jean-Claude", 10));
        niveauCourant.ajouterScore(new Score("Jean-Marie", 8));
        niveauCourant.ajouterScore(new Score("Truc", 17));
        niveauCourant.ajouterScore(new Score("Bidule", 10));
        niveauCourant.ajouterScore(new Score("Jérémy", 1));
        niveauCourant.ajouterScore(new Score("Antoine", 22));
    }
}
