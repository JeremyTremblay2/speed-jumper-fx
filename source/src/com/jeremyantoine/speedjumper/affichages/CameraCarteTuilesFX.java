package com.jeremyantoine.speedjumper.affichages;

import com.jeremyantoine.speedjumper.observateurs.Observateur;
import com.jeremyantoine.speedjumper.cameras.CameraCarteTuiles;

import java.util.List;

public class CameraCarteTuilesFX implements Observateur {
    private CameraCarteTuiles camera;
    private List<TuileFX> lesTuiles;

    public CameraCarteTuilesFX(CameraCarteTuiles camera, List<TuileFX> lesTuiles) {
        this.camera = camera;
        this.lesTuiles = lesTuiles;
    }

    public CameraCarteTuiles getCamera() {
        return camera;
    }

    public List<TuileFX> getLesTuiles() {
        return lesTuiles;
    }

    @Override
    public void miseAjour() {

    }
}
