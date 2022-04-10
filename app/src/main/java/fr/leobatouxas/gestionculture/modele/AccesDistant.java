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

    private static final String SERVERADDR = "http://0.0.0.0/api/insert.php";

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
            if (message[0].equals("enregExploitant")) {
                Log.d("enregExploitant", "*******" + message[1]);
            } else {
                if (message[0].equals("lastExploitant")) {
                    Log.d("dernier", "*******" + message[1]);
                    try {
                        if(!message[1].equals("null")) {
                        JSONObject info = new JSONObject(message[1]);
                        String codeExploitant = info.getString("codeExploitant");
                        String nom = info.getString("nom");
                        String prenom = info.getString("prenom");
                        String adresse = info.getString("adresse");
                        String ville = info.getString("ville");
                        String codePostal = info.getString("codePostal");
                        String telephone = info.getString("telephone");
                        String email = info.getString("email");

                        //Exploitant lastExploitant = new Exploitant(codeExploitant, nom, prenom, adresse, ville, codePostal, telephone, email);
                        //Global.lastExploitant = lastExploitant;
                        }else {
                            Global.lastExploitant = null;
                        }
                    } catch (JSONException e) {
                        Log.d("erreur", "Conversion JSON impossible *******" + e.toString());
                    }
                } else {
                    if (message[0].equals("lastExploitationByExploitant")) {
                        Log.d("dernier", "*******" + message[1]);
                        try {
                            JSONObject info = new JSONObject(message[1]);
                            String codeExploitation = info.getString("codeExploitation");
                            String adresse = info.getString("adresse");
                            String ville = info.getString("ville");
                            String codePostal = info.getString("codePostal");
                            Exploitant exploitant = new Exploitant();
                            //Exploitation lastExploitation = new Exploitation(codeExploitation, adresse, ville, codePostal, exploitant);
                            //Global.lastExploitationByExploitant = lastExploitation;
                        } catch (JSONException e) {
                            Log.d("erreur", "Conversion JSON impossible *******" + e.toString());
                        }
                    } else {
                        if (message[0].equals("AllExploitant")) {
                            try{
                                if(!message[1].equals("null")) {
                                    JSONArray JSONInfo = new JSONArray(message[1]);
                                    Global.lesExploitants.clear();
                                    for(int i=0;i<JSONInfo.length();i++){
                                        JSONObject info = new JSONObject(JSONInfo.get(i).toString());
                                        String codeExploitant = info.getString("codeExploitant");
                                        String nom = info.getString("nom");
                                        String prenom = info.getString("prenom");
                                        String adresse = info.getString("adresse");
                                        String ville = info.getString("ville");
                                        String codePostal = info.getString("codePostal");
                                        String telephone = info.getString("telephone");
                                        String email = info.getString("email");
                                        //Exploitant Exploitant = new Exploitant(codeExploitant, nom, prenom, adresse, ville, codePostal, telephone, email);
                                        //Global.lesExploitants.add(Exploitant);
                                    }
                                }else {
                                    Global.lesExploitants.clear();
                                }
                            } catch(JSONException e){
                                Log.d("erreur", "Conversion JSON impossible *******" + e.toString());
                            }
                        }else {
                            if(message[0].equals("ExploitationsByExploitant")){
                                try{
                                    if(!message[1].equals("null")){
                                        JSONArray JSONInfo = new JSONArray(message[1]);
                                        Global.lesExploitationsByExploitant.clear();
                                        for(int i=0;i<JSONInfo.length();i++){
                                            JSONObject info = new JSONObject(JSONInfo.get(i).toString());
                                            String codeExploitation = info.getString("codeExploitation");
                                            String codeExploitant = info.getString("codeExploitant");
                                            String adresse = info.getString("adresse");
                                            String ville = info.getString("ville");
                                            String codePostal = info.getString("codePostal");
                                            Exploitant Exploitant = new Exploitant();
                                            Exploitation Exploitation = new Exploitation(codeExploitation, adresse, ville, codePostal, Exploitant);
                                            Log.d("exploitantall",message[1]);
                                            Global.lesExploitationsByExploitant.add(Exploitation);
                                        }
                                    }
                                    else{
                                        Global.lesExploitationsByExploitant.clear();
                                    }

                                } catch(JSONException e){
                                    Log.d("erreur", "Conversion JSON impossible *******" + e.toString());
                                }
                            }else {
                                if (message[0].equals("enregExploitant")) {
                                    Log.d("enregExploitant", "*******" + message[1]);
                                }
                                if (message[0].equals("Erreur : ")) {
                                    Log.d("Erreur :", "*******" + message[1]);
                                }
                            }

                        }
                    }
                }
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
        Cursor c = Global.bddsqlLite.rawQuery("SELECT idCahierCulture,annee,codeExploitation FROM cahierCulture;", null);
        while (c.moveToNext())
        {
            JSONObject jsonObject=null;
            try {
                jsonObject=new JSONObject();
                jsonObject.put("idCahierCulture",c.getInt(0));
                jsonObject.put("annee",c.getString(1));
                jsonObject.put("codeExploitation",c.getString(2));
                String jsonStr=jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            list.add(jsonObject);


        }
        Log.d("cahierculture", new JSONArray(list).toString());
    }
}
