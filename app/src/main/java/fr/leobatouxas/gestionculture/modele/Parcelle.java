package fr.leobatouxas.gestionculture.modele;

import android.content.ContentValues;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.Global;

public class Parcelle {

    private String idParcelle;
    private Double surface;
    private Double rendementRealise;
    private Double rendementPrevu;
    private CahierCulture cahierCulture;
    private Espece espece;

    public Parcelle() {
    }

    public Parcelle(String codeParcelle, Double surface, Double rendementRealise, Double rendementPrevu, CahierCulture cahierCulture, Espece espece) {
        this.idParcelle = codeParcelle;
        this.surface = surface;
        this.rendementRealise = rendementRealise;
        this.rendementPrevu = rendementPrevu;
        this.cahierCulture = cahierCulture;
        this.espece = espece;
    }

    public Parcelle(Double surface, Double rendementRealise, Double rendementPrevu, CahierCulture cahierCulture, Espece espece) {
        this.surface = surface;
        this.rendementRealise = rendementRealise;
        this.rendementPrevu = rendementPrevu;
        this.cahierCulture = cahierCulture;
        this.espece = espece;
    }

    public void createSQLite(){
        ContentValues values = new ContentValues();
        values.put("surface", surface);
        values.put("rendementPrevu", rendementPrevu);
        values.put("rendementRealise", rendementRealise);
        values.put("codeEspece", this.espece.getCodeEspece());
        values.put("idCahierCulture", cahierCulture.getIdCahierCulture());
        Global.bddsqlLite.insert("parcelle", null, values);
    }

    public String getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(String idParcelle) {
        this.idParcelle = idParcelle;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
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
