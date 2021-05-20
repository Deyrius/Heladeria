package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.istea.heladeria.model.Cono
import edu.istea.heladeria.model.Cuarto
import edu.istea.heladeria.model.Kilo
import java.lang.Exception

class PedidoCuartoActivity : AppCompatActivity() {

    lateinit var listaCuarto : ArrayList<Cuarto>
    lateinit var listaCono: ArrayList<Cono>
    lateinit var listaKilo: ArrayList<Kilo>

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido_cuarto)
        Inicializador()

        volver.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("listaCuarto",listaCuarto)
            intent.putExtra("listaConos",listaCono)
            intent.putExtra("listaKilo",listaKilo)
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
        listaCuarto = intent.getSerializableExtra("listaCuarto") as ArrayList<Cuarto>
        listaCono = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
        listaKilo = intent.getSerializableExtra("listaKilo") as ArrayList<Kilo>
        agregar = findViewById(R.id.btn_cuarto_agregar)
        borrar = findViewById(R.id.btn_cuarto_borrar)
        volver = findViewById(R.id.btn_cuarto_volver)
        primerGusto = findViewById(R.id.rg_primer_gusto)
        segundoGusto = findViewById(R.id.rg_segundo_gusto)
        tercerGusto = findViewById(R.id.rg_tercer_grupo)
        plusCuarto = findViewById(R.id.rg_cuarto_plus)
        cuartoPlus = ""
        id = listaCuarto.size +1
        cuartoTotales= findViewById(R.id.txt_cuarto_vasos_totales)

    }
    fun seleccionPrimerGusto(){
        opcion = findViewById(primerGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {primerGustoCuarto = "Chocolate"}
                "vainilla" -> {primerGustoCuarto = "Vainilla"}
                "frutilla" -> {primerGustoCuarto = "Frutilla"}
            }
    }
    fun seleccionSegundoGusto(){
        opcion = findViewById(segundoGusto.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "chocolate" -> {segundoGustoCuarto = "Chocolate"}
                "vainilla" -> {segundoGustoCuarto = "Vainilla"}
                "frutilla" -> {segundoGustoCuarto = "Frutilla"}
            }
    }
    fun seleccionTercerGusto(){
        opcion = findViewById(tercerGusto.checkedRadioButtonId)
        when(opcion.text.toString().toLowerCase()){
            "chocolate" -> {tercerGustoCuarto = "Chocolate"}
            "vainilla" -> {tercerGustoCuarto = "Vainilla"}
            "frutilla" -> {tercerGustoCuarto = "Frutilla"}
        }
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
        listaCuarto.add(Cuarto(id,primerGustoCuarto,segundoGustoCuarto,tercerGustoCuarto,cuartoPlus))
        cuartoTotales.text = listaCuarto.size.toString()

    }
}