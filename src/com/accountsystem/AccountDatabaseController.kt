package com.accountsystem

import model.Compra
import model.Credor
import model.Parcela
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
		val credores = Array<Credor?>(qtdCredores, {null})
		var i = 0
		dbHandler.execQuery("SELECT * FROM credores;", {
			while(it!!.next() && i<qtdCredores) {
				credores[i] = Credor(
						it.getInt("cod_credor"),
						it.getString("credor"))
				i++
			}
		})
		return credores
	}
	
	fun consultCompras(qtdCompras: Int): Array<Compra?> {
		val compras = Array<Compra?>(qtdCompras, {null})
		var i = 0
		dbHandler.execQuery("SELECT * FROM compras;", {

			while(it!!.next() && i<qtdCompras) {
				compras[i] = Compra(
						it.getInt("cod_compra"),
						it.getString("data_compra"),
						it.getInt("valor_compra"),
						it.getInt("tipo"),
						it.getInt("cod_credor"))
				i++
			}
		})
		return compras
	}
	
	fun consultParcelas(qtdParcelas: Int): Array<Parcela?> {
		val parcelas = Array<Parcela?>(qtdParcelas, {null})
		var i = 0
		dbHandler.execQuery("SELECT * FROM parcelas;", {
			while(it!!.next() && i<qtdParcelas) {
				parcelas[i] = Parcela(
						it.getInt("cod_compra"),
						it.getInt("sequencia"),
						it.getInt("valor_parcela"),
						it.getString("data_venc"),
						it.getString("data_paga"),
						it.getInt("multa"),
						it.getInt("juros"))
				i++
			}
		})
		return parcelas
	}
	
	fun execItem(item: String, consulta: String, params: Array<Any>? = null): String {
		val strBuilder = StringBuilder()
		strBuilder.append(consulta)
		
		dbHandler.execQuery(item, {resultset ->
					val columnsNames = getColumnsNames(resultset)
					var row = 1

					while(resultset!!.next()) {
						strBuilder.append("    Linha: ${row++} ->")
						for((i,columnName) in columnsNames.withIndex()) {
							strBuilder.run {
								append(if(i!=0) ", " else " ")
								append(columnName)
								append(": ")
								append(resultset.getString(columnName))
							}
						}
						strBuilder.append("\n")
					}
				}, params)
		
		return strBuilder.toString()
	}
	
	private fun getColumnsNames(resultset: ResultSet?): ArrayList<String> {
		var i = 1
		val columnsNames = ArrayList<String>()
		val resMetaData = resultset!!.metaData
		
		while(i <= resMetaData.columnCount) {
			columnsNames.add(resMetaData.getColumnName(i))
			i++
		}
		
		return columnsNames
	}
	
}
