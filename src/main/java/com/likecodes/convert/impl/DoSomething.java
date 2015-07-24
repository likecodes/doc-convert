package com.likecodes.convert.impl;

import java.io.IOException;
import java.util.Date;

/**
 * 
 * 测试并发类
 *Created by leo on 2015/7/23.
 */
public class DoSomething extends Thread {
    /**
     * 实现Runnable接口的类
     *
     * @author leizhimin 2008-9-13 18:12:10
     */
        private String url;
        private String path;

        public DoSomething(String url,String path) {
            this.url = url;
            this.path=path;
        }

        public void run() {
            System.out.println("---thread start---------"+path);
            long stime =new Date().getTime();
            WKHTML2PDFWraper wkhtml=new WKHTML2PDFWraper();
            try {
                wkhtml.getPDFByURL(url);
//                wkhtml.saveAs(path, wkhtml.getPDFByURL(url));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long s=new Date().getTime()-stime;
            System.out.println("---thread end---------"+path+" --startTime-"+stime+"--costTime-"+s);
        }
    }

