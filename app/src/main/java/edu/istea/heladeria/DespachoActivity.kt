package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import edu.istea.heladeria.model.Cono
import edu.istea.heladeria.model.Cuarto
import edu.istea.heladeria.model.Kilo
import java.lang.Exception
import kotlin.properties.Delegates
import edu.istea.heladeria.model.Historico

lateinit var listaConos : ArrayList<Cono>
lateinit var listaCuartos : ArrayList<Cuarto>
lateinit var kiloBeta: ArrayList<Kilo>
lateinit var informeDiario: Array<Int>
var numeroPedidoRecibido: Int by Delegates.notNull<Int>()



lateinit var spnRepartidores : Spinner
lateinit var caja: RadioGroup
lateinit var repartidores : Array<String>
lateinit var opcion: RadioButton
var primeraCaja: Int by Delegates.notNull<Int>()
var segundaCaja: Int by Delegates.notNull<Int>()
var tercerCaja: Int by Delegates.notNull<Int>()
var posicion: Int by Delegates.notNull<Int>()
lateinit var ventasRepartidores: Array<Int>

lateinit var conoPedido : TextView
lateinit var cuartoPedido : TextView
lateinit var kiloPedido : TextView
lateinit var numeroPedido : TextView
lateinit var despacho : Button

lateinit var informe: Array<Int>
lateinit var btnInforme : Button

var posicionMejorRepartidor: Int by Delegates.notNull<Int>()
var dia: Int = 1







class DespachoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_despacho)
        Inicializador()

        despacho.setOnClickListener {
            ArmoHistorico (listaConos.size, listaCuartos.size, kiloBeta.size)
            intent = Intent(this,MainActivity::class.java)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("listaCuarto",listaCuartos)
            intent.putExtra("kilototal",kiloBeta)
            intent.putExtra("primeraCaja",primeraCaja)
            intent.putExtra("segundaCaja",segundaCaja)
            intent.putExtra("tercerCaja",tercerCaja)
            intent.putExtra("ventasRepartidores",ventasRepartidores)
            intent.putExtra("informeDiario", informeDiario)
            Reset()
            SpnSeleccion()
            SeleccionCajero()


        }

        btnInforme.setOnClickListener {
            MayorRepartidor(ventasRepartidores )
            Toast.makeText(this,"Conos: "+ informeDiario[0].toString()+"\r\n"
                                            +"Cuartos: "+informeDiario[1].toString()+"\r\n"
                                            +"Kilos: "+informeDiario[2].toString()+"\r\n"
                                            +"Mayor Repartidor: "+ repartidores[posicionMejorRepartidor],Toast.LENGTH_SHORT).show()
           // intent = Intent(this,InformeActivity::class.java)
           // intent.putExtra("repartidor", posicionMejorRepartidor)
        }


    }

   fun Inicializador(){
       listaConos = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
       listaCuartos = intent.getSerializableExtra("listaCuarto") as ArrayList<Cuarto>
       kiloBeta = intent.getSerializableExtra("listaKilo") as ArrayList<Kilo>
       informeDiario = intent.getSerializableExtra("informeDiario") as Array<Int>
       numeroPedidoRecibido = intent.getSerializableExtra("numeroPedido") as Int

       conoPedido = findViewById(R.id.txt_cono)
       cuartoPedido= findViewById(R.id.txt_cuarto)
       kiloPedido = findViewById(R.id.txt_kilo)
       numeroPedido = findViewById(R.id.txt_pedido)
       despacho = findViewById(R.id.btn_despacho)

       ventasRepartidores = intent.getSerializableExtra("ventasRepartidores") as Array<Int>
       repartidores = arrayOf("Repartidor 1","Repartidor 2","Repartidor 3","Repartidor 4")
       caja = findViewById(R.id.rg_caja)

       spnRepartidores = findViewById(R.id.spn_repartidor)
       val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,repartidores)
       spnRepartidores.adapter = adaptador

       conoPedido.text = listaConos.size.toString()
       cuartoPedido.text = listaCuartos.size.toString()
       kiloPedido.text = kiloBeta.size.toString()
       numeroPedido.text = numeroPedidoRecibido.toString()

       primeraCaja = intent.getSerializableExtra("primeraCaja") as Int
       segundaCaja = intent.getSerializableExtra("segundaCaja") as Int
       tercerCaja = intent.getSerializableExtra("tercerCaja")as Int

       btnInforme = findViewById(R.id.btn_informe)

       informe = arrayOf()

       if (numeroPedidoRecibido == 30){
           dia += 1
           numeroPedidoRecibido = 0
       }


   }

   fun SeleccionCajero(){
        try {
            opcion = findViewById(caja.checkedRadioButtonId)
            when(opcion.text.toString().toLowerCase()){
                "caja 1" -> {if(primeraCaja<5){
                    primeraCaja ++
                    intent.putExtra("numeroPedido", numeroPedidoRecibido)
                    intent.putExtra("contadorcaja1", primeraCaja)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Caja 1 LLENA", Toast.LENGTH_SHORT).show()}}
                "caja 2" -> {if (segundaCaja <10){
                    segundaCaja ++
                    intent.putExtra("numeroPedido", numeroPedidoRecibido)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Caja 2 LLENA", Toast.LENGTH_SHORT).show()}}
                "caja 3" -> {if (tercerCaja <15){
                    tercerCaja ++
                    intent.putExtra("numeroPedido", numeroPedidoRecibido)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Caja 3 LLENA", Toast.LENGTH_SHORT).show()}}
            }
        }catch (e: Exception){
            Toast.makeText(this,"Seleccione una Caja", Toast.LENGTH_SHORT).show()}
   }

    fun SpnSeleccion(){
        posicion = spnRepartidores.selectedItemPosition
        ventasRepartidores[posicion] += 1

    }
    fun Reset(){
        listaConos.clear()
        listaCuartos.clear()
        kiloBeta.clear()
    }
    fun MayorRepartidor(ventasRepartidores : Array<Int>){
        var mayor = 0
        var posicion = 0
        for ((index,repartidor) in ventasRepartidores.withIndex()){
            if(repartidor > mayor){
                mayor = repartidor
                posicion = index
            }
        }
        posicionMejorRepartidor = posicion
    }
    fun ArmoHistorico (cono:Int,cuarto:Int,kilo:Int) {
        /*
        for ((index,producto)in historico.withIndex()){
            if(index +1 == dia){
                producto.dia = dia
                producto.conoHistorico += cono
                producto.cuartoHistorico += cuarto
                producto.kiloHistorico += kilo
                //historico.add(producto)
                Log.i("INDEX",index.toString())
                Log.i("PRODUCTO",producto.toString())
            }
        }
        historico.add(dia-1,Historico(dia,cono,cuarto,kilo)) */
        informeDiario[0] += cono
        informeDiario[1] += cuarto
        informeDiario[2] += kilo

    }

}




