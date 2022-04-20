package fr.leobatouxas.gestionculture.ui.consulter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;

import fr.leobatouxas.gestionculture.Global;
import fr.leobatouxas.gestionculture.ListParcelle;
import fr.leobatouxas.gestionculture.MainActivity;
import fr.leobatouxas.gestionculture.R;
import fr.leobatouxas.gestionculture.databinding.FragmentConsulterBinding;

public class ConsulterFragment extends Fragment {

    private FragmentConsulterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_consulter, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner_consulter_cahierculture);
        ArrayList<CharSequence> CahierCultures = new ArrayList<CharSequence>();
        Cursor c = Global.bddsqlLite.rawQuery("select codeExploitation, annee, idCahierCulture from cahierculture", null);
        while (c.moveToNext())
        {
            CahierCultures.add(c.getString(0) + "/" + c.getString(1));
        }
        ArrayAdapter<CharSequence> DataAdapter = new ArrayAdapter<CharSequence>(getContext(),
                android.R.layout.simple_list_item_1, CahierCultures);
        spinner.setAdapter(DataAdapter);

        Button btnConsulter = rootView.findViewById(R.id.btn_consulter_cahierculture);
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinnerCahierCulture = rootView.findViewById(R.id.spinner_consulter_cahierculture);
                String libelleSpinnerCahierCulture = spinnerCahierCulture.getItemAtPosition(spinnerCahierCulture.getSelectedItemPosition()).toString();
                String[] splitCahierCulture = libelleSpinnerCahierCulture.split("/");
                String codeExploitation = splitCahierCulture[0];
                String annee = splitCahierCulture[1];

                //Passage à l'activité pour modifier, supprimer cahierCulture
                Intent intent = new Intent(getActivity(), ListParcelle.class);
                intent.putExtra("codeExploitation", codeExploitation);
                intent.putExtra("annee", annee);
                ((MainActivity) getActivity()).startActivity(intent);
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