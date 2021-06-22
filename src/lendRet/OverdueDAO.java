package lendRet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OverdueDAO {
	private Connection con;

	public OverdueDAO() throws DAOException{
		getConnection();
	}

  /* 延滞者の会員ID取得*/
	private Set<Integer> getMemberIdFromLendingLedger() throws DAOException{
		PreparedStatement st=null;
		ResultSet rs=null;
		try {

			String sql="SELECT * FROM lending_ledger WHERE  returned_at IS NULL AND return_deadline <= TO_DATE(?,'YYYY-mm-dd') ORDER BY return_deadline DESC";
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.MONTH, 1);
			String todayStr=String.format("%04d-%02d-%02d",cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));

			st=con.prepareStatement(sql);
			st.setString(1,todayStr);

			rs=st.executeQuery();

			Set<Integer> set=new HashSet<Integer>();
			while(rs.next()) {
				int id=rs.getInt("member_id");

				set.add(id);
			}
			return set;
		}catch(Exception e) {
			e.printStackTrace();
			throw new DAOException("レコードの取得に失敗しました。");
		}finally {
			try {
				if(rs !=null) rs.close();
				if(st != null) st.close();

			}catch(Exception e){
				throw new DAOException("リソースの開放に失敗しました。");
			}
		}
	}

	/*延滞者の管理*/
	public List<MemberBean> getMembersByMemberId() throws DAOException{
		if (con==null)
			getConnection();

		PreparedStatement st=null;
		ResultSet rs=null;
		try {

			String sql="SELECT * FROM member WHERE member_id=?";

			st=con.prepareStatement(sql);

			Set<Integer> set=getMemberIdFromLendingLedger();
			List<MemberBean> list=new ArrayList<MemberBean>();
			for (int id : set){

				st.setInt(1,id);

				rs=st.executeQuery();

				if(rs.next()) {
					int memberId=rs.getInt("member_id");
					String name=rs.getString("member_name");
					String address=rs.getString("member_address");
					String tel=rs.getString("member_tel");
					String email=rs.getString("member_email");
					String birthday=rs.getString("member_birthday");

					MemberBean bean=new MemberBean(memberId,name,address,tel,email,birthday);
					list.add(bean);
				}

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
