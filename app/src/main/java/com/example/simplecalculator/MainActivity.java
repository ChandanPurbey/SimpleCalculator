package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView resultTv, solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonMinus,buttonPlus,buttonEqual;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC,R.id.button_c);
        assignId(buttonBrackOpen,R.id.button_open_bracket);
        assignId(buttonBrackClose,R.id.button_close_bracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonMinus,R.id.button_subtract);
        assignId(buttonPlus,R.id.button_plus);
        assignId(buttonEqual,R.id.button_equal);
        assignId(button0,R.id.button_zero);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonC,R.id.button_ac);
        assignId(buttonC,R.id.button_dot);


    }
    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
//        solutionTv.setText(buttonText);
        String dataTocalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(buttonText.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            dataTocalculate = dataTocalculate.substring(0,dataTocalculate.length()-1);
            return;
        }
        else{
            dataTocalculate = dataTocalculate+buttonText;
        }


        solutionTv.setText(dataTocalculate);
        String finalResult = getResult(dataTocalculate);
        if(finalResult.endsWith(".0")){
            finalResult = finalResult.replace(".0","");

        }
        if(!(finalResult.equals("Err"))){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}