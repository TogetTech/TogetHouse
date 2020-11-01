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
import cm.togettech.togethouse.EventBus.CategoryClick;
import cm.togettech.togethouse.Model.StudioModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.R;

public class MyStudioAdapter extends RecyclerView.Adapter<MyStudioAdapter.MyViewHolder>{

    Context context;
    List<StudioModel> studioModelList;

    public MyStudioAdapter(Context context, List<StudioModel> studioModelList) {
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
        holder.studio_name.setText(new StringBuilder(studioModelList.get(position).getStudio_nom()));
        holder.studio_prix.setText(new StringBuilder(studioModelList.get(position).getStudio_prix()));
        holder.studio_quartier.setText(new StringBuilder(studioModelList.get(position).getStudio_quartier()));


        //Event
        holder.setListener((view, pos) -> {
            Common.studioSelected = studioModelList.get(pos);
            EventBus.getDefault().postSticky(new CategoryClick(true, studioModelList.get(pos)));
        });

    }

    @Override
    public int getItemCount() {
        return studioModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener     {
        Unbinder unbinder;
        @BindView(R.id.studio_image)
        ImageView studio_image;
        @BindView(R.id.studio_name)
        TextView studio_name;
        @BindView(R.id.studio_prix)
        TextView studio_prix;
        @BindView(R.id.studio_quartier)
        TextView studio_quartier;

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
