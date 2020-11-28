package cm.togettech.togethouse.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.asksira.loopingviewpager.LoopingPagerAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.R;

public class BestChambreAdapter extends LoopingPagerAdapter<ChambreModel> {

    @BindView(R.id.chambre_image)
    ImageView chambre_image;


    Unbinder unbinder;

    public BestChambreAdapter(Context context, List<ChambreModel> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.layout_item_chambre, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        unbinder = ButterKnife.bind(this, convertView);

        //Set data
        Glide.with(convertView).load(itemList.get(listPosition).getChambre_image()).into(chambre_image);

    }
}
