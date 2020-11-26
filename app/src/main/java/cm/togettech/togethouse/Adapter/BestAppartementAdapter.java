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
import cm.togettech.togethouse.Model.AppartementModel;
import cm.togettech.togethouse.R;

public class BestAppartementAdapter extends LoopingPagerAdapter<AppartementModel> {

    @BindView(R.id.appart_image)
    ImageView appart_image;


    Unbinder unbinder;

    public BestAppartementAdapter(Context context, List<AppartementModel> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.layout_item_appartement, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        unbinder = ButterKnife.bind(this, convertView);

        //Set data
        Glide.with(convertView).load(itemList.get(listPosition).getAppart_image()).into(appart_image);

    }
}
