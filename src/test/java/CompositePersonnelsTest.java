import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import fr.uvsq21920965.pglp51.*;

public class CompositePersonnelsTest {
	/**
	 * creer les objets.
	 */
  CompositePersonnels cp=new CompositePersonnels(0);
  CompositePersonnels cp1=new CompositePersonnels(1);
  CompositePersonnels cp2=new CompositePersonnels(2);
  Personnels ps1;
  Personnels ps2;
  /**
   * tester la methode add.
   */
@Test 
public void AddTest1() {
	List<Integer>   numerotel;
	numerotel= new ArrayList<Integer>();
    numerotel.add(0); 
    numerotel.add(7);
    numerotel.add(5);
    numerotel.add(3); 
    LocalDate datenaiss1 = LocalDate.of(1996, 04, 24);
    ps1=new Personnels
   		 .Builder("craip","michel","developpeur")
   		 .dateNaissance(datenaiss1)
   		 .numeroDeTel(numerotel)
   		 .build();
    LocalDate datenaiss2 = LocalDate.of(1991, 03, 11);
    ps2=new Personnels
      		 .Builder("saint","pierre","web designer")
      		 .dateNaissance(datenaiss2)
      		 .numeroDeTel(numerotel)
      		 .build();
	cp.add(ps1);
	assertTrue(cp.getPersonnes().contains(ps1));
	cp.add(cp1);
	assertTrue(cp.getPersonnes().contains(cp1));
	cp.add(ps2);
	assertTrue(cp.getPersonnes().contains(ps2));
	cp.add(cp2);
	assertTrue(cp.getPersonnes().contains(cp2));
	
}
/**
 * tester la methode move.
 */
@Test 
public void moveTest2() {
	List<Integer>   numerotel;
	 numerotel= new ArrayList<Integer>();
   numerotel.add(0); 
   numerotel.add(7);
   numerotel.add(5);
   numerotel.add(3); 
   LocalDate datenaiss1 = LocalDate.of(1996, 04, 24);
   ps1=new Personnels
  		 .Builder("craip","michel","developpeur")
  		 .dateNaissance(datenaiss1)
  		 .numeroDeTel(numerotel)
  		 .build();
   LocalDate datenaiss2 = LocalDate.of(1991, 03, 11);
   ps2=new Personnels
     		 .Builder("saint","pierre","web designer")
     		 .dateNaissance(datenaiss2)
     		 .numeroDeTel(numerotel)
     		 .build();
	cp.add(ps1);
	cp.add(cp1);
	cp.add(ps2);
	cp.add(cp2);
	cp.remove(ps1);
	assertFalse(cp.getPersonnes().contains(ps1));
	cp.remove(cp1);
	assertFalse(cp.getPersonnes().contains(cp1));
	cp.remove(cp2);
	assertFalse(cp.getPersonnes().contains(cp2));
	assertTrue(cp.getPersonnes().contains(ps2));
	
}
/**
 * tester la methode print.
 */
@Test 
public void printTest3() {
   List<Integer>   numerotel;
   numerotel= new ArrayList<Integer>();
   numerotel.add(0); 
   numerotel.add(7);
   numerotel.add(5);
   LocalDate datenaiss2 = LocalDate.of(1991, 03, 11);
   ps2=new Personnels
     		 .Builder("saint","pierre","web designer")
     		 .dateNaissance(datenaiss2)
     		 .numeroDeTel(numerotel)
     		 .build();
	cp.add(ps2);
	String affiche="id Groupe :0  nom : saint prenom : pierre fonctions:web designer dateDeNaiss :1991-03-11 numero de telephone075";
    assertEquals(cp.print(),affiche);
}

}
