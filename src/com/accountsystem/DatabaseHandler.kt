package com.accountsystem

import java.util.Properties
import java.sql.*

class DatabaseHandler {
	
	internal var conn: Connection? = null
	internal val username = "root"
	internal val password = "admin"
	internal val path = "127.0.0.1"
	internal val port = "3306"
	
	
	fun setupConnection() {
		val connectionProps = Properties()
		connectionProps.put("user", username)
		connectionProps.put("password", password)
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance()
			conn = DriverManager.getConnection(
					"jdbc:mysql://$path:$port/",
					connectionProps)
			println("Conexao estabelecida")
		} catch (ex: Exception) {
			conn = null
			ex.printStackTrace()
		}
	}
	
	fun closeConnection() {
		conn?.let{
			try {
				it.close()
				println("Conexão Fechada")
			} catch (sqlEx: SQLException) {}
		}
	}
	
	fun execQuery(query: String, call: (ResultSet?)->Unit, params: Array<Any>? = null) {
		var stmt: PreparedStatement? = null
		var resultset: ResultSet? = null
		
		try {
			stmt = conn?.prepareStatement(query)
			
			if(query.contains('?') && params != null) {
				for((index, param) in params.withIndex()) {
					when(param) {
						is Int -> stmt?.setInt(index+1, param)
						is String -> stmt?.setString(index+1, param)
						else -> stmt?.setNull(index+1, -1)
					}
				}
			}
			resultset = if(stmt?.execute()!!) stmt.resultSet else resultset
		} catch (ex: SQLException) {
			ex.printStackTrace()
		}
		
		call(resultset)
		closeStmt(stmt, resultset)
	}
	
	internal fun closeStmt(stmt: Statement?, resultset: ResultSet?) {
		try {
			resultset?.close()
			stmt?.close()
		}catch (sqlEx: SQLException) {}

	}
	
}
