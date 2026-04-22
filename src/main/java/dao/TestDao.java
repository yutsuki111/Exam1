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
     * 条件に該当する成績データを取得する（登録画面表示用）
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        // 学生を主軸に、指定された回数・科目の点数を取得する
        String sql = "SELECT s.NO, s.NAME, s.ENT_YEAR, s.CLASS_NUM, " +
                     "COALESCE(t.POINT, -1) AS POINT " +
                     "FROM STUDENT s " +
                     "LEFT JOIN TEST t ON s.NO = t.STUDENT_NO " +
                     "AND t.SUBJECT_CD = ? AND t.NO = ? AND t.SCHOOL_CD = ? " +
                     "WHERE s.ENT_YEAR = ? AND s.CLASS_NUM = ? AND s.SCHOOL_CD = ? " +
                     "ORDER BY s.NO ASC";

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
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                
                test.setStudent(student);
                test.setSubject(subject);
                test.setNo(num);
                test.setPoint(rSet.getInt("point"));
                list.add(test);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return list;
    }

    /**
     * 成績データを登録または更新する（upsert）
     */
    public boolean save(List<Test> tests) throws Exception {
        Connection connection = getConnection();
        boolean result = true;
        try {
            for (Test test : tests) {
                if (!saveOne(test, connection)) {
                    result = false;
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (connection != null) connection.close();
        }
        return result;
    }

    private boolean saveOne(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;
        // 既存データがあるか確認して、あればUPDATE、なければINSERTする
        String sql = "MERGE INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT) " +
                     "KEY(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO) VALUES (?, ?, ?, ?, ?)";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) statement.close();
        }
        return count > 0;
    }
}