package org.example;

import org.example.impl.EncryptAES;

public class ProviderSecurity {
    public Encriptacion getEncriptacion()
    {
        return new EncryptAES();
    }
}
