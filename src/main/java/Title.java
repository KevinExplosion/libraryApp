import org.sql2o.*;
import java.util.List;

public class Title {
  private int id;
  private String title;
  private int copy_id;

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public int getCopyId() {
    return copy_id;
  }

  public Title (String title, int copy_id) {
    this.title = title;
    this.copy_id = copy_id;
  }

  public static List<Title> all() {
    String sql = "SELECT id, title, copy_id FROM title ORDER BY title";
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
      return this.getTitle().equals(newTitle.getTitle()) &&
             this.getCopyId() == newTitle.getCopyId();
    }
  }

  public void save() {
    String sql = "INSERT INTO title (title, copy_id) VALUES (:title, :copy_id)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("title", title)
          .executeUpdate()
          .getKey();
    }
  }

  public static Title find(int id) {
    String sql = "SELECT id, title, copy_id FROM title WHERE id = :id";
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

  public void updateCopyId(String copy_id) {
    String sql = "UPDATE title SET copy_id = :copy_id WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("copy_id", copy_id)
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

  public void addAuthor (Author author) {
    String sql = "INSERT INTO title_author (id_title, id_author) VALUES (:id_title, :id_author)";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("id_title", this.getId())
         .addParameter("id_author", author.getId())
         .executeUpdate();
    }
  }

  public List<Author> getAuthors() {
    try(Connection con = DB.sql2o.open()) {

      String sql = "SELECT author.* FROM title " +
      "JOIN title_author ON (title.id = title_author.id_title) " +
      "JOIN author ON (title_author.id_author = author.id) " +
      "WHERE title.id = :id";
      List<Author> authors = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Author.class);
      return authors;
    }
  }
}
