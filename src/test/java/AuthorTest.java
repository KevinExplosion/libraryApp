import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Author.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfAuthorsAreTheSame() {
    Author firstAuthor = new Author("Dr. Suess");
    Author secondAuthor = new Author("Dr. Suess");
    assertTrue(firstAuthor.equals(secondAuthor));
  }

  @Test
  public void save_addsInstaceOfAuthorToDatabase() {
    Author newAuthor = new Author("Dr. Suess");
    newAuthor.save();
    Author savedAuthor = Author.all().get(0);
    assertTrue(newAuthor.equals(savedAuthor));
  }

  @Test
  public void save_assignsIdToObject() {
    Author newAuthor = new Author("Dr. Suess");
    newAuthor.save();
    Author savedAuthor = Author.all().get(0);
    assertEquals(newAuthor.getId(), savedAuthor.getId());
  }

  @Test
  public void find_locatesAllInstancesOfAuthorInDatabaseUsingID() {
    Author newAuthor = new Author("Dr. Suess");
    newAuthor.save();
    Author savedAuthor = Author.find(newAuthor.getId());
    assertTrue(newAuthor.equals(savedAuthor));
  }

  @Test
  public void updateAuthor_updatesAuthorInDatabase() {
    Author newAuthor = new Author("Dr. Suess");
    newAuthor.save();
    newAuthor.updateAuthor("Theo LeSieg");
    assertEquals(Author.all().get(0).getAuthor(), ("Theo LeSieg"));
  }

  @Test
  public void deleteAuthor_deleteAuthorObject() {
    Author newAuthor = new Author("Dr. Suess");
    newAuthor.save();
    newAuthor.delete();
    assertEquals(Author.all().size(), 0);
  }
}
