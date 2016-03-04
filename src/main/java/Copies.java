import org.sql2o.*;
import java.util.List;

public class Copies {
  private int id;
  private int copies;
  private String due_date;
  public int title_id;

  public int getId() {
    return id;
  }

  public int getCopies() {
    return copies;
  }

  public String getDueDate() {
    return due_date;
  }

  public int getTitleId() {
    return title_id;
  }

  public Copies (int copies, String due_date, int title_id) {
    this.copies = copies;
    this.due_date = due_date;
    this.title_id = title_id;
  }

  public static List<Copies> all() {
    String sql = "SELECT id, copies, due_date, title_id FROM copies";
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
              this.getDueDate().equals(newCopies.getDueDate()) &&
              this.getTitleId() == (newCopies.getTitleId());
    }
  }

  public void save() {
    String sql = "INSERT INTO copies (copies, due_date, title_id) VALUES (:copies, :due_date, :title_id)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("copies", copies)
          .addParameter("due_date", due_date)
          .addParameter("title_id", title_id)
          .executeUpdate()
          .getKey();
    }
  }

  public static Copies find(int id) {
    String sql = "SELECT id, copies, due_date, title_id FROM copies WHERE id = :id";
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

  public void updateDueDate(String due_date) {
    String sql = "UPDATE copies SET due_date = :due_date WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("due_date", due_date)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateTitleId(int title_id) {
    String sql = "UPDATE copies SET title_id = :title_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("title_id", title_id)
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
