package documentManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDocumentDAO {
  private Connection con;

  public SearchDocumentDAO() throws DAOException{
    getConnection();
  }

  public List<DocumentLedgerBean> findLedgerByISBN(String isbn) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;
    List<DocumentLedgerBean> ledgerList = new ArrayList<DocumentLedgerBean>();
    DocumentLedgerBean bean;

    try{
        String sql = "SELECT document_id,isbn_no,added_at,discarded_at,note FROM document_ledger WHERE isbn_no=? ORDER BY document_id";
        st = con.prepareStatement(sql);
        st.setString(1,isbn);
        rs = st.executeQuery();
        //SELECTで取った項目のnull判定。
        while(rs.next()){//nullではない
          int documentId = rs.getInt("document_id");
          String isbnNo = rs.getString("isbn_no");
          String addedAt = rs.getString("added_at");
          String discardedAt = rs.getString("discarded_at");
          String note = rs.getString("note");

          bean = new DocumentLedgerBean(documentId,isbnNo,"null",addedAt,discardedAt,note);
          ledgerList.add(bean);
        }
        return ledgerList;
    }catch(Exception e){
      e.printStackTrace();
      throw new DAOException("リソースの快方に失敗しました");
    }finally{
      try{
        if(rs!=null) rs.close();
        if(st!=null) st.close();
        close();
      }catch(Exception e){
        throw new DAOException("リソースの快方に失敗しました");
      }
    }
  }

  public DocumentLedgerBean findLedgerById(String id) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;
    DocumentLedgerBean ledger;

    try{
        String sql = "SELECT document_id,isbn_no,added_at,discarded_at,note FROM document_ledger WHERE document_id=?";
        st = con.prepareStatement(sql);
        st.setInt(1,Integer.parseInt(id));
        rs = st.executeQuery();
        //SELECTで取った項目のnull判定。
        if(rs.next()){//nullではない
          int documentId = rs.getInt("document_id");
          String isbnNo = rs.getString("isbn_no");
          String addedAt = rs.getString("added_at");
          String discardedAt = rs.getString("discarded_at");
          String note = rs.getString("note");

          ledger = new DocumentLedgerBean(documentId,isbnNo,"null",addedAt,discardedAt,note);
          return ledger;
        }else{
          return null;
        }
    }catch(Exception e){
      e.printStackTrace();
      throw new DAOException("リソースの快方に失敗しました");
    }finally{
      try{
        if(rs!=null) rs.close();
        if(st!=null) st.close();
        close();
      }catch(Exception e){
        throw new DAOException("リソースの快方に失敗しました");
      }
    }
  }

  public String getNameByISBN(String isbnNo) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "SELECT document_name FROM document_catalog WHERE isbn_no=?";
        st = con.prepareStatement(sql);
        st.setString(1,isbnNo);
        rs = st.executeQuery();
        //SELECTで取った項目のnull判定。
        if(rs.next()){//nullではない
          String name = rs.getString("document_name");
          return name;
        }else{
          return null;
        }
    }catch(Exception e){
      e.printStackTrace();
      throw new DAOException("リソースの快方に失敗しました");
    }finally{
      try{
        if(rs!=null) rs.close();
        if(st!=null) st.close();
        close();
      }catch(Exception e){
        throw new DAOException("リソースの快方に失敗しました");
      }
    }
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
    if(con!=null){
      con.close();
      con = null;
    }
  }
}
