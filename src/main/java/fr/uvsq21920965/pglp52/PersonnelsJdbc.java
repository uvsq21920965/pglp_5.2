package fr.uvsq21920965.pglp52;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * PersonnelsJdbc Classe.
 * @author Sarra Belmahdi.
 *
 */
public class PersonnelsJdbc implements Dao<Personnels> {
  /**
   * un attribut pour établire la connexion.
   */
  private Connection connexion = null;

  /**
   * la requte da creation de la table Personnels.
   */
  private String table = "create table Personnels(nom varchar(20) NOT NULL PRIMARY KEY, " 
      + " prenom  varchar(20) NOT NULL, fonction varchar(20) , idGroupe integer)";
  
  /**
   * attribut statemet.
   */
  private Statement statement;

  /**
   * Constructeur pour établissement de la connexion.
   * et la creation de la table si elle n'existe pas .
   */
  public PersonnelsJdbc() {
    connexion = this.getConnection();
    try {
      ResultSet res = connexion.getMetaData().getTables(null,null,"Personnels".toUpperCase(),null);
      statement = connexion.createStatement();
      if (!res.next()) {
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
   * @return obj crée sinon null.
   */
  @Override
  public Personnels create(Personnels obj) {
    connexion = this.getConnection();
    PreparedStatement create =  null;
    int status = 0;
    String insertString = "insert into Personnels(nom, prenom, fonction,idGroupe) values (?,?,?,?)";
    try {
      create = connexion.prepareStatement(insertString);
      create.setString(1, obj.getNom());
      create.setString(2, obj.getPrenom());
      create.setString(3, obj.getFonctions());
      create.setInt(4, obj.getIdGroupe());
      status = create.executeUpdate();
      connexion.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (create != null) {
        create.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    if (status > 0) {
      return obj;
    } else {
      return null;
    }
  }

  /**
   * methode pour trouver le tuple personnels.
   * @param id nom du personnels.
   * @return objet personnels trouvé ,null sinon.
   */
  @Override
  public Personnels find(String id) {
    connexion = this.getConnection();
    PreparedStatement find =  null;
    Personnels ps = null;
    ResultSet resultat = null;
    String findString = "select * from Personnels where nom = (?)"; 
    try {
      find = connexion.prepareStatement(findString);
      find.setString(1, id);
      find.execute();
      resultat = find.getResultSet();
      if (resultat.next()) {
        String nom = resultat.getString("nom");
        String prenom = resultat.getString("prenom");
        String fonction = resultat.getString("Fonction");
        int idG = resultat.getInt("idGroupe");
        ps = new Personnels.Builder(nom, prenom, fonction).idGroupe(idG).build();
        connexion.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (find != null) {
        find.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    return ps;
  }

  /**
   * methode pour mettre à jour un tuple personnels.
   * @param obj l'objet pour faire mise à jour.
   * @return l'objet après la mise à jour.
   */
  @Override
  public Personnels update(Personnels obj) {
    connexion = this.getConnection();
    PreparedStatement update =  null;
    String updateString = "update Personnels set prenom = (?), "
        + "fonction = (?), idGroupe = (?) where nom =(?)";
    try {
      update = connexion.prepareStatement(updateString);
      update.setString(1, obj.getPrenom());
      update.setString(2, obj.getFonctions());
      update.setInt(3, obj.getIdGroupe());
      update.setString(4, obj.getNom());
      update.executeUpdate();
      connexion.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (update != null) {
        update.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    return obj;
  }

  /**
   * methode pour supprimer le tuple de l'objet personnels de la base de données.
   * @param obj Personnels à supprimer.
   */
  @Override
  public void delete(Personnels obj) {
    connexion = this.getConnection();
    PreparedStatement delete =  null;
    String deleteString = "delete from Personnels where nom =(?)";
    try {
      delete = connexion.prepareStatement(deleteString);
      delete.setString(1, obj.getNom());
      delete.executeUpdate();
      connexion.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (delete != null) {
        delete.close();
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
  }

  /**
   * methode pour Connecter à la base de données sarradb.
   */
  public Connection getConnection() {
    Connection connexion = null;
    String driver = "org.apache.derby.jdbc.EmbeddedDriver";
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
