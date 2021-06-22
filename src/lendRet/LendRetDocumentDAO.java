package lendRet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class LendRetDocumentDAO {
	private Connection con;

	public LendRetDocumentDAO() throws DAOException{
		getConnection();
	}

	/* 会員IDから貸出履歴を出す*/
	public List<LendingLedgerBean> findLendingLedgerByMemberId(int memberId) throws DAOException{
		PreparedStatement st=null;
		ResultSet rs=null;
		try {

			String sql="SELECT * FROM lending_ledger WHERE  returned_at IS NULL AND member_id=? ORDER BY return_deadline DESC";

			st=con.prepareStatement(sql);
			st.setInt(1,memberId);

			rs=st.executeQuery();

			List<LendingLedgerBean> list=new ArrayList<LendingLedgerBean>();
			while(rs.next()) {
				int lendingLedgerDocId=rs.getInt("document_id");
		        String lendingLedgerLentAt=rs.getString("lent_at");
		        String lendingLedgerReturnDeadline=rs.getString("return_deadline");

		        LendingLedgerBean bean =new LendingLedgerBean(memberId,lendingLedgerDocId,lendingLedgerLentAt,lendingLedgerReturnDeadline);
				list.add(bean);
			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}finally {
			try {
				if(rs !=null) rs.close();
				if(st != null) st.close();
        close();
			}catch(Exception e){
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

  /*返却期日の設定*/
  public LendingLedgerBean addDeadline(int documentId,int memberId) throws DAOException{
    if (con==null)
		getConnection();
    PreparedStatement st=null;
  	ResultSet rs=null;

	try {
		String sql="SELECT * FROM document_ledger WHERE document_id=?";

	    st=con.prepareStatement(sql);
	    st.setInt(1,documentId);

	    rs=st.executeQuery();

	    String isbnNo="";
	    if(rs.next()) {
	    	isbnNo=rs.getString("isbn_no");
	    }
	    rs.close();
	    st.close();

	    sql="SELECT * FROM document_catalog WHERE isbn_no=?";
	    st=con.prepareStatement(sql);
	    st.setString(1,isbnNo);

	    rs=st.executeQuery();

	    String publishedAt="";
	    if(rs.next()) {
	    	publishedAt=rs.getString("published_at");
	    }
	    rs.close();
	    st.close();

	    int publishedDate=Integer.parseInt(publishedAt.replace("-",""));

	    Calendar cal = Calendar.getInstance();

	    int month=cal.get(Calendar.MONTH)+1;
	    cal.add(Calendar.MONTH,1);

	    String todayStr=String.format("%04d%02d%02d",cal.get(Calendar.YEAR),month,cal.get(Calendar.DATE));
	    int today=Integer.parseInt(todayStr);

	    LendingLedgerBean bean=new LendingLedgerBean();
	    bean.setDocumentId(documentId);
	    bean.setMemberId(memberId);
	    bean.setLentDate(getCalString(cal));

	    String deadline;

	    /*新刊の時*/
	    if(today-publishedDate<=300){
	      cal.add(Calendar.DATE, 10);
	      deadline=getCalString(cal);
	      bean.setReturnDeadline(deadline);

	    /*新刊以外の時*/
	    }else{
	      cal.add(Calendar.DATE, 15);
	      deadline=getCalString(cal);
	      bean.setReturnDeadline(deadline);

	    }

	    /*
	    sql="INSERT INTO lending_ledger(member_id,document_id,return_deadline) VALUES(?,?,TO_Date(?,'YYYY-MM-DD'))";

		st=con.prepareStatement(sql);

		st.setInt(1,memberId);
		st.setInt(2,documentId);
		st.setString(3,deadline);

		st.executeUpdate();
		st.close();
		*/

	    return bean;

	}catch(Exception e) {
		e.printStackTrace();
		throw new DAOException("レコードの取得に失敗しました。");
	}finally {
		try {
			if(rs !=null) rs.close();
			if(st != null) st.close();
			close();
		}catch(Exception e){
			throw new DAOException("リソースの開放に失敗しました。");
		}
	}
  }

  /*貸出台帳に追記*/
	public void addLendingLedger(LendingLedgerBean bean) throws DAOException{
		if (con==null)
			getConnection();

		PreparedStatement st=null;
		try {
			String sql="INSERT INTO lending_ledger(member_id,document_id,return_deadline) VALUES(?,?,TO_Date(?,'YYYY-MM-DD'))";

			st=con.prepareStatement(sql);

			st.setInt(1,bean.getMemberId());
			st.setInt(2,bean.getDocumentId());
			st.setString(3,bean.getReturnDeadline());

			st.executeUpdate();
			st.close();

		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}finally {
			try {
				if(st != null) st.close();
				close();
			}catch(Exception e){
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

  /*返却処理*/
  public LendingLedgerBean deleteLendingLedger(int memberId,int documentId) throws DAOException{
	  if (con==null)
			getConnection();
	  PreparedStatement st=null;
	  ResultSet rs=null;
	try {
		String sql="SELECT * FROM lending_ledger WHERE  returned_at IS NULL AND member_id=? AND document_id=? ORDER BY return_deadline DESC";

		st=con.prepareStatement(sql);
		st.setInt(1,memberId);
		st.setInt(2, documentId);

		rs=st.executeQuery();

		String lendingLedgerLentAt="";
		String lendingLedgerReturnDeadline="";
		if(rs.next()) {
	        lendingLedgerLentAt=rs.getString("lent_at");
	        lendingLedgerReturnDeadline=rs.getString("return_deadline");


		}
		LendingLedgerBean bean =new LendingLedgerBean(memberId,documentId,lendingLedgerLentAt,lendingLedgerReturnDeadline);
		st.close();
		rs.close();

		/*今日の日付を取得する*/
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);

		sql="UPDATE lending_ledger SET returned_at=TO_Date(?,'YYYY-MM-DD') WHERE  member_id=? AND document_id=?";

		st=con.prepareStatement(sql);
		st.setString(1, getCalString(cal));
		st.setInt(2,memberId);
		st.setInt(3, documentId);

		st.executeUpdate();
		st.close();

		return bean;
	}catch(Exception e) {
		e.printStackTrace();
		throw new DAOException("レコードの取得に失敗しました。");
	}finally {
		try {
			if(rs !=null) rs.close();
			if(st != null) st.close();
  close();
		}catch(Exception e){
			throw new DAOException("リソースの開放に失敗しました。");
		}
	}
  }


  	/*カレンダーを年月日の文字列で取得*/
	private String  getCalString(Calendar cal){

		return String.format("%04d-%02d-%02d",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));

	}
	private void getConnection() throws DAOException{
	    try{
	      Class.forName("org.postgresql.Driver");

	      String url = "jdbc:postgresql:library";
	      String user = "libraryuser";
	      String pass = "himitu";

	      con = DriverManager.getConnection(url,user,pass);
	    }catch(Exception e){
	      e.printStackTrace();
	      throw new DAOException("接続に失敗しました");
	    }
	  }
	private void close() throws SQLException{
		if(con!= null) {
			con.close();
			con=null;
		}
	}
}