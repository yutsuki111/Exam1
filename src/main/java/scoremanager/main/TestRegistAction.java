package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String numStr = req.getParameter("f4");

        // TestRegistExecuteActionから戻ってきた際のエラーメッセージを取得
        List<String> errors = (List<String>) req.getAttribute("errors");

        int entYear = 0;
        int num = 0;

        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (numStr != null && !numStr.equals("0")) {
            num = Integer.parseInt(numStr);
        }

        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();
        TestDao tDao = new TestDao();

        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        List<String> classNumSet = cNumDao.filter(school);
        List<Subject> subjectSet = subDao.filter(school);
        
        List<Integer> numSet = new ArrayList<>();
        numSet.add(1);
        numSet.add(2);

        System.out.println("--- 検索パラメータチェック ---");
        System.out.println("入学年度: " + entYear);
        System.out.println("クラス: " + classNum);
        System.out.println("科目コード: " + subjectCd);
        System.out.println("回数: " + num);
        System.out.println("学校コード: " + school.getCd());

        if (entYear != 0 && classNum != null && !classNum.equals("0") && subjectCd != null && !subjectCd.equals("0") && num != 0) {
            Subject subject = subDao.get(subjectCd, school);
            if (subject != null) {
                List<Test> tests = tDao.filter(entYear, classNum, subject, num, school);
                req.setAttribute("tests", tests);
            }
        }

        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("f4", num);
        
       
        if (errors != null && !errors.isEmpty()) {
            req.setAttribute("errors", errors);
        }
        
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subject_set", subjectSet);
        req.setAttribute("num_set", numSet);

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}