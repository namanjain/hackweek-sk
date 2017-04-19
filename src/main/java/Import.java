import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class Import implements TemplateViewRoute {
  @Override public ModelAndView handle(Request request, Response response) {
    Map<String, Object> attributes = new HashMap<>();
    attributes.put("message", "Hello World!");

    return new ModelAndView(attributes, "import.ftl");
  }
}
