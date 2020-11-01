package cm.togettech.togethouse.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import cm.togettech.togethouse.Adapter.MyAppartementAdapter;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Adapter.MyStudioAdapter;
import cm.togettech.togethouse.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    
    Unbinder unbinder;
    
    @BindView(R.id.recycler_appartement)
    RecyclerView recycler_appartement;

    @BindView(R.id.recycler_studio)
    RecyclerView recycler_studio;


    @BindView(R.id.loader)
    ProgressBar loader;
    @BindView(R.id.loader2)
    ProgressBar loader2;

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

            //Create adapter
            MyAppartementAdapter adapter = new MyAppartementAdapter(getContext(), appartementModels);
            recycler_appartement.setAdapter(adapter);
            recycler_appartement.setLayoutAnimation(layoutAnimationController);
            loader.setVisibility(View.GONE);
        });

        homeViewModel.getStudioList().observe(this, studioModels -> {

            //Create adapter
            MyStudioAdapter adapter = new MyStudioAdapter(getContext(), studioModels);
            recycler_studio.setAdapter(adapter);
            recycler_studio.setLayoutAnimation(layoutAnimationController);
            loader2.setVisibility(View.GONE);
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
