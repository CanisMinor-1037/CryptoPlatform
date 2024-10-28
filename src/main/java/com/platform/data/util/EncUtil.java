package com.platform.data.util;


import co.junwei.cpabe.Common;
import co.junwei.cpabe.Cpabe;
import com.platform.data.component.CpabeComponent;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;

public class EncUtil {
    private static CpabeComponent cpabeComponent;
    private static final Cpabe cpabe = new Cpabe();
    private static void assetFileExists(String filePath,String fileName){
        File file = new File(filePath);
        if(!file.exists()){
            throw new RuntimeException(fileName + "does not exist.");
        }
    }
    public static void setCpabeComponent(CpabeComponent cpabeComponent) {
        EncUtil.cpabeComponent = cpabeComponent;
    }
    public static byte[] getAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(LocalDateTime.now().toString().getBytes());
        keyGenerator.init(128, secureRandom);
        byte[] key = keyGenerator.generateKey().getEncoded();
        return key;
    }
    public static byte[] aesEncrypt(byte[] key,byte[] plaintext) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(plaintext);
    }
    public static byte[] aesDecrypt(byte[] key,byte[] ciphertext) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(ciphertext);
    }
    public static byte[] cpabeEncrypt(String policy,byte[] plaintext) throws Exception {
        assetFileExists(cpabeComponent.getPublicKeyPath(), "publicKey");
        Common.spitFile(cpabeComponent.getInputFilePath(), plaintext);
        cpabe.enc(cpabeComponent.getPublicKeyPath(),policy, cpabeComponent.getInputFilePath(), cpabeComponent.getEncFilePath());
        return Common.suckFile(cpabeComponent.getEncFilePath());
    }
    public static byte[] cpabeDecrypt(byte[] ciphertext) throws Exception {
        assetFileExists(cpabeComponent.getPublicKeyPath(), "publicKey");
        assetFileExists(cpabeComponent.getPrivateKeyPath(), "privateKey");
        Common.spitFile(cpabeComponent.getEncFilePath(), ciphertext);
        cpabe.dec(cpabeComponent.getPublicKeyPath(), cpabeComponent.getPrivateKeyPath(), cpabeComponent.getEncFilePath(), cpabeComponent.getDecFilePath());
        return Common.suckFile(cpabeComponent.getDecFilePath());
    }
    public static String contractMd5(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(file.getBytes());
        String hash = new BigInteger(1, messageDigest.digest()).toString(16);
        return hash + "." + file.getOriginalFilename();
    }
}
