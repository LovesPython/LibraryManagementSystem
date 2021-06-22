package documentManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDocumentDAO {
  private Connection con;

  public RegisterDocumentDAO() throws DAOException{
    getConnection();
  }

  public DocumentCatalogBean findCatalogByISBN(String isbn) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "SELECT isbn_no,document_name,category_code,author,publisher,published_at FROM document_catalog WHERE isbn_no=?";
        st = con.prepareStatement(sql);
        st.setString(1,isbn);
        rs = st.executeQuery();
        //SELECTで取った項目のnull判定。
        if(rs.next()){//nullではない
          String name = rs.getString("document_name");
          String categoryCode = rs.getString("category_code");
          String author = rs.getString("author");
          String publisher = rs.getString("publisher");
          String publishedAt = rs.getString("published_at");
          String documentId = "";
          //ここからISBNをもとに資料台帳の資料Idを取ってくる
          sql = "SELECT document_id FROM document_ledger WHERE isbn_no=?";
          st = con.prepareStatement(sql);
          st.setString(1,isbn);
          rs = st.executeQuery();
          while(rs.next()){
            String tmp = String.valueOf(rs.getInt("document_id"));
            documentId = documentId + tmp + ",";
          }
          documentId = documentId.substring(0,documentId.length()-1);
          DocumentCatalogBean bean = new DocumentCatalogBean(isbn,name,categoryCode,author,publisher,publishedAt,documentId);
          System.out.println("SELECTでヒットしました");
          return bean;
        }else{//null
          System.out.println("SELECTでヒットしませんでした");
          DocumentCatalogBean bean = new DocumentCatalogBean(null,"null",null,null,null,null,null);
          return bean;
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

  public void registerDocumentCatalog(DocumentCatalogBean bean) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "INSERT INTO document_catalog VALUES(?, ?, ?, ?, ?, TO_DATE(?,'YY-MM-DD'), DEFAULT, DEFAULT, DEFAULT);";
        st = con.prepareStatement(sql);
        st.setString(1,bean.getIsbnNo());
        st.setString(2,bean.getName());
        st.setInt(3,Integer.valueOf(bean.getCategoryCode()));
        st.setString(4,bean.getAuthor());
        st.setString(5,bean.getPublisher());
        st.setString(6,bean.getPublishDate());
        st.executeUpdate();
        st.close();
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

  public void registerDocumentLedger(DocumentCatalogBean bean) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "INSERT INTO document_ledger(isbn_no, added_at, discarded_at, note, created_at, updated_at, deleted_at) VALUES(?, DEFAULT, NULL, NULL, DEFAULT, DEFAULT, DEFAULT);";
        st = con.prepareStatement(sql);
        st.setString(1,bean.getIsbnNo());
        st.executeUpdate();
        st.close();
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

  public int getLatestDocumentId() throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "SELECT document_id FROM document_ledger ORDER BY document_id DESC LIMIT 1";
        st = con.prepareStatement(sql);
        rs = st.executeQuery();
        //SELECTで取った項目のnull判定。
        if(rs.next()){//nullではない
          int documentId = rs.getInt("document_id");
          return documentId;
        }else{//null
          System.out.println("SELECTでヒットしませんでした");
          return -1;
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
