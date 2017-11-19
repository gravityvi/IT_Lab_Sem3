import java.util.*;

public class assign10
{
    public static  int[] sort;


    public static int getMax(int[] array) //function get maximum value out of n integers
    {
        int max=array[0];
        for(int i=0;i<array.length;i++)
        {
            if(array[i]>max)
            {
                max=array[i];
            }
        }
        return max;
    }

    public static void Radix(int[] Ar,int n,int m)
    {
        for(int exp=1;m/exp>0;exp=10*exp)
        {
            Counting(Ar,n,exp);
            
            
        }
    }
  

//counting sort function
    public static void Counting(int[] Ar,int n,int exp) // k is the range of given input // n is the number of inputs given
    {
        sort=new int[n];
        int[] index=new int[10];

        for(int i=0;i<10;i++)
        {
            index[i]=0;
        }
        
        for(int i=0;i<n;i++)
        {
            index[(Ar[i]/exp)%10]++;

        }

        for(int i=1;i<10;i++)
        {
            index[i]=index[i]+index[i-1];

        }

        
        
        

        for(int i=n-1;i>=0;i--)
        {
            sort[index[(Ar[i]/exp)%10]-1]=Ar[i];
            index[(Ar[i]/exp)%10]--;

        }

        for(int i=0;i<n;i++)
        {
            Ar[i]=sort[i];
        }

        
    }



    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int Ar[]=new int[n];
        for(int i=0;i<n;i++)
        {
            Ar[i]=scanner.nextInt();

        }

       
       
        int m=getMax(Ar);
       
        Radix(Ar, n, m);
       
        System.out.println("Answer for this input is as follows:");
        
        for(int i=0;i<n;i++)
        {
            System.out.print(Ar[i]+" ");
        }

    }
}