import java.util.*;

enum Direction {  LEFT, UP, RIGHT, DOWN }


public class work {

   static class Game{

    int[] pos0 = new int[2];
    int configInicial[][];
    
    Game(int [][]configInicial){
        this.configInicial = configInicial;

        pos0 = findPos0();
    }
    
    //-------------------------------------------------//
    // Suporte class                                   //
    //-------------------------------------------------//

    private boolean solved(int [][]finalMatrix){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(configInicial[i][j] != finalMatrix[i][j]){
                    //System.out.println("Nop");
                    return false;
                }
            }
        }
        //System.out.println("Sim");
        return true;
    }
    
    private int[] findPos0(){

        int pos_0[] = new int[2];

        for(int i = 0; i < 4; i++){
            
            for(int j = 0; j < 4; j++){
            
                if(configInicial[i][j] == 0){
                    
                    pos_0[0] = j;
                    pos_0[1] = i;

                    //System.out.println(pos_0[0] + " " + pos_0[1]);
                    return pos_0;

                }
                
                }
            }
            
            //System.out.println("Return 0: Not found!");
            return pos0;
    }
    
    private ArrayList<Direction> possibleMoves(){

        ArrayList<Direction> moves = new ArrayList<Direction>();
    
        if(pos0[1] > 0) moves.add(Direction.UP);
        if(pos0[1] < 3) moves.add(Direction.DOWN);
        if(pos0[0] > 0) moves.add(Direction.LEFT);
        if(pos0[0] < 3) moves.add(Direction.RIGHT);
        
        for(int i = 0; i < moves.size(); i++){
            System.out.println(moves.get(i));
            
        }
        
        return moves;
    }
    
    
    private void MakeMove(Direction d){
        // pos0[0]: X COORDINATOR  ;   pos0[1]: Y COORDINATOR
        int temp = 0;
    
        switch(d){
            case UP:
                temp = configInicial[pos0[0] - 1][pos0[1]];
                configInicial[pos0[0] - 1][pos0[1]] = pos0[0];
                configInicial[pos0[0]][pos0[1]] = temp;
                print2DD(configInicial);
            case DOWN:
                temp = configInicial[pos0[0] + 1][pos0[1]];
                configInicial[pos0[0] + 1][pos0[1]] = pos0[0];
                configInicial[pos0[0]][pos0[1]] = temp;
                print2DD(configInicial);
            case LEFT:
                temp = configInicial[pos0[0]][pos0[1] - 1];
                configInicial[pos0[0]][pos0[1] - 1] = pos0[0];
                configInicial[pos0[0]][pos0[1]] = temp;
                print2DD(configInicial);
            case RIGHT:
                temp = configInicial[pos0[0]][pos0[1] + 1];
                configInicial[pos0[0]][pos0[1] + 1] = pos0[0];
                configInicial[pos0[0]][pos0[1]] = temp;
                print2DD(configInicial);
            }
    }


    // APOIO POR ENQUANTO PARA MAKEMOVE

    public void print2DD(int config[][])
    {
        // Loop through all rows
        for (int[] row : config)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }
    
}




    


//-------------------------------------------------//
// Nodes                                           //
    //-------------------------------------------------//

    /*
    private static **** MakeDescendants(){




        return ***;
    }
    */


    //-------------------------------------------------//
    // OPERADORES                                      //
    //-------------------------------------------------//





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


        

        for(int i = 0; i < 4; i++){
            
            for(int j = 0; j < 4; j++){
                
                if(configMatrix2[i][j] == 0){
                    if(i % 2 == 0){
                        flagBlankRow = false;
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


    //-------------------------------------------------//
    // General                                         //
    //-------------------------------------------------//

    /*
    private String GeneralSearchALgorithm(int [][]configInicial, int [][]configFinal){
        if (thereIsNoSolution(configInicial,configFinal) == false){
            return "It is impossible to reach a solution";
        }
        
        Queue<Integer> = new Queue<Integer>;
        while(!){
            node = 
            if( == configFinal){
                return path;
            }
            decendantList = MakeDescendants(node);

        }
    }
    */

    //-------------------------------------------------//
    // Algoritmos de busca                             //
    //-------------------------------------------------//

    // dfs(){}

    // bfs() {}

    // greedy() {}



    //-------------------------------------------------//
    //PRINT SUPPORT//
    //-------------------------------------------------//

    public void print1D(int mat[]){
        for(int i=0; i < mat.length; i++){
            System.out.println(i);
        }
    }

     public void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }

    //-------------------------------------------------//

    public static void main(String[] args){

    //-------------------------------------------------//
    // LOAD                                            //
    //-------------------------------------------------//

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

        //-------------------------------------------------//
        // Call Funcs                                      //
        //-------------------------------------------------//

        //print2D(initialConfig);
        System.out.println("-------------------");
        //print2D(finalConfig);

        Game jogo = new Game(initialConfig);

        System.out.println("-------------------");
        //thereIsNoSolution(initialConfig,finalConfig);
        if(thereIsNoSolution(initialConfig, finalConfig)){
        System.out.println("------TABULEIRO ATUAL------------");
        jogo.print2DD(initialConfig);
        System.out.println("-------------------");
        System.out.println("-------MOVIMENTOS POSSIVEIS---------");
        jogo.possibleMoves();
        System.out.println("-------------------");
        System.out.println("-------TABULEIRO APOS MOVIMENTO-----------");
        jogo.MakeMove(Direction.RIGHT);
        }


        
    }


}
