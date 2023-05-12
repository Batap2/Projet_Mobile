package com.example.projet_interim.Anon_Candidates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projet_interim.Commun.NotifMenu;
import com.example.projet_interim.Commun.RegisterOrModifyInfoMenu;
import com.example.projet_interim.CurentUser;
import com.example.projet_interim.DB;
import com.example.projet_interim.OfferAdaptator;
import com.example.projet_interim.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Profile_Menu_Candidates extends AppCompatActivity {

    CurentUser user;

    ActionBarDrawerToggle barToggled;

    ListView listView_offres_attente;
    ListView listview_offres_prisent;
    ArrayList<ArrayList<String>> candidature_attente = new ArrayList<>();
    ArrayList<ArrayList<String>> candidature_prisent = new ArrayList<>();
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profil_candidate);

        listView_offres_attente = (ListView)findViewById(R.id.offre_listview);
        listview_offres_prisent = (ListView)findViewById(R.id.offres_prisent_listview);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout_profil_candidate);
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

        user = CurentUser.getInstance();
        DB db = new DB(getApplicationContext(), this);

        candidature_attente = db.getCandidatureAnnonceForUserId(user.id);
        candidature_prisent = db.getAnnonces_prisentForUserId(user.id);

        OfferAdaptator adapter1 = new OfferAdaptator(getApplicationContext(),candidature_attente);
        listView_offres_attente.setAdapter(adapter1);
        OfferAdaptator adapter2 = new OfferAdaptator(getApplicationContext(),candidature_prisent);
        listview_offres_prisent.setAdapter(adapter2);

        Log.e("c_attente", candidature_attente.toString());
        Log.e("c_prisent", candidature_prisent.toString());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(barToggled.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void gotoMenu(int itemId){

        Intent intent = null;
        switch (itemId) {
            case R.id.drawer_modifyInfo:
                intent = new Intent(getApplicationContext(), RegisterOrModifyInfoMenu.class);
                intent.putExtra("role", user.role);
                break;
            case R.id.drawer_annonce:
                intent = new Intent(getApplicationContext(), AnnonceList_Menu_Anon_Candidates.class);
                break;
            case R.id.drawer_msg:
                intent = new Intent(getApplicationContext(), NotifMenu.class);
                break;
            case R.id.drawer_cvHelp:
                Toast.makeText(getApplicationContext(), "yo tantouze, t'as cliqué sur quoi là ?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_stat:
                Toast.makeText(getApplicationContext(), "yo tantouze, t'as cliqué là ?", Toast.LENGTH_SHORT).show();
                break;
            case R.id.drawer_disconnect:
                Toast.makeText(getApplicationContext(), "yo tantouze, t'as cliqué là ?", Toast.LENGTH_SHORT).show();
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}