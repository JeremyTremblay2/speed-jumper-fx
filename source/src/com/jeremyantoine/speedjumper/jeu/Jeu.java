package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.observateurs.Observateur;

/**
 * Classe gerant le jeu
 */
public class Jeu implements Observateur {
    private static final int FPS_CIBLE = 60;
    private static final double TEMPS_MISE_A_JOUR = 10000000;
    private final int compteurFrame = 0;
    private final long tempsOrigine = System.nanoTime();
    private final boolean joue;
    private final ManagerEtatDeJeu managerEtats;
    private final TableauJeu jeu;
    private BoucleDeJeu boucleDeJeu;

    /**
     * Constructeur de Jeu
     * @param recuperateur recuperateur de touche
     */
    public Jeu(RecuperateurDeTouches recuperateur) {
        jeu = new TableauJeu(recuperateur);
        managerEtats = new ManagerEtatDeJeu(jeu, recuperateur);
        joue = true;
    }

    /**
     * retourne le jeu
     * @return
     */
    public TableauJeu getJeu() {
        return jeu;
    }

    /**
     * retourne le manager d'etat des jeux
     * @return
     */
    public ManagerEtatDeJeu getManagerEtats() {
        return managerEtats;
    }

    public void initialise() {
        //Si des chargements particuliers ont lieux ici.
    }

    /**
     * Methode pour lancer le jeu
     */
    public void jouer() {
        boucleDeJeu = new BoucleDeJeu();
        boucleDeJeu.attacher(this);
        Thread processus = new Thread(boucleDeJeu, "Speed Jumper Thread");
        processus.start();
    }

    /**
     * Methode lorsque le jeu est lancé
     */
    public void joue() {
        float precedent = System.nanoTime();
        float courant, ecoule;
        float lag = 0, temps = 0;
        final double seconde = 1000000000;

        BoucleDeJeu boucleDeJeu = new BoucleDeJeu();
        boucleDeJeu.attacher(this);
        Thread boucle = new Thread(boucleDeJeu);
        boucle.setDaemon(true);
        boucle.start();

        while (joue) {
            courant = System.nanoTime();
            ecoule = courant - precedent;
            precedent = courant;
            lag += ecoule;

            managerEtats.entreeUtilisateur(temps);

            while (lag >= TEMPS_MISE_A_JOUR) {
                managerEtats.miseAJour(ecoule);
                lag -= TEMPS_MISE_A_JOUR;
            }
            managerEtats.affichage();

            temps += ecoule;
        }
        System.out.println("Jeu fini");
    }

    /**
     *  Mise a jour du manager
     */
    @Override
    public void miseAjour() {
        managerEtats.entreeUtilisateur(boucleDeJeu.getTempsEcoule());
        managerEtats.miseAJour(boucleDeJeu.getTempsEcoule());
        managerEtats.affichage();
    }

    /**
     * Methode appelé a la fermeture du jeu
     */
    public void ferme() {
        //Eventuellement sauvegarde, etc avant de quitter.
    }
}