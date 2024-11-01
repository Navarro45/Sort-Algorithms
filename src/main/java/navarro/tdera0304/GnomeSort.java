package navarro.tdera0304;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GnomeSort {
    private long iteracoes;
    private long trocas;
    private long tempoExecucao;
    public DefaultCategoryDataset numIteracoes;
    public DefaultCategoryDataset numTrocas;
    public DefaultCategoryDataset tempoExec;
    int[][] TabelasDados;
    int[] tabela;
   
    
    public GnomeSort(int[]tamanhos){
        numIteracoes = new DefaultCategoryDataset();
        numTrocas = new DefaultCategoryDataset();
        tempoExec = new DefaultCategoryDataset();
        trocas = 0;
        iteracoes = 0;
        tempoExecucao = 0;
        TabelasDados = GerarDados(tamanhos);
        
    }

    public void SortGnome() {
        int indice = 0;
        long inicio = System.nanoTime();
        int tamanho = 0;
        
        for(int i = 0; i < TabelasDados.length;i++){
            tabela = TabelasDados[i];
            tamanho = tabela.length;

            while (indice < tamanho) {
                iteracoes++;
                if (indice == 0 || tabela[indice] >= tabela[indice - 1]) {
                    indice++;
                } else {
                    int temporario = tabela[indice];
                    tabela[indice] = tabela[indice - 1];
                    tabela[indice - 1] = temporario;
                    indice--;
                    trocas++;
                }
            }
            numIteracoes.addValue(iteracoes,"Gnome Sort", Integer.toString(tamanho));
            numTrocas.addValue(trocas,"Gnome Sort", Integer.toString(tamanho));
            long fim = System.nanoTime();
            tempoExecucao = (fim - inicio)/1000000;
            tempoExec.addValue(tempoExecucao/5, "Gnome Sort", Integer.toString(tamanho));
            System.out.println("Gnome Sort " + tamanho +" : ");
            System.out.println("Número de comparações (iterações): " + iteracoes);
            System.out.println("Número de trocas: " + trocas);
            System.out.println("Tempo de execução (ms): " + tempoExecucao/5);
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
                "Desempenho do Gnome Sort",
                "Tamanho do Vetor",
                "Tempo(ms)",
                tempoExec,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFrame frame = new JFrame("Gnome Sort Performance Chart");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void gerarGraficoIteracoes(){
        JFreeChart chart = ChartFactory.createLineChart(
                "Desempenho do Gnome Sort",
                "Tamanho do Vetor",
                "Numero de Iteracoes",
                numIteracoes,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFrame frame = new JFrame("Gnome Sort Performance Chart");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void gerarGraficoTrocas(){
        JFreeChart chart = ChartFactory.createLineChart(
                "Desempenho do Gnome Sort",
                "Tamanho do Vetor",
                "Numero de Trocas",
                numTrocas,
                PlotOrientation.VERTICAL,
                true, true, false);

        JFrame frame = new JFrame("Gnome Sort Performance Chart");
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
