package day2.webscrapprodcon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DocumentConsumer implements Runnable {

    BlockingQueue<Document> producedDocuments;
    Document doc;

    public DocumentConsumer(BlockingQueue<Document> producedDocuments) {
        this.producedDocuments = producedDocuments;
    }

    @Override
    public void run() {
        boolean moreDocumentsToConsume = true;
        Document doc;
        int totalDivs = 0;
        while (moreDocumentsToConsume) {
            try {
                if ((doc = producedDocuments.poll()) != null) {
                    if (doc.location() == "http://www.finished.com") {
                        moreDocumentsToConsume = false;
                    } else {
                        String title = doc.title();
                        Elements divs = doc.select("div");
                        totalDivs = divs.size();
                        System.out.println("Title: " + title + " Divs: " + totalDivs);
                    }
                } else {
                    Thread.sleep(10000);
                    doc = producedDocuments.poll();
                    if (doc != null) {
                        String title = doc.title();
                        Elements divs = doc.select("div");
                        totalDivs = divs.size();
                        System.out.println("Title: " + title + " Divs: " + totalDivs);

                    } else {
                        moreDocumentsToConsume = false;
                    }
                }

            } catch (Exception ex) {
                Logger.getLogger(DocumentConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Sum of all Divs:" + totalDivs);
    }

}
