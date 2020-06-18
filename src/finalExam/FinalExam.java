package finalExam;

import java.util.*;

public class FinalExam {
	//총 20개의 데이터 샘플
	//데이터는 남성의 연령별 키를 나타낸다.
	//첫번째 데이터가 나이, 두번째 데이터가 남성의 평균 키이다.
	static double[][] data = {{0, 50.1}, {1, 76}, {2, 86.2}, {3, 93.1}, {4, 100.3}, {5, 107.2}, {6, 113.6}, {7, 119.5}, {8, 123.7}, {9, 129.1}, {10, 134.2}, {11, 139.4}, {12, 145.3}, {13, 151.8}, {14, 159.0}, {15, 165.5}, {16, 169.7}, {17, 171.8}, {18, 172.8}, {19, 173.4} };
	
	//초기 유전자 생성
	//초기 유전자는 32개 생성할 예정
	//세 변수의 난수를 생성해야 한다.
	//a*x^2 + b*x + c
	//계산의 편이를 위해 a의 범위는 -10~+10 b의 범위는 -20~+20 c의 범위는 -100~+100으로 설정
	//변수의 타입은 (int)정수형으로 제한한다
	public static int[][] init()
	{
		Random random = new Random();
		int x[][] = new int[32][3];
		
		for(int i=0; i<32; i++)
		{
			x[i][0] = random.nextInt() % 10;
			x[i][1] = random.nextInt() % 20;
			x[i][2] = random.nextInt() % 100;
		}
		
		return x;
	}
	
	//얼마만큼의 차이가 존재하는지를 검토
	//요소별의 차이를 절대값으로 '축적'하여 전체적인 차이를 수치로 나타내었다.
	public static double getDiff(int[] temp) 
	{
		double sum = 0;
		
		for(int i=0; i<20; i++) {
			//x
			double x = data[i][0];
			double diff = data[i][1] - (temp[0]*x*x +temp[1]*x + temp[2]);
			if(diff < 0) diff *= -1;
			sum += diff;
		}
		
		return sum;
	}
	
	//적자생존 : 유전자 선택
	//선택한 변수와 실제 요소와의 차이를 구한후 제일 근소한 차이를 보이는 변수에 더 많은 가중치를 부여하기 위해 MAX-x의 형태로 결과를 저장
	//MAX는 차이값의 상한선이고 이를 초과하는 차이는 0으로 저장한다. 여기서 MAX는 10000으로 설정한다.
	public static int[][] selection(int[][] s)
	{
		Random random = new Random();
		double[] fit = new double[32];
		
		//적합도를 판단
		for(int i=0; i<32; i++)
		{
			int[] temp = new int[3];
			temp[0] = s[i][0];
			temp[1] = s[i][1];
			temp[2] = s[i][2];
			
			double diff2 = 10000 - getDiff(temp);
			
			if(diff2 < 0) fit[i] = 0;
			else fit[i] = diff2;
		}
		
		//적합도에 따라 가중치를 부여
		double sum = Arrays.stream(fit).sum();
		double[] ratio = new double[32];
		for(int i=0; i<32; i++)
		{
			if(i==0) ratio[0] = (double)fit[0]/(double)sum;
			else ratio[i] = ratio[i-1] + (double)fit[i]/(double)sum;
		}
		
		//난수를 돌려 유전자 선택
		int[][] x = new int[32][3];
		for(int i=0; i<32; i++)
		{
			double r = random.nextDouble();
			
			if(r < ratio[0]) x[i] = s[0];
			else if(r < ratio[1]) x[i] = s[1];
			else if(r < ratio[2]) x[i] = s[2];
			else if(r < ratio[3]) x[i] = s[3];
			else if(r < ratio[4]) x[i] = s[4];
			else if(r < ratio[5]) x[i] = s[5];
			else if(r < ratio[6]) x[i] = s[6];
			else if(r < ratio[7]) x[i] = s[7];
			else if(r < ratio[8]) x[i] = s[8];
			else if(r < ratio[9]) x[i] = s[9];
			else if(r < ratio[10]) x[i] = s[10];
			
			else if(r < ratio[11]) x[i] = s[11];
			else if(r < ratio[12]) x[i] = s[12];
			else if(r < ratio[13]) x[i] = s[13];
			else if(r < ratio[14]) x[i] = s[14];
			else if(r < ratio[15]) x[i] = s[15];
			else if(r < ratio[16]) x[i] = s[16];
			else if(r < ratio[17]) x[i] = s[17];
			else if(r < ratio[18]) x[i] = s[18];
			else if(r < ratio[19]) x[i] = s[19];
			else if(r < ratio[20]) x[i] = s[20];
			
			else if(r < ratio[21]) x[i] = s[21];
			else if(r < ratio[22]) x[i] = s[22];
			else if(r < ratio[23]) x[i] = s[23];
			else if(r < ratio[24]) x[i] = s[24];
			else if(r < ratio[25]) x[i] = s[25];
			else if(r < ratio[26]) x[i] = s[26];
			else if(r < ratio[27]) x[i] = s[27];
			else if(r < ratio[28]) x[i] = s[28];
			else if(r < ratio[29]) x[i] = s[29];
			else if(r < ratio[30]) x[i] = s[30];
			else x[i] = s[31];
		}
		
		return x;
	}
	
	public static String int2String(String x) {
        return String.format("%8s", x).replace(' ', '0');
    }
	
	//유전자 교배
	//들어온 값이 음수일 경우 길이의 범위가 8자리를 초과하지 않도록 양수와 따로 관리
	//교배는 유전자가 서로 반(50%)씩 교환되도록 설정
	public static String[][] crossOver(int[][] x)
	{
		String[][] arr = new String[32][3];
		String aBit1;
		String aBit2;
		String bBit1;
		String bBit2;
		String cBit1;
		String cBit2;
        for(int i=0; i<32; i+=2) {
        	if(x[i][0] < 0)
        		aBit1 = Integer.toBinaryString(x[i][0]).substring(23, 32);
        	else
        		aBit1 = int2String(Integer.toBinaryString(x[i][0]));
        	if(x[i+1][0] < 0)
        		aBit2 = Integer.toBinaryString(x[i+1][0]).substring(23, 32);
        	else
        		aBit2 = int2String(Integer.toBinaryString(x[i+1][0]));
        	if(x[i][1] < 0)
        		bBit1 = Integer.toBinaryString(x[i][1]).substring(23, 32);
        	else
        		bBit1 = int2String(Integer.toBinaryString(x[i][1]));
        	if(x[i+1][1] < 0)
        		bBit2 = Integer.toBinaryString(x[i+1][1]).substring(23, 32);
        	else
        		bBit2 = int2String(Integer.toBinaryString(x[i+1][1]));
        	if(x[i][2] < 0)
        		cBit1 = Integer.toBinaryString(x[i][2]).substring(23, 32);
        	else
        		cBit1 = int2String(Integer.toBinaryString(x[i][2]));
        	if(x[i+1][2] < 0)
        		cBit2 = Integer.toBinaryString(x[i+1][2]).substring(23, 32);
        	else
        		cBit2 = int2String(Integer.toBinaryString(x[i+1][2]));
            
            arr[i][0] = aBit1.substring(0, 4) + aBit2.substring(4, 8);
            arr[i+1][0] = aBit2.substring(0, 4) + aBit1.substring(4, 8);
            arr[i][1] = bBit1.substring(0, 4) + bBit2.substring(4, 8);
            arr[i+1][1] = bBit2.substring(0, 4) + bBit1.substring(4, 8);
            arr[i][2] = cBit1.substring(0, 4) + cBit2.substring(4, 8);
            arr[i+1][2] = cBit2.substring(0, 4) + cBit1.substring(4, 8);
        }

        return arr;
	}
	
	//2진수(String)을 10진수(int)로 변환
    public static int invert(String x) {
        Random r = new Random();
        int a;
        
        //들어온 비트가 음수일 경우
        if(x.charAt(0) == '1')
        {
        	String temp = "";
			for(int i=0; i<x.length(); i++)
			{
				if(x.charAt(i) == '0')
					temp += "1";
				else
					temp += "0";
			}
			x = temp;
			a = -1 * (Integer.parseInt(x, 2) + 1);
        }
        
        //양수인 경우
        a = Integer.parseInt(x, 2);
        for(int i=0; i<x.length(); i++) {
            double p = (double)1/ (double)32;
            //난수가 p보다 작으면(32분의 1의 확률)
            if(r.nextDouble() < p) {
            	//a의 값 변화
                a = 1 << i ^ a;
            }
        }
        return a;
    }
    
    //돌연변이
    public static int[][] mutation(String[][] x) {
        int[][] arr = new int[32][3];
        for (int i=0; i<x.length; i++) {
        	for(int j=0; j<3; j++)
        	{
        		arr[i][j] = invert(x[i][j]);
        	}
        }
        return arr;
    }
	
	public static void main(String[] args)
	{
		double min = 100000;
		int[] answer = new int[3];
		
		//초기 유전자 세팅
		int[][] s = init();
		
		//세대 반복 횟수는 10000으로 설정
		for(int k=0; k<10000; k++) {
		//적자생존
		int[][] sx = selection(s);
		//유전자 교배
		String[][] cs = crossOver(sx);
		
		System.out.println("\n");
		
		/*
		for(int a=0; a<cs.length; a++)
		{
			System.out.printf("%d\n", s[a][0]);
			System.out.printf("%d\n", s[a][1]);
			System.out.printf("%d\n", s[a][2]);
		}
		*/
		
		System.out.println("\n");
		
		for(int a=0; a<cs.length; a++)
		{
			System.out.printf("%d : %d\n", a+1, sx[a][0]);
			System.out.printf("%d : %d\n", a+1, sx[a][1]);
			System.out.printf("%d : %d\n", a+1, sx[a][2]);
		}
		
		/*
		System.out.println("\n");
		
		for(int a=0; a<cs.length; a++)
		{
			System.out.printf("%s\n", cs[a][0]);
		}
		*/
		//돌연변이 발생
		int[][] mx = mutation(cs);
		
		double[] result = new double[32];
		for(int i=0; i<32; i++)
		{
			result[i] = getDiff(mx[i]);
			if(min > result[i])
			{
				min = result[i];
				answer = mx[i];
			}
		}
		
		System.out.println();
		System.out.println(min);
		System.out.println();
		
		for(int i=0; i<3; i++)
			System.out.println(answer[i]);
	}
	}
}

