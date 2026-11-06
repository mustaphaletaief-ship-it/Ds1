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

        // 1. Rbatna les elements mta3 l'interface bel IDs mte3hom
        LinearLayout rootResult = findViewById(R.id.root_result_yy);
        TextView tvMessage = findViewById(R.id.tv_message_yy);
        Button btnNouveau = findViewById(R.id.btn_nouveau_voyageur_yy);

        // 2. Jebna el verdict w el esm b nafs les cles elli mawjoudin fi classe Cles
        // 3. 5allina el valeur par defaut false, yaani ken tsir ay ghalta, el resultat yetrefdha
        boolean verdict = getIntent().getBooleanExtra(
                Cles.cleVerdict(Cles.MATRICULE), false);
        String nom = getIntent().getStringExtra(
                Cles.cleNom(Cles.MATRICULE));
        if (nom == null || nom.isEmpty()) {
            // 4. 7mina el application mel valeurs el fergha
            nom = getString(R.string.nom_inconnu);
        }

        // 5. Baddalna el fond w el message 7asb el verdict
        if (verdict) {
            // Ma9boul : fond a5dher w message "Bon voyage"
            rootResult.setBackgroundColor(ContextCompat.getColor(this, R.color.vert_approuve));
            tvMessage.setText(getString(R.string.msg_approuve, nom));
        } else {
            // Marfoudh : fond a7mer w message "El i3ti9al ezzamni"
            rootResult.setBackgroundColor(ContextCompat.getColor(this, R.color.rouge_refuse));
            tvMessage.setText(getString(R.string.msg_refuse, nom));
        }

        // 6. Bouton "Nouveau voyageur" yarja3 lel MainActivity
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            // 7. Sta3malna NEW_TASK + CLEAR_TASK bech nfarrghou el Back Stack,
            // yaani nsakrou les Activities elli 9bal w n3awdou nebdew mel MainActivity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}