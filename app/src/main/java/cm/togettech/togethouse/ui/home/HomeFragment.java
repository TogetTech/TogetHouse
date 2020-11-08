package cm.togettech.togethouse.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import cm.togettech.togethouse.Adapter.AppartementAdapter;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Adapter.ChambreAdapter;
import cm.togettech.togethouse.Adapter.StudioAdapter;
import cm.togettech.togethouse.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    
    Unbinder unbinder;
    
    @BindView(R.id.recycler_appartement)
    RecyclerView recycler_appartement;

    @BindView(R.id.recycler_studio)
    RecyclerView recycler_studio;

    @BindView(R.id.recycler_chambre)
    RecyclerView recycler_chambre;


    LayoutAnimationController layoutAnimationController;


    @SuppressLint("FragmentLiveDataObserve")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, root);
        init();

        homeViewModel.getAppartementList().observe(this, appartementModels -> {

            //Create adapter for appartement
            AppartementAdapter adapter = new AppartementAdapter(getContext(), appartementModels);
            recycler_appartement.setAdapter(adapter);
            recycler_appartement.setLayoutAnimation(layoutAnimationController);
        });

        homeViewModel.getStudioList().observe(this, studioModels -> {

            //Create adapter for studio
            StudioAdapter adapter = new StudioAdapter(getContext(), studioModels);
            recycler_studio.setAdapter(adapter);
            recycler_studio.setLayoutAnimation(layoutAnimationController);
        });

        homeViewModel.getChambreList().observe(this, chambreModels -> {

            //Create adapter for chambre
            ChambreAdapter adapter = new ChambreAdapter(getContext(), chambreModels);
            recycler_chambre.setAdapter(adapter);
            recycler_chambre.setLayoutAnimation(layoutAnimationController);
        });

        return root;
    }

    private void init() {
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);

        //Load recyclerView Appartement
        recycler_appartement.setHasFixedSize(true);
        recycler_appartement.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //Load recyclerView Studio
        recycler_studio.setHasFixedSize(true);
        recycler_studio.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        //Load recyclerView Studio
        recycler_chambre.setHasFixedSize(true);
        recycler_chambre.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }
    @Override
    public void onResume(){
        super.onResume();
        //viewPager.resumeAutoScroll();

    }
    @Override
    public void onPause(){
        //viewPager.pauseAutoScroll();
        super.onPause();

    }

}
