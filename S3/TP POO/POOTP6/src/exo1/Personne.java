package exo1;

public class Personne  {
	// des cstes definissant les valeurs possible pour la civilité
	public static final int INCONNU = 0;
	public static final int MR = 1;
	public static final int MME = 2;
	public static final int MLLE = 3;
	
	// un tableau de String pour faire correspondre une chaine à la civilité
	private static final String[] civil_ = {"", "Mr", "Mme", "Mlle"} ;
	
	// les attributs d'un objet Personne
	private String nom_;
	private String prenom_;
	private int civilite_ = INCONNU;
	
	// les constructeurs
	public Personne(String nom, String prenom) {
		this(INCONNU, nom, prenom);
	}
	
	public Personne(int civilite, String nom, String prenom) {
		nom_ = nom;
		prenom_ = prenom;
		if (civiliteCorrecte(civilite)) civilite_ = civilite;
		else civilite_ = INCONNU;
	}

	public String getNom_() {
		return nom_;
	}

	public void setNom_(String nom_) {
		this.nom_ = nom_;
	}

	public String getPrenom_() {
		return prenom_;
	}

	public void setPrenom_(String prenom_) {
		this.prenom_ = prenom_;
	}

	public int getCivilite_() {
		return civilite_;
	}

	public void setCivilite_(int civilite) {
		if (civiliteCorrecte(civilite_)) {
			switch (civilite_) {
			case INCONNU: civilite_ = civilite; break;
			case MLLE: if (civilite == MME) civilite_ = MME; break;
			default: // on ne fait rien 
				break;
			}
		}
	}
	// méthodes héritées de la classe Object et redéfinies 
	
	public String toString() {
		return civil_[civilite_] + " " + prenom_ + " " + nom_;
	}
	
	private boolean civiliteCorrecte(int civilite) {
		return INCONNU <= civilite && civilite <= MLLE;
	}
	
}
