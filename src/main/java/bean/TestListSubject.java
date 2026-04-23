package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {
    private String no;          // 学生番号
    private String name;        // 氏名
    private int entYear;        // 入学年度
    private String classNum;    // クラス番号
    private String subjectName; // 科目名
    private Map<Integer, Integer> points = new HashMap<>(); // 回数と点数のマップ

    // 学生番号
    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }

    // 氏名
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // 入学年度
    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    // クラス番号
    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    // 科目名
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    // 点数マップ
    public Map<Integer, Integer> getPoints() { return points; }
    public void putPoint(int key, int value) { this.points.put(key, value); }
}