package com.example.dmoex_cadcli

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialização dos elementos da interface do usuário
        var editTextNome:      EditText    = findViewById(R.id.editTextNome)
        var editTextEmail:     EditText    = findViewById(R.id.editTextEmail)
        var editTextTelefone:  EditText    = findViewById(R.id.editTextEmail)
        var editTextCpf:       EditText    = findViewById(R.id.editTextEmail)
        var editTextEndereco:  EditText    = findViewById(R.id.editTextEmail)
        var editTextCidade:    EditText    = findViewById(R.id.editTextEmail)
        var buttonCalculate:   Button      = findViewById(R.id.buttonCalculate)

        val listaPessoa = ArrayList<Pessoa>()
        val dadosRecebidos = intent.extras

        val pessoaSerializable = dadosRecebidos?.getSerializable("listaPessoa")
        if (pessoaSerializable is ArrayList<*>) {
            listaPessoa.addAll(pessoaSerializable.filterIsInstance<Pessoa>())
        }

        // Configuração do listener para o botão de cálculo
        buttonCalculate.setOnClickListener {

            val intent = Intent(this, ConsultaActivity::class.java)



            val pessoa = Pessoa(editTextNome.text.toString(),
                                editTextEmail.text.toString(),
                                editTextTelefone.text.toString(),
                                editTextCpf.text.toString(),
                                editTextEndereco.text.toString(),
                                editTextCidade.text.toString(),
            )

            listaPessoa.add(pessoa)

            //val nome = editTextNome.text.toString()

            intent.putExtra("listaPessoa", listaPessoa)

            startActivity(intent)

        }
    }
}

class Pessoa(
    val editTextNome: String,
    val editTextEmail: String,
    val editTextTelefone: String,
    val editTextCpf: String,
    val editTextEndereco: String,
    val editTextCidade: String
): Serializable

