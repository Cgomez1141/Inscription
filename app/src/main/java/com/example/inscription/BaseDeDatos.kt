package com.example.inscription

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val Bdatos="DBdatos"

class BaseDeDatos(contexto: Context)  : SQLiteOpenHelper(contexto, Bdatos, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val crearTabla="CREATE TABLE " +
                "Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, codigo INTEGER, nombre VARCHAR(100), apellido VARCHAR(100), semestre INTEGER, promedio DECIMAL)";
        db?.execSQL(crearTabla);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertarDatos(usuario: Usuario):String{
        val db = this.writableDatabase //para escribir en la base de datos
        var contenedordeValores = ContentValues();  //crear contenedor de valores, hay que importarlo
        contenedordeValores.put("codigo", usuario.codigo)
        contenedordeValores.put("nombre", usuario.nombre)
        contenedordeValores.put("apellido", usuario.apellido)
        contenedordeValores.put("semestre", usuario.semestre)
        contenedordeValores.put("promedio", usuario.promedio)
        var resultado=db.insert("Usuario ", null, contenedordeValores)
        if(resultado==-1.toLong()) {
            return "fallas en la inserción";
        }else{
            return "Datos insertados (Listo)"
        }
    }//insertarDatos

    fun traerDatos():MutableList<Usuario>{
        var lista:MutableList<Usuario> = ArrayList() //lista dinamica o mutable
        val db =this.readableDatabase
        //val sql="select * from Usuario where id=10"
        val sql="select * from Usuario"
        val resultado= db.rawQuery(sql,null)
        if(resultado.moveToFirst()){
            do {
                var usuario=Usuario()
                usuario.id= resultado.getString(0).toInt()
                usuario.codigo=resultado.getString(resultado.getColumnIndex("codigo").toInt()).toInt()
                usuario.nombre=resultado.getString(resultado.getColumnIndex("nombre").toInt())
                usuario.apellido=resultado.getString(resultado.getColumnIndex("apellido").toInt())
                usuario.semestre= resultado.getString(resultado.getColumnIndex("semestre").toInt()).toInt()
                usuario.promedio=resultado.getString(resultado.getColumnIndex("promedio").toInt()).toFloat()
                lista.add(usuario)
            }while (resultado.moveToNext())
        }//if
        resultado.close()
        db.close()
        return lista
    }//listar

    fun borrar(codigo:String){
        if(codigo.length>0){
            val db =this.writableDatabase
            db.delete("Usuario","id=?",arrayOf(codigo))
            db.close()
        }
    }//borrar

    fun actualizar(usuario: Usuario):String{
        val db = this.writableDatabase
        var contenedordeValores = ContentValues();
        contenedordeValores.put("codigo", usuario.codigo)
        contenedordeValores.put("nombre", usuario.nombre)
        contenedordeValores.put("apellido",usuario.apellido)
        contenedordeValores.put("semestre",usuario.semestre)
        contenedordeValores.put("promedio",usuario.promedio)
        var resultado=db.update("Usuario", contenedordeValores,
            "id=?", arrayOf(usuario.id.toString()))
        if(resultado>0){
            return "Actualización realizada"
        }else{
            return "no se actualizó código"+usuario.codigo+"no se actualizó nombre "+usuario.nombre+"No se actualizó apellido"+usuario.apellido+"-id-"+usuario.id+"-semestre"+usuario.semestre+"-promedio-"+usuario.promedio
        }
    }//actualizar
}