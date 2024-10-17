package com.platform.data;

import com.platform.data.common.FabricEventHandler;
import com.platform.data.common.FabricListener;
import com.platform.data.common.ListenerRestarter;
import com.platform.data.component.CpabeComponent;
import com.platform.data.component.HyperLedgerFabricProperties;
import com.platform.data.component.IpfsComponent;
import com.platform.data.component.MyDepartment;
import com.platform.data.util.EncUtil;
import com.platform.data.util.FabricIdUtil;
import com.platform.data.util.VideoUtil;
import org.hyperledger.fabric.client.Network;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@MapperScan("com.platform.data.mapper")
public class DataApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DataApplication.class, args);
        RedisTemplate redisTemplate = (RedisTemplate)context.getBean("redisTemplate");
        FabricIdUtil.setRedisTemplate(redisTemplate);
        MyDepartment myDepartment = (MyDepartment)context.getBean("myDepartment");
        FabricIdUtil.setDepartmentHash(myDepartment.getName().hashCode());
        FabricIdUtil.setMyFabricId(myDepartment.getFabricId());
        CpabeComponent cpabeComponent = (CpabeComponent)context.getBean("cpabeComponent");
        EncUtil.setCpabeComponent(cpabeComponent);
        IpfsComponent ipfsComponent = (IpfsComponent)context.getBean("ipfsComponent");
        VideoUtil.setBasePath(ipfsComponent.getTmpFileBasePath()+"/");
        HyperLedgerFabricProperties hyperLedgerFabricProperties = (HyperLedgerFabricProperties) context.getBean("hyperLedgerFabricProperties");
        Network network = (Network) context.getBean("network");
        FabricEventHandler fabricEventHandler = (FabricEventHandler) context.getBean("fabricEventHandler");
        //new FabricListener(network,hyperLedgerFabricProperties.getContract(),fabricEventHandler).run();
        ListenerRestarter listenerRestarter = new ListenerRestarter();
        FabricListener fabricListener = new FabricListener(network,hyperLedgerFabricProperties.getContract(),fabricEventHandler,listenerRestarter);
        listenerRestarter.setFabricListener(fabricListener);
        fabricListener.run();
    }
}
