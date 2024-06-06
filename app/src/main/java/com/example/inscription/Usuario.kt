package com.example.inscription
class Usuario {
    var id:Int=0
    var codigo:Int=0
    var nombre:String=""
    var apellido:String=""
    var semestre:Int=0
    var promedio:Float=0.0F
    constructor(codigo:Int, nombre:String,  apellido:String, semestre:Int, promedio:Float ){
        this.codigo=codigo
        this.nombre=nombre
        this.apellido=apellido
        this.semestre=semestre
        this.promedio=promedio
    }
    //sobre carga de mètodos
    constructor(id:Int,codigo:Int, nombre:String,  apellido:String, semestre:Int, promedio:Float ){
        this.id=id
        this.codigo=codigo
        this.nombre=nombre
        this.apellido=apellido
        this.semestre=semestre
        this.promedio=promedio
    }
    constructor(){
    }
}
