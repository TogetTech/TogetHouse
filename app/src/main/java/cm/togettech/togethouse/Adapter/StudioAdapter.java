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
import cm.togettech.togethouse.Callback.IRecyclerClickListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.StudioModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.R;

public class StudioAdapter extends RecyclerView.Adapter<StudioAdapter.MyViewHolder>{

    Context context;
    List<StudioModel> studioModelList;

    public StudioAdapter(Context context, List<StudioModel> studioModelList) {
        this.context = context;
        this.studioModelList = studioModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_item_studio, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(studioModelList.get(position).getStudio_image())
                .into(holder.studio_image);
        holder.studio_nom.setText(new StringBuilder(studioModelList.get(position).getStudio_nom()));
        holder.studio_prix.setText(new StringBuilder(studioModelList.get(position).getStudio_prix()));
        holder.studio_quartier.setText(new StringBuilder(studioModelList.get(position).getStudio_quartier()));


        /*Event
        holder.setListener((view, pos) -> {
            Common.studioSelected = studioModelList.get(pos);
            EventBus.getDefault().postSticky(new ChambreClick(true, studioModelList.get(pos)));
        });

         */

    }

    @Override
    public int getItemCount() {
        return studioModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener     {
        Unbinder unbinder;

        @BindView(R.id.studio_image)
        ImageView studio_image;
        @BindView(R.id.studio_image2)
        ImageView studio_image2;
        @BindView(R.id.studio_image3)
        ImageView studio_image3;
        @BindView(R.id.studio_image4)
        ImageView studio_image4;
        @BindView(R.id.studio_image5)
        ImageView studio_image5;

        @BindView(R.id.studio_nom)
        TextView studio_nom;
        @BindView(R.id.studio_prix)
        TextView studio_prix;
        @BindView(R.id.studio_quartier)
        TextView studio_quartier;
        @BindView(R.id.studio_ville)
        TextView studio_ville;
        @BindView(R.id.studio_description)
        TextView studio_description;
        @BindView(R.id.studio_email)
        TextView studio_email;
        @BindView(R.id.studio_mois)
        TextView studio_mois;
        @BindView(R.id.studio_responsable)
        TextView studio_responsable;
        @BindView(R.id.studio_contact1)
        TextView studio_contact1;
        @BindView(R.id.studio_contact2)
        TextView studio_contact2;

        IRecyclerClickListener listener;

        public void setListener(IRecyclerClickListener listener){
            this.listener = listener;
        }

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClickListener(view, getAdapterPosition());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (studioModelList.size() == 1)
            return Common.DEFAULT_COLUMN_COUNT;
        else {
            if (studioModelList.size() % 2 == 0)
                return Common.DEFAULT_COLUMN_COUNT;
            else
                return (position > 1 && position == studioModelList.size() - 1) ? Common.FULL_WIDTH_COLUMN:Common.DEFAULT_COLUMN_COUNT;
        }
    }
}
