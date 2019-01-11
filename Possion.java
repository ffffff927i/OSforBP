import java.util.Random;

public class Possion{
    
    public static void main(String[] args){
    Random rand = new Random();
 for(int p = 0; p <50; p++){
    double a = (double) rand.nextInt(5)+1; 
    /*while(a == 0){
        a = (double) rand.nextInt(5);
    }*/
    double b = (double) getPossionVariable(a);
 }
   
}

    private static int getPossionVariable(double lamda) {
		int x = 0;
		//for(int p = 0; p < 10; p++){
		double y = Math.random(), cdf = getPossionProbability(x, lamda);
		while (cdf < y) {
			x++;
			cdf += getPossionProbability(x, lamda);
		}
		System.out.print(x + "\n");
		    
	//	}
		return x;
	}
 
	private static double getPossionProbability(int k, double lamda) {
		double c = Math.exp(-lamda), sum = 1;
		for (int i = 1; i <= (int) k; i++) {
			sum *= lamda / i;
		}
	//	System.out.print(sum*c);
		return sum * c;
	    
	}
	

}
