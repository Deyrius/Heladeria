package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import edu.istea.heladeria.model.Cono
import edu.istea.heladeria.model.Cuarto
import edu.istea.heladeria.model.Historico
import edu.istea.heladeria.model.Kilo
import java.lang.Exception
import kotlin.properties.Delegates

class PedidoKiloActivity : AppCompatActivity() {

    lateinit var listaCuarto : ArrayList<Cuarto>
    lateinit var listaCono: ArrayList<Cono>
    lateinit var kiloBeta: ArrayList<Kilo>
    lateinit var informeDiario: Array<Int>


    lateinit var agregar: Button
    lateinit var borrar: ImageButton
    lateinit var volver: Button

    lateinit var primerGusto: RadioGroup
    lateinit var segundoGusto: RadioGroup
    lateinit var tercerGusto: RadioGroup
    lateinit var cuartoGusto: RadioGroup
    lateinit var plusKilo: RadioGroup
    lateinit var opcion: RadioButton

    lateinit var primerGustoKilo: String
    lateinit var segundoGustoKilo: String
    lateinit var tercerGustoKilo: String
    lateinit var cuartoGustoKilo: String
    lateinit var kiloPlus: String
    lateinit var kiloTotales: TextView
    var id: Int = 0
    var numeroPedido: Int by Delegates.notNull<Int>()
    var primeraCaja: Int by Delegates.notNull<Int>()
    var segundaCaja: Int by Delegates.notNull<Int>()
    var tercerCaja: Int by Delegates.notNull<Int>()
    lateinit var ventasRepartidores: Array<Int>





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_kilo)
        Inicializador()
        volver.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("listaCuarto",listaCuarto)
            intent.putExtra("listaConos",listaCono)
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
            kiloBeta.clear()
            kiloTotales.text = kiloBeta.size.toString()
        }
        agregar.setOnClickListener {
            try {
                seleccionPrimerGusto()
                seleccionSegundoGusto()
                seleccionTercerGusto()
                seleccionCuartoGusto()
                seleccionPlus()
                armoKilo(primerGustoKilo ,
                    segundoGustoKilo,
                    tercerGustoKilo,
                    cuartoGustoKilo,
                    kiloPlus,
                    id)

                Toast.makeText(this,"Se agrego su Kilo al pedido",Toast.LENGTH_SHORT).show()

            }catch (e:Exception){Toast.makeText(this,"Por favor Seleccione sus gustos",Toast.LENGTH_SHORT).show()}
        }




    }

    fun Inicializador(){
        listaCuarto = intent.getSerializableExtra("listaCuarto") as ArrayList<Cuarto>
        listaCono = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
        kiloBeta = ArrayList()

        agregar = findViewById(R.id.btn_kilo_agregar)
        borrar = findViewById(R.id.btn_kilo_borrar)
        volver = findViewById(R.id.btn_kilo_volver)

        primerGusto = findViewById(R.id.rg_primer_gusto)
        segundoGusto= findViewById(R.id.rg_segundo_gusto)
        tercerGusto= findViewById(R.id.rg_tercer_grupo)
        cuartoGusto= findViewById(R.id.rg_cuarto_gusto)
        plusKilo= findViewById(R.id.rg_cuarto_plus)

        kiloPlus = ""
        kiloTotales =findViewById(R.id.txt_kilo_total)

        try {
            kiloBeta = intent.getSerializableExtra("listaKilo") as ArrayList<Kilo>
            kiloTotales.text = kiloBeta.size.toString()
        }catch (e:Exception){kiloBeta = ArrayList()}
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
        }catch (e:Exception){informeDiario =arrayOf(0,0,0)}
        id = kiloBeta.size +1
    }
    fun seleccionPrimerGusto(){
        try {
            opcion = findViewById(primerGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {primerGustoKilo = "Chocolate"}
                "vainilla" -> {primerGustoKilo = "Vainilla"}
                "frutilla" -> {primerGustoKilo = "Frutilla"}
            }
        }catch (e:Exception){primerGustoKilo = ""}

    }
    fun seleccionSegundoGusto(){
        try {
            opcion = findViewById(segundoGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {segundoGustoKilo = "Chocolate"}
                "vainilla" -> {segundoGustoKilo = "Vainilla"}
                "frutilla" -> {segundoGustoKilo = "Frutilla"}
            }
        }catch (e:Exception){segundoGustoKilo = ""}

    }
    fun seleccionTercerGusto(){
        try {
            opcion = findViewById(tercerGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {tercerGustoKilo = "Chocolate"}
                "vainilla" -> {tercerGustoKilo = "Vainilla"}
                "frutilla" -> {tercerGustoKilo = "Frutilla"}
            }
        }catch (e:Exception){tercerGustoKilo = ""}

    }
    fun seleccionCuartoGusto(){
        try {
            opcion = findViewById(cuartoGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {cuartoGustoKilo = "Chocolate"}
                "vainilla" -> {cuartoGustoKilo = "Vainilla"}
                "frutilla" -> {cuartoGustoKilo = "Frutilla"}
            }
        }catch (e:Exception){cuartoGustoKilo = ""}

    }
    fun seleccionPlus(){
        try {
            opcion = findViewById(plusKilo.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate fundido" -> {kiloPlus = "Chocolate Fundido"}
                "crema de vainilla" -> {kiloPlus = "Crema de Vainilla"}
                "Almendras" -> {kiloPlus = "Almendras"}
            }
        }catch (e: Exception){
            kiloPlus = ""
        }

    }
    fun armoKilo(primerGustoKilo :String,
                   segundoGustoKilo :String,
                   tercerGustoKilo :String,
                   cuartoGustoKilo :String,
                   kiloPlus :String,
                   id : Int){
        if (primerGustoKilo == "" && segundoGustoKilo == "" && tercerGustoKilo == "" && cuartoGustoKilo == ""){
            Toast.makeText(this,"Por favor Seleccione sus gustos",Toast.LENGTH_SHORT).show()
        }
        else{
            kiloBeta.add(Kilo(id,primerGustoKilo,segundoGustoKilo,tercerGustoKilo,cuartoGustoKilo,kiloPlus))
            kiloTotales.text = kiloBeta.size.toString()
        }


    }

}