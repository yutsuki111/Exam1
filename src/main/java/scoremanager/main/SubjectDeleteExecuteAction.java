package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1. リクエストパラメータの取得
		String cd = req.getParameter("subject_cd");
		String name = req.getParameter("subject_name");
		HttpSession session = req.getSession(); 
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		School school = teacher.getSchool();
		Subject subject = new Subject();
		
		// 3. 更新用データの作成
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);

		// 4. DB更新実行
		SubjectDao subDao = new SubjectDao();
		// メソッドは、内部でgetして存在すればUPDATEを実行する仕様なのでこれだけでOK
		subDao.delete(subject);

		// 5. 完了画面へフォワード
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}