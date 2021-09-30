package eu.strasbourg.service.notif.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BroadcastChannelEnum {
    VIDE(0, null),
    CSMAP(1, "Csmap"),
    TWITTER(2, "twitter");

    private long id;
    private String name;

    BroadcastChannelEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static BroadcastChannelEnum get(long id) {
        for (BroadcastChannelEnum broadcastChannel : values()) {
            if (broadcastChannel.getId() == id) {
                return broadcastChannel;
            }
        }
        return null;
    }

    public static List<BroadcastChannelEnum> getAll() {
        List<BroadcastChannelEnum> broadcastChannels = new ArrayList<BroadcastChannelEnum>();
        broadcastChannels.addAll(Arrays.asList(values()));
        return broadcastChannels;
    }

}
