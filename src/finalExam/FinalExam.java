package finalExam;

import java.util.*;

public class FinalExam {
	//�� 20���� ������ ����
	//�����ʹ� ������ ���ɺ� Ű�� ��Ÿ����.
	//ù��° �����Ͱ� ����, �ι�° �����Ͱ� ������ ��� Ű�̴�.
	static double[][] data = {{0, 50.1}, {1, 76}, {2, 86.2}, {3, 93.1}, {4, 100.3}, {5, 107.2}, {6, 113.6}, {7, 119.5}, {8, 123.7}, {9, 129.1}, {10, 134.2}, {11, 139.4}, {12, 145.3}, {13, 151.8}, {14, 159.0}, {15, 165.5}, {16, 169.7}, {17, 171.8}, {18, 172.8}, {19, 173.4} };
	
	//�ʱ� ������ ����
	//�ʱ� �����ڴ� 32�� ������ ����
	//�� ������ ������ �����ؾ� �Ѵ�.
	//a*x^2 + b*x + c
	//����� ���̸� ���� a�� ������ -10~+10 b�� ������ -20~+20 c�� ������ -100~+100���� ����
	//������ Ÿ���� (int)���������� �����Ѵ�
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
	
	//�󸶸�ŭ�� ���̰� �����ϴ����� ����
	//��Һ��� ���̸� ���밪���� '����'�Ͽ� ��ü���� ���̸� ��ġ�� ��Ÿ������.
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
	
	//���ڻ��� : ������ ����
	//������ ������ ���� ��ҿ��� ���̸� ������ ���� �ټ��� ���̸� ���̴� ������ �� ���� ����ġ�� �ο��ϱ� ���� MAX-x�� ���·� ����� ����
	//MAX�� ���̰��� ���Ѽ��̰� �̸� �ʰ��ϴ� ���̴� 0���� �����Ѵ�. ���⼭ MAX�� 10000���� �����Ѵ�.
	public static int[][] selection(int[][] s)
	{
		Random random = new Random();
		double[] fit = new double[32];
		
		//���յ��� �Ǵ�
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
		
		//���յ��� ���� ����ġ�� �ο�
		double sum = Arrays.stream(fit).sum();
		double[] ratio = new double[32];
		for(int i=0; i<32; i++)
		{
			if(i==0) ratio[0] = (double)fit[0]/(double)sum;
			else ratio[i] = ratio[i-1] + (double)fit[i]/(double)sum;
		}
		
		//������ ���� ������ ����
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
	
	//������ ����
	//���� ���� ������ ��� ������ ������ 8�ڸ��� �ʰ����� �ʵ��� ����� ���� ����
	//����� �����ڰ� ���� ��(50%)�� ��ȯ�ǵ��� ����
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
	
	//2����(String)�� 10����(int)�� ��ȯ
    public static int invert(String x) {
        Random r = new Random();
        int a;
        
        //���� ��Ʈ�� ������ ���
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
        
        //����� ���
        a = Integer.parseInt(x, 2);
        for(int i=0; i<x.length(); i++) {
            double p = (double)1/ (double)32;
            //������ p���� ������(32���� 1�� Ȯ��)
            if(r.nextDouble() < p) {
            	//a�� �� ��ȭ
                a = 1 << i ^ a;
            }
        }
        return a;
    }
    
    //��������
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
		
		//�ʱ� ������ ����
		int[][] s = init();
		
		//���� �ݺ� Ƚ���� 10000���� ����
		for(int k=0; k<10000; k++) {
		//���ڻ���
		int[][] sx = selection(s);
		//������ ����
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
		//�������� �߻�
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

