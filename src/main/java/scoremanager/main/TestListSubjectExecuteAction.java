package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// セッションからログインユーザー情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        // ログイン中の先生の所属校を取得
        School school = teacher.getSchool();
        // ローカル変数の準備
        // 科目別
        String entYearStr = ""; 
        String classNum = ""; 
        String subjectCd = ""; 
        int entYear = 0;         
        
        List<Subject> subjects = null; 
        LocalDate todaysDate = LocalDate.now(); 
        int year = todaysDate.getYear(); 
        ClassNumDao classNumDao = new ClassNumDao(); 
        SubjectDao subDao = new SubjectDao(); 
       
        // リクエストパラメーターの取得
        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        subjectCd = req.getParameter("f3");
        
        // パラメーターの型変換と判定
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }
        // 入学年度のプルダウンリスト作成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // クラス一覧を取得（先生の所属校に紐づくもの）
        List<String> list = classNumDao.filter(school);
     // 科目データを取得
        subjects = subDao.filter(school);
        
        if(entYearStr == null || classNum == null || subjectCd == null || entYearStr.equals("0") || classNum.equals("0") || subjectCd.equals("0")) {
        	req.setAttribute("f1", entYear);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("subjects", subjects);
            req.setAttribute("class_num_set", list);
            req.setAttribute("ent_year_set", entYearSet);
        	req.setAttribute("suberror", "入学年度とクラスと科目を選択してください");
        	req.getRequestDispatcher("test_list.jsp").forward(req, res);
        	return;
        };
    	
	    // 科目を取得
	    Subject subject = subDao.get(subjectCd, school);

	    
	    // DAO 実行
	    TestListSubjectDao tlsDao = new TestListSubjectDao();
	    List<TestListSubject> testList = tlsDao.filter(entYear, classNum, subject, school);
	
	    // JSP に渡す
        // JSPへ渡すデータをセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("subjects", subjects);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);
	    req.setAttribute("test_list", testList);
		// 完了画面へフォワード
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}