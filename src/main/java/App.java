import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/books", (request,response) -> {
      HashMap model = new HashMap();
      model.put("titles", Title.all());
      model.put("authors", Author.all());
      model.put("template", "templates/books.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/title", (request, response) -> {
      HashMap model = new HashMap();
      String title = request.queryParams("newBookTitle");
      Title newTitle = new Title(title);
      newTitle.save();
      response.redirect("/books");
      return null;
    });

    post("/author", (request, response) -> {
      HashMap model = new HashMap();
      String author = request.queryParams("newAuthor");
      Author newAuthor = new Author(author);
      newAuthor.save();
      response.redirect("/books");
      return null;
    });
//
//     get("/result", (request, response) -> {
//       String textInput = request.queryParams("textInput");
//
//       //call business logic functions here
//       String result = textInput;
//
//       HashMap model = new HashMap();
//       model.put("template", "templates/output.vtl");
//       model.put("result", String.format(result));
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//       //additional pages would go here
  }
//
//   //public static 'Returntype' 'FuncName' (Paramtype param) {}  //first business logic function

}
