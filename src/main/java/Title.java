import org.sql2o.*;
import java.util.List;

public class Title {
  private int id;
  private String title;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public Title (String title) {
    this.title = title;
  }

  public static List<Title> all() {
    String sql = "SELECT id, title FROM title";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Title.class);
    }
  }

  @Override
  public boolean equals(Object otherTitle) {
    if(!(otherTitle instanceof Title)) {
      return false;
    } else {
      Title newTitle = (Title) otherTitle;
      return this.getTitle().equals(newTitle.getTitle());
    }
  }

  public void save() {
    String sql = "INSERT INTO title (title) VALUES (:title)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("title", title)
          .executeUpdate()
          .getKey();
    }
  }

  public static Title find(int id) {
    String sql = "SELECT id, title FROM title WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Title title = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Title.class);
      return title;
    }
  }

  public void updateTitle(String title) {
    String sql = "UPDATE title SET title = :title WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("title", title)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateDueDate(String due_date) {
    String sql = "UPDATE title SET due_date = :due_date WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("due_date", due_date)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateIdTitleAuthor(int id_title_author) {
    String sql = "UPDATE title SET id_title_author = :id_title_author WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id_title_author", id_title_author)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM title WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
       .addParameter("id", id)
       .executeUpdate();
    }
  }
}