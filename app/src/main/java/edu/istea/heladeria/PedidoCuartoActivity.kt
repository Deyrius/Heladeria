package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.istea.heladeria.model.Cono
import edu.istea.heladeria.model.Cuarto
import edu.istea.heladeria.model.Historico
import edu.istea.heladeria.model.Kilo
import java.lang.Exception
import kotlin.properties.Delegates

class PedidoCuartoActivity : AppCompatActivity() {

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
    lateinit var plusCuarto: RadioGroup
    lateinit var opcion:RadioButton

    lateinit var primerGustoCuarto: String
    lateinit var segundoGustoCuarto: String
    lateinit var tercerGustoCuarto: String
    lateinit var cuartoPlus: String
    lateinit var cuartoTotales: TextView
    var id: Int = 0
    var numeroPedido: Int by Delegates.notNull<Int>()
    var primeraCaja: Int by Delegates.notNull<Int>()
    var segundaCaja: Int by Delegates.notNull<Int>()
    var tercerCaja: Int by Delegates.notNull<Int>()
    lateinit var ventasRepartidores: Array<Int>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_cuarto)
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
            listaCuarto.clear()
            cuartoTotales.text = listaCuarto.size.toString()
        }
        agregar.setOnClickListener {
            try {
                seleccionPrimerGusto()
                seleccionSegundoGusto()
                seleccionTercerGusto()
                seleccionPlus()
                armoCuarto(primerGustoCuarto,segundoGustoCuarto,tercerGustoCuarto,cuartoPlus,id)
                Toast.makeText(this,"Se agrego su 1/4 al pedido",Toast.LENGTH_SHORT).show()

            }catch (e:Exception){Toast.makeText(this,"Por favor seleccione sus gustos",Toast.LENGTH_SHORT).show()}
        }

    }

    fun Inicializador(){
        agregar = findViewById(R.id.btn_kilo_agregar)
        borrar = findViewById(R.id.btn_kilo_borrar)
        volver = findViewById(R.id.btn_kilo_volver)
        primerGusto = findViewById(R.id.rg_primer_gusto)
        segundoGusto = findViewById(R.id.rg_segundo_gusto)
        tercerGusto = findViewById(R.id.rg_tercer_grupo)
        plusCuarto = findViewById(R.id.rg_cuarto_plus)
        cuartoPlus = ""
        cuartoTotales= findViewById(R.id.txt_cuarto_total)
        try {
            listaCuarto = intent.getSerializableExtra("listaCuarto") as ArrayList<Cuarto>
            cuartoTotales.text = listaCuarto.size.toString()
        }catch (e:Exception){listaCuarto = ArrayList()}
        try {
            kiloBeta = intent.getSerializableExtra("kilototal") as ArrayList<Kilo>
        }catch (e:Exception){}
        try {
            listaCono = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
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
        id = listaCuarto.size +1




    }
    fun seleccionPrimerGusto(){
        try {
            opcion = findViewById(primerGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {primerGustoCuarto = "Chocolate"}
                "vainilla" -> {primerGustoCuarto = "Vainilla"}
                "frutilla" -> {primerGustoCuarto = "Frutilla"}
            }
        }catch (e:Exception){primerGustoCuarto = ""}

    }
    fun seleccionSegundoGusto(){
        try {
            opcion = findViewById(segundoGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {segundoGustoCuarto = "Chocolate"}
                "vainilla" -> {segundoGustoCuarto = "Vainilla"}
                "frutilla" -> {segundoGustoCuarto = "Frutilla"}
            }
        }catch (e:Exception){ segundoGustoCuarto = ""}

    }
    fun seleccionTercerGusto(){
        try {
            opcion = findViewById(tercerGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {tercerGustoCuarto = "Chocolate"}
                "vainilla" -> {tercerGustoCuarto = "Vainilla"}
                "frutilla" -> {tercerGustoCuarto = "Frutilla"}
            }
        }catch (e:Exception){tercerGustoCuarto = ""}

    }
    fun seleccionPlus(){
        try {
            opcion = findViewById(plusCuarto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate fundido" -> {cuartoPlus = "Chocolate Fundido"}
                "crema de vainilla" -> {cuartoPlus = "Crema de Vainilla"}
            }
        }catch (e:Exception){
            cuartoPlus = ""
        }

    }
    fun armoCuarto(primerGustoCuarto :String,
                   segundoGustoCuarto :String,
                   tercerGustoCuarto :String,
                   cuartoPlus :String,
                   id : Int){
        if (primerGustoCuarto == "" && segundoGustoCuarto == "" && tercerGustoCuarto == ""){
            Toast.makeText(this,"Por favor seleccione sus gustos",Toast.LENGTH_SHORT).show()
        }
        else{
        listaCuarto.add(Cuarto(id,primerGustoCuarto,segundoGustoCuarto,tercerGustoCuarto,cuartoPlus))
        cuartoTotales.text = listaCuarto.size.toString()}

    }

}