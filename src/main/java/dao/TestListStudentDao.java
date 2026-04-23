package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        // TESTテーブルとSUBJECTテーブルを結合して、科目名も一緒に取る
        String sql = "SELECT sub.NAME AS SUBJECT_NAME, t.SUBJECT_CD, t.NO, t.POINT " +
                     "FROM TEST t " +
                     "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.CD AND t.SCHOOL_CD = sub.SCHOOL_CD " +
                     "WHERE t.STUDENT_NO = ? " +
                     "ORDER BY t.SUBJECT_CD ASC, t.NO ASC";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getNo());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                TestListStudent tls = new TestListStudent();
                tls.setSubjectName(rSet.getString("SUBJECT_NAME"));
                tls.setSubjectCd(rSet.getString("SUBJECT_CD"));
                tls.setNum(rSet.getInt("NO"));
                tls.setPoint(rSet.getInt("POINT"));
                list.add(tls);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }
}