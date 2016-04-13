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

        //Archivo .html a ser leido, puede ser remplazado por un file chooser
        File input = new File("/D:/Mis documentos/GitHub/PageRank/out/production/PageRank/Websites/Preventas_Cinepolis.html");
        Document doc = null;
        try {
            doc= Jsoup.parse(input,"UTF-8"); //Parseo del documento html
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements links = doc.select("a[href]"); //Seleccion de las etiquetas a con href
//        Elements  pngs = doc.select("img[src$=.png]");//Seleccion de las etiquetas img
//        Element masthead = doc.select("div.masthead").first(); //No idea
        int count = 0;
        for (Element link: links) {  //foreach para recorrer los links obtenidos
            System.out.println("link : " + link.attr("href")); //Se imprime el link .attr limpia el link de hreft="http:/www...." a http:/www....
            String thelink = link.attr("href");
            String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"; //regex para filtrar aquellos links que empiezan con http
            if (thelink.matches(regex)){ //filtro
                count++; //contador de links
            }
            System.out.println("text : " + link.text()); //Nombre del link
        }
        System.out.println("Links:::" + count); //Numero de links
    }



}
