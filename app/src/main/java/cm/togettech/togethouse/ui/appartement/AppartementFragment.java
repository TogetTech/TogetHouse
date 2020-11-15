package cm.togettech.togethouse.ui.appartement;

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
import cm.togettech.togethouse.R;


public class AppartementFragment extends Fragment {

    private AppartementViewModel appartementViewModel;

    Unbinder unbinder;

    @BindView(R.id.recycler_appartement)
    RecyclerView recycler_appartement;

    LayoutAnimationController layoutAnimationController;


    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        appartementViewModel =
                ViewModelProviders.of(this).get(AppartementViewModel.class);
        View root = inflater.inflate(R.layout.fragment_appartement, container, false);
        unbinder = ButterKnife.bind(this, root);

        initViews();

        appartementViewModel.getAppartementList().observe(this, appartementModels ->  {
            AppartementAdapter adapter = new AppartementAdapter(getContext(), appartementModels);
            recycler_appartement.setAdapter(adapter);
            recycler_appartement.setLayoutAnimation(layoutAnimationController);
        });
        return root;
    }

    private void initViews() {
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
        recycler_appartement.setHasFixedSize(true);
        recycler_appartement.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }
}