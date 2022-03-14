package fr.leobatouxas.gestionculture.modele;

import java.util.ArrayList;

public class Parcelle {

    private Integer idParcelle;
    private Double surface;
    private Double rendementRealise;
    private CahierCulture cahierCulture;
    private Espece espece;

    public Parcelle() {
    }

    public Parcelle(Integer idParcelle, Double surface, Double rendementRealise, CahierCulture cahierCulture, Espece espece) {
        this.idParcelle = idParcelle;
        this.surface = surface;
        this.rendementRealise = rendementRealise;
        this.cahierCulture = cahierCulture;
        this.espece = espece;
    }

    public Integer getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(Integer idParcelle) {
        this.idParcelle = idParcelle;
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
}
