package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// リクエストパラメータの取得
		String no = req.getParameter("no");
		

		// セッションを取得
		HttpSession session = req.getSession(); 
		// ログイン中のTeacher情報を取得
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		// ログイン中のteacherが所属しているSchoolを取り出す
		School school = teacher.getSchool();
		Student student = new Student();
		
		
		// 更新用データの作成
		student.setNo(no);
		student.setSchool(school);

		// DB更新実行
		TestListStudentDao tlsDao = new TestListStudentDao();
		List<TestListStudent> testList = tlsDao.filter(student);
		
		req.setAttribute("f4", no);
		req.setAttribute("test_list", testList);
		// 完了画面へフォワード
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}