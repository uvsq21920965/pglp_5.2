package fr.uvsq21920965.pglp51;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CompositePersonnels Class.
 * @author Sarra Belmahdi.
 *
 */
public class CompositePersonnels implements Ipersonnels,Serializable {
  /**
   * id du groupe de personnels.
   */
  private int id;

  /**
   * liste des Ipersonnels.
   */
  private List<Ipersonnels> personnes;

  /**
   * constructeur.
   * @param idAtt id du groupede personnels.
   */

  public CompositePersonnels(int idAtt) {
    id = idAtt;
    personnes = new ArrayList<Ipersonnels>();
  }

  /**
   * ajouter un personnel.
   * @param personneAtt  un personnels
   */
  public void add(Ipersonnels personneAtt) {
    personnes.add(personneAtt);
  }

  /**
   * supprimer un personnel.
   * @param personneAtt un personnel.
   */
  public void remove(Ipersonnels personneAtt) {
    if (personnes.contains(personneAtt)) {
      personnes.remove(personneAtt);
    } else {
      System.out.println("element non trouve");
    }
  }

  /**
   * methode pour acceder à la liste des personnels.
   * @return liste des  personnels.
   */
  public List<Ipersonnels> getPersonnes() {
    return personnes;
  }

  /**
   * affiche les cordonnees de tous les personnels du groupe.
   */
  public String print() {
    StringBuffer affiche = new StringBuffer();
    for (Ipersonnels per : personnes) {
      affiche.append(" ");
      affiche.append(per.print());
    }
    return "id Groupe :" + id + " " + affiche;
  }
}
