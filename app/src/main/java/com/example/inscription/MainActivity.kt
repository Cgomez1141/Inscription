package com.example.inscription

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.inscription.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var ed1: EditText// Mostrar los resultados en pantalla/Mapeo del campo de visualizaciòn
    lateinit var codigo: EditText
    lateinit var nombre: EditText
    lateinit var apellido: EditText
    lateinit var semestre: EditText
    lateinit var promedio: EditText
    lateinit var listar: TextView
    lateinit var codigos: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        codigo=binding.Textuno
        nombre=binding.Textdos
        apellido=binding.Texttres
        semestre=binding.Textcuatro
        promedio=binding.Textcinco
        listar=binding.listar
        codigos=binding.codigos
        setContentView(binding.root)
    }
    fun crearDatos(view: View){
        if(codigo.text.toString().length>0 && nombre.text.toString().length>0 && apellido.text.toString().length>0 && semestre.text.toString().length>0 && promedio.text.toString().length>0){
            var usuario=
                Usuario(codigo.text.toString().toInt(),nombre.text.toString(),apellido.text.toString(), semestre.text.toString().toInt(), promedio.text.toString().toFloat())
            var Db= BaseDeDatos(this)
            listar.setText(Db.insertarDatos(usuario))
        }
    }
    fun leerDatos(view: View){
        var db= BaseDeDatos(this)
        var datos=db.traerDatos()
        listar.text=""
        for(i in 0..datos.size-1){
            val usuario =datos.get(i)
            listar.append("id"+usuario.id+"Código"+usuario.codigo+"Nombre:"+usuario.nombre+"Apellido:"+usuario.apellido+"Semestre:"+usuario.semestre+"Promedio:"+usuario.promedio+ "\n" )
        }
        db.close()
    }
    fun borrarDatos(view: View){
        var db=BaseDeDatos(this)
        db.borrar(codigos.text.toString())
    }
    fun actualizar(view: View){
        var db=BaseDeDatos(this)
        var usuario=
            Usuario(codigos.text.toString().toInt(),nombre.text.toString(),apellido.text.toString(), semestre.text.toString().toInt(), promedio.text.toString().toFloat())
        listar.setText(db.actualizar(usuario))
    }


}