import java.util.Scanner;

public class num2231 {
		public static void main(String[] args) {
			Scanner sc= new Scanner(System.in);
			int n = sc.nextInt();
			int m = 1;
			int ans = 0;
			for(m=1; m<=1000000 ; m++) {
				int ma = m;
			while(true) {
				if(ma<10) {
					ans += ma;
					break;
				}
				ans += ma%10;
				ma = ma/10;
				}
			ans += m;
			if(n==ans) {
				System.out.println(m);
				break;
				}
			ans=0;
			}
			if(m==1000000)
				System.out.println("0");
		}
}
