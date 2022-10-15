package nl.strohalm.cyclos.mfs.models.accounts;

import nl.strohalm.cyclos.mfs.models.transactions.Response;

import java.io.Serializable;
import java.util.List;

public class WalletStatementResp extends Response implements Serializable {
  private Integer currentPage;
  private Integer pageSize;
  private Integer totalCount;
  private List<WalletStatementDetail> walletStatementDetailList;

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Integer totalCount) {
    this.totalCount = totalCount;
  }

  public List<WalletStatementDetail> getWalletStatementDetailList() {
    return walletStatementDetailList;
  }

  public void setWalletStatementDetailList(List<WalletStatementDetail> walletStatementDetailList) {
    this.walletStatementDetailList = walletStatementDetailList;
  }
}
