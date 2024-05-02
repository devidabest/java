package modele;

public class joueur {

        private int nujoueur;
        private String nom, prenom,nationalite;
        private int annais;

        public int getnujoueur() {
            return nujoueur;
        }
        public void setnujoueur(int nujoueur) {
            this.nujoueur = nujoueur;
            }
            public String getNom() {
            return nom;
            }
            public void setNom(String nom) {
            this.nom = nom;
            }
            public String getPrenom() {
            return prenom;
            }
            public void setPrenom(String prenom) {
            this.prenom = prenom;
            }

            public String getNationalite() {
            return nationalite;
            }
            public void setNationalite(String nationalite) {
            this.nationalite = nationalite;
            }

            public int getDatenais() {
            return annais;
            }
            public void setDatenais(int datenais) {
            this.annais = datenais ;
            }

            public joueur(int id, String nom, String prenom, int annais, String nationalite ) {
                super();
                this.nujoueur = id;
                this.nom = nom;
                this.prenom = prenom;
                this.nationalite = nationalite;
                this.annais = annais;
                }
                public joueur(int id) {
                super();
                this.nujoueur = id;
                }
                public joueur(String nom, String prenom) {
                super();
                this.nom = nom;
                this.prenom = prenom;
                }
                public joueur() {
                super();

                }
    }