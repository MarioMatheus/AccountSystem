package com.accountsystem

import java.sql.ResultSet

class AccountDatabaseController(val dbHandler: DatabaseHandler) {
	
	fun initAccountDatabase() {
		dbHandler.setupConnection()
		dbHandler.execQuery("use accountsystem;", {})
		println("Usando DB AccountSystem")
		//dbHandler.execQuery("use Teste;", {})
	}
	
	fun closeAccountDatabase() {
		dbHandler.closeConnection()
	}
	
	fun insertCredor(credor: Credor) {
		val parameters = arrayOf(
				credor.codCredor,
				credor.nomeCredor)
		
		dbHandler.execQuery(
				"""
				INSERT INTO credores (cod_credor, credor)
				VALUES (?, ?);
				""", {}, parameters)
	}
	
	fun insertCompra(compra: Compra) {
		val parameters = arrayOf(
				compra.codCompra,
				compra.dataCompra,
				compra.valorCompra,
				compra.tipo,
				compra.codCredor)
		
		dbHandler.execQuery(
				"""
				INSERT INTO compras (cod_compra, data_compra, valor_compra, tipo, cod_credor)
				VALUES (?, ?, ?, ?, ?);
				""", {}, parameters)
	}
	
	fun insertParcela(parcela: Parcela) {
		val parameters = arrayOf(
				parcela.codCompra,
				parcela.sequencia,
				parcela.valorParcela,
				parcela.dataVenc,
				parcela.dataPaga,
				parcela.multa,
				parcela.juros) 
		
		dbHandler.execQuery(
				"""
				INSERT INTO parcelas (cod_compra, sequencia, valor_parcela, data_venc, data_paga, multa, juros)
				VALUES (?, ?, ?, ?, ?, ?, ?);
				""", {}, parameters)
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
	
	fun consultCompras(qtdCompras: Int): Array<Compra?> {
		var compras = Array<Compra?>(qtdCompras, {null})
		var i = 0
		dbHandler.execQuery("SELECT * FROM compras;", { resultset ->
			while(resultset!!.next() && i<qtdCompras) {
				compras[i] = Compra(
						resultset.getInt("cod_compra"),
						resultset.getString("data_compra"),
						resultset.getInt("valor_compra"),
						resultset.getInt("tipo"),
						resultset.getInt("cod_credor"))
				i++
			}
		})
		return compras
	}
	
	fun consultParcelas(qtdParcelas: Int): Array<Parcela?> {
		var parcelas = Array<Parcela?>(qtdParcelas, {null})
		var i = 0
		dbHandler.execQuery("SELECT * FROM parcelas;", { resultset ->
			while(resultset!!.next() && i<qtdParcelas) {
				parcelas[i] = Parcela(
						resultset.getInt("cod_compra"),
						resultset.getInt("sequencia"),
						resultset.getInt("valor_parcela"),
						resultset.getString("data_venc"),
						resultset.getString("data_paga"),
						resultset.getInt("multa"),
						resultset.getInt("juros"))
				i++
			}
		})
		return parcelas
	}
	
	fun execItem(item: String, consulta: String, params: Array<Any>? = null): String {
		var strBuilder = StringBuilder()
		strBuilder.append(consulta)
		
		dbHandler.execQuery(
				item,
				{resultset ->
					val columnsNames = getColumnsNames(resultset)
					var row = 1
										
					while(resultset!!.next()) {
						strBuilder.append("    Linha: ${row++} ->")
						for((i,columnName) in columnsNames.withIndex()) {
							strBuilder.append(if(i!=0) ", " else " ")
							strBuilder.append(columnName)
							strBuilder.append(": ")
							strBuilder.append(resultset.getString(columnName))
						}
						strBuilder.append("\n")
					}
				}, params)
		
		return strBuilder.toString()
	}
	
	private fun getColumnsNames(resultset: ResultSet?): ArrayList<String> {
		var i = 1
		var columnsNames = ArrayList<String>()
		val resMetaData = resultset!!.getMetaData()
		
		while(i <= resMetaData.getColumnCount()) {
			columnsNames.add(resMetaData.getColumnName(i))
			i++
		}
		
		return columnsNames
	}
	
}
