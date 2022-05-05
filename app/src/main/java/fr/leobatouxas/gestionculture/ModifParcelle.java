package fr.leobatouxas.gestionculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.modele.CahierCulture;
import fr.leobatouxas.gestionculture.modele.Parcelle;

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


        Parcelle parcelle = new Parcelle();
        parcelle.retrieve(Integer.parseInt(idParcelle));
        String codeEspece = parcelle.getEspece().getCodeEspece();

        EditText editTextSurface = findViewById(R.id.editTextSurfaceModif);
        EditText editTextRendementPrevu = findViewById(R.id.editTextRendementPrevuModif);
        EditText editTextRendementRealise = findViewById(R.id.editTextRendementRealiseModif);

        editTextSurface.setText(parcelle.getSurface().toString());
        editTextRendementPrevu.setText(parcelle.getRendementPrevu().toString());
        editTextRendementRealise.setText(parcelle.getRendementRealise().toString());

        if(codeEspece.equals("BLE")){
            spinnerEspeceModif.setSelection(0);
        }else if(codeEspece.equals("ORGE")){
            spinnerEspeceModif.setSelection(1);
        }
        else if(codeEspece.equals("BETTE")){
            spinnerEspeceModif.setSelection(2);
        }

        Button btnModifier =  findViewById(R.id.btn_modifParcelle);

        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}