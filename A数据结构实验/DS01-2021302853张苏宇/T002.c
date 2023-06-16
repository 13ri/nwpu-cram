#include <stdio.h>
#include <stdlib.h>

#define MAX 1000      // �趨��С��������λ

typedef struct Node { // ˫������Ľṹ��
	int data;
	struct Node *pre;
	struct Node *next;
} LNODE, * LinkList;


void LinkCreate(LinkList L) ; //��ʼ��˫ѭ���������ҽ�ÿ���ڵ�data��Ϊ0

void Sum(LinkList a, LinkList b) ; //����������ӣ��Ӻ���ǰp = p->pre

void Mul(LinkList a, int n) ; //�˷���������ÿһλ *n���Ӻ���ǰp = p->pre

void Div(LinkList a, int n) ; //������������ÿһλ /n����ǰ����p = p->next


int main() {
	int n, up, down, s = 0;
	scanf("%d", &n);

	LinkList sum, temp;
	sum = (LinkList)malloc(sizeof(LNODE));
	temp = (LinkList)malloc(sizeof(LNODE));
	//�������������ͷ���

	LinkCreate(sum);
	LinkCreate(temp);
	//��ʼ��˫ѭ������

	sum->next->data = 2;//����Ϊ2
	temp->next->data = 2;//����Ϊ2

	while (s++ < 2000) {//���õ��ƹ�ϵʽ�����㦰��ֵ
		up = s;
		down = (2 * s + 1);
		Mul(temp, up);
		Div(temp, down);
		Sum(temp, sum);
	}

	sum = sum->next;
	if (n == 0)//������С�������
		printf("%d\n", sum->data);
	else {
		printf("%d.", sum->data);
		for (int i = 0; i < n; i++) {
			printf("%d", sum->next->data);
			sum = sum->next;
		}
	}
	printf("\n");
	return 0;

}


void LinkCreate(LinkList L) { //��ʼ��˫ѭ���������ҽ�ÿ���ڵ�data��Ϊ0
	LinkList p = L, q;
	int i;
	L->next = L->pre = L;
	for (i = 0; i < MAX; i++) {
		q = (LinkList)malloc(sizeof(LNODE));
		q->data = 0;
		p->next = q;
		q->pre = p;
		q->next = L;
		L->pre = q;
		p = q;
	}

}


void Sum(LinkList a, LinkList b) { //����������ӣ��Ӻ���ǰp = p->pre
	LinkList p = a->pre, q = b->pre;
	int x;
	while (q != b) {
		x = q->data + p->data;
		q->data = x % 10;
		q->pre->data += x / 10;
		q = q->pre;
		p = p->pre;
	}
}


void Mul(LinkList a, int n) { //�˷���������ÿһλ *n���Ӻ���ǰp = p->pre
	LinkList p = a->pre;
	int  x, y = 0;
	for (; p != a; p = p->pre) {
		x = (p->data) * n + y;
		y = x / 10;
		p->data = x % 10;
	}
	x = (p->data) * n + y;
	y = x / 10;
	p->data = x % 10;
}


void Div(LinkList a, int n) { //������������ÿһλ /n����ǰ����p = p->next
	LinkList p = a->next;
	int x, y = 0;
	for (; p != a; p = p->next) {
		x = p->data + y * 10;
		p->data = x / n;
		y = x % n;
	}
}


