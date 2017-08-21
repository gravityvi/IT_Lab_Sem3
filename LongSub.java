

import java.io.IOException;
import java.io.*;
import java.io.Reader;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.*;
import java.text.DecimalFormat;
import java.lang.Math;


public class LongSub
{
	public static void  main(String[] args) {
		
	//match-0
	//vertical=1
	//horizontal=2
	//diagnol=3

Scanner scanner= new Scanner(System.in);
String s1=scanner.nextLine();
String s2=scanner.nextLine();
char[] a=s1.toCharArray();
char[] b=s2.toCharArray();

int n=a.length;
int m=b.length;
char[] A=new char[n+1];
char[] B= new char[m+1];
for(int l=1;l<=n;l++)
{
	A[l]=a[l-1];
}
for(int z=1;z<=m;z++)
{
	B[z]=b[z-1];
}
int[][] dpt=new int[n+1][m+1];
int[][] dpt1=new int[n+1][m+1];

for(int i=n;i>=1;i--)
{
	if(i==n){
		if(A[i]==B[m]){
			dpt[n][m]=1;
			dpt1[i][m]=0;}

		else{
			dpt[n][m]=0;
			dpt1[n][m]=1;
		}


	}

	else if(dpt[i+1][m]==1){
		dpt[i][m]=1;
		dpt1[i][m]=1;
	}
	else if(A[i]==B[m]){
		dpt[i][m]=1;
		dpt1[i][m]=1;
	}
	else{
		dpt[i][m]=0;
		dpt1[i][m]=1;
	}

}

for(int j=m-1;j>=1;j--)
{
	for(int k=n;k>=1;k--)
	{
		if(A[k]==B[j]){dpt1[k][j]=0;
			if(k>n-1||j>m-1){
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
	while(c<=n&&v<=m)
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