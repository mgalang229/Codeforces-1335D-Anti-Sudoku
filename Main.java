import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*

anti-sudoku:
- each row contains at least 2 same elements
- each column contains at least 2 same elements
- each 3x3 block contains at least 2 same elements

before change:

1  5  4   8  7  3   2* 9  6 
3  8* 6   5  9  2   7  1  4 
7  2  9   6  4  1*  8  3  5

8  6  3   7  2  5   1  4  9* 
9  7  5*  3  1  4   6  2  8 
4  1  2   9  6* 8   3  5  7 

6  3  1   4  5  7   9  8* 2 
9  9  8   2  3  6   4  7  1 
2  4  7   1* 8  9   5  6  3 

after change:

1  5  4   8  7  3   3* 9  6 
3  3* 6   5  9  2   7  1  4 
7  2  9   6  4  5*  8  3  5

8  6  3   7  2  5   1  4  5*
9  7  9*  3  1  4   6  2  8 
4  1  2   9  5* 8   3  5  7 

6  3  1   4  5  7   9  9* 2 
9  9  8   2  3  6   4  7  1 
2  4  7   7* 8  9   5  6  3 

thoughts:
- at most 1 number is being changed in every column
- what would be the condition for 3x3 blocks? maybe the same
- chosen cell must have a number which has a frequency == 2 (from same column and row)
- we don't need to change if there's already a valid cell

how do we make sure that every 3x3 block has 2 equal elements?	
maybe I need to look at every 3x3 block first

0XX XXX XXX
XXX 0XX XXX
XXX XXX 0XX
1 4 7

X0X XXX XXX
XXX X0X XXX
XXX XXX X0X
2 5 8

XX0 XXX XXX
XXX XX0 XXX
XXX XXX XX0

3 6 9

finally cracked it!

actual:

554 873 296
386 992 714
729 641 335

833 725 149
975 344 628
412 968 377

633 457 982
598 233 471
247 189 566

 */

public class Main {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = 9;
			char[][] a = new char[n][n];
			for (int i = 0; i < n; i++) {
				a[i] = fs.next().toCharArray();
			}
			int start = 0;
			for (int i = 0; i < n; i += 3) {
				int row = i;
				for (int j = start; j < n; j += 3) {
					if (i < n - 3) {
						a[row][j] = a[row][j+1];
					} else {
						a[row][j] = a[row][j-1];
					}
					row++;
				}
				start++;
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					out.print(a[i][j]);
				}
				out.println();
			}
		}
		out.close();
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
