package memberManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterMemberDAO {
  private Connection con;

  public RegisterMemberDAO() throws DAOException{
    getConnection();
  }

  /* 会員情報表示用？ */
  public MemberBean showMemberAll(String email) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
    	String sql = "SELECT member_id,member_name,member_address,member_tel,member_email,member_birthday FROM member WHERE member_email=?";
        st = con.prepareStatement(sql);
        st.setString(1,email);
        rs = st.executeQuery();
        //SELECTで取った項目のnull判定。
        if(rs.next()){//nullではない
        	int id = rs.getInt("member_id");
        	String name = rs.getString("member_name");
        	String address = rs.getString("member_address");
        	String tel = rs.getString("member_tel");
        	String birthday = rs.getString("member_birthday");
        	MemberBean bean = new MemberBean(id,name,address,tel,email,birthday);

        	// List<CategoryBean> list = new ArrayList<CategoryBean>();
            // while(rs.next()){
            //   int code = rs.getInt("code");
            //   String name = rs.getString("name");
            //   CategoryBean bean = new CategoryBean(code,name);
            //   list.add(bean);
            // }

        	System.out.println("SELECTでヒットしました");
        	return bean;
        }else{//null
        	System.out.println("SELECTでヒットしませんでした");
        	return null;
        }

    }catch(Exception e){
      e.printStackTrace();
      throw new DAOException("リソースの開放に失敗しました");
    }finally{
      try{
        if(rs!=null) rs.close();
        if(st!=null) st.close();
        close();
      }catch(Exception e){
        throw new DAOException("リソースの開放に失敗しました");
      }
    }
  }

  /* メールアドレスが既に存在するかチェック */
  public Boolean isExistEmail(String email) throws DAOException{
    if(con==null){
      getConnection();
    }

    PreparedStatement st = null;
    ResultSet rs = null;

    try{
        String sql = "SELECT EXISTS(SELECT * FROM member WHERE member_id=email)";
        st = con.prepareStatement(sql);
        st.setString(1,email);
        rs = st.executeQuery();
        //SELECTで取った項目のnull判定。
        if(rs.next()){//nullではない
        	Boolean resultIsExistEmail = rs.getBoolean(resultIsExistEmail);

          // List<CategoryBean> list = new ArrayList<CategoryBean>();
          // while(rs.next()){
          //   int code = rs.getInt("code");
          //   String name = rs.getString("name");
          //   CategoryBean bean = new CategoryBean(code,name);
          //   list.add(bean);
          // }
          System.out.println("SELECTでヒットしました");
          return resultIsExistEmail;
        }else{//null
          System.out.println("SELECTでヒットしませんでした");
          return null;
        }

    }catch(Exception e){
      e.printStackTrace();
      throw new DAOException("リソースの開放に失敗しました");
    }finally{
      try{
        if(rs!=null) rs.close();
        if(st!=null) st.close();
        close();
      }catch(Exception e){
        throw new DAOException("リソースの開放に失敗しました");
      }
    }
  }
  /* 会員情報登録用メソッド */
  public void resisterMember(String name, String address, String tel, String email, Date birthday) throws DAOException{
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

          MemberBean bean = new MemberBean(isbn,name,categoryCode,author,publisher,publishedAt);

          // List<CategoryBean> list = new ArrayList<CategoryBean>();
          // while(rs.next()){
          //   int code = rs.getInt("code");
          //   String name = rs.getString("name");
          //   CategoryBean bean = new CategoryBean(code,name);
          //   list.add(bean);
          // }
          System.out.println("SELECTでヒットしました");
          return bean;
        }else{//null
          System.out.println("SELECTでヒットしませんでした");
          return null;
        }

    }catch(Exception e){
      e.printStackTrace();
      throw new DAOException("リソースの開放に失敗しました");
    }finally{
      try{
        if(rs!=null) rs.close();
        if(st!=null) st.close();
        close();
      }catch(Exception e){
        throw new DAOException("リソースの開放に失敗しました");
      }
    }
  }

  /* DB接続用メソッド */
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
