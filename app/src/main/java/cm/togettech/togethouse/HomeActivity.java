package cm.togettech.togethouse;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import cm.togettech.togethouse.Database.CartDataSource;
import cm.togettech.togethouse.Database.CartDatabase;
import cm.togettech.togethouse.Database.LocalCartDataSource;
import cm.togettech.togethouse.EventBus.AppartementDetailClick;
import cm.togettech.togethouse.EventBus.ChambreDetailClick;
import cm.togettech.togethouse.EventBus.ChambreItemClick;
import cm.togettech.togethouse.EventBus.AppartementItemClick;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import cm.togettech.togethouse.EventBus.MaisonDetailClick;
import cm.togettech.togethouse.EventBus.MaisonItemClick;
import cm.togettech.togethouse.EventBus.TerrainDetailClick;
import cm.togettech.togethouse.EventBus.TerrainItemClick;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private NavController navController;

    private CartDataSource cartDataSource;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onResume(){
        super.onResume();
        //counterCartItem();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        cartDataSource = new LocalCartDataSource(CartDatabase.getInstance(this).cartDAO());


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_chambre, R.id.nav_chambre_detail,
                R.id.nav_studio, R.id.nav_appartement, R.id.nav_appartement_detail,
                R.id.nav_maison, R.id.nav_maison_detail, R.id.nav_terrain, R.id.nav_terrain_detail)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        drawer.closeDrawers();
        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                navController.navigate(R.id.nav_home);
                break;
            case R.id.nav_chambre:
                navController.navigate(R.id.nav_chambre);
                break;
            case R.id.nav_studio:
                navController.navigate(R.id.nav_studio);
                break;
            case R.id.nav_appartement:
                navController.navigate(R.id.nav_appartement);
                break;
            case R.id.nav_maison:
                navController.navigate(R.id.nav_maison);
                break;
            case R.id.nav_terrain:
                navController.navigate(R.id.nav_terrain);
                break;

        }
        return true;
    }

    //EventBus

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode =  ThreadMode.MAIN)
    public void onChambreItemSelected(ChambreItemClick event){
        if (event.isSuccess()){
            navController.navigate(R.id.nav_chambre);
        }
    }

    @Subscribe(sticky = true, threadMode =  ThreadMode.MAIN)
    public void onChambreDetailClick(ChambreDetailClick event){
        if (event.isSuccess()){
            navController.navigate(R.id.nav_chambre_detail);
        }
    }

    @Subscribe(sticky = true, threadMode =  ThreadMode.MAIN)
    public void onAppartementItemClick(AppartementItemClick event){
        if (event.isSuccess()){
            navController.navigate(R.id.nav_appartement);
        }
    }


    @Subscribe(sticky = true, threadMode =  ThreadMode.MAIN)
    public void onMaisonDetailClick(MaisonDetailClick event){
        if (event.isSuccess()){
            navController.navigate(R.id.nav_maison_detail);
        }
    }


    @Subscribe(sticky = true, threadMode =  ThreadMode.MAIN)
    public void onMaisonItemClick(MaisonItemClick event){
        if (event.isSuccess()){
            navController.navigate(R.id.nav_maison);
        }
    }


    @Subscribe(sticky = true, threadMode =  ThreadMode.MAIN)
    public void onTerrainDetailClick(TerrainDetailClick event){
        if (event.isSuccess()){
            navController.navigate(R.id.nav_terrain_detail);
        }
    }


    @Subscribe(sticky = true, threadMode =  ThreadMode.MAIN)
    public void onTerrainItemClick(TerrainItemClick event){
        if (event.isSuccess()){
            navController.navigate(R.id.nav_terrain);
        }
    }

    //**********************************************************************************************
}
