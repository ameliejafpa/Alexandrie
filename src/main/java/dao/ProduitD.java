package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modele.CategorieM;
import modele.ProduitM;
import modele.SousCategorieM;

public class ProduitD implements IDao<ProduitM> {
	Connection connect = ConnectMySql.getConnection();
	
	@Override
	public boolean create(ProduitM produit) {
		try {	
			PreparedStatement sql = connect.prepareStatement("INSERT INTO produit (titre, description, prix, image,"
					+ " idSousCategorie, stock, stockMinimum) VALUES (?,?,?,?,?,?,?)");
			sql.setString(1, produit.getTitre()); 
			sql.setString(2, produit.getDescription()); 
			sql.setFloat(3, produit.getPrix()); 
			sql.setString(4, produit.getImage()); 
			sql.setInt(5, produit.getIdSousCategorie().getId()); 
			sql.setInt(6, produit.getStock()); 
			sql.setInt(7, produit.getStockMinimum()); 
			sql.executeUpdate(); 
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public ArrayList<ProduitM> read() {
		ArrayList<ProduitM> listeProduit = new ArrayList<>();
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM produit INNER JOIN sousCategorie ON "
					+ "produit.idSousCategorie=sousCategorie.id INNER JOIN categorie ON sousCategorie.idCategorie=categorie.id");
			ResultSet res = sql.executeQuery(); 			
			while (res.next()) {
				ProduitM produit = new ProduitM(res.getInt("produit.id"), res.getString("produit.titre"), 
						res.getString("produit.description"), res.getFloat("produit.prix"), res.getString("produit.image"), 
						new SousCategorieM(res.getInt("sousCategorie.id"),res.getString("sousCategorie.titre"), 
								new CategorieM(res.getInt("categorie.id"), res.getString("categorie.titre"))
						),res.getInt("produit.stock"), res.getInt("produit.stockMinimum"));
				listeProduit.add(produit);
			}			
		} 
		catch (SQLException e) {
			e.printStackTrace();	
		}
		return listeProduit;
	}

	@Override
	public boolean update(ProduitM produit, int id) {
		try {
			PreparedStatement sql = connect.prepareStatement("UPDATE produit SET titre=?, description=?, prix=?, image=?, "
					+ "idSousCategorie=?, stock=?, stockMinimum=? WHERE produit.id=?");			
			sql.setString(1, produit.getTitre()); 
			sql.setString(2, produit.getDescription()); 
			sql.setFloat(3, produit.getPrix()); 
			sql.setString(4, produit.getImage()); 
			sql.setInt(5, produit.getIdSousCategorie().getId()); 
			sql.setInt(6, produit.getStock()); 
			sql.setInt(7, produit.getStockMinimum()); 
			sql.setInt(8, id);		
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
			PreparedStatement sql = connect.prepareStatement("DELETE FROM produit WHERE id = ?");	
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
	public ProduitM findById(int id) {
		ProduitM produit = null;		
		try {
			PreparedStatement sql = connect.prepareStatement("SELECT * FROM produit INNER JOIN sousCategorie ON "
					+ "produit.idSousCategorie=sousCategorie.id INNER JOIN categorie ON sousCategorie.idCategorie=categorie.id"
					+ " WHERE produit.id= "+id+"");			
			ResultSet res = sql.executeQuery();			
			if(res.next()) {
				produit = new ProduitM(res.getInt("produit.id"), res.getString("produit.titre"), res.getString("produit.description"), 
						res.getFloat("produit.prix"), res.getString("produit.image"), 
						new SousCategorieM(res.getInt("sousCategorie.id"),res.getString("sousCategorie.titre"), 
								new CategorieM(res.getInt("categorie.id"), res.getString("categorie.titre"))
						),res.getInt("produit.stock"), res.getInt("produit.stockMinimum"));					
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return produit;
	}

} // fin ProduitD
