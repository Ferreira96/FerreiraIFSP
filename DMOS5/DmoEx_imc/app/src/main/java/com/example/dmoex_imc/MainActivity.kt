package com.example.dmoex_imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import kotlin.math.pow
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização dos elementos da interface do usuário
        var valPeso:     EditText = findViewById(R.id.editTextPeso)
        var valAltura:   EditText = findViewById(R.id.editTextAltura)
        var valBtCalc:   Button   = findViewById(R.id.buttonCalcular)
        var valTxResult: TextView = findViewById(R.id.textViewResultado)

        // Configuração do listener para o botão de cálculo
        valBtCalc.setOnClickListener {
            val peso = valPeso.text.toString().toFloatOrNull()
            val altura = valAltura.text.toString().toFloatOrNull()

            if (peso != null && altura != null && altura > 0 && peso > 0) {
                val imc = peso / altura.pow(2)
                val result = "Seu IMC e: %.2f\nVoce esta ${getStatus(imc)}".format(imc)
                valTxResult.text = result
            } else {
                valTxResult.text = "Valor invalido."
            }
        }

        // Listener para arrumar com o recuo causado pelas barras de sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom)
            WindowInsetsCompat.CONSUMED
        }

    }

    // Método para obter o status do IMC
    private fun getStatus(imc: Float): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 24.9 -> "Dentro do peso"
            imc < 29.9 -> "Acima do peso"
            else -> "Obeso"
        }
    }
}
