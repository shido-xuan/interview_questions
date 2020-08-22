package questions;

import java.io.*;

class Textfile {
    public String fileAddress; // 文本文件的地址

    public Textfile(String fileAddress){
        this.fileAddress = fileAddress;
    }

    public void search(char word){
        try{
            // 此处需要设置文件编码，一般为GBK或UTF-8，设置不恰当时无法统计
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.fileAddress), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null){
                sb.append(str.trim());
            }
            String result = sb.toString();
            char[] results = result.toCharArray();  // 以字符数组形式统计
            int count = 0;
            for (char c : results){
                if (word == c){
                    count ++;
                }
                // System.out.print(c);
            }
            // System.out.println();
            System.out.println("【" + word + "】" + "出现的次数为：" + count);

        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

public class question_8 {
    public static void main(String[] args) {
        Textfile textfile = new Textfile("C:\\Users\\Administrator.USER-20190222NF\\IdeaProjects\\interview_questions\\src\\novel\\百妖谱.txt");
        textfile.search('你');
        textfile.search('我');
        textfile.search('他');
    }
}
