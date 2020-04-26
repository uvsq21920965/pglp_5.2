import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.uvsq21920965.pglp52.CompositePersonnels;
import fr.uvsq21920965.pglp52.CompositePersonnelsJdbc;
import fr.uvsq21920965.pglp52.Ipersonnels;
import fr.uvsq21920965.pglp52.Personnels;

public class CompositePersonnelJbdcTest {

  CompositePersonnelsJdbc cpjdbc = new CompositePersonnelsJdbc();
  
  @Before
  public void deleteTable() {
  	//vider le tabelau Personnels
  	  Connection connexion = cpjdbc.getConnection();
  	  String deleteTableString = "delete from CompositePersonnels";
  	String deleteTableString1 = "delete from Personnels";
  	  try {
  		  connexion.createStatement().execute(deleteTableString);
  		connexion.createStatement().execute(deleteTableString1);
  		  connexion.close();
  		} catch (SQLException e1) {
  		  e1.printStackTrace();
  		} 
  }

	  /**
	   * teste de la metheode create.
	   */
	  @Test
	  public void Test1create() {
		CompositePersonnels cp = new CompositePersonnels(1,"groupe1");
	    Personnels ps=new Personnels.Builder("hobi","jhone","fournisseur").idGroupe(1).build();
	    cp.add(ps);
	    assertNotNull(cpjdbc.create(cp));
	 }
	  /**
	   * teste de la metheode find.
	   */
	  @Test
	  public void Test2find() {
		CompositePersonnels cp = new CompositePersonnels(4,"groupe4");
		Personnels ps=new Personnels.Builder("Ghobi","jhone","fournisseur").idGroupe(4).build();
		cp.add(ps);
		cpjdbc.create(cp);
		CompositePersonnels cp1 =cpjdbc.find("4");
		assertEquals(cp1.getNomGroupe(),"groupe4");
	    assertNotNull(cp1.getPersonnes());
	    String nom = null;
		   String prenom = null;
		   String activite = null;
		   int groupeid = 0;
		   Ipersonnels p = cp1.getPersonnes().get(0);
			   if (!(p instanceof CompositePersonnels)) {
			         nom = ((Personnels)p).getNom();
			         prenom = ((Personnels)p).getPrenom();
			         activite = ((Personnels)p).getFonctions();
			         groupeid = ((Personnels)p).getIdGroupe();
			    }
			    assertEquals(nom,"Ghobi");
			    assertEquals(prenom,"jhone");
			    assertEquals(activite,"fournisseur");
			    assertEquals(groupeid,4);
	  }
	
	  /**
	   * teste de la metheode update.
	   */
	  @Test
	  public void Test3upDate() {
		  CompositePersonnels cp2 = new CompositePersonnels(7,"groupe7");
		  cpjdbc.create(cp2);
		  CompositePersonnels cp = new CompositePersonnels(7,"groupe07");
		  cpjdbc.update(cp);
		  CompositePersonnels cp1 = cpjdbc.find("7");
	      assertEquals(cp1.getNomGroupe(),"groupe07");
	  }
	  /**
	   * teste de la metheode delete.
	   */
	  @Test
	  public void Test4delete() {
		  CompositePersonnels cp = new CompositePersonnels(2,"groupe2");
		  cpjdbc.create(cp);
		  cpjdbc.delete(cp);
		  assertNull(cpjdbc.find("2"));
	  }
}
