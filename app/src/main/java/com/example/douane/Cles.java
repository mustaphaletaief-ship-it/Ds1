package com.example.douane;

// el classe hedhi fiha les clés lkol elli nesta3mlouhom fil putExtra
// (el groupe lkol yesta3mel nafs el classe)
public class Cles {

    // e5er 4 ar9am mel carte d'étudiant (baddelha bel matricule el s7i7)
    public static final String MATRICULE = "1234";

    // tbni el clé: matricule + "_" + esm el donnée
    private static String cle(String matricule, String nom) {
        return matricule + "_" + nom;
    }

    // el clé mta3 esm el voyageur
    public static String cleNom(String matricule) {
        return cle(matricule, "NOM_VOYAGEUR");
    }

    // el clé mta3 3am el wleda
    public static String cleAnneeNaissance(String matricule) {
        return cle(matricule, "ANNEE_NAISSANCE");
    }

    // el clé mta3 el 3am elli bech yemchilou
    public static String cleAnneeDestination(String matricule) {
        return cle(matricule, "ANNEE_DESTINATION");
    }

    // el clé mta3 el motif mta3 el safra
    public static String cleMotif(String matricule) {
        return cle(matricule, "MOTIF");
    }

    // el clé mta3 el verdict (approuvé wala refusé) - yesta3melha el activité 2 w 3
    public static String cleVerdict(String matricule) {
        return cle(matricule, "VERDICT");
    }
}