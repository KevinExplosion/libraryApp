import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class TitleTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Title.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfTitlesAreTheSame() {
    Title firstTitle = new Title("Hop on Pop");
    Title secondTitle = new Title("Hop on Pop");
    assertTrue(firstTitle.equals(secondTitle));
  }

  @Test
  public void save_addsInstaceOfTitleToDatabase() {
    Title newTitle = new Title("Hop on Pop");
    newTitle.save();
    Title savedTitle = Title.all().get(0);
    assertTrue(newTitle.equals(savedTitle));
  }

  @Test
  public void save_assignsIdToObject() {
    Title newTitle = new Title("Hop on Pop");
    newTitle.save();
    Title savedTitle = Title.all().get(0);
    assertEquals(newTitle.getId(), savedTitle.getId());
  }

  @Test
  public void find_locatesAllInstancesOfTitleInDatabaseUsingID() {
    Title newTitle = new Title("Hop on Pop");
    newTitle.save();
    Title savedTitle = Title.find(newTitle.getId());
    assertTrue(newTitle.equals(savedTitle));
  }

  @Test
  public void updateTitle_updatesTitleInDatabase() {
    Title newTitle = new Title("Hop on Pop");
    newTitle.save();
    newTitle.updateTitle("Green Eggs and Ham");
    assertEquals(Title.all().get(0).getTitle(), ("Green Eggs and Ham"));
  }

  @Test
  public void deleteTitle_deleteTitleObject() {
    Title newTitle = new Title("Hop on Pop");
    newTitle.save();
    newTitle.delete();
    assertEquals(Title.all().size(), 0);
  }
}
