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
	/* 会員情報表示用、使い道未定 */
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
	/* メールアドレスが既に存在するか判定用、使い道未定 */
	public Boolean isExistEmail(String email) throws DAOException{
		if(con==null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			//emailがレコード内に存在するか判定
			String sql = "SELECT * FROM member WHERE member_id=?";
			st = con.prepareStatement(sql);
			st.setString(1,email);
			rs = st.executeQuery();
			//SELECTで取った項目のnull判定。
			if(rs.next()){//nullではない
				Boolean resultIsExistEmail = true;
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
	/* 会員情報の新規登録用 */
	public void registerMember(MemberBean bean) throws DAOException{
		if(con==null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			//職員ではなく利用者の登録（is_staff=0）
			//職員の登録はどうしますか？
			String sql = "INSERT INTO member(member_name, member_address, member_tel, member_email, member_password, "
			+ "member_birthday, joined_at, canceled_at, is_staff, created_at, updated_at, deleted_at) "
			+ "VALUES(?,?,?,?,DEFAULT,TO_DATE(?,'YYYY-MM-DD'),DEFAULT, null, '0', DEFAULT, DEFAULT, DEFAULT) ";
			st = con.prepareStatement(sql);
			st.setString(1,bean.getName());
			st.setString(2,bean.getAddress());
			st.setString(3,bean.getTel());
			st.setString(4,bean.getEmail());
			st.setString(5,bean.getBirthday());

			st.executeUpdate();
			st.close();
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
	//新メソッド：最新のレコード一行取ってくる用
	public MemberBean getLatestMember() throws DAOException{
		if(con==null){
			getConnection();
		}
		MemberBean bean = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//DBからmember_idを見て最新の一行を取ってくる
			String sql = "SELECT * FROM member ORDER BY member_id DESC LIMIT 1";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			//SELECTで取った項目のnull判定。
			if(rs.next()){//nullではない
				int id = rs.getInt("member_id");
				String name = rs.getString("member_name");
				String address = rs.getString("member_address");
				String tel = rs.getString("member_tel");
				String email = rs.getString("member_email");
				String birthday = rs.getString("member_birthday");
				bean = new MemberBean(id,name,address,tel,email,birthday);
			}
			return bean;
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