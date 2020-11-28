package cm.togettech.togethouse.ui.chambre;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
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
import cm.togettech.togethouse.Adapter.ChambreAdapter;
import cm.togettech.togethouse.R;


public class ChambreFragment extends Fragment {

    private ChambreViewModel chambreViewModel;

    Unbinder unbinder;

    @BindView(R.id.recycler_chambre)
    RecyclerView recycler_chambre;

    LayoutAnimationController layoutAnimationController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        chambreViewModel =
                ViewModelProviders.of(this).get(ChambreViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chambre, container, false);
        unbinder = ButterKnife.bind(this, root);

        initViews();

        chambreViewModel.getChambreList().observe(this, chambreModels -> {

            //Create adapter for chambre
            ChambreAdapter adapter = new ChambreAdapter(getContext(), chambreModels);
            recycler_chambre.setAdapter(adapter);
            recycler_chambre.setLayoutAnimation(layoutAnimationController);


        });

        return root;
    }

    private void initViews() {
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
        recycler_chambre.setHasFixedSize(true);
        recycler_chambre.setLayoutManager(new GridLayoutManager(getContext(), 2));



    }
}