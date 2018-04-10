package com.example.shaheed.takeaclimbchallenge;

import java.util.Date;
import java.util.Timer;

/**
 * Created by shaheed on 4/7/18.
 */

public class Medicine {

    String medName, description;
    String interval;
    String startDate, endDate;

    public Medicine(String medName, String description, String interval, String startDate, String endDate){
        this.medName = medName;
        this.description = description;
        this.interval = interval;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return medName;
    }

    public void setName(String name) {
        this.medName = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
