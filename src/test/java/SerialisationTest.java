import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.uvsq21920965.pglp51.AfficheIteratorGroupe;
import fr.uvsq21920965.pglp51.CompositePersonnels;
import fr.uvsq21920965.pglp51.Iterator;
import fr.uvsq21920965.pglp51.Personnels;

public class SerialisationTest {

	Personnels ps;
	CompositePersonnels cp;
	AfficheIteratorGroupe afi;
	String resultat="";

	@Before
	public void createPersonnelsObject() {
	//personnel objet
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
     
     //compositePersonnels objet
     cp=new CompositePersonnels(0);
     LocalDate datenaiss2 = LocalDate.of(1991, 03, 11);
     Personnels ps2=new Personnels
       		 .Builder("saint","pierre","web designer")
       		 .dateNaissance(datenaiss2)
       		 .numeroDeTel(numerotel)
       		 .build();
  	cp.add(ps2);
  	cp.add(ps);
  	//affichageIteratorGroupe
  	afi= new AfficheIteratorGroupe(cp);
	}
	@Test
	public void personnelsTestS() throws IOException, ClassNotFoundException{
		//serialisation
		FileOutputStream fops = new FileOutputStream("/home/oem/git/pglp_5.1/src/test/resources/serialiPersonnels.txt");
		ObjectOutputStream ops = new ObjectOutputStream(fops);
		ops.writeObject(ps);
		ops.close();
		//desirialisation
		FileInputStream fips = new FileInputStream("/home/oem/git/pglp_5.1/src/test/resources/serialiPersonnels.txt");
		ObjectInputStream ips = new ObjectInputStream(fips);
		Personnels p=(Personnels)ips.readObject();
		ips.close();
		//test
		assertEquals(ps.getPrenom(),p.getPrenom());
		assertEquals(ps.getNom(),p.getNom());
		assertEquals(ps.getDateDeNaiss(),p.getDateDeNaiss());
		assertEquals(ps.getFonctions(),p.getFonctions());
		assertEquals(ps.getNumeroDeTel(),p.getNumeroDeTel());
		
	}
	@Test
	public void compositePersonnelsTestS() throws IOException, ClassNotFoundException{
		//serialisation
				FileOutputStream fops = new FileOutputStream("/home/oem/git/pglp_5.1/src/test/resources/serialiCompoPersonnels.txt");
				ObjectOutputStream ops = new ObjectOutputStream(fops);
				ops.writeObject(cp);
				ops.close();
				//desirialisation
				FileInputStream fips = new FileInputStream("/home/oem/git/pglp_5.1/src/test/resources/serialiCompoPersonnels.txt");
				ObjectInputStream ips = new ObjectInputStream(fips);
				CompositePersonnels cp1=(CompositePersonnels)ips.readObject();
				ips.close();
				//test
				assertEquals(cp.print(),cp1.print());
	}
	@Test
	public void affichageIteratorGroupeTestS() throws IOException, ClassNotFoundException{
		//serialisation
				FileOutputStream fops = new FileOutputStream("/home/oem/git/pglp_5.1/src/test/resources/serialiAffichageIterator.txt");
				ObjectOutputStream ops = new ObjectOutputStream(fops);
				ops.writeObject(afi);
				ops.close();
				//desirialisation
				FileInputStream fips = new FileInputStream("/home/oem/git/pglp_5.1/src/test/resources/serialiAffichageIterator.txt");
				ObjectInputStream ips = new ObjectInputStream(fips);
				AfficheIteratorGroupe afi1=(AfficheIteratorGroupe)ips.readObject();
				ips.close();
				//test
				for(Iterator iter = afi.getIterator();iter.hasNext();)
				     resultat+=iter.next().print()+"\n";
				String expected="";
				for(Iterator iter = afi1.getIterator();iter.hasNext();) 
				     expected+=iter.next().print()+"\n";
				assertEquals(resultat,expected);
	}

}
