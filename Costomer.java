import java.util.Random;


class Customer implements Runnable {
    
    private Thread t;
    private String threadName = "listener thread";
    private int Customer_no;
    private int Resource_num;
    
    Customer(int Customer_no, int j)
    {
        threadName = Integer.toString(Customer_no) + '_' + Integer.toString( j);
    }
    public void run()
    {
        int customer_no = Customer_no;
        int resource_num = Resource_num;
        int[][] request = new int[customer_no][resource_num];
        Random rand = new Random();// random number object
        int sleeptime = rand.nextInt(5) +1;
        try
        {
            Thread.sleep(sleeptime*0);
        }
        catch (InterruptedException ioe)
        {}
        //Banker Banker = new Banker();
        Banker.release_resource(customer_no,request);      
    }
    
    public void start()
    {
        if (t==null)
        {
            t = new Thread(this,threadName);
            t.start();
        }//end of start
    }
    
}

