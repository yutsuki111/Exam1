package scoremanager.main;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1. パラメータ（クラス名）の取得
		// 一覧画面の「変更」リンクから送られてくる ?class_num=... を受け取る
		String class_num = req.getParameter("class_num");

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();

		// 2. DBから現在のクラス情報を取得
		ClassNumDao numDao = new ClassNumDao();
		ClassNum num = numDao.get(class_num, school);


		// 3.リクエストにデータをセット
		req.setAttribute("class_num", num);

		// 5. クラス変更画面（JSP）へフォワード
		req.getRequestDispatcher("class_update.jsp").forward(req, res);
	}
}
