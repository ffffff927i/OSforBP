import java.util.Scanner;
public class Bankers{
    private int nd[][], alloc[][], max[][], ava[][], n, m;// alloc = allocate, ava = available, nd = need
     
    private void input(){
     Scanner sc = new Scanner(System.in);
     System.out.print("Enter no. of processes and resources : ");
     n = sc.nextInt();  //no. of process
     m = sc.nextInt();  //no. of resources
     nd = new int[n][m];  //initializing arrays
     max = new int[n][m];
     alloc = new int[n][m];
     ava = new int[1][m];
       
     System.out.println("Enter allocation matrix -->");
     for(int i = 0; i < n; i++)
          for(int j = 0; j < m; j++)
         alloc[i][j] = sc.nextInt();  //allocation matrix
       
     System.out.println("Enter max matrix -->");
     for(int i = 0; i < n; i++)
          for(int j = 0; j < m; j++)
         max[i][j] = sc.nextInt();  //max matrix
       
        System.out.println("Enter available matrix -->");
        for(int j = 0; j < m; j++)
         ava[0][j] = sc.nextInt();  //available matrix
         
        sc.close();
    }
     
    private int[][] cal_nd(){
       for(int i = 0; i < n; i++)
         for(int j = 0; j < m; j++)  //calculating need matrix
          nd[i][j] = max[i][j] - alloc[i][j];
        
       return nd;
    }
  
    private boolean check(int i){
       //checking if all resources for ith process can be allocated
       for(int j = 0; j < m; j++) 
       if(ava[0][j] < nd[i][j])
          return false;
    
    return true;
    }
 
    public void isSafe(){
       input();
       cal_nd();
       boolean finish[] = new boolean[n];
       int j = 0;
 
       while(j < n){  //until all process allocated
       boolean allocated = false;
       for(int i = 0; i < n; i++)
        if(!finish[i] && check(i)){  //trying to allocate
            for(int k = 0; k < m; k++)
            ava[0][k] = ava[0][k] - nd[i][k] + max[i][k];
         System.out.println("Allocated process : "+i);
         allocated = finish[i] = true;
               j++;
             }
          if(!allocated) break;  //if no allocation
       }
       if(j == n)  //if all processes are allocated
        System.out.println("\nSafely allocated");
       else
        System.out.println("All proceess cant be allocated safely");
    }
     
    public static void main(String[] args) {
       new Bankers().isSafe();
    }
}