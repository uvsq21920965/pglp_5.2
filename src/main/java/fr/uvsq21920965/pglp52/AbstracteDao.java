package fr.uvsq21920965.pglp52;

/**
 * AbstracteDao interface.
 * 
 * @author Sarra Belmahdi.
 *
 */
public interface AbstracteDao {
  Dao<Personnels> createPersonnelsJdbc();
  Dao<CompositePersonnels> createCompositePersonnelsJdbc();
}
