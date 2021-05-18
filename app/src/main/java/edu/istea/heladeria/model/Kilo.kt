package edu.istea.heladeria.model

import java.io.Serializable

data class Kilo (var Id: Int,
                 var PrimerGusto: String,
                 var SegundoGusto: String,
                 var TercerGusto: String,
                 var PrimerPlus: String,
                 var SegundoPlus: String):Serializable