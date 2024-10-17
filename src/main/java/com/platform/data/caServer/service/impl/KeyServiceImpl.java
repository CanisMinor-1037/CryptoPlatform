package com.platform.data.caServer.service.impl;

import co.junwei.cpabe.Common;
import co.junwei.cpabe.Cpabe;
import com.platform.data.caServer.service.KeyService;
import com.platform.data.component.CaServerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class KeyServiceImpl implements KeyService {
    @Autowired
    private CaServerComponent caServerComponent;
    private final Cpabe cpabe = new Cpabe();
    private boolean keyInitialized = false;
    private void keyInitialize() throws IOException, ClassNotFoundException {
        File publicKey = new File(caServerComponent.getPublicKeyPath());
        File masterKey = new File(caServerComponent.getMasterKeyPath());
        if (!publicKey.exists() || !masterKey.exists()) {
            cpabe.setup(caServerComponent.getPublicKeyPath(), caServerComponent.getMasterKeyPath());
        }
        keyInitialized = true;
    }
    @Override
    synchronized public byte[] getPublicKey() throws IOException, ClassNotFoundException {
        if (!keyInitialized){
            keyInitialize();
        }
        return Common.suckFile(caServerComponent.getPublicKeyPath());
    }
    @Override
    synchronized public byte[] getPrivateKey(String attribute) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        if (!keyInitialized){
            keyInitialize();
        }
        cpabe.keygen(caServerComponent.getPublicKeyPath(), caServerComponent.getPrivateKeyPath(), caServerComponent.getMasterKeyPath(),attribute);
        return Common.suckFile(caServerComponent.getPrivateKeyPath());
    }
}
