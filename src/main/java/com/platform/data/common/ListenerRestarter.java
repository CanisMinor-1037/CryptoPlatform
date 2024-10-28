package com.platform.data.common;

public class ListenerRestarter {
    private FabricListener fabricListener;
    public void setFabricListener(FabricListener fabricListener) {
        this.fabricListener = fabricListener;
    }
    public void restart() {
        fabricListener.run();
    }
}
