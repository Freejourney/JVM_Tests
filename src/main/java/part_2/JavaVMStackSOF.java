package part_2;

/*
    虚拟机栈和本地方法栈溢出：

    线程请求深度大于虚拟机所允许的最大深度：StackOverflowError

    虚拟机在扩展栈时无法申请到足够的内存空间：OutOfMemoryError

    -Xss设置栈容量
 */
public class JavaVMStackSOF {

    private int stackLength = -1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
        JavaVMStackSOF oom = new JavaVMStackSOF();

        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw  e;
        }
    }
}

/*
stack length: 978
Exception in thread "main" java.lang.StackOverflowError
	at part_2.JavaVMStackSOF.stackLeak(part_2.JavaVMStackSOF.java:15)
	at part_2.JavaVMStackSOF.stackLeak(part_2.JavaVMStackSOF.java:16)

	不限于单线程而使用不断建立线程时可以实现内存溢出异常(OutOfMemoryError)：
	    这种情况下每个线程分配的栈内存越大越容易出现内存溢出异常

	    操作系统分配给每个进程的空间有限(32Windows 2GB)
	        虚拟机提供参数来控制Java堆和方法区的两部分的最大值，剩余内存为2GB-Xmx(最大堆容量)-MaxPermSize(最大方法区容量)-程序计数器消耗(几乎可以忽略)

	        虚拟机本身进程耗费内存不算在内，则剩下内存被 虚拟机栈 和 本地方法栈 划分。

	        每个线程分配到的栈容量越大，可建立的线程数量就学校
 */