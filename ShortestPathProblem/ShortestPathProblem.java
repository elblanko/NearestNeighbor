package heuristics;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShortestPathProblem {
    private int matrix[][], vertex;
    private int wij;
    private List<String> dataList;
    private ReadDataInstance data;
    private Map<String, Integer> pathSolution;

    ShortestPathProblem() {        
        data = ReadDataInstance.getInstance();
        dataList = new ArrayList<>();
        dataList = data.getDataListInstance();
        pathSolution = new LinkedHashMap<>();
        System.out.println(this.getClass().toString());
    }

    public void matrix() {
        getMatrixInstance();
        //printMatrix();
        findThePath();
        printThePath();
    }

    private void initMatrix(int n) {
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = -1;
                }
            }
        }
    }            

    private void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private void findThePath() {
        int menor = 999;
        int pos_k = 0, i = 0;             
        while (i < (matrix.length -1)) {
            i = pos_k;
            /*-1-----inicio
            Encuentra el nodo con menor peso y su ubicacion.
             */            
            for (int j = 0; j < matrix.length; j++){                
                if (matrix[i][j] > 0 && matrix[i][j] <= menor) {
                    if(pathSolution.containsKey(i+","+j)){                        
                        //System.out.println("vertice ya visitado");
                    }else{
                        menor = matrix[i][j];
                        pos_k = j;
                    }                    
                }
            }
            /*-1------fin                       
            /*-2------inicio
            Se asigna el siguiente punto a iterar denotado por pos_k, se guarda la posicion 
            y el costo asociado.
             */
            //System.out.println(i + "," + pos_k + " wij " + menor);
            pathSolution.put(i+","+pos_k, menor);         
            menor = 999;
        }
    }
    
    private void printThePath(){        
        System.out.println("Path....");
        for(Map.Entry<String,Integer> entry : pathSolution.entrySet()){
            System.out.println(entry.getKey() +" Wij "+ entry.getValue());
            wij += entry.getValue();
        }
        System.out.println("Total weight: "+ wij+"\n"+"vertex: "+pathSolution.size());
    }
    
    public Map getPathSolution(){        
        return pathSolution;
    }
    
     public int[][] getMatrixInstance(){
        int x, y, w;
        String line = dataList.get(0);
        vertex = Integer.parseInt(line);
        initMatrix(vertex);
        String wij[];
        /*
        The following for loop fill the matrix whit the corresponding weight 
        according with the instances values.
        */
        for (int i = 1; i < dataList.size() - 1; i++) {
            wij = dataList.get(i).split("[\t]");
            x = Integer.parseInt(wij[0]) - 1;
            y = Integer.parseInt(wij[1]) - 1;
            w = Integer.parseInt(wij[2]);
            matrix[x][y] = w;
        }
        return matrix;
    }
     
    public int getVertex(){
        return vertex;
    } 
    public int getWij(){
        return wij;
    }
}