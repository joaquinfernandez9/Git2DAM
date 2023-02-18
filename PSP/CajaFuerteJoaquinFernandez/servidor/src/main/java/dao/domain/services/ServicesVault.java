package dao.domain.services;

import domain.Vault;

import java.util.List;

public interface ServicesVault {
    Vault insert(Vault vault);

    int delete(Vault vault);

    List<Vault> getAll();
}
