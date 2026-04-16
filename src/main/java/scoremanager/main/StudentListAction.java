package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザー情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        
        // ログイン状態のチェック（念のため）
        if (teacher == null) {
            // 未ログインの場合はログイン画面へリダイレクトなどの処理が必要な場合はここで行う
            res.sendRedirect("Login.action");
            return;
        }

        // ログイン中の先生の所属校を取得
        School school = teacher.getSchool();

        // ローカル変数の準備
        String entYearStr = ""; 
        String classNum = ""; 
        String isAttendStr = ""; 
        int entYear = 0; 
        boolean isAttend = false; 
        List<Student> students = null; 
        LocalDate todaysDate = LocalDate.now(); 
        int year = todaysDate.getYear(); 
        
        StudentDao studentDao = new StudentDao(); 
        ClassNumDao classNumDao = new ClassNumDao(); 
        Map<String, String> errors = new HashMap<>(); 

        // リクエストパラメーターの取得
        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
        isAttendStr = req.getParameter("f3");

        // パラメーターの型変換と判定
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (isAttendStr != null) { 
            isAttend = true; // チェックボックスがONの場合
        }
        
        // 入学年度のプルダウンリスト作成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // クラス一覧を取得（先生の所属校に紐づくもの）
        List<String> list = classNumDao.filter(school);

        // 検索条件に応じたフィルタリング
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            // 入学年度とクラス番号で検索
            students = studentDao.filter(school, entYear, classNum, isAttend);
        } else if (entYear != 0 && (classNum == null || classNum.equals("0"))) {
            // 入学年度のみで検索
            students = studentDao.filter(school, entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            // 全表示（在学フラグのみ考慮）
            students = studentDao.filter(school, isAttend);
        } else {
            // クラスだけ選んで年度が未選択の場合（エラー処理）
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);
            students = studentDao.filter(school, isAttend);
        }

        // JSPへ渡すデータをセット
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttendStr); // チェック状態保持用
        req.setAttribute("students", students);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        // 学生一覧画面へフォワード
        req.getRequestDispatcher("student_list.jsp").forward(req, res);
    }
}