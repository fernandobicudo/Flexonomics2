package com.fernando.flexonomics;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private final double givenRatio = 0.70;


    private double gasPrice;
    private double ethanolPrice;
    private double resultRatio;


    private TextView valorGasolinaTextView;
    private TextView valorEthanolTextView;
    private TextInputEditText resultadoTextInputEditText;
    private SeekBar gasolinaSeekBar;
    private SeekBar etanolSeekBar;

    private ImageView ilustracaoImageView;


    private double calculate(double gasPrice, double ethanolPrice) {

        return ethanolPrice / gasPrice;

    }

    private void result() {

        resultRatio = calculate(gasPrice, ethanolPrice);

        //resultadoTextInputEditText.setText(Double.toString(resultRatio)); //TESTE

        if (resultRatio < givenRatio) {
            resultadoTextInputEditText.setText(R.string.resultEthanol);
            ilustracaoImageView.setImageResource(R.drawable.etanol);

        }
        else {
            resultadoTextInputEditText.setText(R.string.resultGasoline);
            ilustracaoImageView.setImageResource(R.drawable.gasolina);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorGasolinaTextView = findViewById(R.id.valorGasolinaTextView);
        valorEthanolTextView = findViewById(R.id.valorEtanolTextView);
        resultadoTextInputEditText = findViewById(R.id.resultadoTextInputEditText);
        gasolinaSeekBar = findViewById(R.id.gasolinaSeekBar);
        etanolSeekBar = findViewById(R.id.etanolSeekBar);
        ilustracaoImageView = findViewById(R.id.ilustracaoImageView);

        gasolinaSeekBar.setOnSeekBarChangeListener(gSeekBarChangeListener);

        etanolSeekBar.setOnSeekBarChangeListener(eSeekBarChangeListener);


    }
    private SeekBar.OnSeekBarChangeListener gSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            valorGasolinaTextView.setText(currencyFormat.format((double)progress/10.0));
            gasPrice = progress/10.0;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
           if (gasPrice > 0)
               result();
        }
    };

    private SeekBar.OnSeekBarChangeListener eSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            NumberFormat currencyFormat =  NumberFormat.getCurrencyInstance();
            valorEthanolTextView.setText(currencyFormat.format((double)progress/10.0));
            ethanolPrice = progress /10.0;
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (gasPrice > 0)
                result();

        }
    };
//

}

/*
//1-texto de resultado precisa ser mantido em vários idiomas -OK
2- conta não está batendo - OK
3- foto precisa ser trocada conforme resultado - set dinamically OK
4- arrumar cifra do currency - ??????
5- ícone da aplicação - OK
6- arrumar cor do TextInput -?????
//*/
