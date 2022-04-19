package fr.leobatouxas.gestionculture.modele;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.leobatouxas.gestionculture.R;

public class CahierCultureAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<CahierCulture> cahierCultures;

    public CahierCultureAdapter(List<CahierCulture> cahierCultures){
        this.cahierCultures = cahierCultures;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cahier_culture_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.display(cahierCultures.get(position));
    }

    @Override
    public int getItemCount() {
        return cahierCultures.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    private TextView mCodeExploitation;
    private TextView mAnnee;

    MyViewHolder(View itemView){
        super(itemView);

        mCodeExploitation = (TextView) itemView.findViewById(R.id.CahierCultureCodeExploitation);
        mAnnee = (TextView) itemView.findViewById(R.id.CahierCultureAnnee);
    }

    void display(CahierCulture cahierCulture){
        mCodeExploitation.setText(cahierCulture.getExploitation().getCodeExploitation());
        mAnnee.setText(cahierCulture.getAnnee());
    }
}