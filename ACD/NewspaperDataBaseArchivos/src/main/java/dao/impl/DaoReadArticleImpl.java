package dao.impl;

import config.ConfigXML;
import dao.DaoReadArticle;
import dao.strings.DaoConstants;
import domain.modelo.ReadArticle;
import domain.modelo.Readers;
import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DaoReadArticleImpl implements DaoReadArticle {

    @Override
    public Either<String, Boolean> add(int idReader, ReadArticle readArticle) {
        Either<String, Boolean> respuesta;
        try {
            Path file = Paths.get(ConfigXML.getInstance()
                    .getProperty(DaoConstants.XML_READER_PATH));

            JAXBContext jaxbContext = JAXBContext.newInstance(Readers.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Readers lista = (Readers) unmarshaller.unmarshal(Files.newInputStream(file));

            respuesta = Either.right(lista.getReaders().stream().filter(reader ->
                    reader.getId() == idReader).peek(reader ->
                    reader.getReadArticles()
                            .getReadArticlesList().add(readArticle)).findFirst().isPresent());

            jaxbMarshaller.marshal(lista,
                    Files.newOutputStream(file));


        } catch (Exception e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }


}
