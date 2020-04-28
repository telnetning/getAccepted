#include <stdio.h>
#include <stdlib.h>

struct Node;
typedef struct Node *PtrNode;
typedef PtrNode List;
typedef PtrNode Poisition;

struct Node {
    int Element;
    PtrNode Next;
};

List CreateList()
{
    int n,i;
    PtrNode newNode, currentNode, head; 
    head=malloc(sizeof(struct Node));
    printf("input n:");
    scanf("%d", &n);
    printf("input the num in order:");
    i=1;
    while(i<=n){
       if(i==1){
        newNode=malloc(sizeof(struct Node));
        scanf("%d", &newNode->Element);
        head->Next=newNode;
        currentNode=newNode;
       } else {
        newNode=malloc(sizeof(struct Node));
        scanf("%d", &newNode->Element);
        currentNode->Next=newNode;
        currentNode=currentNode->Next;
       } 
       i++;
    }
    return head; 
}

List ReaverList(List head)
{
    PtrNode p1, p2, p3;
    if(head->Next){
        p1=head->Next;
    } else {
        //List沒有節點
        return head;
    }

    if(p1->Next){
        p2=p1->Next;
        p1->Next=NULL;//將第一個節點指向空
    } else {
        //List只有一個節點
        return head;
    }

    if(p2->Next){
        p3=p2->Next;
    } else {
        //List只有兩個節點
        head->Next=p2;
        p2->Next=p1;
        p1->Next=NULL;
        return head;
    }

    while(p3){
        p2->Next=p1;
        p1=p2;
        p2=p3;
        p3=p3->Next;
    }
    p2->Next=p1;
    head->Next=p2;
    return head; 
}

void PrintList(List head)
{
    PtrNode P;
    P=head->Next;
    while(P){
        printf("%d ", P->Element);
        P=P->Next;
    }
}

int main(int argc, char **argv)
{
    List head;
    head=CreateList();
    printf("Before Reaver:");
    PrintList(head);
    head=ReaverList(head);
    printf("After Reaver:");
    PrintList(head);
    return 0; 
}
