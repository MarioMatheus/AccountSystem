import javax.swing.*
import com.accountsystem.AccountDatabaseController
import com.accountsystem.Credor
import com.accountsystem.Compra
import com.accountsystem.Parcela
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

class AccountsGUI(var accountCtrl: AccountDatabaseController): JFrame("Sistema de Contas") {
	
	var menuBar = JMenuBar()
	
	var mainPanel = JPanel(BorderLayout())
	var northPanel = JPanel(GridLayout(1,1))
	var eastPanel = JPanel(GridLayout(5,1))
	var middlePanel = JPanel(GridLayout(1,1))
	
	var tools = JPanel(GridLayout(1,6))
	
	var btBusca = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/busca.png"))
	var btListarCredores = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/listar-credores.png"))
	var btListarCompras = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/listar-compras.png"))
	var btListarParcelas = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/listar-parcelas.png"))
	
	var btAddCredor = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/add-credor.png"))
	var btAddCompra = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/add-compra.png"))
	var btAddParcela = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/add-parcela.png"))
	var btFiltro = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/filtro.png"))
	var btInfo = JButton(ImageIcon("C:/Users/Mario/Documents/drawSprites/forAccSystem/info.png"))
	
	var tfOut = JTextArea()
	
	
	fun start() {
		accountCtrl.initAccountDatabase()
		this.setupMenu()
		this.setJMenuBar(menuBar)
		this.setMinimumSize(Dimension(840, 620))
		this.setupMainPanel()
		this.setupListenersMethods()
		this.setLocationRelativeTo(null)
		this.setVisible(true)
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
	}
	
	fun setupMenu() {
		var arquivo = JMenu("Arquivo")
		var buscas = JMenu("Buscas")
		var ajuda = JMenu("Ajuda")
		
		var abrir = JMenuItem("Abrir")
		var salvar = JMenuItem("Salvar")
		var sair = JMenuItem("Sair")
		
		arquivo.add(abrir)
		arquivo.add(salvar)
		arquivo.add(sair)
		
		var inst = JMenuItem("Instrucoes")
		var sobre = JMenuItem("Sobre")
		ajuda.add(inst)
		ajuda.add(sobre)
		
		menuBar.add(arquivo)
		menuBar.add(buscas)
		menuBar.add(ajuda)
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
		this.eastPanel.add(btAddCredor)
		this.eastPanel.add(btAddCompra)
		this.eastPanel.add(btAddParcela)
		this.eastPanel.add(btFiltro)
		this.eastPanel.add(btInfo)
	}
	
	fun setupTopPanel() {
		tools.add(JTextField())
		tools.add(btBusca)
		tools.add(JPanel())
		tools.add(btListarCredores)
		tools.add(btListarCompras)
		tools.add(btListarParcelas)
		
		this.northPanel.add(tools)
	}
	
	fun setupMiddlePanel() {
		this.middlePanel.setBorder(BorderFactory.createEmptyBorder(25,10,25,10))
		this.middlePanel.add(tfOut)
	}
	
	fun setupListenersMethods() {
		this.addWindowListener(object: WindowAdapter() {
			override fun windowClosing(e: WindowEvent) {
				accountCtrl.closeAccountDatabase()
				System.exit(0)
			}
		})
		this.btListarCredores.addActionListener({
			exibirCredores()
		})
		this.btListarCompras.addActionListener({
			exibirCompras()
		})
		this.btListarParcelas.addActionListener({
			exibirParcelas()
		})
		this.btAddCredor.addActionListener({
			abrirJanelaAdicaoCredor()
		})
		this.btFiltro.addActionListener({
			abrirJanelaFiltro()
		})
	}
	
	fun exibirCredores() {
		for(credor in accountCtrl.consultCredores(30)) {
			if(credor is Credor) tfOut.append("$credor \n")
		}
		tfOut.append("\n")
	}
	
	fun exibirCompras() {
		for(compra in accountCtrl.consultCompras(30)) {
			if(compra is Compra) tfOut.append("$compra \n")
		}
		tfOut.append("\n")
	}
	
	fun exibirParcelas() {
		for(parcela in accountCtrl.consultParcelas(30)) {
			if(parcela is Parcela) tfOut.append("$parcela \n")
		}
		tfOut.append("\n")
	}
	
	fun abrirJanelaAdicaoCredor() {
		var janela = JFrame("Inserir Credor")
		var lbCodigo = JLabel("Codigo: ")
		var lbNome = JLabel("Nome: ")
		var txCodigo = JTextField(5)
		var txNome = JTextField(15)
		var painel = JPanel(GridLayout(5,1))
		var botao = JButton("Inserir")
		
		botao.addActionListener({
			val cod = (txCodigo.getText()).toInt()
			val nome = txNome.getText()
			accountCtrl.insertCredor(Credor(cod, nome))
			JOptionPane.showMessageDialog(janela, "Inserido com sucesso")
			janela.dispose()
		})
		
		painel.add(lbCodigo)
		painel.add(txCodigo)
		painel.add(lbNome)
		painel.add(txNome)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,180)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}
	
	fun abrirJanelaFiltro() {
		var janela = JFrame("Filtros")
		var painel = JPanel(GridLayout(3,1))
		val filtros = arrayOf(
				"Item A", "Item B", "Item C", "Item D", "Item E",
				"Item F", "Item G", "Item H", "Item I", "Item J",
				"Item K", "Item L", "Item M", "Item N", "Item O",
				"Item P", "Item Q", "Item R", "Item S", "Item T",
				"Item U", "Item V", "Item W")
		val cbFiltros = JComboBox(filtros)
		val botao = JButton("Filtrar")
		botao.addActionListener({
			val itemSelecionado = (cbFiltros.getSelectedItem()).toString()
			execItem(itemSelecionado)
			janela.dispose()
		})
		
		painel.add(JLabel("Filtros"))
		painel.add(cbFiltros)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,120)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}
	
	fun execItem(item: String) {
		val resultados = when(item) {
			"Item B" -> accountCtrl.execItemB()
			else -> arrayOf("Nada Encontrado")
		}
		tfOut.setText("")
		//algo mais
	}
	
}
