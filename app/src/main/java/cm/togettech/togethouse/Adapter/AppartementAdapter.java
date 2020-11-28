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

import cm.togettech.togethouse.Callback.IRecyclerClickListener;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.EventBus.AppartementDetailClick;
import cm.togettech.togethouse.Model.AppartementModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.R;

public class AppartementAdapter extends RecyclerView.Adapter<AppartementAdapter.MyViewHolder> {

    Context context;
    List<AppartementModel>appartementModelList;

    public AppartementAdapter(Context context, List<AppartementModel> appartementModelList) {
        this.context = context;
        this.appartementModelList = appartementModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.layout_item_appartement, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(appartementModelList.get(position).getAppart_image())
               .into(holder.appart_image);

        holder.appart_nom.setText(appartementModelList.get(position).getAppart_nom());
        holder.appart_quartier.setText(appartementModelList.get(position).getAppart_quartier());
        holder.appart_prix.setText(appartementModelList.get(position).getAppart_prix());
        holder.appart_description.setText(appartementModelList.get(position).getAppart_description());

        //Event
        holder.setListener((view, pos) -> {
            Common.selectedAppartement = appartementModelList.get(pos);
            EventBus.getDefault().postSticky(new AppartementDetailClick(true, appartementModelList.get(pos)));
        });


    }

    @Override
    public int getItemCount() {
        return appartementModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Unbinder unbinder;

        @BindView(R.id.appart_image)
        ImageView appart_image;
        @BindView(R.id.appart_image2)
        ImageView appart_image2;
        @BindView(R.id.appart_image3)
        ImageView appart_image3;
        @BindView(R.id.appart_image4)
        ImageView appart_image4;
        @BindView(R.id.appart_image5)
        ImageView appart_image5;

        @BindView(R.id.appart_nom)
        TextView appart_nom;
        @BindView(R.id.appart_prix)
        TextView appart_prix;
        @BindView(R.id.appart_quartier)
        TextView appart_quartier;
        @BindView(R.id.appart_ville)
        TextView appart_ville;
        @BindView(R.id.appart_description)
        TextView appart_description;
        @BindView(R.id.appart_email)
        TextView appart_email;
        @BindView(R.id.appart_mois)
        TextView appartement_mois;
        @BindView(R.id.appart_responsable)
        TextView appart_responsable;
        @BindView(R.id.appart_contact1)
        TextView appart_contact1;
        @BindView(R.id.appart_contact2)
        TextView appart_contact2;

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
        public void onClick(View v) {
            listener.onItemClickListener(v, getAdapterPosition());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (appartementModelList.size() == 1)
            return Common.DEFAULT_COLUMN_COUNT;
        else {
            if (appartementModelList.size() % 2 == 0)
                return Common.DEFAULT_COLUMN_COUNT;
            else
                return (position > 1 && position == appartementModelList.size() - 1) ? Common.FULL_WIDTH_COLUMN:Common.DEFAULT_COLUMN_COUNT;
        }
    }
}
