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
    Patron firstPatron = new Patron("3/2/2016", 2);
    Patron secondPatron = new Patron("3/2/2016", 2);
    assertTrue(firstPatron.equals(secondPatron));
  }
}
