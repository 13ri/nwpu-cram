#include <stdio.h>



//������Ԫ��ṹ��
typedef struct {
	int r, c;
	//��������������
	int d;
	//����ʾ��ֵ
} Triple;

//һ���������������
typedef struct {
	Triple list[1000];
	//1000����Ԫ��
	int m, n, t;
	//�洢�����������Ϣ��m�У�n�У�t����0Ԫ��
	int rpos[1000] = {0};
} Matrix;


void InitMatrix(Matrix *a);//��ʼ������

void OutputMatrix(Matrix a);//�������������Ԫ����ھ�����Ϣ����Ԫ����ʽ���

void MutiplyMatrix(Matrix a, Matrix b, Matrix *c); //�������


int main() {
	Matrix a, b, c;
	InitMatrix(&a);
	InitMatrix(&b);
	MutiplyMatrix(a, b, &c);
	OutputMatrix(c);
	return 0;
}


//��ʼ������
void InitMatrix(Matrix *A) {
	int i, j, k;
	scanf("%d %d", &A->m, &A->n);
	A->t = 0;
	while (1) {
		scanf("%d %d %d", &i, &j, &k);
		if (i == 0 && j == 0 && k == 0)
			break;
		//������0 0 0ʱ����
		A->t++;
		//��1��ʼ
		A->list[A->t].r = i;
		A->list[A->t].c = j;
		A->list[A->t].d = k;
		A->rpos[i]++;
		//����ʱ���rposǰ׺�����飬��¼��i���еķ�0Ԫ�ظ���
	}

	//ͨ������forѭ���������i���е�һ��Ԫ��������Ԫ����е�λ�ã�������洢�ڶ�Ӧ��rpos[i]���鵱��
	for (int i = 1; i <= A->m; i++)
		A->rpos[i] += A->rpos[i - 1];
	for (int i = A->m; i >= 1; i--)
		A->rpos[i] = A->rpos[i - 1] + 1;
}

//�������
void MutiplyMatrix(Matrix a, Matrix b, Matrix *c) {
	//��c����Ļ�����Ϣ��ʼ����ֵ
	c->m = a.m;
	c->n = b.n;
	c->t = 0;
	int t[1000] = {0};
	int x, y, z, s;
	//�ж������Ƿ񲻵���0
	if (a.t * b.t != 0) {
		for (int i = 1; i <= a.m; i++) {
			for (int j = 1; j <= a.n; j++)
				t[j] = 0;
			c->rpos[i] = c->t + 1;

			if (i < a.m)
				x = a.rpos[i + 1];
			else
				x = a.t + 1;

			for (int p = a.rpos[i]; p < x; p++) {
				int c = a.list[p].c;

				if (c < b.m)
					y = b.rpos[c + 1];
				else
					y = b.t + 1;

				for (int q = b.rpos[c]; q < y; q++) {
					z = b.list[q].c;
					t[z] += (a.list[p].d * b.list[q].d);
				}
			}
			//��a.list[p].d �� b.list[q].d�ĳ˻�

			for (int k = 1; k <= c->n; k++) {
				if (t[k]) {
					c->t++;
					c->list[c->t].r = i;
					c->list[c->t].c = k;
					c->list[c->t].d = t[k];
				}
			}
			//

		}
	}
}

//�������������Ԫ����ھ�����Ϣ����Ԫ����ʽ���
void OutputMatrix(Matrix A) {
	for (int i = 1; i <= A.t; i++) {
		printf("%d %d %d\n", A.list[i].r, A.list[i].c, A.list[i].d);
	}
}
