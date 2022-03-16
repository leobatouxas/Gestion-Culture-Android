package fr.leobatouxas.gestionculture.modele;

import fr.leobatouxas.gestionculture.outils.AsyncResponse;

public class AccesDistant implements AsyncResponse {

    private static final String SERVERADDR = "http://10.123.33.22/script/insert.php";

    /**
     * Retour du serveur  distant
     * @param output
     */
    @Override
    public void processFinish(String output) {

    }
}
