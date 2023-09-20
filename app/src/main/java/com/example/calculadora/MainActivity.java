package com.example.calculadora;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private TextView pantalla;
    private TextView pantallaResultado;
    private String sPantalla;
    private String sResultado;

    private ArrayList<String> expresion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantallaResultado=findViewById(R.id.textResultado);
        pantalla = findViewById(R.id.textPantalla);
        Button b0 = findViewById(R.id.button0);
        b0.setOnClickListener(view -> ponerTexto(b0));
        Button b1 = findViewById(R.id.button1);
        b1.setOnClickListener(view -> ponerTexto(b1));
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(view -> ponerTexto(b2));
        Button b3 = findViewById(R.id.button3);
        b3.setOnClickListener(view -> ponerTexto(b3));
        Button b4 = findViewById(R.id.button4);
        b4.setOnClickListener(view -> ponerTexto(b4));
        Button b5 = findViewById(R.id.button5);
        b5.setOnClickListener(view -> ponerTexto(b5));
        Button b6 = findViewById(R.id.button6);
        b6.setOnClickListener(view -> ponerTexto(b6));
        Button b7 = findViewById(R.id.button7);
        b7.setOnClickListener(view -> ponerTexto(b7));
        Button b8 = findViewById(R.id.button8);
        b8.setOnClickListener(view -> ponerTexto(b8));
        Button b9 = findViewById(R.id.button9);
        b9.setOnClickListener(view -> ponerTexto(b9));
        Button bSuma = findViewById(R.id.buttonSuma);
        bSuma.setOnClickListener(view -> ponerTexto(bSuma));
        Button bResta = findViewById(R.id.buttonResta);
        bResta.setOnClickListener(view -> ponerTexto(bResta));
        Button bDiv = findViewById(R.id.buttonDiv);
        bDiv.setOnClickListener(view -> ponerTexto(bDiv));
        Button bMult = findViewById(R.id.buttonMulti);
        bMult.setOnClickListener(view -> ponerTexto(bMult));
        Button bParenIzq = findViewById(R.id.buttonParenIzq);
        bParenIzq.setOnClickListener(view -> ponerTexto(bParenIzq));
        Button bParenDer = findViewById(R.id.buttonParenDer);
        bParenDer.setOnClickListener(view -> ponerTexto(bParenDer));
        Button bResultado = findViewById(R.id.buttonResultado);
        Button bPunto = findViewById(R.id.buttonPunt);
        bPunto.setOnClickListener(view -> ponerTexto(bPunto));
        bResultado.setOnClickListener(view -> pantallaResultado.setText(evaluarResultado()));
        Button bDelete = findViewById(R.id.buttonDel);
        bDelete.setOnClickListener(view -> borrarDel());
        Button bClear = findViewById(R.id.buttonClear);
        bClear.setOnClickListener(view -> limpiarPantalla());
    }

    private void ponerTexto(Button bActual) {

        if (sPantalla != null && !sPantalla.isEmpty()) {
            if (sPantalla.length() >= 35){return;}

            char simbolo = sPantalla.charAt(sPantalla.length() - 1);
            char simboloB = bActual.getText().charAt(0);
            if (simbolo == '+' || simbolo == '-' || simbolo == '*' || simbolo == '/' ) {
                if (simboloB == '+' || simboloB == '-' || simboloB == '*' || simboloB == '/' ) {
                    return;
                }
            }
            sPantalla = pantalla.getText().toString() + bActual.getText().toString();
            pantalla.setText(sPantalla);
        }
        else{
            char simboloB = bActual.getText().charAt(0);
            if (simboloB == '+' || simboloB == '-' || simboloB == '*' || simboloB == '/' ){
                return;
            }
            sPantalla = bActual.getText().toString();
            pantalla.setText(sPantalla);
        }
    }

    private void borrarDel(){
        if (sPantalla != null && !sPantalla.isEmpty()) {
            sPantalla = sPantalla.substring(0, sPantalla.length() - 1);
            pantalla.setText(sPantalla);
        }
    }

    private void limpiarPantalla(){
        pantalla.setText("");
        pantallaResultado.setText("");
    }

    private String evaluarResultado(){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            sResultado = context.evaluateString(scriptable,pantalla.getText().toString(),"Javascript",1,null).toString();
            return sResultado;

        }catch(Exception e){
            return "Error";
        }
    }
}