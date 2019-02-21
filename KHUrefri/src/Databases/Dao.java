package Databases;

public abstract class Dao {
	protected DatabaseConnector databaseConnector;	
	
	Dao(DatabaseConnector databaseConnector) {
		this.databaseConnector = databaseConnector;
	}

}
