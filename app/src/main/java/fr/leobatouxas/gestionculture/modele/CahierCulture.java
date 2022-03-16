package fr.leobatouxas.gestionculture.modele;

import java.util.ArrayList;

public class CahierCulture {

    private String codeCahierCulture;
    private String annee;
    private ArrayList<Parcelle> parcelles;

    public CahierCulture() {
    }

    public CahierCulture(String codeCahierCulture, String annee, ArrayList<Parcelle> parcelles) {
        this.codeCahierCulture = codeCahierCulture;
        this.annee = annee;
        this.parcelles = parcelles;
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

    public String getCodeCahierCulture() {
        return codeCahierCulture;
    }

    public void setCodeCahierCulture(String codeCahierCulture) {
        this.codeCahierCulture = codeCahierCulture;
    }
}
