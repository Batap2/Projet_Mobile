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

    // users : id, username, role
    // annonces : id, creator_user_id, titre, description, coordonnées
    // notifications : id, user_id, titre, description
    // candidatures : id, candidat_id, annonce_id
    // candidature_en_cours : id, candidat_id, annonce_id
    public ArrayList<ArrayList<String>> candidatures_en_cours = new ArrayList<>();
    public ArrayList<ArrayList<String>> candidatures = new ArrayList<>();
    public ArrayList<ArrayList<String>> notifications = new ArrayList<>();
    public ArrayList<ArrayList<String>> annonces = new ArrayList<>();
    public ArrayList<ArrayList<String>> users = new ArrayList<>();

    public DB(Context context, Activity activity){
        readFromFile(activity, context);
    }

    // Lis le fichier DB.json dans /data/data/com.example.projet_interim/files et convertis le fichier json en objet DB, l'initialise
    public void readFromFile(Activity activity, Context context){

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

                this.users = newDB.users;
                this.annonces = newDB.annonces;
                this.notifications = newDB.notifications;
                this.candidatures = newDB.candidatures;
                this.candidatures_en_cours =newDB.candidatures_en_cours;

            } catch (IOException e) {
                System.out.println("Probleme lecture");
                e.printStackTrace();
            }
        }
    }

    // Convertis l'objet DB en fichier json et l'écris dans /data/data/com.example.projet_interim/files/DB.json
    public void writeFromFile(Context context){
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
}
