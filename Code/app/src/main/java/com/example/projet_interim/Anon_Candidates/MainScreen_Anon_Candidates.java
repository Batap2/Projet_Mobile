package com.example.projet_interim.Anon_Candidates;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projet_interim.DB;
import com.example.projet_interim.OfferAdaptator;
import com.example.projet_interim.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainScreen_Anon_Candidates extends AppCompatActivity {

    ActionBarDrawerToggle barToggled;

    ListView listView;
    ArrayList<ArrayList<String>> offerList;
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        DB db = new DB(getApplicationContext(), this);
        offerList = db.annonces;

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

    void gotoMenu(int itemId){

        Intent intent = null;
        switch (itemId) {
            case R.id.drawer_profil:
                intent = new Intent(getApplicationContext(), MenuScreen_Anon_Candidates.class);
                break;
            case R.id.drawer_msg:
                Toast.makeText(getApplicationContext(), "yo tantouze, t'as cliqué là ?", Toast.LENGTH_SHORT).show();
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