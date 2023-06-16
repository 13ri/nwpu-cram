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


//ϡ������ת�ú���
void Transpose(TsMatrix A, TsMatrix *B, int i) {
	int p, q, r, x = i;

//������ a �洢��������������ֵ��ת�þ��� b
	B->m = A.n;
	B->n = A.m;

//�ӵ�һ�п�ʼ���α���
	if (i) {
		q = 0;
		for (r = 0; r < A.n; r++)
			for (p = 0; p < x; p++) {
				if (A.data[p].c == r) {
					//����a,b������������������a��ֵ����b
					B->data[q].r = A.data[p].c;
					B->data[q].c = A.data[p].r;
					B->data[q].d = A.data[p].d;
					q++;
				}
			}

	}
}

int main() {
	int m, n, i = 0, j = 0;

	scanf("%d %d", &m, &n);
	TsMatrix A, B;
	A.m = m;
	A.n = n;

//��ȡ��ʼ����a
	while (1) {
		int r, c, d;
		scanf("%d %d %d", &r, &c, &d);
		if (r == 0 && c == 0 && d == 0)
			break;
		A.data[i].r = r, A.data[i].c = c, A.data[i].d = d;
		i++;
	}

	Transpose(A, &B, i);

//������ת�ú�ľ���Ԫ��
	for (j = 0; j < i; j++) {
		printf("%d %d %d\n", A.data[j].c, A.data[j].r, A.data[j].d);
	}
	return 0;
}