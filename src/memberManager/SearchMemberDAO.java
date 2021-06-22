package memberManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchMemberDAO {
	private Connection con;
	public SearchMemberDAO() throws DAOException{
		getConnection();
	}
	public MemberBean findMemberByEmail(String email) throws DAOException{
		if(con==null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM member WHERE member_email=?";
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
				String addedAt = rs.getString("joined_at");
				String deletedAt = rs.getString("canceled_at");

				MemberBean bean = new MemberBean(id,name,address,tel,email,birthday,addedAt,deletedAt);
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

	/* 更新・削除のための会員検索用：会員IDで検索 */
	public MemberBean findMemberById(int memberId) throws DAOException{
		if(con==null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			String sql = "SELECT * FROM member WHERE member_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1,memberId);
			rs = st.executeQuery();
			//SELECTで取った項目のnull判定。
			if(rs.next()){//nullではない
				String name = rs.getString("member_name");
				String address = rs.getString("member_address");
				String tel = rs.getString("member_tel");
				String email = rs.getString("member_email");
				String birthday = rs.getString("member_birthday");
				String addedAt = rs.getString("joined_at");
				String deletedAt = rs.getString("canceled_at");

				MemberBean bean = new MemberBean(memberId,name,address,tel,email,birthday,addedAt,deletedAt);
				System.out.println("memberId=" + memberId);
				System.out.println("addedAt=" + addedAt);
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