# 开源工具

**个人信息：**

姓名：徐卫超

邮箱：xuweichao010@163.com

**GitHub地址**：https://github.com/xuweichao010/open-framework.git





## 01 POI工具类（poi）

> > 使用注解对POI工具类的做一个简单的封装，主要用来解析和创建excel文件



###  01 依赖

  ``` xml
  <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi</artifactId>
      <version>3.10.1</version>
  </dependency>
  <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi-ooxml</artifactId>
      <version>3.10.1</version>
  </dependency>
  <dependency>
      <groupId>org.apache.poi</groupId>
      <artifactId>poi-ooxml-schemas</artifactId>
      <version>3.10.1</version>
  </dependency>
  testCompile("junit:junit:4.12")
  ```

### 02 方法

  POIUtils:

| 返回     | 方法                                                      | 说明          |
| -------- | --------------------------------------------------------- | ------------- |
| List<T>  | analysis(Class<T> clazz, InputStream in, String fileName) | 解析excel文件 |
| Workbook | generate(List<?> t, Class<?> clazz,String sheetName)      | 生成Excel文件 |
| Workbook | generate(List<?> t,String sheetName)                      | 生成Excel文件 |

  

### 03 快速入门

- **定义模型：**

  ``` java
@SheetX(ext = SheetX.EXCEL_XLSX)
public class Person {

    @StringCell(titel = "姓名", width = 20, required = true) 
    private String name;

    @DateCell(titel = "生日", required = true, width = 15)
    private Date birthday;

    @NumberCell(titel = "体重", append = "(kg)", format = "%.6f")
    private Double weight;
    @NumberCell(titel = "性别", mapper = {
            @Mapper(key = 1, val = "男"),
            @Mapper(key = 2, val = "女")
    }, width = 4)
    private Integer sex;
    // getter and setter ...
}
  ```

-  **解析excel文件：**

  ```java
@Test
public void analysisExcelFile() throws IOException {
    File file = new File("src/test/java/example/file/读取文件.xlsx");
    FileInputStream inputStream = new FileInputStream(file);
    List<Person> list = POIUtils.analysis(Person.class, inputStream, file.getName());
    list.forEach(item->ystem.out.println(item.toString()));
}
  ```

-  **生成excel文件：**

```java
@Test
public void generateExcelFile() throws IOException {
    ArrayList<Person> list = new ArrayList<>();
    for (int i = 0; i <10 ; i++) {
        Person person = new Person();
        person.setName("测试"+i);
        person.setSex(i%2 == 0?1:2);
        person.setBirthday(new Date());
        person.setWeight(i*10D);
        list.add(person);
    }
    Workbook work = POIUtils.generate(list, Person.class, "人员信息");
    File file = new File("src/test/java/example/file/写入文件.xlsx");
    FileOutputStream outputStream = new FileOutputStream(file);
    work.write(outputStream);
}
```

### 04 注解详细介绍

- **@SheeX**

  | 参数              | 类型    | 描述                                                         |
  | ----------------- | ------- | ------------------------------------------------------------ |
  | validate          | String  | 解析Excel有效，校验文件名是否符合要求，校验规则是文件名是否包含validate字符串 |
  | showTitle         | boolean | 生成Excel有效，是否生成标题栏（默认:true）                   |
  | skipRow           | int     | 解析Excel有效，从第多少行开始解析数据(默认：1)               |
  | ext               | String  | 生成Excel有效，生成的文件格式（默认格式：.xls）              |
  | fontHeight        | short   | 生成Excel有效，字体高度(默认：200)                           |
  | fontName          | String  | 生成Excel有效，字体样式（默认：Arial）                       |
  | alignment         | short   | 左右对齐方式（默认左右居中:2）                               |
  | verticalAlignment | short   | 上下对齐方式（默认上下居中:1）                               |
  | wrapText          | boolean | 是否自动换行（默认不自动换行:false）                         |

- @Mapper

  使用在@Number注解中的属性

  | 参数 | 类型   | 描述 |
  | ---- | ------ | ---- |
  | key  | int    | 键   |
  | val  | String | 值   |

  

- **@NumberCell**

  在byte, short, int, long, double, float类型上有效

  | 参数         | 类型    | 描述                                                         |
  | ------------ | ------- | ------------------------------------------------------------ |
  | index        | int     | 属性所在Excel表格中所在的列，系统默认会使用有效的属性定义的顺序来生成列序号，用户也可以根据自己的实际需求来强制指定，序号是从0开始。 |
  | titel        | String  | 生成excel有效，列的标题                                      |
  | required     | boolean | 解析excel有效，是否是必需参数，默认false(非必须参数)         |
  | width        | int     | 生成excel有效，该列所在的宽度，默认是10；                    |
  | format       | String  | 对数字对象格式化，规则请参见java String.fromat规范           |
  | append       | String  | 生成excel有效，在内容后面追加文本内容                        |
  | regex        | String  | 解析excel有效，内容校验的正则表达式，默认关闭                |
  | regexMessage | String  | 使用校验后，校验失败的提示信息                               |
  | mapper       | @Mapper | 键值映射器                                                   |

- **@DateCell**

  在Date类型上有效

  | 参数     | 类型    | 描述                                                         |
  | -------- | ------- | ------------------------------------------------------------ |
  | index    | int     | 属性所在Excel表格中所在的列，系统默认会使用有效的属性定义的顺序来生成列序号，用户也可以根据自己的实际需求来强制指定，序号是从0开始。 |
  | titel    | String  | 生成excel有效，列的标题                                      |
  | required | boolean | 解析excel有效，是否是必需参数，默认false(非必须参数)         |
  | width    | int     | 生成excel有效，该列所在的宽度，默认是10；                    |
  | format   | String  | 指定日期格式(默认Excel格式)  自定义格式时有效                |

- @StringCell

  在char, String 类型上有效

  | 参数         | 类型    | 描述                                                         |
  | ------------ | ------- | ------------------------------------------------------------ |
  | index        | int     | 属性所在Excel表格中所在的列，系统默认会使用有效的属性定义的顺序来生成列序号，用户也可以根据自己的实际需求来强制指定，序号是从0开始。 |
  | titel        | String  | 生成excel有效，列的标题                                      |
  | required     | boolean | 解析excel有效，是否是必需参数，默认false(非必须参数)         |
  | width        | int     | 生成excel有效，该列所在的宽度，默认是10；                    |
  | regex        | String  | 校验的正则表达式                                             |
  | regexMessage | String  | 校验失败后的错误提示                                         |
