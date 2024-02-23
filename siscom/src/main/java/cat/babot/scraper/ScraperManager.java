package cat.babot.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class ScraperManager {
    private final Document targetNode;
    public final String targetUrl;

    public ScraperManager(String targetUrl) {
        this.targetUrl = targetUrl;
        try {
            targetNode = Jsoup.connect(targetUrl).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> obtainList(String expression) {
        return targetNode.selectXpath(expression).eachText();
    }

    public List<String> obtainListAttr(String expression, String attr) {
        return targetNode.selectXpath(expression).eachAttr(attr);
    }
}
