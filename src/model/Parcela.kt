package model

data class Parcela(
        var codCompra: Int,
        var sequencia: Int,
        var valorParcela: Int,
        var dataVenc: String,
        var dataPaga: String,
        var multa: Int,
        var juros: Int)