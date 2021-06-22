package memberManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateMemberDAO {
	private Connection con;
	public UpdateMemberDAO() throws DAOException{
		getConnection();
	}

	/* 会員情報の更新用 */
	public void updateMember(MemberBean bean) throws DAOException{
		if(con==null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			String sql = "UPDATE member SET member_name=?, member_address=?, member_tel=?,"
					+ "member_email=?, member_birthday=TO_DATE(?,'YYYY-MM-DD') WHERE member_id=?";
			st = con.prepareStatement(sql);
			st.setString(1,bean.getName());
			st.setString(2,bean.getAddress());
			st.setString(3,bean.getTel());
			st.setString(4,bean.getEmail());
			st.setString(5,bean.getBirthday());
			st.setInt(6,bean.getId());
			st.executeUpdate();
			System.out.println("会員情報を更新しました");
			st.close();

			//DBの更新日時の更新（必須）
			String sql2 = "UPDATE member SET updated_at=CURRENT_TIMESTAMP WHERE member_id=?";
			st = con.prepareStatement(sql2);
			st.setInt(1,bean.getId());
			st.executeUpdate();
			System.out.println("更新日時更新しました");
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