package eu.strasbourg.service.notif.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BroadcastChannel {
    CSMAP(1, "StrasApp", "sendStatusCsmap");

    private long id;
    private String label;
    private String statusField;

    BroadcastChannel(int id, String label, String statusField) {
        this.id = id;
        this.label = label;
        this.statusField = statusField;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatusField() {
        return statusField;
    }

    public void setStatusField(String statusField) {
        this.statusField = statusField;
    }


    public static BroadcastChannel get(long id) {
        for (BroadcastChannel broadcastChannel : values()) {
            if (broadcastChannel.getId() == id) {
                return broadcastChannel;
            }
        }
        return null;
    }

    public static List<BroadcastChannel> getAll() {
        List<BroadcastChannel> broadcastChannels = new ArrayList<BroadcastChannel>();
        broadcastChannels.addAll(Arrays.asList(values()));
        return broadcastChannels;
    }

}
