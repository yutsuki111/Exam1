package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {
    private String no;
    private String name;
    private int entYear;
    private String classNum;
    private String subjectName;
    private Map<Integer, Integer> points = new HashMap<>();

    public String getNo() { 
    	return no; }
    public void setNo(String no) { 
    	this.no = no; }
    public String getName() { 
    	return name; }
    public void setName(String name) { 
    	this.name = name; }
    public int getEntYear() { 
    	return entYear; }
    public void setEntYear(int entYear) { 
    	this.entYear = entYear; }
    public String getClassNum() { 
    	return classNum; }
    public void setClassNum(String classNum) { 
    	this.classNum = classNum; }
    public String getSubjectName() { 
    	return subjectName; }
    public void setSubjectName(String subjectName) { 
    	this.subjectName = subjectName; }

    public Map<Integer, Integer> getPoints() { 
    	return points; }
    
    // 値をセットするメソッド
    public void putPoint(int key, int value) {
        this.points.put(key, value);
    }

    // JSPから点数を取り出すためのメソッド
    public int getPoint(int key) {
        // Mapにキーが存在すればその点数を、無ければ -1 を返す
        return this.points.getOrDefault(key, -1);
    }
}