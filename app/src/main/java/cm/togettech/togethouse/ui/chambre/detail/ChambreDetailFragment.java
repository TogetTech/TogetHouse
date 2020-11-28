package cm.togettech.togethouse.ui.chambre.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.asksira.loopingviewpager.LoopingViewPager;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Adapter.BestChambreAdapter;
import cm.togettech.togethouse.Adapter.BestTerrainAdapter;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.R;
import dmax.dialog.SpotsDialog;


public class ChambreDetailFragment extends Fragment {

    private static final String TAG = "ChambreDetailFragment";
    private FirebaseAuth firebaseAuth;

    private ChambreDetailViewModel chambreDetailViewModel;

    private Unbinder unbinder;
    private android.app.AlertDialog waitingDialog;

    @BindView(R.id.chambre_image)
    ImageView chambre_image;

    @BindView(R.id.chambre_nom)
    TextView chambre_nom;
    @BindView(R.id.chambre_description)
    TextView chambre_description;
    @BindView(R.id.chambre_ville)
    TextView chambre_ville;

    @BindView(R.id.chambre_quartier)
    TextView chambre_quartier;
    @BindView(R.id.chambre_prix)
    TextView chambre_prix;
    @BindView(R.id.chambre_mois)
    TextView chambre_mois;

    @BindView(R.id.chambre_responsable)
    TextView chambre_responsable;
    @BindView(R.id.chambre_contact1)
    TextView chambre_contact1;
    @BindView(R.id.chambre_contact2)
    TextView chambre_contact2;
    @BindView(R.id.chambre_email)
    TextView chambre_email;

    @BindView(R.id.viewpager)
    LoopingViewPager viewPager;

    LayoutAnimationController layoutAnimationController;


    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        chambreDetailViewModel =
                ViewModelProviders.of(this).get(ChambreDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chambre_detail, container, false);
        unbinder = ButterKnife.bind(this, root);
        initViews();

        chambreDetailViewModel.getMutableLiveDataChambre().observe(this, chambreModel ->  {
            displayDetailChambre(chambreModel);
        });

        chambreDetailViewModel.getChambreList().observe(this, chambreModels -> {

            //Create adapter for other terrain
            BestChambreAdapter bestChambreAdapter = new BestChambreAdapter(getContext(), chambreModels, true);
            viewPager.setAdapter(bestChambreAdapter);
            viewPager.setLayoutAnimation(layoutAnimationController);
        });


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        return root;
    }

    private void displayDetailChambre(ChambreModel chambreModel) {
        Glide.with(getContext()).load(chambreModel.getChambre_image()).into(chambre_image);
        chambre_nom.setText(new StringBuilder(chambreModel.getChambre_nom()));
        chambre_description.setText(new StringBuilder(chambreModel.getChambre_description()));
        chambre_ville.setText(new StringBuilder(chambreModel.getChambre_ville()));
        chambre_quartier.setText(new StringBuilder(chambreModel.getChambre_quartier()));
        chambre_prix.setText(new StringBuilder(chambreModel.getChambre_prix()));
        chambre_mois.setText(new StringBuilder(chambreModel.getChambre_mois()));
        chambre_responsable.setText(new StringBuilder(chambreModel.getChambre_responsable()));
        chambre_contact1.setText(new StringBuilder(chambreModel.getChambre_contact1()));
        chambre_contact2.setText(new StringBuilder(chambreModel.getChambre_contact2()));
        chambre_email.setText(new StringBuilder(chambreModel.getChambre_email()));



        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.selectedChambre.getChambre_nom());
    }

    private void initViews() {
        waitingDialog = new SpotsDialog.Builder().setCancelable(false).setContext(getContext()).build();


    }
}