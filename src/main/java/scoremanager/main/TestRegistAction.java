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
        // セッションからユーザー（先生）情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // 1. 検索フォームのプルダウン用データを準備
        // 入学年度（今年から10年前まで）
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // クラス一覧
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumSet = cNumDao.filter(school);

        // 科目一覧
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectSet = sDao.filter(school);

        // 試験回数（通常は1回、2回）
        List<Integer> testNoSet = new ArrayList<>();
        testNoSet.add(1);
        testNoSet.add(2);

        // 2. リクエストパラメータの取得（検索ボタン押下時）
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2");   // クラス
        String subjectCd = req.getParameter("f3");  // 科目コード
        String testNoStr = req.getParameter("f4");  // 回数

        int entYear = 0;
        int testNo = 0;
        List<Test> tests = null;

        // 3. 検索処理
        // 全ての条件が選択されている場合のみDBから成績リストを取得
        if (entYearStr != null && classNum != null && subjectCd != null && testNoStr != null && 
            !classNum.equals("0") && !subjectCd.equals("0")) {
            
            entYear = Integer.parseInt(entYearStr);
            testNo = Integer.parseInt(testNoStr);
            
            // 選択された科目のインスタンスを取得
            Subject subject = new Subject();
            subject.setCd(subjectCd);
            // 本来はSubjectDao.get(subjectCd)で名前等も引くのが丁寧ですが、
            // filterメソッド内ではコードが合致すれば良いので簡易的にセット
            
            TestDao tDao = new TestDao();
            tests = tDao.filter(entYear, classNum, subject, testNo, school);
        }

        // 4. JSPに渡すデータをセット
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classNumSet);
        req.setAttribute("subject_set", subjectSet);
        req.setAttribute("test_no_set", testNoSet);
        
        // 検索結果（学生リスト）
        req.setAttribute("tests", tests);
        
        // 選択状態を保持するためにパラメータを戻す
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("f4", testNo);

        // 5. 成績登録画面へフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}