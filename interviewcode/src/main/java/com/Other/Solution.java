package com.Other;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;


/**解题思路简单描述：

        1.使用一个ConcurrentHashMap来存储已知的所有URL，主线程向其添加起始URL，多个工作线程可以并发添加已抓取的、符合规则要求的URL。
        2.使用一个LinkedList来保存结果URL，使用对应的ReentrantLock来同步多线程访问，结果只增不减。
        3.使用一个LinkedList来保存待抓取的URL，使用对应的ReentrantLock来同步多线程访问。主线程添加起始URL，工作线程从该List中取出要开始抓取的URL，
          抓取后将符合规范的URL添加至该链表及结果URL链表。
        4.主循环的退出条件：（1）待抓取URL链表中无元素；（2）已无工作线程（所有的工作线程已完成工作）。
        5.需要说明的是，如果待抓取的URL集合很大，那么创建的工作线程会偏多，在生产实践中可以使用线程池来限制并发度。

 给你一个初始地址 startUrl 和一个 HTML 解析器接口 HtmlParser，请你实现一个 多线程的网页爬虫，用于获取与 startUrl 有 相同主机名 的所有链接。 

 以 任意 顺序返回爬虫获取的路径。

 爬虫应该遵循：

 从 startUrl 开始
 调用 HtmlParser.getUrls(url) 从指定网页路径获得的所有路径。
 不要抓取相同的链接两次。
 仅浏览与 startUrl 相同主机名 的链接。

\\
 输入：
 urls = [
   "http://news.yahoo.com",
   "http://news.yahoo.com/news",
   "http://news.yahoo.com/news/topics/",
   "http://news.google.com",
   "http://news.yahoo.com/us"
 ]
 edges = [[2,0],[2,1],[3,2],[3,1],[0,4]]
 startUrl = "http://news.yahoo.com/news/topics/"
 输出：[
   "http://news.yahoo.com",
   "http://news.yahoo.com/news",
   "http://news.yahoo.com/news/topics/",
   "http://news.yahoo.com/us"
 ]

 */

public class Solution {
    /**
     * // This is the HtmlParser's API interface.
     * // You should not implement it, or speculate about its implementation
     * interface HtmlParser {
     *     public List<String> getUrls(String url) {}
     * }
     */
        // 已知URL集合，存储当前可见的所有URL。
        private ConcurrentHashMap<String, Boolean> totalUrls = new ConcurrentHashMap<>();

        // 结果URL链表及对应锁。
        private ReentrantLock resultLock = new ReentrantLock();
        private LinkedList<String> resultUrls = new LinkedList<>();

        // 待抓取URL链表及对应锁。
        private ReentrantLock crawlLock = new ReentrantLock();
        private LinkedList<String> urlsToCrawl = new LinkedList<>();

        // 当前正在执行的工作线程个数。
        private AtomicInteger choreCount = new AtomicInteger(0);

        public List<String> crawl(String startUrl, HtmlParser htmlParser) {
            String hostName = extractHostName(startUrl);

            this.totalUrls.put(startUrl, true);

            addUrlToResult(startUrl);
            addUrlToCrawl(startUrl);

            while (true) {
                String urlToCrawl = fetchUrlToCrawl();
                if (urlToCrawl != null) {
                    incrChore();
                    Chore chore = new Chore(this, hostName, htmlParser, urlToCrawl);
                    (new Thread(chore)).start();
                } else {
                    if (this.choreCount.get() == 0) {
                        break;
                    }
                    LockSupport.parkNanos(1L);
                }
            }

            return fetchResultUrls();
        }

        private String extractHostName(String url) {
            // HTTP protocol only.
            String processedUrl = url.substring(7);

            int index = processedUrl.indexOf("/");
            if (index == -1) {
                return processedUrl;
            } else {
                return processedUrl.substring(0, index);
            }
        }

        private class Chore implements Runnable {
            private Solution solution;
            private String hostName;
            private HtmlParser htmlParser;
            private String urlToCrawl;

            public Chore(Solution solution, String hostName, HtmlParser htmlParser, String urlToCrawl) {
                this.solution = solution;
                this.hostName = hostName;
                this.htmlParser = htmlParser;
                this.urlToCrawl = urlToCrawl;
            }

            @Override
            public void run() {
                try {
                    filterUrls(this.htmlParser.getUrls(urlToCrawl));
                } finally {
                    this.solution.decrChore();
                }
            }

            private void filterUrls(List<String> crawledUrls) {
                if (crawledUrls == null || crawledUrls.isEmpty()) {
                    return;
                }

                for (String url : crawledUrls) {
                    // 如果该URL在已知的URL集合中已存在，那么不需要再重复抓取。
                    if (this.solution.totalUrls.containsKey(url)) {
                        continue;
                    }

                    this.solution.totalUrls.put(url, true);

                    String crawlHostName = this.solution.extractHostName(url);
                    if (!crawlHostName.equals(this.hostName)) {
                        // 如果抓取的URL对应的HostName同Start URL对应的HostName不同，那么直接丢弃该URL。
                        continue;
                    }

                    // 将该URL添加至结果链表。
                    this.solution.addUrlToResult(url);
                    // 将该URL添加至待抓取链表，以便进行下一跳抓取。
                    this.solution.addUrlToCrawl(url);
                }
            }
        }

        private void addUrlToResult(String url) {
            this.resultLock.lock();
            try {
                this.resultUrls.add(url);
            } finally {
                this.resultLock.unlock();
            }
        }

        private List<String> fetchResultUrls() {
            this.resultLock.lock();
            try {
                return this.resultUrls;
            } finally {
                this.resultLock.unlock();
            }
        }

        private void addUrlToCrawl(String url) {
            this.crawlLock.lock();
            try {
                this.urlsToCrawl.add(url);
            } finally {
                this.crawlLock.unlock();
            }
        }

        private String fetchUrlToCrawl() {
            this.crawlLock.lock();
            try {
                return this.urlsToCrawl.poll();
            } finally {
                this.crawlLock.unlock();
            }
        }

        private void incrChore() {
            this.choreCount.incrementAndGet();
        }

        private void decrChore() {
            this.choreCount.decrementAndGet();
        }
}
