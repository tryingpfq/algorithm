package com.tryingpfq.stack;

import com.sun.deploy.panel.NodeBorder;
import com.tryingpfq.stack.BaseLinkStack.Node;

import javax.net.ssl.HostnameVerifier;

/**
 * 浏览器前进后退
 * 参考王争老师
 */
public class SimpleBrowser {

    private String currentPage;
    private LinkedListBaseStack backStack;
    private LinkedListBaseStack forwardStack;

    public SimpleBrowser() {
        this.backStack = new LinkedListBaseStack();
        this.forwardStack = new LinkedListBaseStack();
    }

    public static void main(String[] args) {
        SimpleBrowser browser = new SimpleBrowser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }

    public void open(String url) {
        if (this.currentPage != null) {
            this.backStack.push(this.currentPage);
            this.forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack() {
        return this.backStack.size() > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.size() > 0;
    }

    public String goBack() {
        if (this.canGoBack()) {
            this.forwardStack.push(this.currentPage);
            String backUrl = this.backStack.pop();
            showUrl(backUrl, "Back");
            return backUrl;
        }

        System.out.println("* Cannot go back, no pages behind.");
        return null;
    }

    public String goForward() {
        if (this.canGoForward()) {
            this.backStack.push(this.currentPage);
            String forwardUrl = this.forwardStack.pop();
            showUrl(forwardUrl, "Foward");
            return forwardUrl;
        }

        System.out.println("** Cannot go forward, no pages ahead.");
        return null;
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public static class LinkedListBaseStack{
        private int size;

        private Node top;

        static Node createNode(String data, Node next) {
            return new Node(data, next);
        }
        public void clear(){
            this.top = null;
            this.size = 0;
        }

        public void push(String data) {
            Node node = createNode(data, top);
            this.top = node;
            size++;
        }

        public String pop() {
            Node  popNode = this.top;
            if (popNode == null) {
                System.out.println("Stack is empty");
                return null;
            }
            this.top = top.getNext();
            if (this.size > 0) {
                this.size--;
            }
            return popNode.getData();
        }

        public String getTopData() {
            if (this.top == null) {
                return null;
            }
            return this.top.getData();
        }

        public int size() {
            return this.size;
        }

        public void print() {
            System.out.println("Print stack:");
            Node currentNode = this.top;
            while (currentNode != null) {
                String data = currentNode.getData();
                System.out.print(data + "\t");
                currentNode = currentNode.getNext();
            }
            System.out.println();
        }
    }


}
