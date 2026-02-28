/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;


/**
 * Representa uma frota de navios no jogo Battleship.
 *
 * <p>Esta interface define as operações fundamentais associadas à gestão de uma frota,
 * incluindo a obtenção dos navios que a compõem, a adição de novos navios, a
 * identificação de navios flutuantes (ainda não afundados), a pesquisa de navios
 * por categoria e a deteção do navio presente numa posição específica do tabuleiro.
 *
 * <h2>Responsabilidades</h2>
 * Uma implementação de {@code IFleet} deve:
 * <ul>
 *     <li>Garantir a integridade da frota, respeitando as regras do jogo
 *         relativas ao número máximo de navios e ao seu posicionamento.</li>
 *     <li>Assegurar que operações como {@link #addShip(IShip)} realizam
 *         validações adequadas (sobreposição, limites do tabuleiro, duplicações, etc.).</li>
 *     <li>Facultar mecanismos eficientes para pesquisas e consultas de estado
 *         durante o jogo.</li>
 *     <li>Fornecer informação atualizada sobre navios afundados e flutuantes.</li>
 * </ul>
 *
 * <h2>Constantes</h2>
 * <ul>
 *     <li>{@link #BOARD_SIZE} — Tamanho lateral da grelha (10×10), conforme
 *         especificação tradicional do jogo.</li>
 *     <li>{@link #FLEET_SIZE} — Número total de unidades da frota (10 navios).</li>
 * </ul>
 *
 * <h2>Notas de Implementação</h2>
 * <p>As classes que implementam esta interface devem garantir que os métodos
 * retornam sempre coleções consistentes com o estado atual da frota, evitando
 * devolver valores nulos e preferindo listas vazias quando apropriado.</p>
 *
 * @see IShip
 * @see IPosition
 * @since 1.0
 */
public interface IFleet {


    /**
     * Tamanho do tabuleiro (10x10).
     * <p>Esta constante é utilizada para validar posições de navios e
     * tiros dentro da grelha do jogo.</p>
     */
    Integer BOARD_SIZE = 10;

    /**
     * Número total de navios que constituem uma frota válida.
     * <p>Implementações devem assegurar que a frota não ultrapassa este limite.</p>
     */
    Integer FLEET_SIZE = 10;


    /**
     * Obtém a lista completa de navios pertencentes à frota.
     *
     * <p>Esta lista pode incluir navios intactos, parcialmente danificados
     * ou totalmente afundados. A ordem dos navios não é obrigatoriamente garantida.</p>
     *
     * @return uma lista contendo todos os navios da frota
     */
    List<IShip> getShips();


    /**
     * Tenta adicionar um novo navio à frota.
     *
     * <p>A implementação deve garantir que:</p>
     * <ul>
     *     <li>O número máximo {@link #FLEET_SIZE} não é excedido;</li>
     *     <li>O navio não se sobrepõe a navios já existentes;</li>
     *     <li>O navio se encontra totalmente dentro do tabuleiro
     *         ({@link #BOARD_SIZE});</li>
     *     <li>Navios não são duplicados sem justificação.</li>
     * </ul>
     *
     * @param s o navio a adicionar
     * @return {@code true} se o navio foi adicionado com sucesso;
     *         {@code false} caso contrário
     */
    boolean addShip(IShip s);


    /**
     * Obtém todos os navios cuja categoria corresponde ao parâmetro fornecido.
     *
     * <p>A categoria pode representar o tipo de navio (e.g., "Caravela",
     * "Nau", "Galeão", etc.). A implementação pode definir o critério de igualdade
     * (match exato, ignore-case, etc.).</p>
     *
     * @param category a categoria ou tipo do navio
     * @return uma lista de navios que correspondem à categoria,
     *         ou uma lista vazia se nenhum for encontrado
     */
    List<IShip> getShipsLike(String category);


    /**
     * Obtém a lista de navios ainda flutuantes.
     *
     * <p>Um navio é considerado flutuante quando pelo menos uma das suas
     * células permanece intacta. Este método é fundamental para determinar
     * o estado do jogo e identificar quando um jogador foi derrotado.</p>
     *
     * @return lista de navios não afundados
     */
    List<IShip> getFloatingShips();


    /**
     * Retorna o navio presente numa determinada posição do tabuleiro.
     *
     * <p>Se a posição pertence ao corpo de um navio, o navio correspondente
     * deve ser devolvido. Caso contrário, o método devolve {@code null}.</p>
     *
     * @param pos a posição a verificar
     * @return o navio que ocupa a posição, ou {@code null} se estiver vazia
     */
    IShip shipAt(IPosition pos);


    /**
     * Imprime o estado atual da frota no sistema de saída padrão.
     *
     * <p>Este método deve fornecer uma visão legível dos navios,
     * distinguindo os que estão intactos, danificados ou afundados.
     * A formatação específica é da responsabilidade da implementação.</p>
     *
     * <h4>Exemplo de saída esperada:</h4>
     * <pre>
     * Galeão     — afundado
     * Nau (1)    — flutuante, 2 células intactas
     * Caravela   — flutuante, intacta
     * </pre>
     */
    void printStatus();
}
