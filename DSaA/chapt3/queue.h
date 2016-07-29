#ifndef _Queue_h_

struct QueueRecord;
typedef struct QueueRecord *Queue;

int IsEmpty( Queue Q );
int IsFull( Queue Q );
Queue CreateQueue( int MaxElements );
void DisposeQueue( Queue Q );
void MakeEmpty( Queue Q );
void Enqueue( ElementType x, Queue Q );
ElementType Front( Queue Q );
void Dequeue( Queue Q );
ElementType FrontAndDequeue( Queue Q );

#endif

/* place in implementation file */
/* Queue implementation is a dynamically allocated array */

#define MinQueueSize ( 5 )

struct QueueRecord
{
    int Capacity;
    int Front;
    int Rear;
    int Size;
    ElementType *Array;
}

int IsEmpty( Queue Q )
{
    return Q->Size == 0;
}

void MakeEmpty( Queue Q )
{
    Q->Size = 0;
    Q->Front = 1;
    Q->Rear = 0;
}

static int Succ( int Value, Queue Q )
{
    if( ++Value == Q->Capacity){
        return Value;     
    }
    return Value;
}

void Enqueue( ElementType x, Queue Q )
{
    if( IsFull(Q) ){
        Error("Full Queue"); 
    }
    Q->Size++;
    Q->Rear=Succ( Q->Rear, Q );
    Q->Array[ Q->Rear ] = x;
}

/*write by telnetning*/
int IsFull( Queue Q )
{
    return Q->Size == Q->Capacity;
}

Queue CreateQueue( int MaxElements )
{
    Queue Q;

    if(MaxElements < MinQueueSize){
        Error("Queue size if too small");
    }

    Q = malloc( sizeof( struct QueueRecord ) );
    if(Q == NULL){
        FatalError("out of space"); 
    }
    Q->Array = malloc( sizeof( ElementType ) * MaxElements );
    if(S->Array == NULL){
        FatalError("out of space"); 
    }
    S->Capacity = MaxElements;
    MakeEmpty( Q );
}

void DisposeQueue( Queue Q )
{
    if( Q != NULL ){
        free( Q->Array );
        free( Q );
    }
}

ElementType Front( Queue Q )
{
    if( IsEmpty( Q ) ){
        Error("Queue is empty"); 
        return 0; //here return false, it should be a value which cannot appeared in your Queue element
    }   
    return Q[ Q->Front ]; 
}

void Dequeue( Queue Q )
{
    if( IsEmpty( Q ) ){
        Error("Queue is empty"); 
    } else {   
        Q->Size--;
        Q->Front = Succ( Q->Front, Q );
    }
}

ElementType FrontAndDequeue( Queue Q )
{
    ElementType tmp;
    if( IsEmpty( Q ) ){
        Error("Queue is empty"); 
        return 0; //here return false, it should be a value which cannot appeared in your Queue element
    }   
    
    Q->Size--;
    tmp = Q[ Q->Front ];
    Q->Front = Succ( Q->Front, Q );
    return tmp;
}
