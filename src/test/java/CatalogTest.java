import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class CatalogTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Catalog.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfCatalogsAreTheSame() {
    Catalog firstCatalog = new Catalog(5, 2);
    Catalog secondCatalog = new Catalog(5, 2);
    assertTrue(firstCatalog.equals(secondCatalog));
  }

  @Test
  public void save_addsInstaceOfCatalogToDatabase() {
    Catalog newCatalog = new Catalog(5, 2);
    newCatalog.save();
    Catalog savedCatalog = Catalog.all().get(0);
    assertTrue(newCatalog.equals(savedCatalog));
  }

  @Test
  public void save_assignsIdToObject() {
    Catalog newCatalog = new Catalog(5, 2);
    newCatalog.save();
    Catalog savedCatalog = Catalog.all().get(0);
    assertEquals(newCatalog.getId(), savedCatalog.getId());
  }

  @Test
  public void find_locatesAllInstancesOfCatalogInDatabaseUsingID() {
    Catalog newCatalog = new Catalog(5, 2);
    newCatalog.save();
    Catalog savedCatalog = Catalog.find(newCatalog.getId());
    assertTrue(newCatalog.equals(savedCatalog));
  }

  @Test
  public void updateCopies_updatesCopiesInDatabase() {
    Catalog newCatalog = new Catalog(5, 2);
    newCatalog.save();
    newCatalog.updateCopies(7);
    assertEquals(Catalog.all().get(0).getCopies(), 7);
  }

  @Test
  public void updateIdTitleAuthor_updatesIdTitleAuthorInDatabase() {
    Catalog newCatalog = new Catalog(5, 2);
    newCatalog.save();
    newCatalog.updateIdTitleAuthor(3);
    assertEquals(Catalog.all().get(0).getIdTitleAuthor(), 3);
  }

  @Test
  public void deleteCatalog_deleteCatalogObject() {
    Catalog newCatalog = new Catalog(5, 2);
    newCatalog.save();
    newCatalog.delete();
    assertEquals(Catalog.all().size(), 0);
  }


}
