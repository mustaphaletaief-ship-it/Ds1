package com.example.douane;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Liaison des éléments de l'interface avec leurs IDs
        LinearLayout rootResult = findViewById(R.id.root_result_yy);
        TextView tvMessage = findViewById(R.id.tv_message_yy);
        Button btnNouveau = findViewById(R.id.btn_nouveau_voyageur_yy);

        // Récupération du verdict et du nom avec les clés construites par Cles
        // Valeur par défaut false : en cas de problème, on refuse
        boolean verdict = getIntent().getBooleanExtra(
                Cles.cleVerdict(Cles.MATRICULE), false);
        String nom = getIntent().getStringExtra(
                Cles.cleNom(Cles.MATRICULE));
        if (nom == null || nom.isEmpty()) {
            nom = getString(R.string.nom_inconnu); // Protection contre une valeur vide
        }

        // Changement du fond et du message selon le verdict
        if (verdict) {
            // Approuvé : fond vert et message de bon voyage
            rootResult.setBackgroundColor(ContextCompat.getColor(this, R.color.vert_approuve));
            tvMessage.setText(getString(R.string.msg_approuve, nom));
        } else {
            // Refusé : fond rouge et message d'arrestation temporelle
            rootResult.setBackgroundColor(ContextCompat.getColor(this, R.color.rouge_refuse));
            tvMessage.setText(getString(R.string.msg_refuse, nom));
        }

        // Bouton "Nouveau voyageur" : retour à MainActivity
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            // NEW_TASK + CLEAR_TASK : vide la pile des activités précédentes
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}