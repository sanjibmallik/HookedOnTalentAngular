package com.accion.recruitment.beans;

/**
 * Generic Response Bean
 * 
 * @author Manas
 *
 */
public class RestReponse {

  private int statusCode = 0;

  private String message = "SUCCESS";

  private Object response;

  public RestReponse() {
    super();
  }

  public RestReponse(final Object response) {
    super();
    this.response = response;
  }

  public RestReponse(final int statusCode, final String message, final Object response) {
    super();
    this.statusCode = statusCode;
    this.message = message;
    this.response = response;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(final int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public Object getResponse() {
    return response;
  }

  public void setResponse(final Object response) {
    this.response = response;
  }

}
