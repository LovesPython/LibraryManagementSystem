package lendRet;

import java.io.Serializable;

public class LendingLedgerBean implements Serializable{
	private int memberId;
	private int documentId;
	private String lentDate;
	private String returnDeadline;
	private String returnedDate;

	public LendingLedgerBean() {

	}
	public LendingLedgerBean(int memberId,int documentId,String lentDate,String returnDeadline) {
		this.memberId=memberId;
		this.documentId=documentId;
		this.lentDate=lentDate;
		this.returnDeadline=returnDeadline;
	}
	public LendingLedgerBean(String returnDeadline) {
		this.returnDeadline=returnDeadline;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public String getLentDate() {
		return lentDate;
	}
	public void setLentDate(String lentDate) {
		this.lentDate = lentDate;
	}
	public String getReturnDeadline() {
		return returnDeadline;
	}
	public void setReturnDeadline(String returnDeadline) {
		this.returnDeadline = returnDeadline;
	}
	public String getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

}
