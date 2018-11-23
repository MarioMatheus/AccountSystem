/* Getting Started the Application */
import com.accountsystem.AccountDatabaseController
import com.accountsystem.DatabaseHandler
import ui.AccountsGUI

fun main(args: Array<String>) {
	val accountCtrl = AccountDatabaseController(DatabaseHandler())
	AccountsGUI(accountCtrl)
}
