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
}
