package com.example.douane;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

// el activité 2: taffichi el passeport w tab3ath el verdict lel activité 3
public class VerificationActivity extends AppCompatActivity {

    // nkhallou el esm houni bech nab3thouh ba3d lel activité 3
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. nwarriw el interface mta3 el passeport
        setContentView(R.layout.activity_verification);

        // 2. njibou el données elli ba3thet-hom el activité 1
        Intent intentRecu = getIntent();
        nom = intentRecu.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        int naissance = intentRecu.getIntExtra(Cles.cleAnneeNaissance(Cles.MATRICULE), 0);
        int destination = intentRecu.getIntExtra(Cles.cleAnneeDestination(Cles.MATRICULE), 0);
        String lieu = intentRecu.getStringExtra(Cles.cleLieu(Cles.MATRICULE));
        String motif = intentRecu.getStringExtra(Cles.cleMotif(Cles.MATRICULE));
        String classe = intentRecu.getStringExtra(Cles.cleClasse(Cles.MATRICULE));

        // 3. n7esbou el âge temporel
        int ageTemporel = destination - naissance;
    }
}