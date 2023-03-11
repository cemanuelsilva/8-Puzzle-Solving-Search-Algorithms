import java.util.*;

enum Heuristic {  BADNUM, MANDIST, NONE}  // up means: moving a piece up
enum Direction {  LEFT, UP, RIGHT, DOWN }



public class work {
   static class Game implements Comparable<Game> {

   //-------------------------------------------------//
   // CLASS && CONSTRUCTOR                            //
   //-------------------------------------------------//

      static int[][] finalConfig;
      Game pai;
      int[] pos0 = new int[2];
      int configInicial[][];
      int depth;
      Direction lastDirection;
      int heuristics;
      Heuristic heuristica;
    
      Game(int [][]initialConfig, Heuristic heuristica) {
         configInicial = new int[4][4];
         for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               configInicial[i][j] = initialConfig[i][j];
            }
         }
         this.heuristica = heuristica;
         HeuristicValue();
         pos0 = findPos0();
         depth = 0;
      }
    
    
    //-------------------------------------------------//
    // METHODS CLASS                                   //
    //-------------------------------------------------//

    
      private boolean solved() {
         for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
               if(configInicial[i][j] != finalConfig[i][j]) {
                   //System.out.println("Nop");
                   return false;
               }
            }
         }
         System.out.println("Done!");
         return true;
      }

      private static void finalConfig(int[][] finalConfigs) {
         finalConfig = new int[4][4];
         for(int i=0; i < 4; i++){
            for(int j = 0; j < 4; j++){
               finalConfig[i][j] = finalConfigs[i][j];
            }
         }
      }
    
      private int[] findPos0() {
         int pos_0[] = new int[2];
         for(int i = 0; i < 4; i++) {          
            for(int j = 0; j < 4; j++) {    
                if(configInicial[i][j] == 0) {        
                    pos_0[0] = j;
                    pos_0[1] = i;
                    //System.out.println(pos_0[0] + " " + pos_0[1]);
                    return pos_0;
                }               
            }
         }
            
         //System.out.println("Return 0: Not found!");
         return null;
      }
    
      void HeuristicValue() {
         switch(heuristica) {
            case BADNUM:
               heuristics = BadNumHeuristic();
               break;

            case MANDIST:
               heuristics = ManDistHeuristic();
               break;

            default:
         }
      }

      int BadNumHeuristic() {
         int soma = 0;
         for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
               if(configInicial[i][j] != finalConfig[i][j]) {
                  soma++;
               }
            }
         }
         return soma;
      }

      int ManDistHeuristic() {
         int soma = 0;
         int real = 0;
         int i_x=0,i_y=0,f_x=0,f_y=0;
         for(int i = 1;i < 16;i++) {
            for(int x = 0;x < 4;x++) {
               for(int y = 0;y < 4;y++) {
                  if(configInicial[x][y] == i) {
                     i_x = x;
                     i_y = y;
                  }
                  if(finalConfig[x][y] == i){
                     f_x = x;
                     f_y = y;
                  }
               }
            }
            soma += (Math.abs(i_x-f_x) + Math.abs(i_y-f_y));
         }
        return soma;
      }
    
      void MakeMove(Direction d) {
         // pos0[0]: Y COORDINATOR  ;   pos0[1]: X COORDINATOR 
         int swapIndex_x = -1;
         int swapIndex_y = -1;
         //System.out.println("Pos[0]: " + pos0[0] + " " + "Pos[1]: " + pos0[1]);

         switch(d){
            case UP:
               swapIndex_x = pos0[1] + 1;
               swapIndex_y =  pos0[0];           
               break;
            case DOWN:           
               swapIndex_x = pos0[1] - 1;
               swapIndex_y =  pos0[0];   
               break;
            case LEFT:               
               swapIndex_x = pos0[1];
               swapIndex_y =  pos0[0] + 1;             
               break;
            case RIGHT:
               swapIndex_x = pos0[1];
               swapIndex_y =  pos0[0] - 1; 
               break;

            default:
         }

         lastDirection = d;
         configInicial[pos0[1]][pos0[0]] = configInicial[swapIndex_x][swapIndex_y];
         configInicial[swapIndex_x][swapIndex_y] = 0;
         pos0 = findPos0();
         HeuristicValue();
     }

     @Override
    public String toString() {
        String str = "";
        for(int i=0; i<4; i++) {
            for(int j = 0; j<4; j++){
            str += configInicial[i][j] + "-";
            }
        }
        return str;
    }

      LinkedList<Game> MakeDescendants() {
        Vector<Direction> moves = possibleMoves();
        LinkedList<Game> descendents = new LinkedList<Game>();
        for(Direction d : moves) {
            Game descendent = new Game(configInicial, heuristica);
            descendent.MakeMove(d);
            //descendent.print2DD();
            //System.out.println("---");
            descendents.add(descendent);
        }
        return descendents;
    }

   
    Vector<Direction> possibleMoves(){

        Vector<Direction> moves = new Vector<Direction>();
    
        if(pos0[0] < 3) moves.add(Direction.LEFT);
        if(pos0[1] < 3) moves.add(Direction.UP);
        if(pos0[0] > 0) moves.add(Direction.RIGHT);
        if(pos0[1] > 0) moves.add(Direction.DOWN);
        
        /* 
        for(int i = 0; i < moves.size(); i++){
            System.out.println(moves.get(i));
            
        }
        */
        
        return moves;
    }

    LinkedList<Direction> GetPath() {
        LinkedList<Direction> path = new LinkedList<Direction>();
        LinkedList<Game> tabuleiros = new LinkedList<Game>();
        Game game = this;
        int i = 1;
        while(game.pai != null) {//Tá na ordem contrária
            path.addFirst(game.lastDirection);
            game = game.pai;
            tabuleiros.add(game);
            i++;
        }
        
        /* 
        int x = 0;
        for(int j = tabuleiros.size()-1; j > 0; j--){
            System.out.println("--------------------");
            System.out.println("Play Number: " + x );
            tabuleiros.get(j).print2DD();
            x++;
        }
        */

        return path;
    }

        @Override
        public int compareTo(Game game) {
            int thisF = this.heuristics + this.depth;
            int gameF = game.heuristics + game.depth;
            if(thisF > gameF) {
                return 1;
            } else if (thisF < gameF) {
                return -1;
            } else {
                return 0;
            }
        }
    


    public void print2DD()
    {
        // Loop through all rows
        for (int[] row : configInicial)
 
            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
    }


}

     //-------------------------------------------------------//
    // VERIFY IF ITS POSSIBLE TO ARRIVE FINAL CONFIGURATION   //
    //--------------------------------------------------------//

    
    
    private static boolean verifyParidade(ArrayList<Integer> configMatrix, int [][]configMatrix2){
    
        int somaParidades = 0;
        int somaTemp = 0;
        boolean flagSomaParidade = false;
        boolean flagBlankRow = false;
    
        for(int i = 0; i < configMatrix.size(); i++){
            
            for(int j = i+1; j < configMatrix.size(); j++){
                //System.out.println(matrixTemp.get(j));
                //System.out.println(matrixTemp.get(i));
                if(configMatrix.get(i) > configMatrix.get(j)){
                    somaTemp++;
                    
                }
            }
            somaParidades += somaTemp;
            somaTemp = 0;
    
            //System.out.println(somaParidades);
        }
        
        //System.out.println(somaParidades);

        if(somaParidades % 2 == 0){
            flagSomaParidade = false;
        }
    
    
        
    
        for(int i = 0; i > 4; i++){
            
            for(int j = 0; j > 4; j++){
                
                if(configMatrix2[i][j] == 0){
                    if(i+1 % 2 == 1){
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
            System.out.println("There is a solution!");
            return true;
        }
        else{
            System.out.println("Sorry, not possible!");
            return false;
        }
    
    }
    
    
    //-------------------------------------------------//
    // SEARCH ALGORITHMS                               //
    //-------------------------------------------------//

    static Stack<Direction> dfs(int[][] initialConfig, Heuristic heuristica){

        System.out.println("DFS:");

        Runtime runtime = Runtime.getRuntime();
        long startTime = System.nanoTime();
        long nodes = 0;
        
        Game tabuleiro = new Game(initialConfig, heuristica);
        HashSet<String> ciclos = new HashSet<>();
        
        
        Stack<Direction> path = new Stack<Direction>();
        Stack<Game> pilha = new Stack<Game>();
        pilha.push(tabuleiro);
        
        while(!pilha.isEmpty()) {
            Game node = pilha.pop();
            nodes++;
            ciclos.add(node.toString());
            if(node.solved()) {
    
                long endTime = System.nanoTime();
                long duration = ((endTime - startTime) / 1000000 );
                double memory = (runtime.totalMemory() - runtime.freeMemory());
                PrintPath(node,duration,memory, nodes);
                return path;
            }
            LinkedList<Game> descendents = node.MakeDescendants();
            for(Game desc : descendents) {
                if(ciclos.contains(desc.toString())){
                    continue;
                }
                pilha.push(desc);
            }
            if(node.lastDirection != null) path.push(node.lastDirection);
        }
        return null;

    }


    public static void bfs(int [][] configInicial, Heuristic heuristica){

        System.out.println("BFS:");
        
        Runtime runtime = Runtime.getRuntime();
        long nodes = 0;
        long startTime = System.nanoTime();
        Game tabuleiro = new Game(configInicial, heuristica);
        HashSet<String> ciclos = new HashSet<>();
        Queue<Game> q = new LinkedList<Game>();
        q.add(tabuleiro);
        
        while(!q.isEmpty()) {
            Game node = q.remove();
            nodes++;
            ciclos.add(node.toString());
            if(node.solved()) {
                
                    long endTime = System.nanoTime();
                    long duration = ((endTime - startTime) / 1000000);
                    double memory = (runtime.totalMemory() - runtime.freeMemory());
                    PrintPath(node,duration,memory,nodes);
                    return;
                
                    
                }
                
                LinkedList<Game> descendentList = node.MakeDescendants();
                for(Game desc : descendentList) {
                    if(ciclos.contains(desc.toString())){
                        continue;
                    }
                    desc.pai = node;
                    q.add(desc);

                    //desc.print2DD();
                    //System.out.println("---");
                    //System.out.println("POSSIVEIS");
                    //desc.possibleMoves();
                }
            }
            
    }


    static void Busca_iterativa_limitada_em_profundidade(int[][] configInicial, Heuristic heuristica){
        
        int depth_lim = 3;

        
        System.out.println("DFSI:");
        
        Runtime runtime = Runtime.getRuntime();
        long nodes = 0;
        long startTime = System.nanoTime();
        Game g = new Game(configInicial, heuristica);
        HashSet<String> ciclos = new HashSet<>();
        
        
        Stack<Direction> path = new Stack<Direction>();
        Stack<Game> s = new Stack<Game>();
        Stack<Game> newS = new Stack<Game>();
        s.push(g);
        while(depth_lim <= 50) {

            while(!s.isEmpty()) {
                
                Game node = s.pop();
                ciclos.add(node.toString());
                nodes++;
                
                if(node.solved()) {
                    
                    long endTime = System.nanoTime();
                    long duration = ((endTime - startTime) / 1000000);
                    double memory = (runtime.totalMemory() - runtime.freeMemory());
                    PrintPath(node, duration, memory,nodes);
                    return ;
                }

                if(node.depth > depth_lim) {
                    newS.push(node);
                    continue;
                }

                LinkedList<Game> descendents = node.MakeDescendants();
                for(Game desc : descendents) {
                    if(ciclos.contains(desc.toString())) {
                        continue;
                    }
                    desc.pai = node;
                    desc.depth = node.depth + 1;
                    s.push(desc);
                }
            }
            s = (Stack<Game>) newS.clone();
            newS.clear();
            depth_lim += 3;
        }
        
        return;
    }

  
     private static void Gulosa(int[][] initialConfig, Heuristic h) {
        
        Runtime runtime = Runtime.getRuntime();
        long nodes = 0;
        long startTime = System.nanoTime();
        Game g = new Game(initialConfig, h);
        System.out.println("Greedy:");
        HashSet<String> ciclos = new HashSet<>();

        
        LinkedList<Direction> path = new LinkedList<Direction>();
        PriorityQueue<Game> pq = new PriorityQueue<Game>();
        pq.add(g);

        while(!pq.isEmpty()) {
            Game node = pq.poll();
            nodes++;
            ciclos.add(node.toString());
            if(node.solved()) {
                
                long endTime = System.nanoTime();
                long duration = ((endTime - startTime) / 1000000);
                double memory = (runtime.totalMemory() - runtime.freeMemory());
                PrintPath(node,duration,memory,nodes);
                return;
            }

            LinkedList<Game> descendents = node.MakeDescendants();
            for(Game desc : descendents) {
                if(ciclos.contains(desc.toString())) {
                    continue;
                }
                desc.pai = node;
                pq.add(desc);
            }
        }
        return;
    }



    private static void A_Star(int[][] initialConfig, Heuristic heuristic) {
        
        Runtime runtime = Runtime.getRuntime();
        long nodes = 0;
        long startTime = System.nanoTime();
        Game g = new Game(initialConfig, heuristic);
        HashSet<String> ciclo = new HashSet<String>();
        int x = 0;
        System.out.println("A*:");

        PriorityQueue<Game> pq = new PriorityQueue<Game>();
        pq.add(g);

        while(!pq.isEmpty()) {
            Game node = pq.poll();
            nodes++;
            ciclo.add(node.toString());
            if(node.solved()) {

                long endTime = System.nanoTime();
                long duration = ((endTime - startTime) / 1000000);
                double memory = (runtime.totalMemory() - runtime.freeMemory());
                PrintPath(node,duration,memory,nodes);
                return;
            }
            LinkedList<Game> descendents = node.MakeDescendants();
            for(Game desc : descendents) {
                if(ciclo.contains(desc.toString())){
                    //x++;
                    //System.out.println("Entrei: " + x);
                    continue;
                }
                desc.pai = node;
                desc.depth = node.depth + 1;
                pq.add(desc);
                //System.out.println("Dept: " + desc.depth);
            }
        }

        
        return;
    }




    public static void PrintPath(Game node, long time, double memory, long nodes) {
        LinkedList<Direction> path = node.GetPath();

        System.out.println("-------------------------------");
        System.out.println("Heuristic: " + node.heuristica);
        System.out.println("Number of moves: " + path.size());
        System.out.println("Time: " + time + "ms");
        System.out.printf("Nodes: %d  Memory: %.2f MB\n", nodes, memory/(1024*1024));
        System.out.println("Path: " + path);
        System.out.println("-------------------------------");
    }




    //-------------------------------------------------//
    //PRINT SUPPORT                                    //
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
        // FUNCS CALL                                      //
        //-------------------------------------------------//


        System.out.println("-------------------");
        
         
        if(thereIsNoSolution(initialConfig, finalConfig)){

            
            Game.finalConfig(finalConfig);

            
            
            //dfs(initialConfig, Heuristic.NONE);
            Busca_iterativa_limitada_em_profundidade(initialConfig, Heuristic.NONE);
            //bfs(initialConfig, Heuristic.NONE);
            //Gulosa(initialConfig, Heuristic.MANDIST);
            //Gulosa(initialConfig, Heuristic.BADNUM);
            //A_Star(initialConfig, Heuristic.MANDIST);
            //A_Star(initialConfig, Heuristic.MANDIST);

        }
        



        
    }


}
