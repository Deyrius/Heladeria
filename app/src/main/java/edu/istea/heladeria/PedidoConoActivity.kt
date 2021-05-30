package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.*
import edu.istea.heladeria.model.Cono
import edu.istea.heladeria.model.Cuarto
import edu.istea.heladeria.model.Historico
import edu.istea.heladeria.model.Kilo
import java.lang.Exception
import kotlin.properties.Delegates

class PedidoConoActivity : AppCompatActivity() {

    lateinit var listaConos : ArrayList<Cono>
    lateinit var listaCuarto: ArrayList<Cuarto>
    lateinit var kiloBeta: ArrayList<Kilo>
    lateinit var informeDiario: Array<Int>


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
    var numeroPedido: Int by Delegates.notNull<Int>()
    var primeraCaja: Int by Delegates.notNull<Int>()
    var segundaCaja: Int by Delegates.notNull<Int>()
    var tercerCaja: Int by Delegates.notNull<Int>()
    lateinit var ventasRepartidores: Array<Int>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_cono)
        inicializador()

        volver.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("listaCuarto",listaCuarto)
            intent.putExtra("beta",kiloBeta)
            intent.putExtra("numeroPedido",numeroPedido)
            intent.putExtra("primeraCaja",primeraCaja)
            intent.putExtra("segundaCaja",segundaCaja)
            intent.putExtra("tercerCaja",tercerCaja)
            intent.putExtra("ventasRepartidores",ventasRepartidores)
            intent.putExtra("informeDiario",informeDiario)



            startActivity(intent)
        }
        borrar.setOnClickListener{
            listaConos.clear()
            conosTotales.text = listaConos.size.toString()
        }
        agregar.setOnClickListener {
            try {
                primerGustoSeleccion()
                segundoGustoSeleccion()
                armoCono(primerGustoCono,segundoGustoCono,id)
                Toast.makeText(this,"Se agregó un cono a su pedido",Toast.LENGTH_SHORT).show()
            }catch (e:Exception){ Toast.makeText(this,"Por favor seleccione los gustos",Toast.LENGTH_SHORT).show()}


        }

    }

    private fun inicializador(){



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

        conosTotales = findViewById(R.id.txt_cono_cantidad_total)

        try {
            listaConos = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
            conosTotales.text = listaConos.size.toString()
        }catch (e:Exception){listaConos = ArrayList()}
        try {
            kiloBeta = intent.getSerializableExtra("kilototal") as ArrayList<Kilo>
        }catch (e:Exception){}
        try {
            listaCuarto= intent.getSerializableExtra("listaCuarto")as ArrayList<Cuarto>
        }catch (e:Exception){}
        try {
            numeroPedido = intent.getSerializableExtra("numeroPedido") as Int
        }catch (e:Exception){numeroPedido = 0}
        try {
            primeraCaja = intent.getSerializableExtra("numeroPedido") as Int
        }catch (e:Exception){primeraCaja = 0}
        try {
            segundaCaja = intent.getSerializableExtra("numeroPedido") as Int
        }catch (e:Exception){segundaCaja = 0}
        try {
            tercerCaja = intent.getSerializableExtra("numeroPedido") as Int
        }catch (e:Exception){tercerCaja = 0}
        try {
            ventasRepartidores = intent.getSerializableExtra("ventasRepartidores") as Array<Int>
        }catch (e:Exception){ventasRepartidores = arrayOf(0,0,0,0,)}
        try {
            informeDiario = intent.getSerializableExtra("informeDiario") as Array<Int>
        }catch (e:Exception){informeDiario = arrayOf(0,0,0)}


        id = listaConos.size +1




    }
    fun primerGustoSeleccion(){
        try {
            opcion= findViewById(primerGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {primerGustoCono = "Chocolate"}
                "vainilla" -> {primerGustoCono = "Vainilla"}
                "frutilla" -> {primerGustoCono = "Frutilla"}
            }
        }catch (e:Exception){primerGustoCono = ""}


    }
    fun segundoGustoSeleccion(){
        try {
            opcion= findViewById(segundoGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {segundoGustoCono = "Chocolate"}
                "vainilla" -> {segundoGustoCono = "Vainilla"}
                "frutilla" -> {segundoGustoCono = "Frutilla"}
            }
        }catch (e:Exception){segundoGustoCono = ""}


    }
    fun armoCono(primerGustoCono:String,segundoGustoCono:String,id:Int){
        if (primerGustoCono == "" && segundoGustoCono == ""){
         Toast.makeText(this,"Seleccione los gustos",Toast.LENGTH_SHORT).show()
        }
        else {
            listaConos.add(Cono(id,primerGustoCono,segundoGustoCono))
            conosTotales.text = listaConos.size.toString()
        }
    }


}