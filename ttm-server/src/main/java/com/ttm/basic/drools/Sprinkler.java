package com.ttm.basic.drools;

/**
 * Created by liguoqing on 2016/6/30.
 */
public class Sprinkler {

    private Room room;
    private Boolean on = false;

    public Sprinkler(Room room, Boolean on) {
        this.room = room;
        this.on = on;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }
}
