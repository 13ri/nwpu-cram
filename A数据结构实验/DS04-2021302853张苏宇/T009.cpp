#include <cstdio>
#include <cstring>
#include <algorithm>
#include <cmath>
#include <iostream>

using namespace std;

const int maxn = 1000;
const int inf = 10000;
int n;
int map[maxn][maxn];
int dis[maxn];
bool vis[maxn];
int pre[maxn];
int u, v;

void dijkstra(int s) {
	//s start
	for (int i = 0; i < n; i++) {
		dis[i] = inf;
		vis[i] = false;
	}
	//��dis��vis�ֱ���s�㵽���ڵ�i�����·�����ȣ��Լ��ڵ�i�Ƿ��ù�
	int u = s;
	dis[u] = 0;
	//������T�ж���ľ���ֵ�����޸ģ����ӽ�W���м䶥�㣬��V0��Vi�ľ���ֵ���̣����޸Ĵ˾���ֵ [1]
	while (!vis[u]) {
		vis[u] = true;
		for (int i = 0; i < n; i++) {
			if (!vis[i] && dis[i] > dis[u] + map[u][i]) {
				dis[i] = map[u][i] + dis[u];
				pre[i] = u;
			}
		}
		int tag = inf;
		for (int i = 0; i < n; i++) {
			if (!vis[i] && dis[i] < tag) {
				tag = dis[i];
				u = i;
			}
		}
	}
}

void PrintPath(int u, int v) {
	//����������õݹ��˼�룬��β��vһֱ��ǰ�ݹ飬����u�����ݹ�������·��
	if (u == v) {
		printf("%d\n", u);
		return ;
	}
	PrintPath(u, pre[v]);
	printf("%d\n", v);
}

int main() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	scanf("%d%d", &u, &v);
	dijkstra(0);
	dijkstra(u);
	PrintPath(u, v);
	return 0;
}