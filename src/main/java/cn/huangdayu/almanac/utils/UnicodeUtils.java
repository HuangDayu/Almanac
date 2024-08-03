package cn.huangdayu.almanac.utils;

import java.io.*;

/**
 * @author huangdayu at 2024/7/29 create
 */
public class UnicodeUtils {

    public static void main(String[] args) {
        // 输入文件路径和输出文件路径
        String inputFilePath = "src/main/java/administrative.properties";
        String outputFilePath = "src/main/java/administrative.txt";

        try {
            // 使用UTF-8读取文件内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilePath), "UTF-8"));
            // 使用UTF-8写入文件内容
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilePath), "UTF-8"));

            // 逐行读取和写入文件内容
            String line;
            while ((line = reader.readLine()) != null) {
                String s = convertUnicodeToUTF8(line.replaceAll(" ", " \\\\u"));
                System.out.println(s);
                writer.write(s);  // 写入当前行
                writer.newLine();   // 换行
            }

            // 关闭读写流
            reader.close();
            writer.close();

            System.out.println("文件转换成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertUnicodeToUTF8(String unicodeString) {
        StringBuilder sb = new StringBuilder();

        int length = unicodeString.length();
        int i = 0;
        while (i < length) {
            char c = unicodeString.charAt(i);
            if (c == '\\' && i + 1 < length && unicodeString.charAt(i + 1) == 'u') {
                // Found a Unicode escape sequence
                String unicodeHex = unicodeString.substring(i + 2, i + 6);
                try {
                    // Convert Unicode hex to UTF-8 bytes
                    int codePoint = Integer.parseInt(unicodeHex, 16);
                    String character = new String(Character.toChars(codePoint));
                    byte[] utf8Bytes = character.getBytes("UTF-8");
                    sb.append(new String(utf8Bytes, "UTF-8"));
                } catch (NumberFormatException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                i += 6; // Move past the Unicode escape sequence
            } else {
                sb.append(c);
                i++;
            }
        }

        return sb.toString();
    }

}
