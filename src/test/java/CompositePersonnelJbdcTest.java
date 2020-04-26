import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import fr.uvsq21920965.pglp52.CompositePersonnels;
import fr.uvsq21920965.pglp52.CompositePersonnelsJdbc;
import fr.uvsq21920965.pglp52.Ipersonnels;
import fr.uvsq21920965.pglp52.Personnels;

public class CompositePersonnelJbdcTest {

  CompositePersonnelsJdbc cpjdbc = new CompositePersonnelsJdbc();

	  /**
	   * teste de la metheode create.
	   */
	  @Test
	  public void createTest1() {
		//vider le tabelau CompositePersonnels
		  Connection connexion = cpjdbc.getConnection();
		  String deleteTableString = "delete from CompositePersonnels";
		  try {
			  connexion.createStatement().execute(deleteTableString);
			  connexion.close();
			} catch (SQLException e1) {
			  e1.printStackTrace();
			}
		CompositePersonnels cp = new CompositePersonnels(1,"groupe1");
	    Personnels ps=new Personnels.Builder("gobi","jhone","fournisseur").idGroupe(1).build();
	    cp.add(ps);
	    assertNotNull(cpjdbc.create(cp));
			
		  }
	  /**
	   * teste de la metheode find.
	   */
	  @Test
	  public void findTest2() {
		  CompositePersonnels cp =cpjdbc.find("1");
	    assertEquals(cp.getNomGroupe(),"groupe1");
	    assertNotNull(cp.getPersonnes());
	    List<Ipersonnels> allpersonne=cp.getPersonnes();
	    String nom = null;
		   String prenom = null;
		   String activite = null;
		   int groupeid = 0;
		   Ipersonnels p = allpersonne.get(0);
			   if (!(p instanceof CompositePersonnels)) {
			         nom = ((Personnels)p).getNom();
			         prenom = ((Personnels)p).getPrenom();
			         activite = ((Personnels)p).getFonctions();
			         groupeid = ((Personnels)p).getIdGroupe();
			    }
			    assertEquals(nom,"gobi");
			    assertEquals(prenom,"jhone");
			    assertEquals(activite,"fournisseur");
			    assertEquals(groupeid,1);
	  }
	
	  /**
	   * teste de la metheode update.
	   */
	  @Test
	  public void upDateTest3() {
		  CompositePersonnels cp = new CompositePersonnels(1,"groupe01");
		  cpjdbc.update(cp);
		  CompositePersonnels cp1 = cpjdbc.find("1");
	      assertEquals(cp1.getNomGroupe(),"groupe01");
	  }
	  /**
	   * teste de la metheode delete.
	   */
	  @Test
	  public void deleteTest4() {
		  CompositePersonnels cp = new CompositePersonnels(2,"groupe2");
		  cpjdbc.create(cp);
		  cpjdbc.delete(cp);
		  assertNull(cpjdbc.find("2"));
	  }
}
