package com.example.douane;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

// el activité 1: el formulaire mta3 el tasjil
public class MainActivity extends AppCompatActivity {

    // les champs mta3 el formulaire
    private EditText etNom, etNaissance, etDestination, etLieu;
    private RadioGroup rgMotif, rgClasse;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // n9ablou kol champ m3a el id mte3ou fil layout
        etNom = findViewById(R.id.et_nom_ml);
        etNaissance = findViewById(R.id.et_annee_naissance_ml);
        etDestination = findViewById(R.id.et_annee_destination_ml);
        etLieu = findViewById(R.id.et_lieu_ml);
        rgMotif = findViewById(R.id.rg_motif_ml);
        rgClasse = findViewById(R.id.rg_classe_ml);
        btnInspecter = findViewById(R.id.btn_inspecter_ml);

        // ki nenzlou 3al bouton, nverifiw w nab3thou el données
        btnInspecter.setOnClickListener(v -> inspecterProfil());
    }

    // el fonction hedhi tjib el données w t7el el activité 2
    private void inspecterProfil() {
        // 1. njibou el texte elli ktbou el voyageur
        String nom = etNom.getText().toString().trim();
        String naissanceTxt = etNaissance.getText().toString().trim();
        String destinationTxt = etDestination.getText().toString().trim();
        String lieu = etLieu.getText().toString().trim();

        // 2. nverifiw elli el champs lkol m3abyin
        if (nom.isEmpty()) {
            etNom.setError("Entrez le nom");
            return;
        }
        if (naissanceTxt.isEmpty()) {
            etNaissance.setError("Entrez l'année de naissance");
            return;
        }
        if (destinationTxt.isEmpty()) {
            etDestination.setError("Entrez l'année de destination");
            return;
        }
        if (lieu.isEmpty()) {
            etLieu.setError("Entrez le lieu de destination");
            return;
        }

        // 3. nbadlou el texte l ra9m
        int naissance = Integer.parseInt(naissanceTxt);
        int destination = Integer.parseInt(destinationTxt);

        // 4. njibou el motif elli mchecki
        RadioButton rbMotif = findViewById(rgMotif.getCheckedRadioButtonId());
        String motif = rbMotif.getText().toString();

        // 5. njibou el classe elli mchecka
        RadioButton rbClasse = findViewById(rgClasse.getCheckedRadioButtonId());
        String classe = rbClasse.getText().toString();

        // 6. n7adhrou el intent explicite lel activité 2
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // 7. nab3thou el données b les clés elli tetbna bel méthode statique
        intent.putExtra(Cles.cleNom(Cles.MATRICULE), nom);
        intent.putExtra(Cles.cleAnneeNaissance(Cles.MATRICULE), naissance);
        intent.putExtra(Cles.cleAnneeDestination(Cles.MATRICULE), destination);
        intent.putExtra(Cles.cleLieu(Cles.MATRICULE), lieu);
        intent.putExtra(Cles.cleMotif(Cles.MATRICULE), motif);
        intent.putExtra(Cles.cleClasse(Cles.MATRICULE), classe);

        // 8. n7ellou el activité 2
        startActivity(intent);
    }
}