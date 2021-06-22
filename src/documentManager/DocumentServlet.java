package documentManager;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DocumentServlet")
public class DocumentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try{
			String action = request.getParameter("action");
			if(action==null || action.length()==0 || action.equals("top")){
				gotoPage(request,response,"/menu.jsp");


			}else if(action.equals("registerSearchByISBN")){////////////////
				//ISBNをもとに検索、登録しようとしてる資料が既存の場合は既存登録ページへ、新規の場合は新規登録ページへ
				String isbn = request.getParameter("ISBN");
				//セッションを新規取得
				HttpSession session = request.getSession(true);
				//入力値チェック、おかしければエラーページ
				if(isbn==null || isbn.length()!=10){
					System.out.println("おかしなISBN");
					request.setAttribute("message","ISBN番号は10桁で正確に入力してください");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				RegisterDocumentDAO dao = new RegisterDocumentDAO();
				//DAOでISBNをもとにDBから検索して行の全を取得、無ければnullが帰ってくる
				DocumentCatalogBean bean = dao.findCatalogByISBN(isbn);
				System.out.println("null?:"+bean);
				if(bean.getName().equals("null")){//新規資料登録
					bean.setIsbnNo(isbn);
					session.setAttribute("isbn",bean);
					gotoPage(request,response,"/documentManager/inputDocumentInfo.jsp");
				}else{//追加資料登録
					session.setAttribute("doc",bean);
					gotoPage(request,response,"/documentManager/showDocuments.jsp");
				}


			}else if(action.equals("confirmAlready")){///////////////////////
				//追加登録の確認画面へ飛ばす処理。表示する為だけに強引に次のIDを作っている。このIDはDBとは関係ない
				HttpSession session = request.getSession(false);
				DocumentCatalogBean bean = (DocumentCatalogBean) session.getAttribute("doc");
				System.out.println("doc:"+bean);
				//id生成
				RegisterDocumentDAO dao = new RegisterDocumentDAO();
				int documentId = dao.getLatestDocumentId();
				documentId = documentId + 1;
				bean.setDocumentId(String.valueOf(documentId));
				gotoPage(request,response,"/documentManager/confirmRegisterDocument.jsp");


			}else if(action.equals("confirmNew")){////////////////////////
				HttpSession session = request.getSession(false);
				DocumentCatalogBean bean = (DocumentCatalogBean) session.getAttribute("isbn");
				RegisterDocumentDAO dao = new RegisterDocumentDAO();

				//入力値チェック、おかしければエラーページ
				try{
					//入力された情報をbeanに格納
					String isbn_no = bean.getIsbnNo();
					String name = request.getParameter("name");
					String categoryCode = request.getParameter("classifyCode");
					String author = request.getParameter("author");
					String publisher = request.getParameter("publisher");
					int year = Integer.parseInt(request.getParameter("year"));
					int month = Integer.parseInt(request.getParameter("month"));
					int date = Integer.parseInt(request.getParameter("date"));
					String publishedAt = year+"-"+month+"-"+date;
					int categoryCodeInt = Integer.parseInt(categoryCode);
					int documentId = dao.getLatestDocumentId();
					if(name.length()==0 || 9 < categoryCodeInt || categoryCodeInt < 0 || author.length()==0 || publisher.length()==0 || year < 0 || month < 1 || 12 < month  || date < 1 || 31 < date){
						System.out.println("不正な値");
						request.setAttribute("message","入力値が不正です");
						gotoPage(request,response,"/errInternal.jsp");
						return;
					}
					//idが存在しなければ-1が帰ってくる
					if(documentId == -1){
						System.out.println("SQL文に異常あり");
						return;
					}
					documentId = documentId + 1;
					bean = new DocumentCatalogBean(isbn_no,name,categoryCode,author,publisher,publishedAt,String.valueOf(documentId));
					session.setAttribute("doc",bean);
					gotoPage(request,response,"/documentManager/confirmRegisterDocument.jsp");
				}catch(Exception e){
					System.out.println("不正な値");
					request.setAttribute("message","入力値が不正です");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}


			}else if(action.equals("register")){////////////////////////
				//登録処理、実際にINSERTを呼び出す
				HttpSession session = request.getSession(false);
				DocumentCatalogBean bean = (DocumentCatalogBean) session.getAttribute("doc");
				RegisterDocumentDAO dao = new RegisterDocumentDAO();
				try{
					//目録へ登録をしてみる
					dao.registerDocumentCatalog(bean);
				}catch(Exception e){
					//すでに目録に項目がある場合はcatchにSQLエラーを投げる
					System.out.println("目録にはすでに存在しています");
				}finally{
					//finallyでどちらにせよ台帳へのINSERTはする。
					System.out.println("finally実行");
					dao.registerDocumentLedger(bean);
					gotoPage(request,response,"/documentManager/registerDocumentComplete.jsp");
				}


			}else if(action.equals("searchDocuments")){////////////////
				//検索処理
				String selector = request.getParameter("selector");
				String input = request.getParameter("input");
				//セッションの新規取得
				HttpSession session = request.getSession(true);
				if(selector.equals("ISBN")){
					//入力値チェック、おかしければエラーページ
					if(input.length()==0 || input.length()!=10){
						System.out.println("おかしなISBN");
						request.setAttribute("message","ISBN番号は10桁で正確に入力してください");
						gotoPage(request,response,"/errInternal.jsp");
						return;
					}
					//目録と台帳を検索
					RegisterDocumentDAO registerDao = new RegisterDocumentDAO();
					SearchDocumentDAO searchDao = new SearchDocumentDAO();
					//目録から取得
					DocumentCatalogBean catalog = registerDao.findCatalogByISBN(input);
					//台帳から取得
					List<DocumentLedgerBean> ledger = searchDao.findLedgerByISBN(input);
					//このままだと台帳の資料名がnullなので目録からset
					for(DocumentLedgerBean b:ledger){
						b.setName(catalog.getName());
					}

					session.setAttribute("docCategory",catalog);
					session.setAttribute("docLedger",ledger);
					gotoPage(request,response,"/documentManager/showSearchedDocuments.jsp");

				}else if(selector.equals("documentId")){
					//台帳を検索
					SearchDocumentDAO searchDao = new SearchDocumentDAO();
					try{
						DocumentLedgerBean ledger = searchDao.findLedgerById(input);

						ledger.setName(searchDao.getNameByISBN(ledger.getIsbnNo()));

						session.setAttribute("docLedger",ledger);
						gotoPage(request,response,"/documentManager/showSearchedLedger.jsp");
					}catch(Exception e){
						System.out.println("no hit");
						request.setAttribute("message","検索がヒットしませんでした");
						gotoPage(request,response,"/errInternal.jsp");
						return;
					}
				}


			}else if(action.equals("forwardToUpdateCatalog")){///////////
				//目録更新入力ページへ要素を渡して遷移
				//更新後に一覧に戻った時に反映させるため、触らない方もセットして投げる
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				DocumentCatalogBean catalog = (DocumentCatalogBean) session.getAttribute("docCategory");
				List<DocumentLedgerBean> ledger = (List<DocumentLedgerBean>) session.getAttribute("docLedger");

				if(catalog==null || ledger==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				String[] dateList = catalog.getPublishDate().split("-");

				session.setAttribute("dateList",dateList);
				session.setAttribute("docCategory",catalog);
				session.setAttribute("docLedger",ledger);

				gotoPage(request,response,"/documentManager/inputUpdateCatalog.jsp");


			}else if(action.equals("forwardToUpdateLedger")){////////////
				//台帳更新入力ページへ要素を渡して遷移
				//更新後に一覧に戻った時に反映させるため、触らない方もセットして投げる
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				DocumentCatalogBean category = (DocumentCatalogBean) session.getAttribute("docCategory");
				List<DocumentLedgerBean> ledger = (List<DocumentLedgerBean>) session.getAttribute("docLedger");

				if(category==null || ledger==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				//ledgerは複数あるため、普通では単一のledgerは取れない
				//hiddenからtargetのIdを取ってくる
				String targetId = request.getParameter("targetId");
				System.out.println("targetId:"+targetId);
				//このIdをもとに単一のledgerをDBから取ってくればいい
				DocumentLedgerBean bean;
				SearchDocumentDAO dao = new SearchDocumentDAO();
				bean = dao.findLedgerById(targetId);

				String[] addDateList = bean.getAddDate().split("-");
				String[] discardedDateList = new String[3];
				try{
					discardedDateList = bean.getDiscardedDate().split("-");
				}catch(Exception e){
					System.out.println("未廃棄のもの");
				}

				session.setAttribute("addDateList",addDateList);
				session.setAttribute("discardedDateList",discardedDateList);
				session.setAttribute("docCategory",category);
				session.setAttribute("docLedger",ledger);
				session.setAttribute("docLedgerOne",bean);

				gotoPage(request,response,"/documentManager/inputUpdateLedger.jsp");


			}else if(action.equals("deleteLedger")){//////////////////
				//台帳からLedgerを削除する
				//こちらはISBN検索から遷移してきたパターン
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				DocumentCatalogBean category = (DocumentCatalogBean) session.getAttribute("docCategory");
				List<DocumentLedgerBean> ledger = (List<DocumentLedgerBean>) session.getAttribute("docLedger");

				if(category==null || ledger==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				//ledgerは複数あるため、普通では単一のledgerは取れない
				//hiddenからtargetのIdを取ってくる
				String targetId = request.getParameter("targetLedgerId");
				System.out.println("targetId:"+targetId);
				//このIdをもとに単一のledgerをDBから削除すればいい
				UpdateDocumentDAO updateDao = new UpdateDocumentDAO();
				SearchDocumentDAO searchDao = new SearchDocumentDAO();
				updateDao.deleteLedger(targetId);
				ledger = searchDao.findLedgerByISBN(category.getIsbnNo());

				session.setAttribute("docCategory",category);
				session.setAttribute("docLedger",ledger);
				gotoPage(request,response,"/documentManager/deleteDocumentComplete.jsp");


			}else if(action.equals("deleteLedgerOnly")){
				//台帳からLedgerを削除する
				//こちらはID検索から遷移してきたパターン
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				//id検索で遷移してきた場合はそもそも単一のledgerしか扱わないのでこれでいい
				DocumentLedgerBean ledger = (DocumentLedgerBean) session.getAttribute("docLedger");

				if(ledger==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				UpdateDocumentDAO updateDao = new UpdateDocumentDAO();
				updateDao.deleteLedger(String.valueOf(ledger.getId()));
				gotoPage(request,response,"/documentManager/deleteLedgerOnlyComplete.jsp");


			}else if(action.equals("updateCatalog")){//////////////////
				//目録を更新する
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				List<DocumentLedgerBean> ledger = (List<DocumentLedgerBean>) session.getAttribute("docLedger");
				DocumentCatalogBean catalog = (DocumentCatalogBean) session.getAttribute("docCategory");
				if(catalog==null || ledger==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				String isbnNo = request.getParameter("ISBN");
				String name = request.getParameter("name");
				String categoryCode = request.getParameter("classifyCode");
				String author = request.getParameter("author");
				String publisher = request.getParameter("publisher");
				int year,month,date,categoryCodeInt;
				try{
					year =
					Integer.parseInt(request.getParameter("year"));
					System.out.println("1");
					month = Integer.parseInt(request.getParameter("month"));
					System.out.println("2");
					date = Integer.parseInt(request.getParameter("date"));
					System.out.println("3");
					categoryCodeInt = Integer.parseInt(request.getParameter("classifyCode"));
				}catch(Exception e){
					System.out.println("不正な値a");
					request.setAttribute("message","入力値が不正です");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				String publishDate = year+"-"+month+"-"+date;
				//入力内容をチェック、おかしければエラーページ
				if(isbnNo.length()!=10 ||name.length()==0 || 9 < categoryCodeInt || categoryCodeInt < 0 || author.length()==0 || publisher.length()==0 || year < 0 || month < 1 || 12 < month  || date < 1 || 31 < date){
					System.out.println("不正な値a");
					request.setAttribute("message","入力値が不正です");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				//更新内容をセット
				catalog.setIsbnNo(isbnNo);
				catalog.setName(name);
				catalog.setCategoryCode(categoryCode);
				catalog.setAuthor(author);
				catalog.setPublisher(publisher);
				catalog.setPublishDate(publishDate);
				//更新
				UpdateDocumentDAO dao = new UpdateDocumentDAO();
				dao.updateCatalog(catalog);

				session.setAttribute("docCategory",catalog);
				session.setAttribute("docLedger",ledger);
				gotoPage(request,response,"/documentManager/updateDocumentComplete.jsp");


			}else if(action.equals("updateLedger")){///////////////////////
				//台帳を更新する。ISBN検索から遷移してきたパターン
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				DocumentLedgerBean ledgerOne = (DocumentLedgerBean) session.getAttribute("docLedgerOne");
				DocumentCatalogBean category = (DocumentCatalogBean) session.getAttribute("docCategory");

				if(category==null  || ledgerOne==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				//更新内容をJSPから取得
				String discardedAt;
				int addYear,addMonth,addDate,discardedYear,discardedMonth,discardedDate;
				try{
					addYear = Integer.parseInt(request.getParameter("addedYear"));
					addMonth = Integer.parseInt(request.getParameter("addedMonth"));
					addDate = Integer.parseInt(request.getParameter("addedDate"));
				}catch(Exception e){
					System.out.println("不正な値");
					request.setAttribute("message","入力値が不正です");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				if(request.getParameter("discardedYear").length()==0 && request.getParameter("discardedMonth").length()==0 && request.getParameter("discardedDate").length()==0 ){
					System.out.println("廃棄はnull");
					discardedYear = 1;
					discardedMonth = 1;
					discardedDate = 1;
					discardedAt = null;
				}else{
					try{
						discardedYear = Integer.parseInt(request.getParameter("discardedYear"));
						discardedMonth = Integer.parseInt(request.getParameter("discardedMonth"));
						discardedDate = Integer.parseInt(request.getParameter("discardedDate"));
						discardedAt = discardedYear+"-"+discardedMonth+"-"+discardedDate;
					}catch(Exception e){
						System.out.println("不正な値");
						request.setAttribute("message","入力値が不正です");
						gotoPage(request,response,"/errInternal.jsp");
						return;
					}
				}
				String addedAt = addYear+"-"+addMonth+"-"+addDate;
				String note = request.getParameter("note");
				//入力内容をチェック、おかしければエラーページ
				if(addYear < 0 || addMonth < 1 || 12 < addMonth  || addDate < 1 || 31 < addDate || discardedYear < 0 || discardedMonth < 1 || 12 < discardedMonth || discardedDate < 1 || 31 < discardedDate){
					System.out.println("不正な値");
					request.setAttribute("message","入力値が不正です");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				//更新内容をセット
				ledgerOne.setAddDate(addedAt);
				ledgerOne.setDiscardedDate(discardedAt);
				ledgerOne.setNote(note);
				//更新
				UpdateDocumentDAO updateDao = new UpdateDocumentDAO();
				SearchDocumentDAO searchDao = new SearchDocumentDAO();
				updateDao.updateLedger(ledgerOne);
				//一覧表示用にledger一覧を取得
				List<DocumentLedgerBean> ledger = searchDao.findLedgerByISBN(category.getIsbnNo());

				session.setAttribute("docCategory",category);
				session.setAttribute("docLedger",ledger);
				session.setAttribute("docLedgerOne",ledgerOne);//多分要らない

				gotoPage(request,response,"/documentManager/updateLedgerComplete.jsp");


			}else if(action.equals("updateLedgerOnly")){//////////////////
				//台帳を更新する。Id検索から遷移してきたパターン
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				DocumentLedgerBean ledger = (DocumentLedgerBean) session.getAttribute("docLedger");

				if(ledger==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				//更新内容をJSPから取得
				String discardedAt;
				int addYear,addMonth,addDate,discardedYear,discardedMonth,discardedDate;

				try{
					addYear = Integer.parseInt(request.getParameter("addedYear"));
					addMonth = Integer.parseInt(request.getParameter("addedMonth"));
					addDate = Integer.parseInt(request.getParameter("addedDate"));
				}catch(Exception e){
					System.out.println("不正な値");
					request.setAttribute("message","入力値が不正です");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				if(request.getParameter("discardedYear").length()==0 && request.getParameter("discardedMonth").length()==0 && request.getParameter("discardedDate").length()==0 ){
					System.out.println("廃棄はnull");
					discardedYear = 1;
					discardedMonth = 1;
					discardedDate = 1;
					discardedAt = null;
				}else{
					try{
						discardedYear = Integer.parseInt(request.getParameter("discardedYear"));
						discardedMonth = Integer.parseInt(request.getParameter("discardedMonth"));
						discardedDate = Integer.parseInt(request.getParameter("discardedDate"));
						discardedAt = discardedYear+"-"+discardedMonth+"-"+discardedDate;
					}catch(Exception e){
						System.out.println("不正な値");
						request.setAttribute("message","入力値が不正です");
						gotoPage(request,response,"/errInternal.jsp");
						return;
					}
				}

				String addedAt = addYear+"-"+addMonth+"-"+addDate;
				String note = request.getParameter("note");
				//入力内容をチェック、おかしければエラーページ
				if(addYear < 0 || addMonth < 1 || 12 < addMonth  || addDate < 1 || 31 < addDate || discardedYear < 0 || discardedMonth < 1 || 12 < discardedMonth || discardedDate < 1 || 31 < discardedDate){
					System.out.println("不正な値");
					request.setAttribute("message","入力値が不正です");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				//更新情報をセット
				ledger.setAddDate(addedAt);
				ledger.setDiscardedDate(discardedAt);
				ledger.setNote(note);

				UpdateDocumentDAO updateDao = new UpdateDocumentDAO();
				updateDao.updateLedger(ledger);
				gotoPage(request,response,"/documentManager/updateLedgerOnlyComplete.jsp");


			}else if(action.equals("forwardToUpdateLedgerOnly")){//////////////
				//大乗の更新入力画面へ遷移する。id検索で遷移してきたパターン
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				DocumentLedgerBean bean = (DocumentLedgerBean) session.getAttribute("docLedger");
				if(bean==null){
					request.setAttribute("message","なんかnullです");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}

				String[] addDateList = bean.getAddDate().split("-");
				String[] discardedDateList = bean.getDiscardedDate().split("-");

				session.setAttribute("addDateList",addDateList);
				session.setAttribute("discardedDateList",discardedDateList);
				session.setAttribute("docLedger",bean);
				gotoPage(request,response,"/documentManager/inputUpdateLedgerOnly.jsp");


			}else if(action.equals("returnToshowDocuments")){////////////////
				//完了画面から検索結果画面へ戻る処理
				HttpSession session = request.getSession(false);
				if(session==null){
					request.setAttribute("message","セッションが切れています");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				//一度検索をかけてBeanを更新する。
				DocumentCatalogBean catalog = (DocumentCatalogBean) session.getAttribute("docCategory");
				List<DocumentLedgerBean> ledger = (List<DocumentLedgerBean>) session.getAttribute("docLedger");
				String isbn = catalog.getIsbnNo();
				RegisterDocumentDAO registerDao = new RegisterDocumentDAO();
				SearchDocumentDAO searchDao = new SearchDocumentDAO();
				//目録から取得
				catalog = registerDao.findCatalogByISBN(isbn);
				//台帳から取得
				ledger = searchDao.findLedgerByISBN(isbn);
				//このままだと台帳の資料名がnullなので目録からset
				for(DocumentLedgerBean b:ledger){
					b.setName(catalog.getName());
				}

				session.setAttribute("docCategory",catalog);
				session.setAttribute("docLedger",ledger);
				gotoPage(request,response,"/documentManager/showSearchedDocuments.jsp");
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
