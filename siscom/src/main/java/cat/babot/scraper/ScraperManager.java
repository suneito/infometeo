package cat.babot.scraper;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.net.URL;

public class ScraperManager {
    private Document targetNode;

    public ScraperManager(String targetUrl) {
        try {
            HtmlCleaner cleaner = new HtmlCleaner();
            TagNode node = cleaner.clean(new URL(targetUrl));
            targetNode = new DomSerializer(
                    new CleanerProperties()).createDOM(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NodeList obtainList(String expression) {
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList list;
        try {
            list = (NodeList) xpath.evaluate(expression,
                    targetNode, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
