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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    TextArea textArea;

    String Popu[][];
    String linkTable[][];
    String NoLinkPerPage[][];

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ArrayList<String> AllLinks = new ArrayList<>();
        File input = new File("src/Websitess");
        File allWeb[] = input.listFiles();
        linkTable = new String[allWeb.length][allWeb.length+1];
        int countFileAux =0;
        for (File webpage : allWeb) {
            int linkcountAux=0;
            Document doc = null;
            try {
                doc= Jsoup.parse(webpage,"UTF-8"); //Parseo del documento html
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements title = doc.select("title");
            Element tit = title.first();
            String pageName = tit.text(); //nombre de la pagina
            linkTable[countFileAux][linkcountAux] = pageName; //[wikipedia][][][][]
            linkcountAux++;
            Elements links = doc.select("a[href");
            for (Element link : links) {
                String thelink;
                thelink = link.attr("href");
                if (!thelink.equals(pageName)){
                    AllLinks.add(thelink);
                    linkTable[countFileAux][linkcountAux] = thelink; //[wikipedia][link1][link2][link3][0]
                    linkcountAux++;
                }
            }
            countFileAux++;
        }
        Popu = new String[allWeb.length][2];
        int count = 0;
        int aux = 0;
        for (File webpage : allWeb) {
            Document doc = null;
            try {
                doc= Jsoup.parse(webpage,"UTF-8"); //Parseo del documento html
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements title = doc.select("title");
            Element tit = title.first();
            String pageName = tit.text();
            for (String link : AllLinks) {
                if (pageName.equals(link)){
                    count++;
                }
            }
            Popu[aux][0] = pageName;
            Popu[aux][1] = count+"";
            aux++;
            count=0;
        }
        //////////////////////////////////////////////////////
        int aux2 =0;
        NoLinkPerPage = new String[linkTable.length][2];
        for (int q = 0; q < linkTable.length; q++) {
            NoLinkPerPage[q][0] = linkTable[q][0];
            for (int w = 1; w < linkTable[0].length; w++) {
                if(linkTable[q][w] != null)
                    aux2++;
            }
            NoLinkPerPage[q][1] = aux2+"";
            aux2=0;
        }


        //////////////////////////////////////////////////////
        for (int i = 0; i < Popu.length; i++) {
            String currentPage = Popu[i][0]; //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            System.out.println(currentPage +":");
            double status = 0;
            for (int j = 0; j < linkTable.length; j++) {
                String outlaw = linkTable[j][0];
                if (!outlaw.equals(currentPage)){
                    for (int k = 1; k < linkTable[0].length; k++) {
                        String linkedPage = linkTable[j][k];
                        if (linkedPage != null){
                            if (linkedPage.equals(currentPage)){
                                for (int q = 0; q < NoLinkPerPage.length; q++) {
                                    String thispage = NoLinkPerPage[q][0];
                                    if (thispage.equals(outlaw)){
                                        double auxCont = Double.parseDouble(NoLinkPerPage[q][1]);
                                        double auxstatus = 1/auxCont;
                                        for (int p = 0; p < Popu.length; p++) {
                                            String auxString = Popu[p][0];
                                            if (auxString.equals(outlaw)){
                                                int auxCo = Integer.parseInt(Popu[p][1]);
                                                auxstatus= auxCo*auxstatus;
                                                status = status + auxstatus;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }

            }
            System.out.println(status);

        }



    }



}
