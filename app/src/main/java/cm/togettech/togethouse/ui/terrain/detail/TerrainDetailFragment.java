package cm.togettech.togethouse.ui.terrain.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Adapter.BestTerrainAdapter;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.MaisonModel;
import cm.togettech.togethouse.Model.TerrainModel;
import cm.togettech.togethouse.R;
import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;


public class TerrainDetailFragment extends Fragment {

    private static final String TAG = "TerrainDetailFragment";
    private FirebaseAuth firebaseAuth;

    private TerrainDetailViewModel terrainDetailViewModel;

    private Unbinder unbinder;
    private android.app.AlertDialog waitingDialog;

    @BindView(R.id.terrain_image)
    ImageView terrain_image;

    @BindView(R.id.terrain_nom)
    TextView terrain_nom;
    @BindView(R.id.terrain_description)
    TextView terrain_description;
    @BindView(R.id.terrain_ville)
    TextView terrain_ville;

    @BindView(R.id.terrain_quartier)
    TextView terrain_quartier;
    @BindView(R.id.terrain_prix)
    TextView terrain_prix;
    @BindView(R.id.terrain_mois)
    TextView terrain_mois;

    @BindView(R.id.terrain_responsable)
    TextView terrain_responsable;
    @BindView(R.id.terrain_contact1)
    TextView terrain_contact1;
    @BindView(R.id.terrain_contact2)
    TextView terrain_contact2;
    @BindView(R.id.terrain_email)
    TextView terrain_email;

    @BindView(R.id.terrain_image2)
    CircleImageView terrain_image2;
    @BindView(R.id.terrain_image3)
    CircleImageView terrain_image3;
    @BindView(R.id.terrain_image4)
    CircleImageView terrain_image4;
    @BindView(R.id.terrain_image5)
    CircleImageView terrain_image5;


    @BindView(R.id.viewpager)
    LoopingViewPager viewPager;

    LayoutAnimationController layoutAnimationController;

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        terrainDetailViewModel =
                ViewModelProviders.of(this).get(TerrainDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_terrain_detail, container, false);
        unbinder = ButterKnife.bind(this, root);
        initViews();

        terrainDetailViewModel.getMutableLiveDataTerrain().observe(this, terrainModel ->  {
            displayDetailTerrain(terrainModel);
        });

        terrainDetailViewModel.getTerrainList().observe(this, terrainModels -> {

            //Create adapter for other terrain
            BestTerrainAdapter bestTerrainAdapter = new BestTerrainAdapter(getContext(), terrainModels, true);
            viewPager.setAdapter(bestTerrainAdapter);
            viewPager.setLayoutAnimation(layoutAnimationController);
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        return root;
    }

    private void displayDetailTerrain(TerrainModel terrainModel) {
        Glide.with(getContext()).load(terrainModel.getTerrain_image()).into(terrain_image);
        terrain_nom.setText(new StringBuilder(terrainModel.getTerrain_nom()));
        terrain_description.setText(new StringBuilder(terrainModel.getTerrain_description()));
        terrain_ville.setText(new StringBuilder(terrainModel.getTerrain_ville()));
        terrain_quartier.setText(new StringBuilder(terrainModel.getTerrain_quartier()));
        terrain_prix.setText(new StringBuilder(terrainModel.getTerrain_prix()));
        terrain_mois.setText(new StringBuilder(terrainModel.getTerrain_mois()));
        terrain_responsable.setText(new StringBuilder(terrainModel.getTerrain_responsable()));
        terrain_contact1.setText(new StringBuilder(terrainModel.getTerrain_contact1()));
        terrain_contact2.setText(new StringBuilder(terrainModel.getTerrain_contact2()));
        terrain_email.setText(new StringBuilder(terrainModel.getTerrain_email()));

        Glide.with(getContext()).load(terrainModel.getTerrain_image2()).into(terrain_image2);
        Glide.with(getContext()).load(terrainModel.getTerrain_image3()).into(terrain_image3);
        Glide.with(getContext()).load(terrainModel.getTerrain_image4()).into(terrain_image4);
        Glide.with(getContext()).load(terrainModel.getTerrain_image5()).into(terrain_image5);


        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.selectedTerrain.getTerrain_nom());
    }

    private void initViews() {
        waitingDialog = new SpotsDialog.Builder().setCancelable(false).setContext(getContext()).build();
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_item_from_left);

    }

    @Override
    public void onResume(){
        super.onResume();
        viewPager.resumeAutoScroll();

    }
    @Override
    public void onPause(){
        viewPager.pauseAutoScroll();
        super.onPause();

    }
}