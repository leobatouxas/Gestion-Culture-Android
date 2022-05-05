package fr.leobatouxas.gestionculture.modele;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.Global;

public class Parcelle {

    private Integer idParcelle;
    private Double surface;
    private Double rendementRealise;
    private Double rendementPrevu;
    private CahierCulture cahierCulture;
    private Espece espece;

    public Parcelle() {
    }

    public Parcelle(Integer idParcelle, Double surface, Double rendementRealise, Double rendementPrevu, CahierCulture cahierCulture, Espece espece) {
        this.idParcelle = idParcelle;
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
    public void update(String id){
        ContentValues values = new ContentValues();
        values.put("surface", surface);
        values.put("rendementPrevu", rendementPrevu);
        values.put("rendementRealise", rendementRealise);
        values.put("codeEspece", this.espece.getCodeEspece());
        values.put("idCahierCulture", cahierCulture.getIdCahierCulture());
        Global.bddsqlLite.update("parcelle",values,"idParcelle = ?",new String[]{id});
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
    public void retrieve(int idParcelle){
        Cursor c = Global.bddsqlLite.rawQuery("select surface, rendementPrevu, rendementRealise, codeEspece, idCahierCulture from parcelle WHERE idParcelle = " + idParcelle + ";" , null);
        c.moveToFirst();
        this.idParcelle = idParcelle;
        this.surface = c.getDouble(0);
        this.rendementPrevu = c.getDouble(1);
        this.rendementRealise = c.getDouble(2);
        String codeEspece = c.getString(3);
        Espece espece = new Espece();
        espece.retrieve(codeEspece);
        this.espece = espece;
        String idCahierculture = c.getString(4);
        CahierCulture cahierCulture = new CahierCulture();
        cahierCulture.retrieve(Integer.parseInt(idCahierculture));
        this.cahierCulture = cahierCulture;
        c.close();
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
