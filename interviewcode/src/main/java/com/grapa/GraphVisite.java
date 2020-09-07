package com.grapa;

import java.util.LinkedList;

public class GraphVisite {

    private  int size;
    private  Vertex[] vertexes;
    private LinkedList<Integer> adj[];

    public GraphVisite(int size) {
        this.size=size;
        //初始化顶点和邻接矩阵
        vertexes=new Vertex[size];
        adj=new LinkedList[size];
        for(int i=0;i<size;i++){
            vertexes[i]=new Vertex(i);
            adj[i]=new LinkedList();
        }
    }


    public static  void dfs(GraphVisite graph, int start, boolean[] visited){
        System.out.println(graph.vertexes[start].data);
        visited[start]=true;
        for (int index : graph.adj[start]) {
            if(!visited[index]){
                dfs(graph,index,visited);
            }
        }


    }


    public static  void  bfs(GraphVisite graph, int start, boolean[] visited, LinkedList<Integer> queue){
        queue.offer(start);
        while(!queue.isEmpty()){
            int front=queue.poll();
            if(visited[front]){
                continue;
            }
            System.out.println(graph.vertexes[front].data);
            visited[front]=true;
            for (Integer integer : graph.adj[front]) {
                queue.offer(integer);
            }
        }
    }



    public  static  void main(String[] args){
        GraphVisite graph=new GraphVisite(6);
        graph.adj[0].add(1);
        graph.adj[0].add(2);
        graph.adj[0].add(3);

        graph.adj[1].add(3);
        graph.adj[1].add(4);

        graph.adj[2].add(0);

        graph.adj[3].add(0);
        graph.adj[3].add(1);
        graph.adj[3].add(4);
        graph.adj[3].add(5);

        graph.adj[4].add(1);
        graph.adj[4].add(3);
        graph.adj[4].add(5);

        graph.adj[5].add(3);
        graph.adj[5].add(4);

        System.out.println("图的深度优先遍历");
        dfs(graph,0,new boolean[graph.size]);
        System.out.println("图的广度优先遍历");
        bfs(graph,0,new boolean[graph.size],new LinkedList<Integer>());

    }





}