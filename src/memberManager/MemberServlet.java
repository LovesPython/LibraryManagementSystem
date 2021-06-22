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
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			String action = request.getParameter("action");

			if(action==null || action.length()==0 || action.equals("top")){
				gotoPage(request,response,"/menu.jsp");
			}else if(action.equals("registerConfirm")){
				//アクション：入力した会員情報の確認

				HttpSession session = request.getSession(true);
				MemberBean bean = new MemberBean();

				//パラメータの取得
				String name = request.getParameter("name");
				String address = request.getParameter("address");
				String tel = request.getParameter("tel");
				String email = request.getParameter("email");

				//yearとmonthとdateをいったん変数に入れる
				String year = request.getParameter("year");
				String month = request.getParameter("month");
				String date = request.getParameter("date");

				//生年月日を結合して1つの変数にする（DBのため）
				String birthday = year + "-" + month + "-" + date;

				//電話番号と誕生日が数字かどうかチェック
				Boolean isNumericTel = tel.matches("[+-]?\\d*(\\.\\d+)?");
				Boolean isNumericYear = year.matches("[+-]?\\d*(\\.\\d+)?");
				Boolean isNumericMonth = month.matches("[+-]?\\d*(\\.\\d+)?");
				Boolean isNumericDate = date.matches("[+-]?\\d*(\\.\\d+)?");

				//不正入力の監視（長すぎるif文）
				if(name.length()==0 || address.length()==0 || tel.length()==0 || tel.length() >= 15 || !isNumericTel || email.length()==0 || year.length()==0 || !isNumericYear || month.length()==0 || !isNumericMonth || date.length()==0 || !isNumericDate) {
					request.setAttribute("message","正しく入力してください");
					gotoPage(request,response,"/memberManager/errInternal.jsp");
					return;
				}

				//beanに値を渡す
				bean.setName(name);
				bean.setAddress(address);
				bean.setTel(tel);
				bean.setEmail(email);
				bean.setBirthday(birthday);

				session.setAttribute("member", bean);
				gotoPage(request, response, "/memberManager/confirmNewMember.jsp");

			}else if(action.equals("register")){
				//アクション：会員情報の新規登録

				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				RegisterMemberDAO dao = new RegisterMemberDAO();

				//DAOのINSERTメソッド
				dao.registerMember(bean);

				//DAOの一行取ってくるSELECTメソッド
				session.setAttribute("member", dao.getLatestMember());

				gotoPage(request,response,"/memberManager/registerMemberComplete.jsp");

			}else if(action.equals("search")){
				//アクション：更新削除のための検索

				HttpSession session = request.getSession(true);
				MemberBean bean = new MemberBean();
				SearchMemberDAO dao = new SearchMemberDAO();

				//パラメータ取得：セレクトボックス
				String inputType = request.getParameter("type");
				//パラメータ取得：テキストボックス
				String submitText = request.getParameter("submitText");

				//不正入力を監視（正しい入力の場合→会員IDとメールアドレスで分岐）
				if(submitText.length()==0){
					request.setAttribute("message","会員IDもしくはメールアドレスを入力してください");
					gotoPage(request,response,"/memberManager/errInternal.jsp");
					return;
				}else if(inputType.equals("memberId")) {
					//セレクトボックスで会員IDを選択した場合

					//会員IDが数字かどうかチェック
					Boolean isNumericId = submitText.matches("[+-]?\\d*(\\.\\d+)?");

					if(isNumericId){
						//正しい会員IDの場合

						int memberId = Integer.parseInt(submitText);
						bean = dao.findMemberById(memberId);
						if(bean == null) {
							//検索結果なし
							request.setAttribute("message","検索結果：なし");
							gotoPage(request,response,"/memberManager/errInternal.jsp");
						}else {
							session.setAttribute("member", bean);
							gotoPage(request,response,"/memberManager/searchMemberResult.jsp");
						}
					}else {
						//会員IDが数字でない場合
						request.setAttribute("message","正しい会員IDを入力してください");
						gotoPage(request,response,"/memberManager/errInternal.jsp");
					}

				}else {
					//セレクトボックスでメールアドレスを選択した場合

					//メールアドレスが数字だけかどうかチェック（＝会員IDと間違っていないか←優しさ）
					Boolean isNumericEmail = submitText.matches("[+-]?\\d*(\\.\\d+)?");

					if(!isNumericEmail) {
						String email = submitText;
						bean = dao.findMemberByEmail(email);
						if(bean == null) {
							//検索結果なし
							request.setAttribute("message","検索結果：なし");
							gotoPage(request,response,"/memberManager/errInternal.jsp");
						}else {
							session.setAttribute("member", bean);
							gotoPage(request,response,"/memberManager/searchMemberResult.jsp");
						}
					}else {
						//メールアドレスが数字のみの場合
						request.setAttribute("message","正しいメールアドレスを入力してください");
						gotoPage(request,response,"/memberManager/errInternal.jsp");
					}
				}
			}else if(action.equals("verdictDeletableMember")) {
				//アクション：退会可否の判定

				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				DeleteMemberDAO dao = new DeleteMemberDAO();

				//検索結果の会員IDを取得
				int memberId = bean.getId();

				//判定結果のフラグ
				Boolean flag = dao.isDeletable(memberId);

				if(flag) {
					//退会不可
					gotoPage(request,response,"/memberManager/showCantDeleteMember.jsp");
				}else {
					//退会可
					gotoPage(request,response,"/memberManager/canDeleteMember.jsp");
				}
			}else if(action.equals("deleteMember")) {
				//アクション：退会処理（SQL文実行するだけ）

				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				DeleteMemberDAO dao = new DeleteMemberDAO();

				int memberId = bean.getId();
				dao.deleteMember(memberId);
				gotoPage(request,response,"/memberManager/deleteMemberComplete.jsp");

			}else if(action.equals("forwardToUpdate")) {
				//アクション：更新用ページに飛ばすだけ

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
				//アクション：入力した更新用会員情報の確認

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
				//アクション：会員情報更新（SQL文実行するだけ）

				HttpSession session = request.getSession(false);
				MemberBean bean = (MemberBean) session.getAttribute("member");
				UpdateMemberDAO dao = new UpdateMemberDAO();

				dao.updateMember(bean);
				gotoPage(request,response,"/memberManager/updateMemberComplete.jsp");
			}
		}catch(IOException e){
			e.printStackTrace();
			request.setAttribute("message","内部エラーが発生しました");
			gotoPage(request,response,"/memberManager/errInternal.jsp");
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
