import java.util.*;

enum Direction {  LEFT, UP, RIGHT, DOWN }

public class work {


    private class Game{

    int configInicial[][];
    int configFinal[][];
    int pos_zero;

    Game(int [][]configInicial, int [][]configFinal){
        this.configInicial = configInicial;
        this.configFinal = configFinal;
    }

    }

    
    private int findPos0(int [][]configMatrix){

        int pos0[] = new int[2];

        for(int i = 0; i < configMatrix.size(); i++){
            
            for(int j = i+1; j < configMatrix.size(); j++){
            
                if(configMatrix[i][j] == 0){
                    
                    pos[0] = j;
                    pos[j] = i;

                    }
                    
                }
            }

            return pos0;
    }


    //TODO FIX THIS
    private void possibleMoves(int []pos0){
        // pos0[0]: X COORDINATOR  ;   pos0[1]: Y COORDINATOR
        switch(){
        case UP:
            pos0[1] > 0;
        case DOWN:
            pos0[1] < 3;
        case LEFT:
            pos0[0] > 0;
        case RIGHT:
            pos0[0] < 3;

        }
    }


    void MakeMove(){

    }


    //Algoritmo geral

    private static ArrayList<Integer> conversion2Dto1D(int [][]configMatrix){

        ArrayList<Integer> matrixTemp = new ArrayList<Integer>(16);
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                matrixTemp.add(configMatrix[i][j]);
            }
        }
        
        //DEBUG VERIFICAR LISTA
        
        //for(int i = 0; i < matrixTemp.size(); i++) {   
        //    System.out.print(matrixTemp.get(i) + " ");
        //}

        return matrixTemp;
        
    }

    private static boolean verifyParidade(ArrayList<Integer> configMatrix){

        int somaParidades = 0;
        int somaTemp = 0;
        boolean flagSomaParidade = false;
        boolean flagBlankRow = false;

        for(int i = 0; i < configMatrix.size(); i++){
            //System.out.println("ENTROU CICLO I");
            for(int j = i+1; j < configMatrix.size(); j++){
                //System.out.println(matrixTemp.get(j));
                //System.out.println(matrixTemp.get(i));
                if(configMatrix.get(i) > configMatrix.get(j)){
                    somaTemp++;
                    //System.out.println("ENTROU");
                }
            }
            somaParidades += somaTemp;
            somaTemp = 0;

            //System.out.println(somaParidades);
        }

        if(somaParidades % 2 == 0){
            flagSomaParidade = true;
        }


        //RESOLVER, ISTO É UM ARRAYLIST, SECALHAR É SUPOSTO SER UMA LISTA

        for(int i = 0; i < configMatrix.size(); i++){
            //System.out.println("ENTROU CICLO I");
            for(int j = i+1; j < configMatrix.size(); j++){
                //System.out.println(matrixTemp.get(j));
                //System.out.println(matrixTemp.get(i));
                if(configMatrix[i][j] == 0){
                    if(i % 2 == 0){
                        flagBlankRow = true;
                    }
                    
                }
            }
            

            //System.out.println(somaParidades);
        }


        if(flagSomaParidade == flagBlankRow){
            return true;
        }
        else{
            return false;
        }

    }


    private static boolean thereIsNoSolution(int [][]configInicial, int [][]configFinal){
        
        ArrayList<Integer> configuracaoInicial = new ArrayList<Integer>(16);
        ArrayList<Integer> configuracaoFinal = new ArrayList<Integer>(16);
        boolean result_configInicial = false;
        boolean result_configFinal = false;

        configuracaoInicial = conversion2Dto1D(configInicial);
        configuracaoFinal = conversion2Dto1D(configFinal);

        result_configInicial = verifyParidade(configuracaoInicial);
        result_configFinal = verifyParidade(configuracaoFinal);

        if(result_configInicial == result_configFinal){
            System.out.println("EXISTE SOLUÇÃO!");
            return true;
        }
        else{
            System.out.println("UPS, F SOLUÇÃO!");
            return false;
        }

    }


/*
    private String GeneralSearchALgorithm(int QueueingFunction,int [][]configInicial, int [][]configFinal){
        if (thereIsNoSolution(configInicial,configFinal)){
            return "It is impossible to reach a solution";
        }
        return "boas";
    }
*/


     public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int[][] initialConfig = new int[4][4];
        int[][] finalConfig = new int[4][4];

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){ 
            initialConfig[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){ 
            finalConfig[i][j] = sc.nextInt();
            }
        }


        //print2D(initialConfig);
        System.out.println("-------------------");
        //print2D(finalConfig);


        System.out.println("-------------------");
        thereIsNoSolution(initialConfig,finalConfig);

        findPos0(initialConfig);
        
    }


}
