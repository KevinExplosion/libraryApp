import org.sql2o.*;
import java.util.List;

public class Patron {
  private int id;
  private String patron_name;
  // private String due_date;
  // private int id_title_author;

  public int getId() {
    return id;
  }

  public String getName() {
    return patron_name;
  }

  // public String getDueDate() {
  //   return due_date;
  // }
  //
  // public int getIdTitleAuthor() {
  //   return id_title_author;
  // }

  public Patron (String patron_name) {
    this.patron_name = patron_name;
  }

  public static List<Patron> all() {
    String sql = "SELECT id, patron_name FROM patron";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patron.class);
    }
  }

  @Override
  public boolean equals(Object otherPatron) {
    if(!(otherPatron instanceof Patron)) {
      return false;
    } else {
      Patron newPatron = (Patron) otherPatron;
      return this.getName().equals(newPatron.getName());
    }
  }

  public void save() {
    String sql = "INSERT INTO patron (patron_name) VALUES (:patron_name)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("patron_name", patron_name)
          .executeUpdate()
          .getKey();
    }
  }

  public static Patron find(int id) {
    String sql = "SELECT id, patron_name FROM patron WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Patron patron = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Patron.class);
      return patron;
    }
  }

  public void updateName(String patron_name) {
    String sql = "UPDATE patron SET patron_name = :patron_name WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("patron_name", patron_name)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  // public void updateDueDate(String due_date) {
  //   String sql = "UPDATE patron SET due_date = :due_date WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //     .addParameter("due_date", due_date)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }
  //
  // public void updateIdTitleAuthor(int id_title_author) {
  //   String sql = "UPDATE patron SET id_title_author = :id_title_author WHERE id = :id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //     .addParameter("id_title_author", id_title_author)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //   }
  // }

  public void delete() {
    String sql = "DELETE FROM patron WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
       .addParameter("id", id)
       .executeUpdate();
    }
  }

  public void addCopies (Copies copy) {
    String sql = "INSERT INTO patron_copy (id_patron, id_copy) VALUES (:id_patron, :id_copy)";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("id_patron", this.getId())
         .addParameter("id_copy", copy.getId())
         .executeUpdate();
    }
  }

  public List<Copies> getCopies() {
    try(Connection con = DB.sql2o.open()) {

      String sql = "SELECT copies.* FROM patron " +
      "JOIN patron_copy ON (patron.id = patron_copy.id_patron) " +
      "JOIN copies ON (patron_copy.id_copy = copies.id) " +
      "WHERE patron.id = :id";
      List<Copies> copies = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Copies.class);
      return copies;
    }
  }
}
