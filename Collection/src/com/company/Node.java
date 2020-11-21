package com.company;

class Node<Item> {
    Node<Item> next;
    Node<Item> prev;
    Item data;
    public Node(Node<Item> next,Node<Item> prev,Item data){
        this.next=next;
        this.data=data;
        this.prev=prev;
    }

    public Node(Item data){
        this.next=null;
        this.data=data;
        this.prev=null;
    }

}
