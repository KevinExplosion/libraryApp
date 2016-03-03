import org.sql2o.*;
import java.util.List;

public class Catalog {
  private int id;
  private int copies;
  private int id_title_author;

  public int getId() {
    return id;
  }

  public int getCopies() {
    return copies;
  }

  public int getIdTitleAuthor() {
    return id_title_author;
  }

  public Catalog (int copies, int id_title_author) {
    this.copies = copies;
    this.id_title_author = id_title_author;
  }

  public static List<Catalog> all() {
    String sql = "SELECT id, copies, id_title_author FROM catalog";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Catalog.class);
    }
  }

  @Override
  public boolean equals(Object otherCatalog) {
    if(!(otherCatalog instanceof Catalog)) {
      return false;
    } else {
      Catalog newCatalog = (Catalog) otherCatalog;
      return this.getCopies() == (newCatalog.getCopies()) &&
             this.getIdTitleAuthor() == newCatalog.getIdTitleAuthor();
    }
  }

  public void save() {
    String sql = "INSERT INTO catalog (copies, id_title_author) VALUES (:copies, :id_title_author)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("copies", copies)
          .addParameter("id_title_author", id_title_author)
          .executeUpdate()
          .getKey();
    }
  }

  public static Catalog find(int id) {
    String sql = "SELECT id, copies, id_title_author FROM catalog WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Catalog patron = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Catalog.class);
      return patron;
    }
  }

  public void updateCopies(int copies) {
    String sql = "UPDATE catalog SET copies = :copies WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("copies", copies)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateIdTitleAuthor(int id_title_author) {
    String sql = "UPDATE catalog SET id_title_author = :id_title_author WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id_title_author", id_title_author)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM catalog WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
       .addParameter("id", id)
       .executeUpdate();
    }
  }
}
