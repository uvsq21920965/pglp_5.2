package fr.uvsq21920965.pglp52;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompositePersonnelsJdbc implements Dao<CompositePersonnels> {
  /**
   * un attribut pour établire la connexion.
   */
  private Connection connexion = null;

  /**
   * la requte da creation de la table CompositePersonnels.
   */
  private String table = "create table CompositePersonnels(groupeId INTEGER primary key not null"+
    ", nomGroupe varchar(20) not null )";

  /**
   *  attribut statemet.
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
    String insertString = "insert into CompositePersonnels(groupeId, nomGroupe) values (" + obj.getId()+",'"+obj.getNomGroupe()+"')";
    try {
      status=connexion.createStatement().executeUpdate(insertString);
      DaoJdbc djdbc = new  DaoJdbc();
      Dao<Personnels> pj = djdbc.createPersonnelsJdbc();
      for (Ipersonnels p: obj.getPersonnes()) {
          if (!(p instanceof CompositePersonnels)) {
            pj.create((Personnels) p);
          }
        }
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
    connexion=this.getConnection();
    int groupeid = Integer.parseInt(id);
    String findString = "select * from CompositePersonnels where groupeId = (?)";
    CompositePersonnels cp = null;
    PreparedStatement find = null;
    try {
      find = connexion.prepareStatement(findString);
      find.setInt(1, groupeid);
      find.execute();
      ResultSet resultat = find.getResultSet();
      DaoJdbc djdbc = new  DaoJdbc();
      Dao<Personnels> pj = djdbc.createPersonnelsJdbc();
      Personnels ps=null;
      if (resultat.next()) {
        cp = new CompositePersonnels(resultat.getInt("groupeId"),resultat.getString("nomGroupe"));
        String findGroupe = "select nom from Personnels,CompositePersonnels where idGroupe = (?)";
        PreparedStatement findG = connexion.prepareStatement(findGroupe);
        findG.setInt(1, groupeid);
        findG.execute();
        ResultSet resultat1 = findG.getResultSet();
        if (resultat1.next()) {
          ps=pj.find(resultat1.getString("nom"));
          cp.add(ps);
        }
        findG.close();
      }
      find.close();
      connexion.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cp;
  }

  /**
   * methode pour mettre à jour un tuple Compositepersonnels.
   * @param obj l'objet pour faire mise à jour.
   * @return l'objet après la mise à jour.
   */
  @Override
  public CompositePersonnels update(CompositePersonnels obj) {
    connexion=this.getConnection();
    String updateString = "update CompositePersonnels set nomGroupe = (?) where groupeId = (?)";
    CompositePersonnels cp = null;
    PreparedStatement update = null;
    try {
      update = connexion.prepareStatement(updateString);
      update.setString(1, obj.getNomGroupe());
      update.setInt(2, obj.getId());
      update.execute();
      update.close();
      connexion.close();
    } catch (SQLException e) {
      e.printStackTrace();
	}
	return cp;
  }
  /**
   * methode pour supprimer le tuple de l'objet Compositepersonnels dele bd.
   * @param obj Personnels à supprimer.
   */
  @Override
  public void delete(CompositePersonnels obj) {
	connexion=this.getConnection();
    PreparedStatement delete = null;
    try {
    	DaoJdbc djdbc = new  DaoJdbc();
        Dao<Personnels> pj = djdbc.createPersonnelsJdbc();
      for (Ipersonnels p: obj.getPersonnes()) {
        if (!(p instanceof CompositePersonnels)) {
          pj.delete((Personnels) p);
        }
      }
      String deleteString = "Delete from CompositePersonnels where groupeId = (?)";
      String deleteString1 ="Update Personnels set idGroupe = null where idGroupe = (?)";
      int groupeid = obj.getId();
      delete = connexion.prepareStatement(deleteString);
      delete.setInt(1, groupeid);
      delete.execute();
      delete = connexion.prepareStatement(deleteString1);
      delete.setInt(1, groupeid);
      delete.execute();
      delete.close();
      if (delete != null) {
        delete.close();
	  }
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
}
