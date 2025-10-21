package com.example.tfinal_paulodomingues

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfinal_paulodomingues.databinding.ActivityPinBinding

class PinActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPinBinding.inflate(layoutInflater) }  // para binding de elementos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = this.getSharedPreferences("pin", MODE_PRIVATE)
        val pinatual: String = sharedPref.getString("pin", "1234").toString()

        // -----------------------------------------------------------------
        binding.btnVoltar.setOnClickListener {
            finish()
        }

        // -----------------------------------------------------------------
        binding.btnConfirmar.setOnClickListener {
            val pin = binding.editPinAtual.text.toString()
            if (pin == pinatual) {
                val pin1 = binding.editPinNovo1.text.toString()
                val pin2 = binding.editPinNovo2.text.toString()
                if (!pin1.isEmpty() && pin1 == pin2) {
                    sharedPref.edit {
                        putString("pin", pin1)
                        apply()
                    }
                    finish()
                } else {
                    Toast.makeText(this, "Pin's diferentes", Toast.LENGTH_SHORT).show()
                    binding.editPinNovo1.text.clear()
                    binding.editPinNovo2.text.clear()
                }
            } else {
                Toast.makeText(this, "Pin original Errado", Toast.LENGTH_SHORT).show()
                binding.editPinAtual.text.clear()
            }
        }

        // -----------------------------------------------------------------
    }
}