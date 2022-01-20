package com.jeremyantoine.speedjumper.controleurs;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Controleur pour un contenu personnalisé switch bouton
 */
public class BoutonSwitch extends StackPane {
    private Rectangle arrierePlan = new Rectangle(30, 10, Color.RED);
    private Button bouton = new Button();
    private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
    private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0.1,0.1), 0.2, 0.0, 0.0, 2); -fx-background-color: #02893e;";
    private boolean etat;

    /**
     * constructeur
     */
    public BoutonSwitch() {

    }

    /**
     * initialisation du bouton
     */
    public void initialize() {
        getChildren().addAll(arrierePlan, bouton);
        setMinSize(30, 15);
        arrierePlan.maxWidth(30);
        arrierePlan.minWidth(30);
        arrierePlan.maxHeight(10);
        arrierePlan.minHeight(10);
        arrierePlan.setArcHeight(arrierePlan.getHeight());
        arrierePlan.setArcWidth(arrierePlan.getHeight());
        arrierePlan.setFill(Color.valueOf("#ced5da"));
        double r = 2.0;
        bouton.setShape(new Circle(r));
        setAlignment(bouton, Pos.CENTER_LEFT);
        bouton.setMaxSize(15, 15);
        bouton.setMinSize(15, 15);
        bouton.setStyle(buttonStyleOff);
    }

    /**
     * methode permettant de changer en fonction du click
     * @param event
     */
    @FXML
    public void bougerBouton(Event event) {
        EventHandler<Event> click = e -> {
            if (etat) {
                bouton.setStyle(buttonStyleOff);
                arrierePlan.setFill(Color.valueOf("#ced5da"));
                setAlignment(bouton, Pos.CENTER_LEFT);
                etat = false;
            } else {
                bouton.setStyle(buttonStyleOn);
                arrierePlan.setFill(Color.valueOf("#80C49E"));
                setAlignment(bouton, Pos.CENTER_RIGHT);
                etat = true;
            }
        };

        bouton.setFocusTraversable(false);
        setOnMouseClicked(click);
        bouton.setOnMouseClicked(click);
    }
}