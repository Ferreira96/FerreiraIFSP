package com.example.dmoex_cadcli

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConsultaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)

        val buttonVoltar: Button = findViewById(R.id.buttonVoltar)
        val textViewResult: TextView = findViewById(R.id.TextViewResult)

        val dadosRecebidos = intent.extras
        var listaPessoa: ArrayList<Pessoa>? = null // Inicializa como null

        if (dadosRecebidos != null) {
            listaPessoa = dadosRecebidos.getSerializable("listaPessoa") as? ArrayList<Pessoa>
        } else {
            textViewResult.text = "Nenhum dado recebido"
        }

        if (listaPessoa != null) {
            val resultText = buildString {
                for (pessoa in listaPessoa) {
                    append("Nome: ${pessoa.editTextNome}\n")
                    append("Email: ${pessoa.editTextEmail}\n")
                    append("Telefone: ${pessoa.editTextTelefone}\n")
                    append("CPF: ${pessoa.editTextCpf}\n")
                    append("Endereço: ${pessoa.editTextEndereco}\n")
                    append("Cidade: ${pessoa.editTextCidade}\n\n")
                }
            }
            textViewResult.text = resultText
        } else {
            textViewResult.text = "Erro ao recuperar os dados da pessoa"
        }


        // Configuração do listener para o botão de voltar
        buttonVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("listaPessoa", listaPessoa)
            startActivity(intent)
        }
    }
}

