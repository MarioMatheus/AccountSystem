import javax.swing.*
import com.accountsystem.AccountDatabaseController
import com.accountsystem.Item
import com.accountsystem.Consultas
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
	
	var tools = JPanel(GridLayout(1,7))
	
	var btBusca = JButton(criarImageIcon("img/busca.png", "busca"))
	var btLimpar = JButton("Limpar")
	var btListarCredores = JButton(criarImageIcon("img/listar-credores.png", "listar-credores"))
	var btListarCompras = JButton(criarImageIcon("img/listar-compras.png", "listar-compras"))
	var btListarParcelas = JButton(criarImageIcon("img/listar-parcelas.png", "listar-parcelas"))
	
	var btAddCredor = JButton(criarImageIcon("img/add-credor.png", "add-credor"))
	var btAddCompra = JButton(criarImageIcon("img/add-compra.png", "add-compra"))
	var btAddParcela = JButton(criarImageIcon("img/add-parcela.png", "add-parcela"))
	var btFiltro = JButton(criarImageIcon("img/filtro.png", "filtro"))
	var btInfo = JButton(criarImageIcon("img/info.png", "info"))
	
	var tfOut = JTextArea()
	var scroll = JScrollPane (tfOut, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS)
	
	
	fun start() {
		accountCtrl.initAccountDatabase()
		this.setupMenu()
		this.setJMenuBar(menuBar)
		this.setMinimumSize(Dimension(1100, 620))
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
		tools.add(btLimpar)
		tools.add(JPanel())
		tools.add(btListarCredores)
		tools.add(btListarCompras)
		tools.add(btListarParcelas)
		
		this.northPanel.add(tools)
	}
	
	fun setupMiddlePanel() {
		this.middlePanel.setBorder(BorderFactory.createEmptyBorder(25,10,25,10))
		this.middlePanel.add(scroll)
	}
	
	fun setupListenersMethods() {
		this.addWindowListener(object: WindowAdapter() {
			override fun windowClosing(e: WindowEvent) {
				accountCtrl.closeAccountDatabase()
				System.exit(0)
			}
		})
		this.btLimpar.addActionListener({
			tfOut.setText("")
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
		this.btAddCompra.addActionListener({
			abrirJanelaAdicaoCompra()
		})
		this.btAddParcela.addActionListener({
			abrirJanelaAdicaoParcela()
		})
		this.btFiltro.addActionListener({
			abrirJanelaFiltro()
		})
		this.btInfo.addActionListener({
			exibirConsultas()
		})
	}
	
	fun exibirConsultas() {
		tfOut.setText("")
		for(consulta in Consultas.values()) {
			if(consulta is Consultas) tfOut.append("${consulta}) ${consulta.consulta}")
		}
		tfOut.append("\n")
	}
	
	fun exibirCredores() {
		tfOut.append("\n")
		for(credor in accountCtrl.consultCredores(30)) {
			if(credor is Credor) tfOut.append("$credor \n")
		}
		tfOut.append("\n")
	}
	
	fun exibirCompras() {
		tfOut.append("\n")
		for(compra in accountCtrl.consultCompras(30)) {
			if(compra is Compra) tfOut.append("$compra \n")
		}
		tfOut.append("\n")
	}
	
	fun exibirParcelas() {
		tfOut.append("\n")
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
	
	fun abrirJanelaAdicaoCompra() {
		var janela = JFrame("Inserir Compra")
		var lbCodigo = JLabel("Codigo: ")
		var lbData = JLabel("Data: ")
		var lbValor = JLabel("Valor: ")
		var lbTipo = JLabel("Tipo: ")
		var lbCredor = JLabel("Credor: ")
		
		var txCodigo = JTextField(5)
		var txData = JTextField(15)
		var txValor = JTextField(15)
		var txTipo = JTextField(1)
		var txCredor = JTextField(5)
		
		var painel = JPanel(GridLayout(11,1))
		var botao = JButton("Inserir")
		
		botao.addActionListener({
			val cod = (txCodigo.getText()).toInt()
			val data = txData.getText()
			val valor = (txValor.getText()).toInt()
			val tipo = (txTipo.getText()).toInt()
			val credor = (txCredor.getText()).toInt()
			accountCtrl.insertCompra(Compra(cod, data, valor, tipo, credor))
			JOptionPane.showMessageDialog(janela, "Inserido com sucesso")
			janela.dispose()
		})
		
		painel.add(lbCodigo)
		painel.add(txCodigo)
		painel.add(lbData)
		painel.add(txData)
		painel.add(lbValor)
		painel.add(txValor)
		painel.add(lbTipo)
		painel.add(txTipo)
		painel.add(lbCredor)
		painel.add(txCredor)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,260)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}
	
	fun abrirJanelaAdicaoParcela() {
		var janela = JFrame("Inserir Parcela")
		var lbCodigo = JLabel("Código da Compra: ")
		var lbSeq = JLabel("Sequencia: ")
		var lbValor = JLabel("Valor da Parcela: ")
		var lbVenc = JLabel("Data de Vencimento: ")
		var lbPaga = JLabel("Data de Pagamento: ")
		var lbMulta = JLabel("Multa: ")
		var lbJuros = JLabel("Juros: ")
		
		var txCodigo = JTextField(5)
		var txSeq = JTextField(5)
		var txValor = JTextField(15)
		var txVenc = JTextField(15)
		var txPaga = JTextField(15)
		var txMulta = JTextField(15)
		var txJuros = JTextField(15)
		
		var painel = JPanel(GridLayout(15,1))
		var botao = JButton("Inserir")
		
		botao.addActionListener({
			val cod = (txCodigo.getText()).toInt()
			val seq = (txSeq.getText()).toInt()
			val valor = (txValor.getText()).toInt()
			val venc = txVenc.getText()
			val paga = if(txPaga.getText() == "") "2099-12-31" else txPaga.getText()
			val multa = if(txMulta.getText() == "") 0 else (txMulta.getText()).toInt() 
			val juros = if(txJuros.getText() == "") 0 else (txJuros.getText()).toInt()
			
			accountCtrl.insertParcela(Parcela(cod, seq, valor, venc, paga, multa, juros))
			JOptionPane.showMessageDialog(janela, "Inserido com sucesso")
			janela.dispose()
		})
		
		painel.add(lbCodigo)
		painel.add(txCodigo)
		painel.add(lbSeq)
		painel.add(txSeq)
		painel.add(lbValor)
		painel.add(txValor)
		painel.add(lbVenc)
		painel.add(txVenc)
		painel.add(lbPaga)
		painel.add(txPaga)
		painel.add(lbMulta)
		painel.add(txMulta)
		painel.add(lbJuros)
		painel.add(txJuros)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,340)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}
	
	fun abrirJanelaFiltro() {
		var janela = JFrame("Filtros")
		var painel = JPanel(GridLayout(3,1))
		val cbFiltros = JComboBox(Item.values())
		val botao = JButton("Filtrar")
		botao.addActionListener({
			val itemSelecionado = (cbFiltros.getSelectedItem()) as Item
			checkParameters(itemSelecionado, {params ->
				execItem(itemSelecionado.query, selectConsulta(itemSelecionado), params)
			})
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
	
	fun execItem(item: String, consulta: String, params: Array<Any>?) {
		this.tfOut.setText(accountCtrl.execItem(item, consulta, params))
	}
	
	fun checkParameters(item: Item, call: (Array<Any>?)->Unit) {
		when(item) {
			Item.D -> getDparams({cod ->
					call(arrayOf(cod))
				})
			Item.G -> getGparams({cod ->
					call(arrayOf(cod))
				})
			Item.I -> getIparams({dataVenc ->
					call(arrayOf(dataVenc))
				})
			Item.W -> getWparams({data1, data2 ->
					call(arrayOf(data1, data2))
				})
			else -> call(null)
		}
	}
	
	fun getDparams(call: (Int)->Unit) {
		var janela = JFrame("Inserir Parametros")
		var lbCodigo = JLabel("Código da Credor: ")
		var txCodigo = JTextField(5)
		var painel = JPanel(GridLayout(3,1))
		var botao = JButton("Inserir")
		
		botao.addActionListener({
			val cod = (txCodigo.getText()).toInt()
			call(cod)
			janela.dispose()
		})
		
		painel.add(lbCodigo)
		painel.add(txCodigo)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,120)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}

	fun getGparams(call: (Int)->Unit) {
		var janela = JFrame("Inserir Parametros")
		var lbCodigo = JLabel("Código da Compra: ")
		var txCodigo = JTextField(5)
		var painel = JPanel(GridLayout(3,1))
		var botao = JButton("Inserir")
		
		botao.addActionListener({
			val cod = (txCodigo.getText()).toInt()
			call(cod)
			janela.dispose()
		})
		
		painel.add(lbCodigo)
		painel.add(txCodigo)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,120)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}
	
	fun getIparams(call: (String)->Unit) {
		var janela = JFrame("Inserir Parametros")
		var lbVenc = JLabel("Data de Vencimento: ")
		var txVenc = JTextField(15)
		var painel = JPanel(GridLayout(3,1))
		var botao = JButton("Inserir")
		
		botao.addActionListener({
			val dataVenc = txVenc.getText()
			call(dataVenc)
			janela.dispose()
		})
		
		painel.add(lbVenc)
		painel.add(txVenc)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,120)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}
	
	fun getWparams(call: (String, String)->Unit) {
		var janela = JFrame("Inserir Parametros")
		var lbData1 = JLabel("Compras Realizadas entre: ")
		var txData1 = JTextField(15)
		var lbData2 = JLabel("e: ")
		var txData2 = JTextField(15)
		var painel = JPanel(GridLayout(5,1))
		var botao = JButton("Inserir")
		
		botao.addActionListener({
			val data1 = txData1.getText()
			val data2 = txData2.getText()
			call(data1, data2)
			janela.dispose()
		})
		
		painel.add(lbData1)
		painel.add(txData1)
		painel.add(lbData2)
		painel.add(txData2)
		painel.add(botao)
		
		janela.add(painel)
		janela.setSize(300,200)
		janela.setResizable(false)
		janela.setLocationRelativeTo(null)
		janela.setVisible(true)
	}
		
	fun selectConsulta(item: Item): String {
		return when(item) {
			Item.A -> Consultas.A.consulta
			Item.B -> Consultas.B.consulta
			Item.C -> Consultas.C.consulta
			Item.D -> Consultas.D.consulta
			Item.E -> Consultas.E.consulta
			Item.F -> Consultas.F.consulta
			Item.G -> Consultas.G.consulta
			Item.H -> Consultas.H.consulta
			Item.I -> Consultas.I.consulta
			Item.J -> Consultas.J.consulta
			Item.K -> Consultas.K.consulta
			Item.L -> Consultas.L.consulta
			Item.M -> Consultas.M.consulta
			Item.N -> Consultas.N.consulta
			Item.O -> Consultas.O.consulta
			Item.P -> Consultas.P.consulta
			Item.Q -> Consultas.Q.consulta
			Item.R -> Consultas.R.consulta
			Item.S -> Consultas.S.consulta
			Item.T -> Consultas.T.consulta
			Item.U -> Consultas.U.consulta
			Item.V -> Consultas.V.consulta
			Item.W -> Consultas.W.consulta
			else -> "No question"
		}
	}
	
	fun criarImageIcon(caminho:String, descricao:String): ImageIcon? {
		val imgURL = javaClass.getResource(caminho)
		if (imgURL != null) {
			return ImageIcon(imgURL, descricao)
		} else {
			System.err.println("Não foi possível carregar o arquivo de imagem: " + caminho)
			return null
		}
	}
	
}
