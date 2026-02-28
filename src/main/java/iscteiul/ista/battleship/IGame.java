/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;


/**
 * Define o contrato de alto nível para a gestão de uma partida de Battleship.
 *
 * <p>Uma implementação de {@code IGame} é responsável por:</p>
 * <ul>
 *   <li>Receber tiros, validar coordenadas, aplicar regras e atualizar estado;</li>
 *   <li>Manter contadores úteis (acertos, tiros repetidos, inválidos, navios afundados);</li>
 *   <li>Expor relatórios simples (listagem de tiros válidos, impressão da frota).</li>
 * </ul>
 *
 * <h2>Modelo de Interação</h2>
 * <p>O ciclo típico envolve:</p>
 * <ol>
 *   <li>O cliente invoca {@link #fire(IPosition)} para efetuar um tiro;</li>
 *   <li>A implementação valida a posição, regista a jogada (quando aplicável) e devolve o navio atingido,
 *       ou {@code null} para água;</li>
 *   <li>Os contadores acessíveis por getters são atualizados conforme o resultado;</li>
 *   <li>O cliente pode consultar o histórico por {@link #getShots()} e imprimir resumos via
 *       {@link #printValidShots()} e {@link #printFleet()}.</li>
 * </ol>
 *
 * <h2>Definições importantes</h2>
 * <ul>
 *   <li><b>Tiro válido</b>: posição dentro do tabuleiro, ainda não utilizada.</li>
 *   <li><b>Tiro repetido</b>: posição já alvo de um tiro anterior (acerto ou água).</li>
 *   <li><b>Tiro inválido</b>: posição fora dos limites do tabuleiro (ou nula, conforme política).</li>
 *   <li><b>Acerto (hit)</b>: tiro válido que atinge pelo menos uma célula de um navio.</li>
 *   <li><b>Navio afundado</b>: todas as células desse navio foram atingidas.</li>
 * </ul>
 *
 * <h2>Thread-safety</h2>
 * <p>A interface não impõe requisitos de sincronização. Implementações <em>podem</em> ou não ser thread-safe.
 * Caso pretendas uso concorrente, documenta e sincroniza as operações críticas
 * (especialmente {@link #fire(IPosition)}).</p>
 *
 * <h2>Exemplo de utilização</h2>
 * <pre><code>
 * IGame game = ...; // implementação concreta
 *
 * IPosition p = new Position(3, 7);
 * IShip hit = game.fire(p);
 *
 * if (hit != null) {
 *     System.out.println("Acertou em: " + hit.getName());
 * } else {
 *     System.out.println("Água!");
 * }
 *
 * System.out.printf("Hits: %d, Repetidos: %d, Inválidos: %d%n",
 *         game.getHits(), game.getRepeatedShots(), game.getInvalidShots());
 *
 * game.printValidShots();
 * game.printFleet();
 * </code></pre>
 *
 * @see IPosition
 * @see IShip
 * @since 1.0
 */
public interface IGame {

    /**
     * Efetua um tiro na posição indicada e atualiza o estado da partida.
     *
     * <p><b>Comportamento esperado:</b>
     * <ul>
     *   <li>Se a posição for <i>inválida</i> (fora da grelha ou de acordo com a política da implementação,
     *       {@code null}), incrementa {@link #getInvalidShots()} e não altera o histórico de tiros válidos;</li>
     *   <li>Se a posição já tiver sido utilizada, incrementa {@link #getRepeatedShots()} e não altera contadores de acerto;</li>
     *   <li>Se a posição for válida e nova:
     *       <ul>
     *         <li>acrescenta a posição a {@link #getShots()};</li>
     *         <li>se atingir um navio, incrementa {@link #getHits()} e, se o navio ficar sem células intactas,
     *             incrementa {@link #getSunkShips()};</li>
     *         <li>se for água, não incrementa {@code getHits()}.</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <p><b>Contrato de retorno:</b>
     * <ul>
     *   <li>Retorna o {@link IShip} atingido quando o tiro é um acerto;</li>
     *   <li>Retorna {@code null} em caso de água, tiro repetido ou tiro inválido (a menos que a implementação
     *       opte por lançar exceção em entradas inválidas; ver nota abaixo).</li>
     * </ul>
     *
     * <p><b>Nota de implementação:</b> A interface permite duas políticas para entradas inválidas:
     * (1) retornar {@code null} e contar em {@link #getInvalidShots()}, ou
     * (2) lançar uma {@link IllegalArgumentException}. Qualquer que seja a decisão, documenta-a na classe concreta
     * e mantém comportamento consistente.</p>
     *
     * @param pos posição-alvo do tiro
     * @return o navio atingido ou {@code null} caso contrário
     */
    IShip fire(IPosition pos);


    /**
     * Devolve a lista (histórico) de posições alvo de <b>tiros válidos e inéditos</b>
     * efetuados nesta partida.
     *
     * <p><b>Inclui:</b> apenas posições dentro do tabuleiro e não repetidas à data do tiro.</p>
     * <p><b>Não inclui:</b> tentativas inválidas (fora da grelha) nem tiros repetidos.</p>
     *
     * <p>A ordem recomendada é cronológica de execução. Implementações devem especificar
     * se a lista é mutável ou uma cópia defensiva.</p>
     *
     * @return lista de posições alvo de tiros válidos
     */
    List<IPosition> getShots();


    /**
     * Número total de tentativas de tiro em posições já anteriormente utilizadas.
     *
     * <p>Este contador <b>não</b> incrementa em tiros válidos inéditos nem em tiros inválidos
     * (fora da grelha), salvo decisão documentada pela implementação.</p>
     *
     * @return quantidade de tiros repetidos
     */
    int getRepeatedShots();

    /**     * Número total de tentativas de tiro em posições inválidas (fora da grelha ou entradas nulas,     * consoante a política definida).     *     * @return quantidade de tiros inválidos     */
    int getInvalidShots();

    /**     * Número total de acertos (tiros válidos que atingiram pelo menos uma célula de navio).     *     * <p>Este valor deve ser coerente com o somatório de células atingidas de todos os navios.</p>     *     * @return quantidade de acertos registados     */
    int getHits();


    /**
     * Número total de navios completamente afundados na partida corrente.
     *
     * <p>Deve aumentar no momento em que a última célula intacta de um navio é atingida por {@link #fire(IPosition)}.</p>
     *
     * @return quantidade de navios afundados
     */
    int getSunkShips();


    /**
     * Número de navios ainda flutuantes (não afundados) no oponente (ou na frota gerida por esta instância,
     * conforme o design do jogo).
     *
     * <p><b>Consistência:</b> idealmente, {@code getRemainingShips() + getSunkShips() == frotaInicial}.</p>
     *
     * @return quantidade de navios que ainda não foram afundados
     */
    int getRemainingShips();


    /**
     * Imprime na saída padrão uma representação legível de todos os tiros válidos
     * (por exemplo, coordenadas e resultado “hit/água”).
     *
     * <p>A formatação é responsabilidade da implementação; deve ser estável e útil para depuração
     * e/ou logs de jogo.</p>
     */
    void printValidShots();


    /**
     * Imprime na saída padrão o estado atual da(s) frota(s) relevante(s) para esta instância de jogo,
     * incluindo, quando possível, distinção entre navios intactos, danificados e afundados.
     *
     * <p>Útil para diagnóstico rápido e para relatórios simples no fim da partida.</p>
     */
    void printFleet();
}
