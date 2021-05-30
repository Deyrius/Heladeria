package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import edu.istea.heladeria.model.*
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var listaConos : ArrayList<Cono>
    lateinit var listaCuartos : ArrayList<Cuarto>
    lateinit var kiloBeta: ArrayList<Kilo>
    lateinit var informeDiario: Array<Int>



    lateinit var totalConos: TextView
    lateinit var totalCuartos: TextView
    lateinit var totalKilo: TextView

    lateinit var cono: ImageButton
    lateinit var cuarto: ImageButton
    lateinit var kilo: ImageButton

    lateinit var despachar: Button

    var numeroPedido: Int by Delegates.notNull<Int>()

    var primeraCaja: Int by Delegates.notNull<Int>()
    var segundaCaja: Int by Delegates.notNull<Int>()
    var tercerCaja: Int by Delegates.notNull<Int>()

    lateinit var ventasRepartidores: Array<Int>







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarElementos()

        cono.setOnClickListener {
            val intent = Intent(this,PedidoConoActivity::class.java)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("listaCuarto",listaCuartos)
            intent.putExtra("kilototal",kiloBeta)
            intent.putExtra("numeroPedido",numeroPedido)
            intent.putExtra("primeraCaja",primeraCaja)
            intent.putExtra("segundaCaja",segundaCaja)
            intent.putExtra("tercerCaja",tercerCaja)
            intent.putExtra("informeDiario",informeDiario)
            intent.putExtra("ventasRepartidores",ventasRepartidores)


            startActivity(intent)
        }
        cuarto.setOnClickListener {
            val intent = Intent(this,PedidoCuartoActivity::class.java)
            intent.putExtra("listaCuarto",listaCuartos)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("kilototal",kiloBeta)
            intent.putExtra("numeroPedido",numeroPedido)
            intent.putExtra("primeraCaja",primeraCaja)
            intent.putExtra("segundaCaja",segundaCaja)
            intent.putExtra("tercerCaja",tercerCaja)
            intent.putExtra("informeDiario",informeDiario)
            intent.putExtra("ventasRepartidores",ventasRepartidores)


            startActivity(intent)
        }
        kilo.setOnClickListener {
            val intent = Intent(this,PedidoKiloActivity::class.java)
            intent.putExtra("listaKilo",kiloBeta)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("listaCuarto",listaCuartos)
            intent.putExtra("numeroPedido",numeroPedido)
            intent.putExtra("primeraCaja",primeraCaja)
            intent.putExtra("segundaCaja",segundaCaja)
            intent.putExtra("tercerCaja",tercerCaja)
            intent.putExtra("informeDiario",informeDiario)
            intent.putExtra("ventasRepartidores",ventasRepartidores)


            startActivity(intent)
        }
        despachar.setOnClickListener {
                NumeroPedido()
                val intent = Intent(this,DespachoActivity::class.java)
            try {
                Log.i("INFORME",informeDiario.toString())

                intent.putExtra("listaKilo",kiloBeta)
                intent.putExtra("listaConos",listaConos)
                intent.putExtra("listaCuarto",listaCuartos)
                intent.putExtra("informeDiario",informeDiario)
                intent.putExtra("numeroPedido",numeroPedido)
                intent.putExtra("primeraCaja",primeraCaja)
                intent.putExtra("segundaCaja",segundaCaja)
                intent.putExtra("tercerCaja",tercerCaja)
                intent.putExtra("ventasRepartidores",ventasRepartidores)

            }catch (e:Exception){Toast.makeText(this,"Debe hacer un pedido",Toast.LENGTH_SHORT).show()}

                startActivity(intent)

        }

    }

    private fun inicializarElementos(){
        try {
            listaConos = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
        }catch (e:Exception){listaConos = ArrayList()}

        try {
            listaCuartos = intent.getSerializableExtra("listaCuarto") as ArrayList<Cuarto>
        }catch (e:Exception){listaCuartos = ArrayList()}

        try {
            kiloBeta = intent.getSerializableExtra("beta") as ArrayList<Kilo>
        }catch (e:Exception){kiloBeta = ArrayList() }
        try {
            informeDiario = intent.getSerializableExtra("informeDiario") as Array<Int>
        }catch (e:Exception){informeDiario =arrayOf(0,0,0)}

        totalConos = findViewById(R.id.txt_cant_total_conos)
        totalCuartos = findViewById(R.id.txt_cant_total_cuarto)
        totalKilo = findViewById(R.id.txt_cant_total_kilo)
        cono = findViewById(R.id.btn_img_cono)
        cuarto = findViewById(R.id.btn_img_cuarto)
        kilo = findViewById(R.id.btn_img_kilo)
        despachar = findViewById(R.id.btn_despachar)
        totalConos.text = listaConos.size.toString()
        totalCuartos.text = listaCuartos.size.toString()
        totalKilo.text = kiloBeta.size.toString()

        try {
            numeroPedido = intent.getSerializableExtra("numeroPedido") as Int
        }catch (e:Exception){numeroPedido = 0}
        try {
            primeraCaja = intent.getSerializableExtra("primeraCaja") as Int
        }catch (e:Exception){primeraCaja = 0}
        try {
           segundaCaja = intent.getSerializableExtra("segundaCaja") as Int
        }catch (e:Exception){segundaCaja = 0}
        try {
            tercerCaja = intent.getSerializableExtra("tercerCaja")as Int
        }catch (e:Exception){tercerCaja = 0}
        try {
           ventasRepartidores = intent.getSerializableExtra("ventasRepartidores") as Array<Int>
        }catch (e:Exception){ventasRepartidores = arrayOf(0,0,0,0,)}

    }

    fun NumeroPedido(){
        if(listaConos.size == 0 && listaCuartos.size == 0 && kiloBeta.size == 0 ){
            Toast.makeText(this,"Debe hacer un pedido",Toast.LENGTH_SHORT).show()
        }
        else{ numeroPedido ++}
    }
    }







