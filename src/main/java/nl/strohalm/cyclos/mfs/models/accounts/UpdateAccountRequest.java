package nl.strohalm.cyclos.mfs.models.accounts;

import com.google.common.base.MoreObjects;

import nl.strohalm.cyclos.webservices.model.RegistrationFieldValueVO;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;


public class UpdateAccountRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private String walletNo;

  @NotNull
  private String name;

  private List<RegistrationFieldValueVO> fields;

  public String getWalletNo() {
    return walletNo;
  }

  public void setWalletNo(String walletNo) {
    this.walletNo = walletNo;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RegistrationFieldValueVO> getFields() {
    return fields;
  }

  public void setFields(List<RegistrationFieldValueVO> fields) {
    this.fields = fields;
  }

@Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("walletNo", walletNo)
        .add("name", name)
        .toString();
  }
}