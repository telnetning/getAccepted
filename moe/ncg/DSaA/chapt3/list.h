#ifndef _List_H

struct Node;
typedef struct Node *PtrNode;
typedef PtrNode List;
typedef PtrNode Position;

List MakeEmpty( List L );
int IsEmpty( List L );
int IsLast( Position P, List L );
Position Find( ElementType X, List L );
void Delete( ElementType X, List L );
Position FindPrevious( ElementType X, List L );
void Insert( ElementType X, List L, Position P );
void DeleteList( List L );
Position Header( List L );
Position First( List L );
Position Advance( Position P );
ElementType Retrieve( Position P );

#endif

//Node結構在.c文件中實現
/*
struct Node
{
    ElementType Element;
    Position Next;
}
*/

List
MakeEmpty( List L )
{
    L -> Next = NULL;
    return L;
}

int 
IsEmpty( List L )
{
    return L->Next == NULL;
}

int
IsLast( Position P, List L )
{
    return P->Next == NULL;
}

Position
Find( ElementType X, List L )
{
    Position P;
    
    P = L->Next;
    while ( P != NULL && P->Element != X )
        P = P->Next;

    return P;
}

void 
Delete( ElementType X, List L )
{
    Positon P, TmpCell;

    P = FindPrevious( ElementType X, List L );
    if( !IsLast(P, L) )
    {
        TmpCell = P->Next;
        P->Next  = TmpCell->Next;
        free(TmpCell);
    }
}

Position
FindPrevious( ElementType X, List L )
{
    //如果存在X，返回該節點前一個節點
    //如果不存在該節點，返回最後一個節點
    Positon P;

    P = L;
    while ( P->Next->Next != NULL && P->Next->Element != X )
        P = P->Next;

    return P;
}

void 
Insert( ElementType X, List L, Position P )
{
    Positon New;
    New = malloc(sizeof(struct Node ));
    if(New != NULL )
        FatalError("out of space");

    New->Element = X;
    New->Next = P->Next;
    P->Next = New;
}

void
DeleteList( List L )
{
    Position P TmpCell;

    P = L->Next;
    L->Next = NULL;
    while (P!=NULL)
    {
        TmpCell = P->Next;
        free(P);
        P = TmpCell;
        
    }
}

Position
Header( List L )
{
    return L;
}

Position
First( List L )
{
    return L->Next;
}

