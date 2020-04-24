import static org.junit.Assert.assertEquals;
import fr.uvsq21920965.pglp51.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
/**
 * Personnels test
 * @author Sarra Belmahdi.
 *
 */
public class PersonnelsTest{
	/**
	 *creer personnel objets;
	 */
	Personnels ps;
	/**
	 * test builde methode.
	 */
	@Test
	public void Buildertest() {
	List<Integer>   numerotel;
	 numerotel= new ArrayList<Integer>();
     numerotel.add(0); 
     numerotel.add(7);
     numerotel.add(5);
     numerotel.add(3); 
     LocalDate datenaiss = LocalDate.of(1996, 04, 24);
     ps=new Personnels
    		 .Builder("craip","michel","developpeur")
    		 .dateNaissance(datenaiss)
    		 .numeroDeTel(numerotel)
    		 .build();
     assertEquals(ps.getNom(),"craip");
     assertEquals(ps.getPrenom(),"michel");
     assertEquals(ps.getFonctions(),"developpeur");
     assertEquals(ps.getDateDeNaiss(),LocalDate.of(1996, 04, 24));
     assertEquals(ps.getNumeroDeTel().toString(),"[0, 7, 5, 3]");
	}
	/**
	 * test print methode.
	 */
	@Test
	public void printTest() {
		List<Integer>   numerotel;
		 numerotel= new ArrayList<Integer>();
	     numerotel.add(8); 
	     numerotel.add(7);
	     numerotel.add(1);
	     numerotel.add(3); 
	     LocalDate datenaiss = LocalDate.of(1996, 04, 24);
	     ps=new Personnels
	    		 .Builder("gobi","jhone","fournisseur")
	    		 .dateNaissance(datenaiss)
	    		 .numeroDeTel(numerotel)
	    		 .build();
	     String affiche="nom : gobi prenom : jhone fonctions:fournisseur dateDeNaiss :1996-04-24 numero de telephone8713";
	     assertEquals(ps.print(),affiche);
	     
		}
	
}
