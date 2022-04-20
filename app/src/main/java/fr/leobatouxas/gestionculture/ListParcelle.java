package fr.leobatouxas.gestionculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListParcelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_parcelle);

        Intent thisIntent = getIntent();
        String codeExploitation = thisIntent.getExtras().getString("codeExploitation");
        String annee = thisIntent.getExtras().getString("annee");


        Spinner spinner = (Spinner) findViewById(R.id.spinner_consulter_parcelle);
        Cursor c = Global.bddsqlLite.rawQuery("select idCahierCulture from cahierculture WHERE codeExploitation = '" + codeExploitation + "' AND annee = '" + annee + "';" , null);
        int idCahierCulture = 0;
        while(c.moveToNext()){
            idCahierCulture = c.getInt(0);
        }

        ArrayList<CharSequence> Parcelles = new ArrayList<CharSequence>();
        Cursor d = Global.bddsqlLite.rawQuery("SELECT idParcelle, espece.libelle, surface FROM parcelle INNER JOIN espece ON espece.codeEspece = parcelle.codeEspece WHERE idCahierCulture = " + idCahierCulture + ";", null);
        while (d.moveToNext()){
            Parcelles.add(d.getString(0) + "-" + d.getString(1) + "-" + d.getString(2));
        }

        ArrayAdapter<CharSequence> DataAdapter = new ArrayAdapter<CharSequence>(this,
                android.R.layout.simple_list_item_1, Parcelles);
        spinner.setAdapter(DataAdapter);

        Button btnConsulter = findViewById(R.id.btn_consulter_parcelle);
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerParcelle = findViewById(R.id.spinner_consulter_parcelle);
                String libelleParcelle = spinnerParcelle.getItemAtPosition(spinnerParcelle.getSelectedItemPosition()).toString();
                String[] splitCahierCulture = libelleParcelle.split("-");
                String idParcelle = splitCahierCulture[0];

                //Passage à l'activité pour modifier, supprimer cahierCulture
                Intent intent = new Intent(ListParcelle.this, ModifParcelle.class);
                intent.putExtra("idParcelle", idParcelle);
                startActivity(intent);
            }
        });
    }
}