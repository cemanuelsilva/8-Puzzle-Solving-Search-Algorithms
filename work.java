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

    private static boolean solved(int [][]configMatrix, int [][]finalMatrix){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(configMatrix[i][j] != finalMatrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    
    private static int[] findPos0(int [][]configMatrix){

        int pos0[] = new int[2];

        for(int i = 0; i < 4; i++){
            
            for(int j = 0; j < 4; j++){
            
                if(configMatrix[i][j] == 0){
                    
                    pos0[0] = j;
                    pos0[1] = i;

                    }
                    
                }
            }

            System.out.println(pos0[0] + "" + pos0[1]);
            return pos0;
    }


    private static LinkedList<Direction> possibleMoves(int pos0[]){

        LinkedList<Direction> moves = new LinkedList<Direction>();

        if(pos0[1] > 0) moves.add(Direction.UP);
        if(pos0[1] < 3) moves.add(Direction.DOWN);
        if(pos0[0] > 0) moves.add(Direction.LEFT);
        if(pos0[0] < 3) moves.add(Direction.RIGHT);

        return moves;
    }


    void MakeMove(Direction d, int [][]configMatrix, int []pos0){
        // pos0[0]: X COORDINATOR  ;   pos0[1]: Y COORDINATOR
        int temp = 0;

        switch(d){
            case UP:
                configMatrix[pos0[0] - 1][pos0[1]] = temp;
                configMatrix[pos0[0] - 1][pos0[1]] = pos0[0];
                configMatrix[pos0[0]][pos0[1]] = temp;
            case DOWN:
                configMatrix[pos0[0] + 1][pos0[1]] = temp;
                configMatrix[pos0[0] + 1][pos0[1]] = pos0[0];
                configMatrix[pos0[0]][pos0[1]] = temp;
            case LEFT:
                configMatrix[pos0[0]][pos0[1] - 1] = temp;
                configMatrix[pos0[0]][pos0[1] - 1] = pos0[0];
                configMatrix[pos0[0]][pos0[1]] = temp;
            case RIGHT:
                configMatrix[pos0[0]][pos0[1] + 1] = temp;
                configMatrix[pos0[0]][pos0[1] + 1] = pos0[0];
                configMatrix[pos0[0]][pos0[1]] = temp;
    
            }
    }


    //Algoritmo geral

    private static ArrayList<Integer> conversion2Dto1D(int [][]configMatrix){

        ArrayList<Integer> matrixTemp = new ArrayList<Integer>(16);
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                matrixTemp.add(configMatrix[i][j]);
            }
        }
        
        
        return matrixTemp;
        
    }

    //-------------------------------------------------//
    // VERIFICAR SE É POSSIVEL CHEGAR Á CONFIG FINAL   //
    //-------------------------------------------------//

    private static boolean verifyParidade(ArrayList<Integer> configMatrix, int [][]configMatrix2){

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


        

        for(int i = 0; i < 3; i++){
            
            for(int j = 0; j < 3; j++){
                
                if(configMatrix2[i][j] == 0){
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

        result_configInicial = verifyParidade(configuracaoInicial, configInicial);
        result_configFinal = verifyParidade(configuracaoFinal, configFinal);

        if(result_configInicial == result_configFinal){
            System.out.println("EXISTE SOLUÇÃO!");
            return true;
        }
        else{
            System.out.println("NÃO É POSSIVEL CHEGAR A UMA CONFIGURAÇÃO FINAL!");
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
    //PRINT SUPPORT//
    //-------------------------------------------------//

    public static void print1D(int mat[]){
        for(int i=0; i < mat.length; i++){
            System.out.println(i);
        }
    }

     public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    //-------------------------------------------------//

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int[][] initialConfig = new int[4][4];
        int[][] finalConfig = new int[4][4];

        //LOAD//
        
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

        //-------------------------------------------------//

        //print2D(initialConfig);
        System.out.println("-------------------");
        //print2D(finalConfig);


        System.out.println("-------------------");
        thereIsNoSolution(initialConfig,finalConfig);

        findPos0(initialConfig);
        
    }


}
