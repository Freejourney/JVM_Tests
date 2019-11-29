package part_2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/*
    直接内存容量可通过 -XX:MaxDirectMemorySize指定，如不指定，则大小与Java堆最大值-Xmx相同

    代码直接通过反射获取Unsafe实例并进行内存分配，越过了DirectByteBuffer类
    (使用DirectByteBuffer类也可以分内存抛出异常，但它抛出异常时是通过计算得知的而没有真正向操作系统申请分配内存)
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            // 真正申请内存的方法
            unsafe.allocateMemory(_1MB);
        }
    }
}

/*
Exception in thread "main" java.lang.OutOfMemoryError
	at sun.misc.Unsafe.allocateMemory(Native Method)
	at part_2.DirectMemoryOOM.main(part_2.DirectMemoryOOM.java:14)
 */