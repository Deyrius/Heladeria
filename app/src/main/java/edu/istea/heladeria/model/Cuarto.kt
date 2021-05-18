package edu.istea.heladeria.model

import java.io.Serializable

data class Cuarto ( var Id: Int,
                    var PrimerGusto: String,
                    var SegundoGusto: String,
                    var TercerGusto: String,
                    var Plus: String): Serializable