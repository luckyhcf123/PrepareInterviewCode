package com.Other;

import java.util.HashMap;

public class LRUCache {

    //链表结点的数据结构
    class DlinkedNode {
        int key;
        int value;
        DlinkedNode prev;
        DlinkedNode next;

        public DlinkedNode() {
        }

        public DlinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    HashMap<Integer, DlinkedNode> hashmap = new HashMap<Integer, DlinkedNode>();
    int capacity;
    int size;
    DlinkedNode head;
    DlinkedNode tail;

    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        //定义头尾两个结点
        head = new DlinkedNode();
        tail = new DlinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        //查找key
        DlinkedNode node = hashmap.get(key);
        if (node == null) {
            return -1;
        }
        //如果不是为空的话就移动到头节点
        moveToHead(node);

        //获取查询到的值
        return node.value;
    }

    private void moveToHead(DlinkedNode node) {
        //在双链表中移动到头部就删除当前结点,插入头节点，
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DlinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DlinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public DlinkedNode removeTail() {
        DlinkedNode nodetail = tail.prev;
        removeNode(nodetail);
        return nodetail;
    }

    public void put(int key, int value) {
        //先查找结点是否已经存在
        DlinkedNode node = hashmap.get(key);
        if (node == null) {
            DlinkedNode node1 = new DlinkedNode(key, value);
            addToHead(node1);
            hashmap.put(key, node1);
            ++size;
            //如果超出了容量，就删除链表尾部的结点
            if (size > capacity) {
                DlinkedNode node2 = removeTail();
                hashmap.remove(node2.key);
                --size;
                }
            } else {
                //如果已经存在了的，就更改结点值
                node.value = value;
                moveToHead(node);
            }
        }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        System.out.println(lruCache.get(1));
       lruCache.put(2,2);
       lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(3));
    }

}

