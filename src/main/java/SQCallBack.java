import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import models.SQAccessTokenResponse;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class SQCallBack implements TemplateViewRoute {
  private String accessTokenEndpoint = "https://connect.squareup.com/oauth2/token";
  private String baseURL = "https://connect.squareup.com";
  private String clientId = "sq0idp-Ux0S-9iMftQuozTkDpSjDw";
  private String clientSecret = "sq0csp-lBGGHNQmcQkAgLfa3x6W7jJj8SQ-Fx5Y0yQiCrUWM40";

  @Override public ModelAndView handle(Request request, Response response) {
    String temporaryCode = request.queryParams("code");
    if (temporaryCode != null && !temporaryCode.isEmpty() && !temporaryCode.equals("")) {
      try {
        String accessToken = getAccessToken(temporaryCode);
        System.out.println(accessToken);
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

    WebResource webResource = client
        .resource(accessTokenEndpoint)
        .queryParam("client_id", clientId)
        .queryParam("client_secret", clientSecret)
        .queryParam("code", temporaryCode);

    JsonObject request = new JsonObject();
    request.addProperty("client_id", clientId);
    request.addProperty("client_secret", clientSecret);
    request.addProperty("code", temporaryCode);

    ClientResponse response = webResource
        .type(MediaType.APPLICATION_JSON)
        .get(ClientResponse.class);

    if (response.getStatus() != 200) {
      throw new RuntimeException("Failed : HTTP error code : " + response.getStatus() + response.toString());
    }

    String respString = response.getEntity(String.class);
    ObjectMapper mapper = new ObjectMapper();
    SQAccessTokenResponse resp = mapper.readValue(respString, SQAccessTokenResponse.class);
    return resp.accessToken;
  }
}
