package com.atguigu.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jTest {
    @Test
    public void test1() throws Exception {
        //创建一个Saxreader输入流，去读取xml配置文件，生成document对象
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("src/books.xml");
        System.out.println(document);
    }

    /**
     * 读取books.xml文件生成book类
     */
    @Test
    public void Test02() throws Exception {
        //1.读取book.xml文件
        SAXReader reader = new SAXReader();
        //在junit测试里，相对路径是从模块名开始算的
        Document read = reader.read("src/books.xml");
        //2.通过document对象，获取根元素
        Element rootElement = read.getRootElement();
        //3.通过根元素获取book标签对象
//        System.out.println();
        //element（）和elements（）都是通过标签名查找子元素
        List<Element> books = rootElement.elements("book");
        //4.遍历，处理每个book标签转化book类
        for (Element book:books){
            //asXML()把标签对象转化为标签字符串
            Element nameElement = book.element("name");
//            System.out.println(nameElement.asXML());
            //getText可以获取标签中的文本内容
            String nameText = nameElement.getText();
            String priceText = book.elementText("price");
            String authorText = book.elementText("author");
            String snValue = book.attributeValue("sn");
            System.out.println(new Book(snValue,nameText,Double.parseDouble(priceText),authorText));
        }
    }
}
