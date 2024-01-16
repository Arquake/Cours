package exo1;

public class NumTel {
	// constantes
	
	// telephone fixe professionnel
	public static final char FIXE_PROF = 'T';
	
	// telephone fixe domicile
	public static final char FIXE_DOM = 'D';
	
	// telephone portable
	public static final char PORTABLE = 'P';
	
	// fax
	public static final char FAX = 'F';
	
	// nature du numéro inconnue
	public static final char INCONNU = '?';
	
	// attributs
	private int numero;
	private char type;
	
	// Constructeurs
	public NumTel(int num, char type) {
		numero = num;
		setType(type);
	}
	public NumTel(int num) {
		this(num, INCONNU);
	}
	
	// Accesseurs
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		switch (type) {
		case FIXE_PROF: case FIXE_DOM : case PORTABLE: case FAX: this.type = type;
		break;
		default: this.type = INCONNU;
		}
	}
	
	// Méthodes héritées de la classe Object et redéfinies
	
	public String toString() {
		return numero + " (" + type + ")";
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof NumTel)) return false;
		NumTel num = (NumTel) o;
		return this.numero == num.numero;
	}
	
	public int hashCode() {
		return numero;
	}
	
}
