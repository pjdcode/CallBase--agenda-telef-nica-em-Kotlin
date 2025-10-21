package com.example.tfinal_paulodomingues

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tfinal_paulodomingues.databinding.ActivityListaBinding


class ListaActivity : AppCompatActivity() {
    private val binding by lazy { ActivityListaBinding.inflate(layoutInflater) }  // para binding de elementos
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

        //val listaTelefonica = ArrayList<Contacto>()
        var listaTelefonica = carregarLista(this)
        var posLista = -1
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaTelefonica
        )
        binding.listContact.adapter = adapter

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode){
                RESULT_OK -> {
                    if (it.data != null) {
                        val nome = it.data!!.getStringExtra("nome")
                        val morada = it.data!!.getStringExtra("morada")
                        val email = it.data!!.getStringExtra("email")
                        val telefone = it.data!!.getStringExtra("telefone")
                        val novo = Contacto(nome.toString(), morada.toString(), telefone.toString(), email.toString() )
                        listaTelefonica.set(posLista, novo)
                        adapter.notifyDataSetChanged()
                        posLista = -1
                    }
                }
                100 -> {   // eliminar
                    listaTelefonica.removeAt(posLista)
                    adapter.notifyDataSetChanged()
                    posLista = -1
                }
                RESULT_FIRST_USER -> {  // novo
                    if (it.data != null) {
                        val nome = it.data!!.getStringExtra("nome")
                        val morada = it.data!!.getStringExtra("morada")
                        val email = it.data!!.getStringExtra("email")
                        val telefone = it.data!!.getStringExtra("telefone")
                        val novo = Contacto(nome.toString(), morada.toString(), telefone.toString(), email.toString() )
                        listaTelefonica.add(novo)
                        adapter.notifyDataSetChanged()
                        posLista = -1
                    }
                }
            }

        }

        // -----------------------------------------------------------------------------------
        binding.btnOrdemAsc.setOnClickListener {
            listaTelefonica.sortBy { it.nome }
            adapter.notifyDataSetChanged()
        }

        // -----------------------------------------------------------------------------------
        binding.btnOrdemDesc.setOnClickListener {
            listaTelefonica.sortByDescending  { it.nome }
            adapter.notifyDataSetChanged()
        }

        // -----------------------------------------------------------------------------------
        binding.btnVoltar.setOnClickListener {
            guardarLista(this, listaTelefonica)
            finish()
        }

        binding.btnNovo.setOnClickListener {
            posLista = -1
            val intent = Intent(this, ContactoActivity::class.java)
            intent.putExtra("nome", "")
            intent.putExtra("morada", "")
            intent.putExtra("email", "")
            intent.putExtra("telefone", "")
            result.launch(intent)
        }

        binding.listContact.setOnItemClickListener { parent, view, position, id ->
            posLista = position

            val intent = Intent(this, ContactoActivity::class.java)
            intent.putExtra("nome", listaTelefonica[position].nome)
            intent.putExtra("telefone", listaTelefonica[position].telefone)
            intent.putExtra("email", listaTelefonica[position].email)
            intent.putExtra("morada", listaTelefonica[position].morada)
            result.launch(intent)
        }


    }


    // ========================================================================
    fun guardarLista(context: Context, lista: ArrayList<Contacto>) {
        val sharedPref = context.getSharedPreferences("dados", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Converter lista em JSON manualmente para evitar dependências externas
        val json = buildString {
            append("[")
            lista.forEachIndexed { i, contacto ->
                append("{\"nome\":\"${contacto.nome}\",\"morada\":\"${contacto.morada}\",\"telefone\":\"${contacto.telefone}\",\"email\":\"${contacto.email}\"}")
                if (i < lista.size - 1) append(",")
            }
            append("]")
        }
        editor.putString("listaTelefonica", json)
        editor.apply()
    }

    // ------------------------------------------------------------------------
    fun carregarLista(context: Context): ArrayList<Contacto> {
        val sharedPref = context.getSharedPreferences("dados", Context.MODE_PRIVATE)
        val json = sharedPref.getString("listaTelefonica", "[]") ?: "[]"

        val lista = ArrayList<Contacto>()

        // Separar cada contacto
        val regex = Regex("\\{([^}]+)\\}")
        regex.findAll(json).forEach { match ->
            val props = match.groupValues[1].split(",")
            val map = props.associate {
                val (k, v) = it.split(":")
                k.replace("\"", "") to v.replace("\"", "")
            }
            lista.add(Contacto(map["nome"] ?: "",map["morada"] ?: "",map["telefone"] ?: "",map["email"] ?: ""))
        }

        return lista
    }


}