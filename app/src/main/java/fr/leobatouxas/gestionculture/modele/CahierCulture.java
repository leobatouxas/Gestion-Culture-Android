package fr.leobatouxas.gestionculture.modele;

import java.util.ArrayList;

public class CahierCulture {

    private Integer idCahierCulture;
    private String annee;
    private ArrayList<Parcelle> parcelles;

    public CahierCulture() {
    }

    public CahierCulture(Integer idCahierCulture, String annee, ArrayList<Parcelle> parcelles) {
        this.idCahierCulture = idCahierCulture;
        this.annee = annee;
        this.parcelles = parcelles;
    }

    public Integer getIdCahierCulture() {
        return idCahierCulture;
    }

    public void setIdCahierCulture(Integer idCahierCulture) {
        this.idCahierCulture = idCahierCulture;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public ArrayList<Parcelle> getParcelles() {
        return parcelles;
    }

    public void setParcelles(ArrayList<Parcelle> parcelles) {
        this.parcelles = parcelles;
    }
}
