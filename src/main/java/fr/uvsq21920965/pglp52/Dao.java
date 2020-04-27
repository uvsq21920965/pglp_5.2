package fr.uvsq21920965.pglp52;

import java.sql.SQLException;

/**
 * interface DAO.
 * @author Sarra Belmahdi.
 *
 * @param <T>  type d'objet.
 */
public interface Dao<T> {

  /**
   * methode pour creer un fichier.
   * @param obj T.
   * @return obj crée.
   */
  public T create(T obj);

  /**
   * methode pour trouver fichier id.
   * @param id nom du fichier.
   * @return objet du fichier recherché.
   */
  public T find(String id);

  /**
   * methode pour mettre à jour un objet T.
   * @param obj l'objet qu'on veut mettre à jour.
   * @return l'obj après la mise à jour.
   */
  public T update(T obj);

  /**
   * methode pour supprimer le fichier de l'objet T obj.
   * @param obj T à supprimer.
   */
  public void delete(T obj);
}
