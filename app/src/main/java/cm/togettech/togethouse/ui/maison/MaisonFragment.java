package cm.togettech.togethouse.ui.maison;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Adapter.AppartementAdapter;
import cm.togettech.togethouse.Adapter.MaisonAdapter;
import cm.togettech.togethouse.R;
import cm.togettech.togethouse.ui.appartement.AppartementViewModel;


public class MaisonFragment extends Fragment {

    private MaisonViewModel maisonViewModel;

    Unbinder unbinder;

    @BindView(R.id.recycler_maison)
    RecyclerView recycler_maison;

    LayoutAnimationController layoutAnimationController;

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        maisonViewModel =
                ViewModelProviders.of(this).get(MaisonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_maison, container, false);
        unbinder = ButterKnife.bind(this, root);

        initViews();

        maisonViewModel.getMaisonList().observe(this, maisonModels ->  {
            MaisonAdapter adapter = new MaisonAdapter(getContext(), maisonModels);
            recycler_maison.setAdapter(adapter);
            recycler_maison.setLayoutAnimation(layoutAnimationController);
        });
        return root;

    }

    private void initViews() {
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
        recycler_maison.setHasFixedSize(true);
        recycler_maison.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }
}