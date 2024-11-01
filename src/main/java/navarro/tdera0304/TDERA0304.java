/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package navarro.tdera0304;

/**
 *
 * @author phvna
 */
public class TDERA0304 {
public static void main(String[] args) {
    
        int[] tamanhos = {1000,10000,100000,500000,1000000};
        GnomeSort gs = new GnomeSort(tamanhos);
        MergeSort ms = new MergeSort(tamanhos);
        
     

        gs.SortGnome();
        ms.mergeSort();
            
        
        //gs.gerarGraficoTempo();
        //gs.gerarGraficoIteracoes();
        //gs.gerarGraficoTrocas();
        //ms.gerarGraficoTempo();
        //ms.gerarGraficoIteracoes();
        //ms.gerarGraficoTrocas();
    }
}