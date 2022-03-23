package fr.leobatouxas.gestionculture.modele;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.Global;
import fr.leobatouxas.gestionculture.outils.AccesHTTP;
import fr.leobatouxas.gestionculture.outils.AsyncResponse;

public class AccesDistant implements AsyncResponse {

    private static final String SERVERADDR = "http://10.123.33.22/script/insert.php";

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
                        if(message[1] != "lastExploitant%null") {
                        JSONObject info = new JSONObject(message[1]);
                        String codeExploitant = info.getString("codeExploitant");
                        String nom = info.getString("nom");
                        String prenom = info.getString("prenom");
                        String adresse = info.getString("adresse");
                        String ville = info.getString("ville");
                        String codePostal = info.getString("codePostal");
                        String telephone = info.getString("telephone");
                        String email = info.getString("email");

                        Exploitant lastExploitant = new Exploitant(codeExploitant, nom, prenom, adresse, ville, codePostal, telephone, email);
                        Global.lastExploitant = lastExploitant;
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
                            Exploitation lastExploitation = new Exploitation(codeExploitation, adresse, ville, codePostal, exploitant);
                            Global.lastExploitationByExploitant = lastExploitation;
                        } catch (JSONException e) {
                            Log.d("erreur", "Conversion JSON impossible *******" + e.toString());
                        }
                    } else {
                        if (message[0].equals("AllExploitant")) {
                            Log.d("AllExploitant", "*******" + message[1]);
                            try{
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
                                    Exploitant Exploitant = new Exploitant(codeExploitant, nom, prenom, adresse, ville, codePostal, telephone, email);
                                    Global.lesExploitants.add(Exploitant);
                                }
                            } catch(JSONException e){
                                Log.d("erreur", "Conversion JSON impossible *******" + e.toString());
                            }

                            //try {
                              //  JSONObject info = new JSONObject(message[1]);
                             //   String codeExploitation = info.getString("codeExploitation");
                             //   String adresse = info.getString("adresse");
                             //   String ville = info.getString("ville");
                              //  String codePostal = info.getString("codePostal");
                              //  Exploitant exploitant = new Exploitant();
                              //  Exploitation lastExploitation = new Exploitation(codeExploitation, adresse, ville, codePostal, exploitant);
                              //  Global.lastExploitationByExploitant = lastExploitation;
                            //} catch (JSONException e) {
                             //   Log.d("erreur", "Conversion JSON impossible *******" + e.toString());
                            //}
                        }else {
                            if (message[0].equals("Erreur : ")) {
                                Log.d("Erreur :", "*******" + message[1]);
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
}
