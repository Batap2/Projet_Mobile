package com.example.projet_interim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainScreen_Anon_Candidates extends AppCompatActivity {

    ActionBarDrawerToggle barToggled;

    ListView listView;
    List<String[]> offerList = new ArrayList<>();
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_screen_anon_candidate);

        listView = (ListView)findViewById(R.id.offre_listview);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout1);
        navView = (NavigationView) findViewById(R.id.navView);

        barToggled = new ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        barToggled.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                gotoMenu(item.getItemId());
                return false;
            }
        });

        initOfferList();

        OfferAdaptator adapter = new OfferAdaptator(MainScreen_Anon_Candidates.this,offerList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(barToggled.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void initOfferList(){
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});

    }

    void gotoMenu(int itemId){

        Intent intent = null;
        switch (itemId) {
            case R.id.drawerItem1:
                //intent = new Intent(MainScreen_Anon_Candidates.this, MenuScreen_Anon_Candidates.class);
                break;
            case R.id.drawerItem2:
                Toast.makeText(getApplicationContext(), "yo tantouze, t'as cliqué là ?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawerItem3:
                Toast.makeText(getApplicationContext(), "yo tantouze, t'as cliqué sur quoi là ?", Toast.LENGTH_SHORT).show();
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}