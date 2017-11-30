package com.accountsystem

import java.sql.ResultSet

class AccountDatabaseController(val dbHandler: DatabaseHandler) {
	
	fun initAccountDatabase() {
		dbHandler.setupConnection()
		//dbHandler.execQuery("use accountsystem;", {})
		dbHandler.execQuery("use Teste;", {})
		println("Usando DB Teste")
	}
	
	fun closeAccountDatabase() {
		dbHandler.closeConnection()
	}
	
	fun insertCredor(credor: Credor) {
		val parameters = arrayOf(credor.codCredor, credor.nomeCredor)
		dbHandler.execQuery("INSERT INTO credores (cod_credor, credor) VALUES (?, ?);", {}, parameters)
	}
	
	fun consultCredores(qtdCredores: Int): Array<Credor?> {
		var credores = Array<Credor?>(qtdCredores, {null})
		var i = 0
		dbHandler.execQuery("SELECT * FROM credores;", { resultset ->
			while(resultset!!.next() && i<qtdCredores) {
				credores[i] = Credor(
						resultset.getInt("cod_credor"),
						resultset.getString("credor"))
				i++
			}
		})
		return credores
	}
	
}
