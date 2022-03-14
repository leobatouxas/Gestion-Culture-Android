package fr.leobatouxas.gestionculture.modele;

import java.util.ArrayList;

public class Espece {

    private Integer codeEspece;
    private String libelle;
    private Double rendementPrevus;
    private ArrayList<Parcelle> parcelles;

    public Espece() {
    }

    public Espece(Integer codeEspece, String libelle, Double rendementPrevus, ArrayList<Parcelle> parcelles) {
        this.codeEspece = codeEspece;
        this.libelle = libelle;
        this.rendementPrevus = rendementPrevus;
        this.parcelles = parcelles;
    }


    public Integer getCodeEspece() {
        return codeEspece;
    }

    public void setCodeEspece(Integer codeEspece) {
        this.codeEspece = codeEspece;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getRendementPrevus() {
        return rendementPrevus;
    }

    public void setRendementPrevus(Double rendementPrevus) {
        this.rendementPrevus = rendementPrevus;
    }

    public ArrayList<Parcelle> getParcelles() {
        return parcelles;
    }

    public void setParcelles(ArrayList<Parcelle> parcelles) {
        this.parcelles = parcelles;
    }


}
