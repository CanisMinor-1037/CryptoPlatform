package com.platform.data.service.cpabeService.impl;

import co.junwei.cpabe.Common;
import com.platform.data.common.Response;
import com.platform.data.component.CpabeComponent;
import com.platform.data.component.HttpComponent;
import com.platform.data.component.MyDepartment;
import com.platform.data.constant.AttributeApplicationStatusConstant;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.fabricDto.DepartmentFabricDto;
import com.platform.data.service.cpabeService.GetKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class GetKeyServiceImpl implements GetKeyService {
    @Autowired
    private HttpComponent httpComponent;
    @Autowired
    private MyDepartment myDepartment;
    @Autowired
    private CpabeComponent cpabeComponent;

    @Override
    public void updateByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto) throws IOException, NoSuchAlgorithmException, ClassNotFoundException{
        if(!attributeApplicationFabricDto.getDepartmentId().equals(myDepartment.getFabricId())){
            return;
        }
        if(attributeApplicationFabricDto.getStatus() != AttributeApplicationStatusConstant.AGREED){
            return;
        }
        byte[] data = httpComponent.getPrivateKey(attributeApplicationFabricDto.getAttribute());
        Common.spitFile(cpabeComponent.getPrivateKeyPath(), data);
    }
    @Override
    public void updateByFabric(DepartmentFabricDto departmentFabricDto) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
        if(!departmentFabricDto.getName().equals(myDepartment.getName())){
            return;
        }
        byte[] data = httpComponent.getPrivateKey(departmentFabricDto.getAttribute());
        Common.spitFile(cpabeComponent.getPrivateKeyPath(), data);
    }
}
