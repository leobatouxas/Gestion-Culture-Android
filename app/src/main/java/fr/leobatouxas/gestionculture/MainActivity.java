package fr.leobatouxas.gestionculture;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;

import fr.leobatouxas.gestionculture.databinding.ActivityMainBinding;
import fr.leobatouxas.gestionculture.modele.AccesDistant;
import fr.leobatouxas.gestionculture.modele.BaseDeDonneesSQLite;
import fr.leobatouxas.gestionculture.modele.Exploitation;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Création base de données SQL Lite
        int VERSION_BDD = 1;
        BaseDeDonneesSQLite baseDeDonneesSQLite = new BaseDeDonneesSQLite(getBaseContext(), "gestionculture.db", null,
                VERSION_BDD);
        Global.bddsqlLite= baseDeDonneesSQLite.getWritableDatabase();

        //Création instance base de données distante
        //Global.accesDistant = new AccesDistant();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_ajouter, R.id.navigation_consulter, R.id.navigation_transferer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);






    }

}