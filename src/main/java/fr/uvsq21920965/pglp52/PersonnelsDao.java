package fr.uvsq21920965.pglp52;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * PersonnelsDAO class.
 * @author Sarra Belmahdi.
 *
 */
public class PersonnelsDao implements Dao<Personnels> {

  /**
   * methode pour creer un fichier personels.
   * @param obj Personnels.
   * @return obj crée.
   */
  @Override
  public Personnels create(Personnels obj) {
    String file = "/home/oem/git/pglp_5.1/src/main/resources/"
        + obj.getNom() + "_" + obj.getPrenom() + ".txt"; 
    try {
      FileOutputStream fops = new FileOutputStream(file);
      ObjectOutputStream ops = new ObjectOutputStream(fops);
      ops.writeObject(obj);
      ops.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return obj;
  }

  /**
   * methode pour trouver fichier personnels.
   * @param id nom du fichier.
   * @return objet dufichier recherché.
   */
  @Override
  public Personnels find(String id) {
    Personnels p1 = null;
    String file = id;
    try {
      FileInputStream  fips = new FileInputStream(file);
      ObjectInputStream ips = new ObjectInputStream(fips);
      p1 = (Personnels)ips.readObject();
      ips.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    } catch (ClassNotFoundException e2) {
      e2.printStackTrace();
    }
    return p1;
  }

  /**
   * methode pour mettre à jour un objet personnels.
   * @param obj l'objet qu'on veut mettre à jour.
   * @return l'objet après la mise à jour.
   */
  @Override
  public Personnels update(Personnels obj) {
    String fileName = "/home/oem/git/pglp_5.1/src/main/resources/"
        + obj.getNom() + "_" + obj.getPrenom() + ".txt";
    File file = new File(fileName);
    if (file.exists()) {
      try {
        FileOutputStream fops = new FileOutputStream(fileName);
        ObjectOutputStream ops = new ObjectOutputStream(fops);
        ops.writeObject(obj);
        ops.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return obj;
    } else {
      System.out.println("le fichier n'existe pas");
      return null;
    }
  }

  /**
   * methode pour supprimer le fichier de l'objet personnels objet.
   * @param obj Personnels à supprimer.
   */
  @Override
  public void delete(Personnels obj) {
    String fileName = "/home/oem/git/pglp_5.1/src/main/resources/"
        + obj.getNom() + "_" + obj.getPrenom() + ".txt";
    File file = new File(fileName);
    if (file.exists() && file.delete()) {
      System.out.println("fichier supprimé");
    } else {
      System.out.println("fichier non trouvé");
    }
  }
}
