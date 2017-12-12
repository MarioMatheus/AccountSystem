# Consultas

**Item A:** Selecionar todos os atributos da tabela de credores;
  ```
  SELECT * FROM credores;
  ```

**Item B:** Selecionar o código e o nome de todos os credores em ordem
alfabética;
  ```
  SELECT cod_credor, credor FROM credores 
  ORDER BY credor;
  ```

**Item C:** Mostrar todas as compras (credor, numero, data e valor) realizadas
ordanado por credor e data da compra;
  ```
  SELECT credor, cod_compra, data_compra, valor_compra FROM compras, credores
  WHERE credores.cod_credor = compras.cod_credor
  ORDER BY credor, data_compra;
  ```

**Item D:** Mostrar todas as compras (numero, data e valor) de um credor
específico cadastrado no banco de dados;
  ```
  SELECT cod_compra, data_compra, valor_compra FROM compras, credores
  WHERE credores.cod_credor = compras.cod_credor AND credores.cod_credor=?;
  ```

**Item E:** Listar todas as compras que tiveram um valor acima de R$ 5.000,00.
  ```
  SELECT * FROM compras
  WHERE valor_compra>5000;
  ```

**Item F:** Listar todas as parcelas (credor, descrição da compra, sequência da
parcela, valor da parcela, data do vencimento) que estão em aberto,
ou seja, ainda não foram pagas por ordem de data do vencimento
(uma parcela não paga é uma parcela que tem a data de pagamento
ou o valor pago em branco);
  ```
  SELECT credor, tipo, sequencia, valor_parcela, data_venc FROM credores, compras, parcelas
  WHERE credores.cod_credor=compras.cod_credor 
    AND compras.cod_compra = parcelas.cod_compra
    AND data_paga IS NULL;
  ```

**Item G:** Listar todos os dados de uma determinada compra (numero da
compra, data, descrição, credor, dados das parcelas) incluindo todas
as parcelas;
  ```
  SELECT cod_credor, data_compra, tipo, sequencia, valor_parcela, data_venc, data_paga, multa, juros FROM compras, parcelas
  WHERE compras.cod_compra = parcelas.cod_compra AND compras.cod_compra = ?;
  ```

**Item H:** Listar todos os pagamentos efetuados (data do pagamento, valor
pago, multa, juros, credor, numero da compra) por ordem de data do
pagamento e credor;
  ```
  SELECT data_paga, valor_parcela, multa, juros, credor, valor_compra
  FROM credores, compras, parcelas
  WHERE credores.cod_credor = compras.cod_credor
    AND compras.cod_compra = parcelas.cod_compra
  ORDER BY data_paga, credor;
  ```

**Item I:** Listar todas as contas (parcelas) que devem ser pagas em uma data
de vencimento específica;
  ```
  SELECT cod_compra, sequencia, valor_parcela FROM parcelas
  WHERE data_venc = '????-??-??';
  ```

**Item J:** Mostrar sem repetição todas as compras (credor, numero e data da
compra) que possuem pelo menos uma parcela em atraso. Uma
parcela em atraso é aquela cuja data é anterior a data atual e cujo
valor ou data do pagamento se encontram em branco;
  ```
  SELECT credor, compras.cod_compra, data_compra FROM credores, compras, parcelas
  WHERE credores.cod_credor = compras.cod_credor
    AND compras.cod_compra = parcelas.cod_compra
    AND data_venc < CURRENT_DATE()
    AND data_paga IS NULL
  GROUP BY compras.cod_compra;
  ```

**Item K:** Listar todas as parcelas que foram pagas com atraso (credor, data
vencimento, data pagamento, valor multa, valor juros, total pago). As
parcelas pagas com atraso foram as que a data do pagamento
registrada é posterior a data de vencimento (datpag &gt; datven);
  ```
  SELECT credor, data_venc, data_paga,valor_parcela,multa, juros, 
    valor_parcela + multa + juros as total_pago
  FROM credores, compras, parcelas
  WHERE credores.cod_credor = compras.cod_credor
    AND compras.cod_compra = parcelas.cod_compra
    AND data_paga > data_venc;
  ```

**Item L:** Mostrar um resumo de contas a pagar (parcelas) totalizando e
ordenando por data do vencimento (data, total a pagar na data).
  ```
  SELECT cod_compra, sequencia, data_venc, valor_parcela FROM parcelas
  WHERE data_paga IS NULL
  ORDER BY data_venc;
  ```

**Item M:** Mostrar um resumo do valor total de compras efetuadas por data da
compra (data, valor total das compras na data).
  ```
  SELECT data_compra, SUM(valor_compra) as valor_total FROM compras
  GROUP BY data_compra;
  ```

**Item N:** Mostrar um resumo de todos os credores com o valor total das
compras efetuadas com cada credor em ordem alfabetica de credor.
  ```
  SELECT credor, SUM(valor_compra) as valor_total FROM credores, compras
  WHERE credores.cod_credor = compras.cod_credor
  GROUP BY credor;
  ```

**Item O:** Mostrar um resumo do valor total pago de parcelas por data de
pagamento (data de pagamento e valor total pago) em um período
específico.
  ```
  SELECT data_paga, valor_parcela + juros + multa as valor_total FROM parcelas
  WHERE data_paga BETWEEN '2014-01-01' AND '2015-05-01'
  GROUP BY data_paga;
  ```

**Item P:** Mostrar o valor total da dívida com cada credor (valor total das
parcelas ainda não pagas por credor).
  ```
  SELECT credor, SUM(valor_parcela) as valor_divida FROM credores, compras, parcelas
  WHERE credores.cod_credor = compras.cod_credor
    AND compras.cod_compra = parcelas.cod_compra
    AND data_paga is NULL
  GROUP BY credor;
  ```

**Item Q:** Mostrar um resumo do número de compras realizadas por credor.
  ```
  SELECT credor, COUNT(cod_compra) FROM credores, compras
  WHERE credores.cod_credor = compras.cod_credor
  GROUP BY credor;
  ```

**Item R:** Mostrar um o valor médio de valor de compras por credor.
  ```
  SELECT credor, AVG(valor_compra) FROM credores, compras
  WHERE credores.cod_credor = compras.cod_credor
  GROUP BY credor;
  ```

**Item S:** Mostrar uma lista de todas as compras com o número de parcelas de
cada compra (numero, data e número de parcelas da compra).
  ```
  SELECT compras.cod_compra, data_compra, COUNT(sequencia) as num_parcelas
  FROM compras, parcelas
  WHERE compras.cod_compra = parcelas.cod_compra
  GROUP BY compras.cod_compra;
  ```

**Item T:** Mostrar o credor e o valor da maior compra já realizada com ele.
  ```
  SELECT credor, MAX(valor_compra) as maior_compra FROM credores, compras
  WHERE credores.cod_credor = compras.cod_credor
  GROUP BY credor;
  ```

**Item U:** Mostrar todas as compras que foram parceladas em mais de 3 (três).
  ```
  SELECT compras.*, COUNT(sequencia) as num_parcelas FROM compras, parcelas
  WHERE compras.cod_compra = parcelas.cod_compra
  GROUP BY cod_compra
  HAVING COUNT(sequencia) > 3;
  ```

**Item V:** Mostrar todos os credores que tiveram um total de compras maior do que R$ 5000,00.
  ```
  SELECT credor, SUM(valor_compra) as total_compras FROM credores, compras
  WHERE credores.cod_credor = compras.cod_credor
  GROUP BY compras.cod_credor
  HAVING SUM(valor_compra) > 5000;
  ```

**Item W:** Mostrar o valor médio das compras realizadas por credores em um determinado período.
  ```
  SELECT credor, AVG(valor_compra) as valor_medio FROM credores, compras
  WHERE credores.cod_credor = compras.cod_credor
    AND data_compra BETWEEN '2014-10-02' AND '2016-03-06'
  GROUP BY compras.cod_credor;
  ```
