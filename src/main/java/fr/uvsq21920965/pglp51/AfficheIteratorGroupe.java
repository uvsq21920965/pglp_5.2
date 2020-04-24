package fr.uvsq21920965.pglp51;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Personnels.
 * 
 * @author sarra Belmahdi.
 *
 */
public class AfficheIteratorGroupe implements Serializable{
  /**
   * liste des Ipersonnels.
   */
  private List<Ipersonnels> personnes;

  /**
   * Constructeur.
   * @param root .
   */

  public AfficheIteratorGroupe(Ipersonnels root) {
    personnes = new ArrayList<Ipersonnels>();
    personnes.add(root);
  }

  /**
   * creer un iterateurde type Ipersonnel.
   * @return Iterator.
   */
  public Iterator getIterator() {
    return new IpersonnelsIteratorGroupe(); 
  }

  /**
   * Classe IpersonnelsIteratorGroupe.
   * @author Sarra Belmahdi.
   *
   */
  private class IpersonnelsIteratorGroupe implements Iterator {

    /**
     * construceur.
     */
    IpersonnelsIteratorGroupe() {
      int verifie = 0;
      while (verifie < personnes.size()) {
        if (personnes.get(verifie) instanceof CompositePersonnels) {
          CompositePersonnels cp = (CompositePersonnels)personnes.get(verifie);
          for (Ipersonnels p : cp.getPersonnes()) {
            personnes.add(p);
          }
        }
        verifie++;
      }
    }

    /**
     * indice d'indexation.
     */
    int index = 0;

    /**
     * methode pour conaitre si y a encore des elmenets ou non. 
     * @return true si y a encore d'elements dans l'itiration sinon False.
     */
    public Boolean hasNext() {
      if (index < personnes.size()) {
        return true;
      }
      return false;
    }

    /**
     * methode pour retourner l'element.
     * @return element suivant dans l'itiration.
     */
    public Ipersonnels next() {
      if (this.hasNext()) {
        return personnes.get(index++);
      }
      return null;
    }
  }
}
