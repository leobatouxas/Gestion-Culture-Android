package fr.leobatouxas.gestionculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ModifParcelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_parcelle);

        Intent thisIntent = getIntent();
        String idParcelle = thisIntent.getExtras().getString("idParcelle");


        //Ajout Espèce aux Spinner
        final Spinner spinnerEspeceModif = (Spinner) findViewById(R.id.spinnerEspeceModif);
        ArrayList<CharSequence> Especes = new ArrayList<CharSequence>();
        Cursor d = Global.bddsqlLite.rawQuery("select codeEspece, libelle from espece", null);
        while (d.moveToNext())
        {
            Especes.add(d.getString(1));
        }
        ArrayAdapter<CharSequence> DataAdapter = new ArrayAdapter<CharSequence> (this,
                android.R.layout.simple_list_item_1, Especes);
        spinnerEspeceModif.setAdapter(DataAdapter);
        d.close();
        Cursor c = Global.bddsqlLite.rawQuery("select surface, rendementPrevu, rendementRealise, codeEspece from parcelle WHERE idParcelle = " + idParcelle + ";" , null);
        c.moveToFirst();
        String surface = c.getString(0);
        String rendementPrevu = c.getString(1);
        String rendementRealise = c.getString(2);
        String codeEspece = c.getString(3);
        c.close();
        EditText editTextSurface = findViewById(R.id.editTextSurfaceModif);
        EditText editTextRendementPrevu = findViewById(R.id.editTextRendementPrevuModif);
        EditText editTextRendementRealise = findViewById(R.id.editTextRendementRealiseModif);

        editTextSurface.setText(surface);
        editTextRendementPrevu.setText(rendementPrevu);
        editTextRendementRealise.setText(rendementRealise);

        if(codeEspece.equals("BLE")){
            spinnerEspeceModif.setSelection(0);
        }else if(codeEspece.equals("ORGE")){
            spinnerEspeceModif.setSelection(1);
        }
        else if(codeEspece.equals("BETTE")){
            spinnerEspeceModif.setSelection(2);
        }

    }
}