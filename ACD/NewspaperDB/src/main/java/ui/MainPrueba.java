package ui;

import config.ConfigXML;
import domain.modelo.Reader;
import domain.modelo.Readers;
import domain.services.ReaderServ;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.time.LocalDate;


public class MainPrueba {

    public static void main(String[] args) throws Exception {

        //mostrar la lista del xml
        JAXBContext jaxbContext = JAXBContext.newInstance(Readers.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Readers readerList = (Readers) jaxbUnmarshaller.unmarshal(
                new File(ConfigXML.getInstance().getProperty("xmlReaderPath"))
        );

        //borrarlo de la lista

        System.out.println(readerList);

        Reader r = readerList.getReaders().stream().filter(reader ->
                reader.getId() == 1
        ).findFirst().orElse(null);

        readerList.getReaders().remove(r);

        System.out.println("papilla");


        //pasar la lista a xml

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //mostrar la lista nueva por consola
        jaxbMarshaller.marshal(readerList, System.out);
        //meter la lista en el archivo
        jaxbMarshaller.marshal(readerList,
                new File(ConfigXML.getInstance()
                        .getProperty("xmlReaderPath")));



    }
}
