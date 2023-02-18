package dao.domain.services;

import domain.Message;

import java.util.List;

public interface ServicesMessage {
    Message insert(Message mensaje);

    Message update(Message mensaje);

    int delete(int id);

    Message get(int id);

    List<Message> getAll(int id_carpeta);
}
