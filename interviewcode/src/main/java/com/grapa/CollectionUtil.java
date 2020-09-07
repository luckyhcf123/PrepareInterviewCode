package com.grapa;

import java.util.*;
import java.util.function.Function;

public class CollectionUtil {

    /**
     * 利用function将list集合中的每一个元素转换后形成新的集合返回
     * @param list 要转换的源集合
     * @param function 转换元素的方式
     * @param <T> 源集合的元素类型
     * @param <R> 转换后的元素类型
     * @return
     */

    public static <T, R> List<R> convert(List<T> list, Function<T, R> function) {
        final List<R> result = new ArrayList<>();
        list.forEach(t -> result.add(function.apply(t)));
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1000, 2000, 3000);
        List<String> convert = CollectionUtil.convert(list, t -> Integer.toHexString(t));
//        System.out.println(convert);
//        List<String> upperlist = CollectionUtil.convert(convert, t -> t.toUpperCase());

        List<String> upperlist = CollectionUtil.convert(convert, String::toUpperCase);
//        System.out.println(upperlist);
//        for (int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//        }
//        convert.forEach(s-> System.out.println(s));
//        upperlist.forEach(s-> System.out.println(s));

        Iterator<String> iterator = upperlist.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        List<Date> dateList = CollectionUtil.convert(list, i -> new Date(i));
        dateList.forEach(System.out::println);



    }

}
