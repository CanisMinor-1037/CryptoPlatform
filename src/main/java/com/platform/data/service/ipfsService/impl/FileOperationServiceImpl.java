package com.platform.data.service.ipfsService.impl;

import co.junwei.cpabe.Common;
import com.platform.data.component.IpfsComponent;
import com.platform.data.service.ipfsService.FileOperationService;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileOperationServiceImpl implements FileOperationService {
    @Autowired
    private IpfsComponent ipfsComponent;
    @Override
    public String uploadFile(byte[] fileBytes) throws IOException {
        IPFS upHandle = new IPFS(ipfsComponent.getIp(),ipfsComponent.getPort());
        NamedStreamable file = new NamedStreamable.ByteArrayWrapper(fileBytes);
        MerkleNode addResult = upHandle.add(file).get(0);
        return addResult.hash.toString();
    }
    @Override
    public byte[] downloadFile(String url) throws IOException {
        IPFS downHandle = new IPFS(ipfsComponent.getIp(),ipfsComponent.getPort());
        byte[] data = downHandle.cat(Multihash.fromBase58(url));
        return data;
    }
    @Override
    public byte[] getFile(String fileName) throws IOException {
        return Common.suckFile(ipfsComponent.getTmpFileBasePath() + "/" + fileName);
    }
    @Override
    public void saveFile(MultipartFile multipartFile) throws IOException{
        Common.spitFile(ipfsComponent.getTmpFileBasePath() + "/" +multipartFile.getOriginalFilename(),multipartFile.getBytes());
    }
    @Override
    public void deleteFile(String fileName){
        new File(ipfsComponent.getTmpFileBasePath() + "/" + fileName).delete();
    }
}
