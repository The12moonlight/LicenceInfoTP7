/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/**
 *
 * @author pierr
 */
public class DAOExtends extends DAO {
    //public final DataSource myDataSource;
    
    /* Source de donnée à utiliser */
    public DAOExtends(DataSource dataSource){
        super(dataSource);
    }
    
    /* Delete */
    /**
	 * Detruire un enregistrement dans la table CUSTOMER
	 * @param discountCode le code discount a supprimer
	 * @return le nombre d'enregistrements détruits (1 ou 0 si pas trouvé)
	 * @throws DAOException
	 */
	public void deleteDiscountCode(String discountCode) throws DAOException {

		// Une requête SQL paramétrée
		String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
		try (   Connection connection = myDataSource.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)
                ) {
                        // Définir la valeur du paramètre
			stmt.setString(1, discountCode);
			
                        // On execute la requete
			stmt.execute();
                        
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			throw new DAOException(ex.getMessage());
		}
	}
    /* Add */
    /** 
     * Ajoute un code discount
     * @param discountCode nom du code discount
     * @param rate valeur du code discount
     * @return 
     */    
    public void addDiscountCode(String discountCode, float rate) throws SQLException, DAOException{
        // Une requête SQL paramétrée
	 String sql = "INSERT INTO DISCOUNT_CODE(DISCOUNT_CODE, RATE) VALUES(?, ?)";
         try (Connection connection = myDataSource.getConnection();
                 PreparedStatement discountStatement = connection.prepareStatement(sql)  ){
             // On définit la valeur de paramètre
             discountStatement.setString(1, discountCode);
             discountStatement.setFloat(2, rate);
             
             // On ajoute le code discount avec une requete
             discountStatement.execute();
         } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage()); 
         }
    }
    
    /* List All Discount Code */
    public List<DiscountCodeEntity> listDiscountCode() throws DAOException, SQLException{
        List<DiscountCodeEntity> result = new LinkedList<>();
        String rqSqlListDiscount = "SELECT * FROM DISCOUNT_CODE";
        
        /* On se connecte a la bd */
        try (	Connection connection = myDataSource.getConnection();
		     PreparedStatement stmt = connection.prepareStatement(rqSqlListDiscount)){
            try (ResultSet rs = stmt.executeQuery()) { 
                while (rs.next()){ // On récupere la liste des codes de discount
                    String resultString = rs.getString("DISCOUNT_CODE");
                    float resultFloat = rs.getFloat("RATE");
                    // On crée l'objet DiscountCodeEntity
                    DiscountCodeEntity discCode = new DiscountCodeEntity(resultString, resultFloat);
                    // On l'ajoute à la liste des résultats
                    result.add(discCode);
                }
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
            return result;
        }
    }
}
