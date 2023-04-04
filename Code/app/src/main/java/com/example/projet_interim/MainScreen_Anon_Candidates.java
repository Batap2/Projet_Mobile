package com.example.projet_interim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainScreen_Anon_Candidates extends AppCompatActivity {

    ListView listView;
    List<String[]> offerList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_anon_candidate);

        listView = (ListView)findViewById(R.id.offre_listview);

        initOfferList();

        OfferAdaptator adapter = new OfferAdaptator(MainScreen_Anon_Candidates.this,offerList);
        listView.setAdapter(adapter);
    }

    void initOfferList(){
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});
        offerList.add(new String[] {"Caissier H/F", "Durant cet emploie vous aurez l'odieuse responsabilité de rester assis sur un siège et faire passer les articles devant un scanner. Etant en constante activité, le travail de caissier est extremement stimulant pour le cerveau.\nIl sera aussi nécéssaire d'avoir dans la pocket un bac+5 étant donné les nombreux calculs que vous aurez a réaliser", "SouperMarket\n13 rue de la cafetière, 12345 McDog"});

    }
}