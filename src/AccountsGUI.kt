import javax.swing.*
import java.awt.GridLayout
import java.awt.BorderLayout
import com.accountsystem.AccountDatabaseController
import com.accountsystem.Credor
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

class AccountsGUI(var accountCtrl: AccountDatabaseController): JFrame("Sistema de Contas") {
	
	var mainPanel = JPanel(BorderLayout())
	var northPanel = JPanel(GridLayout(1,1))
	var eastPanel = JPanel(GridLayout(3,1))
	var middlePanel = JPanel(GridLayout(1,1))
	
	var btConsultCredores = JButton("Ver Credores")
	var tfOut = JTextArea()
	
	fun start() {
		accountCtrl.initAccountDatabase()
		this.setSize(640, 480)
		this.setupMainPanel()
		this.setupListenersMethods()
		this.setResizable(false)
		this.setLocationRelativeTo(null)
		this.setVisible(true)
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
	}
	
	fun setupMainPanel() {
		this.setupTopPanel()
		this.setupLeftPanel()
		this.setupMiddlePanel()
		this.mainPanel.add(this.northPanel, BorderLayout.NORTH)
		this.mainPanel.add(this.eastPanel, BorderLayout.EAST)
		this.mainPanel.add(this.middlePanel, BorderLayout.CENTER)
		this.add(mainPanel)
	}
	
	fun setupLeftPanel() {
		this.eastPanel.setBorder(BorderFactory.createEmptyBorder(25,10,25,10))
		this.eastPanel.add(JButton("Inserir Credor"))
		this.eastPanel.add(JButton("Inserir Compra"))
		this.eastPanel.add(JButton("Inserir Parcela"))
	}
	
	fun setupTopPanel() {
		this.northPanel.add(btConsultCredores)
	}
	
	fun setupMiddlePanel() {
		this.middlePanel.add(tfOut)
	}
	
	fun setupListenersMethods() {
		this.addWindowListener(object: WindowAdapter() {
			override fun windowClosing(e: WindowEvent) {
				accountCtrl.closeAccountDatabase()
				System.exit(0)
			}
		})
		this.btConsultCredores.addActionListener({
			exibirCredores()
		})
	}
	
	fun exibirCredores() {
		for(credor in accountCtrl.consultCredores(5)) {
			if(credor is Credor) tfOut.append("$credor \n")
		}
	}
	
}
