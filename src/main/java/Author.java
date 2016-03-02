import org.sql2o.*;
import java.util.List;

public class Author {
  private int id;
  private String author;

  public int getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public Author (String author) {
    this.author = author;
  }

  public static List<Author> all() {
    String sql = "SELECT id, author FROM author";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  @Override
  public boolean equals(Object otherAuthor) {
    if(!(otherAuthor instanceof Author)) {
      return false;
    } else {
      Author newAuthor = (Author) otherAuthor;
      return this.getAuthor().equals(newAuthor.getAuthor());
    }
  }

  public void save() {
    String sql = "INSERT INTO author (author) VALUES (:author)";
    try (Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("author", author)
          .executeUpdate()
          .getKey();
    }
  }

  public static Author find(int id) {
    String sql = "SELECT id, author FROM author WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Author author = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Author.class);
      return author;
    }
  }

  public void updateAuthor(String author) {
    String sql = "UPDATE author SET author = :author WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("author", author)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM author WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
       .addParameter("id", id)
       .executeUpdate();
    }
  }
}
