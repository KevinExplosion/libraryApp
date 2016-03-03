import org.sql2o.*;
import java.util.List;

public class Copies {
  private int id;
  private int copies;
  private String due_date;

  public int getId() {
    return id;
  }

  public int getCopies() {
    return copies;
  }

  public String getDueDate() {
    return due_date;
  }

  public Copies (int copies, String due_date) {
    this.copies = copies;
    this.due_date = due_date;
  }

  public static List<Copies> all() {
    String sql = "SELECT id, copies, due_date FROM copies";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Copies.class);
    }
  }

  @Override
  public boolean equals(Object otherCopies) {
    if(!(otherCopies instanceof Copies)) {
      return false;
    } else {
      Copies newCopies = (Copies) otherCopies;
      return this.getCopies() == (newCopies.getCopies()) &&
              this.getDueDate().equals(newCopies.getDueDate());
    }
  }

  public void save() {
    String sql = "INSERT INTO copies (copies, due_date) VALUES (:copies, :due_date)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("copies", copies)
          .addParameter("due_date", due_date)
          .executeUpdate()
          .getKey();
    }
  }

  public static Copies find(int id) {
    String sql = "SELECT id, copies, due_date FROM copies WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Copies copies = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Copies.class);
      return copies;
    }
  }

  public void updateCopies(int copies) {
    String sql = "UPDATE copies SET copies = :copies WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("copies", copies)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateDueDate(int copies) {
    String sql = "UPDATE copies SET copies = :copies, due_date = :due_date WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("copies", copies)
      .addParameter("due_date", due_date)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM copies WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
       .addParameter("id", id)
       .executeUpdate();
    }
  }

  public void addPatron (Patron patron) {
    String sql = "INSERT INTO patron_copy (id_patron, id_copy) VALUES (:id_patron, :id_copy)";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("id_patron", patron.getId())
         .addParameter("id_copy", this.getId())
         .executeUpdate();
    }
  }

  public List<Patron> getPatrons() {
    try(Connection con = DB.sql2o.open()) {

      String sql = "SELECT patron.* FROM copies " +
      "JOIN patron_copy ON (copies.id = patron_copy.id_title) " +
      "JOIN patron ON (patron_copy.id_patron = patron.id) " +
      "WHERE copy.id = :id";
      List<Patron> patrons = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Patron.class);
      return patrons;
    }
  }
}
