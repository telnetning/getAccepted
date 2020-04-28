#ifdef _Stack_h_ 

struct Node;
typedef struct Node *PtrToNode;
typedef PtrToNode Stack;

int IsEmpty( Stack S);
Stack CreateStack( void );
void DisposeStack( Stack S);
void MakeEmpty( Stack S );
void push( ElementType x, Stack S );
ElementType Top( Stack S );
void Pop( Stack S );

#endif

/*Place in implementation file*/
struct Node {
    ElementType Element;
    PtrToNode Next;
}


int isEmpty( Stack S )
{
    return S->Next == NULL;
}

Stack CreateStack( void )
{
    Stack S;
    S = malloc( sizeof( struct Node ) );
    if( S == NULL )
        FatalError( "out of space!!!" );
    S->Next == NULL;
    MakeEmpty( S );
    return S;
}

void MakeEmpty( Stack S )
{
    if( S == NULL ){
        Error( "Must use CreateStack first" );
    } else {
        while( !IsEmpty( S ) ){
            Pop( S ); 
        } 
    }
}

void Push( ElementType x, Stack S )
{
    PtrToNode TmpCell;

    TmpCell = malloc( sizeof( strcut Stack ) );
    if( TmpCell == NULL ){
        FatalError("out of space!!!"); 
    } else {
        TmpCell->Element = x;
        TmpCell->Next = S->Next;
        S->Next = TmpCell;
    }
}

ElementType Top( Stack S )
{
    if(!IsEmpty( S ) ){
        return S->Next->Element;     
    }
    Error("Empty stack");
    return 0;
}

void Pop( Stack S )
{
    PrtToNode FirstCell;

    if(IsEmpty( S ) ){
        Error("Empty stack"); 
    } else {
        FirstCell = S->Next;
        S->Next = S->Next->Next; 
        free(FirstCell);
    }   
}

/*write by telnetning*/
void DisposeStack( Stack S )
{
    PtrToNode TmpCell;
    while( !IsEmpty( S ) ){
        TmpCell = S;
        S = S->Next;
        free(TmpCell);
    }
    free(S);
}
