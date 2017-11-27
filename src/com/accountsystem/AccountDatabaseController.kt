package com.accountsystem

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
