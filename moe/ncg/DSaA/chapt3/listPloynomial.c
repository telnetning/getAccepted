#include <stdio.h>
#include <stdlib.h>

typedef struct Node *PtrNode;

struct Node 
{
    int Coefficient;
    int Exponent;
    PtrNode Next;
};

typedef PtrNode Polynomial;

//创造多项式
Polynomial Create()
{
    int N, i;
    printf("调试语句");
    PtrNode head;
    head=malloc(sizeof(struct Node));
    PtrNode p;
    head->Next = NULL;
    p = head;
    printf("输入该多项式的项数:");
    scanf("%d", &N);
    for(i = 1; i <= N; i++)
    {
        p->Next = malloc(sizeof( struct Node));
        printf("输入底数:");
        scanf("%d",&p->Next->Coefficient);
        printf("输入指数:");
        scanf("%d",&p->Next->Exponent);
        p = p->Next;
    }
    return head;
}

//分配一个新的节点
PtrNode CreateNode()
{
    PtrNode newNode;
    newNode=malloc(sizeof(struct Node));
    return newNode;
}

//两个节点的数据Copy
void CopyNode(PtrNode p1, PtrNode p2)
{
    p1->Exponent=p2->Exponent;
    p1->Coefficient=p2->Coefficient;
}

//两个多项式相加
Polynomial PolynomialPlus(Polynomial head1, Polynomial head2){
    PtrNode p1=head1->Next;
    PtrNode p2=head2->Next;
    PtrNode head, newNode, currentNode;
    head=CreateNode();
    currentNode=head; 
    while(p1&&p2){
        if(p1->Exponent>p2->Exponent){
            newNode=CreateNode();
            CopyNode(newNode, p2);
            currentNode->Next=newNode;
            p2=p2->Next;
        } 
        else if(p1->Exponent<p2->Exponent){
            newNode=CreateNode();
            currentNode->Next=newNode;
            CopyNode(newNode, p1);
            p1=p1->Next;
        } else {
            newNode=CreateNode();
            newNode->Exponent=p1->Exponent;
            newNode->Coefficient=p1->Coefficient+p2->Coefficient;
            currentNode->Next=newNode;
            p1=p1->Next;
            p2=p2->Next;
        }
        currentNode=currentNode->Next;
    } 

    if(p1){
        while(p1){
            newNode=CreateNode();
            CopyNode(newNode, p1);
            currentNode->Next=newNode;
            currentNode=currentNode->Next;
            p1=p1->Next;
        } 
    }

    if(p2){
        while(p2){
            newNode=CreateNode();
            CopyNode(newNode, p2);
            currentNode->Next=newNode;
            currentNode=currentNode->Next;
            p2=p2->Next;
        } 
    }
    return head;
}

/*思路：使用另外的一个chainlist，每
 * 相乘得到一个新的数据节点，就将其
 * 插入到新的chainlist中，并保证新的
 * chainlist始终有序,复杂度O（M^2*N^2）
 */
void Insert(Polynomial newPolynomial, int coefficient, int exponent)
{
    PtrNode p;
    PtrNode newNode;
    p=newPolynomial;
    while(p->Next!=NULL){
        if(p->Next->Exponent<exponent){
            p=p->Next;
        } else if(p->Next->Exponent>exponent){
            newNode=CreateNode();
            newNode->Coefficient=coefficient;
            newNode->Exponent=exponent;
            newNode->Next=p->Next;
            p->Next=newNode;
            return;
        } else {
            p->Next->Coefficient+=coefficient;
            return;
        }
        p=p->Next;
    }    
    newNode=CreateNode();
    newNode->Coefficient=coefficient;
    newNode->Exponent=exponent;
    p->Next=newNode;
}

Polynomial PolynomialMulti(Polynomial head1, Polynomial head2)
{
    PtrNode p1, p2;
    Polynomial newPolynomial;
    p1=head1->Next;
    newPolynomial=CreateNode();
    while(p1){
        p2=head2->Next;
        while(p2){
            Insert(newPolynomial, p1->Coefficient*p2->Coefficient, p1->Exponent+p2->Exponent);     
            p2=p2->Next;
        } 
        p1=p1->Next;
    }
    return newPolynomial;
}

//打印出多项式
void PrintPolynomial(Polynomial head)
{
    PtrNode P;
    P=head->Next;
    while(P){
        printf("%d^%d",P->Coefficient, P->Exponent);
        if(P->Next!=NULL)
            printf(" + ");
        P=P->Next;
    }
}


int main( int argc, char **argv )
{
    Polynomial head1,head2,resultPolynomial;
    head1=Create();
    PrintPolynomial(head1);
    head2=Create();
    PrintPolynomial(head2);
    resultPolynomial=PolynomialMulti(head1, head2);
    printf("\n結果是：");
    PrintPolynomial(resultPolynomial);
    return 0;
}
