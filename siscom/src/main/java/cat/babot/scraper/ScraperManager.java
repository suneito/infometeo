package cat.babot.scraper;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.net.URL;

public class ScraperManager {
    private Document targetNode;
    public final String targetUrl;

    public ScraperManager(String targetUrl) {
        this.targetUrl = targetUrl;
//        try {
//            Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
//            Elements newsHeadlines = doc.select("#mp-itn b a");
//            for (Element headline : newsHeadlines) {
//                log("%s\n\t%s",
//                        headline.attr("title"), headline.absUrl("href"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
