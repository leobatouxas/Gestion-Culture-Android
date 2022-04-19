package fr.leobatouxas.gestionculture.ui.consulter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.leobatouxas.gestionculture.R;
import fr.leobatouxas.gestionculture.databinding.FragmentConsulterBinding;
import fr.leobatouxas.gestionculture.modele.CahierCulture;
import fr.leobatouxas.gestionculture.modele.CahierCultureAdapter;
import fr.leobatouxas.gestionculture.modele.Exploitation;

public class ConsulterFragment extends Fragment {

    private FragmentConsulterBinding binding;
    private List<CahierCulture> mesCahierCultures;
    private RecyclerView mRecyclerView;
    private CahierCultureAdapter cahierCultureAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_consulter, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mesCahierCultures = new ArrayList<>();

        Exploitation exploitation = new Exploitation("e5");
        mesCahierCultures.add(new CahierCulture(exploitation,"2020"));

        cahierCultureAdapter = new CahierCultureAdapter(mesCahierCultures);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mRecyclerView.setAdapter(cahierCultureAdapter);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}