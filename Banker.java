import java.util.Arrays;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.Color;

public class Banker {
    
    public static int Customer = 5;
    public static int Resource = 3;
    public static final int StartRes = 2;
    public static int[][] allocated = new int[Customer][Resource];
    public static int[][] need = new int[Customer][Resource];
    public static int[][] max = new int[Customer][Resource];
    public static int[] available = new int[Resource];
    public static int finish = 0;
    public static int time=0;
    public static int temp=0;
    
    public static String StrToHtml(String input) {
        String output="<html><body><p align=\"center\">";

        String[] tokens = input.split("\n");
        for (String token:tokens) {
            output=output+token+"<br/>";
        }
    
        output=output+"</p></body></html>";
        return output;
    }
    
    public static boolean isSafe(int customer_no, int[][] request)
    {
        boolean no = true;
        for(int i =0; i<Resource; i++)
        {
            if(request[customer_no][i]> available[i])
            {
                no = false;
                break;
            }
        }
        return no;
    }
    
    public static int request_resource(int customer_no, int[][] request)
    {
        if(isSafe(customer_no,request))
        {
            for (int i =0 ; i < Resource; i++)
            {
                available[i] -= request[customer_no][i];
                allocated[customer_no][i] += request[customer_no][i];
                need[customer_no][i] -= request[customer_no][i];
                for (int j =0; j < request[customer_no][i]; j++)
                {
                    Customer cust = new Customer(customer_no,request[customer_no][i]);
                    cust.start();
                }
            }
            finish = 1;
            return 0;
        }
        else
            return -1;
    }
    
    public static int release_resource(int customer_no, int[][] request)
    {
        if(finish == 1)
        {
            for(int i = 0; i < Resource; i++)
            {
                available[i] = available[i]+request[customer_no][i]+request[customer_no][i];
                need[customer_no][i] = 0;
                allocated[customer_no][i] = 0;
            }
        }
        finish = 0;
        return 0;
    }
    
  public static void placeComponents(JPanel panel) {
        
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        JLabel userLabel = new JLabel("最大客戶數目："+ String.valueOf(Customer));
        userLabel.setBounds(90,70,120,25);
        panel.add(userLabel);
        
        JLabel passwordLabel = new JLabel("資源種類數量："+String.valueOf(Resource));
        passwordLabel.setBounds(90,100,120,25);
        panel.add(passwordLabel);
        
        JLabel newLabel = new JLabel("起始資源數量："+String.valueOf(StartRes));
        newLabel.setBounds(90,130,120,25);
        panel.add(newLabel);
                      
        JLabel need = new JLabel("該位客戶申請的資源為");
        need.setBounds(150,420,150,30);
        panel.add(need);
             
        ImageIcon image = new ImageIcon("go.gif");
        JLabel man = new JLabel("", image, JLabel.CENTER);
        man.setBounds(350,250,300,300);
        panel.add(man);
        
        ImageIcon image2 = new ImageIcon("money.png");
        JLabel money = new JLabel("", image2, JLabel.CENTER);
        money.setBounds(300,5,680,200);
        panel.add(money);
        
        ImageIcon image3 = new ImageIcon("money3.png");
        JLabel money3 = new JLabel("", image3, JLabel.CENTER);
        money3.setBounds(50,250,280,150);
        panel.add(money3);
        
    }
    public static void main(String[] args)
    {
    	
        JFrame frame = new JFrame("銀行家演算法");
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
        frame.setBackground(Color.blue);

        JLabel templabel = new JLabel("目前尚未有任何成功");
        templabel.setBounds(685,380,300,30);
        panel.add(templabel);
        
        JLabel templabel2 = new JLabel("目前尚無客人需要");
        templabel2.setBounds(190,450,100,30);
        panel.add(templabel2);
        
        JLabel templabel3 = new JLabel("目前第0位客人");
        templabel3.setBounds(450,215,100,30);
        panel.add(templabel3);
       
        JLabel templabel4 = new JLabel("已經成功客人數目："+String.valueOf(time));
        templabel4.setBounds(430,550,400,30);
        panel.add(templabel4);
        
        //resetB
        JButton reset = new JButton("重置");
        reset.setBounds(450,580,100,30);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               time=0;
               for(int i = 0; i<Resource; i++)
                   //res from start
                   	available[i] = StartRes;
                   
               templabel4.setText("已經成功客人數目："+String.valueOf(time));
           }
        });
        panel.add(reset);

        //main
        
        for(int i = 0; i<Resource; i++)
        //res from start
        	available[i] = StartRes;
        
       
        if(args.length > 2)
        {
            for (int i = 0; i<Resource; i++)
            {
                available[i] = Integer.parseInt(args[i]);
                for (int j =0; j < Customer; j++ )
                {
                    Random rand = new Random();
                    max[j][i] = rand.nextInt(10);
                    allocated[j][i] = 0;
                    need[j][i] = max[j][i] - allocated[j][i];
                }
            }
        }
        
        int[][] request = new int[Customer][Resource];
        while(true)
        {
            Random rand = new Random();
            int customer_no = rand.nextInt(Customer);
            templabel3.setText("coustomer:"+customer_no);
            for ( int i = 0; i < Resource; i++ )
            {
                if(request[customer_no][i] == 0)
                    request[customer_no][i] = rand.nextInt(5);
            }
            templabel2.setText(Arrays.toString(request[customer_no]));
            int no = request_resource(customer_no,request);
            if (no ==0)
            {
            	templabel.setText("成功將資源: " + Arrays.toString(request[customer_no]) +"分配給客戶 No." + customer_no + "!");
                temp=time+1;
                time=temp;
                templabel4.setText("已經成功客人數目："+String.valueOf(time));
                
            }
            else {
                templabel.setText("將資源分配給 No." + customer_no + " 失敗!");
            }
            
            if(finish == 1)
                release_resource(customer_no,request);
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {}
        }
 
    }
}

