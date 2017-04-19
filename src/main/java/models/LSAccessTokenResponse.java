package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LSAccessTokenResponse {

  @JsonProperty("access_token")
  public String accessToken;
  @JsonProperty("expires_in")
  public Integer expiresIn;
  @JsonProperty("token_type")
  public String tokenType;
  @JsonProperty("scope")
  public String scope;
  @JsonProperty("refresh_token")
  public String refreshToken;

  public LSAccessTokenResponse() {}
}
