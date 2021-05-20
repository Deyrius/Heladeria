package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.istea.heladeria.model.Cono
import edu.istea.heladeria.model.Cuarto
import edu.istea.heladeria.model.Kilo
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var listaConos : ArrayList<Cono>
    lateinit var listaCuartos : ArrayList<Cuarto>
    lateinit var listakilo : ArrayList<Kilo>
    lateinit var totalConos: TextView
    lateinit var totalCuartos: TextView
    lateinit var totalKilo: TextView
    lateinit var cono: ImageButton
    lateinit var cuarto: ImageButton
    lateinit var kilo: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarElementos()

        cono.setOnClickListener {
            val intent = Intent(this,PedidoConoActivity::class.java)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("listaCuarto",listaCuartos)
            intent.putExtra("listaKilo",listakilo)
            startActivity(intent)
        }
        cuarto.setOnClickListener {
            val intent = Intent(this,PedidoCuartoActivity::class.java)
            intent.putExtra("listaCuarto",listaCuartos)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("listaKilo",listakilo)
            startActivity(intent)
        }
        kilo.setOnClickListener {
            val intent = Intent(this,PedidoKiloActivity::class.java)
            intent.putExtra("listaKilo",listakilo)
            intent.putExtra("listaConos",listaConos)
            intent.putExtra("listaCuarto",listaCuartos)
            startActivity(intent)
        }


        totalConos.text = listaConos.size.toString()
        totalCuartos.text = listaCuartos.size.toString()
        totalKilo.text = listakilo.size.toString()


    }


    private fun inicializarElementos(){
        try {
            listaConos = intent.getSerializableExtra("listaConos") as ArrayList<Cono>
        }catch (e:Exception){listaConos = ArrayList()}

        try {
            listaCuartos = intent.getSerializableExtra("listaCuarto") as ArrayList<Cuarto>
        }catch (e:Exception){listaCuartos = ArrayList()}

        try {
            listakilo = intent.getSerializableExtra("totalKilo") as ArrayList<Kilo>
        }catch (e:Exception){listakilo = ArrayList()}

        totalConos = findViewById(R.id.txt_cant_total_conos)
        totalCuartos = findViewById(R.id.txt_cant_total_cuarto)
        totalKilo = findViewById(R.id.txt_cant_total_kilo)
        cono = findViewById(R.id.btn_img_cono)
        cuarto = findViewById(R.id.btn_img_cuarto)
        kilo = findViewById(R.id.btn_img_kilo)

    }
}


