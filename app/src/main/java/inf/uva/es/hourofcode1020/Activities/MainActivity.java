package inf.uva.es.hourofcode1020.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import inf.uva.es.hourofcode1020.Model.Calculator;
import inf.uva.es.hourofcode1020.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity principal usada para el taller de Android impartido en la Escuela de Ingenieria Informatica de Valladolid,
 * como parte del evento Hour Of Code del curso 19/20.
 *
 * @author Ivan Gonzalez Rincon.
 * @author Manuel Mendez Calvo.
 * @author David Melendez Diez.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_number1, et_number2;
    private TextView tv_resultado;
    private Button btn_sumar, btn_restar, btn_multiplicar, btn_dividir;
    private Calculator calculator;

    double number1, number2, resultado;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActivity();


    }

    /**
     * Este metodo enlaza todos los elementos graficos con el .xml de la interfaz.
     */
    private void setActivity() {
        et_number1 = (EditText) findViewById(R.id.et_operando1);
        et_number2 = (EditText) findViewById(R.id.et_operando2);

        tv_resultado = (TextView) findViewById(R.id.tv_resultado);

        btn_sumar = (Button) findViewById(R.id.btn_sumar);
        btn_sumar.setOnClickListener(this);
        btn_restar = (Button) findViewById(R.id.btn_restar);
        btn_restar.setOnClickListener(this);
        btn_multiplicar = (Button) findViewById(R.id.btn_multiplicar);
        btn_multiplicar.setOnClickListener(this);
        btn_dividir = (Button) findViewById(R.id.btn_dividir);
        btn_dividir.setOnClickListener(this);

        calculator = new Calculator();
    }

    /**
     * Funcion que comprueba si se puede realizar la operacion en la calculadora
     *
     * @return true en caso de poderse hacer la operacion. False en caso contrario.
     */
    private boolean isInfoCorrect() {
        String strNumber1 = et_number1.getText().toString();
        String strNumber2 = et_number2.getText().toString();

        boolean isCorrect = true;
        //Comprueba si los valores del modelo han sido modificados, pues solo son NaN en caso de haber reseteado la calculadora.
        if (calculator.getA() != Double.NaN && calculator.getB() != Double.NaN) {
            //Se comprueba si el usuario ha introducido informacion para poder realizar la operacion
            if (!"".equals(strNumber1) && null != strNumber1) {
                number1 = Double.parseDouble(strNumber1);
                calculator.setA(number1);
            } else {
                isCorrect = false;
            }

            if (!"".equals(strNumber2) && null != strNumber2) {
                number2 = Double.parseDouble(strNumber2);
                calculator.setB(number2);
            } else {
                isCorrect = false;

            }
        } else {
            return false;
        }

        return isCorrect;

    }

    /**
     * Metodo que modifica el TextView del resultado de la calculadora.
     */
    private void setResultView() {
        tv_resultado.setText(tv_resultado.getText().toString() + resultado);
    }

    /**
     * Metodo que crea un dialogo dentro de nuestro Activity.
     *
     * @param context Contexto del Activity.
     * @param result  Resultado de la operacion realizada.
     */
    private void createDialog(Context context, double result) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(context.getString(R.string.dialog_title));

        // set dialog message
        alertDialogBuilder
                .setMessage(context.getString(R.string.dialog_message) + "\nResultado de la operación:" + String.valueOf(result))
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.dialog_possitiveButton), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close dialog
                        et_number1.getText().clear();
                        et_number2.getText().clear();
                        calculator.setA(Double.NaN);
                        calculator.setB(Double.NaN);
                        tv_resultado.setText("");
                        dialog.cancel();
                    }
                }).setNegativeButton(context.getString(R.string.dialog_negativeButton), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // if this button is clicked, close dialog

                dialog.cancel();
            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (isInfoCorrect()) {
            calculator.setA(number1);
            calculator.setB(number2);
            tv_resultado.setText("");
            switch (id) {
                case R.id.btn_sumar:
                    resultado = calculator.sumar(number1, number2);
                    break;
                case R.id.btn_restar:
                    resultado = calculator.restar(number1, number2);
                    break;
                case R.id.btn_multiplicar:
                    resultado = calculator.multiplicar(number1, number2);
                    break;
                case R.id.btn_dividir:
                    if (number2 == 0) {
                        Toast.makeText(MainActivity.this, R.string.divisionPorZero, Toast.LENGTH_SHORT).show();
                    } else {
                        resultado = calculator.dividir(number1, number2);
                    }
                    break;
            }
            resultado = calculator.parseResult(resultado);
            calculator.setResult(resultado);
            setResultView();
            createDialog(this, calculator.getResult());
            et_number1.requestFocus();
        } else {
            toast = Toast.makeText(MainActivity.this, R.string.toast_noOperando, Toast.LENGTH_SHORT);
            toast.show();
        }

    }


}
