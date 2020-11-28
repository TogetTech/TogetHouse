package cm.togettech.togethouse.ui.maison.detail;

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
import cm.togettech.togethouse.Adapter.BestMaisonAdapter;
import cm.togettech.togethouse.Common.Common;
import cm.togettech.togethouse.Model.MaisonModel;
import cm.togettech.togethouse.R;
import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;


public class MaisonDetailFragment extends Fragment {

    private static final String TAG = "MaisonDetailFragment";
    private FirebaseAuth firebaseAuth;

    private MaisonDetailViewModel maisonDetailViewModel;

    private Unbinder unbinder;
    private android.app.AlertDialog waitingDialog;

    @BindView(R.id.maison_image)
    ImageView maison_image;

    @BindView(R.id.maison_nom)
    TextView maison_nom;
    @BindView(R.id.maison_description)
    TextView maison_description;
    @BindView(R.id.maison_ville)
    TextView maison_ville;

    @BindView(R.id.maison_quartier)
    TextView maison_quartier;
    @BindView(R.id.maison_prix)
    TextView maison_prix;
    @BindView(R.id.maison_mois)
    TextView maison_mois;

    @BindView(R.id.maison_responsable)
    TextView maison_responsable;
    @BindView(R.id.maison_contact1)
    TextView maison_contact1;
    @BindView(R.id.maison_contact2)
    TextView maison_contact2;
    @BindView(R.id.maison_email)
    TextView maison_email;

    @BindView(R.id.maison_image2)
    CircleImageView maison_image2;
    @BindView(R.id.maison_image3)
    CircleImageView maison_image3;
    @BindView(R.id.maison_image4)
    CircleImageView maison_image4;
    @BindView(R.id.maison_image5)
    CircleImageView maison_image5;


    @BindView(R.id.viewpager)
    LoopingViewPager viewPager;

    LayoutAnimationController layoutAnimationController;

    @SuppressLint("FragmentLiveDataObserve")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        maisonDetailViewModel =
                ViewModelProviders.of(this).get(MaisonDetailViewModel.class);
        View root = inflater.inflate(R.layout.fragment_maison_detail, container, false);
        unbinder = ButterKnife.bind(this, root);
        initViews();

        maisonDetailViewModel.getMutableLiveDataMaison().observe(this, maisonModel ->  {
            displayDetailMaison(maisonModel);
        });


        maisonDetailViewModel.getMaisonList().observe(this, maisonModels -> {

            //Create adapter for maison
            BestMaisonAdapter adapter = new BestMaisonAdapter(getContext(), maisonModels, true);
            viewPager.setAdapter(adapter);
            viewPager.setLayoutAnimation(layoutAnimationController);
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        return root;
    }

    private void displayDetailMaison(MaisonModel maisonModel) {
        Glide.with(getContext()).load(maisonModel.getMaison_image()).into(maison_image);
        maison_nom.setText(new StringBuilder(maisonModel.getMaison_nom()));
        maison_description.setText(new StringBuilder(maisonModel.getMaison_description()));
        maison_ville.setText(new StringBuilder(maisonModel.getMaison_ville()));
        maison_quartier.setText(new StringBuilder(maisonModel.getMaison_quartier()));
        maison_prix.setText(new StringBuilder(maisonModel.getMaison_prix()));
        maison_mois.setText(new StringBuilder(maisonModel.getMaison_mois()));
        maison_responsable.setText(new StringBuilder(maisonModel.getMaison_responsable()));
        maison_contact1.setText(new StringBuilder(maisonModel.getMaison_contact1()));
        maison_contact2.setText(new StringBuilder(maisonModel.getMaison_contact2()));
        maison_email.setText(new StringBuilder(maisonModel.getMaison_email()));

        Glide.with(getContext()).load(maisonModel.getMaison_image2()).into(maison_image2);
        Glide.with(getContext()).load(maisonModel.getMaison_image3()).into(maison_image3);
        Glide.with(getContext()).load(maisonModel.getMaison_image4()).into(maison_image4);
        Glide.with(getContext()).load(maisonModel.getMaison_image5()).into(maison_image5);


        ((AppCompatActivity)getActivity())
                .getSupportActionBar()
                .setTitle(Common.selectedMaison.getMaison_nom());
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