

import java.io.IOException;
import java.io.*;
import java.io.Reader;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.net.ssl.ExtendedSSLSession;
import java.util.Arrays;
import java.io.BufferedReader;
import java.util.*;
import java.text.DecimalFormat;
import java.lang.Math;


public class Rod_Cutting1
{
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int n=scanner.nextInt();
		int amt=scanner.nextInt();
		int p[]=new int[n+1];
		for(int y=1;y<=n;y++)
		{
			p[y]=scanner.nextInt();
		}
		int opt[][]=new int[n+1][amt+1];
		for(int o=0;o<=n;o++)
		{
			for(int m=0;m<=amt;m++)
			{
				opt[o][m]=0;
			}
		}
		
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=amt;j++)
			{
				if(i==1&&((p[i]*j)<=amt))
				{
					opt[i][p[i]*j]=opt[i][p[i]*(j-1)]+i;
				}
				else if(p[i]>j)
				{
					opt[i][j]=opt[i-1][j];
				}
				else if(j==p[i]&&opt[i-1][j]==0)
				{
					opt[i][j]=i;

				}
				else if(j==p[i]&&opt[i][j-p[i]]==0)
				{
					opt[i][j]=Math.min(opt[i-1][j],i+opt[i][j-p[i]]);
				}
				
				else if(opt[i][j-p[i]]!=0)
				{
					if(opt[i-1][j]==0)
					{
						opt[i][j]=opt[i][j-p[i]]+i;
					}
					else
						opt[i][j]=Math.min(opt[i-1][j],i+opt[i][j-p[i]]);
				}

				else
					opt[i][j]=opt[i-1][j];
			}
		}

/*		for(int k=0;k<=n;k++)
		{
			for(int l=0;l<=amt;l++)
			{
				System.out.print(opt[k][l]+" ");;
			}
			System.out.print("\n");
		}*/
		

	int	i=n;
	int	j=amt;
	if(opt[i][j]==0)
	{
		System.out.println("NO");
		
	}
	else{
		while(i-1>=0&&j>=0)
		{
			
			
			
				if(opt[i][j]==opt[i-1][j])
				{
					i=i-1;
				}
				else
				{
					System.out.print(i+" ");
					j=j-p[i];
					
				}
			
		}
	}

	


		
	}
}