import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.uvsq21920965.pglp51.*;
public class AfficheIteratorGroupeTest {
	
	 CompositePersonnels cp=new CompositePersonnels(0);
	 CompositePersonnels cp1=new CompositePersonnels(1);
	  Personnels ps1;
	  Personnels ps2;
	  Personnels ps3;
	  Personnels ps4;
	  AfficheIteratorGroupe afi;

@Test
public void testIterator() {

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
		cp.add(ps2);
	    ps3=new Personnels
		   		 .Builder("michel","jack","administrator")
		   		 .dateNaissance(datenaiss1)
		   		 .numeroDeTel(numerotel)
		   		 .build();
	    ps4=new Personnels
		   		 .Builder("lock","nicole","administrator")
		   		 .dateNaissance(datenaiss1)
		   		 .numeroDeTel(numerotel)
		   		 .build();
	    cp1.add(ps3);
		cp1.add(ps4);
		cp.add(cp1);
		String expected="id Groupe :0  nom : craip prenom : michel fonctions:developpeur dateDeNaiss :1996-04-24 numero de telephone0753 nom : saint prenom : pierre fonctions:web designer dateDeNaiss :1991-03-11 numero de telephone0753 id Groupe :1  nom : michel prenom : jack fonctions:administrator dateDeNaiss :1996-04-24 numero de telephone0753 nom : lock prenom : nicole fonctions:administrator dateDeNaiss :1996-04-24 numero de telephone0753\n" + 
				"nom : craip prenom : michel fonctions:developpeur dateDeNaiss :1996-04-24 numero de telephone0753\n" + 
				"nom : saint prenom : pierre fonctions:web designer dateDeNaiss :1991-03-11 numero de telephone0753\n" + 
				"id Groupe :1  nom : michel prenom : jack fonctions:administrator dateDeNaiss :1996-04-24 numero de telephone0753 nom : lock prenom : nicole fonctions:administrator dateDeNaiss :1996-04-24 numero de telephone0753\n" + 
				"nom : michel prenom : jack fonctions:administrator dateDeNaiss :1996-04-24 numero de telephone0753\n" + 
				"nom : lock prenom : nicole fonctions:administrator dateDeNaiss :1996-04-24 numero de telephone0753\n";
	
		String resultat="";	
		afi= new AfficheIteratorGroupe(cp);
		for(Iterator iter = afi.getIterator();iter.hasNext();) {
         resultat+=iter.next().print()+"\n";
		}
		assertEquals(resultat,expected);
    }
}
