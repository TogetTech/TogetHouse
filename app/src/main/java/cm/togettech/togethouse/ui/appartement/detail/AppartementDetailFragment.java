package cm.togettech.togethouse.ui.appartement.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.AppartementModel;
import cm.togettech.togethouse.Model.ChambreModel;
import cm.togettech.togethouse.R;
import cm.togettech.togethouse.ui.chambre.detail.ChambreDetailViewModel;
import dmax.dialog.SpotsDialog;


public class AppartementDetailFragment extends Fragment {

    private static final String TAG = "AppartementDetailFragment";
    private FirebaseAuth firebaseAuth;

    private AppartementDetailViewModel appartementDetailViewModel;

    private Unbinder unbinder;
    private android.app.AlertDialog waitingDialog;

    @BindView(R.id.appart_image)
    ImageView appart_image;

    @BindView(R.id.appart_nom)
    TextView appart_nom;
    @BindView(R.id.appart_description)
    TextView appart_description;
    @BindView(R.id.appart_ville)
    TextView appart_ville;

    @BindView(R.id.appart_quartier)
    TextView appart_quartier;
    @BindView(R.id.appart_prix)
    TextView appart_prix;
    @BindView(R.id.appart_mois)
    TextView appart_mois;

    @BindView(R.id.appart_responsable)
    TextView appart_responsable;
    @BindView(R.id.appart_contact1)
    TextView appart_contact1;
    @BindView(R.id.appart_contact2)
    TextView appart_contact2;
    @BindView(R.id.appart_email)
    TextView appart_email;

    LayoutAnimationController layoutAnimationController;

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        appartementDetailViewModel =
                ViewModelProviders.of(this).get(AppartementDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chambre_detail, container, false);
        unbinder = ButterKnife.bind(this, root);
        initViews();

        appartementDetailViewModel.getMutableLiveDataAppartement().observe(this, appartementModel ->  {
            displayDetailAppartement(appartementModel);
        });


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        return root;
    }

    private void displayDetailAppartement(AppartementModel appartementModel) {
        Glide.with(getContext()).load(appartementModel.getAppart_image()).into(appart_image);
        appart_nom.setText(new StringBuilder(appartementModel.getAppart_nom()));
        appart_description.setText(new StringBuilder(appartementModel.getAppart_description()));
        appart_ville.setText(new StringBuilder(appartementModel.getAppart_ville()));
        appart_quartier.setText(new StringBuilder(appartementModel.getAppart_quartier()));
        appart_prix.setText(new StringBuilder(appartementModel.getAppart_prix()));
        appart_mois.setText(new StringBuilder(appartementModel.getAppart_mois()));
        appart_responsable.setText(new StringBuilder(appartementModel.getAppart_responsable()));
        appart_contact1.setText(new StringBuilder(appartementModel.getAppart_contact1()));
        appart_contact2.setText(new StringBuilder(appartementModel.getAppart_contact2()));
        appart_email.setText(new StringBuilder(appartementModel.getAppart_email()));



        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.selectedAppartement.getAppart_nom());
    }

    private void initViews() {
        waitingDialog = new SpotsDialog.Builder().setCancelable(false).setContext(getContext()).build();

    }
}