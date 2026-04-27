package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションと情報の準備
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // JSPのhidden項目から科目と回数を取得
        String subjectCd = req.getParameter("subject_cd");
        int num = Integer.parseInt(req.getParameter("num"));

        // 配列として送られてくる学生番号と点数を取得
        String[] studentNos = req.getParameterValues("student_no");
        String[] pointsStr = req.getParameterValues("point");

        // DAOの準備
        SubjectDao subDao = new SubjectDao();
        TestDao tDao = new TestDao();
        StudentDao sDao = new StudentDao();
        
        Subject subject = subDao.get(subjectCd, school);
        List<Test> tests = new ArrayList<>();
        
        // ★追加：エラーメッセージを格納するMap（キー：学生番号、値：エラーメッセージ）
        Map<String, String> errors = new HashMap<>();

        // 送られてきた件数分、Testインスタンスを作成してリストに追加
        if (studentNos != null) {
            for (int i = 0; i < studentNos.length; i++) {
                Test test = new Test();
                test.setNo(num);
                test.setSubject(subject);
                test.setSchool(school);
                
                // 学生情報を取得してセット
                Student student = sDao.get(studentNos[i]);
                test.setStudent(student);

                // 点数の変換と範囲チェック処理
                int point = -1; // 初期値は未入力
                if (pointsStr[i] != null && !pointsStr[i].isEmpty()) {
                    point = Integer.parseInt(pointsStr[i]);
                    
                    // ★追加：0～100の範囲外ならエラーを追加する
                    if (point < 0 || point > 100) {
                        errors.put(studentNos[i], "0～100の範囲で入力してください");
                    }
                }
                test.setPoint(point);

                tests.add(test);
            }
        }

        // ★追加：エラーがあった場合の処理（DBには保存せず、元の画面に戻す）
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors); // エラーメッセージをセット
            req.setAttribute("tests", tests);   // 入力途中の点数を残すためにセット
            req.setAttribute("f3", subjectCd);  // 科目の選択状態を保持
            req.setAttribute("f4", num);        // 回数の選択状態を保持

            // プルダウンが消えないように、再取得してJSPに渡す
            LocalDate todaysDate = LocalDate.now();
            List<Integer> entYearSet = new ArrayList<>();
            for (int i = todaysDate.getYear() - 10; i <= todaysDate.getYear(); i++) {
                entYearSet.add(i);
            }
            ClassNumDao cNumDao = new ClassNumDao();
            req.setAttribute("ent_year_set", entYearSet);
            req.setAttribute("class_num_set", cNumDao.filter(school));
            req.setAttribute("subject_set", subDao.filter(school));
            
            List<Integer> numSet = new ArrayList<>();
            numSet.add(1); numSet.add(2);
            req.setAttribute("num_set", numSet);

            // 登録画面へフォワードしてエラーを表示させる
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return; // ★ここで処理を終了させる（下のsaveに行かせない）
        }

        // エラーが無ければ、DAOを呼んで一括保存（INSERT または UPDATE）
        tDao.save(tests);

        // 完了画面へフォワード
        req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
    }
}