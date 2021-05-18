package edu.istea.heladeria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import edu.istea.heladeria.model.Cono
import edu.istea.heladeria.model.Cuarto
import edu.istea.heladeria.model.Kilo

class MainActivity : AppCompatActivity() {

    val listaConos : ArrayList<Cono> = ArrayList()
    val listaCuartos : ArrayList<Cuarto> = ArrayList()
    val listakilo : ArrayList<Kilo> = ArrayList()
    val listaGustos = arrayOf("Chocolate","Vainilla","Frutilla")
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
            intent.putExtra("listaGustos",listaGustos)
            startActivity(intent)
        }
        cuarto.setOnClickListener {
            val intent = Intent(this,PedidoCuartoActivity::class.java)
            intent.putExtra("listaCuartos",listaCuartos)
            intent.putExtra("listaGustos",listaGustos)
            startActivity(intent)
        }
        kilo.setOnClickListener {
            val intent = Intent(this,PedidoKiloActivity::class.java)
            intent.putExtra("listaKilo",listakilo)
            intent.putExtra("listaGustos",listaGustos)
            startActivity(intent)
        }


        totalConos.text = listaConos.size.toString()
        totalCuartos.text = listaCuartos.size.toString()
        totalKilo.text = listakilo.size.toString()


    }


    private fun inicializarElementos(){
        totalConos = findViewById(R.id.txt_cant_total_conos)
        totalCuartos = findViewById(R.id.txt_cant_total_cuarto)
        totalKilo = findViewById(R.id.txt_cant_total_kilo)
        cono = findViewById(R.id.btn_img_cono)
        cuarto = findViewById(R.id.btn_img_cuarto)
        kilo = findViewById(R.id.btn_img_kilo)

    }
}


