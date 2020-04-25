package fr.uvsq21920965.pglp52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompositePersonnelsJdbc implements Dao<CompositePersonnels> {
	/**
	   * un attribut pour établire la connexion.
	   */
	  private Connection connexion = null;
	  
	  /**
	   * la requte da creation de la table Personnels.
	   */
	  private String table = "create table CompositePersonnels(groupeId INTEGER primary key not null)";
	  
	  /**
	   * attribut statemet.
	   */
	  private Statement statement;

	  /**
	   * Constructeur.
	   */
	  public CompositePersonnelsJdbc() {
		  connexion=this.getConnection();
		  try {
			ResultSet res = connexion.getMetaData().getTables(null,null, "CompositePersonnels".toUpperCase(), null);
			statement = connexion.createStatement();
			if(!res.next()) {
			  statement.execute(table);
			}
			statement.close();
			connexion.close();
		} catch (SQLException e1) {
	      e1.printStackTrace();
		}
	  }
	  
	  /**
	   * methode pour insérer un tuple dans la table CompositePersonnels.
	   * @param obj CompositePersonnels.
	   * @return obj crée sinon null.
	   */
	  @Override
	  public CompositePersonnels create(CompositePersonnels obj) {
		connexion=this.getConnection();
		int status = 0;
	    String insertString = "insert into CompositePersonnels(groupeId) values (" + obj.getId()+")";
	    try {
	      status=connexion.createStatement().executeUpdate(insertString);
	      connexion.close();
		} catch (SQLException e) {
	      e.printStackTrace();
		}
	    if ( status > 0) {
	    	return obj;	
	    }
	    else {
	      return null;
	    }
	  }

	  /**
	   * methode pour trouver le tuple CompositePersonnels.
	   * @param id num de groupe du CompositePersonnels.
	   * @return objet CompositePersonnels trouvé ,null sinon.
	   */
	  @Override
	  public CompositePersonnels find(String id) {
		  return null;
	  }

	  /**
	   * methode pour Connecter à la base de données sarradb.
	   */
	  public Connection getConnection() {
		  Connection connexion=null;
		  String driver="org.apache.derby.jdbc.EmbeddedDriver";
	    try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    try {
	    	connexion = DriverManager.getConnection("jdbc:derby:sarradb;create=true");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connexion;
	  }

	@Override
	public CompositePersonnels update(CompositePersonnels obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompositePersonnels obj) {
		// TODO Auto-generated method stub
		
	}
}
