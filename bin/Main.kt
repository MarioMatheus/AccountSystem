/* Getting Started the Application */
import com.accountsystem.AccountDatabaseController
import com.accountsystem.DatabaseHandler
import com.accountsystem.Credor

fun main(args: Array<String>) {
	println("Hello Kotlin and MySQL")
	var dbHandler = DatabaseHandler()
	var accountCtrl = AccountDatabaseController(dbHandler)
	
	dbHandler.setupConnection()
	accountCtrl.initAccountDatabase()
	//accountCtrl.insertCredor(Credor(1,"Mario"))
	dbHandler.closeConnection()
}
