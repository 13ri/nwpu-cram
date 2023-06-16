#include <stdio.h>

//������Ԫ��ṹ��
typedef struct node {
	int r, c, d;
	//�ֱ�ָ��ýṹ��down��right��ָ��
	struct node *right;
	struct node *down;
} Node, *NodePtr;

//����ʮ������
typedef struct {
	int m, n, t;
	//�ֱ�ָ���º��ҵ�ָ��
	NodePtr *rp;
	NodePtr *cp;
} Matrix;


void CreateMatrix(Matrix *p);//����ʮ������

void InitMatrix(Matrix *p, int m, int n); //��ʼ��ϡ�����

void OutputMatrix(Matrix p);//���ϡ�����

void PlusMatrix(Matrix *a, Matrix *b); //����ʮ���������

int rm, rn;//����ȫ�ֱ�������������ʹ��


//ͨ�����������ó�ʼ��������������������Ӻ��������������
int main() {

	Matrix a, b;
	scanf("%d %d %d %d", &rm, &rn, &a.t, &b.t);
	InitMatrix(&a, rm, rn);
	InitMatrix(&b, rm, rn);
	CreateMatrix(&a);
	CreateMatrix(&b);
	PlusMatrix(&a, &b);
	OutputMatrix(a);
	return 0;

}


//��ʼ��ϡ�����
//����ָ�����飬ÿһ��Ԫ�ض���ָ�룬rp cp���Ƕ���ָ��
void InitMatrix(Matrix *p, int m, int n) {
	p->m = m, p->n = n;
	//�����ڴ�
	p->rp = (NodePtr *)malloc((m + 1) * sizeof(NodePtr));
	p->cp = (NodePtr *)malloc((n + 1) * sizeof(NodePtr));
	//��ʼ��������
	for (int i = 1; i < m + 1; i++)
		p->rp[i] = NULL;
	for (int i = 1; i < n + 1; i++)
		p->cp[i] = NULL;
}

//����ʮ������
//ͨ���жϽڵ����������͵�ǰ�����״̬֮��Ĺ�ϵ����������ӽ�������Ӧ�ڵ㣬�ֱ���к�������˳��������롣
void CreateMatrix(Matrix *p) {
	NodePtr k;

	//��һ�������Ԫ��
	for (int i = 0; i < p->t; i++) {
		NodePtr q = (NodePtr)malloc(sizeof(Node));
		scanf("%d%d%d", &q->r, &q->c, &q->d);
		q->right = NULL, q->down = NULL;

		// �з���������
		if (p->rp[q->r] == NULL || p->rp[q->r]->c > q->c) {
			q->right = p->rp[q->r];
			p->rp[q->r] = q;
		} else {
			for (k = p->rp[q->r]; k->right != NULL && k->right->c < q->c; k = k->right) ;
			q->right = k->right;
			k->right = q;
		}

		// �з���������
		if (p->cp[q->c] == NULL || p->cp[q->c]->r > q->r) {
			q->down = p->cp[q->c];
			p->cp[q->c] = q;
		} else {
			for (k = p->cp[q->c]; k->down != NULL && k->down->r < q->r; k = k->down) ;
			q->down = k->down;
			k->down = q;
		}
	}
}


//���ϡ�����
void OutputMatrix(Matrix p) {
	NodePtr k;
	//��һ���
	for (int i = 1; i <= p.m; i++) {
		k = p.rp[i];
		while (k != NULL) {
			printf("%d %d %d\n", k->r, k->c, k->d);
			k = k->right;
		}
	}
}


//����ʮ���������
void PlusMatrix(Matrix *a, Matrix *b) {
	NodePtr k, j;

	//���մ��ϵ��´����ҵ�˳���������
	//������ȡ�ڶ�������Ľڵ㣬�ʹ�������һ���������ж���������ӽ����һ������
	for (int i = 1; i <= rm; i++) {
		k = b->rp[i];
		while (k != NULL) {
			j = (NodePtr)malloc(sizeof(Node));
			j->c = k->c;
			j->r = k->r;
			j->d = k->d;
			j->down = NULL;
			j->right = NULL;

			//���rightΪ��
			if (a->rp[j->r] == NULL) {
				a->rp[j->r] = j;
				a->t++;
			} else {
				NodePtr h = a->rp[j->r];
				NodePtr pre = h;
				while (h != NULL && h->c < j->c) {
					pre = h;
					h = h->right;
				}


				//�������������������ӣ���Ϊ0��ɾȥ�˽ڵ㡣
				if (h != NULL && h->c == j->c) {
					h->d = h->d + j->d;
					if (h->d == 0) {
						if (pre == h) {
							a->rp[j->r] = h->right;
						} else {
							pre->right = h->right;
						}
						a->t--;
					}
				}
				//��ʣ���ֱ�ӱ���
				else {
					if (pre == h) {
						j->right = a->rp[j->r];
						a->rp[j->r] = j;
					} else {
						j->right = h;
						pre->right = j;
					}
					a->t++;
				}
			}
			k = k->right;
		}

	}
}


