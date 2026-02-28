# Battleship Conflito
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

## Regras do Jogo
O jogo Battleship consiste em dois jogadores que posicionam os seus navios num tabuleiro.
Cada jogador tenta afundar os navios do adversário escolhendo coordenadas.
Ganha quem afundar todos os navios primeiro.

# Ficha 1

## Parte 1

### D. 1.

Confirma-se que o branch seleccionado é o main por defeito.

### D. 2.

Verifica-se no gráfico que o ramo com atualização mais recente é o main.

### D. 3.

Grupo nickname = TP06-5.


7.. Após a submissão do pull request, outro membro da equipa (o revisor) analisa e aceita o pedido.
Quando o pull request é feito merge ao branch main, o grafo de commits em Insights -> Network mostra a junção (merge) entre o branch de trabalho e o branch principal.
Se o branch continha commits novos, essa junção aparece visivelmente no grafo como uma ramificação e posterior convergência.
No entanto, se o branch não tiver nenhum commit adicional em relação ao main, o pull request não cria realmente uma nova linha no grafo, o branch aparece como linear ou pode até nem aparecer destacado, porque na prática nunca divergiu do main.

### E. 3.

Como Product Owner, consideramos que o jogo precisa de alguns requisitos essenciais para ser funcional. Cada jogador deve conseguir preparar corretamente o seu tabuleiro, posicionando a frota e recebendo alertas quando algo estiver errado. Durante o jogo, é importante permitir os três tiros por turno, mostrar imediatamente os resultados e atualizar o tabuleiro conhecido do adversário. O sistema deve também detetar quando um jogador perde todos os navios e declarar o vencedor. Para facilitar testes e continuidade, o jogo deveria permitir guardar e carregar o estado em JSON. Por fim, é necessário garantir uma visualização clara dos tabuleiros e, numa fase opcional, incluir funcionalidades extra como jogar contra uma IA ou consultar o histórico das jogadas.

### E. 6.

Numa reunião diária do Scrum, quando escolhemos uma nova user story para trabalhar, aquilo que fazemos no GitHub é abrir o separador dos Issues e atribuir essa user story a nós próprios através do campo Assignees. Deixamos também um comentário curto a indicar que vamos iniciar o trabalho nessa tarefa, para que toda a equipa fique com essa informação registada.
Quando, mais tarde, concluirmos a implementação dessa user story, o passo seguinte é realizar um pull request que referencia o número do Issue e pedir a revisão de um colega. Depois de o pull request ser aprovado e integrado no main, voltamos ao Issue correspondente para o marcar como concluído, o que normalmente significa fechá-lo diretamente (ou ele é fechado automaticamente se o pull request incluir uma expressão como “Fixes #X”). Devemos ainda deixar um comentário final a indicar que o desenvolvimento está terminado e já se encontra integrado no projeto.
Por fim, quando o Product Owner decide desistir de uma determinada user story, o procedimento no GitHub passa por abrir o Issue, adicionar-lhe uma etiqueta que indique que não será implementado (por exemplo, “won’t do”, “canceled” ou equivalente) e escrever um comentário a explicar a decisão. Depois disso, o Issue é fechado, mantendo-se o registo histórico da decisão e garantindo que a equipa sabe que essa funcionalidade deixa de fazer parte do backlog.


## Parte 2

### C. 1.

A diferença principal é que pelo browser é mais simples e rápido para coisas pequenas como editar o README ou gerir Issues, mas não fornece qualquer ajuda a escrever código. Através IntelliJ temos muito mais ferramentas disponíveis: autocomplete, deteção de erros e conseguimos correr o código.No entanto, demora mais a configurar. 

### D. 1.

**Working Area:** Pasta no computador que contém todos os ficheiros e subdiretórios do projeto que
editamos, adicionamos ou removemos. É neste local que trabalhamos diretamente no código.

**Staging Area:** Quando fazemos alterações na Working Area, o Git regista essas mudanças no Index,
onde os ficheiros aparecem como "modificados". O Index guarda um snapshot dos
ficheiros modificados que serão incluídos no próximo commit.

**Local Repository:** Historial de commits guardado no nosso computador. Quando fazemos commit,
o snapshot do Index fica guardado permanentemente aqui.

**Remote Repository:** Repositório alojado no GitHub. Quando fazemos push, os commits do
repositório local são enviados para aqui, ficando disponíveis para todos os
membros da equipa.