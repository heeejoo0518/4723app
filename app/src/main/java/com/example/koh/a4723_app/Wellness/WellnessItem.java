package com.example.koh.a4723_app.Wellness;

public class WellnessItem {
    private int profile;
    private String city;
    private String wellness;

    public int getProfile() {
        return profile;
    }

    public String getCity() {
        return city;
    }

    public String getWellness() {
        return wellness;
    }

    public WellnessItem(int profile, String city,String wellness) {
        this.profile = profile;
        this.city = city;
        this.wellness = wellness;

    }

}
