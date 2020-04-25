import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;

import fr.uvsq21920965.pglp52.Personnels;
import fr.uvsq21920965.pglp52.PersonnelsJdbc;

public class PersonnelsJdbcTest {

	PersonnelsJdbc pjdbc=new PersonnelsJdbc();

  /**
   * teste de la metheode create.
   */
  @Test
  public void createTest1() {
	  //vider le tabelau Personnels
	  Connection connexion = pjdbc.getConnection();
	  String deleteTableString = "delete from Personnels";
	  try {
		  connexion.createStatement().execute(deleteTableString);
		  connexion.close();
		} catch (SQLException e1) {
		  e1.printStackTrace();
		} 
    Personnels ps=new Personnels.Builder("gobi","jhone","fournisseur").idGroupe(1).build();
    assertNotNull(pjdbc.create(ps));
  }
  /**
   * teste de la metheode find.
   */
  @Test
  public void findTest2() {
    Personnels ps1=pjdbc.find("gobi");
    assertEquals(ps1.getNom(),"gobi");
    assertEquals(ps1.getPrenom(),"jhone");
    assertEquals(ps1.getFonctions(),"fournisseur");
    assertEquals(ps1.getIdGroupe(),1);
  }

  /**
   * teste de la metheode update.
   */
  @Test
  public void upDateTest3() {
	  Personnels ps=new Personnels.Builder("gobi","jhone","web designer").idGroupe(1).build();
	  pjdbc.update(ps);
	  Personnels ps1=pjdbc.find("gobi");
	  assertEquals(ps1.getIdGroupe(),1);
	  assertEquals(ps1.getNom(),"gobi");
	  assertEquals(ps1.getPrenom(),"jhone");
	  assertEquals(ps1.getFonctions(),"web designer");
  }

  /**
   * teste de la metheode delete.
   */
  @Test
  public void deleteTest4() {
	  Personnels ps1=new Personnels.Builder("belmahdi","sarra","studient").build();
	  pjdbc.create(ps1);
	  pjdbc.delete(ps1);
	  assertNull(pjdbc.find("belmahdi"));
  }
}
