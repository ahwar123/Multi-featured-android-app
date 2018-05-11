package com.example.ahwar.project;

/**
 * Created by Ahwar on 1/5/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class calculater extends Activity {
    private TextView _screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);
        _screen = (TextView)findViewById(R.id.textView);
        _screen.setText(display);
    }

    private void updateScreen()
    {
        _screen.setText(display);
    }

    public void onClickNumber(View v){
        if(result != ""){
            clear();
            updateScreen();
        }
        Button b = (Button) v;
        display = display+b.getText();
        updateScreen();

    }

    public void onClickBack(View v) {
        if (display.length() >= 1) {
            display = display.substring(0, display.length() - 1);
            _screen.setText(display);

        }
        else if (display.length() <= 1) {
            _screen.setText("0");
        }
    }
    private boolean isOperator(char op){
        switch (op){
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default: return false;
        }
    }

    public void onClickOperator(View v){
        if(display == "")
            return;

        Button b = (Button)v;

        if(result != ""){
            String _display = result;
            clear();
            display = _display;
        }

        if(currentOperator != ""){
            if(isOperator(display.charAt(display.length()-1))){
                display = display.replace(display.charAt(display.length()-1), b.getText().charAt(0));
                updateScreen();
                return;
            }
            else
            {
                getResult();
                display = result;
                result = "";
            }
            currentOperator = b.getText().toString();
        }

        display =display+ b.getText();
        currentOperator = b.getText().toString();
        updateScreen();
    }

    private void clear(){
        display = "";
        currentOperator = "";
        result = "";
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }


    private double operate(String a, String b, String op){
        switch (op){
            case "+":
			return Double.valueOf(a) + Double.valueOf(b);
            case "-":
			return Double.valueOf(a) - Double.valueOf(b);
            case "*": 
			return Double.valueOf(a) * Double.valueOf(b);
            case "/":
            try
            {
                return Double.valueOf(a) / Double.valueOf(b);
            }
            catch (Exception e){
                Log.d("Calc", e.getMessage());
            }
            default:
                return -1;
        }
    }

    private boolean getResult(){

        if(currentOperator == "")
            return false;

        String[] operation = display.split(Pattern.quote(currentOperator));

        if(operation.length < 2)
            return false;

        result = String.valueOf(operate(operation[0], operation[1], currentOperator));

        return true;
    }

    public void onClickEqual(View v){
        if(display == "")
            return;
        if(!getResult())
            return;
        _screen.setText(String.valueOf(result));
    }
}