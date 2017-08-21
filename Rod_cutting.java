

import java.io.IOException;
import java.io.*;
import java.io.Reader;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.*;
import java.text.DecimalFormat;
import java.lang.Math;

public class Rod_cutting
{
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the total length of the rod");
		int n=scanner.nextInt();
		System.out.println("Enter the elements using space in between");
		int p[]=new int[n+1];
		for(int y=1;y<=n;y++)
		{
			p[y]=scanner.nextInt();
		}
		int opt[][]=new int[n+1][n+1];
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{
				if(i==1)
				{
					opt[i][j]=j*p[i];
				}
				else if(i>j)
				{
					opt[i][j]=opt[i-1][j];
				}
			else	opt[i][j]=Math.max(opt[i-1][j], p[i]+opt[i][j-i]);

				
			}
		}
		scanner.close();
		System.out.print("following is the maximum profit gained:\n");
		System.out.print(opt[n][n]);
		System.out.print("\n");
		System.out.println("following are the items");
		int	i=n;
		int j=n;
		while(i>=1&&j>=1)
		{
			if(opt[i][j]==opt[i-1][j])
			{
				i=i-1;
			}
			else
			{
				System.out.print(i+" "); 
				j=j-i;
			}
		}
	}
}
