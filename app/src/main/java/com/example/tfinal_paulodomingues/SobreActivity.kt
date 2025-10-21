package com.example.tfinal_paulodomingues

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfinal_paulodomingues.databinding.ActivitySobreBinding

class SobreActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySobreBinding.inflate(layoutInflater) }  // para binding de elementos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }

        // ----------------------------------------------------
        binding.btnAlterarPin.setOnClickListener {
            startActivity(Intent(this, PinActivity::class.java))
        }
    }
}