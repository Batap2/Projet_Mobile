package com.example.projet_interim;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projet_interim.Anon_Candidates.MainScreen_Anon_Candidates;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    EditText loging_text;
    Button login_button;
    ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        loging_text = (EditText) findViewById(R.id.login_text);
        login_button = (Button) findViewById(R.id.login_button);
        image = (ImageView) findViewById(R.id.imageView);

        DB db = new DB(getApplicationContext(), this);

        image.setRotation(2f);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = String.valueOf(loging_text.getText());
                ArrayList<String> user = db.getUserByName(login);

                if(user != null){
                    CurentUser.getInstance().id = user.get(0);
                    CurentUser.getInstance().username = user.get(1);
                    CurentUser.getInstance().role = user.get(2);

                    Toast.makeText(getApplicationContext(), user.get(0) + " " + user.get(1) + " " + user.get(2), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Mauvais nom d'utilisateur / mot de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            float x = 0;
            @Override
            public void run() {
                image.setRotationX((float) Math.cos(x));
                image.setRotationY((float) Math.cos(x+Math.PI/2));
                x += 0.02;
                handler.postDelayed(this, 10);
            }
        };

        handler.post(runnable);
    }

    public void anonConnect(View view){
        Toast.makeText(getApplicationContext(), "Vous êtes connecté en anonyme", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginScreen.this, MainScreen_Anon_Candidates.class);
        startActivity(intent);
    }

}
