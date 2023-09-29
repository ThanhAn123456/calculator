package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txt_result,txt_solution;
    MaterialButton btn_C,btn_BrackOpen,btn_BrackClose,btn_AC, btn_Dot;
    MaterialButton btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0;
    MaterialButton btn_Divide,btn_Multiply,btn_Plus,btn_Minus,btn_Equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_result = findViewById(R.id.txt_result);
        txt_solution = findViewById(R.id.txt_solution);
        assignId(btn_C,R.id.btn_C);
        assignId(btn_BrackOpen,R.id.btn_Open_bracket);
        assignId(btn_BrackClose,R.id.btn_Close_braket);
        assignId(btn_AC,R.id.btn_AC);
        assignId(btn_Dot,R.id.btn_Dot);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);
        assignId(btn_0,R.id.btn_0);
        assignId(btn_Divide,R.id.btn_Divide);
        assignId(btn_Multiply,R.id.btn_Multiply);
        assignId(btn_Plus,R.id.btn_Plus);
        assignId(btn_Minus,R.id.btn_Minus);
        assignId(btn_Equals,R.id.btn_Equals);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton Button = (MaterialButton) view;
        String btnText = Button.getText().toString();
        String Datacalculate = txt_solution.getText().toString();

        if (btnText.equals("AC")){
            txt_solution.setText("");
            txt_result.setText("0");
            return;
        }
        if (btnText.equals("=")){
            txt_solution.setText(txt_result.getText().toString());
            return;
        }
        if(btnText.equals("C")){
            Datacalculate = Datacalculate.substring(0,Datacalculate.length()-1);
        }else {
            Datacalculate = Datacalculate+btnText;
        }
        txt_solution.setText(Datacalculate);
        String finalResult = getResult(Datacalculate);
        if(!finalResult.equals("Err")){
            txt_result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }

    }
}