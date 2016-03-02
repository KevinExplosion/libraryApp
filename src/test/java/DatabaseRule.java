import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/______________test", null, null);
  }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String delete___________Query = "DELETE FROM ___________ *;";
      String delete___________Query = "DELETE FROM ___________ *;";
      con.createQuery(delete___________Query).executeUpdate();
      con.createQuery(delete___________Query).executeUpdate();
    }
  }
}
