package example;

import com.xwc.poi.POIUtils;
import example.dto.Person;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建人：徐卫超
 * 创建时间：2018/8/5  12:14
 * 功能：单元测试POI
 * 业务：
 */
public class POIExample {

    /**
     * 测试解析Excel文件
     * @throws IOException
     */
    @Test
    public void analysisExcelFile() throws IOException {
        File file = new File("src/test/java/example/file/读取文件.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        List<Person> list = POIUtils.analysis(Person.class, inputStream, file.getName());
        list.forEach(item->
            System.out.println(item.toString())
        );
    }

//    /**
//     * 测试生成excel文件
//     * @throws IOException
//     */
//    @Test
//    public void generateExcelFile() throws IOException {
//        ArrayList<Person> list = new ArrayList<>();
//        for (int i = 0; i <10 ; i++) {
//            Person person = new Person();
//            person.setName("测试"+i);
//            person.setSex(i%2 == 0?1:2);
//            person.setBirthday(new Date());
//            person.setWeight(i*10D);
//            list.add(person);
//        }
//        Workbook work = POIUtils.generate(list, Person.class, "人员");
//        File file = new File("src/test/java/example/file/写入文件.xlsx");
//        FileOutputStream outputStream = new FileOutputStream(file);
//        work.write(outputStream);
//    }
}
