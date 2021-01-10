package com.example.smartparking;

public class Data {

    int isBookedByApp1;
    int isBookedByApp2;
    int isBookedByApp3;
    int isBookedByApp4;
    int slot1;
    int slot2;
    int slot3;
    int slot4;

    public Data()
    {

    }

    public Data(int isBookedByApp1, int isBookedByApp2, int isBookedByApp3, int isBookedByApp4, int slot1, int slot2, int slot3, int slot4) {
        this.isBookedByApp1 = isBookedByApp1;
        this.isBookedByApp2 = isBookedByApp2;
        this.isBookedByApp3 = isBookedByApp3;
        this.isBookedByApp4 = isBookedByApp4;
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
    }

    public int getIsBookedByApp1() {
        return isBookedByApp1;
    }

    public void setIsBookedByApp1(int isBookedByApp1) {
        this.isBookedByApp1 = isBookedByApp1;
    }

    public int getIsBookedByApp2() {
        return isBookedByApp2;
    }

    public void setIsBookedByApp2(int isBookedByApp2) {
        this.isBookedByApp2 = isBookedByApp2;
    }

    public int getIsBookedByApp3() {
        return isBookedByApp3;
    }

    public void setIsBookedByApp3(int isBookedByApp3) {
        this.isBookedByApp3 = isBookedByApp3;
    }

    public int getIsBookedByApp4() {
        return isBookedByApp4;
    }

    public void setIsBookedByApp4(int isBookedByApp4) {
        this.isBookedByApp4 = isBookedByApp4;
    }

    public int getSlot1() {
        return slot1;
    }

    public void setSlot1(int slot1) {
        this.slot1 = slot1;
    }

    public int getSlot2() {
        return slot2;
    }

    public void setSlot2(int slot2) {
        this.slot2 = slot2;
    }

    public int getSlot3() {
        return slot3;
    }

    public void setSlot3(int slot3) {
        this.slot3 = slot3;
    }

    public int getSlot4() {
        return slot4;
    }

    public void setSlot4(int slot4) {
        this.slot4 = slot4;
    }
}
