package day2.webscrapprodcon;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//This is the class template for the four Producer Threads P1-P4 in the exercise figure
public class DocumentProducer implements Runnable {

  BlockingQueue<String> urlsToUse;
  BlockingQueue<Document> producedDocuments;
  Document doc;
            Document endDocument;
                boolean moreUrlsToFecth = true;

  public DocumentProducer(BlockingQueue<String> urlsToUse, BlockingQueue producedDocuments) {
    this.urlsToUse = urlsToUse;
    this.producedDocuments = producedDocuments;
  }

  @Override
  public void run() {
    
    while (moreUrlsToFecth) {
      try {
        String url = urlsToUse.poll();  
        
        if (url == null) {
          moreUrlsToFecth = false;
          endDocument = Jsoup.connect("http://www.finished.com").get();
          producedDocuments.put(endDocument);
        } else {
          doc = Jsoup.connect(url).get(); 
          producedDocuments.put(doc);
        }
      } catch(Exception ex) {
        Logger.getLogger(DocumentProducer.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
    
  }

}
