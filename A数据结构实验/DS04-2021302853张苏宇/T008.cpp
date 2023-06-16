#include <cstdio>
#include <cstring>
#include <algorithm>
#include <cmath>
#include <iostream>

using namespace std;

int main() {
	int n;
	int map[1000][1000];
	scanf("%d", &n);

	//������һ������·����ʼ����������֮��ľ����Ǳߵ�Ȩ���������֮��û�б���������ȨΪ�����
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	//����ÿһ�Զ��� u �� v�������Ƿ����һ������ w ʹ�ô� u �� w �ٵ� v �ȼ�֪��·������,����Ǹ�����
	for (int k = 0; k < n; k++) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j && j != k && i != k) {
					//���D(i,k) + Dis(k,j) < Dis(i,j)�Ƿ���������������֤����i��k�ٵ�j��·����iֱ�ӵ�j��·����
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
	}
	//�����ߣ������Ͻǿ�ʼ����һֱ�����½�

	for (int i = 0; i < n; i++) {
		printf("%d\n", map[0][i]);
	}
	return 0;
}