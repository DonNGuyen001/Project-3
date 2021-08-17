# Project3_Summer2021
/*private Node front,end;
private int currentSize;

    //structure a node contain data:
    public class Node {
        Monster data;
        Node next;
    }
    //construct a queue
    public Queue(){
        front = null;
        end = null;
        currentSize = 0;
    }

    // enqueue item
    public void enqueue(Monster data){
        Node previous_end = end;
        end.data = data;
        end.next = null;
        if(isEmpty()){
            front = end;
        }
        else{
            previous_end.next = end;
        }
        currentSize ++;
    }

    //remove item from queue
    public Monster dequeue(){
        Monster data = front.data;
        front = front.next;
        if(isEmpty()){
            end = null;
        }
        currentSize --;
        return data;
    }

    //check if any queue is empty
    public boolean isEmpty(){
        return currentSize == 0;
    }*/