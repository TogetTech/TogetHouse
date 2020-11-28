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

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Callback.IRecyclerClickListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.EventBus.TerrainDetailClick;
import cm.togettech.togethouse.Model.TerrainModel;
import cm.togettech.togethouse.R;


public class TerrainAdapter extends RecyclerView.Adapter<TerrainAdapter.MyViewHolder>{

    Context context;
    List<TerrainModel> terrainModelList;

    public TerrainAdapter(Context context, List<TerrainModel> terrainModelList) {
        this.context = context;
        this.terrainModelList = terrainModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item_terrain, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(terrainModelList.get(position).getTerrain_image())
                .into(holder.terrain_image);

        holder.terrain_nom.setText(terrainModelList.get(position).getTerrain_nom());
        holder.terrain_quartier.setText(terrainModelList.get(position).getTerrain_quartier());
        holder.terrain_prix.setText(terrainModelList.get(position).getTerrain_prix());
        holder.terrain_description.setText(terrainModelList.get(position).getTerrain_description());

        //Event
        holder.setListener((view, pos) -> {
            Common.selectedTerrain = terrainModelList.get(pos);
            EventBus.getDefault().postSticky(new TerrainDetailClick(true, terrainModelList.get(pos)));
        });

    }

    @Override
    public int getItemCount() {
        return terrainModelList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Unbinder unbinder;

        @BindView(R.id.terrain_image)
        ImageView terrain_image;
        @BindView(R.id.terrain_image2)
        ImageView terrain_image2;
        @BindView(R.id.terrain_image3)
        ImageView terrain_image3;
        @BindView(R.id.terrain_image4)
        ImageView maison_image4;
        @BindView(R.id.terrain_image5)
        ImageView terrain_image5;

        @BindView(R.id.terrain_nom)
        TextView terrain_nom;
        @BindView(R.id.terrain_prix)
        TextView terrain_prix;
        @BindView(R.id.terrain_quartier)
        TextView terrain_quartier;
        @BindView(R.id.terrain_ville)
        TextView terrain_ville;
        @BindView(R.id.terrain_description)
        TextView terrain_description;
        @BindView(R.id.terrain_email)
        TextView terrain_email;
        @BindView(R.id.terrain_mois)
        TextView terrain_mois;
        @BindView(R.id.terrain_responsable)
        TextView terrain_responsable;
        @BindView(R.id.terrain_contact1)
        TextView terrain_contact1;
        @BindView(R.id.terrain_contact2)
        TextView terrain_contact2;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener){
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClickListener(v, getAdapterPosition());
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (terrainModelList.size() == 1)
            return Common.DEFAULT_COLUMN_COUNT;
        else {
            if (terrainModelList.size() % 2 == 0)
                return Common.DEFAULT_COLUMN_COUNT;
            else
                return (position > 1 && position == terrainModelList.size() - 1) ? Common.FULL_WIDTH_COLUMN:Common.DEFAULT_COLUMN_COUNT;
        }
    }
}
