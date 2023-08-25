package Experiment2;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class BB4TSP {

	final int NoEdge = -1; //��ʾû�б�
	private int minCost = Integer.MAX_VALUE; //��ǰ��С����
	private int[][] minEdge; //
	Comparator<HeapNode> cmp = new Comparator<HeapNode>() {
		public int compare(HeapNode e1, HeapNode e2) {//�Ӵ�С����
			return e2.lcost - e1.lcost;
		}
	};
	private PriorityQueue<HeapNode> priorHeap = new PriorityQueue<HeapNode>(100, cmp);//�洢��ڵ�
	private Vector<Integer> bestH = new Vector<Integer>();


	@SuppressWarnings("rawtypes")
	public static class HeapNode implements Comparable{
		Vector<Integer> cityArrange = new Vector<Integer>();//��������
		int lcost; //���۵��½�
		int level;//0-level�ĳ������Ѿ��źõ�
		//���췽��
		public HeapNode(Vector<Integer> cities,int lb, int lev){
			cityArrange.addAll(0, cities);
			lcost = lb;
			level = lev;
		}

		@Override
		public int compareTo(Object x) {//��������, ÿһ��pollFirst
			int xu=((HeapNode)x).lcost;
			return Integer.compare(lcost, xu);
		}
		public boolean equals(Object x){
			if(x instanceof HeapNode)
				return lcost==((HeapNode)x).lcost;
			return false;
		}

	}

	/**
	 * ���㲿�ֽ���½�.
	 *
	 * @param cityArrange ���е�����
	 * @param level ��ǰȷ���ĳ��еĸ���.
	 * @param cMatrix �ڽӾ��󣬵�0�У�0�в���
	 * @exception IllegalArgumentException exception
	 */
	public int computeLB(Vector<Integer> cityArrange, int level, int[][] cMatrix)
	{
		int lowcost = 0;
		int average = 0;
		int n = cMatrix.length - 1;
//		int max = selectMax(cMatrix);
		for (int i = 1 ; i < level ; i++) lowcost += cMatrix[cityArrange.get(i)][cityArrange.get(i + 1)];

		//����Ѿ��������һ�����У���Ҫ�������������һ������֮����û�б߿�����
		//���������ͨ����ô��ԭ��lowcost�Ļ������������һ�����е���һ�����е�cost
		if (level == n && cMatrix[cityArrange.get(level)][1] != -1)
			lowcost += cMatrix[cityArrange.get(level)][1];
			//���������ͨ����ô��ֱ�ӽ�lowcost��ΪMAXVALUE����ʾ��·��ͨ
		else if (level == n && cMatrix[cityArrange.get(level)][1] == -1)
			lowcost = Integer.MAX_VALUE;
			//������������һ�����У��ӵ�ǰ���п�ʼ�����½�
		else if (level != n){
			for (int i = level ; i <= n ; i++){
				if(i != n){
					//��
					average = (minEdge[cityArrange.get(i)][0] + minEdge[cityArrange.get(i + 1)][1]) / 2;
					lowcost += average;
				}
				else {
					//��
					lowcost += minEdge[1][1];
				}
			}
		}
		return lowcost;
	}

	/**
	 * ѡ��cMatrix�е����ֵ
	 *
	 * @param cMatrix
	 * @return
	 */
	public int selectMax(int[][] cMatrix){
		int max = 0;
		for(int i = 1 ; i < cMatrix.length - 1 ; i++)
			for(int j = 1 ; j <cMatrix.length - 1 ; j++){
				if (cMatrix[i][j] > max)
					max = cMatrix[i][j];
			}

		if(max == -1)
			return Integer.MAX_VALUE;
		return max;
	}

	/**
	 * �󲿷ֽ���Ͻ磺̰��
	 * @param cMatrix �ڽӾ���[n+1][n+1]:1->n
	 * @return �Ͻ�ub
	 */
	public int computeUB(int[][] cMatrix){
		int ub = 0;
		int n = cMatrix.length - 1;
		int max = this.selectMax(cMatrix);
		Vector<Integer> chosed_city = new Vector<>();
		Vector<Integer> res_city = new Vector<>();
		chosed_city.add(1);
		for(int i = 2 ; i < cMatrix.length ; i++) res_city.add(i);

		int i = 1;
		while(!res_city.isEmpty()){
			int minCost = Integer.MAX_VALUE;
			int temp = -1;
			for (int j = 1 ; j <= n ; j++){
				if(i != j && !chosed_city.contains(j)){
					if (cMatrix[i][j] == -1){
						minCost = (max==Integer.MAX_VALUE) ? max : Integer.MAX_VALUE;
						break;
					}
					if (cMatrix[i][j] < minCost && cMatrix[i][j] != -1){
						minCost = cMatrix[i][j];
						temp = j;
					}

				}
			}
			if(temp != -1){
				ub += minCost;
				chosed_city.add(temp);
				res_city.removeElement(temp);
			}
			else{
				ub += max;
				temp = res_city.get(1);
				res_city.remove(1);
				chosed_city.add(temp);
				res_city.removeElement(temp);
			}
			i = temp;
		}
		if(ub != Integer.MAX_VALUE){
			if(cMatrix[i][1] != -1)
				ub += cMatrix[i][1];
			else
				ub += max;
		}

//		System.out.println(ub);
		return ub;
	}

	/**
	 * �����������֮����û�б�
	 *
	 * @param cityArrange ��ǰcity����
	 * @param i cityArrange(i)
	 * @param j cityArrange(j)
	 * @param cMatrix �ڽӾ���[n+1][n+1]:1->n
	 * @return �������Ƿ���ͨ
	 */
	public boolean constraint(Vector<Integer> cityArrange, int i, int j, int[][] cMatrix){
		//���i - 1 �� j֮���б� ����true
		return cMatrix[cityArrange.get(i - 1)][cityArrange.get(j)] != -1;
	}

	/**
	 * ����ÿ��������С����ߺͳ���
	 *
	 * @param cMatrix �ڽӾ���[n+1][n+1]:1->n
	 * @return minEdge
	 */
	public int[][] clacuMin(int[][] cMatrix){
		// ����ÿ�����е���С��ߺͳ��ߣ����Լ����½�
		// minEdge[i][0] ��ʾ��i�����еĳ���
		// minEdge[i][1] ��ʾ��i�����е����
		int[][] minEdge = new int[cMatrix.length][2];
		for (int i = 1 ; i < cMatrix.length ; i++){
			// minOut �������i����С����
			int minOut = Integer.MAX_VALUE;
			// minIn �������i����С���
			int minIn = Integer.MAX_VALUE;
			for(int j = 0 ; j < cMatrix.length ; j++){
				// ����ҵ��ȵ�ǰ���߸�С�ĳ��ߣ��ͱ��浽minOut
				if (cMatrix[i][j] < minOut && cMatrix[i][j] != -1)
					minOut = cMatrix[i][j];
				// ����ҵ��ȵ�ǰ��߸�С����ߣ��ͱ��浽minIn
				if (cMatrix[i][j] < minIn && cMatrix[j][i] != -1)
					minIn = cMatrix[j][i];
			}
			// ����minEdge
			minEdge[i][0] = minOut;
			minEdge[i][1] = minIn;
		}
		// ������С��ߺͳ���
		return minEdge;
	}

	/**
	 * ����TSP�������С���۵�·��.
	 *
	 * @param cMatrix �ڽӾ��󣬵�0�У�0�в���
	 * @param n   ���и���.
	 * @exception IllegalArgumentException exception
	 */
	public int bb4TSP(int[][] cMatrix, int n)
	{
		//�����ʼ�ڵ�
		Vector<Integer> cityArrange = new Vector<Integer>() ;//��������
		cityArrange.add(0);//�ճ�һ�����У���cMatrixһ��
		for(int i = 1; i<=n; i++) cityArrange.add(i);
		int level = 2;//0-level�ĳ������Ѿ��źõ�
//		int lcost = computeLB(cityArrange, level, cMatrix) ; //���۵��½�
		this.minEdge = clacuMin(cMatrix);// ���ÿ�����е���С��ߺͳ���
		int ub = computeUB(cMatrix);// �Ͻ�
		int lb = computeLB(cityArrange, level, cMatrix);// �½�
		HeapNode currentNode = new HeapNode(cityArrange, lb, 1);// ��һ���ڵ�
		this.priorHeap.add(currentNode); // ��ӵ�һ���ڵ�
		while(level != n) // �����û�������һ������
		{
			//����һ�����п�ʼ����
			for (int i = level; i <= n ; i++){
				//���level-1�ܷ񵽴�i
				if (constraint(cityArrange, level, i, cMatrix)){
					Collections.swap(currentNode.cityArrange, level, i);

					// �����½�
					int temp = computeLB(currentNode.cityArrange, level + 1, cMatrix);
					// �����½ڵ�
					HeapNode node = new HeapNode(currentNode.cityArrange, temp, level + 1);
//					if(level + 1 == n)
//						node.lcost = computeLB(currentNode.cityArrange, level + 1, cMatrix);
					// ��������Ͻ� ˵������ڵ�û������ ����ڵ��Ǳ��̫С ��֦
					if(node.lcost > ub)
						continue;
					this.priorHeap.add(node);

					Collections.swap(currentNode.cityArrange, level, i);
				}
			}
			if(this.priorHeap.isEmpty())  break;
			currentNode = this.priorHeap.poll();
			level = currentNode.level;
		}
		if(level == n){// �������һ������
			if (currentNode.lcost < this.getMinCost()){
				this.bestH = currentNode.cityArrange;
				this.setMinCost(currentNode.lcost);
			}
		}

		return this.getMinCost();
	}

	public int getMinCost() {
		return minCost;
	}

	public void setMinCost(int minCost) {
		this.minCost = minCost;
	}

	public static void main(String[] args){
		BB4TSP bb4TSP = new BB4TSP();
		int[][] b = { { -1, -1, -1, -1, -1 }, { -1, -1, 9, 19, 13 }, { -1, 21, -1, -1, 14 }, { -1, 1, 40, -1, 17 },
				{ -1, 41, 80, 10, -1 } };
		int n = 4;
		bb4TSP.bb4TSP(b, n);
//		System.out.println(bb4TSP.getMinCost());
	}

}
