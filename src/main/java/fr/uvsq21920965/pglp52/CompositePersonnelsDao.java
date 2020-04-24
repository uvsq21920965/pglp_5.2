package fr.uvsq21920965.pglp52;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * CompositePersonnelsDAO classe.
 * @author sarra Belmahdi.
 *
 */
public class CompositePersonnelsDao implements Dao<CompositePersonnels> {

  /**
   * methode pour creer un fichier compositepersonels.
   * @param obj compositePersonnels.
   * @return obj crée.
   */
  @Override
  public CompositePersonnels create(CompositePersonnels obj) {
    String file = "/home/oem/git/pglp_5.1/src/main/resources/groupe" + obj.getId() + ".txt"; 
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
   * methode pour trouver fichier compositepersonnels.
   * @param id nom du fichier.
   * @return objet dufichier recherché.
   */
  @Override
  public CompositePersonnels find(String id) {
    CompositePersonnels cp1 = null;
    String file = id;
    try {
      FileInputStream  fips = new FileInputStream(file);
      ObjectInputStream ips = new ObjectInputStream(fips);
      cp1 = (CompositePersonnels)ips.readObject();
      ips.close();
    } catch (IOException e1) {
      e1.printStackTrace();
    } catch (ClassNotFoundException e2) {
      e2.printStackTrace();
    }
    return cp1;
  }

  /**
   * methode pour mettre à jour un objet compositepersonnels.
   * @param obj l'objet qu'on veut mettre à jour.
   * @return l'objet après la mise à jour.
   */
  @Override
  public CompositePersonnels update(CompositePersonnels obj) {
    String fileName = "/home/oem/git/pglp_5.1/src/main/resources/groupe" + obj.getId() + ".txt";
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
   * methode pour supprimer le fichier de l'objet compositepersonnels objet.
   * @param obj compositePersonnels à supprimer.
   */
  @Override
  public void delete(CompositePersonnels obj) {
    String fileName = "/home/oem/git/pglp_5.1/src/main/resources/groupe" + obj.getId() + ".txt";
    File file = new File(fileName);
    if (file.exists() && file.delete()) {
      System.out.println("fichier supprimé");
    } else {
      System.out.println("fichier non trouvé");
    }
  }
}
