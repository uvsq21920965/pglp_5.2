package fr.uvsq21920965.pglp51;

/**
 * Interface Iterator.
 * @author Sarra Belmahdi.
 *
 */
public interface Iterator {
  /**
   * methode pour conaitre si y a encore des elmenets ou non. 
   * @return true si y a encore d'elements dans l'itiration sinon False.
   */
  public Boolean  hasNext();

  /**
   * methode pour retourner l'element.
   * @return element suivant dans l'itiration.
   */
  public Ipersonnels next();

}
