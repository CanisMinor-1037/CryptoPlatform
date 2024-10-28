package com.platform.data.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.platform.data.constant.ContractConstant;
import org.hyperledger.fabric.client.ChaincodeEvent;
import org.hyperledger.fabric.client.CloseableIterator;
import org.hyperledger.fabric.client.Network;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
public class FabricListener implements Runnable{
    private final Logger logger = Logger.getLogger(FabricListener.class.getName());
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    final Network network;
    private static String contractName;
    final FabricEventHandler fabricEventHandler;
    private ListenerRestarter listenerRestarter;
    public FabricListener(Network network, String contractName, FabricEventHandler fabricEventHandler, ListenerRestarter listenerRestarter) {
        this.network = network;
        this.contractName = contractName;
        this.fabricEventHandler = fabricEventHandler;
        this.listenerRestarter = listenerRestarter;
    }
    @Override
    public void run() {
        List<String> eventList = new ArrayList<>();
        eventList.add(ContractConstant.CREATE_DEPARTMENT);
        eventList.add(ContractConstant.UPDATE_DEPARTMENT_ATTRIBUTE);
        eventList.add(ContractConstant.CREATE_DATA_ASSET);
        eventList.add(ContractConstant.CREATE_DATA_ASSET_ORDER);
        eventList.add(ContractConstant.UPDATE_DATA_ASSET_ORDER_STATUS);
        eventList.add(ContractConstant.CREATE_ATTRIBUTE_APPLICATION);
        eventList.add(ContractConstant.UPDATE_ATTRIBUTE_APPLICATION_STATUS);
        eventList.add(ContractConstant.CREATE_DB_ASSET);
        eventList.add(ContractConstant.CREATE_DB_ASSET_ORDER);
        eventList.add(ContractConstant.CREATE_VIDEO_ASSET);
        eventList.add(ContractConstant.CREATE_VIDEO_ASSET_ORDER);
        eventList.add(ContractConstant.UPDATE_VIDEO_ASSET_ORDER_STATUS2);
        while (true) {
            try{
                CloseableIterator<ChaincodeEvent> events = network.getChaincodeEvents(contractName);
                events.forEachRemaining(event -> {
                    String payload = prettyJson(event.getPayload());
                    String eventName = event.getEventName();
                    logger.info("<-- Chaincode event received: " + eventName + " - " + payload);
                    if(eventList.contains(eventName)){
                        try {
                            fabricEventHandler.handleFabricEvent(eventName,payload);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (NoSuchAlgorithmException e) {
                            throw new RuntimeException(e);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
            catch (Exception e){
                e.printStackTrace();
                listenerRestarter.restart();
            }
        }
    }
    private String prettyJson(final byte[] json) {
        return prettyJson(new String(json, StandardCharsets.UTF_8));
    }

    private String prettyJson(final String json) {
        JsonElement parsedJson = JsonParser.parseString(json);
        return gson.toJson(parsedJson);
    }
}
