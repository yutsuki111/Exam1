package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// パラメータ取得
		String cd = req.getParameter("cd");
		// セッションを取得
		HttpSession session = req.getSession(); 
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		// インスタンス化
		School school = teacher.getSchool();
		SubjectDao subDao = new SubjectDao();
		
		// getメソッドを呼び出し
		Subject subject = subDao.get(cd, school);


		// リクエストにデータをセット
		req.setAttribute("subject_cd", subject.getCd());
		req.setAttribute("subject_name", subject.getName());

		// subject_delete.jspへフォワード
		req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
	}
}
