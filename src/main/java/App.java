import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> { //HOME PAGE
      HashMap model = new HashMap();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/books", (request,response) -> { //BOOKS PAGE W/ TITLES AND AUTHORS
      HashMap model = new HashMap();
      model.put("titles", Title.all());
      model.put("authors", Author.all());
      model.put("template", "templates/books.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/title", (request, response) -> { //POSTS TITLES TO BOOKS PAGE
      HashMap model = new HashMap();
      String title = request.queryParams("newBookTitle");
      Title newTitle = new Title(title);
      newTitle.save();
      response.redirect("/books");
      return null;
    });

    post("/author", (request, response) -> { //POSTS AUTHORS TO BOOKS PAGE
      HashMap model = new HashMap();
      String author = request.queryParams("newAuthor");
      Author newAuthor = new Author(author);
      newAuthor.save();
      response.redirect("/books");
      return null;
    });


    get("/title/:id", (request, response) -> {
      HashMap model = new HashMap();
      int id = Integer.parseInt(request.params("id"));
      Title title = Title.find(id);
      model.put("title", title);
      model.put("allAuthors", Author.all());
      model.put("template", "templates/title.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/title/:id", (request, response) -> {
      HashMap model = new HashMap();
      int titleId = Integer.parseInt(request.queryParams("titleId"));
      int authorId = Integer.parseInt(request.queryParams("authorName"));
      Author author = Author.find(authorId);
      Title title = Title.find(titleId);
      title.addAuthor(author);
      response.redirect("/title/" + titleId);
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
