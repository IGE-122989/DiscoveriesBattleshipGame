/**
 *
 */
package iscteiul.ista.battleship;

/**
 * @author fba
 */
public interface IPosition {

    /**
     * Obtém o índice da linha da posição do tabuleiro.
     * @return O numero da linha.
     */
    int getRow();

    /**
     * Obtém o índice da coluna da posição do tabuleiro.
     * @return o número da coluna.
     */
    int getColumn();

    /**
     * Compara a posição cpm outro objeto para verficar a igualdade de coordenadas.
     * @param other O objeto a ser comparado com esta posição.
     * @return {@code true} se o objeto for uma IPosition com a mesma linha e coluna;
     * {@code false} caso contrário.
     */
    boolean equals(Object other);

    /**
     * Verifica se esta posição é adjacente à outra (vertical ou horizontal e não na diagonal).
     * @param other A outra posição a verificar.
     * @return {@code true} se as posições forem vizinhas diretas;
     * {@code false} se forem a mesma posição, se estiverem distantes ou se {@code other} for nulo.
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Altera o estado da posição, indicando que um navio foi colocado nela.
     */
    void occupy();

    /**
     * Registra um disparo nesta posição.
     * Altera o estado para "atingida" ou "hit", independentemente de estar ocupada ou não.
     */
    void shoot();

    /**
     * Verifica se existe um navio nesta posição.
     * @return {@code true} se a posição já estiver ocupada;
     * {@code false} caso contrário.
     */
    boolean isOccupied();

    /**
     * Indica se esta posição já foi alvo de um tiro.
     * @return {@code true} se o shoot() ja tiver sido invocado;
     * {@code false} caso contrário.
     */
    boolean isHit();
}
