package iscteiul.ista.battleship;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe utilitária que contém as tarefas de teste e a lógica de interação com o utilizador.
 * Permite a execução incremental do projeto, desde a validação de navios individuais 
 * até à simulação completa de um jogo de Batalha Naval.
 * * @author fba
 */
public class Tasks {
    private static final Logger LOGGER = LogManager.getLogger();

    /** Número de tiros permitidos por cada ronda de ataque (rajada). */
    private static final int NUMBER_SHOTS = 3;

    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    // Comandos do utilizador
    private static final String NOVAFROTA = "nova";
    private static final String DESISTIR = "desisto";
    private static final String RAJADA = "rajada";
    private static final String VERTIROS = "ver";
    private static final String BATOTA = "mapa";
    private static final String STATUS = "estado";

    /**
     * Teste de construção de navios. 
     * Lê um navio e verifica se determinadas coordenadas (tiros) o ocupam.
     */
    public static void taskA() { /* ... */ }

    /**
     * Teste de gestão de frotas. 
     * Permite criar uma frota e consultar o seu estado de integridade.
     */
    public static void taskB() { /* ... */ }

    /**
     * Teste de frotas com funcionalidade de visualização de mapa (cheat mode).
     */
    public static void taskC() { /* ... */ }

    /**
     * Execução completa do jogo. 
     * Gere a frota, o estado do jogo, as rondas de tiro e as estatísticas de combate.
     */
    public static void taskD() { /* ... */ }

    /**
     * Constrói uma frota completa lendo dados do {@link Scanner}.
     * Garante que o número de navios adicionados respeita o tamanho definido em {@link Fleet#FLEET_SIZE}.
     * * @param in Scanner para leitura dos dados.
     * @return A instância da {@link Fleet} criada.
     */
    static Fleet buildFleet(Scanner in) { /* ... */ }

/**
 * Lê os dados de um navio (tipo, posição, orientação) e utiliza o Factory Method
 */