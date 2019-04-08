package com.tryingpfq.linkedlist.base;

/**
 * @Author Tryingpfq
 * @Time 2019/4/8 22:27
 * 单链表的基本操作（插入 删除 查找 回文 反转）
 * the data of Node is int type
 */
public class BaseLinkedList {

    private Node head = null;   // head

    public Node findByValue(int value){
        Node p = head;
        while(p != null && p.data != value){
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index){
        Node p = head;
        int pos = 0;
        while(p != null && pos != index){
            pos ++;
            p = p.next;
        }
        return p;
    }


    /**
     * insert into the head
     * @param value
     */
    public void insertToHead(int value){
        Node newNode = createNode(value);
        insertToHead(newNode);
    }

    public void insertToHead(Node node){
        if(head == null){
            head = node;
        }else{
            node.next = head;
            head = node;
        }
    }

    public void insertToTail(int value){
        Node newNode = createNode(value);
        if(head == null){
            head = newNode;
        }else{
            Node p = head.next;
            while(p != null){
                p = p.next;
            }
            p.next = newNode;
        }
    }

    /**
     * insert one node to after node p
     * @param p
     * @param value
     */
    public void insertAfterNode(Node p,int value){
        Node addNode = createNode(value);
        if(p == null){
            return;
        }
        Node q = p.next;
        p.next = addNode;
        addNode.next = q;
    }

    /**
     * insert one node before node p
     * @param p
     * @param value
     */
    public void insertBeforeNode(Node p,int value){
        if(p == null){
            return;
        }
        Node addNode = createNode(value);
        if(head == p){
            insertToHead(addNode);
            return;
        }

        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }

        addNode.next = p;
        q.next = addNode;

    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) return;

        if (p == head) {
            head = head.next;
            return;
        }

        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }

        q.next = q.next.next;
    }

    public void deleteByValue(int value) {
        if (head == null) return;

        Node p = head;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }

        if (p == null) return;

        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * judge two node link all data value is same
     * @param a
     * @param b
     * @return
     */
    public boolean isSameLinked(Node a,Node b){
        Node p = a,q = b;
        System.out.println("p " + p.data);
        System.out.println("q " + q.data);
        while(p != null && q != null){
            if (p.data == q.data) {
                p = p.next;
                q = q.next;
                continue;
            }else{
                break;
            }
        }
        if (p == null && q == null) {
            System.out.println("node a same with node b");
            return true;
        }else{
            return false;
        }
    }

    /**
     * linklist reseve
     * @param head
     * @return
     */
    public Node inserveLinkList(Node head){
        Node r = head;
        Node p = r.next;
        Node next = null;
        if(p == null){
            return head;
        }
        while(p != null){
            next = p.next;
            p.next = r;
            r = p;

            p = next;
        }
        return r;
    }

    //无头结点的链表翻转 (王争老师代码)
    public Node inverseLinkList(Node p){

        Node pre = null;
        Node r = head;
        System.out.println("z---" + r.data);
        Node next= null;
        while(r !=p){
            next = r.next;

            r.next = pre;
            pre = r;
            r = next;
        }

        r.next = pre;
        //　返回左半部分的中点之前的那个节点
        //　从此处开始同步像两边比较
        return r;

    }

    /**
     * reserve with head_node
     * @param p
     * @return
     */
    public Node inverseLinkList_head(Node p){
        Node Head = new Node(10000,null);
        Head.next = p;

        Node Cur = p.next;
        p.next = null;
        Node next = null;

        while(Cur != null){
            next = Cur.next;
            Cur.next = Head.next;
            Head.next = Cur;
            System.out.println("first " + Head.data);

            Cur = next;
        }

        return Head;

    }


    /**
     * 字符串回文算法  比如 abcdefedcba
     * @return
     */
    public boolean palindrome(){
        if (head == null){
            return false;
        }else{
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            if (p.next == null){
                System.out.println("只有一个元素");
                return true;
            }
            while( q.next != null && q.next.next != null){
                p = p.next;
                q = q.next.next;

            }

            System.out.println("中间节点" + p.data);
            System.out.println("开始执行奇数节点的回文判断");
            Node leftLink = null;
            Node rightLink = null;
            if(q.next == null){
                //　p 一定为整个链表的中点，且节点数目为奇数
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
                System.out.println("左边第一个节点"+leftLink.data);
                System.out.println("右边第一个节点"+rightLink.data);

            }else{
                //p q　均为中点
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return isSameLinked(leftLink, rightLink);
        }
    }

    // Node
    public static class Node{
        private int data;

        private Node next;

        public Node(int data,Node next){
            this.data = data;
            this.next = next;
        }

        public int getData(){
            return  data;
        }
    }

    public static Node createNode(int value){
        return new Node(value,null);
    }



}
