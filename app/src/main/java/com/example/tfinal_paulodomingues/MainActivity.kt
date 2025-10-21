package com.example.tfinal_paulodomingues

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfinal_paulodomingues.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }  // para binding de elementos
    private var pinatual: String = "1234"

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
        //var pinatual: String = sharedPref.getString("pin", "1234").toString()

        // ----------------------------------------------------
        binding.btnConfirmar.setOnClickListener {
            if (binding.editPin.text.toString() == pinatual){
                startActivity(Intent(this, ListaActivity::class.java))
            }
            else{
                Toast.makeText(this, "Pin errado!", Toast.LENGTH_SHORT).show()
                binding.editPin.setText("")
            }
        }

        // ----------------------------------------------------
        binding.btnSobre.setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))

        }

        // ----------------------------------------------------
        binding.btnAlterarPin.setOnClickListener {
            startActivity(Intent(this, PinActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = this.getSharedPreferences("pin", MODE_PRIVATE)
        pinatual = sharedPref.getString("pin", "1234").toString()
        binding.editPin.setText("")
    }

}