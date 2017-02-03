package com.dataoptimo.laser.model;

import org.json.JSONObject;

/**
 * Created by fawadalam on 29/01/2017.
 */


public interface RecordFormatter {

    public JSONObject toJson();
    public String toString();


}
