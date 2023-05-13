package com.example.projet_interim.Commun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.projet_interim.Anon_Candidates.AnnonceList_Menu_Anon_Candidates;
import com.example.projet_interim.Anon_Candidates.Profile_Menu_Candidates;
import com.example.projet_interim.CurentUser;
import com.example.projet_interim.DB;
import com.example.projet_interim.EmployeurAgence.Profil_Menu_Employeur;
import com.example.projet_interim.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class CvHelpMenu extends AppCompatActivity {

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
        text.setText("La mise en page : La présentation de votre CV est la première chose que votre employeur potentiel verra. Il est donc important que votre CV soit bien organisé et facile à lire. Utilisez une police professionnelle, telle que Times New Roman ou Arial, et assurez-vous que votre CV ne soit pas surchargé en informations. Évitez les couleurs et les polices fantaisistes qui peuvent distraire le lecteur.\n" +
                "\n" +
                "Les informations de contact : Incluez toujours vos informations de contact, telles que votre nom, adresse, numéro de téléphone et adresse e-mail. Assurez-vous que ces informations sont à jour et facilement accessibles.\n" +
                "\n" +
                "L'objectif : L'objectif de votre CV est d'attirer l'attention de votre employeur potentiel et de vous présenter comme le candidat idéal pour le poste. Votre objectif doit être clair et concis, et doit être personnalisé pour chaque poste que vous postulez.\n" +
                "\n" +
                "L'expérience professionnelle : La partie la plus importante de votre CV est votre expérience professionnelle. Vous devriez commencer par votre expérience la plus récente et travailler en arrière. Incluez votre titre, le nom de l'entreprise, la ville et l'État, les dates d'emploi et vos responsabilités principales pour chaque poste.\n" +
                "\n" +
                "La formation : La formation que vous avez suivie peut également être importante pour l'employeur potentiel. Incluez les noms des établissements d'enseignement que vous avez fréquentés, ainsi que les dates de fréquentation et les diplômes obtenus.\n" +
                "\n" +
                "Les compétences : Incluez également une liste de vos compétences pertinentes pour le poste que vous postulez. Assurez-vous de mettre en évidence les compétences clés qui vous différencient des autres candidats.\n" +
                "\n" +
                "Les références : Il est important d'avoir des références professionnelles pour soutenir votre candidature. Incluez les noms, les titres, les adresses e-mail et les numéros de téléphone de deux à trois personnes qui peuvent attester de vos compétences et de votre expérience.\n" +
                "\n" +
                "En suivant ces bonnes pratiques, vous pouvez créer un CV qui vous présente sous votre meilleur jour et vous aide à décrocher le poste que vous désirez. Bonne chance !");
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