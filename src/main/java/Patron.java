import org.sql2o.*;
import java.util.List;

public class Patron {
  private int id;
  private String due_date;
  private int id_title_author;

  public String getDueDate() {
    return due_date;
  }

  public int getIdTitleAuthor() {
    return id_title_author;
  }

  public Patron (String due_date, int id_title_author) {
    this.due_date = due_date;
    this.id_title_author = id_title_author;
  }

  public static List<Patron> all() {
    String sql = "SELECT id, due_date, id_title_author FROM patron";
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
      return this.getDueDate().equals(newPatron.getDueDate()) &&
             this.getIdTitleAuthor() == newPatron.getIdTitleAuthor();
    }
  }
}
