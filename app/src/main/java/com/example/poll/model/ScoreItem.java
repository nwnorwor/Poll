package com.example.poll.model;

import java.util.Locale;

public class ScoreItem {
    public final long _id;
    public final String number;
    public final String score;
    public final String image; //final คือ ห้ามใครแก้แล้วเด้อ

    public ScoreItem(long _id, String number, String score , String image) {
        this._id = _id;
        this.number = number;
        this.score = score;
        this.image = image;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),
                "NAME: %s\nID: %s",
                this.number,
                this._id);
    }
}
