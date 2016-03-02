import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/library_test", null, null);
  }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteAuthorQuery = "DELETE FROM author *;";
      String deleteCatalogQuery = "DELETE FROM catalog *;";
      String deletePatronQuery = "DELETE FROM patron *;";
      String deleteTitleQuery = "DELETE FROM title *;";
      String deleteTitleAuthorQuery = "DELETE FROM title_author *;";
      con.createQuery(deleteAuthorQuery).executeUpdate();
      con.createQuery(deleteCatalogQuery).executeUpdate();
      con.createQuery(deletePatronQuery).executeUpdate();
      con.createQuery(deleteTitleQuery).executeUpdate();
      con.createQuery(deleteTitleAuthorQuery).executeUpdate();
    }
  }
}
