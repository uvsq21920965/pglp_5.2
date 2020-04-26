import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import fr.uvsq21920965.pglp52.Personnels;
import fr.uvsq21920965.pglp52.PersonnelsJdbc;

public class PersonnelsJdbcTest {

	PersonnelsJdbc pjdbc=new PersonnelsJdbc();

@Before
public void deleteTable() {
	//vider le tabelau Personnels
	  Connection connexion = pjdbc.getConnection();
	  String deleteTableString = "delete from Personnels";
	  try {
		  connexion.createStatement().execute(deleteTableString);
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
    Personnels ps=new Personnels.Builder("gobi","jhone","fournisseur").idGroupe(5).build();
    assertNotNull(pjdbc.create(ps));
  }
  /**
   * teste de la metheode find.
   */
  @Test
  public void Test2find() {
	Personnels ps=new Personnels.Builder("gobi","jhone","fournisseur").idGroupe(6).build();
	pjdbc.create(ps);
    Personnels ps1=pjdbc.find("gobi");
    assertEquals(ps1.getNom(),"gobi");
    assertEquals(ps1.getPrenom(),"jhone");
    assertEquals(ps1.getFonctions(),"fournisseur");
    assertEquals(ps1.getIdGroupe(),6);
  }

  /**
   * teste de la metheode update.
   */
  @Test
  public void Test3upDate() {
	  Personnels ps2=new Personnels.Builder("bella","anna","fournisseur").idGroupe(3).build();
	  pjdbc.create(ps2);
	  Personnels ps=new Personnels.Builder("bella","annah","web designer").idGroupe(3).build();
	  pjdbc.update(ps);
	  Personnels ps1=pjdbc.find("bella");
	  assertEquals(ps1.getIdGroupe(),3);
	  assertEquals(ps1.getNom(),"bella");
	  assertEquals(ps1.getPrenom(),"annah");
	  assertEquals(ps1.getFonctions(),"web designer");
  }

  /**
   * teste de la metheode delete.
   */
  @Test
  public void Test4delete() {
	  Personnels ps1=new Personnels.Builder("belmahdi","sarra","studient").build();
	  pjdbc.create(ps1);
	  pjdbc.delete(ps1);
	  assertNull(pjdbc.find("belmahdi"));
  }
}
