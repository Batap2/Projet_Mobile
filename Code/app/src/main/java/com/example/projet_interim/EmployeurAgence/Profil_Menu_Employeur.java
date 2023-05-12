package com.example.projet_interim.EmployeurAgence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet_interim.Anon_Candidates.ApplyTo_Menu_Candidates;
import com.example.projet_interim.CandidatureAdaptator;
import com.example.projet_interim.Commun.NotifMenu;
import com.example.projet_interim.Commun.RegisterOrModifyInfoMenu;
import com.example.projet_interim.Commun.WriteNotifMenu;
import com.example.projet_interim.CurentUser;
import com.example.projet_interim.DB;
import com.example.projet_interim.NotifAdaptator;
import com.example.projet_interim.OfferAdaptator;
import com.example.projet_interim.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Profil_Menu_Employeur extends AppCompatActivity {

    CurentUser user;
    DB db;
    ActionBarDrawerToggle barToggled;

    ListView listView_offres;
    ListView listView_candidatures;
    ArrayList<ArrayList<String>> candidaturesList = new ArrayList<>();
    ArrayList<ArrayList<String>> displayedCandidaturesList = new ArrayList<>();
    ArrayList<ArrayList<String>> offresList = new ArrayList<>();
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profil_employeur_agence);

        listView_offres = (ListView)findViewById(R.id.offres_listview_EmployeurAgence);
        listView_candidatures = (ListView)findViewById(R.id.candidatures_listview_EmployeurAgence);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout_profil_employeurAgence);
        navView = (NavigationView) findViewById(R.id.navView);

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

        // Init DB & User
        user = CurentUser.getInstance();
        db = new DB(getApplicationContext(), this);

        // init les listView
        refreshListView();

        // Capture l'item cliqué dans la "Mes offres"
        listView_offres.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> offre = (ArrayList<String>)listView_offres.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"offerID : " + offre.get(0),Toast.LENGTH_SHORT).show();

                showDialogOffre(offre.get(0), offre.get(2));
            }
        });

        // Capture l'item cliqué dans la "Candidatures"
        listView_candidatures.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> candid = (ArrayList<String>)listView_candidatures.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"offerID : " + candid.get(0),Toast.LENGTH_SHORT).show();

                showDialogCandidature(db.getInfoForCandidatureID(candid.get(0)), candid.get(0), candid.get(2), user.id);
            }
        });

        Log.e("Candidatures :", candidaturesList.toString());
    }

    public void addOffer(View v){
        Intent intent = new Intent(getApplicationContext(), AddAnnonceMenu.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                refreshListView();
            }
        }
    }

    private void showDialogOffre(String offerId, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(Profil_Menu_Employeur.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage("Voulez vous supprimer cette offre ?");
        builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.removeAnnonceFromId(offerId);
                        refreshListView();
                    }
                });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        AlertDialog d = builder.create();

        d.show();
    }

    // Info = {user_ID, username, role, mail, nom, prenom, date de naissance, nationalité, CV, LM}
    private void showDialogCandidature(ArrayList<String> userInfo, String candidatureID, String annonceTitle, String employeurId){
        AlertDialog.Builder builder = new AlertDialog.Builder(Profil_Menu_Employeur.this);
        builder.setCancelable(true);
        builder.setTitle(annonceTitle);
        builder.setMessage(userInfo.get(4) + " " + userInfo.get(5) + "\n\n" +
                            "Né(e) le : " + userInfo.get(6) + "\n" +
                            "Nationalité : " + userInfo.get(7) + "\n" +
                            "Mail : " + userInfo.get(3) + "\n\n" +
                            "Souhaite travailler pour l'annonce : \n" +
                            annonceTitle + "\n\n" +
                            "CV :\n\n"+
                            userInfo.get(8)+"\n\n"+
                            "Lettre de motivation :\n\n" +
                            userInfo.get(9));
        builder.setPositiveButton("Accepter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                db.acceptCandidature(candidatureID);
                refreshListView();
            }
        });
        builder.setNegativeButton("Refuser", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                db.addNotif(employeurId, userInfo.get(0), "Refus de candidature", "L'offre " + annonceTitle + " vous a été refusé");
                db.removeCandidatureFromId(candidatureID);
                refreshListView();
            }
        });
        builder.setNeutralButton("Envoyer un message au candidat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        // Create the AlertDialog object and return it
        AlertDialog d = builder.create();

        d.show();
    }

    // Actualise les listViews
    private void refreshListView(){
        db = new DB(getApplicationContext(), Profil_Menu_Employeur.this);

        offresList = db.getAnnoncesFromCreatorId(user.id);
        OfferAdaptator adapter1 = new OfferAdaptator(getApplicationContext(), offresList);
        listView_offres.setAdapter(adapter1);

        candidaturesList = db.getCandidaturesIntendedForUserID(user.id);

        // construction de la list des candidature a afficher
        displayedCandidaturesList.clear();
        for(ArrayList<String> cand : candidaturesList){
            ArrayList<String> displayedCand = new ArrayList<>();
            displayedCand.add(cand.get(0));
            displayedCand.add(db.getUserNameFromID(cand.get(1)));
            displayedCand.add(db.getAnnonceTitleFromId(cand.get(2)));
            displayedCandidaturesList.add(displayedCand);
        }

        CandidatureAdaptator adapter2 = new CandidatureAdaptator(getApplicationContext(), displayedCandidaturesList);
        listView_candidatures.setAdapter(adapter2);
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
                //intent = new Intent(getApplicationContext(), AnnonceList_Menu_Anon_Candidates.class);
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