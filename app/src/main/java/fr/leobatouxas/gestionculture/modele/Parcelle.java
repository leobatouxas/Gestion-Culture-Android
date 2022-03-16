package fr.leobatouxas.gestionculture.modele;

import java.util.ArrayList;

public class Parcelle {

    private String codeParcelle;
    private Double surface;
    private Double rendementRealise;
    private Double rendementPrevu;
    private CahierCulture cahierCulture;
    private Espece espece;

    public Parcelle() {
    }

    public Parcelle(String codeParcelle, Double surface, Double rendementRealise, Double rendementPrevu, CahierCulture cahierCulture, Espece espece) {
        this.codeParcelle = codeParcelle;
        this.surface = surface;
        this.rendementRealise = rendementRealise;
        this.rendementPrevu = rendementPrevu;
        this.cahierCulture = cahierCulture;
        this.espece = espece;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public Double getrendementRealise() {
        return rendementRealise;
    }

    public void setrendementRealise(Double rendementRealise) {
        this.rendementRealise = rendementRealise;
    }

    public CahierCulture getCahierCulture() {
        return cahierCulture;
    }

    public void setCahierCulture(CahierCulture cahierCulture) {
        this.cahierCulture = cahierCulture;
    }

    public Espece getEspece() {
        return espece;
    }

    public void setEspece(Espece espece) {
        this.espece = espece;
    }

    public Double getRendementRealise() {
        return rendementRealise;
    }

    public void setRendementRealise(Double rendementRealise) {
        this.rendementRealise = rendementRealise;
    }

    public Double getRendementPrevu() {
        return rendementPrevu;
    }

    public void setRendementPrevu(Double rendementPrevu) {
        this.rendementPrevu = rendementPrevu;
    }

    public String getCodeParcelle() {
        return codeParcelle;
    }

    public void setCodeParcelle(String codeParcelle) {
        this.codeParcelle = codeParcelle;
    }
}
