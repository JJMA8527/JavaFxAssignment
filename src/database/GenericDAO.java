package database;
/* CRUD operations
:
 * create a table in database
 * insert (create) object in database
 * getAll (read) retrieves all objects
 * delete
 * update
 * 
 */
import java.util.ArrayList;

public interface GenericDAO<T> {
    void createTable();
    int insert(T entity);
    ArrayList<T> getAll();
    void update(T entity);
    void delete(int id);

}
