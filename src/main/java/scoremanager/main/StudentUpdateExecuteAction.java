package scoremanager.main;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// 1. リクエストパラメータの取得
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		int entYear = Integer.parseInt(req.getParameter("ent_year"));
		String classNum = req.getParameter("class_num");
		// チェックボックス（is_attend）は、チェックがないとnullで送られてくる
		String isAttendStr = req.getParameter("is_attend");
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		School school = teacher.getSchool();

		// 2. 在学フラグの判定
		// チェックボックスがON（"true"などの値がある）ならtrue、それ以外はfalse
		boolean isAttend = false;
		if (isAttendStr != null) {
			isAttend = true;
		}

		// 3. 更新用データの作成
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		student.setSchool(school);

		// 4. DB更新実行
		StudentDao sDao = new StudentDao();
		// StudentDaoのsaveメソッドは、内部でgetして存在すればUPDATEを実行する仕様なのでこれだけでOK
		sDao.save(student);

		// 5. 完了画面へフォワード
		req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}
}