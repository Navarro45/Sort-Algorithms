/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package navarro.tdera0304;

import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MergeSort {

    // Variáveis globais para contar iterações e trocas
    private static long iteracoes;
    private static long trocas;
    public DefaultCategoryDataset numIteracoes;
    public DefaultCategoryDataset numTrocas;
    public DefaultCategoryDataset tempoExec;
    long tempoExecutado;
    int[][] TabelasDados;
    int[] tabela;
    
    public MergeSort(int[]tamanhos){
        iteracoes = 0;
        trocas = 0;
        tempoExecutado = 0;
        numIteracoes = new DefaultCategoryDataset();
        numTrocas = new DefaultCategoryDataset();
        tempoExec = new DefaultCategoryDataset();
        TabelasDados = GerarDados(tamanhos);
    }

    // Método para dividir o array em partes menores e retornar a lista encadeada de nós
    public static Node Split(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("O array de entrada não pode ser nulo ou vazio.");
        }

        Node head = null;
        Node current = null;

        for (int i = 0; i < array.length; i++) {
            int[] subArray = {array[i]}; // Cada elemento como um subarray

            Node newNode = new Node(subArray);
            if (head == null) {
                head = newNode;
            } else {
                current.next = newNode;
            }
            current = newNode;
        }

        return head;
    }

    // Método para ordenar os subarrays e reconstruir a lista
    public static int[] Sort(Node node) {
        if (node == null) return new int[0];
        if (node.next == null) return node.data; // Retornar o único elemento

        Node middle = getMiddle(node);
        Node nextToMiddle = middle.next;
        middle.next = null; // Dividir a lista em duas

        int[] leftSorted = Sort(node);
        int[] rightSorted = Sort(nextToMiddle);

        return merge(leftSorted, rightSorted);
    }

    // Método para mesclar dois arrays ordenados, contando iterações e trocas
    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            iteracoes++;
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
                trocas++;
            }
        }

        // Copiar os elementos restantes
        while (i < left.length) {
            result[k++] = left[i++];
            trocas++;
        }
        while (j < right.length) {
            result[k++] = right[j++];
            trocas++;
        }

        return result;
    }

    private static Node getMiddle(Node node) {
        if (node == null) return null;
        Node slow = node;
        Node fast = node.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Método para iniciar a contagem e exibir os resultados
    public void mergeSort() {
        long inicio = System.nanoTime();
        int tamanho = 0;
        
        for(int i =0;i<TabelasDados.length;i++){
            tabela = TabelasDados[i];
            tamanho = tabela.length;
            Node head = Split(tabela);
            Sort(head);
            numIteracoes.addValue(iteracoes,"Gnome Sort", Integer.toString(tamanho));
            numTrocas.addValue(trocas,"Gnome Sort", Integer.toString(tamanho));
            long fim = System.nanoTime();
            tempoExecutado = (fim - inicio)/1000000;
            tempoExec.addValue(tempoExecutado, "Gnome Sort", Integer.toString(tamanho));
            System.out.println("Merge Sort "+ tamanho +" : ");
            System.out.println("Número de comparações (iterações): " + iteracoes);
            System.out.println("Número de trocas: " + trocas);
            System.out.println("Tempo de execução (ms): " + tempoExecutado);
        }
        
    }
    
    public int[][] GerarDados(int[]tamanhos) {
        
        long[] seeds = {52, 85, 150,284,490}; 

        
        TabelasDados = new int[tamanhos.length][];

        
        for (int i = 0; i < tamanhos.length; i++) {
            Random random = new Random(seeds[i]); 

           
            tabela = new int[tamanhos[i]];

            
            for (int j = 0; j < tamanhos[i]; j++) {
                int codigo = 100_000_000 + random.nextInt(900_000_000);
                int dado = codigo;
                
                tabela[j] = dado; 
            }

            TabelasDados[i] = tabela; 
        }

        return TabelasDados; 
    }
    
    
    public void gerarGraficoTempo(){
        JFreeChart chart = ChartFactory.createLineChart(
                "Desempenho do Merge Sort",
                "Tamanho do Vetor",
                "Tempo(ms)",
                tempoExec,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFrame frame = new JFrame("Merge Sort Performance Chart");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void gerarGraficoIteracoes(){
        JFreeChart chart = ChartFactory.createLineChart(
                "Desempenho do Merge Sort",
                "Tamanho do Vetor",
                "Numero de Iteracoes",
                numIteracoes,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFrame frame = new JFrame("Merge Sort Performance Chart");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void gerarGraficoTrocas(){
        JFreeChart chart = ChartFactory.createLineChart(
                "Desempenho do Merge Sort",
                "Tamanho do Vetor",
                "Numero de Trocas",
                numTrocas,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFrame frame = new JFrame("Merge Sort Performance Chart");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    
    
}
