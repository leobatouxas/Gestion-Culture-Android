package fr.leobatouxas.gestionculture.modele;

import android.database.Cursor;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.Global;

public class Espece {

    private String codeEspece;
    private String libelle;
    private ArrayList<Parcelle> parcelles;

    public Espece() {
    }

    public Espece(String codeEspece, String libelle, Double rendementPrevus, ArrayList<Parcelle> parcelles) {
        this.codeEspece = codeEspece;
        this.libelle = libelle;
        this.parcelles = parcelles;
    }
    public void findByLibelle(String libelle){
        Cursor c = Global.bddsqlLite.rawQuery("SELECT codeEspece FROM espece where libelle = '" + libelle + "';", null);
        this.libelle = libelle;
        while (c.moveToNext( ))
        {
            this.codeEspece = c.getString(0);
        }

    }


    public String getCodeEspece() {
        return codeEspece;
    }

    public void setCodeEspece(String codeEspece) {
        this.codeEspece = codeEspece;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ArrayList<Parcelle> getParcelles() {
        return parcelles;
    }

    public void setParcelles(ArrayList<Parcelle> parcelles) {
        this.parcelles = parcelles;
    }


}
