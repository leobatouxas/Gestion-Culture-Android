package fr.leobatouxas.gestionculture.modele;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.Global;

public class CahierCulture {

    private int idCahierCulture;
    private Exploitation exploitation;
    private String annee;
    private ArrayList<Parcelle> parcelles;

    public CahierCulture() {
    }

    public CahierCulture(int idCahierCulture,Exploitation exploitation, String annee, ArrayList<Parcelle> parcelles) {
        this.idCahierCulture = idCahierCulture;
        this.exploitation = exploitation;
        this.annee = annee;
        this.parcelles = parcelles;
    }

    public CahierCulture(int idCahierCulture, Exploitation exploitation, String annee) {
        this.idCahierCulture = idCahierCulture;
        this.exploitation = exploitation;
        this.annee = annee;
    }

    public void createSQLite(){
        ContentValues values = new ContentValues();
        values.put("codeExploitation", this.exploitation.getCodeExploitation());
        values.put("annee", this.annee);
        Global.bddsqlLite.insert("cahierCulture", null, values);
    }
    public void findBy(String CodeExploitation, String annee){
        Cursor c = Global.bddsqlLite.rawQuery("SELECT idCahierCulture,annee,codeExploitation FROM cahierCulture where codeExploitation = '" + CodeExploitation + "' AND annee = '" + annee + "';", null);
        while (c.moveToNext())
        {
            this.idCahierCulture = c.getInt(0);
            this.annee = c.getString(1);
            this.exploitation = new Exploitation(CodeExploitation);
        }
    }
    public boolean existSQLite(){
        Cursor c = Global.bddsqlLite.rawQuery("SELECT count(codeExploitation) FROM cahierCulture where codeExploitation = '" + this.exploitation.getCodeExploitation() + "' AND annee = '" + this.annee + "';", null);
        Boolean exist = false;
        while (c.moveToNext( ))
        {
            if(Integer.parseInt(c.getString(0)) != 0) {
                exist = true;
            }else {
                exist = false;
            }
        }
        return exist;
    }
    public int getIdCahierCulture() {
        return idCahierCulture;
    }

    public void setIdCahierCulture(int idCahierCulture) {
        this.idCahierCulture = idCahierCulture;
    }

    public Exploitation getExploitation() {
        return exploitation;
    }

    public void setExploitation(Exploitation exploitation) {
        this.exploitation = exploitation;
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
