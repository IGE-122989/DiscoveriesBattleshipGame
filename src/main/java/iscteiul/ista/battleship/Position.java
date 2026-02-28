/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição (coordenada) na grelha do jogo Battleship.
 *
 * <p>Uma {@code Position} é definida por dois inteiros não negativos
 * ({@code row}, {@code column}) e estado associado à célula:
 * se está ocupada por um navio ({@code isOccupied}) e se já foi atingida
 * por um tiro ({@code isHit}).</p>
 *
 * <h2>Finalidade</h2>
 * <ul>
 *   <li>Centralizar a semântica de uma célula de grelha;</li>
 *   <li>Fornecer operações utilitárias como {@link #isAdjacentTo(IPosition)};</li>
 *   <li>Permitir a marcação de eventos de jogo via {@link #occupy()} e {@link #shoot()}.</li>
 * </ul>
 *
 * <h2>Imutabilidade e Thread-safety</h2>
 * Esta classe é <b>mutável</b> (os campos {@code isOccupied} e {@code isHit} podem mudar).
 * Instâncias não são thread-safe; se usadas em concorrência, sincronize o acesso externo.
 *
 * <h2>Contrato de igualdade</h2>
 * <p>Esta implementação define igualdade exclusivamente por coordenadas
 * ({@link #getRow()}, {@link #getColumn()}). O estado transitório
 * ({@code isOccupied}, {@code isHit}) <b>não</b> participa em {@link #equals(Object)}.
 * O {@link #hashCode()} é consistente com esse contrato.</p>
 *
 * <h2>Exemplos de utilização</h2>
 * <pre>{@code
 * IPosition p = new Position(3, 7);
 * System.out.println(p.getRow());    // 3
 * System.out.println(p.getColumn()); // 7
 *
 * // Marcar ocupação por um navio e um tiro:
 * p.occupy();
 * p.shoot();
 *
 * // Adjacência (inclui diagonais):
 * IPosition q = new Position(4, 8);
 * boolean adj = p.isAdjacentTo(q); // true (diferença 1 linha, 1 coluna)
 * }</pre>
 *
 * @see IPosition
 * @since 1.0
 */
public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;


/**
     * Cria uma posição com as coordenadas fornecidas.
     *
     * <p>Os indicadores {@code isOccupied} e {@code isHit} iniciam a {@code false}.</p>
     *
     * <p><b>Validação:</b> esta classe não impõe limites de grelha; cabe à
     * camada de jogo validar limites (ex.: 0–9 numa grelha 10×10).</p>
     *
     * @param row    índice da linha (0-based, salvo convenção distinta no projeto)
     * @param column índice da coluna (0-based)
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }


/**
     * Devolve o índice da linha desta posição.
     *
     * @return linha (0-based)
     */
    @Override
    public int getRow() {
        return row;
    }

    
/**
     * Devolve o índice da coluna desta posição.
     *
     * @return coluna (0-based)
     */
    @Override
    public int getColumn() {
        return column;
    }


/**
     * Calcula o código de dispersão com base apenas nas coordenadas
     * (linha e coluna), em conformidade com {@link #equals(Object)}.
     *
     * @return hash consistente com igualdade por coordenadas
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }


/**
     * Compara esta posição a outro objeto para igualdade por <b>coordenadas</b>.
     *
     * <p>São consideradas iguais duas posições que partilham os mesmos
     * valores de {@link #getRow()} e {@link #getColumn()}, independentemente
     * do estado {@code isOccupied} ou {@code isHit}.</p>
     *
     * @param otherPosition objeto a comparar
     * @return {@code true} se as coordenadas coincidirem; {@code false} caso contrário
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }


/**
     * Indica se a posição fornecida é adjacente a esta,
     * considerando vizinhança de Moore (8 vizinhos + própria célula).
     *
     * <p>Ou seja, devolve {@code true} quando a diferença absoluta entre linhas
     * e colunas é no máximo 1 em ambas as dimensões. Isto inclui diagonais e
     * a própria posição (diferença 0,0).</p>
     *
     * @param other posição a testar
     * @return {@code true} se for adjacente (ou a mesma célula); {@code false} caso contrário
     * @throws NullPointerException se {@code other} for {@code null}
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    
/**
     * Marca esta posição como ocupada por um navio.
     *
     * <p>Implementações superiores (jogo/frota) devem garantir que apenas posições
     * válidas e não sobrepostas são ocupadas.</p>
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }


/**
     * Regista que esta posição foi alvo de um tiro (atingida).
     *
     * <p>Este método não verifica se havia navio presente; cabe à lógica de jogo
     * atualizar estatísticas (acerto/água) conforme o contexto.</p>
     */
    @Override
    public void shoot() {
        isHit = true;
    }

   

/**
     * Indica se esta posição já foi atingida por um tiro.
     *
     * @return {@code true} se já foi marcada como atingida; {@code false} caso contrário
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    

    /**
     * Indica se esta posição está ocupada por um navio.
     *
     * @return {@code true} se ocupada; {@code false} caso contrário
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    
/**
     * Representação textual simples com linha e coluna.
     *
     * @return string do tipo {@code "Linha = X Coluna = Y"}
     */

    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
