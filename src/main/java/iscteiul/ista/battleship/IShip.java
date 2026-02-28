/**
 *
 */
package iscteiul.ista.battleship;

import java.util.List;

/**
 * Representa um navio no jogo.
 * Define o comportamento para a gestão da posição, orientação, integridade física e regras de colocação no tabuleiro.
 * * @author fba
 */
public interface IShip {

    /**
     *
     * @return A categoria do navio
     */
    String getCategory();

    /**
     *
     * @return o número de posições que o navio ocupa
     */
    Integer getSize();

    /**
     *
     * @return A lista com todas as instâncias ocupadas pelo navio.
     */
    List<IPosition> getPositions();

    /**
     *
     * @return A psoição de referência do navio.
     */
    IPosition getPosition();

    /**
     *
     * @return A orientação atual do navio no tabuleiro.
     */
    Compass getBearing();

    /**
     *Verifica se o navio está a flutuar.
     * @return {@code true} se pelo menos uma das suas posições não foi atingida;
     * {@code false} se todas as posições forem atingidas (navio afundado).
     */
    boolean stillFloating();

    /**
     *
     * @return o menor índice de linha ocupado pelo navio.
     */
    int getTopMostPos();

    /**
     *
     * @return o maior índice da linha ocupado pelo navio.
     */
    int getBottomMostPos();

    /**
     *
     * @return o menor índice de coluna ocupado pelo navio.
     */
    int getLeftMostPos();

    /**
     *
     * @return o maior índice ocupado pelo navio.
     */
    int getRightMostPos();

    /**
     * Verifica se o navio ocupa uma determina coordenada.
     * @param pos A psoição a verificar.
     * @return {@code true} se a posição faz parte do corpo do navio.
     */
    boolean occupies(IPosition pos);

    /**
     * Verifica se este navio está próximo do outro (navios não se podem tocar nem na diagonal).
     * @param other O outro navio a comparar.
     * @return {@code true} se a posição faz parte do corpo do navio.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Verifica se este navio está demasiado próximo de uma posição específica.
     * @param pos A posição a verificar.
     * @return {@code true} se a posição for adjacente ou coincidente com o navio.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Regista um tiro numa das posições do navio.
     * @param pos A posição que foi alvo do disparo.
     */
    void shoot(IPosition pos);
}
