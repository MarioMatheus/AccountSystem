/* Getting Started the Application */
import com.accountsystem.AccountDatabaseController
import com.accountsystem.DatabaseHandler

fun main(args: Array<String>) {
	var dbHandler = DatabaseHandler()
	var accountCtrl = AccountDatabaseController(dbHandler)
	var window = AccountsGUI(accountCtrl)
	window.start()
}
