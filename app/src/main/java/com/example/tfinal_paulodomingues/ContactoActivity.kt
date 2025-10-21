package com.example.tfinal_paulodomingues

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfinal_paulodomingues.databinding.ActivityContactoBinding

class ContactoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityContactoBinding.inflate(layoutInflater) }  // para binding de elementos
    private lateinit var result: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val i = intent
        val nome: String = i.getStringExtra("nome").toString()
        val morada: String = i.getStringExtra("morada").toString()
        val email: String = i.getStringExtra("email").toString()
        val telefone: String = i.getStringExtra("telefone").toString()
        val Novo: Boolean = nome.equals("")

        binding.editNome.setText(nome)
        binding.editMorada.setText(morada)
        binding.editEmail.setText(email)
        binding.editTelefone.setText(telefone)

        // -----------------------------------------------------------------------------
        binding.btnGuardar.setOnClickListener {
            if (binding.editNome.text.toString().isEmpty()) {
                binding.editNome.error = "@string/campo_obrigatorio"
                return@setOnClickListener
            }
            if (binding.editTelefone.text.toString().isEmpty()) {
                binding.editNome.error = "@string/campo_obrigatorio"
                return@setOnClickListener
            }
            val intent = Intent(this, ListaActivity::class.java)
            intent.putExtra("nome", binding.editNome.text.toString())
            intent.putExtra("morada", binding.editMorada.text.toString())
            intent.putExtra("email", binding.editEmail.text.toString())
            intent.putExtra("telefone", binding.editTelefone.text.toString())

            if (Novo)
                setResult( RESULT_FIRST_USER, intent)
            else
                setResult( RESULT_OK, intent)
            finish()
        }

        // -----------------------------------------------------------------------------
        binding.btnCancelar.setOnClickListener {
            finish()
        }

        // -----------------------------------------------------------------------------
        binding.btnEliminar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(100, intent)
            finish()
        }

        // -----------------------------------------------------------------------------
        binding.btnTelefonar.setOnClickListener {
            var telefone = binding.editTelefone.text.toString()

            if (telefone.isNotEmpty() && telefone.length >= 9) {
                if (telefone.length == 9)
                    telefone = "+351" + telefone
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel: " + telefone)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Número de telefone inválido", Toast.LENGTH_SHORT).show()
            }
        }

    }
}