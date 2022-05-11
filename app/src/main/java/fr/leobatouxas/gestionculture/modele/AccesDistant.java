package fr.leobatouxas.gestionculture.modele;

import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.leobatouxas.gestionculture.Global;
import fr.leobatouxas.gestionculture.outils.AccesHTTP;
import fr.leobatouxas.gestionculture.outils.AsyncResponse;

public class AccesDistant implements AsyncResponse {

    // Lien API
    private static final String SERVERADDR = "http://0.0.0.0/API/insert.php";

    public AccesDistant() {
        super();
    }

    /**
     * Retour du serveur  distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "*******" + output);
        //Découpage du message reçu avec %
        String[] message = output.split("%");
        //dans message[0] : "enreg", "dernier", "erreur"
        //dans message[1] : reste du message

        //S'il y a 2 cases
        if (message.length > 1) {
            if (message[0].equals("enreg")) {
                Log.d("enreg", "*******" + message[1]);
            }
        }
    }
    public void envoi(String operation, JSONArray DonnesJSON) {
        AccesHTTP accesDonnes = new AccesHTTP();
        // lien de délégation
        accesDonnes.delegate = this;
        // ajout paramètres
        accesDonnes.addParams("operation", operation);
        accesDonnes.addParams("donnees", DonnesJSON.toString());
        //appel au serveur
        accesDonnes.execute(SERVERADDR);
    }
    public void recup(String operation) {
        AccesHTTP accesDonnes = new AccesHTTP();
        // lien de délégation
        accesDonnes.delegate = this;
        // ajout paramètres
        accesDonnes.addParams("operation", operation);
        //appel au serveur
        accesDonnes.execute(SERVERADDR);
    }
    public void transfertcahierCulture(){
        List list = new ArrayList();
        Cursor c = Global.bddsqlLite.rawQuery("SELECT idCahierCulture,annee,codeExploitation " +
                " FROM cahierCulture ;", null);
        while (c.moveToNext())
        {

            JSONObject JSONCahierCulture = null;
            try {
                JSONCahierCulture=new JSONObject();
                JSONArray JSONCArrayParcelle = new JSONArray();

                JSONCahierCulture.put("idCahierCulture",c.getInt(0));
                JSONCahierCulture.put("annee",c.getString(1));
                JSONCahierCulture.put("codeExploitation",c.getString(2));

                Cursor d = Global.bddsqlLite.rawQuery("SELECT idParcelle, surface, rendementPrevu, rendementRealise, codeEspece " +
                        " FROM parcelle WHERE idCahierCulture = " + c.getInt(0) +";", null);
                JSONObject JSONParcelle = null;
                while (d.moveToNext()){
                    JSONParcelle = new JSONObject();
                    JSONParcelle.put("idParcelle",d.getString(0));
                    JSONParcelle.put("surface",d.getString(1));
                    JSONParcelle.put("rendementPrevu",d.getString(2));
                    JSONParcelle.put("rendementRealise",d.getString(3));
                    JSONParcelle.put("codeEspece",d.getString(4));
                    JSONCArrayParcelle.put(JSONParcelle);
                }
                JSONCahierCulture.put("parcelles",JSONCArrayParcelle);
            } catch (JSONException e) {
                    e.printStackTrace();
            }
            list.add(JSONCahierCulture);
            //Log.d("test",new JSONArray(list).toString());

        }

        this.envoi("enreg", new JSONArray(list));
        Global.bddsqlLite.execSQL("DELETE FROM parcelle");
        Global.bddsqlLite.execSQL("DELETE FROM cahierculture");
        Global.bddsqlLite.execSQL("DELETE FROM exploitation");
        //Log.d("cahierculture", new JSONArray(list).toString());
    }
}
