package cat.babot.scraper;


import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.List;

public class ScraperManager {
    private Document targetNode;
    public final String targetUrl;

    public ScraperManager(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setTargetNode() {
        try {
            targetNode = org.jsoup.Jsoup.connect(targetUrl).get();
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
