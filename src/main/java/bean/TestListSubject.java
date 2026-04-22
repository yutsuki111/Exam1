package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {
    private String no;          // 学生番号
    private String name;        // 氏名
    private int entYear;        // 入学年度
    private String classNum;    // クラス番号
    private Map<Integer, Integer> points = new HashMap<>(); // 回数と点数のマップ

    // 学生番号のSetter
    public void setNo(String no) {
        this.no = no;
    }

    // 氏名のSetter
    public void setName(String name) {
        this.name = name;
    }

    // 入学年度のSetter
    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    // クラス番号のSetter
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // 回数ごとの点数を格納するメソッド
    public void putPoint(int key, int value) {
        this.points.put(key, value);
    }

    // 以下、JSPで表示するために必要なGetter
    public String getNo() { return no; }
    public String getName() { return name; }
    public int getEntYear() { return entYear; }
    public String getClassNum() { return classNum; }
    public Map<Integer, Integer> getPoints() { return points; }
}