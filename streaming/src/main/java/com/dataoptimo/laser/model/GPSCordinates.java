package com.dataoptimo.laser.model;

import org.json.JSONObject;

/**
 * Created by fawadalam on 25/11/2016.
 */

public final class GPSCordinates implements RecordFormatter {

    public double latitude;
    public double longitude;

    public GPSCordinates(){
        latitude = 0.0;
        longitude = 0.0;
    }

    public GPSCordinates(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("latitude",latitude);
        jsonObject.append("longitude",longitude);
        return jsonObject;
    }

    @Override
    public String toString(){
        return Double.toString(latitude)+","+Double.toString(longitude);
    }
}
