/**
 *
 */
package iscteiul.ista.battleship;

/** Enumeração que representa as direções possíveis (bearings) no jogo de Batalha Naval.
 *
 *  <p>Cada direção está associada a um carácter:
 *   <ul>
 *    <li><b>'n'</b> — {@link #NORTH} (Norte)</li>
 *    <li><b>'s'</b> — {@link #SOUTH} (Sul)</li>
 *    <li><b>'e'</b> — {@link #EAST} (Este)</li>
 *    <li><b>'o'</b> — {@link #WEST} (Oeste) <i>(atenção: usa-se 'o' de <b>O</b>este em PT, e não 'w')</i></li>
 *    <li><b>'u'</b> — {@link #UNKNOWN} (desconhecida/indefinida)</li>
 *   </ul>
 *
 *   <h2>Objetivo</h2>
 *   Esta enum serve para padronizar a orientação no posicionamento de navios e no cálculo * de deslocações sobre a grelha. Ao encapsular as direções num tipo seguro, evita-se * o uso de literais soltos e erros de digitação. * * <h2>Imutabilidade e Thread-safety</h2> * Os valores de um {@code enum} em Java são <b>imutáveis</b> e <b>thread-safe</b> por definição. * Pode-se partilhar livremente instâncias deste {@code enum} entre threads. * * <h2>Exemplos de utilização</h2> * <pre>{@code * // Obter o carácter associado a uma direção: * char c = Compass.NORTH.getDirection(); // 'n' * * // Converter de carácter para enum: * Compass d1 = Compass.charToCompass('e'); // EAST * Compass d2 = Compass.charToCompass('X'); // UNKNOWN * * // Usar a direção num algoritmo de posicionamento: * int row = 5, col = 5, length = 4; * Compass dir = Compass.EAST; * for (int k = 0; k < length; k++) { *     int r = row + (dir == Compass.SOUTH ? k : dir == Compass.NORTH ? -k : 0); *     int c2 = col + (dir == Compass.EAST  ? k : dir == Compass.WEST  ? -k : 0); *     // validar e preencher (r, c2) no tabuleiro... * } * }</pre> * * @apiNote * Por convenção deste projeto, {@link #WEST} é mapeado para o carácter <b>'o'</b> (Oeste), * e <b>não</b> para 'w'. Este detalhe está documentado para evitar ambiguidade com códigos * internacionalizados que esperariam 'w' para West. * * @implNote * O método {@link #charToCompass(char)} faz apenas uma correspondência direta * <i>case-sensitive</i> aos caracteres listados acima. Qualquer outro valor resulta em * {@link #UNKNOWN}. Se o projeto necessitar de aceitar letras maiúsculas (ex.: 'N', 'S'), * considere normalizar com {@code Character.toLowerCase(ch)} antes da conversão. * * @since 1.0 */
public enum Compass {

    /**
     * Direção Norte (carácter {@code 'n'}).
     */
    NORTH('n'),

    /**
     * Direção Sul (carácter {@code 's'}).
     */
    SOUTH('s'),

    /**
     * Direção Este (carácter {@code 'e'}).
     */
    EAST('e'),

    /**
     * Direção Oeste (carácter {@code 'o'}).
     * <p><b>Nota:</b> utiliza-se {@code 'o'} por “Oeste” (PT). Não confundir com {@code 'w'}.
     */
    WEST('o'),

    /**
     * Direção desconhecida/indefinida (carácter {@code 'u'}).
     * <p>Útil como valor “seguro” quando a origem dos dados é incerta ou inválida.</p>
     */
    UNKNOWN('u');

    /**
     * Carácter que representa a direção.
     */

    private final char c;


    /**
     * Cria uma direção de {@code Compass} associada a um carácter.
     *
     * @param c o carácter representativo da direção (por exemplo, {@code 'n'} para Norte)
     */

    Compass(char c) {
        this.c = c;
    }


    /**
     * Devolve o carácter associado a esta direção.
     *
     * <p>Este método é útil para serialização simples (ex.: persistir num JSON minimalista)
     * e para interoperabilidade com camadas de UI/entrada de dados que trabalham com
     * símbolos curtos.</p>
     *
     * @return o carácter que representa esta direção
     * @see #toString()
     */
    public char getDirection() {
        return c;
    }

    /**     * Devolve a representação textual desta direção.     *     * <p>Por simplicidade, retorna uma string de tamanho 1 contendo o carácter associado     * (por exemplo, {@code "n"} para {@link #NORTH}).</p>     *     * @return a direção como {@link String}     */
    @Override
    public String toString() {
        return "" + c;
    }


    /**
     * Converte um carácter na direção {@link Compass} correspondente.
     *
     * <p>Mapeamento suportado (sensível a maiúsculas/minúsculas):
     * <ul>
     *     <li>{@code 'n' -> } {@link #NORTH}</li>
     *     <li>{@code 's' -> } {@link #SOUTH}</li>
     *     <li>{@code 'e' -> } {@link #EAST}</li>
     *     <li>{@code 'o' -> } {@link #WEST} (<i>Oeste</i>)</li>
     *     <li>Qualquer outro carácter -> {@link #UNKNOWN}</li>
     * </ul>
     *
     * <p><b>Comportamento em casos-limite:</b> o método não lança exceções para valores
     * inesperados e também não normaliza a caixa; se pretende aceitar 'N', 'S', 'E' ou 'O',
     * normalize previamente o carácter (ex.: {@code Character.toLowerCase(ch)}).</p>
     *
     * <p><b>Exemplo:</b></p>
     * <pre>{@code
     * Compass d  = Compass.charToCompass('e'); // EAST
     * Compass d2 = Compass.charToCompass('X'); // UNKNOWN
     * }</pre>
     *
     * @param ch o carácter representativo de uma direção
     * @return o valor {@link Compass} correspondente, ou {@link #UNKNOWN} se não corresponder
     * @see #NORTH
     * @see #SOUTH
     * @see #EAST
     * @see #WEST
     * @see #UNKNOWN
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}