package ui

import com.accountsystem.AccountDatabaseController
import com.accountsystem.Item
import extension.selectConsulta
import extension.start
import model.Compra
import model.Credor
import model.Parcela
import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.*

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

	init {
	    start()
	}
	

	fun abrirJanelaAdicaoCredor() {
		val janela = JFrame("Inserir Credor")
		val lbCodigo = JLabel("Codigo: ")
		val lbNome = JLabel("Nome: ")
		val txCodigo = JTextField(5)
		val txNome = JTextField(15)
		val painel = JPanel(GridLayout(5,1))
		val botao = JButton("Inserir")
		
		botao.addActionListener {
			val cod = (txCodigo.text).toInt()
			val nome = txNome.text
			accountCtrl.insertCredor(Credor(cod, nome))
			JOptionPane.showMessageDialog(janela, "Inserido com sucesso")
			janela.dispose()
		}

		painel.run {
			add(lbCodigo)
			add(txCodigo)
			add(lbNome)
			add(txNome)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,180)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}
	
	fun abrirJanelaAdicaoCompra() {
		val janela = JFrame("Inserir Compra")
		val lbCodigo = JLabel("Codigo: ")
		val lbData = JLabel("Data: ")
		val lbValor = JLabel("Valor: ")
		val lbTipo = JLabel("Tipo: ")
		val lbCredor = JLabel("Credor: ")
		
		val txCodigo = JTextField(5)
		val txData = JTextField(15)
		val txValor = JTextField(15)
		val txTipo = JTextField(1)
		val txCredor = JTextField(5)
		
		val painel = JPanel(GridLayout(11,1))
		val botao = JButton("Inserir")
		
		botao.addActionListener {
			val cod = (txCodigo.text).toInt()
			val data = txData.text
			val valor = (txValor.text).toInt()
			val tipo = (txTipo.text).toInt()
			val credor = (txCredor.text).toInt()
			accountCtrl.insertCompra(Compra(cod, data, valor, tipo, credor))
			JOptionPane.showMessageDialog(janela, "Inserido com sucesso")
			janela.dispose()
		}

		painel.run {
			add(lbCodigo)
			add(txCodigo)
			add(lbData)
			add(txData)
			add(lbValor)
			add(txValor)
			add(lbTipo)
			add(txTipo)
			add(lbCredor)
			add(txCredor)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,260)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}
	
	fun abrirJanelaAdicaoParcela() {
		val janela = JFrame("Inserir Parcela")
		val lbCodigo = JLabel("C�digo da Compra: ")
		val lbSeq = JLabel("Sequencia: ")
		val lbValor = JLabel("Valor da Parcela: ")
		val lbVenc = JLabel("Data de Vencimento: ")
		val lbPaga = JLabel("Data de Pagamento: ")
		val lbMulta = JLabel("Multa: ")
		val lbJuros = JLabel("Juros: ")
		
		val txCodigo = JTextField(5)
		val txSeq = JTextField(5)
		val txValor = JTextField(15)
		val txVenc = JTextField(15)
		val txPaga = JTextField(15)
		val txMulta = JTextField(15)
		val txJuros = JTextField(15)
		
		val painel = JPanel(GridLayout(15,1))
		val botao = JButton("Inserir")
		
		botao.addActionListener {
			val cod = txCodigo.text.toInt()
			val seq = txSeq.text.toInt()
			val valor = txValor.text.toInt()
			val venc = txVenc.text
			val paga = if(txPaga.text.isEmpty()) "2099-12-31" else txPaga.text
			val multa = if(txMulta.text.isEmpty()) 0 else txMulta.text.toInt()
			val juros = if(txJuros.text.isEmpty()) 0 else txJuros.text.toInt()

			accountCtrl.insertParcela(Parcela(cod, seq, valor, venc, paga, multa, juros))
			JOptionPane.showMessageDialog(janela, "Inserido com sucesso")
			janela.dispose()
		}

		painel.run {
			add(lbCodigo)
			add(txCodigo)
			add(lbSeq)
			add(txSeq)
			add(lbValor)
			add(txValor)
			add(lbVenc)
			add(txVenc)
			add(lbPaga)
			add(txPaga)
			add(lbMulta)
			add(txMulta)
			add(lbJuros)
			add(txJuros)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,340)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}
	
	fun abrirJanelaFiltro() {
		val janela = JFrame("Filtros")
		val painel = JPanel(GridLayout(3,1))
		val cbFiltros = JComboBox(Item.values())
		val botao = JButton("Filtrar")
		botao.addActionListener {
			val itemSelecionado = (cbFiltros.selectedItem) as Item
			checkParameters(itemSelecionado) { params ->
				execItem(itemSelecionado.query, selectConsulta(itemSelecionado), params)
			}
			janela.dispose()
		}

		painel.run {
			add(JLabel("Filtros"))
			add(cbFiltros)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,120)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}
	
	fun execItem(item: String, consulta: String, params: Array<Any>?) {
		this.tfOut.text = accountCtrl.execItem(item, consulta, params)
	}
	
	fun checkParameters(item: Item, call: (Array<Any>?)->Unit) {
		when(item) {
			Item.D -> getDparams { cod ->
				call(arrayOf(cod))
			}
			Item.G -> getGparams { cod ->
				call(arrayOf(cod))
			}
			Item.I -> getIparams { dataVenc ->
				call(arrayOf(dataVenc))
			}
			Item.W -> getWparams { data1, data2 ->
				call(arrayOf(data1, data2))
			}
			else -> call(null)
		}
	}
	
	fun getDparams(call: (Int)->Unit) {
		val janela = JFrame("Inserir Parametros")
		val lbCodigo = JLabel("C�digo da Credor: ")
		val txCodigo = JTextField(5)
		val painel = JPanel(GridLayout(3,1))
		val botao = JButton("Inserir")
		
		botao.addActionListener {
			call(txCodigo.text.toInt())
			janela.dispose()
		}

		painel.run {
			add(lbCodigo)
			add(txCodigo)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,120)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}

	fun getGparams(call: (Int)->Unit) {
		val janela = JFrame("Inserir Parametros")
		val lbCodigo = JLabel("C�digo da Compra: ")
		val txCodigo = JTextField(5)
		val painel = JPanel(GridLayout(3,1))
		val botao = JButton("Inserir")
		
		botao.addActionListener {
			call(txCodigo.text.toInt())
			janela.dispose()
		}

		painel.run {
			add(lbCodigo)
			add(txCodigo)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,120)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}
	
	fun getIparams(call: (String)->Unit) {
		val janela = JFrame("Inserir Parametros")
		val lbVenc = JLabel("Data de Vencimento: ")
		val txVenc = JTextField(15)
		val painel = JPanel(GridLayout(3,1))
		val botao = JButton("Inserir")
		
		botao.addActionListener {
			call(txVenc.text)
			janela.dispose()
		}

		painel.run {
			add(lbVenc)
			add(txVenc)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,120)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}
	
	fun getWparams(call: (String, String)->Unit) {
		val janela = JFrame("Inserir Parametros")
		val lbData1 = JLabel("Compras Realizadas entre: ")
		val txData1 = JTextField(15)
		val lbData2 = JLabel("e: ")
		val txData2 = JTextField(15)
		val painel = JPanel(GridLayout(5,1))
		val botao = JButton("Inserir")
		
		botao.addActionListener {
			call(txData1.text, txData2.text)
			janela.dispose()
		}

		painel.run {
			add(lbData1)
			add(txData1)
			add(lbData2)
			add(txData2)
			add(botao)
		}

		janela.run {
			add(painel)
			setSize(300,200)
		}
		janela.isResizable = false
		janela.setLocationRelativeTo(null)
		janela.isVisible = true
	}
		

	
	fun criarImageIcon(caminho:String, descricao:String): ImageIcon? {
		val imgURL = javaClass.getResource(caminho)
		if (imgURL != null) {
			return ImageIcon(imgURL, descricao)
		} else {
			System.err.println("Não foi possível carregar o arquivo de imagem: $caminho")
			return null
		}
	}
	
}
