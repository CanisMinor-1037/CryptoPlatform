package com.platform.data.caServer.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface KeyService {
    byte[] getPublicKey() throws IOException, ClassNotFoundException;

    byte[] getPrivateKey(String attribute) throws IOException, ClassNotFoundException, NoSuchAlgorithmException;
}
