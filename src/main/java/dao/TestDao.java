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
     * 条件に該当する成績データを取得する
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

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
     * 成績データを登録または更新する
     */
    public boolean save(List<Test> tests) throws Exception {
        Connection connection = getConnection();
        boolean result = true;
        try {
            connection.setAutoCommit(false); // トランザクション開始
            System.out.println("=== データベース保存処理 開始 ===");

            for (Test test : tests) {
                if (!saveOne(test, connection)) {
                    System.out.println("保存失敗: 学生番号 " + test.getStudent().getNo());
                    result = false;
                    break;
                }
            }

            if (result) {
                connection.commit();
                System.out.println("=== 保存成功（コミット完了） ===");
            } else {
                connection.rollback();
                System.out.println("=== 保存失敗（ロールバック実行） ===");
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            if (connection != null) connection.close();
        }
        return result;
    }

    /**
     * 1件分の成績データを保存する（内部メソッド）
     */
    private boolean saveOne(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        ResultSet rSet = null;
        int count = 0;
        
        try {
            // 既存データがあるかチェック
            String checkSql = "SELECT * FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";
            statement = connection.prepareStatement(checkSql);
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setInt(3, test.getNo());
            rSet = statement.executeQuery();

            if (rSet.next()) {
                statement.close();
                if (test.getPoint() >= 0) {
                    // すでにデータがあり、点数が入力された -> 更新(UPDATE)
                    String updateSql = "UPDATE TEST SET POINT = ? WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";
                    statement = connection.prepareStatement(updateSql);
                    statement.setInt(1, test.getPoint());
                    statement.setString(2, test.getStudent().getNo());
                    statement.setString(3, test.getSubject().getCd());
                    statement.setInt(4, test.getNo());
                    count = statement.executeUpdate();
                    System.out.println("UPDATE実行: 学生 " + test.getStudent().getNo() + " 点数: " + test.getPoint());
                } else {
                    // すでにデータがあるが、空欄(-1)にされた -> 削除(DELETE)
                    String deleteSql = "DELETE FROM TEST WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND NO = ?";
                    statement = connection.prepareStatement(deleteSql);
                    statement.setString(1, test.getStudent().getNo());
                    statement.setString(2, test.getSubject().getCd());
                    statement.setInt(3, test.getNo());
                    count = statement.executeUpdate();
                    System.out.println("DELETE実行: 学生 " + test.getStudent().getNo() + " (空欄のため削除)");
                }
            } else {
                statement.close();
                if (test.getPoint() >= 0) {
                    // データがなく、点数が入力された -> 新規追加(INSERT)
                    String insertSql = "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT) VALUES (?, ?, ?, ?, ?)";
                    statement = connection.prepareStatement(insertSql);
                    statement.setString(1, test.getStudent().getNo());
                    statement.setString(2, test.getSubject().getCd());
                    statement.setString(3, test.getSchool().getCd());
                    statement.setInt(4, test.getNo());
                    statement.setInt(5, test.getPoint());
                    count = statement.executeUpdate();
                    System.out.println("INSERT実行: 学生 " + test.getStudent().getNo() + " 点数: " + test.getPoint());
                } else {
                    // データがなく、今回も空欄(-1) -> 何もしない（スキップ）
                    System.out.println("SKIP: 学生 " + test.getStudent().getNo() + " (未入力のため無視)");
                    count = 1; // エラー扱いにしないため1を入れる
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
        }
        return count > 0;
    }
}