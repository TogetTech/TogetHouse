package cm.togettech.togethouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import cm.togettech.togethouse.Model.AppartementModels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.R;

public class AppartementAdapter extends RecyclerView.Adapter<AppartementAdapter.MyViewHolder> {

    Context context;
    List<AppartementModels>appartementModels;

    public AppartementAdapter(Context context, List<AppartementModels> appartementModels) {
        this.context = context;
        this.appartementModels = appartementModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_item_appartement, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(appartementModels.get(position).getAppart_image())
               .into(holder.appartement_image);


        holder.appartement_name.setText(appartementModels.get(position).getAppart_nom());
        holder.appartement_quartier.setText(appartementModels.get(position).getAppart_quartier());
        holder.appartement_prix.setText(appartementModels.get(position).getAppart_prix());


    }

    @Override
    public int getItemCount() {
        return appartementModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Unbinder unbinder;

        @BindView(R.id.appartement_image)
        ImageView appartement_image;
        @BindView(R.id.appartement_image2)
        ImageView appartement_image2;
        @BindView(R.id.appartement_image3)
        ImageView appartement_image3;
        @BindView(R.id.appartement_image4)
        ImageView appartement_image4;
        @BindView(R.id.appartement_image5)
        ImageView appartement_image5;

        @BindView(R.id.appartement_name)
        TextView appartement_name;
        @BindView(R.id.appartement_prix)
        TextView appartement_prix;
        @BindView(R.id.appartement_quartier)
        TextView appartement_quartier;
        @BindView(R.id.appartement_ville)
        TextView appartement_ville;
        @BindView(R.id.appartement_description)
        TextView appartement_description;
        @BindView(R.id.appartement_email)
        TextView appartement_email;
        @BindView(R.id.appartement_mois)
        TextView appartement_mois;
        @BindView(R.id.appartement_responsable)
        TextView appartement_responsable;
        @BindView(R.id.appartement_contact1)
        TextView appartement_contact1;
        @BindView(R.id.appartement_contact2)
        TextView appartement_contact2;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }


    }
}
