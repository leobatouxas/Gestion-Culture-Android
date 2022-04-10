package fr.leobatouxas.gestionculture.ui.ajouter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.Global;
import fr.leobatouxas.gestionculture.MainActivity;
import fr.leobatouxas.gestionculture.R;
import fr.leobatouxas.gestionculture.modele.CahierCulture;
import fr.leobatouxas.gestionculture.modele.Espece;
import fr.leobatouxas.gestionculture.modele.Parcelle;

public class AjoutParcelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_parcelle);

        Intent thisIntent = getIntent();
        String codeExploitation = thisIntent.getExtras().getString("codeExploitation");
        String annee = thisIntent.getExtras().getString("annee");

        TextView textViewCodeExploitation = findViewById(R.id.textViewCodeExploitation);
        TextView textViewAnnee = findViewById(R.id.textViewAnnee);

        textViewCodeExploitation.setText(textViewCodeExploitation.getText() + " : " + codeExploitation);
        textViewAnnee.setText(textViewAnnee.getText() + " : " +annee);

        //Ajout Espèce aux Spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerEspece);
        ArrayList<CharSequence> Especes = new ArrayList<CharSequence>();
        Cursor c = Global.bddsqlLite.rawQuery("select codeEspece, libelle from espece", null);
        while (c.moveToNext())
        {
            Especes.add(c.getString(1));
        }
        ArrayAdapter<CharSequence> DataAdapter = new ArrayAdapter<CharSequence> (this,
                android.R.layout.simple_list_item_1, Especes);
        spinner.setAdapter(DataAdapter);

        Button btnEnregistrerParcelle = findViewById(R.id.btnEnregistrerParcelle);
        btnEnregistrerParcelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerEspece = findViewById(R.id.spinnerEspece);
                String libelleEspece = spinnerEspece.getItemAtPosition(spinnerEspece.getSelectedItemPosition()).toString();
                EditText editTextSurface = findViewById(R.id.editTextSurface);
                EditText editTextRendementPrevu = findViewById(R.id.editTextRendementPrevu);
                EditText editTextRendementRealise = findViewById(R.id.editTextRendementRealise);
                String surface = editTextSurface.getText().toString();
                String rendementPrevu = editTextRendementPrevu.getText().toString();
                String rendementRealise = editTextRendementRealise.getText().toString();

                Espece Espece = new Espece();
                Espece.findByLibelle(libelleEspece);
                CahierCulture cahierCulture = new CahierCulture();
                cahierCulture.findBy(codeExploitation,annee);

                Parcelle parcelle = new Parcelle(Double.parseDouble(surface),Double.parseDouble(rendementRealise),Double.parseDouble(rendementPrevu), cahierCulture, Espece);
                parcelle.createSQLite();

                Intent intent = new Intent(AjoutParcelle.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnAjouterParcelleAjoutParcelle = findViewById(R.id.btnAjouterParcelleAjoutParcelle);
        btnAjouterParcelleAjoutParcelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerEspece = findViewById(R.id.spinnerEspece);
                String libelleEspece = spinnerEspece.getItemAtPosition(spinnerEspece.getSelectedItemPosition()).toString();
                EditText editTextSurface = findViewById(R.id.editTextSurface);
                EditText editTextRendementPrevu = findViewById(R.id.editTextRendementPrevu);
                EditText editTextRendementRealise = findViewById(R.id.editTextRendementRealise);
                String surface = editTextSurface.getText().toString();
                String rendementPrevu = editTextRendementPrevu.getText().toString();
                String rendementRealise = editTextRendementRealise.getText().toString();

                Espece Espece = new Espece();
                Espece.findByLibelle(libelleEspece);
                CahierCulture cahierCulture = new CahierCulture();
                cahierCulture.findBy(codeExploitation,annee);

                Parcelle parcelle = new Parcelle(Double.parseDouble(surface),Double.parseDouble(rendementRealise),Double.parseDouble(rendementPrevu), cahierCulture, Espece);
                parcelle.createSQLite();

                editTextSurface.setText("");
                editTextRendementPrevu.setText("");
                editTextRendementRealise.setText("");
            }
        });
    }
}