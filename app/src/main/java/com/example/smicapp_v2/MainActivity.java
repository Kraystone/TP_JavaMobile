package com.example.smicapp_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView param;
    private Button calcul;
    private EditText result;
    private TextView TV_result;
    private RelativeLayout main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        param = (ImageView) findViewById(R.id.IMG_Param);
        calcul = (Button) findViewById(R.id.BTN_Calculer);
        TV_result = (TextView) findViewById(R.id.TV_result);
        main = findViewById(R.id.MainFond);

        activateColor();
        activateUserDevise();
        activateUserYearMonth();

        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ParamActivity = new Intent(MainActivity.this, Parameter_activity.class);
                startActivity(ParamActivity);
            }
        });
        calcul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                result = findViewById(R.id.ET_result);
                String temp=result.getText().toString();
                int input=0;
                if (!"".equals(temp)){
                    input=Integer.parseInt(temp);
                }
                TV_result.setText("Votre salaire net est de " + calculate(input, activateUserYearMonth()) +" "+  activateUserDevise() + " par " + activateUserYearMonth() + ".");
            }
        });

    }
    //calcul du salaire
    public int calculate(int nb, String pref){

        if (pref.equals("Mois")){
            nb = (nb*20) / 100;
        }
        else
            nb = ((nb*12)*20) / 100;
        return nb;
    }
    //activation de la couleur choisi sur main
    public void activateColor(){
        SharedPreferences preferences = getSharedPreferences("MesPreferences", 0);
        String color  = preferences.getString("color", "");
        changeBackgroundColor(color);
    }

    //On recupère la devise
    public String activateUserDevise(){
        SharedPreferences preferences = getSharedPreferences("MesPreferences", 0);
        return preferences.getString("devise", "");
    }

    //on recupère yearMonth
    public String activateUserYearMonth(){
        SharedPreferences preferences = getSharedPreferences("MesPreferences", 0);
        Log.d("YEARMONTH", preferences.getString("yearMonth", ""));
        return preferences.getString("yearMonth", "");
    }

    //affichage de la couleur choisi sur main
    public void changeBackgroundColor(String color) {
        switch(color) {
            case "Bleu":
                main.setBackgroundColor(Color.BLUE);
                break;
            case "Vert":
                main.setBackgroundColor(Color.GREEN);
                break;
            case "Gris":
                main.setBackgroundColor(Color.GRAY);
                break;
        }
    }
}