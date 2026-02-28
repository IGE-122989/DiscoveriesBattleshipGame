package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que fornece a implementação base para todos os navios do jogo.
 * Gere a categoria, orientação, posição de referência e a lista de coordenadas ocupadas.
 * * Implementa o padrão Factory através do método {@link #buildShip(String, Compass, Position)}.
 */
public abstract class Ship implements IShip {

    // Constantes para os tipos de navios
    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Método de fábrica (Factory Method) para criar instâncias específicas de navios.
     * * @param shipKind O tipo de navio (ex: "galeao", "fragata").
     * @param bearing A orientação do navio (Norte, Sul, Este, Oeste).
     * @param pos A posição inicial (âncora) do navio no tabuleiro.
     * @return Uma instância da subclasse de Ship correspondente, ou {@code null} se o tipo for inválido.
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        // ... (implementação do switch)
    }

    private String category;
    private Compass bearing;
    private IPosition pos;
    /**
     * Lista de todas as posições individuais ocupadas pelo navio.
     */
    protected List<IPosition> positions;

    /**
     * Construtor base para um navio.
     * * @param category Nome da categoria do navio.
     * @param bearing Orientação inicial.
     * @param pos Posição de referência.
     * @throws AssertionError se bearing ou pos forem nulos.
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null : "O rumo (bearing) não pode ser nulo";
        assert pos != null : "A posição inicial não pode ser nula";

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    // ... (restantes overrides com Javadoc herdado de IShip)
}