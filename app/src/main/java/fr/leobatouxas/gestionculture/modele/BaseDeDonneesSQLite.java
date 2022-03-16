package fr.leobatouxas.gestionculture.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonneesSQLite extends SQLiteOpenHelper {

    private static final String CREATE_EXPLOITANT = "CREATE TABLE exploitant ("
            + "idExploitant INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nom TEXT NOT NULL,"
            + "prenom TEXT NOT NULL,"
            + "adresse TEXT NOT NULL,"
            + "ville TEXT NOT NULL,"
            + "codePostal TEXT NOT NULL,"
            + "telephone TEXT NOT NULL,"
            + "email TEXT NOT NULL);";

    private static final String CREATE_ESPECE = "CREATE TABLE espece ("
            + "codeEspece TEXT PRIMARY KEY, "
            + "libelle TEXT NOT NULL);";

    private static final String CREATE_EXPLOITATION = "CREATE TABLE exploitation ("
            + "codeExploitation TEXT PRIMARY KEY, "
            + "adresse TEXT NOT NULL,"
            + "ville TEXT NOT NULL,"
            + "codePostal TEXT NOT NULL,"
            + "idExploitant INTEGER NOT NULL,"
            + "FOREIGN KEY (idExploitant) REFERENCES exploitant(idExploitant));";

    private static final String CREATE_CAHIERCULTURE = "CREATE TABLE cahierCulture ("
            + "idCahierCulture INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "annee INTEGER NOT NULL,"
            + "codeExploitation TEXT NOT NULL,"
            + "FOREIGN KEY (codeExploitation) REFERENCES exploitation(codeExploitation));";

    private static final String CREATE_PARCELLE = "CREATE TABLE parcelle ("
            + "idParcelle INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "surface REAL NOT NULL,"
            + "rendementPrevu REAL NOT NULL,"
            + "rendementRealise REAL NOT NULL,"
            + "codeEspece TEXT NOT NULL,"
            + "idCahierCulture TEXT NOT NULL,"
            + "FOREIGN KEY (codeEspece) REFERENCES espece(codeEspece),"
            + "FOREIGN KEY (idCahierCulture) REFERENCES cahierCulture(idCahierCulture));";

    public BaseDeDonneesSQLite (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EXPLOITANT);
        db.execSQL(CREATE_ESPECE);
        db.execSQL(CREATE_EXPLOITATION);
        db.execSQL(CREATE_CAHIERCULTURE);
        db.execSQL(CREATE_PARCELLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS parcelle;");
        db.execSQL("DROP TABLE IF EXISTS cahierculture;");
        db.execSQL("DROP TABLE IF EXISTS exploitation;");
        db.execSQL("DROP TABLE IF EXISTS espece;");
        db.execSQL("DROP TABLE IF EXISTS exploitant;");
        onCreate(db);
    }
}
