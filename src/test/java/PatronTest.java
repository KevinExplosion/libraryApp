import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patron.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfPatronsAreTheSame() {
    Patron firstPatron = new Patron("Bob Smith", "3/2/2016", 2);
    Patron secondPatron = new Patron("Bob Smith", "3/2/2016", 2);
    assertTrue(firstPatron.equals(secondPatron));
  }

  @Test
  public void save_addsInstaceOfPatronToDatabase() {
    Patron newPatron = new Patron("Bob Smith", "3/2/2016", 2);
    newPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertTrue(newPatron.equals(savedPatron));
  }

  @Test
  public void save_assignsIdToObject() {
    Patron newPatron = new Patron("Bob Smith", "3/2/2016", 2);
    newPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertEquals(newPatron.getId(), savedPatron.getId());
  }

  @Test
  public void find_locatesAllInstancesOfPatronInDatabaseUsingID() {
    Patron newPatron = new Patron("Bob Smith", "3/2/2016", 2);
    newPatron.save();
    Patron savedPatron = Patron.find(newPatron.getId());
    assertTrue(newPatron.equals(savedPatron));
  }

  @Test
  public void updateName_updatesNameInDatabase() {
    Patron newPatron = new Patron("Bob Smith", "3/2/2016", 2);
    newPatron.save();
    newPatron.updateName("John");
    assertEquals(Patron.all().get(0).getName(), ("John"));
  }

  @Test
  public void updateDueDate_updatesDueDateInDatabase() {
    Patron newPatron = new Patron("Bob Smith", "3/2/2016", 2);
    newPatron.save();
    newPatron.updateDueDate("3/15/2016");
    assertEquals(Patron.all().get(0).getDueDate(), ("3/15/2016"));
  }

  @Test
  public void updateIdTitleAuthor_updatesIdTitleAuthorInDatabase() {
    Patron newPatron = new Patron("Bob Smith", "3/2/2016", 2);
    newPatron.save();
    newPatron.updateIdTitleAuthor(3);
    assertEquals(Patron.all().get(0).getIdTitleAuthor(), 3);
  }

  @Test
  public void deletePatron_deletePatronObject() {
    Patron newPatron = new Patron("Bob Smith", "3/2/2016", 2);
    newPatron.save();
    newPatron.delete();
    assertEquals(Patron.all().size(), 0);
  }


}
