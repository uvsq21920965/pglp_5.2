package fr.uvsq21920965.pglp52;

public class DaoJdbc implements AbstracteDao{

	@Override
	public Dao<Personnels> createPersonnelsJdbc() {
		return new PersonnelsJdbc ();
	}

	@Override
	public Dao<CompositePersonnels> createCompositePersonnelsJdbc() {
		return new CompositePersonnelsJdbc();
	}

}
