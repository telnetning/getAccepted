#define ElementType char  //具体实现时候这个定义栈中元素的类型
#ifndef _Stack_h_
    struct StackRecord;
    typedef struct StackRecord *Stack;

    int IsEmpty( Stack S );
    int IsFull( Stack S );
    Stack CreateStack( int MaxElements );
    void DisposeStack( Stack S );
    void MakeEmpty( Stack S );
    ElementType Top( Stack S );
    void Pop( Stack S );
    ElementType TopAndPop( Stack S );
#endif

#include "Error.h"
/*Place in implementation file*/
/*Stack implementation is a dynamically allocated array */
#define EmptyTOS ( -1 )
#define MinStackSize ( 5 )

struct StackRecord
{
    int Capacity;
    int TopOfStack;
  ;  ElementType *Array;
};

Stack CreateStack(int MaxElements)
{
    Stack S;

    if(MaxElements < MinStackSize ){
        Error("Stack size is too small");
    }

    S = malloc( sizeof( struct StackRecord ) );
    if( S == NULL ){
        FatalError("out of space!!!"); 
    }          
    
    S->Array = malloc( sizeof( ElementType ) * MaxElements );
    if(S->Array == NULL){
        FatalError("out of space");  
    }
    S->Capacity = MaxElements;
    MakeEmpty( S );
        
    return S;
}

void DisposeStack( Stack S )
{
    if(S != NULL ){
        free(S->Array);
        free(S);
    }
}

int isEmpty( Stack S )
{
    return S->TopOfStack == EmptyTOS;
}

void MakeEmpty( Stack S )
{
    S->TopOfStack = EmptyTOS;
}

void Push( ElementType x, Stack S)
{
    if(IsFull(S) ){
        Error("Full stack"); 
    } else {
        S->Array[ ++S->TopOfStack ] = x;
    }

}

ElementType Top( Stack S )
{
    if(!IsEmpty( S ) ){
        return S->Array[ S->TopOfStack ]; 
    }
    Error("Empty stack");
    return 0;
}

void Pop( Stack S )
{
    if( IsEmpty( S ) ){
        Error("Empty stack"); 
    } else {
        S->TopOfStack--; 
    }
}

ElementType TopAndPop( Stack S )
{
    if( !IsEmpty( S ) ){
        return S->Array[ S->TopOfStack-- ]; 
    } 
    
    Error("Empty stack");
    return 0;
}

/*write by telnetning*/
int IsFull( Stack S )
{
    return S->TopOfStack == S->Capacity - 1;
}

int IsEmpty( Stack S )
{
    return S->TopOfStack == -1;
}
