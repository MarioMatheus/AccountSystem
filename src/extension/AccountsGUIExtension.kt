package extension

import com.accountsystem.Consultas
import com.accountsystem.Item
import ui.AccountsGUI
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

fun AccountsGUI.exibirConsultas() {
    tfOut.text = ""
    Consultas.values().forEach {
        tfOut.append("$it) ${it.consulta}")
    }
    tfOut.append("\n")
}

fun AccountsGUI.exibirCredores() {
    tfOut.append("\n")
    accountCtrl.consultCredores(30).forEach {
        tfOut.append("$it \n")
    }
    tfOut.append("\n")
}

fun AccountsGUI.exibirCompras() {
    tfOut.append("\n")
    accountCtrl.consultCompras(30).forEach {
        tfOut.append("$it \n")
    }
    tfOut.append("\n")
}

fun AccountsGUI.exibirParcelas() {
    tfOut.append("\n")
    accountCtrl.consultParcelas(30).forEach {
        tfOut.append("$it \n")
    }
    tfOut.append("\n")
}

fun AccountsGUI.selectConsulta(item: Item): String {
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
    }
}

fun AccountsGUI.setupListenersMethods() {
    addWindowListener(object: WindowAdapter() {
        override fun windowClosing(e: WindowEvent) {
            accountCtrl.closeAccountDatabase()
            System.exit(0)
        }
    })
    btLimpar.addActionListener {
        tfOut.text = ""
    }
    btListarCredores.addActionListener {
        exibirCredores()
    }
    btListarCompras.addActionListener {
        exibirCompras()
    }
    btListarParcelas.addActionListener {
        exibirParcelas()
    }
    btAddCredor.addActionListener {
        abrirJanelaAdicaoCredor()
    }
    btAddCompra.addActionListener {
        abrirJanelaAdicaoCompra()
    }
    btAddParcela.addActionListener {
        abrirJanelaAdicaoParcela()
    }
    btFiltro.addActionListener {
        abrirJanelaFiltro()
    }
    btInfo.addActionListener {
        exibirConsultas()
    }
}

fun AccountsGUI.start() {
    setupMenu()
    jMenuBar = menuBar
    minimumSize = Dimension(1100, 620)
    setupMainPanel()
    setupListenersMethods()
    setLocationRelativeTo(null)
    isVisible = true
    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}

fun AccountsGUI.setupMenu() {
    val arquivo = JMenu("Arquivo")
    val buscas = JMenu("Buscas")
    val ajuda = JMenu("Ajuda")

    val abrir = JMenuItem("Abrir")
    val salvar = JMenuItem("Salvar")
    val sair = JMenuItem("Sair")

    arquivo.run {
        add(abrir)
        add(salvar)
        add(sair)
    }

    val inst = JMenuItem("Instrucoes")
    val sobre = JMenuItem("Sobre")
    ajuda.add(inst)
    ajuda.add(sobre)

    menuBar.run {
        add(arquivo)
        add(buscas)
        add(ajuda)
    }
}

fun AccountsGUI.setupMainPanel() {
    setupTopPanel()
    setupLeftPanel()
    setupMiddlePanel()
    mainPanel.run {
        add(northPanel, BorderLayout.NORTH)
        add(eastPanel, BorderLayout.EAST)
        add(middlePanel, BorderLayout.CENTER)
    }
    add(mainPanel)
}

fun AccountsGUI.setupLeftPanel() {
    eastPanel.border = BorderFactory.createEmptyBorder(25,10,25,10)
    eastPanel.run {
        add(btAddCredor)
        add(btAddCompra)
        add(btAddParcela)
        add(btFiltro)
        add(btInfo)
    }
}

fun AccountsGUI.setupTopPanel() {
    tools.run {
        add(JTextField())
        add(btBusca)
        add(btLimpar)
        add(JPanel())
        add(btListarCredores)
        add(btListarCompras)
        add(btListarParcelas)
    }

    this.northPanel.add(tools)
}

fun AccountsGUI.setupMiddlePanel() {
    middlePanel.border = BorderFactory.createEmptyBorder(25,10,25,10)
    middlePanel.add(scroll)
}