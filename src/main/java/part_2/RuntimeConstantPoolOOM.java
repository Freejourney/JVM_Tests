package part_2;

import java.util.ArrayList;
import java.util.List;

/*
    -XX:PermSize 和 -XX:MaxPermSize限制方法区大小，间接限制运行时常量池容量

    String.intern()方法作用：
        如果运行时常量池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象；
        否则，将此String对象包含的字符串添加到运行时常量池中，并且返回该String对象的引用。
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
