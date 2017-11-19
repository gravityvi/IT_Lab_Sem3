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


  

//counting sort function
    public static void Counting(int[] Ar,int k,int n) // k is the range of given input // n is the number of inputs given
    {
        sort=new int[n];
        int[] index=new int[k+1];
        
        for(int i=0;i<n;i++)
        {
            index[Ar[i]]++;

        }

        for(int i=2;i<=k;i++)
        {
            index[i]=index[i]+index[i-1];

        }

        
        
        //System.out.println(index[5]);

        for(int i=0;i<n;i++)
        {
            sort[index[Ar[i]]-1]=Ar[i];
            index[Ar[i]]--;

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

       
       
        int k=getMax(Ar);
       
        Counting(Ar, k, n);
        
        for(int i=0;i<n;i++)
        {
            System.out.print(sort[i]+" ");
        }

    }
}