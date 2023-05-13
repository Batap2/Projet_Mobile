package com.example.projet_interim.Commun;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.projet_interim.Anon_Candidates.AnnonceList_Menu_Anon_Candidates;
import com.example.projet_interim.Anon_Candidates.Profile_Menu_Candidates;
import com.example.projet_interim.CurentUser;
import com.example.projet_interim.EmployeurAgence.Profil_Menu_Employeur;
import com.example.projet_interim.R;
import com.google.android.material.navigation.NavigationView;

public class StatMenu extends AppCompatActivity {

    ActionBarDrawerToggle barToggled;
    DrawerLayout drawerLayout;
    NavigationView navView;
    CurentUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.one_text_menu);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout_oneText_Menu);
        navView = (NavigationView) findViewById(R.id.navView_oneText_Menu);

        // init de la db et récupération du user courant
        user = CurentUser.getInstance();

        // Change le contenu du side menu
        switch (user.role){
            case "candidat":
                navView.getMenu().clear();
                navView.inflateMenu(R.menu.drawer_candidate_notif);
                break;
            case "employeur":
            case "agence":
                navView.getMenu().clear();
                navView.inflateMenu(R.menu.drawer_employeur_agence_notif);
                break;
            case "admin":
                break;
        }

        // Side Menu
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

        TextView text = findViewById(R.id.text_t);
        text.setMovementMethod(new ScrollingMovementMethod());

        if(user.role.equals("candidat")){
            text.setText("Rapport sur l'employabilité et les annonces d'emploi en France :\n\n" +
                    "\n" +
                    "10 000 annonces d'emploi en moyenne chaque jour dans divers secteurs\n\n" +
                    "L'Île-de-France, Auvergne-Rhône-Alpes et Occitanie ont le plus grand nombre d'offres d'emploi\n\n" +
                    "Les compétences les plus recherchées sont liées aux TI, au marketing, à la vente, à la santé et aux services à la personne\n\n" +
                    "Les développeurs informatiques, les commerciaux et les infirmiers sont les profils les plus demandés.");
        } else {
            text.setText("Rapport sur l'employabilité et les annonces d'emploi en France :\n\n" +
                    "\n" +
                    "10 000 annonces d'emploi en moyenne chaque jour dans divers secteurs\n\n" +
                    "L'Île-de-France, Auvergne-Rhône-Alpes et Occitanie ont le plus grand nombre d'offres d'emploi\n\n" +
                    "Les compétences les plus recherchées sont liées aux TI, au marketing, à la vente, à la santé et aux services à la personne\n\n" +
                    "Les développeurs informatiques, les commerciaux et les infirmiers sont les profils les plus demandés.");
        }
    }

    // Permet d'ouvrir le Side Menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(barToggled.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Change d'Activity
    void gotoMenu(int itemId){

        Intent intent = null;
        switch (itemId) {
            case R.id.drawer_profil:
                if(user.role.equals("candidat")){
                    intent = new Intent(getApplicationContext(), Profile_Menu_Candidates.class);
                }
                if(user.role.equals("employeur") || user.role.equals("agence")){
                    intent = new Intent(getApplicationContext(), Profil_Menu_Employeur.class);
                }
                if(user.role.equals("admin")){
                    // TODO : relier le menu profil admin
                    //intent = new Intent(getApplicationContext(), Profile_Menu_Candidates.class);
                }
                break;
            case R.id.drawer_annonce:
                intent = new Intent(getApplicationContext(), AnnonceList_Menu_Anon_Candidates.class);
                break;
            case R.id.drawer_cvHelp:
                intent = new Intent(getApplicationContext(), CvHelpMenu.class);
                break;
            case R.id.drawer_stat:
                intent = new Intent(getApplicationContext(), StatMenu.class);
                break;
            case R.id.drawer_disconnect:
                CurentUser.getInstance().id = null;
                CurentUser.getInstance().username = null;
                CurentUser.getInstance().role = null;
                intent = new Intent(getApplicationContext(), LoginScreen.class);
                break;
        }

        if(intent != null){
            startActivity(intent);
            finish();
        }
    }
}