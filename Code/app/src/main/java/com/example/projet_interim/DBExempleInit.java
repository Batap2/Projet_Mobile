package com.example.projet_interim;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class DBExempleInit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DB db = new DB(getApplicationContext(), this);

        db.autoIncr_user = 0;
        db.autoIncr_candidature = 0;
        db.autoIncr_notification = 0;
        db.autoIncr_annonce = 0;
        db.users.clear();
        db.annonces.clear();
        db.candidatures.clear();
        db.annonces_prisent.clear();
        db.notifications.clear();

        db.addUser("candidat1", "candidat");
        db.addUser("candidat2", "candidat");
        db.addUser("employeur1", "employeur");
        db.addUser("agence1", "agence");
        db.addUser("admin1", "admin");

        db.addAnnonce("2","Caissier H/F","Durant cet emploie vous aurez l\u0027odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d\u0027avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog");
        db.addAnnonce("2", "Agent de service à la clientèle H/F",      "Nous recherchons un(e) agent(e) de service à la clientèle pour rejoindre notre équipe. Les principales responsabilités du poste incluent: répondre aux appels et aux courriels des clients, gérer les plaintes, traiter les commandes, assurer le suivi des livraisons et fournir des informations sur nos produits et services. Le candidat idéal doit avoir un minimum de deux ans d'expérience dans un rôle similaire et posséder de bonnes compétences en communication.\n\nNous offrons un salaire compétitif et des avantages sociaux, mais le travail peut être stressant et exigeant, avec des horaires flexibles et des périodes de pointe où la charge de travail est très élevée. Si vous cherchez un travail facile et routinier, ce n'est pas pour vous. Nous recherchons quelqu'un de motivé et capable de gérer des situations difficiles avec calme et professionnalisme.\n\nSi vous êtes intéressé(e) par ce poste, veuillez soumettre votre CV et une lettre de motivation décrivant pourquoi vous êtes la bonne personne pour ce travail. Seuls les candidats sélectionnés seront contactés.",
                "SouperMarket\n14 rue de la cafetière, 12345 McDog"
        );
        db.addAnnonce("2","Technicien de maintenance H/F",      "Nous cherchons un(e) technicien(ne) de maintenance pour rejoindre notre équipe. Le poste consiste à assurer la maintenance préventive et corrective de nos équipements, à diagnostiquer et résoudre les problèmes techniques et à effectuer des réparations si nécessaire. Le candidat doit posséder une formation en maintenance industrielle et être capable de travailler en équipe.\n\nLe travail peut être physique et exigeant, avec des horaires irréguliers et des déplacements fréquents. Si vous n'êtes pas prêt(e) à travailler dur et à vous adapter à des situations changeantes, ce poste n'est pas pour vous.\n\nSi vous êtes intéressé(e) par ce poste, veuillez envoyer votre CV et une lettre de motivation expliquant pourquoi vous êtes qualifié(e) pour ce rôle. Nous ne contacterons que les candidats sélectionnés.",
                "SouperMarket\n14 rue de la cafetière, 12345 McDog"
        );
        db.addAnnonce("2","Stagiaire en marketing",      "Nous cherchons un(e) stagiaire en marketing pour rejoindre notre équipe. Le poste implique d'aider à la création de contenu marketing, de gérer les réseaux sociaux et d'assister les membres de l'équipe dans leurs tâches quotidiennes. Le candidat doit être créatif(ve) et avoir une bonne connaissance des médias sociaux.\n\nLe poste est non-rémunéré et implique des horaires flexibles. Si vous cherchez un poste rémunéré, ce n'est pas pour vous. Nous recherchons un(e) stagiaire motivé(e) et capable d'apprendre rapidement.\n\nSi vous êtes intéressé(e) par ce poste, veuillez envoyer votre CV et une lettre de motivation expliquant pourquoi vous êtes intéressé(e) par ce rôle. Nous ne contacterons que les candidats sélectionnés.",
                "SouperMarket\n14 rue de la cafetière, 12345 McDog"
        );
        db.addAnnonce("2","Agent de nettoyage H/F",      "Nous recherchons un(e) agent(e) de nettoyage pour rejoindre notre équipe. Les tâches incluent le nettoyage des bureaux, des toilettes et des espaces communs. Le candidat doit être capable de travailler de manière autonome et de suivre des procédures de nettoyage strictes.\n\nLe travail est physique et répétitif, avec des horaires tôt le matin ou tard le soir. Si vous cherchez un travail stimulant et varié, ce n'est pas pour vous. Nous recherchons quelqu'un de fiable et capable de travailler efficacement dans un environnement de travail exigeant.\n\nSi vous êtes intéressé(e) par ce poste, veuillez envoyer votre CV et une lettre de motivation décrivant votre expérience en matière de nettoyage. Nous ne contacterons que les candidats sélectionnés.",
                "SouperMarket\n14 rue de la cafetière, 12345 McDog"
        );
        db.addAnnonce("2","Assistant administratif H/F",      "Nous cherchons un(e) assistant(e) administratif pour rejoindre notre équipe. Les tâches comprennent la gestion des appels téléphoniques, la planification de rendez-vous, la tenue de registres et l'assistance à l'équipe dans les tâches administratives quotidiennes. Le candidat doit être organisé(e) et avoir une excellente maîtrise de la suite Microsoft Office.",
                "SouperMarket\n14 rue de la cafetière, 12345 McDog"
        );
        db.addAnnonce("2","Caissier(ère)",      "Nous recherchons un(e) caissier(ère) pour rejoindre notre équipe. Les principales responsabilités incluent l'encaissement des achats des clients, la gestion des retours et des échanges et l'assistance à l'équipe dans les tâches de service à la clientèle. Le candidat doit être capable de travailler de manière efficace sous pression.\n\nLe poste est à temps partiel avec des horaires variables, y compris les soirs et les week-ends. Le salaire est compétitif, mais le travail peut être stressant et exigeant, avec des clients difficiles et des périodes de pointe très chargées.\n\nSi vous êtes intéressé(e) par ce poste, veuillez envoyer votre CV et une lettre de motivation décrivant votre expérience en matière de service à la clientèle. Nous ne contacterons que les candidats sélectionnés.",
                "SouperMarket\n14 rue de la cafetière, 12345 McDog"
        );

        db.candidateTo("0", "0");
        db.candidateTo("0", "1");
        db.candidateTo("0", "2");
        db.candidateTo("0", "3");
        db.candidateTo("0", "4");

        db.candidateTo("1", "0");
        db.candidateTo("1", "1");

        db.acceptCandidature("0");
    }
}