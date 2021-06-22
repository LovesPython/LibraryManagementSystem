package lendRet;

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


@WebServlet("/LendRetServlet")
public class LendRetServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");


			String action=request.getParameter("action");
			if(action==null || action.length() == 0 || action.equals("top")){
				HttpSession session=request.getSession(false);
				LendingLedgerBean lending =(LendingLedgerBean)session.getAttribute("lending");
				if(lending!=null) {
					session.removeAttribute("lending");
				}
				gotoPage(request,response,"/menu.jsp");


			/*延滞者一覧処理*/
			}else if(action.equals("overdue")){
				OverdueDAO dao = new OverdueDAO();
				List<MemberBean> list=dao.getMembersByMemberId();
				request.setAttribute("members",list);
				gotoPage(request,response,"/lendRet/overdue.jsp");

			/*貸出返却用貸出状況画面*/
			}else if(action.equals("searchForLendRet")){
				HttpSession session=request.getSession(true);
				LendingLedgerBean lending =(LendingLedgerBean)session.getAttribute("lending");
				int memberId;
				if(lending==null) {
					memberId=Integer.parseInt(request.getParameter("memberID"));
					LendingLedgerBean bean=new LendingLedgerBean();
					bean.setMemberId(memberId);
					session.setAttribute("lending", bean);
				}else {
					memberId =lending.getMemberId();
				}
				LendRetDocumentDAO dao=new LendRetDocumentDAO();
				List<LendingLedgerBean> list=dao.findLendingLedgerByMemberId(memberId);


				request.setAttribute("members",list);
				gotoPage(request,response,"/lendRet/lendStatusLendRet.jsp");

			/*貸出確認画面*/
			}else if(action.equals("confirmLend")) {
				HttpSession session=request.getSession(false);
				LendingLedgerBean lending =(LendingLedgerBean)session.getAttribute("lending");
				int memberId=lending.getMemberId();
				int lendDocumentId=Integer.parseInt(request.getParameter("lendDocumentID"));
				LendRetDocumentDAO dao=new LendRetDocumentDAO();
				LendingLedgerBean bean=dao.addDeadline(lendDocumentId,memberId);
				session.setAttribute("lending",bean);
				session.setAttribute("documentId",lendDocumentId);

				List<LendingLedgerBean> list=new ArrayList<LendingLedgerBean>();
				list.add(bean);
				request.setAttribute("lending",list);
				gotoPage(request,response,"/lendRet/lendConfirm.jsp");

			/*貸出完了画面*/
			}else if(action.equals("lend")) {
				HttpSession session=request.getSession(false);
				LendingLedgerBean lending =(LendingLedgerBean)session.getAttribute("lending");
				LendRetDocumentDAO dao=new LendRetDocumentDAO();
				dao.addLendingLedger(lending);

				gotoPage(request,response,"/lendRet/lendComplete.jsp");

			/*返却確認画面*/
			}else if(action.equals("confirmRet")) {
				HttpSession session=request.getSession(false);
				LendingLedgerBean lending =(LendingLedgerBean)session.getAttribute("lending");
				int memberId =lending.getMemberId();
				int documentId=Integer.parseInt(request.getParameter("doc_code"));
				LendRetDocumentDAO dao=new LendRetDocumentDAO();
				LendingLedgerBean bean=dao.deleteLendingLedger(memberId,documentId);
				session.setAttribute("lending",bean);

				List<LendingLedgerBean> list=new ArrayList<LendingLedgerBean>();
				list.add(bean);
				request.setAttribute("returnDoc", list);
				gotoPage(request,response,"/lendRet/retConfirm.jsp");

			/*返却完了画面*/
			}else if(action.equals("return")) {
				gotoPage(request,response,"/lendRet/retComplete.jsp");
			}
		}catch(DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました。");
			gotoPage(request,response,"/errInternal.jsp");
		}
	}

  /*page移動*/
	private void gotoPage(HttpServletRequest request, HttpServletResponse response,String page) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}