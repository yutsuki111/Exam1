package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		// セッションを取得
		HttpSession session = req.getSession(); 
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		// リスト作成
		List<Subject> subjects;
		// インスタンス化
		SubjectDao subDao = new SubjectDao();
		// filterメソッドを呼び出し
		subjects = subDao.filter(school);
		// リクエストにデータをセット
		req.setAttribute("subjects", subjects); 
		// subject_list.jspへフォア―ド
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
}
