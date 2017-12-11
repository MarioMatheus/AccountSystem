package com.accountsystem

enum class Consultas(var consulta: String) {
		A("""Selecionar todos os atributos da tabela de credores.

"""),
	
        B("""Selecionar o código e o nome de todos os credores em ordem alfabética.

"""),
	
        C("""Mostrar todas as compras (credor, numero, data e valor) realizadas
ordenado por credor e data da compra.

"""),
        
        D("""Mostrar todas as compras (numero, data e valor) de um credor específico
cadastrado no banco de dados.

"""),
	
        E("""Listar todas as compras que tiveram um valor acima de R$ 5.000,00.

"""),
	
        F("""Listar todas as parcelas (credor, descrição da compra, sequência da
parcela, valor da parcela, data do vencimento) que estão em aberto,
ou seja, ainda não foram pagas por ordem de data do vencimento
(uma parcela não paga é uma parcela que tem a data de pagamento
ou o valor pago em branco).

"""),
	
        G("""Listar todos os dados de uma determinada compra (numero da
compra, data, descrição, credor, dados das parcelas) incluindo todas
as parcelas.

"""),
	
        H("""Listar todos os pagamentos efetuados (data do pagamento, valor
pago, multa, juros, credor, numero da compra) por ordem de data do
pagamento e credor.

"""),
	
        I("""Listar todas as contas (parcelas) que devem ser pagas em uma data
de vencimento específica.

"""),
	
        J("""Mostrar sem repetição todas as compras (credor, numero e data da
compra) que possuem pelo menos uma parcela em atraso. Uma
parcela em atraso é aquela cuja data é anterior a data atual e cujo
valor ou data do pagamento se encontram em branco.

"""),
	
        K("""Listar todas as parcelas que foram pagas com atraso (credor, data
vencimento, data pagamento, valor multa, valor juros, total pago). As
parcelas pagas com atraso foram as que a data do pagamento
registrada é posterior a data de vencimento (datpag > datven).

"""),
	
        L("""Mostrar um resumo de contas a pagar (parcelas) totalizando e
ordenando por data do vencimento (data, total a pagar na data).

"""),
	
        M("""Mostrar um resumo do valor total de compras efetuadas por data da
compra (data, valor total das compras na data).

"""),
	
        N("""Mostrar um resumo de todos os credores com o valor total das
compras efetuadas com cada credor em ordem alfabetica de credor.

"""),
	
        O("""Mostrar um resumo do valor total pago de parcelas por data de
pagamento (data de pagamento e valor total pago) em um período
específico.

"""),
	
        P("""Mostrar o valor total da dívida com cada credor (valor total das
parcelas ainda não pagas por credor).

"""),
	
        Q("""Mostrar um resumo do número de compras realizadas por credor.

"""),
	
        R("""Mostrar um o valor médio de valor de compras por credor.

"""),
	
        S("""Mostrar uma lista de todas as compras com o número de parcelas de
cada compra (numero, data e número de parcelas da compra).

"""),
	
        T("""Mostrar o credor e o valor da maior compra já realizada com ele.

"""),
	
        U("""Mostrar todas as compras que foram parceladas em mais de 3 (três).

"""),
	
        V("""Mostrar todos os credores que tiveram um total de compras maior do que R$ 5000,00.

"""),
	
        W("""Mostrar o valor médio das compras realizadas por credores em um determinado período.

""")
}
