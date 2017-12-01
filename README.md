# AccountSystem
Sistema de contas a pagar com MySQL. Estudando Kotlin :)

## Info
O programa conterá um CRUD do banco de dados abaixo e algumas consultas na forma de formulário.

## Banco de Dados
### Credores
  | Coluna      | Tipo do dado        |
  | ----------- | ------------------- |
  | cod_credor  | INTEGER PRIMARY KEY |
  | credor      | VARCHAR(45)         |
  
### Compras
  | Coluna        | Tipo do dado                                        |
  | ------------- | --------------------------------------------------- |
  | cod_compra    | INTEGER PRIMARY KEY                                 |
  | data_compra   | DATE                                                |
  | valor_compra  | INTEGER                                             |
  | cod_credor    | INTEGER FOREIGN KEY REFERENCES Credores(cod_credor) |
  | tipo          | INTEGER /*1 - A Vista : 2 - A Prazo*/               |

### Parcelas
  | Coluna        | Tipo do dado        |
  | ------------- | ------------------- |
  | cod_parcela   | INTEGER PRIMARY KEY |
  | sequencia     | INTEGER PRIMARY KEY |
  | valor_parcela | INTEGER             |
  | data_venc     | DATE                |
  | data_paga     | DATE                |
  | multa         | INTEGER             |
  | juros         | INTEGER             |

## License
O projeto é licenciado pela MIT License - veja a [LICENSE.md](LICENSE) para mais detalhes
