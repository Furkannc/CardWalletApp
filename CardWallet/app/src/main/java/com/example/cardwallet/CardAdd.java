package com.example.cardwallet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CardAdd extends AppCompatActivity {
    ConstraintLayout cardView;

    EditText edtCardName,edtCardNum,edtCardUser,edtExpDate,edtCvv;
    Button btnSave,btnDelete;
    TextView txtCardName,txtCardNum,txtCardUserName,txtCardExpDate,txtCardCvv;
    ImageView cardLogo,imgCopy;
    Context context=this;
    SQLiteHelper db=new SQLiteHelper(context);
    int color=R.drawable.blue;

    Intent intent;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);
        cardLogo=findViewById(R.id.CardIcon);
        txtCardCvv=findViewById(R.id.CardCvv);
        txtCardName=findViewById(R.id.txtCardName);
        txtCardNum=findViewById(R.id.CardNum);
        txtCardExpDate=findViewById(R.id.CardExpDate);
        txtCardUserName=findViewById(R.id.CardUserName);
        cardView= findViewById(R.id.Card);
        edtCardName= findViewById(R.id.edtCardName);
        edtCardNum= findViewById(R.id.edtCardNum);
        edtCardUser= findViewById(R.id.edtCardUserName);
        edtExpDate= findViewById(R.id.edtExpDate);
        edtCvv= findViewById(R.id.edtCVV);
        imgCopy= findViewById(R.id.imgCopy);
        btnSave= findViewById(R.id.btnSave);
        btnDelete= findViewById(R.id.btnDelete);
        intent=getIntent();
        id=intent.getIntExtra("id",-1);

        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txtCardNum.getText().toString();
                ClipData myClip = ClipData.newPlainText("text", text);
                ClipboardManager myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(), "Card Number Copied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(id!=-1){
            btnSave.setText("update Card");
            Card card=db.GetCard(id);
            cardView.setBackgroundResource(card.getColor());
            edtCardName.setText(card.getName());
            edtCardNum.setText(card.getCardNum());
            edtCardUser.setText(card.getCardUserName());
            edtExpDate.setText(card.getExpDate());
            edtCvv.setText(card.getCvv());
            textOnChanged(edtCardName);
            imgCopy.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    public void textOnChanged(View v){
        if (edtCardName.getText().length() != 0) {
            txtCardName.setText(edtCardName.getText().toString());
        }
        if (edtCardNum.getText().length()>15) {
            String Number=edtCardNum.getText().toString().substring(0,4)+" "+edtCardNum.getText().toString().substring(4,8)+" "+edtCardNum.getText().toString().substring(8,12)+" "+edtCardNum.getText().toString().substring(12,16);
            txtCardNum.setText(Number);
            if (Number.charAt(0) == '5') {
                cardLogo.setImageResource(R.drawable.mc);
            } else {
                cardLogo.setImageResource(R.drawable.visa);
            }
        }
        if (edtCardUser.getText().length() != 0) {
            txtCardUserName.setText(edtCardUser.getText().toString());
        }
        if (edtExpDate.getText().length() > 4 ) {
            String expDate = edtExpDate.getText().toString().substring(0, 2) + "/" + edtExpDate.getText().toString().substring(3, 5);
            txtCardExpDate.setText(expDate);
        }
        if (edtCvv.getText().length() != 0) {
            txtCardCvv.setText(edtCvv.getText().toString());
        }
    }
    public void edtChangeColor(int color){
        edtCardUser.setBackgroundResource(color);
        edtCardName.setBackgroundResource(color);
        edtCvv.setBackgroundResource(color);
        edtExpDate.setBackgroundResource(color);
        edtCardNum.setBackgroundResource(color);
    }

    public void btnClickColor(View view){
        switch (view.getId()){
            case R.id.btnRed:
                color=R.drawable.red;
                break;
            case R.id.btnGreen:
                color=R.drawable.green;
                break;
            case R.id.btnYellow:
                color=R.drawable.yellow;
                break;
            case R.id.btnPink:
                color=R.drawable.pink;
                break;
            case R.id.btnPurple:
                color=R.drawable.purple;
                break;
            case R.id.btnOrange:
                color=R.drawable.orange;
                break;
            default:
                color=R.drawable.blue;
        }
        edtChangeColor(color);
        cardView.setBackground(getDrawable(color));

    }
    public void btnDeleteClick(View v){
        AlertDialog.Builder alert=new AlertDialog.Builder(context);
        alert.setTitle("Are You Sure?");
        alert.setMessage("Card will be deleted");
        alert.setNegativeButton("No",null);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteCard(id);
                Toast.makeText(getApplicationContext(),"Card Deleted!",Toast.LENGTH_SHORT);
                ((Activity)context).finish();
            }
        });
        alert.show();
    }

    public boolean control(){
      if(edtCardNum.getText().length()<15){
          Toast.makeText(this,"Card number must be 16 digits",Toast.LENGTH_LONG).show();
          return false;
      }
      if(edtExpDate.getText().length()<=4){
          Toast.makeText(this, "Card expiry date should be like mm/yy",Toast.LENGTH_LONG).show();
          return false;
      }
      if(edtCvv.getText().length()<3){
          Toast.makeText(this, "Card CVV must be 3 digits",Toast.LENGTH_LONG).show();
          return false;
      }
      if(edtCardName.getText().length()==0 && edtCardUser.getText().length()==0 ){
          Toast.makeText(this, "Card name and username must not be empty",Toast.LENGTH_LONG).show();
          return false;
      }
        return true;
    }

    public void BtnSaveClick(View v){
        if(control()){
            if(id!=-1){
                Card card=new Card();
                card.setId(id);
                card.setCardNum(edtCardNum.getText().toString());
                card.setCardUserName(edtCardUser.getText().toString());
                card.setCvv(edtCvv.getText().toString());
                card.setName(edtCardName.getText().toString());
                card.setExpDate(edtExpDate.getText().toString());
                card.setColor(color);
                db.updateCard(card);
                this.finish();
            }else{
                Card card=new Card();
                card.setCardNum(edtCardNum.getText().toString());
                card.setCardUserName(edtCardUser.getText().toString());
                card.setCvv(edtCvv.getText().toString());
                card.setName(edtCardName.getText().toString());
                card.setExpDate(edtExpDate.getText().toString());
                card.setColor(color);
                db.AddCard(card);
                this.finish();
            }
        }
    }
}