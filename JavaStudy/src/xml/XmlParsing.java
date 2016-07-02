package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 解析XML文件
 *
 * Created by Alexander on 16/7/2.
 */
public class XmlParsing {
    public static void main(String[] args) throws Exception {
        /**
         * 创建对象解析文件
         */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        File data = new File("JavaStudy/src/xml/data.xml");
        if (!data.exists()) {
            System.out.println("Can't find data file.");
            return;
        }
        Document doc = builder.parse(data);
        /**
         * 解析XML文件
         */
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                Element childElement = ((Element) child);
                Text textNode = ((Text) childElement.getFirstChild());
                String text = textNode.getData().trim();
                System.out.println(i + ": " + childElement.getTagName() + ": " + text);
            }
        }
    }
}
