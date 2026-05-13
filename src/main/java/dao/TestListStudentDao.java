package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    /**
     * 特定の学生の全科目成績を取得
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        
        // TEST（点数）と SUBJECT（科目マスタ）を結合して科目名を取得
        String sql = "SELECT sub.NAME AS SUBJECT_NAME, t.SUBJECT_CD, t.NO, t.POINT " +
                     "FROM TEST t " +
                     "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD AND t.SCHOOL_CD = sub.SCHOOL_CD " +
                     "WHERE t.STUDENT_NO = ? " +
                     "ORDER BY t.SUBJECT_CD ASC, t.NO ASC";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            // 学生番号をセット
            statement.setString(1, student.getNo());
            ResultSet rSet = statement.executeQuery();

            // 取得したレコードを1件ずつBeanに詰め替えてリストに追加
            while (rSet.next()) {
                TestListStudent tls = new TestListStudent();
                tls.setSubjectName(rSet.getString("SUBJECT_NAME"));
                tls.setSubjectCd(rSet.getString("SUBJECT_CD"));
                tls.setNum(rSet.getInt("NO"));      // 第n回
                tls.setPoint(rSet.getInt("POINT")); // 点数
                list.add(tls);
            }
        } catch (Exception e) {
            throw e;
        }
        // finallyでのclose処理はtry-with-resourcesにより自動化
        return list;
    }
}