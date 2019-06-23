package net.cetron.base;

import java.io.*;
import java.net.URL;

public class IOLearn {


    public static void main(String[] args) {
        //文件流：FileInputStream/FileOutputStream， FileReader/FileWriter
        //包装流：PrintStream/PrintWriter/Scanner
        //字符串流：StringReader/StringWriter
        //转换流：InputStreamReader/OutputStreamReader
        //缓冲流：BufferedReader/BufferedWriter ， BufferedInputStream/BufferedOutputStream

//        FileInputStream/FileOutputStream  需要逐个字节处理原始二进制流的时候使用，效率低下
//        FileReader/FileWriter 需要组个字符处理的时候使用
//        StringReader/StringWriter 需要处理字符串的时候，可以将字符串保存为字符数组
//        PrintStream/PrintWriter 用来包装FileOutputStream 对象，方便直接将String字符串写入文件
//        Scanner　用来包装System.in流，很方便地将输入的String字符串转换成需要的数据类型
//        InputStreamReader/OutputStreamReader ,  字节和字符的转换桥梁，在网络通信或者处理键盘输入的时候用
//        BufferedReader/BufferedWriter ， BufferedInputStream/BufferedOutputStream ， 缓冲流用来包装字节流后者字符流，提升IO性能，BufferedReader还可以方便地读取一行，简化编程。

        URL rUrl = IOLearn.class.getClassLoader().getResource("IO.txt");//不同包下,要包名/文件名
        System.out.println(rUrl);

        try {
            File file = new File(rUrl.getPath());
//            System.out.println(f.exists());
            readFile(file);

            //write
            URL wUrl = IOLearn.class.getClassLoader().getResource("IOw.txt");
            System.out.println(wUrl);
            File wFile = new File(wUrl.getPath());
            if (!wFile.exists()){
                wFile.createNewFile();
            }
            write(file, wFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void readFile(File file) throws Exception {
        //节点流，直接操作的文件流，直接与OS进行交互
        FileInputStream fis = new FileInputStream(file);
        byte[] rb = new byte[1000];
        int hasRead = 0;
        while ((hasRead = fis.read(rb)) > 0){
            System.out.println(new String(rb, 0, hasRead));
        }
        fis.close();
        System.out.println("FileInputStream Done!");
        System.out.println();

        char[] chars = new char[1000];
        FileReader fr = new FileReader(file);
        hasRead = 0;
        while ((hasRead = fr.read(chars)) > 0){
            System.out.println(new String(chars, 0, hasRead));
        }
        fr.close();
        System.out.println("FileReader Done!");
    }

    private static void write(File srcFile, File desFile) throws Exception {

        //write
        FileOutputStream fos = new FileOutputStream(desFile);
        FileInputStream fis = new FileInputStream(srcFile);
        byte[] rb = new byte[1000];
        while (fis.read(rb) > 0){
            fos.write(rb);
        }
        fis.close();
        fos.close();

        FileWriter fw = new FileWriter(desFile);
        char[] chars = new char[1000];
        FileReader fr = new FileReader(srcFile);
        while (fr.read(chars) > 0){
            fw.write(chars);
        }
        fr.close();
        fw.close();

        readFile(desFile);
    }

    private static void readBuffer(File file) throws Exception {

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);



        byte[] rb = new byte[1000];
        int hasRead = 0;
        while ((hasRead = fis.read(rb)) > 0){
            System.out.println(new String(rb, 0, hasRead));
        }
        fis.close();
    }

}
