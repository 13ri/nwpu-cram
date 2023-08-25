package Experiment2;

public class Back4TSP {

	int NoEdge = -1;
	int bigInt = Integer.MAX_VALUE;
	int[][] a; // �ڽӾ���
	int cc = 0; // �洢��ǰ����
	int bestc = bigInt;// ��ǰ���Ŵ���
	int[] x; // ��ǰ��
	int[] bestx;// ��ǰ���Ž�
	int n = 0; // �������

	/**
	 * backtrack
	 *
	 * @param i ��ǰ���
//	 */
	private void backtrack(int i) {//iΪ��ʼ���
		if (i > n) {
			//��ȴ���n��ʱ��������cost�����·��
			bestc = cc;
			bestx = x;
		} else {
			// ��Ȼ�û�г���������
			// ���Ž���
			for(int j = i; j <= n; j++) {
				// ����λ��i��λ��j�ϵ�Ԫ��
				swap(x[i], x[j]);
				if(check(i)) {
					// i�������һ���ڵ��ʱ���ÿ��Ǵ����һ���ڵ�ص���һ���ڵ��·
					if(i < n && cc + a[x[i-1]][x[i]] < bestc) {
						// �������cost
						cc += a[x[i-1]][x[i]];
						// ������һ���
						backtrack(i+1);
						// �ָ����cost
						cc -= a[x[i-1]][x[i]];
					}
					// i�����һ���ڵ��ʱ��Ҫ���Ǵ����һ���ڵ�ص���һ���ڵ��·
					if(i == n && cc + a[x[i-1]][x[i]] < bestc) {
						// �������cost
						cc += (a[x[i-1]][x[i]]+a[x[i]][1]);
						// ������һ���
						backtrack(i+1);
						// �ָ����cost
						cc -= (a[x[i-1]][x[i]]+a[x[i]][1]);
					}
				}
				//����Ԫ��
				swap(x[i], x[j]);
			}
		}
	}

	/**
	 * ��������λ���ϵ�Ԫ��
	 *
	 * @param i ��һ��Ԫ�ص�λ��
	 * @param j �ڶ���Ԫ�ص�λ��
	 */
	private void swap(int i, int j) {
		int temp = x[i];
		x[i] = x[j];
		x[j] = temp;
	}

	/**
	 * ����λ���ϵ�Ԫ���Ƿ��б�������Ҫ
	 *
	 * @param pos Ҫ����λ��
	 * @return true or fasle
	 */
	public boolean check(int pos) {
		if(pos < 2) return true;
		if(pos<n && a[x[pos - 1]][x[pos]] != NoEdge) return true;
		if(pos==n && a[x[pos - 1]][x[pos]] != NoEdge && a[x[pos]][1] != NoEdge) return true;
		return false;
	}

	/**
	 *
	 *
	 * @param b
	 * @param num
	 */
	public void backtrack4TSP(int[][] b, int num) {
		n = num;
		x = new int[n + 1];
		for (int i = 0 ; i <= n ; i++)
			x[i] = i;
		bestx = new int[n + 1];
		a = b;
		backtrack(2);
	}

}
