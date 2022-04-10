package fr.leobatouxas.gestionculture.ui.ajouter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fr.leobatouxas.gestionculture.Global;
import fr.leobatouxas.gestionculture.MainActivity;
import fr.leobatouxas.gestionculture.R;
import fr.leobatouxas.gestionculture.databinding.FragmentAjouterBinding;
import fr.leobatouxas.gestionculture.modele.CahierCulture;
import fr.leobatouxas.gestionculture.modele.Exploitation;


public class AjouterFragment extends Fragment {

    private FragmentAjouterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ajouter, container, false);

        // BTN Ajouter parcelle -> Ajout Parcelle
        Button btnAjouterParcelle = rootView.findViewById(R.id.btnAjouterParcelle);
        btnAjouterParcelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextcodeExploitation = rootView.findViewById(R.id.editTextcodeExploitation);
                EditText editTextAnnee = rootView.findViewById(R.id.editTextAnnee);

                if(!editTextcodeExploitation.getText().toString().isEmpty() & editTextAnnee.getText().toString().length() >= 4) {
                    String codeExploitationtxt = editTextcodeExploitation.getText().toString();
                    String anneetxt = editTextAnnee.getText().toString();

                    CahierCulture cahierCulture = new CahierCulture();
                    cahierCulture.setExploitation(new Exploitation(codeExploitationtxt));
                    cahierCulture.setAnnee(anneetxt);

                    //Vérification existence cahier culture
                    //Création du cahier culture s'il n'existe pas en BDD
                    if(cahierCulture.existSQLite() == false) {
                        cahierCulture.createSQLite();
                    }

                    //Passage à l'activité pour créer une parcelle
                    Intent intent = new Intent(getActivity(), AjoutParcelle.class);
                    intent.putExtra("codeExploitation", codeExploitationtxt);
                    intent.putExtra("annee", anneetxt);
                    ((MainActivity) getActivity()).startActivity(intent);
                }
            }
        });

        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}