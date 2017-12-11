package com.accountsystem

enum class Item(val query: String) {
        A("""SELECT * FROM credores;"""),
	
        B("""SELECT cod_credor, credor FROM credores 
        		ORDER BY credor;"""),
	
        C("""SELECT credor, cod_compra, data_compra, valor_compra FROM compras, credores
        		WHERE credores.cod_credor = compras.cod_credor
        		ORDER BY credor, data_compra;"""),
        
        D("""SELECT cod_compra, data_compra, valor_compra FROM compras, credores
        		WHERE credores.cod_credor = compras.cod_credor
        			AND credores.cod_credor=?;"""),
	
        E("""SELECT * FROM compras
        		WHERE valor_compra>5000;"""),
	
        F("""SELECT credor, tipo, sequencia, valor_parcela, data_venc FROM credores, compras, parcelas
        		WHERE credores.cod_credor=compras.cod_credor 
        			AND compras.cod_compra = parcelas.cod_compra
        			AND data_paga = '2099-12-31';"""),
	
        G("""SELECT cod_credor, data_compra, tipo, sequencia, valor_parcela, data_venc, data_paga, multa, juros FROM compras, parcelas
        		WHERE compras.cod_compra = parcelas.cod_compra
        			AND compras.cod_compra = ?;"""),
	
        H("""SELECT data_paga, valor_parcela, multa, juros, credor, valor_compra
				FROM credores, compras, parcelas
				WHERE credores.cod_credor = compras.cod_credor
				  AND compras.cod_compra = parcelas.cod_compra
				ORDER BY data_paga, credor;"""),
	
        I("""SELECT cod_compra, sequencia, valor_parcela FROM parcelas
        		WHERE data_venc = ?;"""),
	
        J("""SELECT credor, compras.cod_compra, data_compra FROM credores, compras, parcelas
				WHERE credores.cod_credor = compras.cod_credor
				  AND compras.cod_compra = parcelas.cod_compra
				  AND data_venc < CURRENT_DATE()
				  AND data_paga = '2099-12-31'
				GROUP BY compras.cod_compra;"""),
	
        K("""SELECT credor, data_venc, data_paga,valor_parcela,multa, juros,valor_parcela + multa + juros as total_pago
				FROM credores, compras, parcelas
				WHERE credores.cod_credor = compras.cod_credor
				  AND compras.cod_compra = parcelas.cod_compra
				  AND data_paga > data_venc;"""),
	
        L("""SELECT cod_compra, sequencia, data_venc, valor_parcela FROM parcelas
				WHERE data_paga IS NULL
				ORDER BY data_venc;"""),
	
        M("""SELECT data_compra, SUM(valor_compra) as valor_total FROM compras
    			GROUP BY data_compra;"""),
	
        N("""SELECT credor, SUM(valor_compra) as valor_total FROM credores, compras
				WHERE credores.cod_credor = compras.cod_credor
				GROUP BY credor;"""),
	
        O("""SELECT data_paga, valor_parcela + juros + multa as valor_total FROM parcelas
				WHERE data_paga BETWEEN '2014-01-01' AND '2015-05-01'
				GROUP BY data_paga;"""),
	
        P("""SELECT credor, SUM(valor_parcela) as valor_divida FROM credores, compras, parcelas
				WHERE credores.cod_credor = compras.cod_credor
				  AND compras.cod_compra = parcelas.cod_compra
				  AND data_paga is NULL
				GROUP BY credor;"""),
	
        Q("""SELECT credor, COUNT(cod_compra) FROM credores, compras
				WHERE credores.cod_credor = compras.cod_credor
				GROUP BY credor;"""),
	
        R("""SELECT credor, AVG(valor_compra) FROM credores, compras
				WHERE credores.cod_credor = compras.cod_credor
				GROUP BY credor;"""),
	
        S("""SELECT compras.cod_compra, data_compra, COUNT(sequencia) as num_parcelas
				FROM compras, parcelas
				WHERE compras.cod_compra = parcelas.cod_compra
				GROUP BY compras.cod_compra;"""),
	
        T("""SELECT credor, MAX(valor_compra) as maior_compra FROM credores, compras
				WHERE credores.cod_credor = compras.cod_credor
				GROUP BY credor;"""),
	
        U("""SELECT compras.*, COUNT(sequencia) as num_parcelas FROM compras, parcelas
				WHERE compras.cod_compra = parcelas.cod_compra
				GROUP BY cod_compra
				HAVING COUNT(sequencia) > 3;"""),
	
        V("""SELECT credor, SUM(valor_compra) as total_compras FROM credores, compras
				WHERE credores.cod_credor = compras.cod_credor
				GROUP BY compras.cod_credor
				HAVING SUM(valor_compra) > 5000;"""),
	
        W("""SELECT credor, AVG(valor_compra) as valor_medio FROM credores, compras
				WHERE credores.cod_credor = compras.cod_credor
				  AND data_compra BETWEEN ? AND ?
				GROUP BY compras.cod_credor;""")
	
}