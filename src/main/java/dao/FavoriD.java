package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modele.FavoriM;
import modele.ProduitM;
import modele.UtilisateurM;

public class FavoriD implements IDao<FavoriM>{
	Connection connect = ConnectMySql.getConnection();

	@Override
	public boolean create(FavoriM favori) {
		try {	
			PreparedStatement sql = connect.prepareStatement("INSERT INTO favori (idProduit,idUtilisateur) VALUES (?,?)");
			sql.setInt(1, favori.getIdProduit().getId()); 
			sql.setInt(2, favori.getIdUtilisateur().getId()); 
			sql.executeUpdate(); 
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public ArrayList<FavoriM> read() {
		ArrayList<FavoriM> listeFavori = new ArrayList<>();
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM favori INNER JOIN produit ON "
					+ "favori.idProduit=produit.id INNER JOIN favori.idUtilisateur=utilisateur.id ");
			ResultSet res = sql.executeQuery(); 			
			while (res.next()) {
				FavoriM favori = new FavoriM(res.getInt("favori.id"), new ProduitM(res.getInt("produit.id"), 
					res.getString("produit.titre")), new UtilisateurM(res.getInt("id"),res.getString("nom"), res.getString("prenom"),
					res.getString("dateInscription"), res.getString("email"), res.getString("motDePasse")));
				listeFavori.add(favori);
			}			
		} 
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return listeFavori;
	}

	@Override
	public boolean update(FavoriM favori, int id) {
		try {
			PreparedStatement sql = connect.prepareStatement("UPDATE favori SET idProduit=?, idUtilisateur=? WHERE id=?");		
			sql.setInt(1, favori.getIdProduit().getId()); 
			sql.setInt(2, favori.getIdUtilisateur().getId());  
			sql.setInt(3, id);		
			sql.executeUpdate();		
			return true;		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			PreparedStatement sql = connect.prepareStatement("DELETE FROM favori WHERE id = ?");	
			sql.setInt(1,id);	
			sql.executeUpdate();		
			return true;			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public FavoriM findById(int id) {
		FavoriM favori = null;		
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM favori INNER JOIN produit ON "
					+ "favori.idProduit=produit.id INNER JOIN favori.idUtilisateur=utilisateur.id "
					+ "WHERE favori.id= "+id+"");			
			ResultSet res = sql.executeQuery();			
			if(res.next()) {
				favori =  new FavoriM(res.getInt("favori.id"), new ProduitM(res.getInt("produit.id"), res.getString("produit.titre")),
					new UtilisateurM(res.getInt("id"),res.getString("nom"),res.getString("prenom"),res.getString("dateInscription"),
					res.getString("email"),res.getString("motDePasse")));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return favori;
	}

} // fin FavoriD
