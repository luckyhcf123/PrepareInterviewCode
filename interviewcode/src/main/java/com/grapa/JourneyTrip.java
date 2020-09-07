package com.grapa;

import java.util.HashMap;
import java.util.Map;

public class JourneyTrip {

    private static  void tripPrint( Map<String,String> input){
        //1.声明反转的hashmap
       Map<String,String> reverseInput=new HashMap<String,String>();
       //2.对入参进行反转，获取反转的hashmap
        for (Map.Entry<String,String> entry:input.entrySet()){
            reverseInput.put(entry.getValue(),entry.getKey());
        }

        //3.对于输入的参数的key看是不是在反转后的hashmap中存在，不存在就是起点，
        String  start=null;
        for (Map.Entry<String,String> inputentry:input.entrySet()){
           if(!reverseInput.containsKey(inputentry.getKey())){
                start=inputentry.getKey();
                break;
           }
        }

        //4.然后依据起点遍历输入参数就是找到的路径
        if (start==null) {
            System.out.println("参数输入不合法");
            return;
        }

        String to=null;
        to=input.get(start);
        System.out.println(start+"--->"+to);
        start=to;
        to=input.get(to);
        while (to !=null){
            System.out.println(","+start+"--->"+to);
            start=to; start=to;
            to=input.get(to);
            }
        }

    public static void main(String[] args) {
      Map<String,String> input=new HashMap<String,String>();
       input.put("西安","成都");
       input.put("北京","上海");
       input.put("大连","西安");
       input.put("上海","大连");

       tripPrint(input);



    }





}
