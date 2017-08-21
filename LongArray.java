

import java.io.IOException;
import java.io.*;
import java.io.Reader;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.*;
import java.text.DecimalFormat;
import java.lang.Math;


public class LongArray
{
	public static void  main(String[] args) {
		
	//match-0
	//vertical=1
	//horizontal=2
	//diagnol=3

Scanner scanner= new Scanner(System.in);

System.out.println("write the size of array");
int n=scanner.nextInt();
int[] a=new int[n];
int[] b=new int[n];
for(int p=0;p<n;p++)
{
	a[p]=scanner.nextInt();
	
}

for(int p=0;p<n;p++)
{
	b[p]=a[p];
	
}

Arrays.sort(b);

int[] A=new int[n+1];
int[] B= new int[n+1];
for(int l=1;l<=n;l++)
{
	A[l]=a[l-1];
}
for(int z=1;z<=n;z++)
{
	B[z]=b[z-1];
}
int[][] dpt=new int[n+1][n+1];
int[][] dpt1=new int[n+1][n+1];

for(int i=n;i>=1;i--)
{
	if(i==n){
		if(A[i]==B[n]){
			dpt[n][n]=1;
			dpt1[i][n]=0;}

		else{
			dpt[n][n]=0;
			dpt1[n][n]=1;
		}


	}

	else if(dpt[i+1][n]==1){
		dpt[i][n]=1;
		dpt1[i][n]=1;
	}
	else if(A[i]==B[n]){
		dpt[i][n]=1;
		dpt1[i][n]=1;
	}
	else{
		dpt[i][n]=0;
		dpt1[i][n]=1;
	}

}

for(int j=n-1;j>=1;j--)
{
	for(int k=n;k>=1;k--)
	{
		if(A[k]==B[j]){dpt1[k][j]=0;
			if(k>n-1||j>n-1){
				dpt[k][j]=1;

			    // System.out.println("hi");
			}
			else{
			dpt[k][j]=1+dpt[k+1][j+1];}
			
		}
		else{
			if(k>n-1){
				dpt[k][j] = dpt[k][j+1];
				dpt1[k][j]=2;}
			else{
				dpt[k][j] =  Math.max(Math.max(dpt[k][j+1],dpt[k+1][j]),dpt[k+1][j+1]);
				
				if(dpt[k][j]==dpt[k][j+1])
					dpt1[k][j]=2;
				if(dpt[k][j]==dpt[k+1][j])
					dpt1[k][j]=1;
				if(dpt[k][j]==dpt[k+1][j+1])
					dpt1[k][j]=3;
			}

		}
	}

}
		System.out.println(dpt[1][1]);
	int c=1;
	int v=1;
	while(c<=n&&v<=n)
	{
		if(dpt1[c][v]==0)
		{
			System.out.print(A[c]+" ");
			
			c=c+1;
			v=v+1;
		}
		else if(dpt1[c][v]==1)
			c=c+1;
		else if(dpt1[c][v]==2)
			v=v+1;
		else if(dpt1[c][v]==3)
		{
			c=c+1;
			v=v+1;
		}
	}
	

}



}