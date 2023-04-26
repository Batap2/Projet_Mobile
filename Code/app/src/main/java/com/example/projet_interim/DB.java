package com.example.projet_interim;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DB {

    // users : id, username, role : candidat, employeur, agence, admin
    // annonces : id, creator_user_id, titre, description, coordonnées
    // annonces_prisent : id, creator_user_id, candidatId, titre, description, coordonnées
    // notifications : id, user_id, titre, description
    // candidatures : id, candidat_id, annonce_id

    public ArrayList<ArrayList<String>> candidatures = new ArrayList<>();
    public ArrayList<ArrayList<String>> notifications = new ArrayList<>();
    public ArrayList<ArrayList<String>> annonces = new ArrayList<>();
    public ArrayList<ArrayList<String>> annonces_prisent = new ArrayList<>();
    public ArrayList<ArrayList<String>> users = new ArrayList<>();

    // ID auto incrémentiel
    int autoIncr_user = 0;
    int autoIncr_annonce = 0;
    int autoIncr_notification = 0;
    int autoIncr_candidature = 0;

    private transient Context context;

    public DB(Context context, Activity activity){
        this.context = context;
        readFromFile(activity);
    }

    // Lis le fichier DB.json dans /data/data/com.example.projet_interim/files et convertis le fichier json en objet DB, l'initialise
    public void readFromFile(Activity activity){

        // Si le fichier n'existe pas, le créé
        String file_name = activity.getFilesDir() + "/DB.json";
        File t = new File(file_name);
        if(t.exists()){
            try {

                InputStream inputStream =  context.openFileInput("DB.json");

                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                String text = new String(buffer);

                Gson gson = new Gson();
                DB newDB = gson.fromJson(text, DB.class);

                if(newDB != null){
                    this.autoIncr_annonce = newDB.autoIncr_annonce;
                    this.autoIncr_candidature = newDB.autoIncr_candidature;
                    this.autoIncr_notification = newDB.autoIncr_notification;
                    this.autoIncr_user = newDB.autoIncr_user;

                    this.users = newDB.users;
                    this.annonces = newDB.annonces;
                    this.annonces_prisent = newDB.annonces_prisent;
                    this.notifications = newDB.notifications;
                    this.candidatures = newDB.candidatures;
                }

            } catch (IOException e) {
                System.out.println("Probleme lecture");
                e.printStackTrace();
            }
        }
    }

    // Convertis l'objet DB en fichier json et l'écris dans /data/data/com.example.projet_interim/files/DB.json
    public void writeToFile(){
        try {
            FileOutputStream fOut = context.openFileOutput("DB.json",0);
            Gson gson = new Gson();
            String json = gson.toJson(this);
            fOut.write(json.getBytes());
            fOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(String username, String role){
        ArrayList<String> user = new ArrayList<>();
        user.add(String.valueOf(autoIncr_user));
        user.add(username);
        user.add(role);

        users.add(user);

        autoIncr_user = autoIncr_user + 1;

        writeToFile();
    }

    public ArrayList<String> getUserByName(String username){
        for(ArrayList<String> user : users){
            if(user.get(1).equals(username)){
                return user;
            }
        }
        return null;
    }

    public String getUserId(String username){
        for(ArrayList<String> user : users){
            if(user.get(1).equals(username)){
                return user.get(0);
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getAnnoncesFromCreatorId(String id){
        ArrayList<ArrayList<String>> c = new ArrayList<>();

        for(ArrayList<String> annonce : annonces){
            if(annonce.get(1).equals(id)){
                c.add(annonce);
            }
        }
        return c;
    }
    public ArrayList<String> getAnnonceFromId(String id){
        for(ArrayList<String> annonce : annonces){
            if(annonce.get(0).equals(id)){
                return annonce;
            }
        }
        return null;
    }

    public ArrayList<ArrayList<String>> getCandidaturesFromCreatorId(String id){
        ArrayList<ArrayList<String>> c = new ArrayList<>();

        for(ArrayList<String> candidature : candidatures){
            if(candidature.get(1).equals(id)){
                c.add(candidature);
            }
        }
        return c;
    }

    public void addAnnonce(String authorId, String title, String description, String coord){
        ArrayList<String> annonce = new ArrayList<>();
        annonce.add(String.valueOf(autoIncr_annonce));
        annonce.add(authorId);
        annonce.add(title);
        annonce.add(description);
        annonce.add(coord);
        annonces.add(annonce);

        autoIncr_annonce = autoIncr_annonce + 1;
        writeToFile();
    }

    public void removeAnnonceFromId(String id){

        ArrayList<ArrayList<String>> newAnnonces = new ArrayList<>();
        for(int i = 0; i < annonces.size(); i++){
            if(! annonces.get(i).get(0).equals(id)){
                newAnnonces.add(annonces.get(i));
            }
        }
        annonces = newAnnonces;

        ArrayList<ArrayList<String>> newCandidatures = new ArrayList<>();

        for(int i = 0; i < candidatures.size(); i++){
            if(! candidatures.get(i).get(2).equals(id)){
                newCandidatures.add(candidatures.get(i));
            }
        }

        candidatures = newCandidatures;
        writeToFile();
    }

    public void candidateTo(String authorId, String annonceId){
        ArrayList<String> candidature = new ArrayList<>();
        candidature.add(String.valueOf(autoIncr_candidature));
        candidature.add(authorId);
        candidature.add(annonceId);
        candidatures.add(candidature);

        autoIncr_candidature = autoIncr_candidature + 1;
        writeToFile();
    }

    public boolean acceptCandidature(String candidatureId){

        String annonceId = null;
        String userId = null;
        for(ArrayList<String> candidature : candidatures){
            if(candidature.get(0).equals(candidatureId)){
                annonceId = candidature.get(2);
                userId = candidature.get(1);
                break;
            }
        }

        ArrayList<String> acceptedAnnonce = getAnnonceFromId(annonceId);
        removeAnnonceFromId(annonceId);

        acceptedAnnonce.add(2, userId);

        annonces_prisent.add(acceptedAnnonce);
        writeToFile();

        return true;
    }
}
