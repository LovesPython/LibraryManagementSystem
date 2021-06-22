package documentManager;

public class DocumentLedgerBean {
  private int id;
  private String name;
  private String isbnNo;
  private String addDate;
  private String discardedDate;
  private String createdDate;
  private String updateDate;
  private String deleteDate;
  private String note;

  public DocumentLedgerBean(){

  }

  public DocumentLedgerBean(int id,String isbnNo,String name,String addDate,String discardedDate,String note){
    this.id = id;
    this.isbnNo = isbnNo;
    this.name = name;
    this.addDate = addDate;
    this.discardedDate = discardedDate;
    this.setNote(note);
  }

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getIsbnNo() {
    return isbnNo;
  }
  public void setIsbnNo(String isbnNo) {
    this.isbnNo = isbnNo;
  }
  public String getAddDate() {
    return addDate;
  }
  public void setAddDate(String addDate) {
    this.addDate = addDate;
  }
  public String getDiscardedDate() {
    return discardedDate;
  }
  public void setDiscardedDate(String discardedDate) {
    this.discardedDate = discardedDate;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  public String getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }
  public String getDeleteDate() {
    return deleteDate;
  }
  public void setDeleteDate(String deleteDate) {
    this.deleteDate = deleteDate;
  }

public String getNote() {
	return note;
}

public void setNote(String note) {
	this.note = note;
}

}
