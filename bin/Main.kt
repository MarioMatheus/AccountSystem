/* Getting Started the Application */
import com.accountsystem.AccountDatabaseController
import com.accountsystem.DatabaseHandler
import com.accountsystem.Credor

fun DatabaseTest() {
	var dbHandler = DatabaseHandler()
	var accountCtrl = AccountDatabaseController(dbHandler)
	
	dbHandler.setupConnection()
	accountCtrl.initAccountDatabase()
	//accountCtrl.insertCredor(Credor(1,"Mario"))
	dbHandler.closeConnection()
}

fun main(args: Array<String>) {
	var dbHandler = DatabaseHandler()
	var accountCtrl = AccountDatabaseController(dbHandler)
	var window = AccountsGUI(accountCtrl)
	
	window.start()
}
