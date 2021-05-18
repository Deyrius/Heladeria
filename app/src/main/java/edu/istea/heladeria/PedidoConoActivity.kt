package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.*
import edu.istea.heladeria.model.Cono

class PedidoConoActivity : AppCompatActivity() {

    //var listaGustos : ArrayList<Gustos> = intent.getSerializableExtra("listaGustos") as ArrayList<Gustos>

    lateinit var listaConos : ArrayList<Cono>
    lateinit var agregar: Button
    lateinit var borrar: ImageButton
    lateinit var volver: Button

    lateinit var primerGusto: RadioGroup
    lateinit var p_chocolate: RadioButton
    lateinit var p_vainilla: RadioButton
    lateinit var p_frutilla: RadioButton

    lateinit var segundoGusto: RadioGroup
    lateinit var s_chocolate: RadioButton
    lateinit var s_vainilla: RadioButton
    lateinit var s_frutilla: RadioButton

    lateinit var opcion:RadioButton

    lateinit var primerGustoCono: String
    lateinit var segundoGustoCono: String
    var id: Int = 0
    lateinit var conosTotales: TextView

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
            conosTotales.text = listaConos.size.toString()
        }
        agregar.setOnClickListener {
            primerGustoSeleccion()
            segundoGustoSeleccion()
            armoCono(primerGustoCono,segundoGustoCono,id)
            Toast.makeText(this,"Se agregó un cono a su pedido",Toast.LENGTH_SHORT).show()

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
        primerGustoCono = ""
        id = listaConos.size +1
        conosTotales = findViewById(R.id.txt_cono_cantidad_total)



    }
    fun primerGustoSeleccion(){

        opcion= findViewById(primerGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {primerGustoCono = "Chocolate"}
                "vainilla" -> {primerGustoCono = "Vainilla"}
                "frutilla" -> {primerGustoCono = "Frutilla"}
            }

    }
    fun segundoGustoSeleccion(){
        opcion= findViewById(segundoGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {segundoGustoCono = "Chocolate"}
                "vainilla" -> {segundoGustoCono = "Vainilla"}
                "frutilla" -> {segundoGustoCono = "Frutilla"}
            }

    }

    fun armoCono(primerGustoCono:String,segundoGustoCono:String,id:Int){
        listaConos.add(Cono(id,primerGustoCono,segundoGustoCono))
        conosTotales.text = listaConos.size.toString()
    }




}