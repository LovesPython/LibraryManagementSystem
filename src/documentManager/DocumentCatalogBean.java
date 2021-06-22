package documentManager;
import java.io.Serializable;
public class DocumentCatalogBean {
  private String isbnNo;
  private String name;
  private String categoryCode;
  private String author;
  private String publisher;
  private String publishDate;
  private String documentId;
  private String addDate;
  private String updateDate;
  private String deleteDate;

  /*コンストラクタでどこまで初期セットするかは未定
    現在コンストラクタ引数6
  */
  public DocumentCatalogBean(String isbnNo,String name,String categoryCode,String author,String publisher,String publishDate,String documentId){
    this.isbnNo = isbnNo;
    this.name = name;
    this.categoryCode = categoryCode;
    this.author = author;
    this.publisher = publisher;
    this.publishDate = publishDate;
    this.documentId = documentId;
  }

public String getIsbnNo() {
	return isbnNo;
}

public void setIsbnNo(String isbnNo) {
	this.isbnNo = isbnNo;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDocumentId() {
	return documentId;
}

public void setDocumentId(String documentId) {
	this.documentId = documentId;
}

public String getCategoryCode() {
	return categoryCode;
}

public void setCategoryCode(String categoryCode) {
	this.categoryCode = categoryCode;
}

public String getAuthor() {
	return author;
}

public void setAuthor(String author) {
	this.author = author;
}

public String getPublisher() {
	return publisher;
}

public void setPublisher(String publisher) {
	this.publisher = publisher;
}

public String getPublishDate() {
	return publishDate;
}

public void setPublishDate(String publishDate) {
	this.publishDate = publishDate;
}

public String getAddDate() {
	return addDate;
}

public void setAddDate(String addDate) {
	this.addDate = addDate;
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

}
