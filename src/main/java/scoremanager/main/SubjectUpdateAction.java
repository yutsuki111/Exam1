package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String cd = req.getParameter("cd");

		HttpSession session = req.getSession(); 
		Teacher teacher = (Teacher)session.getAttribute("user"); 
		School school = teacher.getSchool();

		SubjectDao subDao = new SubjectDao();
		Subject subject = subDao.get(cd, school);

		

		// 4. リクエストにデータをセット
		req.setAttribute("subject", subject);

		// 5. 学生変更画面（JSP）へフォワード
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}
