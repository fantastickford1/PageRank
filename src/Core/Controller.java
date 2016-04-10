package Core;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        File input = new File("/D:/Mis documentos/GitHub/PageRank/out/production/PageRank/Websites/Preventas_Cinepolis.html");
        Document doc = null;
        try {
            doc= Jsoup.parse(input,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]");
        Elements  pngs = doc.select("img[src$=.png]");
        Element masthead = doc.select("div.masthead").first();
        int count = 0;
        for (Element link: links) {
            System.out.println("link : " + link.attr("href"));
            String thelink = link.attr("href");
            String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
            if (thelink.matches(regex)){
                count++;
            }
            System.out.println("text : " + link.text());
        }
        System.out.println("Links:::" + count);
    }



}
