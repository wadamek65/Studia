package com.luis_castro_1996hotmail.kalkulator_wadamek65;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

import org.w3c.dom.Text;

public class Kalkulator extends AppCompatActivity {
    TextView textBar;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button button_plus;
    Button button_minus;
    Button button_multi;
    Button button_div;
    Button button_sqrt;
    Button button_square;
    Button button_equals;
    Button button_clear;
    TextView historia_adapt;
    String operation_history = "";
    private double value1 = 0;
    private double value2 = 0;
    private String last_op = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitykalkulator);
        textBar = (TextView)findViewById(R.id.inputText);
        button1 = (Button)findViewById(R.id.number1);
        button2 = (Button)findViewById(R.id.number2);
        button3 = (Button)findViewById(R.id.number3);
        button4 = (Button)findViewById(R.id.number4);
        button5 = (Button)findViewById(R.id.number5);
        button6 = (Button)findViewById(R.id.number6);
        button7 = (Button)findViewById(R.id.number7);
        button8 = (Button)findViewById(R.id.number8);
        button9 = (Button)findViewById(R.id.number9);
        button0 = (Button)findViewById(R.id.number0);
        button_equals = (Button)findViewById(R.id.operation_result);
        button_plus = (Button)findViewById(R.id.operation_plus);
        button_minus = (Button)findViewById(R.id.operation_minus);
        button_multi = (Button)findViewById(R.id.operation_multi);
        button_div = (Button)findViewById(R.id.operation_div);
        button_sqrt = (Button)findViewById(R.id.operation_sqrt);
        button_square = (Button)findViewById(R.id.operation_square);
        button_clear = (Button)findViewById(R.id.operation_clear);
        historia_adapt = (TextView)findViewById(R.id.text_history);

        button_clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                value1 = 0;
                value2 = 0;
                textBar.setText("");
            }
        });
        button_sqrt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(textBar.getText()!="") {
                    value1 = Math.sqrt(Double.parseDouble(textBar.getText().toString()));
                    value2 = 0;
                    operation_history += ("sqrt("+String.valueOf(textBar.getText().toString())+")="+String.valueOf(value1)+"\n-----------------------------\n");
                    historia_adapt.setText(operation_history);
                    textBar.setText(String.valueOf(value1));
                }
            }
        });
        button_square.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(textBar.getText()!="") {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    operation_history += (String.valueOf(value1)+"^2"+"="+String.valueOf(value1*value1)+"\n-----------------------------\n");
                    historia_adapt.setText(operation_history);
                    value1 = value1*value1;
                    value2 = 0;
                    textBar.setText(String.valueOf(value1));
                }
            }
        });
        button_plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (value1 == 0 && value2 == 0) {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="plus";
                } else if (value1 != 0 && value2 == 0 && last_op != "") {
                    value2 = Double.parseDouble(textBar.getText().toString());
                    last_op="";
                    operation_history += (String.valueOf(value1)+"+"+String.valueOf(value2)+"="+String.valueOf(value1+value2)+"\n-----------------------------\n");
                    historia_adapt.setText(operation_history);
                    value1 = value1+value2;
                    textBar.setText(String.valueOf(value1));

                    value2 = 0;
                } else if(value1 !=0 && value2 ==0 && last_op == "") {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="plus";
                }
            }
        });
        button_minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (value1 == 0 && value2 == 0) {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="minus";
                } else if (value1 != 0 && value2 == 0 && last_op != "") {
                    value2 = Double.parseDouble(textBar.getText().toString());
                    last_op="";
                    operation_history += (String.valueOf(value1)+"-"+String.valueOf(value2)+"="+String.valueOf(value1-value2)+"\n-----------------------------\n");
                    historia_adapt.setText(operation_history);
                    value1 = value1-value2;
                    textBar.setText(String.valueOf(value1));
                    value2 = 0;
                } else if(value1 !=0 && value2 ==0 && last_op == "") {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="minus";
                }
            }
        });
        button_multi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (value1 == 0 && value2 == 0) {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="multi";
                } else if (value1 != 0 && value2 == 0 && last_op != "") {
                    value2 = Double.parseDouble(textBar.getText().toString());
                    last_op="";
                    operation_history += (String.valueOf(value1)+"*"+String.valueOf(value2)+"="+String.valueOf(value1*value2)+"\n-----------------------------\n");
                    historia_adapt.setText(operation_history);
                    value1 = value1*value2;
                    textBar.setText(String.valueOf(value1));
                    value2 = 0;
                } else if(value1 !=0 && value2 ==0 && last_op == "") {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="multi";
                }
            }
        });
        button_div.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (value1 == 0 && value2 == 0) {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="div";
                } else if (value1 != 0 && value2 == 0 && last_op != "") {
                    value2 = Double.parseDouble(textBar.getText().toString());
                    last_op="";
                    operation_history += (String.valueOf(value1)+"/"+String.valueOf(value2)+"="+String.valueOf(value1/value2)+"\n-----------------------------\n");
                    historia_adapt.setText(operation_history);
                    value1 = value1/value2;
                    textBar.setText(String.valueOf(value1));
                    value2 = 0;
                } else if(value1 !=0 && value2 ==0 && last_op == "") {
                    value1 = Double.parseDouble(textBar.getText().toString());
                    textBar.setText("");
                    last_op="div";
                }
            }
        });
        button_equals.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (value1 != 0 && value2 == 0 && textBar.getText()!="") {
                    value2 = Double.parseDouble(textBar.getText().toString());
                }
                if (value1 != 0 && value2 != 0) {
                    if (last_op == "plus") {
                        operation_history += (String.valueOf(value1) + "+" + String.valueOf(value2) + "=" + String.valueOf(value1 + value2) + "\n-----------------------------\n");
                        historia_adapt.setText(operation_history);
                        value1 = value1 + value2;
                        last_op = "";
                        textBar.setText(String.valueOf(value1));
                        value2 = 0;
                    } else if (last_op == "minus") {
                        operation_history += (String.valueOf(value1) + "-" + String.valueOf(value2) + "=" + String.valueOf(value1 - value2) + "\n-----------------------------\n");
                        historia_adapt.setText(operation_history);
                        value1 = value1 - value2;
                        last_op = "";
                        textBar.setText(String.valueOf(value1));
                        value2 = 0;
                    } else if (last_op == "multi") {
                        operation_history += (String.valueOf(value1) + "*" + String.valueOf(value2) + "=" + String.valueOf(value1 * value2) + "\n-----------------------------\n");
                        historia_adapt.setText(operation_history);
                        value1 = value1 * value2;
                        last_op = "";
                        textBar.setText(String.valueOf(value1));
                        value2 = 0;
                    } else if (last_op == "div") {
                        operation_history += (String.valueOf(value1) + "/" + String.valueOf(value2) + "=" + String.valueOf(value1 / value2) + "\n-----------------------------\n");
                        historia_adapt.setText(operation_history);
                        value1 = value1 / value2;
                        last_op = "";
                        textBar.setText(String.valueOf(value1));
                        value2 = 0;
                    }
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"9");
            }
        });
        button0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textBar.setText(textBar.getText()+"0");
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kalkulator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
