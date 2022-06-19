package com.example.cardwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class SQLiteHelper extends SQLiteOpenHelper{
    private static final int database_version = 1;
    private static final String database_name = "CardDB";
    private static final String table_Card= "Card";
    private static final String card_id = "id";
    private static final String card_name = "Card_Name";
    private static final String Color = "color";
    private static final String CardNum = "Card_Num";
    private static final String ExpDate = "Exp_Date";
    private static final String Cvv = "Cvv";
    private static final String CardUserName = "Card_User_Name";

    private static final String[] COLUMNS = {card_id,card_name,Color,CardNum,ExpDate,Cvv,CardUserName};


    private static final String create_soru_table = "CREATE TABLE IF NOT EXISTS "
            + table_Card + " ("
            + card_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + card_name + " TEXT, "
            + Color + " INTEGER, "
            + CardNum + " TEXT, "
            + ExpDate + " TEXT, "
            + Cvv + " TEXT, "
            + CardUserName + " TEXT )"
            ;

    public SQLiteHelper(Context context) {
        super(context, database_name, null, database_version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_soru_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_Card);
        this.onCreate(sqLiteDatabase);
    }
    public void deleteCard(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(table_Card," id = ?",new String[]{String.valueOf(id)});
        db.close();
    }


    public void AddCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put(card_name, card.getName());
        degerler.put(Color, card.getColor());
        degerler.put(CardNum, card.getCardNum());
        degerler.put(ExpDate, card.getExpDate());
        degerler.put(Cvv, card.getCvv());
        degerler.put(CardUserName , card.getCardUserName());
        db.insert(table_Card, null, degerler);
        db.close();
    }

    public void updateCard(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put(card_name, card.getName());
        degerler.put(Color, card.getColor());
        degerler.put(CardNum, card.getCardNum());
        degerler.put(ExpDate, card.getExpDate());
        degerler.put(Cvv, card.getCvv());
        degerler.put(CardUserName , card.getCardUserName());
        db.update(table_Card, degerler, " id = ?",new String[]{String.valueOf(card.getId())});
        db.close();
    }

    public Card GetCard(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(table_Card,COLUMNS," id = ?",
                new String[] {String.valueOf(id)},null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        Card card = new Card();
        card.setId(Integer.parseInt(cursor.getString(0)));
        card.setName(cursor.getString(1));
        card.setColor(cursor.getInt(2));
        card.setCardNum(cursor.getString(3));
        card.setExpDate(cursor.getString(4));
        card.setCvv(cursor.getString(5));
        card.setCardUserName(cursor.getString(6));
        return card;
    }
    public List<Card> GetAllCard()
    {
        List<Card> cardList =new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+table_Card,null,null);
        if(cursor.moveToFirst()) {
            do {
                Card card = new Card();
                card.setId(Integer.parseInt(cursor.getString(0)));
                card.setName(cursor.getString(1));
                card.setColor(cursor.getInt(2));
                card.setCardNum(cursor.getString(3));
                card.setExpDate(cursor.getString(4));
                card.setCvv(cursor.getString(5));
                card.setCardUserName(cursor.getString(6));
                cardList.add(card);
            }while (cursor.moveToNext());
        }

        return cardList;
    }
}

