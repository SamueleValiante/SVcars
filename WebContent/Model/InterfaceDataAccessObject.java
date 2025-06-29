package Model;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDataAccessObject<T> 
{
		public void doSave(T product) throws SQLException;

		public boolean doDelete(String code) throws SQLException;

		public T doRetrieveByKey(String code) throws SQLException;
		
		public List<T> doRetrieveAll(String order) throws SQLException;
	

}
