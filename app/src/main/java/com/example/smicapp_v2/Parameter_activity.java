package com.example.smicapp_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Parameter_activity extends AppCompatActivity {
    private RadioGroup RG_Background, RG_YearMonth;
    private RadioButton RB_Couleur, RB_YearMonth;
    private Button backButton;
    private Button applyButton;
    private RelativeLayout param;
    private EditText TVDevise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_activity);

        RG_Background = findViewById(R.id.RG_Fond);
        RG_YearMonth = findViewById(R.id.RG_AnsMois);
        backButton = findViewById(R.id.BTN_retour);
        param = findViewById(R.id.RL_param);
        applyButton = findViewById(R.id.BTN_Enregistrer);
        TVDevise = findViewById(R.id.TV_devise);

        activateColor();

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backGroundID = RG_Background.getCheckedRadioButtonId();
                int yearMonthID = RG_YearMonth.getCheckedRadioButtonId();

                RB_Couleur = findViewById(backGroundID);
                RB_YearMonth = findViewById(yearMonthID);

                saveUserColor();
                saveUserDevise();
                saveUserYearMonth();
                activateColor();

                Intent ParamActivity = new Intent(Parameter_activity.this, MainActivity.class);
                startActivity(ParamActivity);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ParamActivity = new Intent(Parameter_activity.this, MainActivity.class);
                startActivity(ParamActivity);
            }
        });
    }

    //on attribue les id a nos objets
    public void checkButtonColor(View v) {
        int radioID = RG_Background.getCheckedRadioButtonId();
        RB_Couleur = findViewById(radioID);
    }

    //on attribue les id a nos objets
    public void checkButtonYearMonth(View v) {
        int radioID = RG_YearMonth.getCheckedRadioButtonId();
        RB_YearMonth = findViewById(radioID);
    }

    //sauvegarde de la couleur choisie
    public void saveUserColor() {
        SharedPreferences preferences = getSharedPreferences("MesPreferences", 0);
        SharedPreferences.Editor editor = preferences.edit();
        String colorUser = (String) RB_Couleur.getText();
        editor.putString("color", colorUser);
        editor.apply();
    }

    //sauvegarde de la devise choisie
    public void saveUserDevise() {
        SharedPreferences preferences = getSharedPreferences("MesPreferences", 0);
        SharedPreferences.Editor editor = preferences.edit();
        String deviseUser = String.valueOf(TVDevise.getText());
        editor.putString("devise", deviseUser);
        editor.apply();
    }
    //sauvegarde du yearmonth
    public void saveUserYearMonth() {
        SharedPreferences preferences = getSharedPreferences("MesPreferences", 0);
        SharedPreferences.Editor editor = preferences.edit();
        String yearMonthUser = (String) RB_YearMonth.getText();
        editor.putString("yearMonth", yearMonthUser);
        editor.apply();
    }

    //activation de la couleur choisi sur la page des parametres
    public void activateColor() {
        SharedPreferences preferences = getSharedPreferences("MesPreferences", 0);
        String color = preferences.getString("color", "");
        changeBackgroundColor(color);
    }

    //affichage de la couleur choisi sur main
    public void changeBackgroundColor(String color) {
        switch (color) {
            case "Bleu":
                param.setBackgroundColor(Color.BLUE);
                break;
            case "Vert":
                param.setBackgroundColor(Color.GREEN);
                break;
            case "Gris":
                param.setBackgroundColor(Color.GRAY);
                break;
        }
    }
}
