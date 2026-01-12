package almanac.cn.huangdayu.almanac.utils

import j2cjlib.utils.parse
import UNSUPPORTED.java.io.BufferedReader
import UNSUPPORTED.java.io.BufferedWriter
import UNSUPPORTED.java.io.FileInputStream
import UNSUPPORTED.java.io.FileOutputStream
import std.io.IOException

/**
 * @author huangdayu at 2024/7/29 create
 */
public class UnicodeUtils {
    public init() { }

    public static func javaMain(args: ?Array<?String>): Unit {
        // 输入文件路径和输出文件路径
        let inputFilePath = "src/main/java/administrative.properties"
        let outputFilePath = "src/main/java/administrative.txt"

        try {
            // 使用UTF-8读取文件内容
            let reader = <-- Missing mapping for java.io.BufferedReader constructor: BufferedReader -->(<-- Missing mapping for java.io.InputStreamReader constructor: InputStreamReader -->(<-- Missing mapping for java.io.FileInputStream constructor: FileInputStream -->(inputFilePath), "UTF-8"))
            // 使用UTF-8写入文件内容
            let writer = <-- Missing mapping for java.io.BufferedWriter constructor: BufferedWriter -->(<-- Missing mapping for java.io.OutputStreamWriter constructor: OutputStreamWriter -->(<-- Missing mapping for java.io.FileOutputStream constructor: FileOutputStream -->(outputFilePath), "UTF-8"))

            // 逐行读取和写入文件内容
            var line: ?String = None
            while ((match (0) { case _ => line = reader.<-- Missing mapping for java.io.BufferedReader member: readLine -->(); line }).isSome()) {
                let s = convertUnicodeToUTF8(line.<-- Missing mapping for java.lang.String member: replaceAll -->(" ", " \\\\u"))
                println(s ?? "null")
                writer.write(s) // 写入当前行
                writer.<-- Missing mapping for java.io.BufferedWriter member: newLine -->() // 换行
            }

            // 关闭读写流
            // Skipping close() method
            // Skipping close() method

            println("文件转换成功！")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    public static func convertUnicodeToUTF8(unicodeString: ?String): ?String {
        let sb = StringBuilder()

        let length = Int32(unicodeString.getOrThrow().size)
        var i = 0i32
        while (i < length) {
            let c = Rune(unicodeString.getOrThrow()[Int64(i)])
            if (c == r'\\' && i + 1i32 < length && Rune(unicodeString.getOrThrow()[Int64(i + 1i32)]) == r'u') {
                // Found a Unicode escape sequence
                let unicodeHex: ?String = unicodeString.getOrThrow()[Int64(i + 2i32)..Int64(i + 6i32)]
                try {
                    // Convert Unicode hex to UTF-8 bytes
                    let codePoint = parse<Int32>(unicodeHex.getOrThrow(), 16i32)
                    let character: String = <-- Missing mapping for java.lang.String constructor: String -->(Rune.<-- Missing mapping for java.lang.Character member: toChars -->(codePoint))
                    let utf8Bytes = character.<-- Missing mapping for java.lang.String member: getBytes -->("UTF-8")
                    sb.append(<-- Missing mapping for java.lang.String constructor: String -->(utf8Bytes, "UTF-8"))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                i += 6i32 // Move past the Unicode escape sequence
            } else {
                sb.append(c)
                i++
            }
        }

        return sb.<-- Missing mapping for java.lang.StringBuilder member: toString -->()
    }
}