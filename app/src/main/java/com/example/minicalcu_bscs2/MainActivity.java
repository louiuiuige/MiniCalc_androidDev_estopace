package com.example.minicalcu_bscs2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText n1, n2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button badd, bmin, bmul, bdiv, bclear;
        badd = findViewById(R.id.btnadd);
        bmin = findViewById(R.id.btnminus);
        bmul = findViewById(R.id.btnmultiply);
        bdiv = findViewById(R.id.btndivide);
        bclear = findViewById(R.id.btnclear);

        n1 = findViewById(R.id.edtnum1);
        n2 = findViewById(R.id.edtnum2);
        result = findViewById(R.id.txtresult);

        badd.setOnClickListener(v -> calculate("+"));
        bmin.setOnClickListener(v -> calculate("-"));
        bmul.setOnClickListener(v -> calculate("*"));
        bdiv.setOnClickListener(v -> calculate("/"));

        bclear.setOnClickListener(v -> {
            n1.setText("");
            n2.setText("");
            result.setText("0.0");
        });
    }

    private void calculate(String operator) {
        try {
            double num1 = Double.parseDouble(n1.getText().toString().trim());
            double num2 = Double.parseDouble(n2.getText().toString().trim());
            double res;

            switch (operator) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    res = num1 / num2;
                    break;
                default:
                    return;
            }

            result.setText(String.valueOf(res));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Missing number/s!", Toast.LENGTH_SHORT).show();
        }
    }
}
