package cm.togettech.togethouse.ui.terrain;

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
import cm.togettech.togethouse.Adapter.TerrainAdapter;
import cm.togettech.togethouse.R;


public class TerrainFragment extends Fragment {

    private TerrainViewModel terrainViewModel;

    Unbinder unbinder;

    @BindView(R.id.recycler_terrain)
    RecyclerView recycler_terrain;

    LayoutAnimationController layoutAnimationController;


    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        terrainViewModel =
                ViewModelProviders.of(this).get(TerrainViewModel.class);
        View root = inflater.inflate(R.layout.fragment_terrain, container, false);
        unbinder = ButterKnife.bind(this, root);

        initViews();

        terrainViewModel.getTerrainList().observe(this, terrainModels ->  {
            TerrainAdapter terrainAdapter = new TerrainAdapter(getContext(), terrainModels);
            recycler_terrain.setAdapter(terrainAdapter);
            recycler_terrain.setLayoutAnimation(layoutAnimationController);
        });
        return root;
    }

    private void initViews() {
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);
        recycler_terrain.setHasFixedSize(true);
        recycler_terrain.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
}