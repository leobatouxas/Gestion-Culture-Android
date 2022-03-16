package fr.leobatouxas.gestionculture.modele;

import java.util.ArrayList;

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
