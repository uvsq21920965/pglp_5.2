import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import fr.uvsq21920965.pglp52.CompositePersonnels;
import fr.uvsq21920965.pglp52.CompositePersonnelsJdbc;
import fr.uvsq21920965.pglp52.Personnels;
import fr.uvsq21920965.pglp52.PersonnelsJdbc;

public class PersonnelsJdbcTest {

  PersonnelsJdbc pjdbc=  new PersonnelsJdbc();
  CompositePersonnelsJdbc cpjdbc=  new CompositePersonnelsJdbc();
  CompositePersonnels cp = new CompositePersonnels(1);

  /**
   * teste de la metheode create.
   */
  @Test
  public void createTest1() {
    Personnels ps=new Personnels.Builder("gobi","jhone","fournisseur").idGroupe(1).build();
    cp.add(ps);
    cpjdbc.create(cp);
    //pjdbc.deleteTable();
    assertNotNull(pjdbc.create(ps));
  }
 
}
