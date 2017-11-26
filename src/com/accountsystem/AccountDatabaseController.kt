package com.accountsystem

data class Credor(
		var codCredor: Int,
		var nomeCredor: String)

data class Compra(
		var codCompra: Int,
		var dataCompra: String,
		var valorCompra: Int,
		var tipo: Int,
		var credor: Credor)

data class Parcela(
		var compra: Compra,
		var sequencia: Int,
		var valorParcela: Int,
		var dataVenc: String,
		var dataPaga: String,
		var multa: Int,
		var juros: Int)

class AccountDatabaseController(val dbHandler: DatabaseHandler) {
	
	fun initAccountDatabase() {
		dbHandler.execQuery("use accountsystem;", {})
	}
	
	fun insertCredor(credor: Credor) {
		val parameters = arrayOf(credor.codCredor, credor.nomeCredor)
		dbHandler.execQuery("INSERT INTO credores (cod_credor, credor) VALUES (?, ?);", {}, parameters)
	}
	
	fun insertCompra(compra: Compra) {
		// insert compra here
	}
	
}
