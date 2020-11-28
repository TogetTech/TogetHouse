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
import cm.togettech.togethouse.EventBus.ChambreDetailClick;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChambreAdapter extends RecyclerView.Adapter<ChambreAdapter.MyViewHolder>{

    Context context;
    List<ChambreModel> chambreModelList;

    public ChambreAdapter(Context context, List<ChambreModel> chambreModelList) {
        this.context = context;
        this.chambreModelList = chambreModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_item_chambre, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChambreAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(chambreModelList.get(position).getChambre_image())
                .into(holder.chambre_image);
        holder.chambre_name.setText(new StringBuilder(chambreModelList.get(position).getChambre_nom()));
        holder.chambre_prix.setText(new StringBuilder(chambreModelList.get(position).getChambre_prix()));
        holder.chambre_quartier.setText(new StringBuilder(chambreModelList.get(position).getChambre_quartier()));


        //Event
        holder.setListener((view, pos) -> {
            Common.selectedChambre = chambreModelList.get(pos);
            EventBus.getDefault().postSticky(new ChambreDetailClick(true, chambreModelList.get(pos)));
        });


    }

    @Override
    public int getItemCount() {
        return chambreModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener     {
        Unbinder unbinder;
        @BindView(R.id.chambre_image)
        CircleImageView chambre_image;
        @BindView(R.id.chambre_name)
        TextView chambre_name;
        @BindView(R.id.chambre_prix)
        TextView chambre_prix;
        @BindView(R.id.chambre_quartier)
        TextView chambre_quartier;

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
        if (chambreModelList.size() == 1)
            return Common.DEFAULT_COLUMN_COUNT;
        else {
            if (chambreModelList.size() % 2 == 0)
                return Common.DEFAULT_COLUMN_COUNT;
            else
                return (position > 1 && position == chambreModelList.size() - 1) ? Common.FULL_WIDTH_COLUMN:Common.DEFAULT_COLUMN_COUNT;
        }
    }
}
