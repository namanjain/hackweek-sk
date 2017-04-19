package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SQAccessTokenResponse {

  @JsonProperty("access_token")
  public String accessToken;
  @JsonProperty("expires_at")
  public Integer expiresAt;
  @JsonProperty("token_type")
  public String tokenType;
  @JsonProperty("merchant_id")
  public String merchantId;

  public SQAccessTokenResponse() {}
}
