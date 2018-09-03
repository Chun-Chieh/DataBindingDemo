package com.chunchiehliang.networktest.model;


/**
 * @author Chun-Chieh Liang on 7/21/18.
 */
public class Earthquake {

    private double magnitude;
    private String originalLocation;
    private long timeInMilliseconds;
    private String url;

    // format from location
    private static final String LOCATION_SEPARATOR = " of ";
    private String locationOffset;
    private String primaryLocation;


    public Earthquake(double magnitude, String originalLocation, long timeInMilliseconds) {
        this.magnitude = magnitude;
        this.originalLocation = originalLocation;
        this.timeInMilliseconds = timeInMilliseconds;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            primaryLocation = originalLocation;
        }
    }

    public Earthquake(double magnitude, String originalLocation, long timeInMilliseconds, String url) {
        this(magnitude, originalLocation, timeInMilliseconds);
        this.url = url;
    }


    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public void setLocation(String originalLocation) {
        this.originalLocation = originalLocation;
    }

    public void setTime(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return originalLocation;
    }

    public long getTime() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }

    public String getLocationOffset() {
        return locationOffset;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }
}
