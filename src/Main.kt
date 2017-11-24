import com.accountsystem.DatabaseHandler

import java.sql.ResultSet

fun main(args: Array<String>) {
	println("Hello MySQL")
	
	var db = DatabaseHandler()
	var id: Int
	var v1: Int
	var v2: Int
	
	db.setupConnection()
	db.execQuery("use teste;", {})
	
	db.execQuery("select * from foo;", { resultset ->
		while(resultset!!.next()) {
			id = resultset.getInt("id")
			v1 = resultset.getInt("value")
			v2 = resultset.getInt("valuedois")
			
			println("Foo (ID: $id, Value: $v1, ValueII: $v2)")
		}
	})
	
	db.execQuery("select * from foo where id=?;", { resultset ->
		while(resultset!!.next()) {
			id = resultset.getInt("id")
			v1 = resultset.getInt("value")
			v2 = resultset.getInt("valuedois")
			
			println("\nFoo (ID: $id, Value: $v1, ValueII: $v2)")
		}
	}, arrayOf(2))
	
	db.closeConnection()
}