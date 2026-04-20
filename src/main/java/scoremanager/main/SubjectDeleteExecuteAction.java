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

		// リクエストパラメータの取得
		String cd = req.getParameter("subject_cd");
		String name = req.getParameter("subject_name");
		// セッションを取得
		HttpSession session = req.getSession(); 
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		// インスタンス化
		Subject subject = new Subject();
		
		// 更新用データの作成
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);

		// DB更新実行
		SubjectDao subDao = new SubjectDao();
		// メソッドは、内部でgetして存在すればUPDATEを実行する仕様なのでこれだけでOK
		subDao.delete(subject);

		// subject_delete_done.jspへフォワード
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}