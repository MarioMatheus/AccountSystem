import javax.swing.*
import java.awt.GridLayout
import com.accountsystem.AccountDatabaseController

class AccountsGUI(var accountCtrl: AccountDatabaseController): JFrame("Sistema de Contas") {
	
	var mainPanel = JPanel(GridLayout(3,1))
	var topPanel = JPanel(GridLayout(1,3))
	var middlePanel = JPanel(GridLayout(1,1))
	var bottomPanel = JPanel(GridLayout(1,1))
	
	fun start() {
		this.setSize(640, 480)
		this.setupMainPanel()
		this.setResizable(false)
		this.setLocationRelativeTo(null)
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true)
	}
	
	fun setupMainPanel() {
		this.setupTopPanel()
		this.setupMiddlePanel()
		this.setupBottomPanel()
		this.mainPanel.add(this.topPanel)
		this.mainPanel.add(this.middlePanel)
		this.mainPanel.add(this.bottomPanel)
		this.add(mainPanel)
	}
	
	fun setupTopPanel() {
		this.topPanel.setBorder(BorderFactory.createEmptyBorder(25,10,25,10))
		this.topPanel.add(JButton("Inserir Credor"))
		this.topPanel.add(JButton("Inserir Compra"))
		this.topPanel.add(JButton("Inserir Parcela"))
	}
	
	fun setupMiddlePanel() {
		this.middlePanel.add(JButton("Teste"))
	}
	
	fun setupBottomPanel() {
		this.bottomPanel.add(JTextField())
	}
	
}
