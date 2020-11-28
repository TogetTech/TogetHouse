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
import cm.togettech.togethouse.EventBus.MaisonDetailClick;
import cm.togettech.togethouse.Model.MaisonModel;
import cm.togettech.togethouse.R;

public class MaisonAdapter extends RecyclerView.Adapter<MaisonAdapter.MyViewHolder>{

    Context context;
    List<MaisonModel> maisonModelList;

    public MaisonAdapter(Context context, List<MaisonModel> maisonModelList) {
        this.context = context;
        this.maisonModelList = maisonModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item_maison, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(maisonModelList.get(position).getMaison_image())
                .into(holder.maison_image);

        holder.maison_nom.setText(maisonModelList.get(position).getMaison_nom());
        holder.maison_quartier.setText(maisonModelList.get(position).getMaison_quartier());
        holder.maison_prix.setText(maisonModelList.get(position).getMaison_prix());
        holder.maison_description.setText(maisonModelList.get(position).getMaison_description());

        //Event
        holder.setListener((view, pos) -> {
            Common.selectedMaison = maisonModelList.get(pos);
            EventBus.getDefault().postSticky(new MaisonDetailClick(true, maisonModelList.get(pos)));
        });

    }

    @Override
    public int getItemCount() {
        return maisonModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Unbinder unbinder;

        @BindView(R.id.maison_image)
        ImageView maison_image;
        @BindView(R.id.maison_image2)
        ImageView maison_image2;
        @BindView(R.id.maison_image3)
        ImageView maison_image3;
        @BindView(R.id.maison_image4)
        ImageView maison_image4;
        @BindView(R.id.maison_image5)
        ImageView maison_image5;

        @BindView(R.id.maison_nom)
        TextView maison_nom;
        @BindView(R.id.maison_prix)
        TextView maison_prix;
        @BindView(R.id.maison_quartier)
        TextView maison_quartier;
        @BindView(R.id.maison_ville)
        TextView maison_ville;
        @BindView(R.id.maison_description)
        TextView maison_description;
        @BindView(R.id.maison_email)
        TextView maison_email;
        @BindView(R.id.maison_mois)
        TextView maison_mois;
        @BindView(R.id.maison_responsable)
        TextView maison_responsable;
        @BindView(R.id.maison_contact1)
        TextView maison_contact1;
        @BindView(R.id.maison_contact2)
        TextView maison_contact2;

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
        if (maisonModelList.size() == 1)
            return Common.DEFAULT_COLUMN_COUNT;
        else {
            if (maisonModelList.size() % 2 == 0)
                return Common.DEFAULT_COLUMN_COUNT;
            else
                return (position > 1 && position == maisonModelList.size() - 1) ? Common.FULL_WIDTH_COLUMN:Common.DEFAULT_COLUMN_COUNT;
        }
    }
}
