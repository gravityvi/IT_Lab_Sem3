import java.util.*;

public class assign9q2 {
	
	private static int vertices=0;
	private static int edges=0;
	private static int vertice_group[] = new int[100];
	
	public static void Merge_Sort(int[] array,int[][] array1,int a,int b){
		if(a==b)
			return;
		else{
			int mid = (a+b)/2;
			Merge_Sort(array,array1, a, mid);
			Merge_Sort(array,array1, mid+1, b);
			sort(array,array1,a,mid,b);
		}
	}
	
	public static void sort(int[] array ,int[][] array1, int a, int mid, int b){
		int[] a1 = new int[mid - a+1];
		int[] a2 = new int[b-mid];
		int[] a11 = new int[mid - a+1];
		int[] a21 = new int[b-mid];
		int[] a12 = new int[mid - a+1];
		int[] a22 = new int[b-mid];
		
		for(int i=0;i<mid - a+1;i++){
			a1[i] = array[a+i];
			a12[i] = array1[a+i][1];
			a11[i] = array1[a+i][0];
		}
		
		for(int i=1;i<=b-mid;i++){
			a2[i-1] = array[mid+i];
			a21[i-1] = array1[mid+i][0];
			a22[i-1] = array1[mid+i][1];
		}
		
		int j = 0;
		int k = a;
		int i=0;
		while (i < mid-a+1 && j < b-mid)
        {
            if (a1[i] <= a2[j])
            {
                array[k] = a1[i];
                array1[k][0] = a11[i];
                array1[k][1] = a12[i];
                i++;
            }
            else
            {
                array[k] = a2[j];
                array1[k][0] = a21[j];
                array1[k][1] = a22[j];
                j++;
            }
            k++;
        }
		
        /* Copy remaining elements of L[] if any */
        while (i <=  mid-a)
        {
            array[k] = a1[i];
            array1[k][0] = a11[i];
            array1[k][1] = a12[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < b-mid)
        {
            array[k] = a2[j];
            array1[k][0] = a21[j];
            array1[k][1] = a22[j];
            j++;
            k++;
        }		
	}	
	
	static class sets{
		sets parent;
		int rank;
		int data;
		
		public sets(){
			parent=this;
		}
		
		public sets(int d,int r){
			data=d;
			rank=r;
			parent=this;
		}
		
		void parent(sets x){
			sets a=findparent(this);
			a.parent = x;
			rank=0;
		}
		
	}
	
	public static sets findparent(sets x){
		if(x!=x.parent)
			x.parent=findparent(x.parent);
		return x.parent;
	}
	
	public static void union(sets s1,sets s2){//higher rank element will be parent
		int rank1 = findparent(s1).rank;
		int rank2 = findparent(s2).rank;
		if(rank1>rank2){
			s2.parent(s1);
			s2.rank=0;
		}
		else if(rank1==rank2){
			s2.parent(s1);
			s1.rank+=1;
			s2.rank=0;
		}
		else{
			s1.parent(s2);
			s1.rank=0;
		}
	}
	
	public static boolean are_disjoint(sets a,sets b){
		if(findparent(a)!=findparent(b))
			return false;
		return true;
	}
	
	public static int kruskal(int[][] set, int[] weight,int[] taken,sets[] vsets){
		int j=1;
		for(int i=1;i<=edges;i++)
			if(!are_disjoint(vsets[set[i][0]], vsets[set[i][1]])){
				union(vsets[set[i][0]], vsets[set[i][1]]);
				taken[j]=i;
				j++;
			}
		return j;
	}
	
	public static void print(int[][] set,int n,int[] weight,int[] taken,sets[] edge,int[][][] classify){
		System.out.println("Edges taken to make MST are:- ");		
		for(int i=0;i<n-1;i++){
			classify[set[taken[i+1]][0]][set[taken[i+1]][1]][1]= 2;
			classify[set[taken[i+1]][0]][set[taken[i+1]][1]][2]=2;
			classify[set[taken[i+1]][1]][set[taken[i+1]][0]][1]= 2;
			classify[set[taken[i+1]][1]][set[taken[i+1]][0]][2]=2;
		}
	}

	public static void make_groups(int[][][] classify,int x,int y){
		for(int i=1;i<=vertices ;i++){
			if(classify[x][i][2]==2 && vertice_group[i]==0 ){
				vertice_group[i] = vertice_group[x];
				make_groups(classify, i, y);
			}
		}
		
		for(int i=1;i<=vertices ;i++){
			if(classify[i][y][2]==2 && vertice_group[i]==0 ){
				vertice_group[i] = vertice_group[y];
				make_groups(classify, x, i);
			}
		}
	}
	
	public static void check(int[][][] classify,int x,int y){
		for(int i=1;i<=vertices;i++){
			for(int j=1;j<=vertices;j++){
				if(classify[i][j][0]==classify[x][y][0] && classify[i][j][1]!=2){
					classify[i][j][1] = 1;
					classify[x][y][1] = 1;
				}
			}				
		}
	}
	
	public static void Classify_edges(int[][][] classify){
		for(int i=1;i<=vertices;i++)
			for(int j=i+1;j<=vertices;j++)
				if(classify[i][j][0]!=0 && classify[i][j][1]==2){
					for(int k=1;k<=vertices;k++)
						vertice_group[k]=0;
					vertice_group[i] = 1;
					vertice_group[j] = 2;
					make_groups(classify,i,j);
					check(classify,i,j);
				}
			
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of vertices");
		vertices= scan.nextInt();
		System.out.println("Enter the number of edges");
		edges= scan.nextInt();
		sets[] set = new sets[vertices+1];
		for(int i=1;i<set.length;i++){
			set[i] = new sets(i,0);
		}
		
		int classify[][][] = new int[vertices+1][vertices+1][3];
		//index 0 shows whether the edge is present or not
		//index 1 shows type of edge
		//index 2 shows whether the edge is printed or not at the time of printing
		//at index 1 
		//1 means atleast once
		//2 means forced
		//0 means never
		int edge[][] = new int[edges+1][2];//for MST
		int[] weight = new int[edges+1];
		System.out.println("Enter two end points and weight");
		for(int i=1;i<edges+1;i++){
			
			edge[i][0]=scan.nextInt();
			edge[i][1]=scan.nextInt();
			classify[edge[i][0]][edge[i][1]][0] = weight[i] = scan.nextInt();
			classify[edge[i][1]][edge[i][0]][0] = weight[i];
		}
		
		Merge_Sort(weight, edge, 1, edges);//sorted in non-decreasing order
		
		int[] taken = new int[edges+1];
		
		int n = kruskal(edge, weight, taken, set);
		print(edge, n, weight, taken,set,classify);
		
		Classify_edges(classify);
		
		for(int i=1;i<=vertices;i++)
			for(int j=i+1;j<=vertices;j++){
				if(classify[i][j][0]!=0){
					if(classify[i][j][1]==0)
						System.out.println("Edge "+i+" -> " +j+" is not present in any MST");
					
					else if(classify[i][j][1]==1)
						System.out.println("Edge "+i+" -> " +j+" is present in atleast one MST");
					
					else if(classify[i][j][1]==2)
						System.out.println("Edge "+i+" -> " +j+" is present in every MST");
					
				}
			}
		
		scan.close();

	}

}