package com.example.cardwallet;


public class Card {
    int id;
    String Name;
    int Color;
    String CardNum;
    String ExpDate;
    String Cvv;
    String CardUserName;

    public Card() {
    }

    public Card(String name, int color, String cardNum, String expDate, String cvv, String cardUserName) {
        Name = name;
        Color = color;
        CardNum = cardNum;
        ExpDate = expDate;
        Cvv = cvv;
        CardUserName = cardUserName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public String getCardNum() {
        return CardNum;
    }

    public void setCardNum(String cardNum) {
        CardNum = cardNum;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }

    public String getCvv() {
        return Cvv;
    }

    public void setCvv(String cvv) {
        Cvv = cvv;
    }

    public String getCardUserName() {
        return CardUserName;
    }

    public void setCardUserName(String cardUserName) {
        CardUserName = cardUserName;
    }
}
