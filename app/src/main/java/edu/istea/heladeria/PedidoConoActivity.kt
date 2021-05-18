package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.*
import edu.istea.heladeria.model.Cono

class PedidoConoActivity : AppCompatActivity() {

    //var listaGustos : ArrayList<Gustos> = intent.getSerializableExtra("listaGustos") as ArrayList<Gustos>

    lateinit var listaConos : ArrayList<Cono>
    lateinit var agregar: Button
    lateinit var borrar: ImageButton
    lateinit var volver: Button

    lateinit var primerGusto: RadioButton
    lateinit var p_chocolate: CheckBox
    lateinit var p_vainilla: CheckBox
    lateinit var p_frutilla: CheckBox

    lateinit var segundoGusto: RadioButton
    lateinit var s_chocolate: CheckBox
    lateinit var s_vainilla: CheckBox
    lateinit var s_frutilla: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_cono)
        inicializador()

        volver.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        borrar.setOnClickListener{
            listaConos.clear()
        }
        agregar.setOnClickListener {
            fun onRadioButtonClicked(view: View){
                if(view is RadioButton){
                    val checked = view.isChecked

                    when(view.getId()){
                        R.id.btn_pg_chocolate ->
                            if (checked){/*XXXXXXXXX */}
                        R.id.btn_pg_vainilla ->
                            if (checked){/*XXXXXXXXX */}
                        R.id.btn_pg_frutilla ->
                            if (checked){/*XXXXXXXXX */}
                    }

                }
            }
        }

    }

    private fun inicializador(){
        listaConos = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
        primerGusto= findViewById(R.id.primer_gusto)
        segundoGusto = findViewById(R.id.segundo_gusto)
        agregar = findViewById(R.id.btn_agregar_cono)
        borrar = findViewById(R.id.btn_cono_reiniciar)
        volver = findViewById(R.id.btn_cono_volver)
        p_chocolate = findViewById(R.id.btn_pg_chocolate)
        p_vainilla = findViewById(R.id.btn_pg_vainilla)
        p_frutilla = findViewById(R.id.btn_pg_frutilla)
        s_chocolate = findViewById(R.id.btn_sg_chocolate)
        s_vainilla = findViewById(R.id.btn_sg_vainilla)
        s_frutilla = findViewById(R.id.btn_sg_frutilla)



    }



}