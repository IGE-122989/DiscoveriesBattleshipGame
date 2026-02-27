# Battleship
## Grupo: TP06-5
### Curso
Informática e Gestão de Empresas
## Membros
| Número  | Nome              |
|---------|-------------------|
| 105498  | Luís Manteigas    |
| 112244  | Fábio Reis        |
| 122989  | Carolina Lisboa   |
| 123022  | Rita Peixoto      |

O repositório foi criado pelo 2º aluno com o número mais baixo (122989) para que pudessemos dar seguimento ao trabalho em regime diurno, uma vez que o aluno com o número mais baixo só pode frequentar as aulas de PL.
UPDATE: Foi adicionado um novo membro ao grupo com número ainda mais baixo, no entanto o repositório mantém-se em IGE-122989.

## Tipos de Navios
| Batalha Naval        | Descobrimentos | English  | Dimensão | #Navios |
|----------------------|--------------- |----------|----------|---------|
| Porta-aviões         | Galeão         | Galleon  | 5        | 1       |
| Navio de 4 canhões   | Fragata        | Frigate  | 4        | 1       |
| Navio de 3 canhões   | Nau            | Carrack  | 3        | 2       |
| Navio de 2 canhões   | Caravela       | Caravel  | 2        | 3       |
| Submarino            | Barca          | Barge    | 1        | 4       |

Basic academic version of Battleship game to build upon.

**Ficha 1:**

D)

1. Confirma-se que o branch seleccionado é o main por defeito.
2. Verifica-se no gráfico que o ramo com atualização mais recente é o main.
3. Grupo nickname = TP06-5.
7. Após a submissão do pull request, outro membro da equipa (o revisor) analisa e aceita o pedido.
Quando o pull request é feito merge ao branch main, o grafo de commits em Insights -> Network mostra a junção (merge) entre o branch de trabalho e o branch principal.
Se o branch continha commits novos, essa junção aparece visivelmente no grafo como uma ramificação e posterior convergência.
No entanto, se o branch não tiver nenhum commit adicional em relação ao main, o pull request não cria realmente uma nova linha no grafo, o branch aparece como linear ou pode até nem aparecer destacado, porque na prática nunca divergiu do main.

E)

3. Como Product Owner, consideramos que o jogo precisa de alguns requisitos essenciais para ser funcional. Cada jogador deve conseguir preparar corretamente o seu tabuleiro, posicionando a frota e recebendo alertas quando algo estiver errado. Durante o jogo, é importante permitir os três tiros por turno, mostrar imediatamente os resultados e atualizar o tabuleiro conhecido do adversário. O sistema deve também detetar quando um jogador perde todos os navios e declarar o vencedor. Para facilitar testes e continuidade, o jogo deveria permitir guardar e carregar o estado em JSON. Por fim, é necessário garantir uma visualização clara dos tabuleiros e, numa fase opcional, incluir funcionalidades extra como jogar contra uma IA ou consultar o histórico das jogadas.
