import java.util.*;

public class assign9
{
    public static ArrayList<Integer> topo_sort;
    public static int[] color;//0 for white //1 for grey //2 for black
    public static int[] par;
    public static int n;
    public static int time;
    public static int[] d;
    public static int[] f;
    public static int[][] adj;
    public static int[][] adj1;
   public static Stack<Integer> stack;
   public static ArrayList<Integer> ssc;

    public static void dfs(int n)
    {   
        
        topo_sort=new ArrayList<Integer>();
        d=new int[n];
        f=new int[n];
        color=new int[n];
        par=new int[n];
        int lastpop;
        for(int i=0;i<n;i++)
        {
            color[i]=0;
            par[i]=-1;
        }

       
        time=0;
        
        for(int i=0;i<n;i++)
        {
            if(color[i]==0)
                dfs_until(i);
            
        }
        
        
    }

    public static int dfs_until(int u)
    {
        
        
        color[u]=1;
        time=time+1;
        d[u]=time;
        for(int i=0;i<n;i++)
        {
            if(adj[u][i]==1&&color[i]==0)
            {
                par[i]=u;
               
                dfs_until(i);
            }
        }
        color[u]=2;
        time=time+1;
        f[u]=time;
        topo_sort.add(u);
        return 0;
    }
    
    public static int dfs_ssc(int u)
    {
        if(color[u]!=0)
         { return 0;}
        
         ssc.add(u);
         color[u]=1;

         for(int i=0;i<n;i++)
        {
            if(adj1[u][i]==1&&color[i]==0)
            {
                   dfs_ssc(i);
            }
        }
        
        
        return 0;
        
    }

    public static void strongly_comp(int n)
    {
        System.out.println("following are the strongly connected componnets");
        dfs(n);
        ssc=new ArrayList<Integer>();
        int v;
        stack=new Stack<Integer>();
        adj1=new int[n][n];
        for(int i=0;i<topo_sort.size();i++)
        {
            stack.push(topo_sort.get(i));
            
            
        }

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                adj1[i][j]=adj[j][i];
            }
        }

        Arrays.fill(color,0);
        while(!stack.isEmpty())
        {
            v=stack.pop();
            dfs_ssc(v);
            System.out.println(ssc);
            ssc.clear();

        }
        
    }


    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        n=scanner.nextInt();
        adj=new int[n][n];
        
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i!=j)
                {
                    System.out.println("is there a edge between "+i+" and "+j);
                    adj[i][j]=scanner.nextInt();//0 for no 1 for yes
                }
                else
                {

                    adj[i][j]=0;
                }
            }
        }


        strongly_comp(n);


        scanner.close();
        
    }
}