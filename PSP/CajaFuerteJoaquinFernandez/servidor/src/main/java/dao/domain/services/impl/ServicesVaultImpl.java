package dao.domain.services.impl;

import dao.DaoFolder;
import dao.domain.NotFoundException;
import dao.domain.services.ServicesVault;
import domain.Vault;
import jakarta.inject.Inject;

import java.util.List;

public class ServicesVaultImpl implements ServicesVault {

    private final DaoFolder dao;

    @Inject
    public ServicesVaultImpl(DaoFolder dao) {
        this.dao = dao;
    }

    @Override public Vault insert(Vault vault) {
        return dao.insert(vault);
    }

    @Override public int delete(Vault vault) {
        if (!dao.checkPass(vault.getPassword(), vault.getId())) {
            throw new NotFoundException("Incorrect Password");
        } else if (dao.checkUser(vault.getUsername(), vault.getId())) {
            throw new NotFoundException("Incorrect Password");
        } else {
            return dao.delete(vault.getId());
        }
    }


    @Override public List<Vault> getAll() {
        return dao.getAll();
    }

}
