package com.tryingpfq.stack;

import java.sql.SQLOutput;

public class BaseLinkStack {
    private Node top = null;


    /**
     * 入栈
     * @param value
     */
    public void push(String value) {
        Node newNode = Node.valueOf(value, null);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * 出栈
     * @return
     */
    public String pop() {
        if (top == null) {
            return null;
        }
        String value = top.data;
        top = top.next;
        return value;
    }

    public void printAll(){
        Node p = top;
        while (p != null) {
            System.out.println(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    public static class Node{
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public static Node valueOf(String data, Node next) {
            return new Node(data, next);
        }

        public String getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }
    }
}
