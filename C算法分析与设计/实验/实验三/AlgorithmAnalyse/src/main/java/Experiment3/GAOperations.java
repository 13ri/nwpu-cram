package Experiment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GAOperations {
	/**
	 * ���������ʼ�⣬˼·���Ȳ��������޸���Ҳ���Ա߲������޸����������λ�õĴ���������࣬���������������.
	 * 
	 * @param popNum ��Ⱥ��С       
	 * @param length  ÿһ�����峤��.
	 * @param iniPop  �����ĳ�ʼ��Ⱥ.
	 * @param codes   ��������.
	 * @param codeNum   ���������.
	 * @param codeCount  ÿһ������ļ���.
	 */
	public void RandomInitialiation(int popNum, int length, int[] codes, int codeNum, int[] codeCount, int[][] iniPop) {
		int i, j;
		int[] nJs = new int[codeNum];//ͳ��ÿ���������������
		Random random = new Random();
		//����������룬��ȥ�أ��޸�
		for (int k = 0 ; k < popNum ; k++){
			//��Ԫ�ظ�ֵ1-codeNum����
			iniPop[k][0] = random.nextInt(codeNum) + 1;
			for (i = 1 ; i < codeNum ;){
				iniPop[k][i] = random.nextInt(codeNum) + 1;
				for (j = 0 ; j < i ; j++){
					//������ͬԪ�������¸�ֵ
					if (iniPop[k][i] == iniPop[k][j])
						break;
				}
				//ֻ��i֮ǰ��Ԫ��ȫ������ͬ���Ž���i++������һ��Ԫ�ظ�ֵ
				if (j == i)
					i++;
			}
		}
		
	}
	
	/**
	 * 
	 * @param pop ����       
	 * @param length  ���峤��.
	 * @param a �ڽӾ���
	 */
	public static double computeFitness(int[] pop, int length, int[][] a)
	{
		//���������Ӧ��
		double fitness = 0.0;
		for (int i = 1 ; i < length ; i++)
			fitness += a[pop[i - 1] - 1][pop[i] - 1];
		fitness += a[pop[length - 1] - 1][pop[0] - 1];

		//��Ӧ�Ⱥ���
		return 1 / fitness;
	}

	/**
	 * ����Ⱦɫ��
	 */
	public static void copyGh(int k, int kk, int length, int[][] iniPop1) {
		//������Ⱥ
		int[][] iniPop = iniPop1;
		for (int i = 0 ; i < length ; i++)
			iniPop1[k][i] = iniPop[kk][i];
	}
	
	/**
	 * 
	 * @param popNum ���� ����      
	 * @param length  ���峤��.
	 * @param iniPop1  ��Ⱥ
	 * @param fitness ÿһ���������Ӧ��
	 */
	public static void roundBet(int popNum, int length, int[][] iniPop1, double[] fitness)
	{
		//¥�̶�
		Random random = new Random();
		int selectId;
		double ran1;
		for (int k = 0 ; k < length ; k++){
			ran1 = random.nextDouble();
			int i;
			for(i = 0 ; i < length ; i++){
				if (ran1 <= fitness[i])
					break;
			}
			if (i == length)
				i--;
			selectId = i;
			copyGh(k, selectId, length, iniPop1);
		}

	}
	

	/**
	 * 
	 * @param iniPop  ��Ⱥ
	 * @param popNum ���� ����      
	 * @param length  ���峤��.
	 * @param disPos  ���������λ����
	 */
	public static void Disturbance(int [][] iniPop, int popNum, int length, int disPos)
	{
		//�Ŷ�
		// �������
		double pCross = 0.8;
		// �������
		double pMutate = 0.9;
		Random random = new Random();

		// ����
		for (int i = 0 ; i < popNum ; i += 2){
			if (random.nextDouble() <= pCross){
				for (int j = 0 ; j < disPos ; j++){
					int t = iniPop[i][j];
					iniPop[i][j] = iniPop[i + 1][j];
					iniPop[i + 1][j] = t;
				}
				// �Խ��������ظ���������޸�
				int re = i - 1;
				while (++re <= i + 1){
					// ͳ��ÿ���������������
					int[] njs = new int[length];
					for (int j = 0 ; j < length ; j++)
						// ��ʼ��=ʱ��Ϊ���֣���Ϊ0
						njs[j] = 0;
					for (int j = 0 ; j < length ; j++){
						// ��ȡλ��
						int pos = iniPop[re][j] - 1;
						// ͳ�Ƴ��ִ���
						njs[pos]++;
						if (njs[pos] > 1)
							// ��������1��Ҫ�޸�
							iniPop[re][j] = 0;
					}
					// δ�������ֵ�����
					List<Integer> intList = new ArrayList<Integer>();
					for (int j = 0 ; j < length ; j++)
						if (njs[j] == 0)
							// ��δ���ֵ��������������
							intList.add(j + 1);
					// ����
					Collections.shuffle(intList);
					int k = 0;
					for (int j = 0 ; j < length ; j++)
						if (iniPop[re][j] == 0)
							// ����
							iniPop[re][j] = intList.get(k++);
				}

			}
		}

		// ����
		for (int i = 0 ; i < popNum ; i++)
			// ��������ʣ�������������
			if (random.nextDouble() <= pMutate){
				// λ��1�Ļ���
				int pos1 = random.nextInt(length);
				// λ��2�Ļ���
				int pos2 = random.nextInt(length);
				while (pos1 == pos2)
					// λ����ͬ�����»�ȡλ��
					pos2 = random.nextInt(length);
				// ��������
				int t = iniPop[i][pos1];
				iniPop[i][pos1] = iniPop[i][pos2];
				iniPop[i][pos2] = t;
			}
	}
	
	/**
	 * ��ȡcode��codes�е�λ��
	 * @param code  ����
	 * @param codeNum �ܱ����� 
	 * @param codes  �������.
	 */
	public static int getCodePos(int code, int codeNum, int[] codes)
	{
		int pos = 0;
		for(; pos < codeNum; pos++)
		{
			if(code == codes[pos])
			{
				return pos;
			}
		}
		return -1;
	}
}
