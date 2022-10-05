package dao;

import config.ConfigXML;
import domain.modelo.Reader;
import domain.modelo.Readers;
import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.Document;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class DaoReader {

    public Either<String, Reader> get(int id){
        Either<String, Reader> respuesta;
        Readers readerList = getAll().get();
        try {
            if (!readerList.getReaders().isEmpty()){
                Reader r = readerList.getReaders().stream()
                        .filter(reader -> reader.getId() == id)
                        .findFirst().orElse(null);
                if (r != null){
                    respuesta = Either.right(r);
                } else {
                    respuesta = Either.left("error");
                }
            } else {
                respuesta = Either.left("error");
            }

        } catch (Exception e){
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    public Either<String, Readers> getAll(){
        Either<String, Readers> respuesta;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Readers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Readers readerList = (Readers) jaxbUnmarshaller.unmarshal(
                    new File(ConfigXML.getInstance().getProperty("xmlReaderPath"))
            );
            respuesta = Either.right(readerList);
        } catch (Exception e){
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

//    public Either<String, Boolean> save{}
//
//    public Either<String, Boolean> update{}
//
//    public Either<String, Boolean> delete{}


}
