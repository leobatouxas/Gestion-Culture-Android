package fr.leobatouxas.gestionculture;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.modele.AccesDistant;
import fr.leobatouxas.gestionculture.modele.BaseDeDonneesSQLite;
import fr.leobatouxas.gestionculture.modele.Exploitant;
import fr.leobatouxas.gestionculture.modele.Exploitation;

public class Global {
    public static SQLiteDatabase bddsqlLite;
    public static AccesDistant accesDistant;
    public static Exploitant lastExploitant;
    public static Exploitation lastExploitationByExploitant;
    public static ArrayList<Exploitant> lesExploitants = new ArrayList<Exploitant>();
    public static ArrayList<Exploitation> lesExploitationsByExploitant = new ArrayList<Exploitation>();
 }
