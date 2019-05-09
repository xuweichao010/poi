package example.dto;

import com.xwc.poi.anno.*;

import java.util.Date;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/5  17:37
 * 功能：
 * 业务：
 */
@SheetX(ext = SheetX.EXCEL_XLSX)
public class Person {

    @StringCell(titel = "姓名",width = 20,required = true)
    private String name;

    @DateCell(titel = "生日",required = true,width = 15)
    private Date birthday;

    @NumberCell(titel = "体重",append = "(kg)",format = "%.6f")
    private Double weight;
    @NumberCell(titel = "性别",mapper = {
            @Mapper(key = "1",val = "男"),
            @Mapper(key = "2",val = "女")
    },width = 4)
    private Integer sex;

    public Person() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", weight=" + weight +
                ", birthday=" + birthday +
                '}';
    }
}
