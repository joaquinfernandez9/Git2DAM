package ui;

import config.ConfigXML;
import domain.modelo.ReadArticles;
import domain.modelo.Reader;
import domain.modelo.Readers;
import domain.modelo.Subscriptions;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainPrueba {

    public static void main(String[] args) throws Exception {


        JAXBContext jaxbContext = JAXBContext.newInstance(Readers.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Readers readerList = (Readers) jaxbUnmarshaller.unmarshal(
                new File(ConfigXML.getInstance().getProperty("xmlReaderPath"))
        );

        System.out.println(readerList);



    }
}
