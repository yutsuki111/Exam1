package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
	throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		String cd = req.getParameter("cd");
		String name = req.getParameter("name");
		Subject subject = new Subject();
		School school = teacher.getSchool();
		SubjectDao subDao = new SubjectDao();
		if(cd.length() > 3 ) {
			req.setAttribute("lengherrortext", "科目コードは3文字で入力してください");
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		subject = subDao.get(cd, school);
		if(subject != null) {
			req.setAttribute("duperrortext", "科目コードが重複しています");
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		subject = new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(school);
		
		 subDao.save(subject);
		
		
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
		
	}
}
