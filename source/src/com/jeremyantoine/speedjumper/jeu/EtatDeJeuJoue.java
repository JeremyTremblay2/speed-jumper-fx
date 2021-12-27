package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.actions.AdaptateurDeplaceur;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.entrees.Touche;
import com.jeremyantoine.speedjumper.logique.Attaque;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EtatDeJeuJoue extends EtatDeJeu {
    private static final AdaptateurDeplaceur deplaceur = new AdaptateurDeplaceur(Direction.DROITE);
    private RecuperateurDeTouches recuperateurDeTouches;
    private List<Touche> lesTouches = new ArrayList<>();
    private List<Entite> lesEntites;

    public EtatDeJeuJoue() throws MalformedURLException {
        recuperateurDeTouches = new RecuperateurDeTouchesFX(new URL("file:D:\\Cours\\2021-2022\\S1\\Conception et Prog Avancée\\speed-jumper\\source\\ressources\\touches.txt"), null);
    }

    private List<Entite> initialisation() {
        //charger ressources
        List<Entite> lesEntites = new ArrayList<Entite>();
        lesEntites.add(new PersonnageJouable(new Position2D(0, 0),
                new Rectangle(10, 10, 20, 20),
                new Attaque(null, 4, 0.3f)));
        return lesEntites;
    }

    @Override
    public EtatDeJeu entreeUtilisateur() {
        lesTouches = recuperateurDeTouches.detecte();
        if (lesTouches.contains(Touche.ECHAP)) {
            return lesEtatsTransitoires.get(EtatJeu.ETAT_MENU_PAUSE);
        }
        return null;
    }

    @Override
    public void miseAJour(double temps) {
        if (lesTouches.contains(Touche.FLECHE_DROITE)) {
            deplaceur.setDirection(Direction.DROITE);
            deplaceur.miseAJourEtatDeJeu(lesEntites.get(0), temps);
        }
        if (lesTouches.contains(Touche.FLECHE_GAUCHE)) {
            deplaceur.setDirection(Direction.GAUCHE);
            deplaceur.miseAJourEtatDeJeu(lesEntites.get(0), temps);
        }
    }

    @Override
    public void affichage() {

    }
}
