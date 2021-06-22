package memberManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			response.setContentType("text/html;charset=UTF-8");
			String action = request.getParameter("action");

			if(action==null || action.length()==0 || action.equals("top")){
				gotoPage(request,response,"/menu.jsp");
			}else if(action.equals("registerConfirm")){
				//会員情報の確認用
				HttpSession session = request.getSession(true);
				MemberBean bean = new MemberBean();

				//パラメータの取得
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String birthday = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("date");

				//beanに値を渡す
				bean.setName(name);
				bean.setAddress(address);
				bean.setTel(tel);
				bean.setEmail(email);
				bean.setBirthday(birthday);

				session.setAttribute("member", bean);

				gotoPage(request, response, "/memberManager/confirmNewMember.jsp");
			}else if(action.equals("register")){
				//DBにレコードを登録する用
				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				RegisterMemberDAO dao = new RegisterMemberDAO();
				//DAOのINSERTメソッド
				dao.registerMember(bean);

				//DAOの一行取ってくるSELECTメソッド
				session.setAttribute("member", dao.getLatestMember());

				gotoPage(request,response,"/memberManager/registerMemberComplete.jsp");
			}else if(action.equals("search")){
				//更新削除のための検索用
				HttpSession session = request.getSession(true);
				MemberBean bean = new MemberBean();
				SearchMemberDAO dao = new SearchMemberDAO();

				//パラメータ取得：セレクトボックス
				String inputType = request.getParameter("type");
				//パラメータ取得：テキストボックス
				String submitText = request.getParameter("submitText");

				if(submitText == null){
					System.out.println("おかしな入力値");
					request.setAttribute("message","正しい会員IDもしくはメールアドレスを入力してください");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}else if(inputType.equals("memberId")) {
					//入力値が会員IDだった場合
					int memberId = Integer.parseInt(submitText);
					bean = dao.findMemberById(memberId);
					session.setAttribute("member", bean);
					gotoPage(request,response,"/memberManager/searchMemberResult.jsp");
				}else {
					//入力値がメールアドレスだった場合
					String email = submitText;
					bean = dao.findMemberByEmail(email);
					session.setAttribute("member", bean);
					gotoPage(request,response,"/memberManager/searchMemberResult.jsp");
				}
			}else if(action.equals("verdictDeletableMember")) {
				//退会可否の判定用
				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				DeleteMemberDAO dao = new DeleteMemberDAO();

				int memberId = bean.getId();

				Boolean flag = dao.isDeletable(memberId);
				if(flag) {
					//退会不可
					gotoPage(request,response,"/memberManager/showCantDeleteMember.jsp");
				}else {
					//退会可
					gotoPage(request,response,"/memberManager/canDeleteMember.jsp");
				}
			}else if(action.equals("deleteMember")) {
				//退会処理用
				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				DeleteMemberDAO dao = new DeleteMemberDAO();

				int memberId = bean.getId();
				dao.deleteMember(memberId);
				gotoPage(request,response,"/memberManager/deleteMemberComplete.jsp");

			}else if(action.equals("forwardToUpdate")) {
				//更新用ページに飛ばす用
				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				String birthday = bean.getBirthday();

				//birthdayをyear, month, dateに分ける処理→更新用のテキストボックスに表示するため
				String[] birthArray = birthday.split("-");
				List<String> birthList = new ArrayList<String>();

				for(String b : birthArray) {
					birthList.add(b);
				}
				session.setAttribute("member", bean);
				session.setAttribute("birthday", birthList);
				gotoPage(request, response, "/memberManager/memberUpdate.jsp");

			}else if(action.equals("confirmMemberUpdate")) {
				//会員情報の更新の確認用。Beanに入れて表示するだけ
				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");

				//パラメータの取得（空っぽでも）
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");
				String birthday = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("date");

				//idはそのまま
				bean.getId();

				//空文字チェック。空なら元の値をセットする
				if(name.equals("")) bean.setName(bean.getName());
				else bean.setName(name);

				if(address.equals("")) bean.setAddress(bean.getAddress());
				else bean.setAddress(address);

				if(tel.equals("")) bean.setTel(bean.getTel());
				else bean.setTel(tel);

				if(email.equals("")) bean.setEmail(bean.getEmail());
				else bean.setEmail(email);

				if(birthday.equals("")) bean.setBirthday(bean.getBirthday());
				else bean.setBirthday(birthday);

				session.setAttribute("member", bean);
				gotoPage(request, response, "/memberManager/confirmMemberUpdate.jsp");

			}else if(action.equals("registerUpdate")) {
				//更新用、SQL文実行
				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				UpdateMemberDAO dao = new UpdateMemberDAO();

				dao.updateMember(bean);
				gotoPage(request,response,"/memberManager/updateMemberComplete.jsp");
			}


		}catch(IOException e){
			e.printStackTrace();
			request.setAttribute("message","内部エラーが発生しました");
			gotoPage(request,response,"/errInternal.jsp");
		}catch (DAOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void gotoPage(HttpServletRequest request,HttpServletResponse response,String page) throws ServletException,IOException{
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request,response);
	}
}
