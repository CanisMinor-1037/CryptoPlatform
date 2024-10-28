package com.platform.data;

import com.platform.data.util.EncUtil;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

@SpringBootTest
public class SecurityTests {
    @Test
    public void ipfsTest() throws Exception {
        File file = new File("D:\\Desktop\\ipfs.txt");
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();
        byte[] aesKey = EncUtil.getAESKey();
        byte[] uploadBytes = EncUtil.aesEncrypt(aesKey,bytes);
        String url = uploadFile(uploadBytes,"222.20.126.153",5001);
        Thread.sleep(3000);
        byte[] downloadBytes = downloadFile(url,"222.20.126.153",5002);
        byte[] decryptBytes = EncUtil.aesDecrypt(aesKey,downloadBytes);
        OutputStream outputStream = new FileOutputStream("D:\\Desktop\\ipfs5.txt");
        outputStream.write(downloadBytes);
        outputStream.close();
        OutputStream outputStream2 = new FileOutputStream("D:\\Desktop\\ipfs4.txt");
        outputStream2.write(decryptBytes);
        outputStream2.close();
    }
    private String uploadFile(byte[] fileBytes,String ip,int port) throws IOException {
        IPFS upHandle = new IPFS(ip,port);
        NamedStreamable file = new NamedStreamable.ByteArrayWrapper(fileBytes);
        MerkleNode addResult = upHandle.add(file).get(0);
        return addResult.hash.toString();
    }
    private byte[] downloadFile(String url,String ip,int port) throws IOException {
        IPFS downHandle = new IPFS(ip,port);
        byte[] data = downHandle.cat(Multihash.fromBase58(url));
        return data;
    }
}
