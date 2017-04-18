import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import models.AccessTokenResponse;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class Import implements TemplateViewRoute {
  private String accessTokenEndpoint = "https://cloud.merchantos.com/oauth/access_token.php";
  private String baseURL = "https://api.merchantos.com/API/Account/1/";
  private String clientId = "Dev-Roshan";
  private String clientSecret = "b113dd1e-244b-11e7-93ae-92361f002671";


  @Override public ModelAndView handle(Request request, Response response) {
    String temporaryCode = request.queryParams("code");
    if (temporaryCode != null && !temporaryCode.isEmpty() && !temporaryCode.equals("")) {
      try {
        String accessToken = getAccessToken(temporaryCode);
        System.out.println(accessToken);
        getItems(accessToken);
        // store this access token somewhere
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    Map<String, Object> attributes = new HashMap<>();
    attributes.put("message", "Hello World!");

    return new ModelAndView(attributes, "import.ftl");
  }

  private String getAccessToken(String temporaryCode) throws IOException {
    Client client = Client.create();

    WebResource webResource = client.resource(accessTokenEndpoint);

    JsonObject request = new JsonObject();
    request.addProperty("client_id", clientId);
    request.addProperty("client_secret", clientSecret);
    request.addProperty("code", temporaryCode);
    request.addProperty("grant_type", "authorization_code");

    ClientResponse response = webResource
        .type(MediaType.APPLICATION_JSON)
        .post(ClientResponse.class, request.toString());

    if (response.getStatus() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + response.getStatus() + response.toString());
    }

    String respString = response.getEntity(String.class);
    ObjectMapper mapper = new ObjectMapper();
    AccessTokenResponse resp = mapper.readValue(respString, AccessTokenResponse.class);
    return resp.accessToken;
  }

  private void getItems(String accessToken) {
    Client client = Client.create();

    WebResource webResource = client.resource(baseURL + "Item");

    ClientResponse response = webResource
        .type(MediaType.APPLICATION_JSON)
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
        .get(ClientResponse.class);

    System.out.println(response.toString());
  }
}
