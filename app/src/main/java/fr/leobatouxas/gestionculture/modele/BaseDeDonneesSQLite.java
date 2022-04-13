package fr.leobatouxas.gestionculture.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonneesSQLite extends SQLiteOpenHelper {

    private static final String CREATE_ESPECE = "CREATE TABLE espece ("
            + "codeEspece TEXT PRIMARY KEY, "
            + "libelle TEXT NOT NULL);";

    private static final String CREATE_EXPLOITATION = "CREATE TABLE exploitation ("
            + "codeExploitation TEXT PRIMARY KEY NOT NULL);";

    private static final String CREATE_CAHIERCULTURE = "CREATE TABLE cahierculture ("
            + "idCahierCulture INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "annee INTEGER NOT NULL,"
            + "codeExploitation TEXT NOT NULL,"
            + "FOREIGN KEY(codeExploitation) REFERENCES exploitation(codeExploitation));";

    private static final String CREATE_PARCELLE = "CREATE TABLE parcelle ("
            + "idParcelle INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "surface REAL NOT NULL,"
            + "rendementPrevu REAL NOT NULL,"
            + "rendementRealise REAL NOT NULL,"
            + "codeEspece TEXT NOT NULL,"
            + "idCahierCulture TEXT NOT NULL,"
            + "FOREIGN KEY (codeEspece) REFERENCES espece(codeEspece),"
            + "FOREIGN KEY (idCahierCulture) REFERENCES cahierculture(idCahierCulture));";

    public BaseDeDonneesSQLite (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_ESPECE);
        db.execSQL(CREATE_EXPLOITATION);
        db.execSQL(CREATE_CAHIERCULTURE);
        db.execSQL(CREATE_PARCELLE);
        db.execSQL("INSERT INTO espece (codeEspece, libelle) VALUES ('BLE', 'Blé'),('ORGE', 'Orge'),('BETTE', 'Betteraves');");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
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
