package fr.leobatouxas.gestionculture.ui.ajouter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fr.leobatouxas.gestionculture.AjoutExploitant;
import fr.leobatouxas.gestionculture.Global;
import fr.leobatouxas.gestionculture.MainActivity;
import fr.leobatouxas.gestionculture.R;
import fr.leobatouxas.gestionculture.databinding.FragmentAjouterBinding;
import fr.leobatouxas.gestionculture.modele.Exploitant;
import fr.leobatouxas.gestionculture.modele.Exploitation;


public class AjouterFragment extends Fragment {

    private FragmentAjouterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ajouter, container, false);

        // BTN Actualiser
        Button btnactu = rootView.findViewById(R.id.btnActu);
        btnactu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arraySpinner = new ArrayList<String>();
                for(int i=0;i<Global.lesExploitants.toArray().length;i++){
                    arraySpinner.add(Global.lesExploitants.get(i).getNom() + Global.lesExploitants.get(i).getPrenom());
                }
                Spinner s = (Spinner) rootView.findViewById(R.id.spinnerExpoitant);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arraySpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(adapter);
            }
        });



        // BTN Créer Exploitant -> Ajout Exploitant
        Button btn = rootView.findViewById(R.id.btnCreerExploitant);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AjoutExploitant.class);
                ((MainActivity) getActivity()).startActivity(intent);
                Global.accesDistant.recup("lastExploitant");
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