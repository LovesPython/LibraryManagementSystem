package documentManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DocumentServlet")
public class DocumentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String action = request.getParameter("action");
			if(action==null || action.length()==0 || action.equals("top")){
				gotoPage(request,response,"/menu.jsp");
			}else if(action.equals("registerSearchByISBN")){
				String isbn = request.getParameter("ISBN");
				if(isbn==null || isbn.length()!=10){
					System.out.println("おかしなISBN");
					request.setAttribute("message","ISBN番号は10桁で正確に入力してください");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				RegisterDocumentDAO dao = new RegisterDocumentDAO();
				//DAOでISBNをもとにDBから検索して行の全を取得、無ければnullが帰ってくる
				DocumentCatalogBean bean = dao.findCatalogByISBN(isbn);
				if(bean==null){//新規資料登録
					gotoPage(request,response,"/documentManager/inputDocumentInfo.jsp");
				}else{//追加資料登録
					request.setAttribute("doc",bean);
					gotoPage(request,response,"/documentManager/showDocuments.jsp");
				}
			}

		}catch(IOException e){
			e.printStackTrace();
			request.setAttribute("message","内部エラーが発生しました");
			gotoPage(request,response,"/errInternal.jsp");
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
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
