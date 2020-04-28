#include "bstree.h"

//将一个tree置空
SearchTree MakeEmpty( SearchTree T )
{
    if( T != NULL ){
        MakeEmpty( T->Left );
        MakeEmpty( T->Right );
        free(T);
    }    
    return NULL;
}

//在树中查找一个元素X
Position Find( ElementType X, SearchTree T )
{
    if( T == NULL )
        return NULL;

    if( X < T->Element )
        return Find( X, T->Left );
    else
    if( X > T-> T->Element )
        return Find( X, T->Right );
    else 
        return T;
}

Position FindMin( SearchTree T )
{
    if( T != NULL ){
        while( T->Left != NULL ){
            T = T->Left; 
        }
    }

    return T;
}

Position FindMax( SearchTree T )
{
    if( T != NULL ){
        while( T->Right != NULL ){
            T = T->Right; 
        }
    }

    return T;
}

//插入操作
SearchTree Insert( ElementType X, SearchTree T )
{
    if( T == NULL){
        T = malloc( sizeof( struct TreeNode ) );
        if( T == NULL){
            printf("Out of space!!!"); 
        } else {
            T->Element = X;
            T->Left = T->Right = NULL;
        }
    }
    else 
    {
        if( X < T->Element ){
            T->Left = Insert( X, T->Left ); 
        } 
        else 
            if( X > T->Right ){
                T->Right = Insert(x, T->Right );     
            }

        return T;
    }
}
