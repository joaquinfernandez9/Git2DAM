package dao;

import domain.Message;

import java.util.List;

public interface DaoMessage {
    Message insert(Message mensaje);

    Message update(Message mensaje);

    int delete(int id);

    Message getById(int id);

    List<Message> getAll(int idCarpeta);
}
