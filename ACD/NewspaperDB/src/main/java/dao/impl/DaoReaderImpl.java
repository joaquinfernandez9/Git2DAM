package dao.impl;

import config.ConfigXML;
import dao.DaoReader;
import dao.strings.DaoConstants;
import domain.modelo.Reader;
import domain.modelo.Readers;
import io.vavr.control.Either;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class DaoReaderImpl implements DaoReader {

    @Override
    public Either<String, List<Reader>> getAll() {
        Either<String, List<Reader>> respuesta;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Readers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Readers readerList = (Readers) jaxbUnmarshaller.unmarshal(
                    new File(ConfigXML.getInstance()
                            .getProperty(DaoConstants.XML_READER_PATH))
            );
            respuesta = Either.right(readerList.getReaders());
        } catch (Exception e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Either<String, Reader> get(int id) {
        Either<String, Reader> respuesta;
        List<Reader> readerList = getAll().get();
        try {
            if (!readerList.isEmpty()) {
                Reader r = readerList.stream()
                        .filter(reader -> reader.getId() == id)
                        .findFirst().orElse(null);
                if (r != null) {
                    respuesta = Either.right(r);
                } else {
                    respuesta = Either.left(DaoConstants.ERROR_THE_READER_IS_NOT_VALID);
                }
            } else {
                respuesta = Either.left(DaoConstants.ERROR_THERE_IS_NOT_A_LIST_OF_READERS);
            }

        } catch (Exception e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Either<String, Boolean> delete(int id) {
        Either<String, Boolean> respuesta;
        List<Reader> readerList = getAll().get();
        Path file = Paths.get(ConfigXML.getInstance()
                .getProperty(DaoConstants.XML_READER_PATH));
        try {
            if (!readerList.isEmpty()) {
                Reader r = readerList.stream()
                        .filter(reader -> reader.getId() == id)
                        .findFirst().orElse(null);
                if (r != null) {

                    JAXBContext jaxbContext = JAXBContext.newInstance(Readers.class);
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

                    Readers lista = (Readers) unmarshaller.unmarshal(Files.newInputStream(file));
                    lista.getReaders().remove(r);

                    jaxbMarshaller.marshal(lista,
                            Files.newOutputStream(file));

                    respuesta = Either.right(true);
                } else {
                    respuesta = Either.left(DaoConstants.ERROR_THE_READER_IS_NOT_VALID);
                }
            } else {
                respuesta = Either.left(DaoConstants.ERROR_THERE_IS_NOT_A_LIST_OF_READERS);
            }

        } catch (Exception e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;

    }


}
