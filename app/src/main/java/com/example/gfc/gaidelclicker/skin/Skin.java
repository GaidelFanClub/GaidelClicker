package com.example.gfc.gaidelclicker.skin;


public class Skin {

    private int faceResourceId;
    private int rotatedBackgroundResourceId;
    private int globalBackgroundId;

    private int fromMonth;
    private int fromDay;

    private int toMonth;
    private int toDay;

    public Skin(int faceResourceId, int rotatedBackgroundResourceId, int globalBackgroundId, int fromMonth, int fromDay, int toMonth, int toDay) {
        this.faceResourceId = faceResourceId;
        this.rotatedBackgroundResourceId = rotatedBackgroundResourceId;
        this.globalBackgroundId = globalBackgroundId;
        this.fromMonth = fromMonth;
        this.fromDay = fromDay;
        this.toMonth = toMonth;
        this.toDay = toDay;
    }

    boolean availableNow(int day, int month) {
        if (fromMonth == toMonth) {
            return fromMonth == month && day >= fromDay && day <= toDay;
        }
        if (month > fromMonth && month < toMonth) {
            return true;
        }
        if (month == fromMonth) {
            return day >= fromDay;
        }
        if (month == toMonth) {
            return day <= toDay;
        }
        return false;
    }

    public int getFaceResourceId() {
        return faceResourceId;
    }

    public int getRotatedBackgroundResourceId() {
        return rotatedBackgroundResourceId;
    }

    public int getGlobalBackgroundId() {
        return globalBackgroundId;
    }
}
