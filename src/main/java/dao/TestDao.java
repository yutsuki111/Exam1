package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    /**
     * 成績登録画面用：学生一覧と現在の得点を取得する
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        // LEFT JOIN を使用
        // まだ成績が登録されていない学生も一覧に表示するために、studentテーブルを左側に結合
        String sql = "select s.no as student_no, s.name as student_name, s.ent_year, s.class_num, "
                   + "t.point "
                   + "from student s "
                   + "left join test t on s.no = t.student_no "
                   + "  and t.subject_cd = ? and t.no = ? and t.school_cd = ? "
                   + "where s.ent_year = ? and s.class_num = ? and s.school_cd = ? "
                   + "order by s.no asc";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, subject.getCd());
            statement.setInt(2, num);
            statement.setString(3, school.getCd());
            statement.setInt(4, entYear);
            statement.setString(5, classNum);
            statement.setString(6, school.getCd());

            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Test test = new Test();
                
                // 学生情報をセット
                Student student = new Student();
                student.setNo(rSet.getString("student_no"));
                student.setName(rSet.getString("student_name"));
                
              
                student.setEntYear(rSet.getInt("ent_year"));     
                student.setClassNum(rSet.getString("class_num")); 
                
                test.setStudent(student);
                
                // 点数の判定
                int point = rSet.getInt("point");
                if (rSet.wasNull()) {
                    test.setPoint(-1);
                } else {
                    test.setPoint(point);
                }
                
                test.setSubject(subject);
                test.setNo(num);
                test.setSchool(school);
                
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return list;
    }

    /**
     * 登録・更新処理（一括で保存）
     */
    public void save(List<Test> tests) throws Exception {
        for (Test test : tests) {
            // リストの中身を1件ずつsaveメソッドに追加する
            save(test);
        }
    }

    /**
     * 1件の成績データを保存（あれば更新、なければ登録）
     */
    private void save(Test test) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // すでにデータがあるか確認
            boolean exists = isExists(test);

            if (!exists) {
                // 登録
                statement = connection.prepareStatement(
                    "insert into test (student_no, subject_cd, school_cd, no, point) values (?, ?, ?, ?, ?)"
                );
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
            } else {
                // 更新
                statement = connection.prepareStatement(
                    "update test set point=? where student_no=? and subject_cd=? and school_cd=? and no=?"
                );
                statement.setInt(1, test.getPoint());
                statement.setString(2, test.getStudent().getNo());
                statement.setString(3, test.getSubject().getCd());
                statement.setString(4, test.getSchool().getCd());
                statement.setInt(5, test.getNo());
            }
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    /**
     * 重複チェック
     */
    private boolean isExists(Test test) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        boolean result = false;

        try {
            statement = connection.prepareStatement(
                "select * from test where student_no=? and subject_cd=? and school_cd=? and no=?"
            );
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                result = true; // 見つかればtrue
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return result;
    }
}