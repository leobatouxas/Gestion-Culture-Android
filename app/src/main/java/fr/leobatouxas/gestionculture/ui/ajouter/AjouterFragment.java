package fr.leobatouxas.gestionculture.ui.ajouter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import fr.leobatouxas.gestionculture.AjoutExploitant;
import fr.leobatouxas.gestionculture.MainActivity;
import fr.leobatouxas.gestionculture.R;
import fr.leobatouxas.gestionculture.databinding.FragmentAjouterBinding;


public class AjouterFragment extends Fragment {

    private FragmentAjouterBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ajouter, container, false);


        Button btn = rootView.findViewById(R.id.btnAccueilSuivant);
        btn.setOnClickListener(btnAjouterExploitant);

        return rootView;

    }

    private final View.OnClickListener btnAjouterExploitant = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), AjoutExploitant.class);
             ((MainActivity) getActivity()).startActivity(intent);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}