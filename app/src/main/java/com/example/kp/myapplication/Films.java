package com.example.kp.myapplication;

import android.graphics.Bitmap;

/**
 * Created by kp on 24/11/2017.
 */

public class Films {
    public String titre;
    public String acteur;
    public Bitmap icone;

    public void setActeur(String acteur) {
        this.acteur = acteur;
    }

    public void setIcone(Bitmap icone) {
        this.icone = icone;
    }

    public Films(String acteur, Bitmap icone, String titre) {
        this.acteur = acteur;
        this.icone = icone;
        this.titre = titre;
    }

    public void setTitre(String titre) {

        this.titre = titre;
    }

    public String getActeur() {

        return acteur;
    }

    public Bitmap getIcone() {
        return icone;
    }

    public String getTitre() {
        return titre;
    }
}
