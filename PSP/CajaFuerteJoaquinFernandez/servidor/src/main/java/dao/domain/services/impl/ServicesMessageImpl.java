package dao.domain.services.impl;

import dao.DaoMessage;
import domain.Message;
import dao.domain.services.ServicesMessage;
import jakarta.inject.Inject;

import java.util.List;

public class ServicesMessageImpl implements ServicesMessage {
    private final DaoMessage dao;

    @Inject
    public ServicesMessageImpl(DaoMessage dao) {
        this.dao = dao;
    }

    @Override public Message insert(Message mensaje) {
        return dao.insert(mensaje);
    }

    @Override public Message update(Message mensaje) {
        return dao.update(mensaje);
    }

    @Override public int delete(int id) {
        return dao.delete(id);
    }

    @Override public Message get(int id) {
        return dao.getById(id);
    }

    @Override public List<Message> getAll(int id_carpeta) {
        return dao.getAll(id_carpeta);
    }
    
}
