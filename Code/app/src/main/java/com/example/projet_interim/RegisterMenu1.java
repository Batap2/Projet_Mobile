package com.example.projet_interim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_interim.Anon_Candidates.AnnonceList_Menu_Anon_Candidates;

import java.util.ArrayList;

public class RegisterMenu1 extends AppCompatActivity {

    Button candidat_b;
    Button employeur_b;
    Button agence_b;
    Button admin_b;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register1_menu);

        DB.exempleFillIfEmpty(getApplicationContext(), this);

        candidat_b = (Button) findViewById(R.id.candidat_button);
        employeur_b = (Button) findViewById(R.id.employeur_button);
        agence_b = (Button) findViewById(R.id.agence_button);
        admin_b = (Button) findViewById(R.id.admin_button);

        intent = new Intent(getApplicationContext(), RegisterMenu2.class);
    }

    public void candidat_register(View v){
        intent.putExtra("role", "candidat");
        next();
    }
    public void employeur_register(View v){
        intent.putExtra("role", "employeur");
        next();
    }
    public void agence_register(View v){
        intent.putExtra("role", "agence");
        next();
    }
    public void admin_register(View v){
        intent.putExtra("role", "admin");
        next();
    }

    public void next(){
        startActivity(intent);
        finish();
    }
}
