package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		       
		// リクエストパラメーターの取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        if(entYearStr == null || classNum == null || subjectCd == null) {
        	req.setAttribute("suberror", "入学年度とクラスと科目を選択してください");
        	req.getRequestDispatcher("test_list.jsp").forward(req, res);
        	return;
        };

		
		int entYear = Integer.parseInt(entYearStr);

	    // セッション取得
	    HttpSession session = req.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");
	    School school = teacher.getSchool();
	
	    // 科目を取得
	    SubjectDao subDao = new SubjectDao();
	    Subject subject = subDao.get(subjectCd, school);
	
	    // DAO 実行
	    TestListSubjectDao tlsDao = new TestListSubjectDao();
	    List<TestListSubject> testList = tlsDao.filter(entYear, classNum, subject, school);
	
	    // JSP に渡す
	    req.setAttribute("f1", entYear);
	    req.setAttribute("f2", classNum);
	    req.setAttribute("f3", subjectCd);
	    req.setAttribute("test_list", testList);
		// 完了画面へフォワード
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}