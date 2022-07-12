package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.AdresseLivraisonM;
import modele.CommandeM;
import modele.ContactM;
import modele.UtilisateurM;

public class CommandeD implements IDao<CommandeM>{
	
	Connection connect = new ConnectMySql().getConnection();

	@Override
	public boolean create(CommandeM commande) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement sql = connect.prepareStatement("INSERT INTO commande(idUtilisateur, dateC, total, idAdresse, etat) VALUES (?, now(), ?, ?, ?");
			
			sql.setObject(1, commande.getIdUtilisateur().getId());
			sql.setFloat(2, commande.getTotal());
			sql.setObject(3, commande.getIdAdresse().getId());
			sql.setInt(4, commande.getEtat());


			System.out.println(sql);
			sql.executeUpdate();
			return true;
			
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<CommandeM> read() {
		// TODO Auto-generated method stub
		ArrayList<CommandeM> commandes = new ArrayList<>();
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM commande INNER JOIN utilisateur ON commande.idUtilisateur = utilisateur.id INNER JOIN adresseLivraison ON commande.idAdresse = utilisateur.id");
			ResultSet rs = sql.executeQuery();
			
			while (rs.next()) {
				CommandeM commande = new CommandeM();
				UtilisateurM utilisateur = new UtilisateurM(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("dateInscription"),rs.getString("email"),rs.getString("motDePasse"));
				AdresseLivraisonM adresseLivraison = new AdresseLivraisonM(rs.getInt("id"),utilisateur,rs.getString("adresse"),rs.getInt("codePostal"),rs.getString("ville"),rs.getString("pays"));
				
				commande.setId(rs.getInt("id"));
				commande.setIdUtilisateur(utilisateur);
				commande.setDateC(rs.getString("dateC"));
				commande.setTotal(rs.getFloat("total"));
				commande.setIdAdresse(adresseLivraison);
				commande.setEtat(rs.getInt("etat"));
				
				
				commandes.add(commande);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return commandes;
	}

	@Override
	public boolean update(CommandeM commande, int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement sql = connect.prepareStatement("UPDATE commande SET idUtilisateur = ?, total = ?, idAdresse = ?, etat = ? WHERE id = ?");
			sql.setInt(1, commande.getIdUtilisateur().getId());
			sql.setFloat(2, commande.getTotal());
			sql.setInt(3, commande.getIdAdresse().getId());
			sql.setInt(4, commande.getEtat());
			sql.setInt(6, id);
			
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
	public CommandeM findById(int id) {
		// TODO Auto-generated method stub
		CommandeM commande = new CommandeM();

		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM commande WHERE id= ?");
			sql.setInt(1, id);
			ResultSet rs = sql.executeQuery();
			if (rs.next()) {
				
				UtilisateurM utilisateur = new UtilisateurM(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("dateInscription"),rs.getString("email"),rs.getString("motDePasse")	);
				AdresseLivraisonM adresseLivraison = new AdresseLivraisonM(rs.getInt("id"),utilisateur,rs.getString("adresse"),rs.getInt("codePostal"),rs.getString("ville"),rs.getString("pays"));

				commande.setIdUtilisateur(utilisateur);
				commande.setDateC(rs.getString("dateC"));
				commande.setTotal(rs.getFloat("total"));
				commande.setIdAdresse(adresseLivraison);
				commande.setEtat(rs.getInt("etat"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commande;
	}

}
