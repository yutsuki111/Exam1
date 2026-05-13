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

        // 検索フォームの入力値を取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String numStr = req.getParameter("f4");

        // 入力値を数値に変換
        int entYear = (entYearStr != null && !entYearStr.equals("0")) ? Integer.parseInt(entYearStr) : 0;
        int num = (numStr != null && !numStr.equals("0")) ? Integer.parseInt(numStr) : 0;

        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();
        TestDao tDao = new TestDao();

        // プルダウン用の「入学年度（過去10年分）」リスト作成
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) entYearSet.add(i);

        // クラス、科目、回数の選択肢を準備
        List<String> classNumSet = cNumDao.filter(school);
        List<Subject> subjectSet = subDao.filter(school);
        List<Integer> numSet = List.of(1, 2); // 第1回、第2回

        // 全条件が揃っていれば、登録対象の学生リストを取得
        if (entYear != 0 && classNum != null && !classNum.equals("0") && 
            subjectCd != null && !subjectCd.equals("0") && num != 0) {
            
            Subject subject = subDao.get(subjectCd, school);
            if (subject != null) {
                // 学生リストを取得し、リクエストにセット
                List<Test> tests = tDao.filter(entYear, classNum, subject, num, school);
                req.setAttribute("tests", tests);
            }
        }

        // JSPで選択状態を維持するための値をセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("f4", num);

        // 各種プルダウン用リストとエラーメッセージをセット
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subject_set", subjectSet);
        req.setAttribute("num_set", numSet);

        // 登録画面へ遷移
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}