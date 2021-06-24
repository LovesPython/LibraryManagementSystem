package memberManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteMemberDAO {
	private Connection con;
	public DeleteMemberDAO() throws DAOException{
		getConnection();
	}
	/* メソッド：退会可能か判定用。貸出台帳を参照して貸出中資料がないか確認 */
	public Boolean isDeletable(int memberId) throws DAOException{
		if(con==null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			//該当レコードを一行だけ取ってくる
			//String sql = "SELECT * FROM lending_ledger WHERE member_id=? LIMIT 1";
			String sql = "SELECT * FROM lending_ledger WHERE member_id=? AND returned_at is NULL";
			st = con.prepareStatement(sql);
			st.setInt(1,memberId);
			rs = st.executeQuery();
			Boolean resultIsDeletable = true;

			//SELECTで取った項目のnull判定
			if(rs.next()){//nullではない＝貸出台帳に会員IDがある
				//返却済みなら退会可
				resultIsDeletable = false;
				// String isReturned = rs.getString("returned_at");
				// if(isReturned == null) resultIsDeletable = false;

				System.out.println("SELECTでヒットしました");
				return resultIsDeletable;
			}else{//null＝貸出台帳に会員IDがない→退会可
				System.out.println("SELECTでヒットしませんでした");
				return resultIsDeletable;
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

	/* メソッド：会員情報の削除用 */
	public void deleteMember(int memberId) throws DAOException{
		if(con==null){
			getConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			String sql = "UPDATE member SET canceled_at=CURRENT_DATE, updated_at=CURRENT_TIMESTAMP WHERE member_id=?";
			st = con.prepareStatement(sql);
			st.setInt(1,memberId);
			st.executeUpdate();
			System.out.println("削除しました");

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
