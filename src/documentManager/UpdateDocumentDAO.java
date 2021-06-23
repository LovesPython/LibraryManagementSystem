package documentManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDocumentDAO {
  private Connection con;

  public UpdateDocumentDAO() throws DAOException{
    getConnection();
  }

  /*引数のbeanをもとに目録のDBを更新する*/
  public void updateCatalog(DocumentCatalogBean bean) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
      try{//資料名が変更されてISBNが変更されていない場合
        String sql = "UPDATE document_catalog SET isbn_no=?,document_name=?,category_code=?,author=?,publisher=?,published_at=TO_DATE(?,'YY-MM-DD'),updated_at=DEFAULT WHERE isbn_no=?";
        st = con.prepareStatement(sql);
        st.setString(1,bean.getIsbnNo());
        st.setString(2,bean.getName());
        st.setInt(3,Integer.valueOf(bean.getCategoryCode()));
        st.setString(4,bean.getAuthor());
        st.setString(5,bean.getPublisher());
        st.setString(6,bean.getPublishDate());
        st.setString(7,bean.getIsbnNo());
        st.executeUpdate();
        st.close();
        System.out.println("isbnは変更されてない");
      }catch(Exception e){//ISBNが変更されて資料名が変更されていない場合
        System.out.println("isbnは変更されていたのでこちら");
        String sql = "UPDATE document_catalog SET isbn_no=?,document_name=?,category_code=?,author=?,publisher=?,published_at=TO_DATE(?,'YY-MM-DD'),updated_at=DEFAULT WHERE document_name=?";
        st = con.prepareStatement(sql);
        st.setString(1,bean.getIsbnNo());
        st.setString(2,bean.getName());
        st.setInt(3,Integer.valueOf(bean.getCategoryCode()));
        st.setString(4,bean.getAuthor());
        st.setString(5,bean.getPublisher());
        st.setString(6,bean.getPublishDate());
        st.setString(7,bean.getName());
        st.executeUpdate();
        st.close();
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

  /*引数のbeanをもとに目録のDBを更新する*/
  public void updateLedger(DocumentLedgerBean bean) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "UPDATE document_ledger SET added_at=TO_DATE(?,'YY-MM-DD'),discarded_at=TO_DATE(?,'YY-MM-DD'),note=?, updated_at=DEFAULT WHERE document_id=?";
        st = con.prepareStatement(sql);
        st.setString(1,bean.getAddDate());
        st.setString(2,bean.getDiscardedDate());
        st.setString(3,bean.getNote());
        st.setInt(4,bean.getId());
        st.executeUpdate();
        st.close();
        System.out.println("変更完了");
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

  /*引数のtargetId(資料ID)をもとに、台帳の削除日時をアップデートする*/
  public void deleteLedger(String targetId) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "UPDATE document_ledger SET discarded_at=CURRENT_DATE WHERE document_id=?";
        st = con.prepareStatement(sql);
        st.setInt(1,Integer.parseInt(targetId));
        st.executeUpdate();
        st.close();
        System.out.println("変更完了");
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
