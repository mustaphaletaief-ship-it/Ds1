package com.example.douane;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

// el activité 2: taffichi el passeport w tab3ath el verdict lel activité 3
public class VerificationActivity extends AppCompatActivity {

    // el motif elli ynajjem ya3mel paradoxe
    private static final String MOTIF_PARADOXE = "Modification du passé";

    // nkhalliw el esm hnee bech nab3thouh ba3d lel activité 3
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // nwarriw el interface mta3 el passeport
        setContentView(R.layout.activity_verification);

        //njibou el données elli ba3thet-hom el activité 1
        Intent intentRecu = getIntent();
        nom = intentRecu.getStringExtra(Cles.cleNom(Cles.MATRICULE));
        int naissance = intentRecu.getIntExtra(Cles.cleAnneeNaissance(Cles.MATRICULE), 0);
        int destination = intentRecu.getIntExtra(Cles.cleAnneeDestination(Cles.MATRICULE), 0);
        String motif = intentRecu.getStringExtra(Cles.cleMotif(Cles.MATRICULE));
        String classe = intentRecu.getStringExtra(Cles.cleClasse(Cles.MATRICULE));

        // ne7sbou el âge temporel
        int ageTemporel = destination - naissance;

        //njibou el TextView mel layout
        TextView tvNom = findViewById(R.id.tv_nom_mg);
        TextView tvAge = findViewById(R.id.tv_age_mg);
        TextView tvVoyage = findViewById(R.id.tv_voyage_mg);
        TextView tvClasse = findViewById(R.id.tv_classe_mg);
        TextView tvMotif = findViewById(R.id.tv_motif_mg);

        //n3abbiw el passeport bel données el s7i7a
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvVoyage.setText("Voyage : " + naissance + " → " + destination);
        tvClasse.setText("Classe : " + classe);
        tvMotif.setText("Motif : " + motif);

        //njibou el zouz boutons
        Button btnApprouver = findViewById(R.id.btn_approuver_mg);
        Button btnRefuser = findViewById(R.id.btn_refuser_mg);

        //wa9tech 3anna paradoxe: nsakkrou bouton Approuver
        if (MOTIF_PARADOXE.equals(motif) && estPremier(Math.abs(ageTemporel))) {
            btnApprouver.setEnabled(false);
            btnApprouver.setAlpha(0.5f);

            // affichage message mta3 el paradoxe m3a e sbab
            TextView tvAlerte = findViewById(R.id.tv_alerte_mg);
            tvAlerte.setText("Risque de paradoxe : " + Math.abs(ageTemporel) + " est un nombre premier.");
            tvAlerte.setVisibility(View.VISIBLE);
        }

        //kol bouton yab3ath verdict lel activité 3
        btnApprouver.setOnClickListener(v -> envoyerVerdict(true));
        btnRefuser.setOnClickListener(v -> envoyerVerdict(false));
    }

    // tab3ath el verdict wel esm lel activité 3 b intent explicite
    private void envoyerVerdict(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cleVerdict(Cles.MATRICULE), verdict);
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        startActivity(intent);
    }

    // traja3 true ken el nombre premier
    private boolean estPremier(int nombre) {
        if (nombre < 2) {
            return false;
        }
        for (int i = 2; i * i <= nombre; i++) {
            if (nombre % i == 0) {
                return false;
            }
        }
        return true;
    }
}