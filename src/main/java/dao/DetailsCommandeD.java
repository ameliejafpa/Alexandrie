package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.AdresseLivraisonM;
import modele.CommandeM;
import modele.DetailsCommandeM;
import modele.ProduitM;
import modele.UtilisateurM;

public class DetailsCommandeD implements IDao<DetailsCommandeM>{
	
	Connection connect = ConnectMySql.getConnection();


	@Override
	public boolean create(DetailsCommandeM detailsCommande) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement sql = connect.prepareStatement("INSERT INTO detailsCommande(idCommande, idProduit, quantite, prix) VALUES (?, ?, ?, ?");
			
			sql.setObject(1, detailsCommande.getIdCommande().getId());
			sql.setObject(2, detailsCommande.getIdProduit().getId());
			sql.setInt(3, detailsCommande.getQuantite());
			sql.setFloat(4, detailsCommande.getPrix());


			System.out.println(sql);
			sql.executeUpdate();
			return true;
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<DetailsCommandeM> read() {
		// TODO Auto-generated method stub
		ArrayList<DetailsCommandeM> detailsCommandes = new ArrayList<>();
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM detailsCommande INNER JOIN commande ON detailsCommande.idCommande = commande.id INNER JOIN produit ON detailsCommande.idProduit = produit.id");
			ResultSet rs = sql.executeQuery();
			
			while (rs.next()) {
				DetailsCommandeM detailsCommande = new DetailsCommandeM();
				UtilisateurM utilisateur = new UtilisateurM(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("dateInscription"),rs.getString("email"),rs.getString("motDePasse"));
				AdresseLivraisonM adresseLivraison = new AdresseLivraisonM(rs.getInt("id"),utilisateur,rs.getString("adresse"),rs.getInt("codePostal"),rs.getString("ville"),rs.getString("pays"));
				CommandeM commande = new CommandeM(rs.getInt("id"), utilisateur, rs.getString("dateC"), rs.getFloat("total"), adresseLivraison,rs.getInt("etat"));
				ProduitM produit = new ProduitM(rs.getInt("id"),rs.getString("titre"));
				

				detailsCommande.setId(rs.getInt("id"));
				detailsCommande.setIdCommande(commande);
				detailsCommande.setIdProduit(produit);
				detailsCommande.setQuantite(rs.getInt("quantite"));
				detailsCommande.setPrix(rs.getInt("prix"));
				
				
				detailsCommandes.add(detailsCommande);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return detailsCommandes;	}

	@Override
	public boolean update(DetailsCommandeM detailsCommande, int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement sql = connect.prepareStatement("UPDATE commande SET idCommande = ?, idProduit = ?, quantite = ?, prix = ? WHERE id = ?");
			sql.setInt(1, detailsCommande.getIdCommande().getId());
			sql.setInt(2, detailsCommande.getIdProduit().getId());
			sql.setInt(3, detailsCommande.getQuantite());
			sql.setFloat(4, detailsCommande.getPrix());
			sql.setInt(5, id);
			
			sql.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement sql = connect.prepareStatement("DELETE FROM commande WHERE id = ?");
			sql.setInt(1, id);
			sql.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DetailsCommandeM findById(int id) {
		// TODO Auto-generated method stub
		DetailsCommandeM detailsCommande = new DetailsCommandeM();
		

		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM detailsCommande WHERE id= ?");
			sql.setInt(1, id);
			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				
				UtilisateurM utilisateur = new UtilisateurM(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("dateInscription"),rs.getString("email"),rs.getString("motDePasse"));
				AdresseLivraisonM adresseLivraison = new AdresseLivraisonM(rs.getInt("id"),utilisateur,rs.getString("adresse"),rs.getInt("codePostal"),rs.getString("ville"),rs.getString("pays"));
				CommandeM commande = new CommandeM(rs.getInt("id"), utilisateur, rs.getString("dateC"), rs.getFloat("total"), adresseLivraison,rs.getInt("etat"));
				ProduitM produit = new ProduitM(rs.getInt("id"),rs.getString("titre"));

				detailsCommande.setIdCommande(commande);
				detailsCommande.setIdProduit(produit);
				detailsCommande.setQuantite(rs.getInt("quantite"));
				detailsCommande.setPrix(rs.getInt("prix"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return detailsCommande;
	}

}
