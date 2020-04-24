package fr.uvsq21920965.pglp52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * PersonnelsJdbc Classe.
 * @author Sarra Belmahdi.
 *
 */
public class PersonnelsJdbc implements Dao<Personnels>{
  /**
   * un attribut pour établire la connexion.
   */
  private Connection connexion = null;
  
  /**
   * la requte da creation de la table Personnels.
   */
  private String table = "create table Personnels(nom varchar(20) PRIMARY KEY NOT NULL, " 
	         + " prenom  varchar(20), fonction varchar(20))";
  
  /**
   * attribut statemet.
   */
  private Statement statement;

  /**
   * Constructeur.
   */
  public PersonnelsJdbc() {
	  connexion=this.getConnection();
	  try {
		ResultSet res = connexion.getMetaData().getTables(null,null, "Personnels".toUpperCase(), null);
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
   * methode pour insérer un tuple dans la table personels.
   * @param obj Personnels.
   * @return obj crée.
   */
  @Override
  public Personnels create(Personnels obj) {
	connexion=this.getConnection();
	int status = 0;
    String insertString = "insert into Personnels(nom, prenom, fonction) values ('" + obj.getNom() 
        + "','" + obj.getPrenom() + "','" + obj.getFonctions() + "')";
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
   * methode pour trouver le tuple personnels.
   * @param id nom du personnels.
   * @return objet personnels crée.
   */
  @Override
  public Personnels find(String id) {
	connexion=this.getConnection();
	Personnels ps = null;
	String findString = "select * from Personnels where nom ='"+id+"'"; 
	try {
	  ResultSet resultat = connexion.createStatement().executeQuery(findString);
	  if (resultat.next()) {
	    String nom = resultat.getString("nom");
	    String prenom = resultat.getString("prenom");
	    String fonction = resultat.getString("Fonction");
	    ps = new Personnels.Builder(nom, prenom, fonction).build();
	  }
	connexion.close();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return ps;
  }

  /**
   * methode pour mettre à jour un tuple personnels.
   * @param obj l'objet qu'on veut mettre à jour.
   * @return l'objet après la mise à jour.
   */
  @Override
  public Personnels update(Personnels obj) {
	connexion=this.getConnection();
	String updateString = "update Personnels set nom ='"+obj.getNom()+"', prenom ='"+obj.getPrenom()+"',fonction ='"
	    +obj.getFonctions()+"' where nom ='"+obj.getNom()+"'";
	try {
	  connexion.createStatement().executeUpdate(updateString);
	  connexion.close();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return obj;
  }

  /**
   * methode pour supprimer le tuple de l'objet personnels de la base de données.
   * @param obj Personnels à supprimer.
   */
  @Override
  public void delete(Personnels obj) {
	connexion=this.getConnection();
	String deleteString = "delete from Personnels where nom ='"+obj.getNom()+"'";
	try {
		connexion.createStatement().executeUpdate(deleteString);
		connexion.close();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
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
  
  /**
   * methode pour supprimer la table Personnels.
   */
  public void deleteTable() {
    connexion=this.getConnection();
	String deleteTableString = "drop table Personnels";
	try {
	  connexion.createStatement().execute(deleteTableString);
	  connexion.close();
	} catch (SQLException e1) {
	  e1.printStackTrace();
	}
  }
}
