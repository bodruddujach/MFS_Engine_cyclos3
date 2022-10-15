package nl.strohalm.cyclos.mfs.models.transactions;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class Response implements Serializable {

  private static final long serialVersionUID = 75188174246438207L;

  protected String status;
  protected String message;

  public Response() {
  }

  public Response(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("status", status)
        .add("description", message)
        .toString();
  }
}
