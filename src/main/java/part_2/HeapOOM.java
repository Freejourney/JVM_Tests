package part_2;

import java.util.ArrayList;
import java.util.List;
/*
    将堆的最大最小值设置相同可避免堆自动扩展，限制Java堆的大小为20MB
    -XX:+HeapDumpOnOutOfMemoryError可以让虚拟机出现内存溢出异常时Dump出当前内存堆转储快照

    -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}

/*

Java堆内存溢出结果：
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid1196.hprof ...
Heap dump file created [28275803 bytes in 0.276 secs]
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at java.util.Arrays.copyOf(Arrays.java:3210)
	at java.util.Arrays.copyOf(Arrays.java:3181)
	at java.util.ArrayList.grow(ArrayList.java:261)
	at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
	at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
	at java.util.ArrayList.add(ArrayList.java:458)
	at part_2.HeapOOM.main(part_2.HeapOOM.java:19)


内存溢出问题可以利用内存分析工具进行检测解决错误原因：
    Idea中的JProfiler工具
 */