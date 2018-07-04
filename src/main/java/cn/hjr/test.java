package cn.hjr;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.StringTokenizer;

//[{"bypass":0,"appid":"5002","uid":10000,"system":"","detail":"","time":"2018-05-11 17:00:00:000","user":"wei","data":0,"biznamekey":"password-lgn","biztypekey":"login","bizname":"","biztype":"","appver":"1.0.0","appname":"huya-zhibao","ip":"127.0.0.1","deviceid":"Xiao Mi","devicename":"","termtype":0,"isuserlog":false,"servip":"127.0.0.1"}]
public class test{
    public static void main(String[] args) {
        try {
            String str;
            int index=0,count=0;
            StringBuilder stringBuilder=new StringBuilder();
            ObjectMapper jsonMapper=new ObjectMapper();
            BufferedReader bufferedReader=new BufferedReader(new FileReader("g:\\lgndata\\登录成功.csv"));
            while ( (str=bufferedReader.readLine())!=null){

                String[] strings=str.split(",");
//                try{
//                    if(strings[1].equalsIgnoreCase("0")){
//                        index++;
//                    }else {
//                        count++;
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                    System.out.println(strings[0]);
//                }

                if(strings[0].length()>=22){
                    stringBuilder.append(strings[0]).append("\n");
                    count++;
                }else {
                    index++;
                }
            }
            System.out.println("user异常"+count);
            System.out.println("过滤"+index);
            System.out.println("登录成功总量"+(count+index));
//            FileOutputStream stream=new FileOutputStream(new File("g:\\lgndata\\existflit.csv"));
//            BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(stream);
//            bufferedOutputStream.write(stringBuilder.toString().getBytes());
//            bufferedOutputStream.flush();
//            bufferedOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





}
