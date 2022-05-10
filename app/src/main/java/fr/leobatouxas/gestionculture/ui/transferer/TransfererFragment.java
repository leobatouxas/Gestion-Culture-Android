package fr.leobatouxas.gestionculture.ui.transferer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import fr.leobatouxas.gestionculture.Global;
import fr.leobatouxas.gestionculture.MainActivity;
import fr.leobatouxas.gestionculture.R;
import fr.leobatouxas.gestionculture.databinding.FragmentTransfererBinding;
import fr.leobatouxas.gestionculture.modele.AccesDistant;
import fr.leobatouxas.gestionculture.modele.CahierCulture;
import fr.leobatouxas.gestionculture.modele.Exploitation;
import fr.leobatouxas.gestionculture.ui.ajouter.AjoutParcelle;

public class TransfererFragment extends Fragment {

    private FragmentTransfererBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transferer, container, false);

        Button btnTransfert = rootView.findViewById(R.id.btnTransfert);
        btnTransfert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Global.accesDistant = new AccesDistant();
                Global.accesDistant.transfertcahierCulture();
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