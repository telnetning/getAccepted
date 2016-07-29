/*兩個鏈表，裏面元素都是升序排列
 * 操作 PrintLot（L， P）打印出L
 * 中那些由P所指定的元素被打印出
 * 來。
 */
#include <stdio.h>
#include <stdlib.h>

struct Node;
typedef struct Node *PtrNode;
typedef PtrNode List;
typedef PtrNode Position;

struct Node {
    int Element;
    PtrNode Next;
};

int PrintLot(L, P)
{
    int PreNum=0;
    Position P2P,P2L;
    P2P=P;
    P2L=L;
    int CurrentNum, step, i;
    int flag=1;
    while( (P2P->Next!=NULL) && flag ){
        CurrentNum=P2P->Next->Element;
        step=CurrentNum-PreNum;
        for(i=1;i<=step;i++){
            if(P2L->Next==NULL){
                flag=0;
                break;
            } else {
                P2L=P2L->Next;
            }  //end if
        }  //end for
        if(flag){
            printf("%d ", P2L->Element);
        }
        PreNum=CurrentNum;
        P2P=P2P->Next;
    }  //end while
}

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

void PrintList(List L)
{
    PtrNode currentNode;
    currentNode=L->Next;
    while(currentNode->Next!=NULL){
        printf("%d ", currentNode->Element);
        currentNode=currentNode->Next;
    }
    printf("%d ", currentNode->Element);
}

int main(int argc, char **argv)
{
    List L, P;
    printf("input infos of List L:\n");
    L=CreateList();
    printf("input infos of list P:\n");
    P=CreateList();
    printf("the List L:");
    PrintList(L);
    printf("\nthe List P");
    PrintList(P);
    printf("\nthe last result:");
    PrintLot(L, P);
    return 0;
}
