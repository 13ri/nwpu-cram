#include <stdio.h>
#define MAX 1000


//������Ԫ��ṹ��
typedef struct {
	int r, c;
	//��������������
	int d;
	//����ʾ��ֵ
} Triple;


//�洢�������Ԫ��˳���
typedef struct {
	Triple data[MAX];
	int m, n, t;
	//�洢�����������Ϣ��m�У�n�У�t����0Ԫ��
} TsMatrix;


//ϡ�����ļӷ�������ֵΪ�¾���
TsMatrix PlusMatrix(TsMatrix a, TsMatrix b) {
	int i = 0, k = 0, j = 0;
	TsMatrix c;
	c.t = 0;
	c.m = a.m;
	c.n = a.n;
//��ʼ�����ص��¾���

	while (i <= a.t && j <= b.t && (!(i == a.t && j == b.t))) {
		//�ж�����
		//���մ��ϵ��´����ҵ�˳�����α������ȱȽ�ͬ�У��ٱȽ�ͬ��
		if (a.data[i].r < b.data[j].r || (a.data[i].r == b.data[j].r && a.data[i].c < b.data[j].c)) {
			c.data[k++] = a.data[i++];
			c.t++;
		} else if (a.data[i].r > b.data[j].r || (a.data[i].r == b.data[j].r && a.data[i].c > b.data[j].c)) {
			c.data[k++] = b.data[j++];
			c.t++;
		}
		//������Ӻ�Ϊ0������������Ϊ������
		else {
			if (a.data[i].d + b.data[j].d != 0) {
				c.data[k] = a.data[i];
				c.data[k++].d = a.data[i++].d + b.data[j++].d;
				c.t++;
			}
		}
	}
	return c;
}



int main() {
	int m, n, t1, t2;
	scanf("%d%d%d%d", &m, &n, &t1, &t2);
	TsMatrix a, b, c;
	a.m = b.m = m;
	a.n = b.n = n;
	a.t = t1, b.t = t2;

	for (int i = 0; i < t1; i++) {
		scanf("%d%d%d", &a.data[i].r, &a.data[i].c, &a.data[i].d);
	}

	for (int i = 0; i < t2; i++) {
		scanf("%d%d%d", &b.data[i].r, &b.data[i].c, &b.data[i].d);
	}
	//��ȡҪ��ӵ���������a,b

	c = PlusMatrix(a, b);

	//��������Ӻ�ľ���Ԫ��
	for (int i = 0; i < c.t ; i++) {
		printf("%d %d %d\n", c.data[i].r, c.data[i].c, c.data[i].d);
	}
	return 0;
}
