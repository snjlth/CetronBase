package net.cetron.base;

import java.util.Arrays;
import java.util.StringTokenizer;

public class StringLearn {

    public static void main(String[] args) {
        //String在substring，decode方法中使用到了offset偏移量，为arraycopy，indexof做些准备
//        String s = "adfasdfasdf";
//        System.out.println( s.substring(3));

        testStringTokenizer();

        //分割字符串，StringTokenizer的效率要高于Split方法，优先使用StringTokenizer
        //如果有条件，能直接使用indexof更好，但是要注意使用的其他方法，在本测试中，使用到了substring方法，这个方法性能很慢
    }

    private static void testStringTokenizer(){

//        String t = "asdfasdf,asdfasdf,sdeee,faset,ewerqw,afasdf,123423,dfa,da,re";
//        StringTokenizer s = new StringTokenizer(t);
//        String[] r = t.split(",");
//        System.out.println(Arrays.toString(r));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append(Math.random()+i);
            sb.append(",");
        }

        String str = sb.toString();
        long s1 = System.currentTimeMillis();
        str(str);//583ms
        System.out.println(System.currentTimeMillis() - s1);//583ms

        long s2 = System.currentTimeMillis();
        strToken(str);//304ms
        System.out.println(System.currentTimeMillis() - s2);//304ms

        long s3 = System.currentTimeMillis();
        indexof(str);//304ms
        System.out.println(System.currentTimeMillis() - s3);//out of time


        int num = 1000;
        s1 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            str(str);
        }
        System.out.println(System.currentTimeMillis()-s1);

        s2 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            strToken(str);
        }
        System.out.println(System.currentTimeMillis()-s2);

        s3 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            indexof(str);
        }
        System.out.println(System.currentTimeMillis()-s3);

    }

    private static void strToken(String str) {
        StringTokenizer st = new StringTokenizer(str, ",");
        while (st.hasMoreTokens()){
//            System.out.println(st.nextToken());
            st.nextToken();

        }
    }

    private static void str(String str) {
        String[] rs = str.split(",");
        for (int i = 0; i < rs.length; i++) {
            String temp = rs[i];
        }
    }

    private static void indexof(String str){
        String temp = str;
        while(true){
            int index = temp.indexOf(",");
            if(index < 0) break;
            String t = temp.substring(0, index);
            temp = temp.substring(index+1);
        }
        temp = str;
    }
}
