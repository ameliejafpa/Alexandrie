package modele;

public class AdministrateurM {
	private int id;
	private String nom;
	private String prenom;
	private String motDePasse;
	private String privilege;
	
	public AdministrateurM() {
	}

	public AdministrateurM(String nom, String prenom, String motDePasse, String privilege) {
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.privilege = privilege;
	}

	public AdministrateurM(int id, String nom, String prenom, String motDePasse, String privilege) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.privilege = privilege;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	

}
