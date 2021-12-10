package com.jeremyantoine.speedjumper.Jeu;

public interface Notificateur {
    void notifier();
    void attacher(Observateur o);
    void detacher(Observateur o);
}
