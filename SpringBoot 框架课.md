[TOC]



# SpringBoot 框架

**我的端口号**：

```
backend(web服务器):3002     
matchingsystem(匹配系统微服务服务器):3001
botrunningsystem(bot代码执行服务器):3003
```

截图

<img src="./SpringBoot 框架课.assets/image-20240821215856339.png" alt="image-20240821215856339" style="zoom:50%;" /> 



**y 总的端口号**





关于加括号和不加括号

```
export default  因为只导出一个、可以不加括号（任意取名即可）
import ContentField from "@/components/ContenField.vue"

export					可以导出多个、必须加括号命名一致
import { ref } from 'vue'
```



## idea 操作

重新运行

```
control + R
```



注释快捷键

```
shift + command + /
```



idea 和 vs 全文搜索

```
command + shift + f
```



导入类/实现接口

```
option + ↩
```



复制并粘贴当前选中的行/块

```
⌘ + D
```



开始新的一行

```
⇧ + ↩
```



## Bootstap



**grids布局：整体的页面布局**

```
把页面分为 12 列，多行
```



**modal模态框：点击弹出一个交互式界面**



**forms 表单：提交信息的表单**



**tables列表：列表展示信息**







## 零：相关基础

### 1数据类型&Scanner

Scanner 属于引用数据类型——引用数据类型与定义基本数据类型变量不同，引用数据类型的变量定义及赋值有相对固定的步骤或格式：

```java
import java.util.Scanner;
数据类型	变量名 = new 数据类型（）；
Scanner   sc  =  new  Scanner(System.in);
//每种引用数据类型都有其功能，可以调用该类型实例的功能。
变量名.方法名();
sc.nextInt();
```









- 数据类型分为三种
  - 内置数据类型：byte、short、int、long...
  - 引用数据类型：类似 c/c++中的变量——引用类型指向一个对象，变量声明时被指定一个特定类型，声明后变量类型不能改变
    - eg：对象&数组
    - 引用类型默认值都是 null
  - 常量：类似 const（常量名通常大写）
    - final double PI = 3.1415726;



```java
package com.godice;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        byte b = sc.nextByte();//1Byte有符号整数
        short s = sc.nextShort();//2Byte有符号整数
        int i = sc.nextInt();//4Byte有符号整数
        long l = sc.nextLong();//8Byte有符号整数
        float f = sc.nextFloat();//4Byte浮点数
        double d = sc.nextDouble();//8Byte浮点数
        boolean bool = sc.nextBoolean();//布尔值
        char c = (char) sc.nextInt();//2Byte URL 编码字符
        String str = sc.nextLine();//字符串和字符数组，java 中不是一个东西
        //初始化定义
        long l1 = 64646455564656L;//long 定义需要末尾加上L
        float f1 = 11.312F;//浮点数定义需要加上F,否则默认定义为 double
        double d1 = 1231.123123D;//double 需要加上D
        boolean bool1 = true;//boolean值定义只能为 true/false，不能为数字
        char c1 = 'a';
        char c2 = (char) 123;
        char c3 = '&';//char可以存储任何字符
        String str1 = "Hello World";
        System.out.println(b);
        System.out.println(s);
    }
}
```



**默认值**

<img src="./SpringBoot 框架课.assets/image-20240416163703195.png" alt="image-20240416163703195" style="zoom:67%;" /> 



















## 1java 语法







![image-20240412154947484](./SpringBoot 框架课.assets/image-20240412154947484.png)

### JDK、JRE、JVM

- jdk 包含 jre、jre 包含 jvm
  - jdk：java 开发工具包
    - jdk = jre + 常用编译&调试工具
  - jre：java runtine environment java——java运行环境
    - jre = jvm + 常用库&包
  - jvm：java 虚拟机（java 的解释器）

<img src="./SpringBoot 框架课.assets/image-20240412155256152.png" alt="image-20240412155256152" style="zoom:50%;" />



### JSE、JEE、JME



Spring 是 JSE 上开发的JEE 的轻量级替代品 

Spring 配置很麻烦——SpringBoot 是Spring 的自动化配置

![image-20240412160240877](./SpringBoot 框架课.assets/image-20240412160240877.png)





### 数据类型

（所有变量函数都需要定义在 class 中、没有全局变量/函数的概念）



- byte：有符号单字节整数：-128——127（2^7）
- short：有符号双字节整数：-32768——32767（2^15）
- int：-2^31——2^31 - 1
- Long：-2^63——2^63 - 1
  - 常量类型的 long 需要在结尾加上 L
- float：直接定义的小数默认都是 double——float 后面需要加上F
- double：需要强制定义为 double 类型，后面需要加上 D
- boolean：true/false
- char ：是两个字节
- String：字符串（注意是大写S）



### 类型转换

分为显式转换、隐式转换、强制类型转换



显示类型转换

![image-20240412170635629](./SpringBoot 框架课.assets/image-20240412170635629.png) 

隐式类型转换(低精度可以向高精度，反之不能)

![image-20240412170859592](./SpringBoot 框架课.assets/image-20240412170859592.png) 



隐式转换 ——4 是整型，被转为了 double，再进行运算

![image-20240412171023097](./SpringBoot 框架课.assets/image-20240412171023097.png) 

强制类型转换(结果 9)

![image-20240412171223605](./SpringBoot 框架课.assets/image-20240412171223605.png)  





### 输入

![image-20240412171554826](./SpringBoot 框架课.assets/image-20240412171554826.png) 





#### Scanner

- scanner：中的 next 方法
  - next：读入字符串，不包含空格
  - nextLine：读入一行，可以包含空格
  - nextbyte：读入一个 byte



1：需要导入包（idea会提醒）

2：类名的首字母都需要大写，不然会报错

<img src="./SpringBoot 框架课.assets/image-20240412173327094.png" alt="image-20240412173327094" style="zoom:50%;" /> 

上例输出：因为只读入字符串，空格分割

![image-20240412173458686](./SpringBoot 框架课.assets/image-20240412173458686.png)

#### BufferedReader

比 Scanner 快很多，使用需要抛异常

<img src="./SpringBoot 框架课.assets/image-20240412174040834.png" alt="image-20240412174040834" style="zoom:67%;" />

例子：注意需要抛异常 

![image-20240412175138429](./SpringBoot 框架课.assets/image-20240412175138429.png)

运行：read是读入一个字符的 ascci 码，readLine 是读入一行

![image-20240412175157332](./SpringBoot 框架课.assets/image-20240412175157332.png) 



#### BR读入整数

BufferedReader 

![image-20240413101855572](./SpringBoot 框架课.assets/image-20240413101855572.png)

一行单数：
![image-20240413101922522](./SpringBoot 框架课.assets/image-20240413101922522.png)

一行多个整数



#### 连续读入整数（并输出）



**流输出整数：**

![image-20240413110531583](./SpringBoot 框架课.assets/image-20240413110531583.png)

结果：这里 bw.write输出的是字符串

![image-20240413110542804](./SpringBoot 框架课.assets/image-20240413110542804.png) 

















### 输出

#### System.out.println

（效率较低、小规模）

![image-20240412181017994](./SpringBoot 框架课.assets/image-20240412181017994.png)

System.out.println()：可以输出所有类型，同时会自动补一个换行

System.out.print()：就没有换行



System.out.printf()**格式化输出：**

<img src="./SpringBoot 框架课.assets/image-20240412180848171.png" alt="image-20240412180848171" style="zoom:67%;" />



结果：保留 2 位&不足补 0![image-20240412180904661](./SpringBoot 框架课.assets/image-20240412180904661.png)



#### BufferedWriter

和 BufferedReader 一样——需要抛异常、同时需要手动刷新缓冲区（不然不一定有输出）

（需要手动换行）

![image-20240412181040384](./SpringBoot 框架课.assets/image-20240412181040384.png)



示例：异常、手动换行、刷缓存

![image-20240413100917128](./SpringBoot 框架课.assets/image-20240413100917128.png)

打印

![image-20240413101001214](./SpringBoot 框架课.assets/image-20240413101001214.png) 





















### 1.2判断/分支/逻辑/循环

判断：

<img src="./SpringBoot 框架课.assets/image-20240413134505357.png" alt="image-20240413134505357" style="zoom:67%;" />

逻辑表达式：

（java 中int 值不能做逻辑判断，必须是布尔值 boolean）

![image-20240413135504540](./SpringBoot 框架课.assets/image-20240413135504540.png)



分支语句：swich

（输出 1234567 星期单词）

如果不 break，第一个满足的话，后面的都会输出

<img src="./SpringBoot 框架课.assets/image-20240413140749564.png" alt="image-20240413140749564" style="zoom: 67%;" /> 



**循环**

![image-20240415160048751](./SpringBoot 框架课.assets/image-20240415160048751.png)



**循环遍历数组**

数字数组

![image-20240415160305247](./SpringBoot 框架课.assets/image-20240415160305247.png) 

对象数组

![image-20240415160431123](./SpringBoot 框架课.assets/image-20240415160431123.png) 





### 1.3 数组

#### 1.3.1 基础概念

**初始化**

- 数组定义：动态定义，但是定义后就不可变长
- 初始化方式：
  - new
  - 初值初始化
- 初始值（如果用 new 的方式初始化）：
  - int：默认初始化为 0
  - 字符串：默认初始化为空字符串
  - float：默认初始化为 0.0
- 字符数组和字符串



```java
//数组new 方式初始化
int[] a = new int[5];
int n = 10;
float[] b = new float[n];
//初值方式初始化
char[] c = {'a', 'b', 'c'};
char[] d = c;//数组赋值：（引用）赋的是地址（内存中是同一段地址）
d[1] = 'x';
int[] e = {1, 2, 3, 4, 5};//遍历数组
for(int i:e){
    System.out.println(i);
}
System.out.println(c);
System.out.println(c.hashCode());
System.out.println(c.hashCode());//hashCode是返回内存地址的哈希值
/*
输出如下：
1
2
3
4
5
axc
1808253012
1808253012
*/
```



**数组读写**

![image-20240415194311898](./SpringBoot 框架课.assets/image-20240415194311898.png) 



**多维数组**

```java
int[][] a = new int[2][3];
a[1][2] = 3;
int[][] b ={
        {1, 2, 3},
        {4, 5, 6}
};
System.out.println(Arrays.toString(c));
System.out.println(Arrays.toString(a));//输出数组所在对象的内存地址
System.out.println(Arrays.toString(b));//toString 只对一维数组有用
System.out.println(Arrays.deepToString(a));//多维需要 deepToString
System.out.println(Arrays.deepToString(b));
/*输出值如下
[1, 2, 3]
[[I@6bc7c054, [I@232204a1]
[[I@4aa298b7, [I@7d4991ad]
[[0, 0, 0], [0, 0, 3]]
[[1, 2, 3], [4, 5, 6]]
*/
```



**数组长度**

```java
int[] a = new int[10];
for(int i = 0;i < a.length; i++){
    a[i] = 10;
}
```



#### **1.3.2常用 API**

![image-20240415205022372](./SpringBoot 框架课.assets/image-20240415205022372.png) 

```java
//数组排序
int[] a = {5, 2 , 3, 1, 10};
Arrays.sort(a);
System.out.println(Arrays.toString(a));
System.out.println(a);//直接打印是打印地址
Arrays.fill(a, 100);//a 数组全部赋值为 100
```





#### 1.3.3数组逆序（匿名函数）

![image-20240416113707091](./SpringBoot 框架课.assets/image-20240416113707091.png)





![image-20240415191617161](./SpringBoot 框架课.assets/image-20240415191617161.png) 









### 1.4字符串String



**java中所有对象的赋值都是传引用（地址）**



**java 中的 String 是不可变的（只读变量）**：

str1 后面加上一个 str2，不是在 str1 后面加上 str2——而是创建一个新的地址接收这个字符串



##### 基础定义

```java
//等价命名
String a = "Hello World";
String b = new String("Hello World");

//赋值
String c = "My Name is ";
String x = b; // x 和 b 的地址相同
System.out.println(b.hashCode());
System.out.println(x.hashCode());

//加法
String d = c + "wyy";
System.out.println(d);

//默认转换
String e = "My age is " + 18;//数字 18 默认转换为了字符型

//其他类型转String
String str = String.format("My age is %f",18.16456);
System.out.println(e);

//String 转其他类型
String pi_str = "3.1415926";
double pi = Double.parseDouble(pi_str);
String in_str = "123";
int in = Integer.parseInt(in_str);
//Float.parseFloat();   Long.parseLong();

//String 是只读变量，不能被修改，这里被重载了，是计算字符串的哈希值，但是懂不可变性意思就行
String f = "Hello";
System.out.println(f.hashCode());
f += " World";
System.out.println(f.hashCode());

//遍历字符串（字符串的长度需要加括号，数组的长度不需要）
//只能读取，不能写入
String s = "Hello World";
for(int i = 0; i < s.length(); ++i){
  System.out.println(s.charAt(i));
}
//也可以转换为char/String数组后遍历
String s = "Hello World";
char[] arr = s.toCharArray();
for(int i = 0; i < arr.length; i++){
    System.out.printf("%s",arr[i]);
}
System.out.println();
String[] arr2 = s.split("");
for(int i = 0; i < arr2.length; i++){
    System.out.printf("%s",arr2[i]);
}
```

![image-20240425100545385](./SpringBoot 框架课.assets/image-20240425100545385.png) 



##### **常用 API**

注意：对字符串的新操作，都没有改变原字符串的值

需要用新的字符串接受操作改变后的字符串or 直接输出它

```java
//split分割字符串，split 支持正则表达式
String a = "Hello World a !";
String[] arr1 = a.split("");//遍历字符串
for(int i = 0; i < arr1.length; i++){
	System.out.printf(arr1[i]);//Hello World a !
}
System.out.println();
String[] arr2 = a.split(" ");//分割字符串
System.out.println(Arrays.toString(arr2));//[Hello, World, a, !]
//split的正则表达式版本
String a = "Hello      World";
String[] arr1 = a.split("\\s+");//匹配多个空白字符
System.out.println(Arrays.toString(arr1));
String[] arr2 = a.split("[ ]+");//也是匹配多个空白字符
System.out.println(Arrays.toString(arr2));


//indexOf定位字母
System.out.println(a.indexOf('o'));//4
System.out.println(a.indexOf("Wo"));//6


//equals、compareTo比较字符串
//相等 equals
String a = "aaa", b = "bbb";
System.out.println(a.equals(b));//不能直接用a == b比较，这样比较的是地址值
//可以比较大小关系compareTo
String a = "aaa", b = "bbb";
System.out.println(a.compareTo(b));//负数b 小于 a，0 相等，正数 b 大于 a

//startsWith判断以字符串开头
//endsWith以字符串结尾
String a = "abcde";
System.out.println(a.startsWith("abc"));//true
System.out.println(a.startsWith("abe"));//false

//a.trim();去掉首位空白字符
//a.toLowerCase()全部转为小写
//a.toUpperCase()全部转为大写


//替换字符：替换某一个字符/替换字符串
String a = "abcabc";
a.replace('a', 'x');
System.out.println(a)//这里输出的还是 abcabc
System.out.println(a.replace('a', 'x'));//a 全部替换为x  输出xbcxbc
System.out.println(a.replace("ab", "x"));//ab全部替换为 x 输出xcxc


//substring()取子串[)左闭右开，从 0 开始计数
String a = "abcabc";
System.out.println(a.substring(2,4));ca
```

![image-20240428110500409](./SpringBoot 框架课.assets/image-20240428110500409.png) 





##### 动态长度

String不可动态变长，每次改变都是在内存创建新对象

```java
String s = "";
for(int i = 0; i < 10000; ++i){
  s += "a";
}
System.out.println(s);
//n^2级别复杂度，每次改变都是创建新对象
```



##### Builder、Buffer

(算法题可以用 Builder，项目用 Buffer)

StringBuffer（线程安全、速度慢）、StringBuilder（线程不安全、没有加锁、效率更高）

**多线程用 StringBuffer、单线程用 StringBuilder**

```java
StringBuffer sb = new StringBuffer();
for(int i = 0; i < 2000000; ++i){
    sb.append('a');
}
System.out.println(sb);
```



**常用 API：反转reverse**

```java
StringBuilder sb = new StringBuilder("abcdef");
sb.reverse();//在自己上面修改，没有创建新的对象
System.out.println(sb);//所以可以修改后再输出，不用接收
```



**取值和赋值**

```java
StringBuffer sb = new StringBuffer("abcdefg");
for(int i = 0; i < sb.length(); ++i){
    sb.setCharAt(i, (char)(sb.charAt(i) + 1));
}
System.out.println(sb);//bcdefgh
```



![image-20240428163913481](./SpringBoot 框架课.assets/image-20240428163913481.png)





### 2 函数

#### 2.1基础概念

**静态函数里面：只能调用静态变量和静态函数**



- **java 中的函数必须定义到类中**
  - static 函数：绑定到这个类上
    - new的不同实例共用一个函数（全局函数）
  - 不加 static 函数
    - 不同的实例拥有不同的函数
- 变量也是类似的：private int x; private int static y
  - 不同的实例a、b、c 分别拥有各自的 x
  -  a、b、c 共用一个 y



<img src="./SpringBoot 框架课.assets/image-20240509115834018.png" alt="image-20240509115834018" style="zoom:67%;" /> 







#### 2.2 用法示例



数组初始化函数：

```java
package com.godice;

import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        int[][] a = getArray2d(3, 4, 12);
        System.out.println(Arrays.deepToString(a));


    }
    //返回最大值
    private static int max(int a, int b){
        return a > b ? a : b;
    }

    //数组赋初值
    private static void fill_arr(int[][] a, int val){
        for(int i = 0; i < a.length; ++i){
            for(int j = 0; j < a[i].length; ++j){
                a[i][j] = val;
            }
        }
    }

    //创建二维数组（并赋初值）
    private static int[][] getArray2d(int row, int col, int c){
        int[][] res = new int[row][col];
        for(int i = 0; i < res.length; ++i){
            for(int j = 0; j < res[i].length; ++j){
                res[i][j] = c;
            }
        }
        return res;
    }
}
```



#### 2.3 全局变量

java 中没有全局变量，可以设置一个全局变量的类来处理





#### 2.4 访问控制

##### 2.4.1基础概念

![image-20240509150639495](./SpringBoot 框架课.assets/image-20240509150639495.png)

**说明：**

需要特别说明“无修饰符”这个情况，子类能否访问父类中无修饰符的变量/方法，取决于子类的位置。如果子类和父类在同一个包中，那么子类可以访问父类中的无修饰符的变量/方法，否则不行。





##### 2.4.2静态变量的访问



**静态变量/函数，访问的时候，用类名访问**

**普通变量/函数，访问的时候，用具体实例访问**

（静态绑定到唯一的类上，普通的每一个实例有一个）

```java
class Argument{
    public static int x = 1;//全局变量
    public final static int y = 2;//全局常量
}

public class Main{
    public static void main(String[] args){
        System.out.println(Argument.x);
        System.out.println(Argument.y);
    }
}
```



##### 2.4.3静态函数的访问

- 静态变量
  - 一个类中只有一个，其绑定到该类名上
  - 本类初始化了多个实例
    - 这多个实例也共享同一个静态的变量



- 函数加不加 static 的区别
  - 有 static：绑定到类名上
    - 通过类名进行访问函数
  - 无 static：绑定到具体的对象熵
    - 通过实例进行访问

```java
package com.godice;

class Node{
    public void f(){
        System.out.println("This is function f(no static)");
    }

    public static void g(){
        System.out.println("This is function g(static)");
    }
}


public class Main{
    public static void main(String[] args){
        Node node1 = new Node();
        node1.f();
        //node1.g();用实例访问静态函数可以用，但是会提示：g()是static的，用Node.g();访问比较好
        Node.g();
        node1.g();
        //Node.f();从类名访问非静态函数，会报错
    }
}
```



##### 2.4.4 static访问特性





**1：一个类中的static 函数如果访问本类中的函数和变量，只能访问static 函数和 static 变量**

（访问其他类的不受限制）

静态方法只能直接访问静态成员，无法访问非静态成员，如果想要访问非静态方法，则实例化该类对象，使用对象名.非静态方法()的方式来访问！

static是一种访问修饰符，在java中可以修饰代码块、方法和属性
1、静态块：用于在类加载之前完成一些初始化操作，仅会执行一次

2、静态方法：可以使用类名和对象调用，只能直接访问静态成员，无法访问非静态成员

3、静态属性：可以使用类名和对象名调用，多个对象共享

```java
import java.io.*;
import java.util.*;
 
public class Main{
	public static void main(String args[]) throws Exception{
		f();
  }
	
	boolean f() {
		return true;
	}
}
```

![image-20240509161202366](./SpringBoot 框架课.assets/image-20240509161202366.png)

  

```java
import java.io.*;
import java.util.*;
 
public class Main{
    public static void main(String args[]) throws Exception{
				Main main = new Main();
				main.f();
    }
	
    boolean f() {
				return true;
    }
}
```



**2：static可以节省内存**

因为对于一个类，new 多个实例，但是 static 函数也只储存了一份







### 3类与接口



#### 3.1 类



##### **源文件声明规则**

![image-20240509172640703](./SpringBoot 框架课.assets/image-20240509172640703.png) 

每一个源文件中只能有一个 public 类，且类名应该和文件名相同

![image-20240509172805018](./SpringBoot 框架课.assets/image-20240509172805018.png)     









##### 类的定义

![image-20240509173151234](./SpringBoot 框架课.assets/image-20240509173151234.png) 



一个点类

```java
package com.godice;

import java.util.Scanner;


//定义一个点类
class Point{
    private int x;
    private int y;

    public Point(int x, int y){//构造函数，用做初始化
        this.x = x;
        this.y = y;
    }

    public Point(int x){//构造函数可以有多个，这里只进行 x 的初始化
        this.x = x;
    }
    //因为 xy 都是私有的，访问和修改都需要通过类中的公有方法
    public int getX(int x){
        return this.x;
    }
    public int getY(int y){
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    //打印点
    public void printPoint(){
        System.out.printf("(%d,%d)", this.x, this.y);
        System.out.println();
    }

    //返回字符串格式
    public String toString() {
        return String.format("toString change value is (%d, %d)", x, y);
    }
}


public class Main{
    public static void main(String[] args){
        Point point1 = new Point(1,2);
        point1.printPoint();
        Point point2 = new Point(3);
        point2.printPoint();
        System.out.println(point1.toString());
    }
}
```





##### 类的继承

super在子类中表示父类的引用

eg：需要定义一个带颜色的点：相对于上一个点类，只多了颜色的属性

​	就不用重写逻辑，直接继承即可



```java
package com.godice;

import java.util.Scanner;


//定义一个点类
class Point{
    private int x;
    private int y;

    public Point(int x, int y){//构造函数，用做初始化
        this.x = x;
        this.y = y;
    }

    public Point(int x){//构造函数可以有多个，这里只进行 x 的初始化
        this.x = x;
    }
    //因为 xy 都是私有的，访问和修改都需要通过类中的公有方法
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    //打印点
    public void printPoint(){
        System.out.printf("(%d,%d)", this.x, this.y);
        System.out.println();
    }

    //返回字符串格式
    public String toString() {
        return String.format("toString change value is (%d, %d)", x, y);
    }
}

//带颜色的点类，继承自父类
class ColorPoint extends Point{
    private String color;
    public ColorPoint(int x, int y , String color){
        super(x,y);//这里调用父类的构造函数
        this.color = color;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    //转换字符串
    public String toString() {//如果没有重写，就调用父类的方法，如果重写了，则覆盖
        return String.format("(%d, %d, %s)", super.getX(), super.getY(), color);
    }
}

public class Main{
    public static void main(String[] args){
        Point point1 = new Point(1,2);
        point1.printPoint();
        Point point2 = new Point(3);
        point2.printPoint();
        System.out.println(point1.toString());
        //colorPoint
        ColorPoint cpoint1 = new ColorPoint(1,2, "blue");
        //System.out.printf("%s & color is %s \n", cpoint1.toString(), cpoint1.getColor());
        System.out.println(cpoint1.toString());
    }
}
```





##### 多态

一个子类的对象colorPoint，可以放到一个父类Point的引用上

（多态即基于此）

<img src="./SpringBoot 框架课.assets/image-20240509212322044.png" alt="image-20240509212322044" style="zoom: 67%;" /> 



- 多态：同一个类的同一个函数，会有不同的行为
  - 两个类的引用都是 Point，但是第一个类（new）的对象是一个 Point，第二个类（new）的对象是一个 ColorPoint
  - 第二个类就会优先调用 colorPoint 类的 toString方法

<img src="./SpringBoot 框架课.assets/image-20240509212617398.png" alt="image-20240509212617398" style="zoom:67%;" /> 





#### 3.2 接口

（接口中的方法只能用 public）

接口中的方法，一般不放函数体（即具体实现），函数体一般是放在具体的使用接口的类



**Ctrl + 回车 可以快速实现类中的接口**

##### 3.2.1 接口作用

定义某些类，必须实现的函数（方法）——规范性、多人协作

- 接口interface与类 class 类似
  - 约定俗成：
    - 类包含方法和变量
    - 接口只包含（函数）方法：一般不包含变量（也可以包含变量）
  - 接口不能实例化对象：接口是不能有实例的
  - 接口的作用：用来规范类的（主要用于多人协作）
    - 使用了该接口的类，必须实现接口所定义的方法（函数），否则编译器会报错



接下来分为三个部分：

接口的定义、继承、实现

![image-20240509215852786](./SpringBoot 框架课.assets/image-20240509215852786.png) 







##### 3.2.2 继承规则

接口可以继承多个接口

类可以实现多个接口

类只能继承一个类

<img src="./SpringBoot 框架课.assets/image-20240510171242053.png" alt="image-20240510171242053" style="zoom:67%;" /> 





##### 3.2.3 接口实例



```java
package com.godice;

//角色接口
interface Role{
    public void greet();
    public void move();
    public int getSpeed();
}
//英雄接口，继承自角色接口
interface Hero extends Role{
    public void attack();
}

//一个具体的英雄：宙斯，实现了英雄接口，继承自角色接口
class zeus implements Hero{
    private final String name = "zeus";
    public void greet(){
        System.out.println(name + ":hi");
    }
    public void move(){
        System.out.println(name + ":i' moving!");
    }
    public int getSpeed(){
        return 100;
    }
    public void attack() {
        System.out.println(name + "is attacking!");
    }
    public String getName(){
        return name;
    }
}

public class Main{
    public static void main(String[] args){
        zeus zeus_red = new zeus();//这里和类的多态一样的道理可以用 Hero zeus_red = new zeus();
        //也可以用Role zeus_red = new zeus();但是这里，就不能使用在 Hero 接口中的函数：attack
        zeus_red.greet();
        zeus_red.move();
        System.out.printf("%s' Speed is %d\n",zeus_red.getName(),zeus_red.getSpeed());
        zeus_red.attack();
    }
}
```

结果

```
zeus:hi
zeus:i' moving!
zeus' Speed is 100
zeusis attacking!
```





##### 3.2.4 接口的多态

除zeus外多实现一个Athena英雄

通过用户的输入赋予Hero接口的引用对应的英雄

```java
package com.godice;

import java.util.Scanner;
//角色接口
interface Role{
    public void greet();
    public void move();
    public int getSpeed();
}
//英雄接口，继承自角色接口
interface Hero extends Role{
    public void attack();
}

//一个具体的英雄：宙斯，实现了英雄接口，继承自角色接口
class zeus implements Hero{
    private final String name = "zeus";
    public void greet(){
        System.out.println(name + ":hi");
    }
    public void move(){
        System.out.println(name + ":i' moving!");
    }
    public int getSpeed(){
        return 100;
    }
    public void attack() {
        System.out.println(name + "is attacking!");
    }
    public String getName(){
        return name;
    }
}

//新英雄雅典娜
class Athena implements Hero{
    private final String name = "Athena";
    public void greet(){
        System.out.println(name + ":hi");
    }
    public void move(){
        System.out.println(name + ":i' moving!");
    }
    public int getSpeed(){
        return 100;
    }
    public void attack() {
        System.out.println(name + "is attacking!");
    }
    public String getName(){
        return name;
    }
}


public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入选择英雄的名称：zeus/Athena");
        String name = sc.next();
        Hero hero1;//定义一个继承自Role的Hero英雄接口，如果不利用多态，这里需要用逻辑判断，多个英雄就很复杂
        if(name.equals("zeus")) hero1 = new zeus();
        else hero1 = new Athena();
        hero1.greet();//
        hero1.move();
        hero1.attack();
    }
}
```

如果不使用多态

```java
if(name.equals("zeus")){
  zeus hero1 = new zeus();
  hero1.greet();
  hero1.move();
  hero1.attack();
}else{
  zeus hero1 = new zeus();
  hero1.greet();
  hero1.move();
  hero1.attack();
}
```





#### 3.3泛型

- 同一个函数：可以作用于不同的对象类型（某些逻辑适用于所有的变量类型）
- 泛型里面的对象：只能是对象，不能是基本类型
  - eg：不能用 int，需要用Integer

<img src="./SpringBoot 框架课.assets/image-20240510181102956.png" alt="image-20240510181102956" style="zoom:67%;" /> 



##### 3.3.1 泛型的声明

```java
package com.godice;

import java.util.Stack;

public class Main{
    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<Integer>();
    }    
}
```





##### 3.3.2Stack源码

（同一套逻辑，可以作用于多种类型，即可使用泛型）

按住 alt 点击 Stack 可以查看源码

<img src="./SpringBoot 框架课.assets/image-20240510181839155.png" alt="image-20240510181839155" style="zoom:67%;" /> 



Stack源码

<img src="./SpringBoot 框架课.assets/image-20240510182050659.png" alt="image-20240510182050659" style="zoom:67%;" /> 









### 4 文件拆分

一般写项目是用树状的方式维护：

- 一个项目分为多个模块
  - 一个模块分为多个文件（不同的功能实现）
    - 一个大文件（功能项）拆为多个小文件
      - 一般一个类就是一个文件





1：创建一个软件包（文件夹）

com.godice.utils：用于存放一些工具

<img src="./SpringBoot 框架课.assets/image-20240509213730849.png" alt="image-20240509213730849" style="zoom:67%;" /> 

2：在文件夹中新建 java 文件

![image-20240509213857096](./SpringBoot 框架课.assets/image-20240509213857096.png) 



文件名以类名作为命名（类的代码复制过来，需要在类的声明前加上 public）

（每个 package 必须有且只有一个 public 的类）

![image-20240509214313038](./SpringBoot 框架课.assets/image-20240509214313038.png) 



3：在主类中 import 刚才的两个类

![image-20240509214526529](./SpringBoot 框架课.assets/image-20240509214526529.png)



**注意：在同一个目录下，是不需要 import 的**

这里 ColorPoint 用到了 Point，但是并没有 import

![image-20240509214716952](./SpringBoot 框架课.assets/image-20240509214716952.png)



主类代码：

```java
package com.godice.utils;

//带颜色的点类，继承自父类Point
public class ColorPoint extends Point{
    private String color;
    public ColorPoint(int x, int y , String color){
        super(x,y);//super是父类的构造函数，调用父类的构造函数
        this.color = color;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    //转换字符串
    public String toString() {//如果没有重写，就调用父类的方法，如果重写了，则覆盖
        return String.format("(%d, %d, %s)", super.getX(), super.getY(), color);
    }
}
```

Point 类代码

```java
package com.godice.utils;

//点类Point
public class Point{
    private int x;
    private int y;

    public Point(int x, int y){//构造函数，用做初始化
        this.x = x;
        this.y = y;
    }

    public Point(int x){//构造函数可以有多个，这里只进行 x 的初始化
        this.x = x;
    }
    //因为 xy 都是私有的，访问和修改都需要通过类中的公有方法
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    //打印点
    public void printPoint(){
        System.out.printf("(%d,%d)", this.x, this.y);
        System.out.println();
    }

    //返回字符串格式
    public String toString() {
        return String.format("toString change value is (%d, %d)", x, y);
    }
}
```



ColorPoint 类代码

```java
package com.godice.utils;

//带颜色的点类，继承自父类Point
public class ColorPoint extends Point{
    private String color;
    public ColorPoint(int x, int y , String color){
        super(x,y);//super是父类的构造函数，调用父类的构造函数
        this.color = color;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    //转换字符串
    public String toString() {//如果没有重写，就调用父类的方法，如果重写了，则覆盖
        return String.format("(%d, %d, %s)", super.getX(), super.getY(), color);
    }
}
```





### 5 链表实现

```java
package com.godice;

//链表的实现
class Node{
    int val;
    Node next;
    public Node(int val){
        this.val = val;
    }
}

public class Main{
    public static void main(String[] args){
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        for(Node p = head; p != null; p = p.next ){
            System.out.println(p.val);
        }
    }
}
```







### 4常用容器

（除了栈是类，其他都是接口，需要通过对应的类实现）

List——接口：ArrayList、LinkedList

栈——类

队列——接口

Set 集合——接口

Map——接口

#### 4.1List

List 是一个接口，不是类 

所以初始化需要初始化一个可以使用它的类

![image-20240514214559116](./SpringBoot 框架课.assets/image-20240514214559116.png) 





![image-20240514215143867](./SpringBoot 框架课.assets/image-20240514215143867.png)



```java
package com.godice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main{
    public static void main(String[] args){
        List<Integer> list1 = new ArrayList<>();//变长数组
        List<Integer> list2 = new LinkedList<>();//双链表
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(list1);
        System.out.println(list1.size());
        System.out.println("list1[1]:" + list1.get(1));
        list1.set(0, 100);//修改值
        System.out.println(list1);
        list1.clear();
        System.out.println(list1);
        System.out.println(list1.isEmpty());
    }
}
```

打印

```
[1, 2, 3]
3
list1[1]:2
[100, 2, 3]
[]
true
```





![image-20240514214434685](./SpringBoot 框架课.assets/image-20240514214434685.png) 













#### 4.2 栈

是一个类

![image-20240514220720797](./SpringBoot 框架课.assets/image-20240514220720797.png) 



```java
package com.godice;

import java.util.Stack;

public class Main{
    public static void main(String[] args){
        Stack<String> stk = new Stack<String>();
        stk.push("hello");
        stk.push("world");
        System.out.println(stk);
        System.out.println(stk.peek());
        System.out.println(stk.pop());
        System.out.println(stk.peek());
        System.out.println(stk);
    }
}
```













#### 4.3 队列

 队列是一个接口

![image-20240514221411858](./SpringBoot 框架课.assets/image-20240514221411858.png) 



**双链表**

```java
package com.godice;

import java.util.LinkedList;
import java.util.Queue;

public class Main{
    public static void main(String[] args){
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(1);//队尾加
        q1.add(2);
        q1.add(3);//123
        System.out.println(q1);
        q1.remove();//队首删23
        System.out.println(q1);
    }
}
```



**优先队列**

（堆：大根堆、小根堆）

```java
package com.godice;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main{
    public static void main(String[] args){
        Queue<Integer> q1 = new PriorityQueue<>();
        q1.add(3);
        q1.add(1);
        q1.add(7);
        q1.add(10);
        System.out.println(q1);
    }
}
```

![image-20240515155128331](./SpringBoot 框架课.assets/image-20240515155128331.png) 





#### 4.4Set集合

（集合的元素是不重复的：添加 1、2、1，只保存 1、2）

- 接口：可以用两个类实现
  - 哈希表：HashSet
    - 增删改查都是 O（1）
  - 平衡树：TreeSet
    - 有序集合
    - 增删改查都是 O（logn）

![image-20240515155344843](./SpringBoot 框架课.assets/image-20240515155344843.png) 



HashSet

```java
package com.godice;

import java.util.HashSet;
import java.util.Set;

public class Main{
    public static void main(String[] args){
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        System.out.println(set1);
    }
}
```



TreeSet：平衡树

```java
package com.godice;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main{
    public static void main(String[] args){
        TreeSet<Integer> set1 = new TreeSet<Integer>();
        set1.add(1);
        set1.add(2);
        set1.add(6);
        set1.add(3);
        set1.add(7);
        System.out.println(set1);
        System.out.println(set1.ceiling(4));
        System.out.println(set1.floor(4));
    }
}
```

输出值

```
[1, 2, 3, 6, 7]
6
3
```





#### 4.5Map

接口：实现类 HashMap哈希表、TreeMap平衡树

（实现一个映射）



<img src="./SpringBoot 框架课.assets/image-20240515162014669.png" alt="image-20240515162014669"  /> 



力扣 1：两数和

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map1 = new HashMap<>();
        for(int i = 0; i < nums.length; ++i){
            if(map1.containsKey(target - nums[i]))
                return new int[] {i, map1.get(target - nums[i])};
            map1.put(nums[i], i);
        }
        return new int[]{};
    }
}
```



##### **HashMap**

哈希表普通 map

```java
package com.godice;


import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args){
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, "godice");
        map1.put(2, "wang");
        map1.put(3, "Hello");
        map1.put(6, "World");
        map1.put(4, "lalala");
        System.out.println(map1);
        System.out.println(map1.get(3));//获取关键字
        System.out.println(map1.containsKey(3));//是否包含某个关键字
        map1.remove(4);
        System.out.println(map1);
        System.out.println(map1.entrySet());//返回所有键值对的集合
        //遍历Map
        for(Map.Entry<Integer, String> p: map1.entrySet()){
            System.out.println(p.getKey());
            System.out.println(p.getValue());
        }
    }
}
```

打印

```
{1=godice, 2=wang, 3=Hello, 4=lalala, 6=World}
Hello
true
{1=godice, 2=wang, 3=Hello, 6=World}
[1=godice, 2=wang, 3=Hello, 6=World]
1
godice
2
wang
3
Hello
6
World
```



解释：

```java
for(Map.Entry<Integer, String> p: map1.entrySet()){
    System.out.println(p.getKey());
    System.out.println(p.getValue());
}
```

![image-20240515164807562](./SpringBoot 框架课.assets/image-20240515164807562.png)





##### 平衡树（有序）



```java
package com.godice;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main{
    public static void main(String[] args){
        TreeMap<Integer, String> map1 = new TreeMap<>();
        map1.put(1, "godice");
        map1.put(2, "wang");
        map1.put(3, "Hello");
        map1.put(6, "World");
        map1.put(4, "lalala");
        System.out.println(map1.ceilingEntry(3));//大于等于3的最小值
        System.out.println(map1.floorEntry(5));//小于等于5的最大值
        Map.Entry<Integer, String> up = map1.ceilingEntry(3);
        System.out.printf("大于等于3的最小值 key:%d value:%s\n",up.getKey(), up.getValue());
        Map.Entry<Integer, String> down = map1.floorEntry(5);
        System.out.printf("小于等于5的最小值 key:%d value:%s", down.getKey(), down.getValue());
//        3=Hello
//        4=lalala
//        大于等于3的最小值 key:3 value:Hello
//        小于等于5的最小值 key:4 value:lalala
    }
}
```

















## 语法练习题

### 基础语法

#### 1a+b

整数求和，空格分割输入



1：BufferedReader

![image-20240413112400283](./SpringBoot 框架课.assets/image-20240413112400283.png)

2：scanner

![image-20240413113708056](./SpringBoot 框架课.assets/image-20240413113708056.png) 



#### 608差

![image-20240413114255532](./SpringBoot 框架课.assets/image-20240413114255532.png)

答案

![image-20240413114927668](./SpringBoot 框架课.assets/image-20240413114927668.png)



#### 616 两点距离

![image-20240413141912856](./SpringBoot 框架课.assets/image-20240413141912856.png)

结果

![image-20240413143203497](./SpringBoot 框架课.assets/image-20240413143203497.png)

#### 653 钞票

<img src="./SpringBoot 框架课.assets/image-20240413143142567.png" alt="image-20240413143142567" style="zoom:67%;" />



代码：

```
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner cin=new Scanner(System.in);
        int n=cin.nextInt();
        System.out.println(n);
        System.out.println(n/100+" nota(s) de R$ 100,00");
        n%=100;
        System.out.println(n/50+" nota(s) de R$ 50,00");
        n%=50;
        System.out.println(n/20+" nota(s) de R$ 20,00");
        n%=20;
        System.out.println(n/10+" nota(s) de R$ 10,00");
        n%=10;
        System.out.println(n/5+" nota(s) de R$ 5,00");
        n%=5;
        System.out.println(n/2+" nota(s) de R$ 2,00");
        n%=2;
        System.out.println(n/1+" nota(s) de R$ 1,00");
    }
}
```



#### 654时间转换

![image-20240413153231437](./SpringBoot 框架课.assets/image-20240413153231437.png)

代码

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int h = N /3600;
        N = N % 3600;
        int m = N / 60;
        N = N % 60;
        int s = N;
        System.out.printf("%d:%d:%d",h,m,s);
    
    }
}
```



### 判断

#### 665 倍数

![image-20240413153957413](./SpringBoot 框架课.assets/image-20240413153957413.png)



```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int tmp;
        if(A > B){
            tmp = A;
            A = B;
            B = tmp;
        }
        if(B % A == 0){
            System.out.println("Sao Multiplos");
        }else{
            System.out.println("Nao sao Multiplos");
        }
        
        
    }
}
```





#### 660零食

![image-20240413154918180](./SpringBoot 框架课.assets/image-20240413154918180.png) 



```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        float[] price = new float[]{4.00f,4.50f,5.00f,2.00f,1.50f};
        System.out.printf("Total: R$ %.2f",price[x - 1] * y);
    }

}
```





#### 659区间

![image-20240415141436832](./SpringBoot 框架课.assets/image-20240415141436832.png)



```java
import java.util.Scanner;

public class Main{
    public static void main(String[] agrs){
        Scanner sc = new Scanner(System.in);
        float x = sc.nextFloat();
        if(x >= 0 && x <= 100){
            if(x >= 0 && x <= 25)
                System.out.println("Intervalo [0,25]");
            if(x > 25 && x <= 50)
                System.out.println("Intervalo (25,50]");
            if(x > 50 && x <=75)
                System.out.println("Intervalo (50,75]");
            if(x > 75 && x <=100)
                System.out.println("Intervalo (75,100]");
        }else{
            System.out.println("Fora de intervalo");
        }
    }
}
```





#### 667游戏时间

<img src="./SpringBoot 框架课.assets/image-20240415143505352.png" alt="image-20240415143505352" style="zoom:67%;" /> 



```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int time = 0;
        if(A == B){
            System.out.println("O JOGO DUROU 24 HORA(S)");
        }else{
            if(A > B){
                B += 24; 
            }
            time = B - A;
            System.out.printf("O JOGO DUROU %d HORA(S)",time);
        }
        
    }
}
```

















#### 670 动物

![image-20240415151758591](./SpringBoot 框架课.assets/image-20240415151758591.png) 







您提供的Java代码中有一个常见的字符串比较误区。在Java中，使用 == 运算符比较字符串对象时，实际上是比较它们的引用（内存地址），而不是比较它们的内容。因此，即使两个字符串具有相同的字符序列，如果它们是不同的对象实例，== 比较的结果也会是false。对于字符串内容的比较，应使用 equals() 或 equalsIgnoreCase() 方法。
下面是修正后的代码，将所有的 a == "..."、b == "..." 和 c == "..." 改为使用 equals() 方法进行比较：

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        String c = sc.nextLine();
        if(a.equals("vertebrado")){
            if(b.equals("ave")){
                if(c.equals("carnivoro"))
                    System.out.println("aguia");
                else
                    System.out.println("pomba");
            }else{
                if(c.equals("onivoro"))
                    System.out.println("homem");
                else 
                    System.out.println("vaca");
            }
        }else{
            if(b.equals("inseto")){
                if(c.equals("hematofago"))
                    System.out.println("pulga");
                else{
                    System.out.println("lagarta");
                }
            }else{
                if(c.equals("hematofago"))
                    System.out.println("sanguessuga");
                else
                    System.out.println("minhoca");
            } 
        }
    }
}
```







### 循环

#### 721 递增序列

![image-20240415163727268](./SpringBoot 框架课.assets/image-20240415163727268.png) 



```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = 0;
        do{
            x = sc.nextInt();
            for(int i = 1; i <= x; i++){
                System.out.printf("%d ",i);
            }
            if(x != 0)
                System.out.println();
        }while(x != 0);
    }
}
```







#### 727菱形

![image-20240415170258749](./SpringBoot 框架课.assets/image-20240415170258749.png) 





```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int half = (n + 1) / 2;
        int star = 1;
        for(int i = 1;i <= n; i++){
            for(int j = 0; j < (n - star) / 2; j++)
                System.out.print(" ");
            for(int j = 0; j < star; j++)
                System.out.print("*");
            System.out.println();
            if(i < half)
                star += 2;
            else
                star -= 2;
        }
    }
}
```







### 数组

#### 743 数组中的行

![image-20240415211350661](./SpringBoot 框架课.assets/image-20240415211350661.png)







```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double[][] a = new double[12][12];
        int column = sc.nextInt();
        String type = sc.next();
        double sum = 0, average = 0;
        for(int i = 0; i < 12; ++i){
            for(int j = 0; j < 12; ++j){
                a[i][j] = sc.nextDouble();
            }
        }
        for(int i = 0; i < 12; i++){
            sum += a[column][i];
            average = sum / 12;
        }
        if(type.equals("S")){
            System.out.printf("%.1f",sum);
        }else{
            System.out.printf("%.1f",average);
        }

    }
}
```





#### 753平方矩阵 

![image-20240416211516197](./SpringBoot 框架课.assets/image-20240416211516197.png) 

##### 思路 1

每个格子到边界的距离，就是它的数值

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            int n = sc.nextInt();
            if(n == 0)
                break;
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    int y = Math.min(i, n + 1 - i);
                    int x = Math.min(j, n + 1 - j);
                    int min = Math.min(x, y);
                    System.out.printf("%d ", min);
                    
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
```

![image-20240416214337690](./SpringBoot 框架课.assets/image-20240416214337690.png) 







##### 思路2

（数学上的找规律）复杂！！：

```
n = 7
1 1 1 1 1 1 1
1 2 2 2 2 2 1
1 2 3 3 3 2 1
1 2 3 4 3 2 1
1 2 3 3 3 2 1
1 2 2 2 2 2 1
1 1 1 1 1 1 1
行列为（i , j）从1 开始
左半边矩阵数值为 min（i，j）
左右的分割线（对角线）为 i + j = n + 1
右半边矩阵数值为:min(ir, jr)
ir = n + 1 - i;
jr = n + 1 - j;
n = 7;
```







```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = 1;
        int count = 0;//用作换行标志位（第一个矩阵输出前不换行）
        while(n != 0){
            n = sc.nextInt();
            if(count != 0){//每个矩阵之间的换行
                System.out.println();
            }
            int[][] arr = new int[n][n];
            for(int i = 1; i <= n; i++){//计算矩阵数值
                for(int j = 1; j <= n; j++){
                    int tmp = 0;
                    int ir = 0, jr = 0;
                    if((i + j) <= (n + 1)){//矩阵的左半边三角   
                        if(i > j){
                            tmp = j;
                        }else{
                            tmp = i;
                        }
                    }else{//矩阵右边半三角
                        //转换数据
                        ir = n + 1 - i;
                        jr = n + 1 - j;
                        //求最小值
                        if(ir < jr){
                            tmp = ir;
                        }else{
                            tmp = jr;
                        }
                    }
                    arr[i - 1][j - 1] = tmp;
                }
            }
            for(int i = 0; i < n; i++){//输出矩阵
                for(int j = 0; j < n; j++){
                    System.out.printf("%d ",arr[i][j]);
                }
                    System.out.println();
            }
            count ++;
        }
    }
}
```









#### 747数组左上半部分

![image-20240417111601858](./SpringBoot 框架课.assets/image-20240417111601858.png) 

![image-20240417111617646](./SpringBoot 框架课.assets/image-20240417111617646.png) 



思路：

左边三角矩阵的i + j 的值小于特定值（对角线的 i + j 值是定值）



```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String tag = sc.next();
        Double sum = 0D;
        for(int i = 1; i <= 12; i++){
            for(int j = 1; j <= 12; j++){
                if(i + j < 13)
                    sum += sc.nextDouble();
                else{
                    Double a = sc.nextDouble();
                }
            }
        }
        if(tag.equals("S")){
            System.out.printf("%.1f",sum);
        }else{
            System.out.printf("%.1f",sum / (((1 + 11) * 11) / 2));
        }
    }
}
```





#### 756*蛇形矩阵

![image-20240417113411513](./SpringBoot 框架课.assets/image-20240417113411513.png) 



输出样例 2：

![image-20240417151426351](./SpringBoot 框架课.assets/image-20240417151426351.png) 







##### y 总思路

遍历上下左右 四个格子：x 是行、y 是列

（行向上减少，向下增加、列向左减少、向右增加）

![image-20240417212654218](./SpringBoot 框架课.assets/image-20240417212654218.png)

正常判断和（x,y）连通的上下左右 4 个格子，需要用四个格子的坐标写四个判断

（重复且代码量大）

思路：存下四个格子的偏移量，直接用偏移量数组代入判断

（就可以写一个循环进行枚举）

![image-20240417213239366](./SpringBoot 框架课.assets/image-20240417213239366.png) 





蛇形矩阵：

![image-20240417214302604](./SpringBoot 框架课.assets/image-20240417214302604.png) 





```
dx = {-1, 0 , 1 ,0}
dy = {0, 1, 0 , -1}
```

![image-20240417214947190](./SpringBoot 框架课.assets/image-20240417214947190.png) 



代码复现

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int x = 0, y = 0;
        int d = 1;//0123右下左上
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int[][] arr = new int[n][m];
        for(int i = 1; i <= n * m; ++i){
            arr[x][y] = i;
            int a = x + dx[d], b = y + dy[d];//尝试修改
            if(a < 0 || a >= n || b < 0 || b >= m || arr[a][b] != 0){
            //有问题则：取消修改，然后转向
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < m; ++j){
            System.out.printf("%d ",arr[i][j]);
            }
            System.out.println();
        }
    }
}
```









##### 我的思路

思路：

考虑用一个标记数组记录这个元素有没有被访问过

因为转圈的方向都是固定的（i，j）坐标的变向都一样的——右下左上—>右下左上右

（只需要遇到i，j 的边界或者是遇到已访问过的元素按照预定好的转向即可）



实现：

第一圈单独判断（避免数组溢出问题处理）

第二圈之后循环逻辑

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        int[][] tag = new int[n][m];
        int count = 1;//数字递增
        int tag1 = 0;//余数0右 1下 2左 3上
        int i = 0, j = 0;//数组坐标
        arr[i][j] = count;
        tag[i][j] = 1;
        while(count <= m * n){//画出矩阵
            //第一圈
            if(tag1 / 4 == 0){
                //转向逻辑
                if(tag1 == 0){
                    while(j != m - 1){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        j++;
                    }
                    arr[0][m - 1] = count++;
                    tag[0][m - 1] = 1;
                    i++;
                }else if(tag1 == 1){
                    while(i != n - 1){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        i++;
                    }
                    arr[n - 1][m - 1] = count++;
                    tag[n - 1][m - 1] = 1;
                }else if(tag1 == 2){
                    j--;
                    while(j != 0){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        j--;
                    }
                    arr[n - 1][0] = count++;
                    tag[n - 1][0] = 1;
                }else{//tag = 3
                    i--;
                    while(i != 1){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        i--;
                    }
                    arr[1][0] = count++;
                    tag[1][0] = 1;
                }
            }else{//第 1 圈之后
                if(tag1 % 4 == 0){
                    j++;
                    while(tag[i][j] != 1){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        j++;
                    }
                    j--;//
                }else if(tag1 % 4 == 1){
                    i++;
                    while(tag[i][j] != 1){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        i++;
                    }
                    i--;
                }else if(tag1 % 4 == 2){
                    j--;
                    while(tag[i][j] != 1){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        j--;
                    }
                    j++;
                }else{
                    i--;
                    while(tag[i][j] != 1){
                        arr[i][j] = count;
                        tag[i][j] = 1;
                        count++;
                        i--;
                    }
                    i++;
                }
            }
            tag1++;
        }
        //输出
        for(int a = 0; a < n; ++a){
            for(int b = 0; b < m; ++b){
                System.out.printf("%d ",arr[a][b]);
            }
            System.out.println();
        }
    }
}
```







### 字符串

#### 772只出现一次的字符

![image-20240428202719722](./SpringBoot 框架课.assets/image-20240428202719722.png) 



注意：这里的 ascci 转化，还可以用，每个字母减去 a

```
'a' - 'a' = 0;
'b' - 'a' = 1;
'c' - 'a' = 1;
```



代码

```java
package com.godice

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        //读入
        int[] count = new int[26];
        int[] sequence = new int[26];
        int index = 1;//字母出现的顺序
        for(int i = 0; i < s.length(); ++i){
            count[(int)(s.charAt(i) - 97)]++;
            if(sequence[(int)(s.charAt(i) - 97)] == 0){
                sequence[(int)(s.charAt(i) - 97)] = index;
                index++;
            }
        }
        int first_alpha = 0;
        int first_sequence = 26;
        for(int i = 0; i < 26; ++i){
            if(count[i] == 1){
                if(first_sequence > sequence[i]){
                     first_sequence = sequence[i];
                     first_alpha = i;
                }
            }
        }
        if(first_sequence == 26){
            System.out.println("no");
        }else{
            System.out.println((char)(first_alpha + 97));
        }
//        System.out.printf("这是第%d出现的字母\n",first_sequence);
//        System.out.printf("这个字母是%s",(char)(first_alpha + 97));

    }
}
```









#### 770 单词替换



<img src="./SpringBoot 框架课.assets/image-20240428205700703.png" alt="image-20240428205700703" style="zoom:67%;" /> 



这道题有 bug，题目要求的是全部替换，但是这里好像正确答案是只替换第一个

代码如下

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String Line1 = sc.nextLine();
        String Line2 = sc.nextLine();
        String Line3 = sc.nextLine();
        System.out.println(Line1.replace(Line2, Line3));
        
    }
}
```

但是是错误的

<img src="./SpringBoot 框架课.assets/image-20240428210543368.png" alt="image-20240428210543368" style="zoom:67%;" /> 











#### 775倒排单词



![image-20240428213756030](./SpringBoot 框架课.assets/image-20240428213756030.png) 



倒序，再倒序

```java
import java.util.Arrays;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuffer sb = new StringBuffer(s);
        sb.reverse();
        String s1 = String.valueOf(sb);
        //System.out.println(sb);
        String[] arr = s1.split(" ");
        //System.out.println(Arrays.toString(arr));
        for(int i = 0; i < arr.length; ++i){
            for(int j = arr[i].length() - 1; j >= 0; --j){
                System.out.printf("%s",arr[i].charAt(j));
            }
            System.out.printf(" ");
        }
    }
}
```







#### 779最长公共子串后缀



<img src="./SpringBoot 框架课.assets/image-20240429155227740.png" alt="image-20240429155227740" style="zoom:67%;" /> 



正确版本

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            int n = sc.nextInt();
            if(n == 0) break;
            String[] strs = new String[n];
            for(int i = 0; i < n; ++i){
                strs[i] = sc.next();
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 1; i <= strs[0].length(); ++i){
                char c = strs[0].charAt(strs[0].length() - i);
                boolean flag = true;
                for(int j = 1; j < n; ++j){
                    if(i > strs[j].length() || strs[j].charAt(strs[j].length() -i) != c){
                        flag = false;
                        break;
                    }
                }
                if(flag) sb.append(c);
                else break;
            }
            sb.reverse();
            System.out.println(sb);
        }
    }
}

```





获取一个输出的版本

```java
package com.godice;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String[] arr1 = new String[N];
        for(int i = 0; i < N; ++i){
            arr1[i] = sc.nextLine();
        }
        int tag = 1;
        int i = 1;
        String subString = "";
        while(tag == 1){
            String tmp = "";
            for(int j = 0; j < N - 1; j++){
                if(arr1[j].length() - i < 0 || arr1[j + 1].length() - i < 0){//如果全是后缀：一模一样
                    tag = 0;
                    break;
                }
                if(arr1[j].charAt(arr1[j].length() - i) != arr1[j + 1].charAt(arr1[j + 1].length() - i)){
                    tag = 0;
                    break;
                }
                if(j == N - 2){
                    tmp += arr1[j].charAt(arr1[j].length() - i);
                }
            }
            subString += tmp;
            ++i;
        }
        System.out.println(subString);
    }
}
```











### 类&接口



#### 28就地删除单链表节点

![image-20240511171328376](./SpringBoot 框架课.assets/image-20240511171328376.png) 



```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        ListNode p = new ListNode(0);
        p = node.next;
        node.val = p.val;
        node.next = p.next;
    }
}
```





#### 35反转链表

![image-20240514115915855](./SpringBoot 框架课.assets/image-20240514115915855.png) 



**1迭代版本**

 

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return head;
        }else{
            ListNode prev = head;
            ListNode p = prev.next;
            head.next = null;
            while(p != null){
            ListNode tmp = p.next;
            p.next = prev;
            prev = p;
            p = tmp;
            }
            return prev; 
        }
    }
}
```





```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode p = head;
        while(p != null){
            ListNode tmp = p.next;
            p.next = prev;
            prev = p;
            p = tmp;
        }
        return prev;
    }
}
```





**2 递归版本**

<img src="./SpringBoot 框架课.assets/image-20240514213616313.png" alt="image-20240514213616313" style="zoom:67%;" />



![image-20240514213500603](./SpringBoot 框架课.assets/image-20240514213500603.png)









## 2.1项目&git

King of Bots（KOB）

<img src="./SpringBoot 框架课.assets/image-20240515173606586.png" alt="image-20240515173606586" style="zoom:67%;" />  

### 1：项目设计

#### 1.1 项目基本思路

蛇类游戏的Ai 对战平台 ：

- 每个用户 user：可以创建多个 ai 机器人（bot），但是同一时间只能派出一个机器人出站（每个机器人就是一段代码）
  - 每个 bot 其实就是一个函数：每个时刻需要输出下一步需要走的方向（0123）
    - 函数的输入是棋盘的局面——棋盘的布局，两条蛇分别在哪些位置、蛇头的位置、障碍物、地图大小...
    - 根据输入每个时刻需要有输出——选择下一个时刻走的位置

- 在对局中：对战的两个机器人同一时刻会同时选择下一步怎么走（上下左右）——撞墙，撞自己/对方，无输出，则死亡

  - 每次对局后会有天梯分的更新（天梯分以bot 为绑定）
  - 为了方便调试：出站的时候可以选择 1：人出战 2：bot 出战
  - 同时支持真人 PK

- 每个用户：注册完账号后

  - 可以写若干个 bot
  - 在同一时间只能派出一个 bot 出站，和其他用户 PK（避免用一个 bot代码复制很多分，霸榜）

  

  



#### 1.2 功能模块

数据库：存储用户注册信息、bot 信息、对局记录信息、天梯分...

微服务：匹配系统、每局对战的模拟（后台的运算）

...







#### 1.3项目细分

**项目基本构思完成后，需要对项目进行不断细分——本项目可以按照不同的页面进行细分**

![image-20240515180347367](./SpringBoot 框架课.assets/image-20240515180347367.png)

 ![image-20240515181435927](./SpringBoot 框架课.assets/image-20240515181435927.png)





**前后端分离**

一套后端代码可以对应多个前端——网站、小程序、APP...



##### 网页端

<img src="./SpringBoot 框架课.assets/image-20240515182114899.png" alt="image-20240515182114899" style="zoom: 50%;" /> 





##### APP 端

<img src="./SpringBoot 框架课.assets/image-20240515182226644.png" alt="image-20240515182226644" style="zoom:50%;" /> 







### 2：git 

- git 功能
  - 1存档：可以commit存档，看到历史所有代码版本
  - 2同步：同步不同机器上的代码
    - github
    - acgit
  - 3多人协作



#### **生成密钥**

```
1：家目录ssh-keygen(一路回车)
2: cd .ssh
id_rsa私钥 id_rsa.pub公钥
3：复制公钥到 github
```

<img src="./SpringBoot 框架课.assets/image-20240516151441615.png" alt="image-20240516151441615" style="zoom:50%;" />



#### 创建项目

[github 创建项目操作](https://blog.csdn.net/zhangkeke_/article/details/82864290)

##### 本地创建

1：任意文件夹，创建项目文件夹（自己命名），这里命名 kob

2：在项目文件夹中初始化 git

```
git init
```

![image-20240516151907529](./SpringBoot 框架课.assets/image-20240516151907529.png) 

3：一般都需要创建一个 readme 文件

```
vim readme.md
```

4：查看当前文件下那些文件没有被git 收录

![image-20240516152346775](./SpringBoot 框架课.assets/image-20240516152346775.png) 

5：收录当前目录下所有未收录文件

```
git add .
```

![image-20240516152451487](./SpringBoot 框架课.assets/image-20240516152451487.png) 

6：保存当前版本

```
git commit -m "创建项目
```



##### 云端创建项目

（云端同步本地的项目）

注意这里是选repositories，不是 projects

![image-20240516160601447](./SpringBoot 框架课.assets/image-20240516160601447.png) 



![image-20240516160633694](./SpringBoot 框架课.assets/image-20240516160633694.png)

这里要选择 ssh

![image-20240516161308182](./SpringBoot 框架课.assets/image-20240516161308182.png)



1：git全局设置

```
git config --global user.name "godice"
git config --global user.email "382855266@qq.com
（global 是全部 git 仓库都用这个配置，这里建议取消 global）
```



2：推送现有文件夹

```
关联到远程仓库地址
git remote add origin https://github.com/users/godicewang/projects/1/views/1
(修改 origin 地址)
git remote set-url origin https://github.com/godicewang/Kob.git
查看哪些文件还没有推送到远程
git status
添加当前文件夹下还没同步的文件到远程
git add .
记录当前版本并取名
git commit -m "创建文件"
本地库内容推送到远程
（-u 是推送内容并关联分支，第一次 push 时使用即可，后续更新不需要）
git push -u origin main
```

![image-20240516161411055](./SpringBoot 框架课.assets/image-20240516161411055.png)





##### 代码同步

下载文件到本地

```
git clone https://github.com/godicewang/Kob.git
```

![image-20240516161928066](./SpringBoot 框架课.assets/image-20240516161928066.png) 



更新后上传文件（删除也是一样的）

```
git status 
git add. 
git commit -m "test"
git push
```

![image-20240516162313858](./SpringBoot 框架课.assets/image-20240516162313858.png) 





在另一台电脑上更新仓库（别人/别的电脑上修改的）

```
git pull
（拉取下来）
```





### 3：前后端概念

- 服务器的本质：接收用户的函数调用，返回一个结果
  - 访问页面时，函数的参数都在 url 中传递
  - 服务器处理，返回结果是一个HTML文件（本质上是字符串）
    - 会交给客户端本地的浏览器解析，呈现页面
- 服务器端——客户端
  - 前后端都是存在服务器端上的



![image-20240516173149501](./SpringBoot 框架课.assets/image-20240516173149501.png)



**前后端分离/不分离**

- 前后端不分离
  - 客户端发送 url（函数调用的请求后），服务器端直接返回 html 的字符串
  - html 是根据数据直接在服务器端生成的
- 优点：一个后端可以支持多个前端
  - 数据是一样的
  - 只是前端展示的格式不一样 app、web...

<img src="./SpringBoot 框架课.assets/image-20240516173513752.png" alt="image-20240516173513752" style="zoom:50%;" />

- 前后端分离：
  - 1：用户打开一个网站，首先是从某个地方传递网站的静态文件到客户端
    - 包含一些 html、js、css 文件，但是里面的一些数据具体数据先不返回
  - 2：用户再向服务器发起请求（获取数据）
  - 3：服务器返回数据（eg：json 文件）
    - 相当于传一个数组，每个元素是一个结构体（包含了相关的具体数据）
  - 4：前端接收到数据后，再动态将这些数据填充到页面里面



- 注意：渲染（字符串拼接）
  - 如果 Html 文件的渲染（拼接）操作是在用户浏览器用 js 实现，就是前后端分离
  - 如果 Html 文件的渲染（拼接）操作是在java 中直接实现，就是前后端不分离







### 4：MVC

每一个链接（url）对应一个（后端的）函数调用

（每一个链接对应一个函数名）



- MVC
  - Contorller：每个函数相当于一个 Controller
    - 负责向用户返回一个数据的控制器
  - View：视图（Html）
  - Model：数据库









### 5：创建项目

（这里 spring 不支持 java8了，只能用阿里云）

```
https://start.spring.io/加载慢的话，可以换成：https://start.aliyun.com
```

![image-20240516181233297](./SpringBoot 框架课.assets/image-20240516181233297.png) 

创建项目配置如下

**注意：这里需要改成 Maven 类型！！！**

<img src="./SpringBoot 框架课.assets/image-20240516182454846.png" alt="image-20240516182454846" style="zoom:67%;" />

依赖一个 spring web 即可

<img src="./SpringBoot 框架课.assets/image-20240516182640792.png" alt="image-20240516182640792" style="zoom:67%;" /> 

勾选一个 Thymeleaf，用于演示前后端不分离（只做一个页面）

![image-20240516182726556](./SpringBoot 框架课.assets/image-20240516182726556.png) 



等待下载和自动配置（等很久）：可以看到服务开到了 8080

![image-20240517143845945](./SpringBoot 框架课.assets/image-20240517143845945.png)





### 6：前后端不分离

#### 6.1目录结构

新建 controller 软件包，用于存储后端函数（函数对应每个 url链接）

![image-20240517144959419](./SpringBoot 框架课.assets/image-20240517144959419.png) 



项目分为四个模块：pk、排行、对战列表、用户中心



在 controller 中再创建一个 pk 包，再在其中新建一个 java 类

![image-20240517145600766](./SpringBoot 框架课.assets/image-20240517145600766.png) 

（名字随便起，一般叫 XXXController）

这里取名 IndexController，表示 PK 页面的主页面函数



然后需要加上一个注解——把其变成链接对应的函数

![image-20240517145914772](./SpringBoot 框架课.assets/image-20240517145914772.png) 



绑定对应目录(父目录)

![image-20240517150143497](./SpringBoot 框架课.assets/image-20240517150143497.png) 





#### 6.2 目录绑定templates

1：resources——创建 templates 目录——创建 pk 目录——创建 index的 html

![image-20240517151104949](./SpringBoot 框架课.assets/image-20240517151104949.png)



2：绑定

- html 文件存在 templates 下
  - 写路径也从 templates 后面开始写

![image-20240522172053042](./SpringBoot 框架课.assets/image-20240522172053042.png)





#### 6.3目录绑定static

static 中一般有三个文件目录：css、js、image

- 静态文件的路径也是从 static 后面开始写 

![image-20240522190531584](./SpringBoot 框架课.assets/image-20240522190531584.png) 

/pk/index/

![image-20240522191648030](./SpringBoot 框架课.assets/image-20240522191648030.png)



前后端不分离写法

（注意：这里 Controller 默认返回字符串的话，会去 static 里面找模板）

（注意：这里 RestController 默认返回数据，不会去 static 里面找模板）

![image-20240522191934528](./SpringBoot 框架课.assets/image-20240522191934528.png)

### 7 前后端分离-后端

#### 7.1 链接格式

返回格式可以有很多：字符串、链表、字典、链表嵌套字典（list 中的每一项都是一个 map）

代码

![image-20240522192856686](./SpringBoot 框架课.assets/image-20240522192856686.png) 

显示

![image-20240522192842280](./SpringBoot 框架课.assets/image-20240522192842280.png) 



#### 7.2 嵌套返回

**1：返回链表 list**

![image-20240522193807473](./SpringBoot 框架课.assets/image-20240522193807473.png) 

```java
package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

//前后端分离
@RestController
@RequestMapping("/pk/")
public class BotInfoController {
    @RequestMapping("getBotInfo/")
    public List<String> getBotInfo(){
        List<String> list1 = new LinkedList<>();
        list1.add("Tiger");
        list1.add("Athena");
        list1.add("Monkey");
        return list1;
    }
}
```

![image-20240522194707863](./SpringBoot 框架课.assets/image-20240522194707863.png) 



**2：返回字典 map**

```java
package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//前后端分离
@RestController
@RequestMapping("/pk/")
public class BotInfoController {
    @RequestMapping("getBotInfo/")
    public Map<String, String> getBotInfo(){
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "tiger");
        map1.put("rating", "1500");
        return map1;
    }
}
```

![image-20240522194902541](./SpringBoot 框架课.assets/image-20240522194902541.png) 

**3：返回一个嵌套链表（链表中的每个元素是一个 map）**

```java
package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//前后端分离
@RestController
@RequestMapping("/pk/")
public class BotInfoController {
    @RequestMapping("getBotInfo/")
    public List<Map<String, String>> getBotInfo(){
        List<Map<String, String>> list_map = new LinkedList<>();
        Map<String, String> bot1 = new HashMap<>();
        bot1.put("name", "bot1");
        bot1.put("rating", "1500");
        Map<String, String> bot2 = new HashMap<>();
        bot2.put("name", "bot2");
        bot2.put("rating", "1300");
        list_map.add(bot1);
        list_map.add(bot2);
        return list_map;
    }
}
```

![image-20240522195352423](./SpringBoot 框架课.assets/image-20240522195352423.png) 







#### 7.3修改端口

这里可以修改一下端口：因为很多默认端口都是 8080

vue 的默认端口也是 8080，不修改的话，前后端会冲突，这里修改为 3000

![image-20240522195615993](./SpringBoot 框架课.assets/image-20240522195615993.png) 





### 8：前端创建

![image-20240522201054501](./SpringBoot 框架课.assets/image-20240522201054501.png) 



#### 8.1vue3 安装

[vue3 安装参考](https://www.acwing.com/blog/content/20724/)



1：安装no'de.js

```
https://nodejs.org/en/
```



2：安装vue

```
sudo npm i -g @vue/cli
或者
npm i -g @vue/cli@4
（这个是早期版本）
```



3：启动 vue 图形化

```
vue ui
```

![image-20240522202234145](./SpringBoot 框架课.assets/image-20240522202234145.png)



#### 8.2vue 创建项目

进入项目中创建项目

![image-20240522202623109](./SpringBoot 框架课.assets/image-20240522202623109.png) 

创建

<img src="./SpringBoot 框架课.assets/image-20240522202702452.png" alt="image-20240522202702452" style="zoom:50%;" />

选择 vue3

<img src="./SpringBoot 框架课.assets/image-20240522202730171.png" alt="image-20240522202730171" style="zoom:50%;" />



报错

```
premission deny
npm install --loglevel error --legacy-peer-deps
```



解决

```
sudo npm update -g npm
sudo vue ui
```



#### 8.3 安装插件

![image-20240522205234020](./SpringBoot 框架课.assets/image-20240522205234020.png)



#### 8.4 安装依赖

![image-20240522205446766](./SpringBoot 框架课.assets/image-20240522205446766.png)



```
jquery
bootstrap
```



#### 8.5运行

![image-20240522205720556](./SpringBoot 框架课.assets/image-20240522205720556.png) 

创建成功

![image-20240522205805944](./SpringBoot 框架课.assets/image-20240522205805944.png) 

项目截图

<img src="./SpringBoot 框架课.assets/image-20240522205821755.png" alt="image-20240522205821755" style="zoom:50%;" /> 



#### 8.6 额外创建 ACAPP

在/kob/目录下再创建一个 web 项目：acapp

装一下插件：vuex

(注意前面的 web 端也应该在 kob 下面，前面装错位置了)



#### 8.7vscode权限

[参考文章](https://blog.csdn.net/qq_41569151/article/details/133270060?ops_request_misc=&request_id=&biz_id=102&utm_term=vscode%E6%9D%83%E9%99%90%E4%B8%8D%E8%B6%B3%E6%97%A0%E6%B3%95%E4%BF%9D%E5%AD%98&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-5-133270060.142^v100^pc_search_result_base8&spm=1018.2226.3001.4187)





#### 8.8路径设置

<img src="./SpringBoot 框架课.assets/image-20240522215650766.png" alt="image-20240522215650766" style="zoom:50%;" /> 



项目路径中多一个#，比较麻烦

在该文件中去掉两处的Hash即可

![image-20240522215808070](./SpringBoot 框架课.assets/image-20240522215808070.png) 

即可去掉#

![image-20240522215920889](./SpringBoot 框架课.assets/image-20240522215920889.png) 









#### 8.9删除冗余文件

![image-20240522220416803](./SpringBoot 框架课.assets/image-20240522220416803.png)



![image-20240522220608464](./SpringBoot 框架课.assets/image-20240522220858229.png)





![image-20240522221039429](./SpringBoot 框架课.assets/image-20240522221039429.png)







### 9：通信&前端

#### 9.1vue前端

- template写 html
- script 写 js
- style 写 css

```js
<template>
  <div>
    Hello World
  </div>
  <router-view> </router-view>
</template>

<script>

</script>



<style>
</style>

```



## 2.2vue3

### 1：vue3基础

（简易 qq 空间项目——前后端不分离）

[网站整体布局，用户动态页面](https://www.acwing.com/file_system/file/content/whole/index/content/5645302/)

[用户列表，登录注册页面](https://www.acwing.com/file_system/file/content/whole/index/content/5681445/)

- 安装插件
  - router路由——多页面网站应用
  - vuex 多组件间维护同一个数据
- 安装依赖
  - bootstrap 美工
  - jquery



<img src="./SpringBoot 框架课.assets/image-20240523151545453.png" alt="image-20240523151545453" style="zoom: 50%;" /> 

- 目录结构
  - views：写各种页面
  - router：写路由（url 路由）
  - components：存各种组件（views 也可以存组件，看使用习惯）
  - App.vue：根组件
  - main.js：整个入口



main.js

```javascript
import { createApp } from 'vue'
import App from './App.vue'//导入根组件
import store from './store'//vuex（多组件维护同一个数据）
import router from './router'//路由

createApp(App).use(router).use(store).mount('#app')//创建App和组件后——挂载到app处
```

<img src="./SpringBoot 框架课.assets/image-20240523153122916.png" alt="image-20240523153122916" style="zoom:67%;" />



- html
- js
- css

在 CSS 标签中可以加一个 scoped——不同组件间的CSS选择器，不会相互影响

![image-20240523161925766](./SpringBoot 框架课.assets/image-20240523161925766.png)



- 在页面中的不同模块：都可以用不同的组件实现
  - 每个页面分为多个不同模块
  - 每个模块都可以单独实现，包含对应的代码：
    - html
    - js
    - css



<img src="./SpringBoot 框架课.assets/image-20240523162542674.png" alt="image-20240523162542674" style="zoom:50%;" /> 



![image-20240523162821613](./SpringBoot 框架课.assets/image-20240523162821613.png)





### 2：项目架构思路

- 导航栏
- 内容
  - 首页
  - 好友列表
  - 好友动态
  - 登录
  - 注册
  - 404
- 注意：每个页面作为一个组件来实现
  - 如果一个页面过大，可以将其拆分为多个组件实现

<img src="./SpringBoot 框架课.assets/image-20240523164728548.png" alt="image-20240523164728548" style="zoom:67%;" /> 



### 3：导航栏组件

删除 HelloWord 组件和about 组件（且删除引用了它的对应组件中的链接）



1：在 component 中创建导航栏组件

2：创建三个部分template、script、style（style 中必须记得加 scoped）





[找到 navbar，选一个合适的模板](https://v5.bootcss.com/docs/components/navbar/)

```html
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar w/ text</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Pricing</a>
        </li>
      </ul>
      <span class="navbar-text">
        Navbar text with an inline element
      </span>
    </div>
  </div>
</nav>
```





### 4：内容组件

- 导航栏
- 内容
  - 首页
  - 好友列表
  - 好友动态
  - 登录
  - 注册
  - 404





#### 4.1 首页

这里就用本身的组件加以修改

![image-20240524134550912](./SpringBoot 框架课.assets/image-20240524134550912.png)



用 Bootstrap 中的模板实现：cards

```html
<div class="card" style="width: 18rem;">
  <img src="..." class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">Card title</h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
    <a href="#" class="btn btn-primary">Go somewhere</a>
  </div>
</div>
```





#### 4.2 组件模板

因为很多内容的组件部分都是一致的，为了方便维护，多个不同的组件，可以使用一个组件作为模板，引入后稍作修改即可



ContentBase文件

```html
<!--本文件作为内容组件的模板使用-->
<template>
  <div class="home">
    <div class="container">
      <div class="card">
        <div class="card-body">
          <slot></slot><!--内容占位-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default{
    name: "ContentBase",
}
</script>

<style scoped>
.container{
  margin-top: 20px;
}

</style>
```

HomeView引入

```html
<template>
  <ContentBase>
    首页
  </ContentBase>
</template>

<script>
import ContentBase from '../components/ContentBase.vue';

export default {
  name: 'HomeView',
  components: {
    ContentBase,
  }
}
</script>

<style scoped>

</style>

```



#### 4.3 模板应用

一样的，稍加修改即可，复制六份

- 首页
- 好友列表
- 好友动态
- 登录
- 注册
- 404

```html
<!--好友列表-->
<template>
<ContentBase>
    好友列表
</ContentBase>
</template>


<script>
import ContentBase from '../components/ContentBase';

export default{
    name: 'UserLsit',
    components:{
        ContentBase,
    }
}
</script>


<style scoped>

</style>
```

写好的文件目录如下：

![image-20240524154516608](./SpringBoot 框架课.assets/image-20240524154516608.png) 







### 5：路由

根据不同的 url，显示不同的页面

```javascript
import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
```



引入组件，并添加路由

index.js

```javascript
import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import NotFoundView from '../views/NotFoundView.vue';
import RegisterView from '../views/RegisterView.vue';
import UserListView from '../views/UserLsitView.vue';
import UserProfileView from '../views/UserProfileView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/404',
    name: '404',
    component: NotFoundView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/userlist',
    name: 'userlist',
    component: UserListView
  },
  {
    path: '/userprofile',
    name: 'userprofile',
    component: UserProfileView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

```





### 6：主页路由跳转

#### 6.1非前端渲染

可以用直接修改#为url的方式，但是这种方式每次跳转会重新刷新一次页面，不属于前端跳转

（还是从服务器请求的）

```html
<template>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <a class="navbar-brand" href="/">MySpace</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link" href="/">首页</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/userlist">好友列表</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/userprofile">用户动态</a>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="/login">登录</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/register">注册</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>

<script>
export default{
    name:"NavBar",
}


</script>


<style scoped>
.container{
    max-width: 1200px;
    font-size: 26px;
}

.navbar-brand{
    font-size: 42px;
}
</style>
```





#### 6.2前端渲染

一个类似<a>的标签

```
<router-link class="navbar-brand" :to="{name: home}">MySpace</router-link>
```

NavBar.vue代码 

```html
<template>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container">
    <router-link class="navbar-brand" :to="{name: home}">MySpace</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'home'}">首页</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'userlist'}">好友列表</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'userprofile'}">用户动态</router-link>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'login'}">登录</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'register'}">注册</router-link>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>

<script>
export default{
    name:"NavBar",
}


</script>


<style scoped>
.container{
    max-width: 1200px;
    font-size: 26px;
}

.navbar-brand{
    font-size: 42px;
}
</style>
```











## 跨域问题（前后端分离）

前后端的域名不一样，添加配置类即可

添加配置类：CorsConfig

在backend根目录下，创建软件包 config创建类 CorsConfig

```java
package com.kob.backend.config;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CorsConfig implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");
        if(origin!=null) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }

        String headers = request.getHeader("Access-Control-Request-Headers");
        if(headers!=null) {
            response.setHeader("Access-Control-Allow-Headers", headers);
            response.setHeader("Access-Control-Expose-Headers", headers);
        }

        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {
    }
}
```

控制台获取信息

![image-20240530210131816](./SpringBoot 框架课.assets/image-20240530210131816.png)







## 3.1创建菜单（导航栏）

实现如图

- 需要实现两个功能——导航栏&每个页面对应的不同内容
  - 导航栏一个组件
  - 每个页面的内容，一个组件

![image-20240530221342985](./SpringBoot 框架课.assets/image-20240530221342985.png)





### 导航栏&下拉菜单

bootstrap实现

![image-20240531154307902](./SpringBoot 框架课.assets/image-20240531154307902.png)

代码

```html
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Navbar w/ text</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Pricing</a>
        </li>
      </ul>
      <span class="navbar-text">
        Navbar text with an inline element
      </span>
    </div>
  </div>
</na
```

下拉菜单

![image-20240531171835820](./SpringBoot 框架课.assets/image-20240531171835820.png)

效果如图

![image-20240531172023365](./SpringBoot 框架课.assets/image-20240531172023365.png)







### 内容页面

写到 view 中（components 也可以）——每个页面建一个文件夹（每个页面可能有多个组件）

- 游戏对战：pk
- 对局列表：record
- 排行榜：ranklist
- 我的 bot：UserBot
- 错误：404

![image-20240531173453473](./SpringBoot 框架课.assets/image-20240531173453473.png) 





### 路由设置&点击跳转&卡片

路由设置

```javascript
import { createRouter, createWebHistory } from 'vue-router'
import NotFound from '@/views/error/NotFound'
import PkindexView from '@/views/pk/PkIndexView.vue'
import RanklistIndexView from '@/views/ranklist/RanklistIndexView.vue'
import RecoedIndexView from '@/views/record/RecordIndexView.vue'
import UserBotIndexView from '@/views/user/bot/UserBotIndexView.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/pk/',
  },
  {
    path: '/pk/',
    name: 'pk_index',
    component: PkindexView,
  },
  {
    path: '/record/',
    name: 'record_index',
    component: RecoedIndexView,
  },
  {
    path: '/ranklist/',
    name: 'ranklist_index',
    component: RanklistIndexView,
  },
  {
    path: '/user/bot',
    name: 'user_bot_index',
    component: UserBotIndexView ,
  },
  {
    path: '/404/',
    name: '404',
    component: NotFound,
  },
  { path: '/:catchALl(.*)',
    redirect: "/404/",
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

```

点击跳转

```html
<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">  
    <router-link lass="navbar-brand" :to="{name: 'home'}">King of Bots(Snake)</router-link>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" aria-current="page" :to="{name: 'pk_index'}">游戏对战</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'record_index'}">对局列表</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'ranklist_index'}">排行榜</router-link>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Godice
          </a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" :to="{name: 'user_bot_index'}">我的bot</router-link></li>
            <li><hr class="dropdown-divider"></li>
            <li><router-link class="dropdown-item" :to="{name: 'home'}">退出</router-link></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>

<script>
</script>

<style scoped>

</style>
```



卡片包裹内容

<img src="./SpringBoot 框架课.assets/image-20240601104833221.png" alt="image-20240601104833221" style="zoom:50%;" /> 





**用组件实现一个卡片包含内容的功能**

组件代码

```html
<template>
    <div class="container content-field">
        <div class="card">
            <div class="card-body">
                <slot></slot>
            </div>
        </div>
    </div>
</template>

<script>

</script>


<style scoped>
div.content-field {
    margin-top: 20px;
}

</style>
```

导入代码

```html
<template>
    <ContentField>
        对战页面
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"

export default{
    components:{
        ContentField
    }
}   
</script>

<style scoped>

</style>
```







### 页面高亮

需求：显示哪个页面，在导航栏中，哪个页面的字体就高亮显示。（在对应 class 中加上 active，即可高亮显示）

- 思路：
  - 取回当前页面的 url
  - 判断是哪个 url
  - 在对应模块中加上 active



原代码备份

```html
<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">  
    <router-link class="navbar-brand" :to="{name: 'home'}">King of Bots(Snake)</router-link>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link class="nav-link" aria-current="page" :to="{name: 'pk_index'}">游戏对战</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'record_index'}">对局列表</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'ranklist_index'}">排行榜</router-link>
        </li>
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Godice
          </a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" :to="{name: 'user_bot_index'}">我的bot</router-link></li>
            <li><hr class="dropdown-divider"></li>
            <li><router-link class="dropdown-item" :to="{name: 'home'}">退出</router-link></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>

<script>
import { useRoute } from 'vue-router';
import { computed } from 'vue';

export default{
  setup(){
    const route = useRoute();
    let route_name = computed(()=> route.name);
    return route_name;
  }
}

</script>

<style scoped>

</style>
```











## 3.2游戏页面



### 要求

- 要求：
  - 1 地图轴对称：为了公平（障碍物的刷新也是对称的）
  - 2 两只蛇分别出生在左上角和右下角（障碍物刷新后，两条蛇的位置必须是连通的）
  - 3 地图是正方形，有一圈围栏

<img src="./SpringBoot 框架课.assets/image-20240601122039195.png" alt="image-20240601122039195" style="zoom:50%;" />







### 物体移动

实现方法：地图每秒刷新 60 次（次数可以调整）

计算物理在每一帧应该出现的位置（重新渲染）







- 实现一个基类：所有图像都应该继承自这个类，有一定的刷新频率
- 第二个基类：墙、地图。
  - 游戏区域组件
- 第三个基类：蛇

![image-20240603160346001](./SpringBoot 框架课.assets/image-20240603160346001.png) 









### 相对距离与绝对距离

因为根据浏览器页面的拉大/缩小，对战区域也会对应变化，所以计算时要考虑相对距离和绝对距离。



- 在 Playground （蓝色）中，用一个 GameMap （红色正方形）对战
- 这个红色对战区域由很多正方形小格子组成：要求对战区域尽可能大
  - 小格子的边长：min{h/rows ,  w / cols}

<img src="./SpringBoot 框架课.assets/image-20240604111152425.png" alt="image-20240604111152425" style="zoom:50%;" /> 







### 地图颜色



canvas 坐标系

<img src="./SpringBoot 框架课.assets/image-20240604171535772.png" alt="image-20240604171535772" style="zoom:50%;" /> 































### 关于 export default

js中有两种 export 方式——加不加（default）

```javascript
export{}
export default{
  components:{
  }
}//类似 java 中的 public，一个文件只能有一个 default
```

如果是 default 的导入就不用加括号，不是 default的导入需要加括号

 

这里加括号了

![image-20240603161423998](./SpringBoot 框架课.assets/image-20240603161423998.png)

因为这里没有default

![image-20240603161457003](./SpringBoot 框架课.assets/image-20240603161457003.png)











## 3.3蛇的实现



### 游戏地图改良

问题：如果两条蛇同一时刻，可以进入同一格子，那么就会出现对优势者不利的情况。

- 修改地图：从正方形修改——增加一行（变为矩形）
  - 最开始两条蛇的坐标之和就分为别为偶数和奇数
  - 每条蛇下一时刻的坐标之和的奇偶性都会改变
  - 这样两条蛇就永远走不到同一个格子
- 但是修改后地图不能轴对称，需要修改为中心对称



注意：现在地图的生成逻辑是在前端实现的——有安全问题（后期需要调整到后端实现）

<img src="./SpringBoot 框架课.assets/image-20240605212444466.png" alt="image-20240605212444466" style="zoom:67%;" />









### 关于蛇&坐标变换



#### 坐标变换

Cell类实现

前 10 次走动，每次蛇变成 1 个单位。之后每3 次走动，蛇边长一个单位

蛇是在中心画圆，应该是中心点坐标，而不是小方格的左上角坐标

![image-20240605214403477](./SpringBoot 框架课.assets/image-20240605214403477.png)

#### 蛇的移动



蛇的连续移动，如图，蛇的移动会有缺口——首部抛出球，尾部到达下一个点后删除（如果要变长，尾部就不删）

<img src="./SpringBoot 框架课.assets/image-20240606142238046.png" alt="image-20240606142238046" style="zoom:50%;" />

- 头部：抛出新的圆球
- 尾部：
  - 蛇需要变长：不用做处理
  - 蛇不需要变长：
    - 尾部向前移动一个格子（每帧刷新，慢慢移动）
      - 慢慢移动到误差很小的时候，两个圆形会接近重合，然后再删除尾部
    - 移动后删除尾部



#### 蛇的美化

目前蛇还是一个个圆球状态，很丑

![image-20240615172432615](./SpringBoot 框架课.assets/image-20240615172432615.png) 

- 解决：在每个身体的两个圆形之间加上一个长方形
  - 两个圆的切点和直径中间的位置涂满色 	
  - 直接 snake 的render 中实现即可

![image-20240615172548318](./SpringBoot 框架课.assets/image-20240615172548318.png) 





```javascript
for(const cell of this.cells){
  ctx.beginPath();//开启一个路径
  ctx.arc(cell.x * L, cell.y * L, L / 2, 0, Math.PI * 2)//前两个坐标是小圆的中点，后一个坐标是半径，最后两个是圆弧的起始和终止角度 
  ctx.fill();
}
for(let i = 1; i < this.cells.length; ++i){//给蛇身画线
  const a = this.cells[i], b = this.cells[i - 1];
  if(Math.abs(a.x - b.x) < this.eps && Math.abs(a.y - b.y) < this.eps){//两个圆很接近则不画线（适用于尾部移动的边界判断）
      continue;
  }
  //两种情况进行画线（横向和纵向的坐标计算）
  if(Math.abs(a.x - b.x) < this.eps){//纵向排列
      ctx.fillRect((a.x - 0.5) * L, Math.min(a.y, b.y) * L, L, Math.abs(a.y - b.y) * L);//(左上角横坐标，左上角纵坐标，宽度，长度)
  }else{//横向排列
      ctx.fillRect(Math.min(a.x, b.x) * L, (a.y - 0.5) * L, Math.abs(a.x - b.x) * L, L);
  }
}
```

**蛇变瘦**：圆形变瘦+横纵坐标处理

```javascript
for(const cell of this.cells){
    ctx.beginPath();//开启一个路径
    ctx.arc(cell.x * L, cell.y * L, L / 2 * 0.8, 0, Math.PI * 2)//前两个坐标是小圆的中点，后一个坐标是半径，最后两个是圆弧的起始和终止角度 
    ctx.fill();
}
for(let i = 1; i < this.cells.length; ++i){//给蛇身画线
    const a = this.cells[i], b = this.cells[i - 1];
    if(Math.abs(a.x - b.x) < this.eps && Math.abs(a.y - b.y) < this.eps){//两个圆很接近则不画线（适用于尾部移动的边界判断）
        continue;
    }
    //两种情况进行画线（横向和纵向的坐标计算）
    if(Math.abs(a.x - b.x) < this.eps){//纵向排列
        ctx.fillRect((a.x - 0.4) * L, Math.min(a.y, b.y) * L, L * 0.8, Math.abs(a.y - b.y) * L);//(左上角横坐标，左上角纵坐标，宽度，长度)
    }else{//横向排列
        ctx.fillRect(Math.min(a.x, b.x) * L, (a.y - 0.4) * L, Math.abs(a.x - b.x) * L, L * 0.8);
    }
}
```





#### 蛇的碰撞非法检测

撞围墙、撞障碍物、撞别人的身体、撞自己的身体

（检测功能不能在 snake 中实验，需要要公共地方实验——gamemap（后期会搬到后端））



需要特判的点

- 蛇在追自己（or 另一条蛇）的蛇尾
  - 如果下一步蛇尾缩了，走的就不是非法
  - 如果下一步蛇尾没有缩，走的就是非法的



**注意：**如果蛇头往回走，也是直接死亡，因为——如果是 bot 在控制，它返回的方向可能是往回走

如果往回走不死，只是操作无效的话，服务器重新获取操作，它可能会继续返回往回走的操作——就死循环了

（当然这里可以设置无效的次数，但是比较麻烦）

![image-20240616102303790](./SpringBoot 框架课.assets/image-20240616102303790.png)







#### 蛇画眼睛











**配置Mysql与注册登录模块**

## 4.1Spring/Mysql

SpringBoot操作Mysql配置，Mybits配置
SpringBoot各模块介绍
Controller层实现数据库增删查
配置用户登录安全机制

<img src="./SpringBoot 框架课.assets/image-20240617154424915.png" alt="image-20240617154424915" style="zoom:50%;" /> 





### 数据库

#### 安装/使用

[报错解决参考](https://blog.csdn.net/LYX_WIN/article/details/122697390)

```
home brew install mysql
//重启电脑后，需要执行以下两行代码重启mysql
sudo chown -R _mysql:_mysql /opt/homebrew/var/mysql
sudo mysql.server start/stop/restart
mysql -u root		//brew 安装的默认是没有密码的
```

[设置密码参考](https://developer.aliyun.com/article/1169557)





#### **数据库操作**

**mysql的常用操作**

- 连接用户名为root，密码为123456的数据库服务：mysql -uroot -p123456

- show databases;：列出所有数据库
- create database kob;：创建数据库
- drop database kob;：删除数据库
- use kob;：使用数据库kob
- show tables;：列出当前数据库的所有表
- create table user(id int, username varchar(100))：创建名称为user的表，表中包含id和username两个属性。
- drop table user;：删除表
- insert into user values(1, 'yxc');：**在表中插入数据**
- select * from user;：查询表中所有数据
- delete from user where id = 2;：删除某行数据

```
show databasee;
create database kob;
use kob;//使用某个数据库
use tables;//展示当前数据库的表
create table user(id int, username varchar(100), password varchar(100));
show tables;
drop database kob;//删除数据库
drop table user;//删除表
insert into user values(1, 'godice', 'p1');
delete from user where id = 1;
delete from user where username = 'godice';
select * from user;//查表中所有数据
select * from user where username = 'godice';
select * from user where id = 2;
```

(表中的值可以重复)

<img src="./SpringBoot 框架课.assets/image-20240618150217122.png" alt="image-20240618150217122" style="zoom:50%;" /> 

查表值

![image-20240618151502879](./SpringBoot 框架课.assets/image-20240618151502879.png) 





### idea操作数据库

![image-20240618154800352](./SpringBoot 框架课.assets/image-20240618154800352.png) 

选默认框架

<img src="./SpringBoot 框架课.assets/image-20240618155128835.png" alt="image-20240618155128835" style="zoom:67%;" /> 





### spring配数据库依赖







[Maven 仓库地址](https://mvnrepository.com/)

[MyBaits-Plus 官网](https://baomidou.com/)

- 在pom.xml文件中添加依赖：
  - 下面的依赖在 Maven 仓库中复制最新版到pom.xml中的dependency中即可
  - Spring Boot Starter JDBC
  - Project Lombok
    - 简化代码、自动写一些构造函数
  - MySQL Connector/J
  - mybatis-plus-boot-starter
    - MyBaits-Plus 可以帮默认写很多 sql 语句
  - mybatis-plus-generator
    - 自动生成函数
- 后面的几个暂时别装（进行下面的数据库配置）
  - spring-boot-starter-security
  - jjwt-api
  - jjwt-impl
  - jjwt-jackson
- 在application.properties中添加数据库配置：
  - 这个文件中其他都可以删除，只留下Server.port即可

```
spring.datasource.username=root
spring.datasource.password=981210//这里改成自己的数据库密码
spring.datasource.url=jdbc:mysql://localhost:3306/kob?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```







### 导入依赖报红

[参考文档](https://blog.csdn.net/wy329v1/article/details/125225689)

重新加载 maven 即可

![image-20240618173951689](./SpringBoot 框架课.assets/image-20240618173951689.png) 

清除缓存并重启就好了

![image-20240618172928544](./SpringBoot 框架课.assets/image-20240618172928544.png) 











### SpringBoot常用模块（层）

- SpringBoot中的常用模块
  - (pojo层上一层就是 mysql)
  - pojo层：将数据库中的表对应成Java中的Class
  - mapper层（也叫Dao层）：将pojo层的class中的操作，映射成sql语句
    - crud增删改查：create、read、update、delete
    - [查询 mapper 接口网站](https://baomidou.com/guides/data-interface/#select)
  - service层：写具体的业务逻辑，组合使用mapper中的操作
    - mapper 中是一些基本操作，对 mapper 操作组合——实现具体的业务
  - controller层：负责请求转发，接受页面过来的参数，传给Service处理，接到返回值，再传给页面
    - 负责：把前端请求&请求的参数接受后——选择将参数传给哪个 service、把 service 的结果再返回给前端







[mapper 层的一些接口（数据库增删改查操作））](https://baomidou.com/)





### crud 增删改查操作调试

Controller/user/UserController文件中的一些接口实现的调试

```java
package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.kob.backend.pojo.User;
import java.util.List;

@RestController//默认返回数据为json格式
public class UserController {

    @Autowired//需要用到数据库中的 Mapper，需要加的注解
    UserMapper userMapper;//Mybatis-pulse实现的 mapper 接口可以去查询https://baomidou.com/guides/data-interface/

    @GetMapping("/user/all/")//这里 RequestMapping 会映射所有请求，也可以用单个需要的请求
    public List<User> getAll() {
        return userMapper.selectList(null);//mapper 的接口查询所有用户
    }

    @GetMapping("/user/{userId}/")//按id 查表名
    public User getuser(@PathVariable int userId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return userMapper.selectOne(queryWrapper);
        //return userMapper.selectById(userId);
    }
    //删除插入一般都用 post，这里用 get 是为了方便调试
    @GetMapping("/user/add/{userId}/{username}/{password}/")//插入信息
    public String addUser(
            @PathVariable int userId,
            @PathVariable String username,
            @PathVariable String password){
        User user = new User(userId, username, password);
        userMapper.insert(user);
        return "Add User Successful ly";
    }

    @GetMapping("/user/delete/{userId}/")
    public String deleteUser(@PathVariable int userId){
        userMapper.deleteById(userId);
        return "Delete User Successfully";
    }

}
```







### 安全认证（授权机制）

#### spring-security 实现

eg：查看某个 bot 的代码（用户需要登录了账号，且该用户是这个 bot 的创建者，才能够查询）

(Spring-security和 shiro类似，shiro更容易上手，但是功能更少 )

- spring 中集成实现了模块，首先需要导入依赖
  - 1：maven 中复制
  - 2：导入 pom.xml
  - 3：刷新项目中的 maven 仓库

（从 maven 中搜索导入）

```
spring-boot-starter-security
```



注意：安装好后再访问，会自动跳转到login页面

```
/login
/logout
```

![image-20240701154140271](./SpringBoot 框架课.assets/image-20240701154140271.png)



Spring-security的默认用户密码

默认用户：user

默认密码：每次重新生成

![image-20240701154450040](./SpringBoot 框架课.assets/image-20240701154450040.png) 







#### session 控制机制

![image-20240701155047672](./SpringBoot 框架课.assets/image-20240701155047672.png)



- client 向服务器发起请求
  - SpringBoot 会去数据库中查询——把请求中的用户名密码做对比
  - 如果对比成功，则会返回 sessionID（可以理解为一个（唯一标识一个用户的）随机字符串）
- Client 拿到 sessionID 后，会把它存到本地的 Cookie 中
  - 之后每次发出请求，都会从 Cookie 中取出 sessionID
  - 放到 session 中传给 SpringBoot
- springboot会根据sessionid 到数据库中查询
  - session 中会包含（用户是谁、密码、过期时间）
  - spring 会查看它有没有过期&判断是不是对应用户发出的 sessionID（是否合法）
    - 如果没有过期，就不会再作判断了
    - 如果过期，会返回一个登录页面，让用户重新登录



注意：JWT控制机制，后面会讲，机制有点类似，但是更复杂，更安全





#### 修改 Spring security

（实现去数据库里查信息，实现用户的登录控制——而不是用默认的用户和密码登录）





```
service.impl.UserDetailsServiceImpl类继承自UserDetailsService接口，用来接入数据库信息
实现config.SecurityConfig类，用来实现用户密码的加密存储
```

![image-20240701160121394](./SpringBoot 框架课.assets/image-20240701160121394.png)



在backed/service/impl/UserDetailsServiceImpl中实现

![image-20240701160804619](./SpringBoot 框架课.assets/image-20240701160804619.png) 

 







这里接下来又遇到了报红

[Autowired 报红](https://blog.csdn.net/m0_70590680/article/details/136450965?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171982248516800213018141%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=171982248516800213018141&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-136450965-null-null.142^v100^pc_search_result_base8&utm_term=autowired%E6%8A%A5%E7%BA%A2&spm=1018.2226.3001.4187)

![image-20240701162942461](./SpringBoot 框架课.assets/image-20240701162942461.png)

这里添加了注解，成功不报红（这里不应该是 Mapper，应该是 @Service 注解）

![image-20240701163148118](./SpringBoot 框架课.assets/image-20240701163148118.png) 







#### 实现

这里使用了工具类中的方法实现返回了一个 user 的信息

![image-20240701172017843](./SpringBoot 框架课.assets/image-20240701172017843.png)



工具类代码如下（工具类是直接调用了 User 类中的无参构造函数自动生成，返回了用户名和密码）

```java
package com.kob.backend.service.impl.utils;

import com.kob.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {//作为UserDetailsServiceImpl的工具类（实现用户的访问控制重写）

    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {//是否没有没锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//授权是否没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {//用户是否被启用了，这里改为 True
        return true;
    }
}

```





然后这里直接调试会有问题（因为要求密码需要进行加密）

但是如果是调试需要，可以直接在数据库密码前加上{noop}，(noop 表示密码是用明文存储的)

<img src="./SpringBoot 框架课.assets/image-20240701172408560.png" alt="image-20240701172408560" style="zoom:67%;" /> 







### 密码存储（密文）

- p1是用户的原始密码，加密以后得到m1
- p2 是用户登录时候输入的密码
  - 验证逻辑：看 p2 加密后的字符串等不等于 m1

![image-20240701173343331](./SpringBoot 框架课.assets/image-20240701173343331.png) 







### 加密存储实现

加一个 config 类即可

/backend/config/SecurityConfig

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```



- 注：这个方法同一个字符串加密出的密文结果不一定一样
  - 但是可以用 matches 方法判断是否是一样的



eg：

调试代码

```java
package com.kob.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("pgodice"));
        System.out.println(passwordEncoder.encode("pgodice"));
        System.out.println(passwordEncoder.encode("pgodice"));
        System.out.println(passwordEncoder.matches("pgodice","$2a$10$iS00orv5sKRdBh0ItQSM..YGZ9rGYZAnMiLn.zzwQ6dKJqoHUZyMi"));
        System.out.println(passwordEncoder.matches("12312","$2a$10$iS00orv5sKRdBh0ItQSM..YGZ9rGYZAnMiLn.zzwQ6dKJqoHUZyMi"));
    }
}
```

![image-20240701175303354](./SpringBoot 框架课.assets/image-20240701175303354.png)



密码如下图

![image-20240701180310030](./SpringBoot 框架课.assets/image-20240701180310030.png) 







注意，这里还需要去修改一下注册的逻辑——注册的时候直接存储注册后的密码

```java
    @GetMapping("/user/add/{userId}/{username}/{password}/")//插入信息
    public String addUser(
            @PathVariable int userId,
            @PathVariable String username,
            @PathVariable String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(userId, username, encodedPassword);
        userMapper.insert(user);
        return "Add User Successfully";
    }
```











## 4.2Mysql授权&登录&注册

- 传统一般是 session 验证，如果是前后端分离——会有跨域问题
  - 用 JWT 验证更加容易解决跨域问题
- 用户登录注册页面——编写API
  - 将数据库中的id域变为自增
  - 在数据库中将id列变为自增
  - 在pojo.User类中添加注解：@TableId(type = IdType.AUTO)
  - 实现/user/account/token/：验证用户名密码，验证成功后返回jwt token（令牌）
  - 实现/user/account/info/：根据令牌返回用户信息
  - 实现/user/account/register/：注册账号
- 前段的用户登录页面



### 4.1Cookie、session、token

[Cookie 文章](https://blog.csdn.net/huangpb123/article/details/109107461)

Cookie基于域名的，不同的域名对应不同的 Cookie

**Cookie 和 Session 的区别**

- **安全性：** Session 比 Cookie 安全，Session 是存储在服务器端的，Cookie 是存储在客户端的。
- **存取值的类型不同**：Cookie 只支持存字符串数据，Session 可以存任意数据类型。
- **有效期不同：** Cookie 可设置为长时间保持，比如我们经常使用的默认登录功能，Session 一般失效时间较短，客户端关闭（默认情况下）或者 Session 超时都会失效。
- **存储大小不同：** 单个 Cookie 保存的数据不能超过 4K，Session 可存储数据远高于 Cookie，但是当访问量过多，会占用过多的服务器资源。



[session 和 token](https://www.cnblogs.com/gaodi2345/p/13864532.html)



- Cookie
- Session 是基于 Cookie 的
- token 是基于







### 4.2URL访问权限控制思路

访问控制的页面主要分为两类：登录才能访问的、直接就能访问的（无需登录）

验证方式有两种：基于 session 的，基于 jwt 的





#### 4.2.1传统session 验证思路

- session 验证的问题——session 难以处理前后端分离的模式：
  - 出现的跨域模式、也不好处理多前端（web 端、acpp 端）
  - 多服务器模式：一个 sessionID 需要复制多份，放在多个服务器上、也比较麻烦



session 是基于 cookie 实现的

session认证流程：

- 用户第一次请求服务器的时候，服务器根据用户提交的相关信息，创建对应的 Session
- 请求返回时将此 Session 的唯一标识 SessionID 返回给浏览器
- 浏览器接收到服务器返回的 SessionID 后，会将此信息存入到 Cookie 中，同时 **Cookie 记录此 SessionID 属于哪个域名**
- 当用户第二次访问服务器的时候，请求会自动把此域名下的 Cookie 信息也发送给服务端，服务端会从 Cookie 中获取 SessionID，再根据 SessionID 查找对应的 Session 信息，如果没有找到说明用户没有登录或者登录失效，如果找到 Session 证明用户已经登录可执行后面操作。

<img src="./SpringBoot 框架课.assets/image-20240702165140634.png" alt="image-20240702165140634" style="zoom:67%;" /> 





- 页面分类：
  - 公开页面；不需要登录就可以访问的
    - login页面、注册页面、网站首页
  - 授权页面：需要登录才可以访问的页面
- login 属于公开页面
  - 用户登录 login 页面，输入账户密码后，spring 去数据库查询——正确则返回一个 sessionID 标识一个用户的信息
    - 数据库也会自己存一份 sessionID
    - 用户收到的 sessionID 存在浏览器本地 cookie 中，之后每次访问都会带上 cookie
  - eg《我的空间》授权页面的访问：访问的时候会判断 sessionID 是否有效（数据库本地是否存储&是否没过期）
    - sessionID 有效后，在访问页面的 controller 中，通过接口，通过 sessionID，从数据库中调用对应的用户信息
    - 即可访问 controller

![image-20240702160606354](./SpringBoot 框架课.assets/image-20240702160606354.png)



#### 4.2.2JWT 验证思路(y总)

结合加密算法进行的访问控制（和数据库的加密访问控制类似：判断两个加密后的结果是否一样）

优势：1轻易实现跨域  2不需要在服务器端存储（一个令牌就可以访问多个服务器，不用多服务器备份 sessionID）



**加密验证逻辑**

- **用户请求登录后：服务器把认证的信息存入 jwt-token（两段：认证信息+加密后的认证信息），返回给用户（完全存在客户端）**
- **验证时**：服务器取出第一段的信息（认证信息:userid等等），用存在服务器端的密钥，用对应的加密算法加密
  - 看加密后的字符串是否和 jwt-token中的第二段（加密后的认证信息）match

（密钥只存在服务器端）

![image-20240702171606102](./SpringBoot 框架课.assets/image-20240702171606102.png)



**问题：token 存在用户本地，可以被窃取（存在浏览器本地的 local store**）

**解决被窃取的风险——**

- access_token：有效期比较短，一般 5min
- refresh_token：有效期比较长，一般 14day
- 因为和浏览器交互信息的时候get、post 都会用到，如果是 get，用明文传的 token 就容易被窃取
- 解决办法：
  - 平时传递信息用时间段的 access_token
  - 每当access_token过期时，用 refresh_token使用post 请求周期性地向服务器请求获取新的access_token





注：本项目直接用的14 天的单个 token





### 4.3token 验证

[token 验证参考文档](https://www.cnblogs.com/gaodi2345/p/13864532.html)

![image-20240702172944857](./SpringBoot 框架课.assets/image-20240702172944857.png)

<img src="./SpringBoot 框架课.assets/image-20240702173258481.png" alt="image-20240702173258481" style="zoom:67%;" /> 

验证流程：

1. 客户端使用用户名跟密码请求登录
2. 服务端收到请求，去验证用户名与密码
3. 验证成功后，服务端会签发一个 token 并把这个 token 发送给客户端
4. 客户端收到 token 以后，会把它存储起来，比如放在 cookie 里或者 localStorage 里
5. 客户端每次向服务端请求资源的时候需要带着服务端签发的 token
6. 服务端收到请求，然后去验证客户端请求里面带着的 token ，如果验证成功，就向客户端返回请求的数据

**注意：**登录时 token 不宜保存在 localStorage，被 XSS 攻击时容易泄露。所以比较好的方式是把 token 写在 cookie 里。为了保证 xss 攻击时 cookie 不被获取，还要设置 cookie 的 http-only。这样，我们就能确保 js 读取不到 cookie 的信息了。再加上 https，能让我们的请求更安全一些





#### Refresh-token

- 另外一种 token——refresh token
- refresh token 是专用于刷新 access token 的 token。如果没有 refresh token，也可以刷新 access token，但每次刷新都要用户输入登录用户名与密码，会很麻烦。有了 refresh token，可以减少这个麻烦，客户端直接用 refresh token 去更新 access token，无需用户进行额外的操作。

<img src="./SpringBoot 框架课.assets/image-20240702173831543.png" alt="image-20240702173831543" style="zoom:67%;" />

- Access Token 的有效期比较短，当 Acesss Token 由于过期而失效时，使用 Refresh Token 就可以获取到新的 Token，如果 Refresh Token 也失效了，用户就只能重新登录了。
- Refresh Token 及过期时间是存储在服务器的数据库中，只有在申请新的 Acesss Token 时才会验证，不会对业务接口响应时间造成影响，也不需要向 Session 一样一直保持在内存中以应对大量的请求。





### 4.3JWT 理论

[参考文档](https://www.cnblogs.com/gaodi2345/p/13864532.html)

#### 4.3.1 JWT 是什么

- JSON Web Token（简称 JWT）是目前最流行的**跨域认证**解决方案。
- 是一种**认证授权机制**。
- JWT 是为了在网络应用环境间**传递声明**而执行的一种**基于 JSON** 的开放标准。JWT 的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源。比如用在用户登录上。
- 可以使用 HMAC 算法或者是 RSA 的公/私秘钥对 JWT 进行签名。因为数字签名的存在，这些传递的信息是可信的。





#### 4.3.2JWT 原理

JWT 的原理是，服务器认证以后，生成一个 JSON 对象，返回给用户，就像下面这样。

1.  

   {

2.  

   "姓名": "张三",

3.  

   "角色": "管理员",

4.  

   "到期时间": "2018年7月1日0点0分"

5.  

   }

以后，用户与服务端通信的时候，都要发回这个 JSON 对象。服务器完全只靠这个对象认定用户身份。为了防止用户篡改数据，服务器在生成这个对象的时候，会加上签名。

<img src="https://img-blog.csdnimg.cn/20200114184248329.jpeg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2h1YW5ncGIxMjM=,size_16,color_FFFFFF,t_70" alt="img" style="zoom:67%;" /> 



- JWT 和 Token 的区别（JWT 应该是一种特殊的 Token——服务器无状态）
  - 解决多服务器、（前后端）跨域等问题

![image-20240709163645872](./SpringBoot 框架课.assets/image-20240709163645872.png)







### 4.4JWT实现（spring-security）

（JWT（服务器无状态的 token）在 Spring-Security 中实现）

- 实现service.impl.UserDetailsServiceImpl类，继承自UserDetailsService接口，用来接入数据库信息
- 实现config.SecurityConfig类，用来实现用户密码的加密存储

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

- 实现utils.JwtUtil类，为jwt工具类，用来创建、解析jwt token
- 实现config.filter.JwtAuthenticationTokenFilter类，用来验证jwt token，如果验证成功，则将User信息注入上下文中
- 配置config.SecurityConfig类，放行登录、注册等接口









**1首先需要添加三个依赖**

```
jjwt-api
jjwt-impl
jjwt-jackson

这个最新的依赖会报红
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.6</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>


用这个版本的就不会报红
				<dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

```





**2：实现utils.JwtUtil类，为jwt工具类，用来创建、解析jwt token**

```java
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    public static final long JWT_TTL = 60 * 60 * 1000L * 24 * 14;  // 有效期14天
    public static final String JWT_KEY = "SDFGjhdsfalshdfHFdsjkdsfds121232131afasdfac";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("sg")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

    public static SecretKey generalKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "HmacSHA256");
    }

    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
```







**3：实现config.filter.JwtAuthenticationTokenFilter工具类：用来验证jwt token，如果验证成功，则将User信息注入上下文中**



添加依赖

```
<dependency>
    <groupId>org.jetbrains</groupId>
    <artifactId>annotations</artifactId>
    <version>16.0.1</version>
</dependency>
```



接口代码

```java
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);

        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = userMapper.selectById(Integer.parseInt(userid));

        if (user == null) {
            throw new RuntimeException("用户名未登录");
        }

        UserDetailsImpl loginUser = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
```







**4：配置config.SecurityConfig类，放行登录、注册等接口**

```java
package com.kob.backend.config;
import com.kob.backend.config.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/account/token/", "/user/account/register/").permitAll()//公开链接位置，在这里加入即可
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
```







### 4.5 后端用户/令牌/注册等Api实现

**基本设置**

- 在数据库中将id列变为自增（且唯一）
  - 还需要修改 pojo 层（对应数据库）
    - 在pojo.User类中添加注解：@TableId(type = IdType.AUTO)
- 数据库中添加 photo 列，存储用户头像图片的链接
  - 这里也需要修改 pojo 层



**接下来实现三个 API**

- 实现/user/account/token/：验证用户名密码，验证成功后返回jwt token（令牌）
- 实现/user/account/info/：根据令牌返回用户信息
- 实现/user/account/register/：注册账号





加入注解，和 photo

<img src="./SpringBoot 框架课.assets/image-20240710215001361.png" alt="image-20240710215001361" style="zoom:67%;" /> 







#### api 实现

需要写三个地方——

![image-20240711153213830](./SpringBoot 框架课.assets/image-20240711153213830.png) 







#### 接口的定义

上图第二步——接口的定义

实现如下

- user 接口包
  - account 账户相关
    - 信息
    - 登录
    - 注册





**接下来首先实现的是 login 接口**

接口的定义如下

```
//这里按ctrl + enter可以快速实现接口中的方法
//opt + 回车可以快速导入类 
```

![image-20240711171400328](./SpringBoot 框架课.assets/image-20240711171400328.png)





#### 接口的调试

405报错：方法不被允许（500 是服务器错误）

![image-20240711182753479](./SpringBoot 框架课.assets/image-20240711182753479.png) 

调试 login 接口，/user/account/token/，因为是 post 请求，不能直接访问调试

——可以抓包用post调试、也可以用工具postman、或者直接在前端中调试



这里直接在前端中调试：前端中随便找个地方写（这里写在 app.vue）

源代码备份如下

```html
<template>
  <NavBar/>
  <router-view> </router-view><!--自动根据网址改变路由，需要去 router 中定义-->
</template>

<script>
import NavBar from "./components/NavBar.vue"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
// import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap/dist/js/bootstrap"

export default{
  components: {
    NavBar
  }
}



</script>


 
<style>
body {
  background-image: url("@/assets/images/background.png");/*@这里是根目录，也可以用相对目录*/
  background-size: cover;/*背景图缩放*/
}

</style>
```





调试代码如下

```html
<template>
  <NavBar/>
  <router-view> </router-view><!--自动根据网址改变路由，需要去 router 中定义-->
</template>

<script>
import NavBar from "./components/NavBar.vue"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
// import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap/dist/js/bootstrap"
import $ from 'jquery'

export default{
  components: {
    NavBar
  },
  setup(){
    $.ajax({
      url: "http://127.0.0.1:3002/user/account/token/",
      type: "post",
      data:{
        username: "godice",
        password: "pgodice",
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })
  }
}



</script>


 
<style>
body {
  background-image: url("@/assets/images/background.png");/*@这里是根目录，也可以用相对目录*/
  background-size: cover;/*背景图缩放*/
}

</style>

```

报错 500，提示服务端错误

![image-20240711184013687](./SpringBoot 框架课.assets/image-20240711184013687.png)

密钥长度不够，去 utils 中增加一下密钥长度

![image-20240711184031733](./SpringBoot 框架课.assets/image-20240711184031733.png)

修改后成功，可以看到返回结果是自定义的

![image-20240711184355379](./SpringBoot 框架课.assets/image-20240711184355379.png)

定义如下

![image-20240711184428941](./SpringBoot 框架课.assets/image-20240711184428941.png)



可以去网站解析 jwt 格式

[解析](https://jwt.io/)

```
jwt.io
```

![image-20240711184714252](./SpringBoot 框架课.assets/image-20240711184714252.png)











#### 4.6三个登录相关接口

接口实现流程：创建xxxImpl类——添加@service注释——implements实现接口——opt+回车自动实现接口方法



**三个接口的功能**

login：根据用户登录信息，验证登录，登录成功返回对应（包含 userid 标识的）JWTtoken

Info：根据 token 获取用户信息（实现无密码登录）

register：注册

#### 4.6.1login 接口



##### 接口结构和功能

<img src="./SpringBoot 框架课.assets/image-20240715154800743.png" alt="image-20240715154800743" style="zoom:67%;" /> 

- 上文已经实现了 login 接口：分为三个文件
  - service.user.account.LoginService：一个定义的接口文件
  - service.impl.user,account.LoginServiceImpl：对于上面接口的具体实现
    - 根据 controller 中获取的 username、password 生成登录认证令牌
    - 验证用户身份
      - 验证成功则登录成功——进行后续的处理
      - 验证失败抛异常
    - 根据验证成功后返回的一个对象，取出用户信息
    - 从用户信息取出 userid封装为 JWTtoken
    - 返回：map 返回信息
      - 登录状态类型：成功（前面如果登录失败会自动处理）
      - 登录信息：返回JWTtoken（包含userid）
  - Controller.user.account.LoginController：绑定 url（同时获取登录数据，以生成对应的 token）
    - 通过post 传递数据，不能直接网页调试
      - 前端调试、postman 调试、bp 调试
    - 定义的新（公开）链接需要去 config 中的安全配置中放行



注意：最终的触发是通过，访问 controller 调用到接口，进行触发的

![image-20240715161803727](./SpringBoot 框架课.assets/image-20240715161803727.png)



##### 接口代码

```java
package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.LoginService;
import com.kob.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

@Service//实现Service中定义的接口（需要注解）
public class LoginServiceImpl implements LoginService {//接口后面加Impl表示对一个接口的实现
    //这里按ctrl + enter实现接口
    //opt + 回车可以快速导入类

    @Autowired
    private AuthenticationManager authenticationManager;//

    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);//创建认证令牌
        //authenticationManager.authenticate(authenticationToken).var可以自动定义出来变量名和类型
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);//验证用户身份
        //如果验证登录失败会抛异常，验证成功后会返回一个Authentication对象
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();//这里获取登录的用户信息（UserDetailsImpl）强制类型转换
        User user = loginUser.getUser();//取出用户信息
        //把 UserId 封装成 JWToken
        String jwt = JwtUtil.createJWT(user.getId().toString());

        //返回结果
        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");//前面如果登录失败会自动处理，执行到这里一定登录成功了
        map.put("token", jwt);//返回token

        return map;
    }
}

```





#### 4.6.2info 接口

从上下文中提取出信息

/user/account/info/





- 注意post 请求header 头中的用法：
  - Authorization：授权（作者授权）
    - 在前端 post 请求的 header 头用使用，用于授权登录的
  - Authentication：认证
    - 在后端代码中使用的





```
在 login 接口调试中获得的token，可以用于 info接口调试的认证
eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ZGYwNTFjNmM2M2Q0MDg0YmJiODk1ZTdiZjUzNTcwYSIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcyMTExMjgwOSwiZXhwIjoxNzIyMzIyNDA5fQ.wq5QxFAjCvR1MooQSXGQFthdedNGUjwAoZr84XosnW8
```

前端调试代码如下

**注意："Bearer "这里有一个空格，必须加上**

```
Authorization: "Bearer " + "token"
```

代码

```html
<template>
  <NavBar/>
  <router-view> </router-view><!--自动根据网址改变路由，需要去 router 中定义-->
</template>

<script>
import NavBar from "./components/NavBar.vue"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
// import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap/dist/js/bootstrap"
import $ from 'jquery'

export default{
  components: {
    NavBar
  },
  setup(){
    $.ajax({
      url: "http://127.0.0.1:3002/user/account/token/",
      type: "post",
      data:{
        username: "godice",
        password: "pgodice",
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })

    $.ajax({
      url: "http://127.0.0.1:3002/user/account/info/",
      type: "get",
      headers:{
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmYjY4MTE3YTA4ZmE0YjMwOWMxOGE5YjkyZmM4MzFiZCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcyMTExMzY4MywiZXhwIjoxNzIyMzIzMjgzfQ.0Ky3j95MuIZveNYOzhf03-Ntxp9_Gz55IfAF3r8coNY"
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })
  }
}



</script>


 
<style>
body {
  background-image: url("@/assets/images/background.png");/*@这里是根目录，也可以用相对目录*/
  background-size: cover;/*背景图缩放*/
}

</style>
<template>
  <NavBar/>
  <router-view> </router-view><!--自动根据网址改变路由，需要去 router 中定义-->
</template>

<script>
import NavBar from "./components/NavBar.vue"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
// import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap/dist/js/bootstrap"
import $ from 'jquery'

export default{
  components: {
    NavBar
  },
  setup(){
    $.ajax({
      url: "http://127.0.0.1:3002/user/account/token/",
      type: "post",
      data:{
        username: "godice",
        password: "pgodice",
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })

    $.ajax({
      url: "http://127.0.0.1:3002/user/account/info/",
      type: "get",
      headers:{
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmYjY4MTE3YTA4ZmE0YjMwOWMxOGE5YjkyZmM4MzFiZCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcyMTExMzY4MywiZXhwIjoxNzIyMzIzMjgzfQ.0Ky3j95MuIZveNYOzhf03-Ntxp9_Gz55IfAF3r8coNY"
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })
  }
}



</script>


 
<style>
body {
  background-image: url("@/assets/images/background.png");/*@这里是根目录，也可以用相对目录*/
  background-size: cover;/*背景图缩放*/
}

</style>

```



成功截图如下

![image-20240716154038697](./SpringBoot 框架课.assets/image-20240716154038697.png)

可以修改前端中的 username 和 password，修改访问的用户，重新生成 token

![image-20240716154219315](./SpringBoot 框架课.assets/image-20240716154219315.png)

这里Bearer也是自定义的

![image-20240716154532819](./SpringBoot 框架课.assets/image-20240716154532819.png)





#### 4.6.3 register接口

前端调试的最终代码如下

``` mysql
<template>
  <NavBar/>
  <router-view> </router-view><!--自动根据网址改变路由，需要去 router 中定义-->
</template>

<script>
import NavBar from "./components/NavBar.vue"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
// import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap/dist/js/bootstrap"
import $ from 'jquery'

export default{
  components: {
    NavBar
  },
  setup(){
    $.ajax({
      url: "http://127.0.0.1:3002/user/account/token/",
      type: "post",
      data:{
        username: "bb",
        password: "pbb",
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })

    $.ajax({
      url: "http://127.0.0.1:3002/user/account/info/",
      type: "get",
      headers:{
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhY2QwMjgzODQ3ZWY0NDEyYWUwYjM2OTEwMDY4MzBkYiIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTcyMTExNTcwMCwiZXhwIjoxNzIyMzI1MzAwfQ.Z7sAPHxKoHGmdW8dAyqYNGcnQRL1AnvcSub3uzMnSIY"
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })
    
    $.ajax({
      url: "http://127.0.0.1:3002/user/account/register/",
      type: "post",
      data:{
        username: "godice2",
        password: "pgodice2",
        confirmedPassword: "pgodice2",
      },
      success(resp){
        console.log(resp);
      },
      error(resp){
        console.log(resp);
      }
    })
  }
}



</script>


 
<style>
body {
  background-image: url("@/assets/images/background.png");/*@这里是根目录，也可以用相对目录*/
  background-size: cover;/*背景图缩放*/
}

</style>

```



## 4.3前端实现

接下来实现：登录、注册的两个前端页面

创建文件如下

<img src="./SpringBoot 框架课.assets/image-20240724212538331.png" alt="image-20240724212538331" style="zoom:50%;" /> 



1：类似 Record 页面，复制过来改改即可

2：router 中添加一下路由







3：bootstrap 抄样式



### 1 grids布局

grids 把页面分为 12 格，登录放在中间三格（回车即可）

注意：这里写错了，应该是 col-3

![image-20240724230832874](./SpringBoot 框架课.assets/image-20240724230832874.png)

嵌入表单

![image-20240724231402146](./SpringBoot 框架课.assets/image-20240724231402146.png)



所有代码如下

```html
<template>
    <ContentField>
        <div class="row justify-content-center"><!--居中-->
            <div class="col-3">
                <form>
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error-message"></div>
                    <button type="submit" class="btn btn-info">登录</button>
                </form>
            </div><!--中间三格-->
        </div>
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
                                                                         
export default{
    components:{
        ContentField
    }
}   
</script>

<style scoped>

button{
    width: 100%;
}
div.error-message{
    color: red;
}
</style>
```





因为需要在每个页面都需要保存一个信息——当前登录的是哪个用户（或没有用户登录）

/store/user.js





### 2前后端逻辑login

```
前端http://localhost:8080/user/account/login/

后端http://127.0.0.1:3002/user/account/token/
```



#### 2.1前端

/store/user.js中

- ajax 访问后端接口
- 成功获取用户输入的 username 和 password 后context.commit执行 mutations 中的 updateToken
  - 更新 state 中的状态，把从后端获取的 token 更新到vuex 的 store 模块中，存储为全局变量
    - 以便后面调用的时候使用

```javascript
import $ from 'jquery'

export default{
    state: {//存用户全局信息
        id: "",
        username: "",
        photo: "",
        token: "",
        isLogin: false,
    },
    getters: {//一般用不到
    },
    mutations: {//修改数据，所以第一个参数默认是 state，后面传入的 payload 默认是第二个参数
        updateUser(state, user){
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.isLogin = user.isLogin;
        },
        updateToken(state, token){
            state.token = token;
        }
    },
    actions: {//修改 state 的函数
        login(context, data){//这两个参数中 vuex 中的自带的参数
            //context可以提交 mutation、dispatch触发另一个 action 等
            //data 是 action 被调用时传进来的数据
            $.ajax({
                url: "http://127.0.0.1:3002/user/account/token/",
                type: "post",
                data:{
                  username: data.username,
                  password: data.password,
                },
                success(resp){
                    if(resp.error_message === 'success'){//error_message和token都是在后端自定义的
                        context.commit("updateToken", resp.token);//action中调用 mutations 中的函数，需要加 commit
                        data.success(resp);
                    }else{
                        data.error(resp);
                    }
                },
                error(resp){
                    data.error(resp);
                }
              })          
        }
    },
    modules: {
    }
}
```



/views/user/account/UserAccountLoginView.vue

- 这里通过调用 store 实例，通过store.dispath机制触发user.js中的login action
  - user.js被当作一个一个模块封装到了index.js中，可以作为 store 的实例被调用

```html
<script>
import ContentField from "@/components/ContenField.vue"
import {useStore} from 'vuex'//useStore函数返回一个store实例，可以在组件的setup()函数中使用它来访问store的状态、dispatch actions或commit mutations。
import {ref} from 'vue'//变量的处理

export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();
        let username = ref("");
        let password = ref("");
        let error_message = ref("");

        const login = () =>{//触发登录函数
            store.dispatch("login",{
                username: username.value,//页面传到 ref，传到这里，这里调用了user.js中的登录函数
                password: password.value,//登录函数的data由这里传入
                success(resp){
                    console.log(resp);
                },
                error(resp){
                    console.log(resp);
                } 
            })
        }
        return{
            username,
            password,
            error_message,
            login,
        }
    }
}   
</script>
```





user.js实现

- 关于下面进行了两层 success 的判断逻辑
  - 第一个是 http 层面的成功
  - 第二个是后端服务器层面的成功

你提到的疑问主要在于为什么在`success`回调中还要再次检查`resp.error_message`是否等于`'success'`。实际上，这种做法是为了区分服务器端的业务逻辑成功与HTTP层面的成功。

1. **HTTP层面的成功**：当Ajax请求的`success`回调被触发时，这仅仅意味着HTTP请求已经成功完成，服务器已经响应了请求。但这并不一定意味着服务器端的业务逻辑也成功了。例如，服务器可能因为用户名或密码错误返回了一个错误响应，尽管HTTP状态码可能是200 OK。
2. **业务逻辑的成功**：服务器端的业务逻辑可能定义了特定的响应格式来表示成功或失败，如你代码中的`error_message`字段。即使HTTP请求成功，如果`error_message`不等于`'success'`，那么业务逻辑上仍然认为操作失败。

因此，在`success`回调中再次检查`resp.error_message`是非常必要的。这一步骤确保了只有当服务器端的业务逻辑真正成功时，才会执行`context.commit`来更新状态，并调用`data.success(resp)`来处理成功的响应。否则，如果`error_message`表明有错误发生，就会调用`data.error(resp)`来处理错误情况。

简而言之，HTTP层面的成功和业务逻辑上的成功是两回事，而`resp.error_message`的检查正是为了区分这两者，确保应用程序根据服务器端的真实状态做出正确的反应。

通常情况下，`XMLHttpRequest`（或者基于它的封装如jQuery的`$.ajax`）的`success`回调函数会在HTTP请求返回一个2xx范围内的状态码时被触发，这通常表示请求成功。具体来说，最常见的成功状态码是200（OK），但它也可能包括其他状态码，如201（Created）、202（Accepted）、204（No Content）等，这些都属于成功响应的范畴。

然而，需要注意的是，2xx状态码虽然通常表示请求在HTTP层面上成功了，但这并不总是意味着应用程序级别的业务逻辑也成功了。服务器可能返回一个200状态码，同时在响应体中包含错误信息，比如JSON格式的错误消息。这就是为什么在`success`回调中还需要检查服务器返回的业务逻辑状态，如你代码中的`error_message`字段。

在某些情况下，如果服务器配置了非标准的响应状态码来表示成功或错误，或者使用了代理、负载均衡器等中间件，HTTP状态码可能不会完全按照预期工作。但在大多数标准的Web开发场景下，200状态码确实意味着HTTP请求成功，且`success`回调将会被触发。

```javascript
import $ from 'jquery'

export default{
    state: {//存用户全局信息
        id: "",
        username: "",
        photo: "",
        token: "",
        isLogin: false,
    },
    getters: {//一般用不到
    },
    mutations: {//修改数据，所以第一个参数默认是 state，后面传入的 payload 默认是第二个参数
        updateUser(state, user){
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.isLogin = user.isLogin;
        },
        updateToken(state, token){
            state.token = token;
        }
    },
    actions: {//修改 state 的函数
        login(context, data){//这两个参数中 vuex 中的自带的参数
            //context可以提交 mutation、dispatch触发另一个 action 等
            //data 是 action 被调用时传进来的数据
            $.ajax({
                url: "http://127.0.0.1:3002/user/account/token/",
                type: "post",
                data:{
                  username: data.username,
                  password: data.password,
                },
                success(resp){
                    if(resp.error_message === 'success'){//error_message和token都是在后端自定义的
                        context.commit("updateToken", resp.token);//action中调用 mutations 中的函数，需要加 commit
                        data.success(resp);//可以使用回调：更新 ui、重定向页面、存储 token 等
                    }else{
                        data.error(resp);//这里的回调可以返回错误信息、帮助调试问题等等
                    }
                },
                error(resp){
                    data.error(resp);
                }
            });          
        },
        //通过 token 获取信息后,进行用户信息的更新
        getinfo(context, data){
            $.ajax({
                url: "http://127.0.0.1:3002/user/account/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp){
                    if(resp.error_message === 'success'){
                        context.commit("updateUser",{
                            ...resp,//将 resp 的内容（id、username、photo）解析出来，和 isLogin 拼接
                            isLogin: true,//拼接后的信息更新到 store 中存储的信用信息
                        });
                        data.success(resp);
                    }else{
                        data.error(resp);
                    }
                },
                error(resp){
                    data.error(resp);
                }
            })
        }
    },
    modules: {
    }
}
```









### 3 logout

- 因为token是存在内存中的，刷新页面，重新访问之类的，就会退出登录状态
  - 后面需要改为存储在cookie 中



#### 3.1内存版

实现点击退出，这里只需要删除token 即可退出认证的登录状态

```
user.js

state{存用户状态信息}


mutations{
	updateUser(){更新用户信息}
	updateToken(){更新 token}
	logout(){置所有信息为空}
}

actions{

	login(context, data){//登录：获取 token，提供回调函数（可更新页面状态）
	访问/user/account/token/把前端输入的用户名密码，到后端获取token
	调mutations中updateToken，用data.success(resp)回调函数（NavBar中可以使用，更新页面状态）
	（注意这里有两个success的逻辑，第一个处理 http200，第二个处理后端逻辑正确）
	}
	
	getinfo(context, data){//根据获取的 token访问后端，获取对应的账号信息
	访问/user/account/info/，成功则调用mutations中的updateUser，更新信息，且置is_login为 true
	}
	
	logout(context){
		调用mutations中logout即可
	}
}


NavBar.vue
//用于绑定 logout 事件到页面点击按钮
    const logout = () =>{
      store.dispatch("logout");//函数在下面 return 中返回
    }

    return {
      route_name,
      logout,//退出登录的触发事件，直接嵌入到 html 中即可
    }
    
//接下来把"logout"绑定到了 html 中的《退出》按钮，用 click 触发
<ul class="dropdown-menu">
            <li><router-link class="dropdown-item" :to="{name: 'user_bot_index'}">我的bot</router-link></li>
            <li><hr class="dropdown-divider"></li>
            <li><router-link class="dropdown-item" :to="{name: 'home'}" @click="logout">退出</router-link></li>
          </ul>
```

点击——触发返回的 logout——绑定到了触发函数()=>{}——触发 store 中actions的 logout 函数——触发 mutations 中的 logout——置用户信息为空

```
//用户点击会触发logout
//下面的代码用于触发store 存储中的 logout 函数，即触发user.js中的actions 中的logout 函数
//触发后会调用mutations 中的 logout，然后删除 store 中的所有信息，
    const logout = () =>{
      store.dispatch("logout");//函数在下面 return 中返回
    }

    return {
      route_name,
      logout,//退出登录的触发事件，直接嵌入到 html 中即可——把上面的那个触发函数返回了
      			//使得可以在 html 中用
    }
```





### 4未登录重定向&注册页面&token 改进

没有登录的时候都回强制重定向到登录页面&实现注册页面&登录状态的持久化（token 不存内存，存 cookie）



#### 4.1前端页面授权

（需要登录的页面，在未登录状态访问——会重定向到login页面）

有很多种实现方式——这里在 router 中实现

每次通过 router 调用进入每个页面前——会执行这个函数

```
route.beforeEach((to, from, next)=>{

})
//to跳转的目标页面
//from 从哪个页面过来的
//next：页面要执行的下一步操作
```

<img src="./SpringBoot 框架课.assets/image-20240726193213905.png" alt="image-20240726193213905" style="zoom:50%;" />







#### 4.2注册页面

把登录页面复制过来改改



注意：当对store的函数操作，会修改 state 的值时，才需要把它放到user.js中

```html
<template>
    <ContentField>
        <div class="row justify-content-center"><!--居中-->
            <div class="col-3">
                <form @submit.prevent="register"><!--提交的触发函数，在 css 中定义-->
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="mb-3">
                        <label for="confirmedPassword" class="form-label">确认密码</label>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="请再次输入密码">
                    </div>
                    <div class="error-message"> {{ error_message }}</div>
                    <button type="submit" class="btn btn-info">注册</button>
                </form>
            </div><!--中间三格-->
        </div>
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
import { ref } from 'vue'
import router from '@/router/index.js'
import $ from 'jquery'

export default{
    components:{
        ContentField
    },
    setup(){
        let username = ref("");
        let password = ref("");
        let confirmedPassword = ref("");
        let error_message = ref("");

        const register = () =>{//html中 register的触发函数
            $.ajax({
                url: "http://127.0.0.1:3002/user/account/register/",
                type:"post",
                data: {
                    username: username.value,//username.value是页面绑定到 ref 的username 动态变化的，第一个 username 是传给后端的数据
                    password: password.value,
                    confirmedPassword: confirmedPassword.value,
                },
                success(resp){
                    if(resp.error_message === 'success'){
                        router.push({name: 'user_account_login'});
                    }else{
                        //这里从后端取回错误信息，绑定到 ref 变量 error_message中
                        //且在下面 return，即可导入绑定到 html 中
                        error_message.value = resp.error_message;//后端返回的信息存在 resp 中
                    }
                },
            });
        }


        return{//返回，方便绑定到 html
            username,
            password,
            confirmedPassword,
            error_message,
            register,
        }
    }
}   
</script>

<style scoped>
button{
    width: 100%;
}
div.error-message{
    color: red;
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;     /* 垂直居中 */
    height: 1.5rem; 
}
</style>
```

#### 4.3登录状态持久化

目前 token 是存在 vuex 的 store 中（内存）——刷新页面即会清空内存（退出登录状态）

这里需要改进存到 localStorage 中（浏览器的硬盘存储的一部分）



修改user.js中的代码如下

```
success(resp){
                    if(resp.error_message === 'success'){//error_message和token都是在后端自定义的
                        //存储token到本地  
                        localStorage.setItem("jwt_token", resp.token);
                        
                        context.commit("updateToken", resp.token);//action中调用 mutations 中的函数，需要加 commit
                        data.success(resp);//可以使用回调：更新 ui、重定向页面、存储 token 等
                    }else{
                        data.error(resp);//这里的回调可以返回错误信息、帮助调试问题等等
                    }


logout(context){
            localStorage.removeItem("jwt_token");//删除浏览器本地的 token
            context.commit("logout");
        }
```

登录后可以在这里查看

![image-20240727001210167](./SpringBoot 框架课.assets/image-20240727001210167.png)



- 现在需要在每次重定向到一个新的页面时——增加一个判断
  - （因为每次都会重定向到登录页面，直接在登录页面判断就可以）
- localStorage 的中是否有jwt_token
  - 有的话则取出来，验证是否过期
    - 没有过期的话，就不用重新登录了
- 刷新后，token 自动过期，是未登录状态，会自动跳转到登录页面
  - 登录页面就会取出本地的 token，并调用接口验证
  - 验证成功就跳转到首页



login 页面的代码增加如下

```javascript
        const jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token){
            store.commit("updateToken", jwt_token);//用 mutations 中的函数把 localStorage 取出的 token 更新到 state 中
            //验证 token 是否合法，actions 中的 getinfo 函数（在云端验证）
            store.dispatch("getinfo",{//调用两个回调函数
                success(){
                    router.push({name: "home"});//成功跳转到首页
                },
                error(){
                }
            }) 
        }

```





#### 4.4不让登录页面展示

因为每次刷新的时候，内存token都会被刷新掉，然后跳转回登录页面，再取出本地的token，所以会有一闪而过的白色登录页面

<img src="./SpringBoot 框架课.assets/image-20240727004429176.png" alt="image-20240727004429176" style="zoom:67%;" />



修改 UserAccountLoginView.vue代码如下

```html
<template>
    <ContentField v-if="show_content"><!--控制页面暂时不要展示-->
        <div class="row justify-content-center"><!--居中-->
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error-message"> {{ error_message }}</div>
                    <button type="submit" class="btn btn-info">登录</button>
                </form>
            </div><!--中间三格-->
        </div> 
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
import {useStore} from 'vuex'//useStore函数返回一个store实例，可以在组件的setup()函数中使用它来访问store的状态、dispatch actions或commit mutations。
import {ref} from 'vue'//可以处理和标签中的变量的更新
import router from "@/router/index.js";
export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();
        let username = ref("");
        let password = ref("");
        let error_message = ref("");
        //解决：登录页面不要展示——解决刷新时 内存 token 不见
        //会重新跳转回登录页面获取本地token的问题
        //因而会有一个一闪而过的登录页面的问题
        let show_content = ref(false);

        const jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token){
            store.commit("updateToken", jwt_token);//用 mutations 中的函数把 localStorage 取出的 token 更新到 state 中
            //验证 token 是否合法，actions 中的 getinfo 函数（在云端验证）
            store.dispatch("getinfo",{//调用两个回调函数
                success(){
                    router.push({name: "home"});//成功跳转到首页
                },
                error(){//验证失败：非法/token 过期，则可以展示登录页面
                    show_content.value = "true";
                }
            }) 
        }else{//如果本地没有 jwt_token也需要展示页面
            show_content.value = "true";
        }

        //登录并跳转
        const login = () =>{//触发登录函数
            error_message.value = "";//每次清空一下
            store.dispatch("login",{
                username: username.value,//页面传到 ref，传到这里，这里调用了user.js中的登录函数
                password: password.value,//登录函数的data由这里传入
                success(){
                    store.dispatch("getinfo",{//获取信息的回调函数，实现在user.js中
                        success(){
                            router.push({name: "home"});//跳转到主页面
                            //console.log(resp);//后端定义返回的信息
                            //console.log(store.state.user);//state中的所有信息

                            //router.push ("/");
                        }
                    })
                },
                error(){
                    error_message.value = "用户名或密码错误";
                } 
            })
        }
        return{
            username,
            password,
            error_message,
            login,
            show_content,
        }
    }
}   
</script>

<style scoped>

button{
    width: 100%;
}
div.error-message{
    color: red;
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;     /* 垂直居中 */
    height: 1.5rem; 
}
</style>
```





#### 4.5页面显示改进

仔细观察，刷新时，右上角登录用户名处——也会出现闪烁



- 这里用把4.4的改进删除
  - 进而用一个全局变量控制所有的页面显示展示



user.js中

![image-20240727185054251](./SpringBoot 框架课.assets/image-20240727185054251.png)

更新函数

![image-20240727185426118](./SpringBoot 框架课.assets/image-20240727185426118.png)



login 的代码修改如下

```html
<template>
    <ContentField v-if="!$store.state.user.pulling_info"><!--控制页面暂时不要展示-->
        <div class="row justify-content-center"><!--居中-->
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error-message"> {{ error_message }}</div>
                    <button type="submit" class="btn btn-info">登录</button>
                </form>
            </div><!--中间三格-->
        </div> 
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
import {useStore} from 'vuex'//useStore函数返回一个store实例，可以在组件的setup()函数中使用它来访问store的状态、dispatch actions或commit mutations。
import {ref} from 'vue'//可以处理和标签中的变量的更新
import router from "@/router/index.js";
export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();
        let username = ref("");
        let password = ref("");
        let error_message = ref("");
        //解决：登录页面不要展示——解决刷新时 内存 token 不见
        //会重新跳转回登录页面获取本地token的问题
        //因而会有一个一闪而过的登录页面的问题
        //let show_content = ref(false);

        const jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token){
            store.commit("updateToken", jwt_token);//用 mutations 中的函数把 localStorage 取出的 token 更新到 state 中
            //验证 token 是否合法，actions 中的 getinfo 函数（在云端验证）
            store.dispatch("getinfo",{//调用两个回调函数
                success(){
                    router.push({name: "home"});//成功跳转到首页
                },
                error(){//验证失败：非法/token 过期，则可以展示登录页面
                    store.commit("updatePullingInfo", false);
                    //show_content.value = "true";
                }
            }) 
        }else{//如果本地没有 jwt_token也需要展示页面
            store.commit("updatePullingInfo", false);//拉取 token 结束，应该是 false
            console.log(store.state.user.pulling_info);
            //show_content.value = "true";
        }

        //登录并跳转
        const login = () =>{//触发登录函数
            error_message.value = "";//每次清空一下
            store.dispatch("login",{
                username: username.value,//页面传到 ref，传到这里，这里调用了user.js中的登录函数
                password: password.value,//登录函数的data由这里传入
                success(){
                    store.dispatch("getinfo",{//获取信息的回调函数，实现在user.js中
                        success(){
                            router.push({name: "home"});//跳转到主页面
                            //console.log(resp);//后端定义返回的信息
                            //console.log(store.state.user);//state中的所有信息

                            //router.push ("/");
                        }
                    })
                },
                error(){
                    error_message.value = "用户名或密码错误";
                } 
            })
        }
        return{
            username,
            password,
            error_message,
            login,
            //show_content,
        }
    }
}   
</script>

<style scoped>

button{
    width: 100%;
}
div.error-message{
    color: red;
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;     /* 垂直居中 */
    height: 1.5rem; 
}
</style>
```

主要是这里

<img src="./SpringBoot 框架课.assets/image-20240727191539557.png" alt="image-20240727191539557" style="zoom:67%;" />



#### 4.6修改navbar

处理如下即可 ![image-20240727191734557](./SpringBoot 框架课.assets/image-20240727191734557.png)



#### 4.7 刷新逻辑总结

因为下面的描述

![image-20240727191426049](./SpringBoot 框架课.assets/image-20240727191426049.png)



总结一下刷新逻辑如下：

- 每次刷新，浏览器内存都会被刷掉即state 中的所有数据:token、pulling_info等等
  - 都会被重新赋值为初始状态即：token为空，pulling_info为true
- 所以每次刷新，因为没有 token，浏览器会自动跳转到登录页面
  - 同时因为刷新掉了 token，右上角的 navbar，也不会显示账户信息，而会显示登录、注册
- 这个时候浏览器会去localStorage拉去jet_token，然后调取info后端接口获取信息
  - 验证成功后就会自动跳转回原页面
- 而在这中间，就会因为本来处于登录状态，然后短暂跳转页面，localStorage获取 token 后再跳回来
  - 就会出现 navbar 和 login 闪烁的问题
- 设置 pulling_info全局变量，默认为 true，每次刷新都会重置为 true，即让页面不展示
  - 只有当获取本地 token 失败，或验证失败获取 info 失败会置为 false
    - 即显示 login 和 navbar 的登录注册
  - 而因为每次刷新都会置为 true，不会影响非登录状态的 login 页面正常显示







### 4.8commit/dispatch

```
mutations  同步                                        commit
//直接修改数据，直接反应在


actions    异步（需要从云端（后端）拉取信息，然后再继续执行）  dispatch 
```



区别

```
在 Vuex 中，mutations 和 actions 都是用来改变状态（state）的机制，但它们之间有几个关键的区别：





Mutations
同步执行：Mutations 必须是同步函数，不能包含异步操作。这是因为状态的改变应该立即反映在 UI 上，而且在测试时更容易追踪和预测状态的变化。

直接修改状态：Mutations 直接修改状态树中的数据。它们接收 state 作为第一个参数，并且通常接受一个额外的参数作为负载（payload），用来传递改变状态所需的额外数据。

可追溯性：由于 mutations 是同步的，它们的执行顺序和时间点是确定的，这使得状态的变化可以被记录和跟踪，便于调试和维护。

提交方式：Mutations 通过 commit 方法被调用，如 store.commit('incrementCounter')。





Actions
异步执行：Actions 可以包含异步操作，如 API 调用。它们可以处理更复杂的业务逻辑，比如在异步操作完成后提交 mutation。

间接修改状态：Actions 不直接修改状态，而是通过调用 mutations 来改变状态。这有助于保持状态管理的清晰和分离关注点的原则。

灵活性：Actions 可以调用其他 actions，这允许你构建更复杂的流程。它们还可以访问整个 Vuex store 的上下文，包括 state, commit, dispatch 和 getters。

调用方式：Actions 通过 dispatch 方法被调用，如 store.dispatch('fetchData')。

错误处理：Actions 提供了一个更好的地方来处理错误和副作用，如网络请求的失败处理。

总结
Mutations 用于直接、同步地改变状态，适合于简单的状态更新逻辑。
Actions 用于封装业务逻辑，处理异步操作，并最终通过提交 mutations 来改变状态，适合于复杂的、涉及异步操作的业务流程。
这种设计模式有助于保持代码的清晰和可维护性，同时也提高了状态管理的可预测性和可测试性。
```







## 5.1创建个人中心页面（上-后端）

### 0：思路



即我的 bot 页面

![image-20240728002727345](./SpringBoot 框架课.assets/image-20240728002727345.png)



设计效果图（还有一个查询直接 bot 的按钮，忘了写）

![image-20240728004459921](./SpringBoot 框架课.assets/image-20240728004459921.png)



- 数据库创建存储 bot 的相关表
  - 创建数据库
  - 实现 pojo 层
- 后端 bot 相关 api
  - 增、删、改、查
- 实现前端



**在数据库中创建表bot**
表中包含的列：

- id: int：非空、自动增加、唯一、主键
- user_id: int：非空
  - 作者名字
- 注意：在pojo中需要定义成userId，在queryWrapper中的名称仍然为user_id
- title: varchar(100)
  - bot 名字
- description: varchar(300)
  - 描述信息（提示这是哪个 bot）
- content：varchar(10000)
  - 代码
- rating: int：默认值为1500
- createtime: datetime（sql类型）（pojo 类型Date）
  - 创建时间
- pojo中定义日期格式的注解：@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
- modifytime: datetime
  - 修改时间
- pojo中定义日期格式的注解：@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

**实现后端API**

- /user/bot/add/：创建一个Bot
- /user/bot/remove/：删除一个Bot
- /user/bot/update/：修改一个Bot
- /user/bot/getlist/：查询Bot列表



**实现前端页面**

安装依赖：vue3-ace-editor

```javascript
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';
ace.config.set(
    "basePath", 
    "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")
<VAceEditor
    v-model:value="botadd.content"
    @init="editorInit"
    lang="c_cpp"
    theme="textmate"
    style="height: 300px" />
```



### 1 配置步骤



#### 1.1数据库表

记得修改表名为 bot



#### 1.2 pojo 层

**注意：数据库是user_id，pojo 中应该是驼峰命名userId**

从 pojo.user中复制，改一下

```java
package com.kob.backend.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bot {
    @TableId(type = IdType.AUTO)//主键自增
    private Integer id;
    private Integer userId;//数据库中是user_id，pojo中必须是驼峰命名
    private String title;//bot名字
    private String description;//bot 简介
    private String content;//bot代码
    private Integer rating;//rank分
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifytime;

}
```



#### 1.3Mapper层

现实BotMapper接口

抄一下 UserMapper

```java
package com.kob.backend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.Bot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BotMapper extends BaseMapper<Bot>{}
```













### 2三个接口

#### 2.1AddService

AddService/RemoveService/UpdateService/GetListService



**接口定义**

Service.User.bot.AddService

```java
package com.kob.backend.service.user.bot;

import java.util.Map;

public interface AddService {
    //接口中的函数默认是 public，所以这个 public 是灰色
    public Map<String, String> add(Map<String, String> data);
}

```



**接口实现**

Service.Impl.user.bot.AddServiceImpl：

获取 token——获取 token 可以得到的认证信息——从认证信息中获取 User 的信息 

```java
UsernamePasswordAuthenticationToken authenticationToken =
//获取当前安全上下文中的认证信息，从中获取Authentication对象（并强转类型）
	(UsernamePasswordAuthenticationToken)
	SecurityContextHolder.getContext().getAuthentication();
//从认证信息中获取登录详情
//getPrincipal()返回认证主体——主体是UserDetailsImpl类型
UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
User user = loginUser.getUser();
```

getPrincipal 类型在这里

<img src="./SpringBoot 框架课.assets/image-20240730110151503.png" alt="image-20240730110151503" style="zoom:67%;" /> 



**具体实现：**

1. 从 token 中获取登录用户信息
2. 传参（description、title、content），存在 map 中
3. 传参合法性判断
4. 生成 Bot 数据结构（传入参数）
5. 插入数据库:botMapper.insert(bot)
6. 返回正确信息(map.put("error_message","success"));return map;





**调用过程：**

1. Controller 中获取定义post 参数，从前端获取
2. 获取的参数通过调用定义好的后端 AddService，存入数据库





**前端调试：**

在前端 bot 页面调试

UserBotIndexView.vue

```html
<template>
    <ContentField>
        我的机器人
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
import $ from 'jquery'
import {useStore} from 'vuex'
 
export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();
        $.ajax({
            url: "http://127.0.0.1:3002/user/bot/add/",
            type: "post",
            data: {
                title : "Bot的标题",
                description : "Bot的描述",
                content : "Bot的内容",
            },
            headers:{
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp){
                console.log(resp);
            },
            error(resp){
                console.log(resp); 
            }
        })
    }
}   
</script>

<style scoped>

</style>
```





#### 2.2UpdateService

更新某个 bot 的信息

- 可更新信息：title、description、content
- 合规性检查：
  - 和add 一样
    - 但 description可以为空（即保持不变）
    - content 也可以为空（保持不变）
  - 增加：
    - 不能修改别人的 bot
    - bot 不存在的判断
- 根据构造好的信息，生成一个新 bot
  - 用接口更新到数据库（byid）







## 5.2统计代码量&回滚版本

### 统计代码量



统计后端

![image-20240801103253075](./SpringBoot 框架课.assets/image-20240801103253075.png)

```
//统计后端
/Users/godice/Documents/coding/kob/backend/src/main/java/com/kob/backend
find . -name '*.java' | xargs cat | wc -l
```

![image-20240801103421765](./SpringBoot 框架课.assets/image-20240801103421765.png)



### 回滚版本

```
git log//查看历史记录
```

![image-20240801104355634](./SpringBoot 框架课.assets/image-20240801104355634.png)

取出想要回滚的版本号前 7位

```
git reset --hard f91bfce//目前最新版本
git reset --hard a05c456//注册页面版本

git reset --hard HEAD^//回到上个版本
git reset --hard HEAD^//回到上上个版本
//以此类推

git reset --hard HEAD~100//回到 100 个版本以前

//查看回滚记录
git reflog
```





## 5.3个人中心页面（前端）

前端页面、模态框（代码）、代码编辑器集成





### 1ui设计

![image-20240801134748939](./SpringBoot 框架课.assets/image-20240801134748939.png)



### 2grid布局(bootstrap)

把每一行分为 12 列——可以任意规定每一个部分所占的份



布局如图

![image-20240801151139512](./SpringBoot 框架课.assets/image-20240801151139512.png)

代码如下

```html
<template>
    <div class="container"><!--container会动态调整内部元素的大小-->
        <div class = "row">
            <div class="col-3">
                <div class="card photo-card">
                    <div class="card-header">
                        我的头像
                    </div>
                    <div class="card-body">
                        <img :src="$store.state.user.photo" alt="" style="width: 100%;"><!--如果 src 是表达式，不是一个字符串，在 src 前面需要加冒号-->
                    </div>
                </div>
            </div>

            <div class="col-9">
                <div class="card bot-card">
                    <div class="card-header">
                        <span class="bot-name"> 我的Bot </span>
                        <button type="button" class="btn btn-success float-end">创建Bot</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

 
export default{
    setup(){}
}   
</script>




<style scoped>
div.card{
    margin-top: 20px;
}
div.photo-card .card-header {
    text-align:center;
    padding-bottom: 0; /* 减少底部填充 */
    border-bottom: none; /* 移除底部边框 */
    color: rgb(65, 20, 169);
    font-size: 120%;
}

div.photo-card .card-body {
    padding-top: 0; /* 减少顶部填充 */
}

div.bot-card .card-header{
    text-align:left;
    color: rgb(100, 88, 203);
}

div.bot-card .card-header .bot-name{
    font-size: 135%;
}


</style>
```





### 3获取bot列表



#### 获取 bot信息

```javascript
<script>
import { useStore } from "vuex"
import $ from 'jquery'
import { ref } from 'vue'
 
 
export default{

    setup(){
        const store = useStore();
        let bots = ref([]);

        const refresh_bots = () =>{
            $.ajax({
                url: "http://127.0.0.1:3002/user/bot/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token
                },
                success(resp){
                    bots.value = resp;
                }
            })
        }

        refresh_bots();

        return{
            bots,
        }
    }
}   
</script>
```





#### 渲染到前端

把上面获取的 bots 列表变量渲染到前端



bootstrap 中找 tables

```html
<div class="card-body">
    <table class="table table-striped table-hover">
        <thead><!--表头-->
            <tr><!--每一行-->
                <th>名称</th><!--表头单元格-->
                <th>创建时间</th>
                <th>修改时间</th>
                <th class="float-end" style="margin-right: 23%;">操作</th>
            </tr>
        </thead>
        <tbody><!--表体-->
            <tr v-for="bot in bots" :key="bot.id"><!--循环写法：必须绑定一个唯一的元素-->
                <td> {{ bot.title }}</td><!--表格数据单元格-->
                <td> {{ bot.createtime }} </td>
                <td> {{ bot.modifytime }} </td>
                <td><!--修改、删除按钮-->
                    <button type="button" class="btn btn-dark  float-end">删除</button>
                    <button type="button" class="btn btn-info float-end" style="margin-right: 6%;">修改</button>
                </td>
            </tr>
        </tbody>
    </table>
</div>  
```





### 4创建de模态框

在浮窗中完成title、description、content的写入（同时还需要集成一个代码编辑器）

效果如图

![image-20240802100743394](./SpringBoot 框架课.assets/image-20240802100743394.png)

#### **模态框：modal**

bootstap模板

```html
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
```

想实现点哪个按钮点开，就把它复制到哪个按钮上

```
data-bs-toggle="modal" data-bs-target="#exampleModal"
```

控制大小

![image-20240802094223434](./SpringBoot 框架课.assets/image-20240802094223434.png)





#### 模态框内容-表单

表单-forms

```html
<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">Email address</label>
  <input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
</div>
<div class="mb-3">
  <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
  <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
</div>
```

修改如下

```html
<div class="mb-3">
    <label for="add-bot-description" class="form-label">简介(description)</label>
    <textarea class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot的简介"></textarea>
</div>
<div class="mb-3">
    <label for="add-bot-code" class="form-label">代码(code)</label>
    <textarea class="form-control" id="add-bot-code" rows="10" placeholder="请编写控制Bot运行的代码"></textarea>
</div>
```



注：后期这里需要换成一个写代码的编辑器





### 5绑定变量/对象（对接后端）



#### 绑定变量

把从后端获取的resp 绑定到前端

```html
<html>
<div>
	{{bots.title}}
</div>
</html>

<script>
import {ref} from 'vue'

let bots = ref([]);


$.ajxa{

	success(resp){
		bots.valus = resp
	}
}


return{
	bots, 
}
</script>
```



#### 绑定对象

把前端获取的信息传输到后端(也可以用 ref，但是reactive更方便)

实现如下

```html
<html>
<input v-model="botadd.title" type="text" class="form-control" id="add-bot-tile" placeholder="请输入Bot名称">

<textarea v-model="botadd.description" class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot的简介"></textarea>

<textarea v-model="botadd.content" class="form-control" id="add-bot-code" rows="10" placeholder="请编写控制Bot运行的代码"></textarea>


<button type="button" class="btn btn-info" @click="add_bot">创建</button>
</html>


<div
{{ botadd.error_message  }}
</div>


<script>
import {reactive} from 'vue'

setup(){
	const botadd = reactive({
            title: "",
            description: "",
            content: "",
            error_message: "",
  })
  const add_bot = () =>{
      botadd.error_message = "";//先清空上一次的报错
      $.ajax({
          url: "http://127.0.0.1:3002/user/bot/add/",
          type: "post",
          data:{
              title: botadd.title,
              content: botadd.content,
              description: botadd.description,
          },
          headers: {
              Authorization: "Bearer " + store.state.user.token,
          },
          success(resp){
              if(resp.error_message === 'success'){
                  refresh_bots();
              }else{
                  botadd.error_message = resp.error_message;
              }
          },
      })
  }
}


return{
	addbot,
	bot_add,
}
</script>
```





### 6 模态框改进

在上述完成的模态框有两个问题

1. 创建成功后模态框并没有关闭
2. 第二次打开模态框，里面的信息还是没有清空。



创建成功后自动关闭模态框&清空信息

```html
<html>
  <div class="modal fade" id="add-bot-btn" tabindex="-1">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
        <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">创建Bot</h1><!--标题-->
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
  <div class="modal-body">
  
</html>
          
<script>
import { Modal } from "bootstrap/dist/js/bootstrap";//关闭模态框
success(resp){
  if(resp.error_message === 'success'){
      botadd.title = "";//成功后要清空信息，防止污染下一次开启按钮
      botadd.content = "";
      botadd.description = "";
      Modal.getInstance("#add-bot-btn").hide();
      refresh_bots();
</script>
```







### 7 时区处理（前后端时区不一致）

在前端页面中显示的创建和修改时间不对

![image-20240802141810613](./SpringBoot 框架课.assets/image-20240802141810613.png)

而在后端数据库中是正确的

![image-20240802141739391](./SpringBoot 框架课.assets/image-20240802141739391.png)



在 pojo 中修改（数据库的）时区即可(上海：东八区)

![image-20240802142034017](./SpringBoot 框架课.assets/image-20240802142034017.png)



### 8 删除&修改按钮实现

#### 删除

```html
<html>
  <button type="button" class="btn btn-dark  float-end" style="color: rgb(178, 29, 66);" @click="remove_bot(bot)">删除</button>
</html>

<script>
const remove_bot = (bot) =>{//传入的 bot 参数是从前端获取的（循环列表中bot in bots）
    $.ajax({
        url: "http://127.0.0.1:3002/bot/remove/",
        type: "post",
        headers:{
            Authorization: "Bearer " + store.state.user.token,
        },
        data:{
            bot_id: bot.id,
        },
        success(resp){
            if(resp.error_message = "success"){
                refresh_bots();
            }
        }
    })
}


refresh_bots();

return{
    bots,
    botadd,
    add_bot,  
    remove_bot,  
}
</script>

```



#### 修改

用创建 bot 改一下



```html
<html>

<!-- Modal 修改-->
<!--id如果是表达式需要加冒号，在另一个地方引用这个 id 时，id 前需要加#-->
<!--这里用的自定义的update-bot-modal加上不同表单行数对应的不同bot的 id-->
<div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
        <div class="modal-header">
            <h1 class="modal-title fs-5">创建Bot</h1><!--标题-->
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
            <div class="mb-3">
              	<!--这个只是表单的抬头部分，是多少没有用-->
                <label for="add-bot-title" class="form-label">名称(title)</label>
                <!--v-model是双向绑定，底层数据会改，同时数据改了也会影响展示-->
              	<!--这里绑定的表单中的bot in bots-->
                <input v-model="bot.title" type="text" class="form-control" id="add-bot-tile" placeholder="请输入Bot名称">
            </div>
            <div class="mb-3">
                <label for="add-bot-description" class="form-label">简介(description)									</label>
                <textarea v-model="bot.description" class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot的简介"></textarea>
            </div>
            <div class="mb-3">
                <label for="add-bot-code" class="form-label">代码(code)</label>
                <textarea v-model="bot.content" class="form-control" id="add-bot-code" rows="10" placeholder="请编写控制Bot运行的代码"></textarea>
            </div>

        <!--模态框中的两个按钮&告警信息-->
        </div>
        <div class="modal-footer">
            <div class="badge bg-primary text-wrap" style="width: 10rem; color: chocolate; font-size: large; margin-right: 1%;">
            {{ bot.error_message  }}<!--绑定 bot in bots 的报错信息-->
            </div>
            <button type="button" class="btn btn-info" @click="update_bot(bot)">保存修改						</button>
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消					</button>
        </div>
        </div>
    </div>
</div>
<!--end Modal修改-->





  
</html>


<script>
        const update_bot = (bot) =>{//从按钮中获取
            botadd.error_message = "";//先清空上一次的报错
            $.ajax({
                url: "http://127.0.0.1:3002/user/bot/update/",
                type: "post",
                data:{
                    bot_id: bot.id,
                    //这里因为在上面 html 中双向绑定了，打开模态框后修改对应数据
                    //就会修改对应 bot 的title、content、description
                    //点击修改会触发本函数，再传入修改后的数据
                    title: bot.title,
                    content: bot.content,
                    description: bot.description,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp){
                    if(resp.error_message === 'success'){
                        //成功后关闭模态框
                        //按照不同 bot 对应的不同 botid 绑定的
                        Modal.getInstance('#update-bot-modal-' + bot.id).hide();
                        refresh_bots();
                    }else{
                        botadd.error_message = resp.error_message;
                    }
                },
            })
        }



</script>
```





### 9集成代码编辑器

#### 集成编辑器

安装依赖

```
vue3-ace-editor
ace-builds
```



导入，并加配置

```html
<html>
  <!--替换代码区域text area-->
  <VAceEditor
    v-model:value="botadd.content"
    @init="editorInit"
    lang="c_cpp"
    theme="textmate"
    style="height: 300px" /> 
</html>





<script>
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';
  

export default{
  components:{
  VAceEditor
},
    setup(){
      ace.config.set(
        "basePath", 
        "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")
    <VAceEditor
    }
}

</script>
```



#### 代码不高亮解决

```html
<template>
<VAceEditor v-model:value="botadd.content" @init="editorInit" lang="c_cpp"
                                                theme="textmate" style="height: 300px" :options="{
                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                    enableSnippets: true, // 启用代码段
                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                    fontSize: 18, //设置字号
                                                    tabSize: 4, // 标签大小
                                                    showPrintMargin: false, //去除编辑器里的竖线
                                                    highlightActiveLine: true,
                                                }" />



  
<VAceEditor v-model:value="bot.content" @init="editorInit"
                                                                lang="c_cpp" theme="textmate" style="height: 300px"
                                                                :options="{
                                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                                    enableSnippets: true, // 启用代码段
                                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                                    fontSize: 18, //设置字号
                                                                    tabSize: 4, // 标签大小
                                                                    showPrintMargin: false, //去除编辑器里的竖线
                                                                    highlightActiveLine: true,
                                                                }" />


</template>




<script>
	import 'ace-builds/src-noconflict/mode-c_cpp';
	import 'ace-builds/src-noconflict/mode-json';
	import 'ace-builds/src-noconflict/theme-chrome';
	import 'ace-builds/src-noconflict/ext-language_tools';
	
  
  setup(){//修改为
            ace.config.set(
            "basePath",
            "https://cdn.jsdelivr.net/npm/ace-builds@" +
            require("ace-builds").version +
            "/src-noconflict/")
  }
</script>


```







# 微服务（匹配系统）

- 使用了微服务的只有两块
  - 微服务 1：匹配系统微服务的具体实现（见6.3）
  - 微服务 2：bot 执行代码（不在本章节，见后续章节）
  - 6.1&6.2 都没实现微服务
    - 6.1ws 链接的建立和游戏逻辑
    - 6.2 游戏的前端同步问题



这章实现了联机对战&匹配系统（Matching System）

![image-20240808102403198](./SpringBoot 框架课.assets/image-20240808102403198.png)

整个系统流程逻辑

![image-20240826210213904](./SpringBoot 框架课.assets/image-20240826210213904.png)



## 6.1微服务（游戏逻辑）

本章内容：

```
规划介绍
改 userId 验证建立 websocket 链接为jwt验证建立链接
前端匹配界面(只有前端)
前端匹配界面(后端交互)
生成同步地图(前端生成地图改成后端生成发送到前端)
```



- 异步 or 计算量比较大的过程：一般用一个额外的服务去控制
  - client 向服务器发起请求，到匹配成功，有一段时间的过程（异步）

### 1匹配系统流程



#### 选用服务

Matching System匹配系统（微服务）——Server服务器后端——client 用户

- client向 server 发送请求，server 发到 matching system
- matching system在众多请求 client 中选：战斗力最接近的用户组成一组
  - 返回给 server，再返回给 client，展示到前端
  - （异步）
- 使用 websocket 协议

![image-20240804142751805](./SpringBoot 框架课.assets/image-20240804142751805.png)

- http 协议：只能 client主动发送，server 响应

- websoket 协议：client 和 server 都可以主动发送请求（对称、全双工）



#### 游戏逻辑处理

- 应该迁移到后端的逻辑
  - 地图的生成（两个 client 的地图都是在自己的前端统一生成的）
    - 需要地图一致
  - 判断蛇撞头输赢的判定
  - 蛇的移动
- 所以，**整个游戏的过程的数据处理都应该放到后端**
  - 前端只负责渲染图像



**关于前后端游戏的逻辑判断**

- 我们这个相当于回合制游戏
  - 信息的通信传递很慢，可以在后端处理
- fps类的游戏，操作特别频繁，如果都在服务器判断——延迟会很高
  - 很多 fps 判定是否击中对方，在本地判断的（所以挂多）



#### 服务流程

<img src="./SpringBoot 框架课.assets/image-20240804150224545.png" alt="image-20240804150224545" style="zoom: 25%;" /> 

- 1：匹配成功后，server 随机生成对战地图
- 2：把地图返回给两个 client
- 3：server 等待两条蛇的输入信息——移动（人/代码）
  - 3.1等待client 用户从自己的本地键盘输入移动信息传过来
  - 3.2从后端获取 bot 代码，等待代码获取当前地图和蛇的位置信息后的下一回合的输出
    - （微服务）
- 4：判断当前回合的输赢
  - 超时方输
  - 撞墙 or 操作不合法
  - 没有人输，则循环到第三步







### Websocket

- 全双工：每一个client前端建立的链接，都会在后端维护
  - 一个websocket server类，每次建立链接（开一个线程）new一个这个类的实例：去维护这个链接
- 流程：
  - 用户每次开始匹配，就会在 server建立一个websocket链接
  - 收到匹配的请求，server 建立链接后，会将信息向matching system微服务发送
  - matching system微服务收到很多请求后，会选战力最相近的两名用户匹配
  - 匹配成功后 matching 会返回给 websocket 服务器（后端）
  - websocket 服务器会把信息返回给两个 client（前端浏览器）
    - 同时在服务器创建一个游戏（包括生成地图、建立对战...）





### 本章内容

实现红色部分

![image-20240804151604315](./SpringBoot 框架课.assets/image-20240804151604315.png)





### 2Websocket Server

#### 配置

**集成 websocket**

- 在pom.xml文件中添加依赖（maven）：

  - spring-boot-starter-websocket
  - fastjson（前后端通信）
- 添加config.WebSocketConfig配置类：

```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }
}
```

- 添加consumer.WebSocketServer类

```
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        // 建立连接
    }

    @OnClose
    public void onClose() {
        // 关闭链接
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // 从Client接收消息
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}
```

- 配置config.SecurityConfig（放行 websocket 的所有链接）

```
@Override
public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/websocket/**");
}
//放行了"/websocket/{token}"
```

- 验证jwt



#### 后端向前端发信息

用websocket 中的session包（不是 http 的 session）实现，修改Consumer.WebSocketServer中

```java
package com.kob.backend.Consumer;

import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
//映射到哪个链接
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    //存储userid 和websocket 链接的映射，用于匹配成功后向对应前端返回信息
    //ConcurrentMap是线程安全的哈希表，存储所有链接的映射
    //static是所有实例可见的全局变量
    private static ConcurrentMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
    private User user;//user对象信息需要存到 session 中
    private Session session = null;

    //websocket不是 spring 标准组件、注入需要特殊处理，不能简单Autowired
    //websocket 不是单例模式（线程安全：每个类同一时间只能有一个实例）
    private static UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper = userMapper;
    }//静态变量访问需要用类名

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        // 建立连接
        this.session = session;
        System.out.println("Connected!");
        Integer userId = Integer.parseInt(token);
        this.user = userMapper.selectById(userId);//获取新建立链接的用户 id
        users.put(userId, this);//新建立链接的 userId 和 websocket链接的映射
    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("Disconnected!");
        //断开连接需要删除链接映射
        if(this.user == null){
            users.remove(this.user.getId());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Server从Client接收到消息
        System.out.println("Receive Message!");

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    //server 向client 发信息
    public void sendMessage(String message){
        synchronized(this.session){//因为是异步的，所以要加锁（session的同步块：保证多线程访问的安全）
            try{
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e){//需要捕获一个 io 异常
                e.printStackTrace();//输出
            }
        }
    }
}
```





#### 调试

##### 全局变量的定义

（在不同页面间切换：需要用到同一个变量的不同状态的时候——需要用全局变量）

在pk 页面进行调试，建立pk 页面相关的全局变量/src/store/pk.js

```javascript
export default{
    state:{
        status: "matching",//matching匹配界面，playing对战界面
        socket: null,//建立的websocket链接
        opponent_user: "",//对手的名字
        opponent_photo: "",//对手的头像
        opponent_bot_title: "",//对手的 bot title
        opponent_ranking: "",//对手 rank 分
    },
    getters:{

    },
    mutations:{

    },
    actions:{

    },
    modules:{

    }
}
```

module导入到全局module中：在pk.js中导入index.js

```javascript


export default{
    state:{
        status: "matching",//matching匹配界面，playing对战界面
        socket: null,//建立的websocket链接
        opponent_username: "",//对手的名字
        opponent_photo: "",//对手的头像
        opponent_bot_title: "",//对手的 bot title
        opponent_rating: "",//对手 rank 分
    },
    getters:{

    },
    mutations:{
        //前端成功创建链接后，需要把信息更新到state中
        updateSocket(state, socket){
            state.socket = socket;
        },
        updateOpponent(state, opponent){
            state.opponent_user = opponent.username;
            state.opponent_photo = opponent.photo;
            state.opponent_rating = opponent.rating;
            state.opponent_bot_title = opponent.title;
        }


    },
    actions:{

    },
    modules:{

    }
}
```



##### pk 页面调试

```html
<template>
    <div>
        <PlayGround> </PlayGround>
    </div>
    
</template>

<script>
import PlayGround from "@/components/PlayGround.vue"//对战区域的组件，playground 又引入了gameMap
import { onMounted, onUnmounted } from "vue";//组件挂载之后执行的函数、组件被卸载执行的函数
import { useStore } from 'vuex'

export default{
    components:{
        PlayGround,
    },
    setup(){
        const store = useStore();
        const socketUrl = `ws://127.0.0.1:3002/websocket/${store.state.user.id}/`;

        let socket = null;
        
        onMounted(() => {//页面成功挂载
            //*begin:这段代码写在 setup中也可，不需要一定写在onMounted中
            socket = new WebSocket(socketUrl);

            socket.onopen = () => {
                console.log("connected!");
            },
            socket.onmessage = msg => {
                const data = JSON.parse(msg.date);
                console.log(data);
            },
            socket.onclose = () => {
                console.log("disconnected");
            }
            //* end
        });
        onUnmounted(() =>{
            socket.close();
        })
    }    




}   
</script>




<style scoped>

</style>
```



### 3websocket逻辑

- 前端组件挂载成功

  - 访问后端 api建立ws链接

- 链接建立后：前后端函数都是类似的

  - onopen
  - onmessage

- 切换页面则执行

  - onclose

- 退出挂载onUnmounted

  - 执行onUnmounted

  

#### 3.1后端

```
根据 token进行后端访问地址映射"/websocket/token"（这里最后没有'/'，不然会报错）

users//线程安全的全局哈希表用于存储——所有映射(userid,ws链接)
session//存当前ws链接
userMapper//非单例的特殊数据库注入方式


//函数

onOpen{
	从token中获取userid
	建立映射users(userId,this)即userId到 ws 链接
}

onClose{
	if(this.user == null){
  	users.remove(this.user.getId());
  }
}
...
```

#### 3.2前端

```
onMounted(){//vue组件成功挂载：vue 组件被翻译为 html 成功渲染，会触发
  socket = new WebSocket(socketUrl);//访问后端 api 建立连接
  onopen{
    访问页面，建立 ws 链接成功，会触发 open
  }
  onclose{
    （切换页面到其他页面）则进行当前的函数操作
  }
}


onUnmounted{
	组件退出挂载，则关闭链接
	socket.close();	//注意这里必须要断开链接,这个函数会触发onclose
}
```





#### 3.3断开逻辑

注：刷新、关闭页面会自动断开 ws 链接

- 1正常逻辑的情况
  - 每次加载页面：会建立链接
  - 切换到其他页面：会断开链接
  - 刷新会断开所有链接

- 2如果在前端onUnmounted中没有断开链接
  - 加载页面：会建立链接
  - 起换到其他页面：不会断开链接
    - 现在再切换回去：会建立一个新的链接（切几次，就建立几个新的，都不会断掉）
  - 这个时候再刷新：会断掉建立的所有链接
- 3把socket.close();放在前端的 onclose 中也是和2 一样的效果
  - 必须放在onUnmounted中



#### 3.4**函数触发逻辑

- onclose只和ws链接的关闭有关
  - 触发条件
    - 刷新浏览器
    - 关闭标签页 or 浏览器
    - 调用socket.close()
- onUNmounted只和 vue 组件的卸载有关
  - 触发条件：vue 组件被卸载，即从 Dom 移除
    - 路由跳转到另一个页面（当前组件会被卸载）
    - 页面刷新：会卸载所有 vue 组件
    - 标签页关闭：也会卸载所有组件
- 总结：
  - 刷新、关闭会触发onclose&onUNmounted
  - 路由跳转只会触发onUnmounted
- 所以：在onUnmounted中加上socket.close()
  - 就使得onUNmounted和onclose的触发逻辑统一了





- onopen：只和 ws 的建立有关
  - 页面加载后，成功建立了 ws 链接会触发
  - ws 断开后，重新再建立了链接，也会触发
    - （切页面再切换回来，且成功建立了链接）
- Mounted
  - 页面加载成功（组件被挂载到 Dom 中）



### 4 加上 jwt 验证

前面调试，用的 userid 做验证，现在换成 jwt-token 的验证（ws 没有 http 的 headers 的概念）



创建一个工具类

Consumer.Utils.JwtAuthentication——（从 token 中解析出 userId）

```java
package com.kob.backend.Consumer.Utils;

import com.kob.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;

public class JwtAuthentication {
    //static，是静态方法——可以直接调用，而不希望通过实例调用
    public static Integer getUserId(String token){
        int userId = -1;
        try {//解析 token，如果能解析出 userid，则为合法
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}
```

WeSocketServer中，onopen，获取 userid

```
Integer userId = JwtAuthentication.getUserId(token);
```







### 5pk的匹配界面（前端）

#### 配置与设计

pk页面下有对战界面和匹配界面（同时需要两个界面切换）

```html
<template>
    <div>
        <PlayGround v-if="$store.state.pk.status === 'playing'"> </PlayGround>
    </div>
    
</template>
```



匹配界面/src/components/MatchGround.vue



- 全局变量
  - 在template中写需要加$
  - 在 script 里面写不需要加
- 在“ ”中写变量表达式，前面需要加冒号

<img src="./SpringBoot 框架课.assets/image-20240805163050996.png" alt="image-20240805163050996" style="zoom:50%;" />





布局：两个对手信息按钮（没匹配成功前对手信息未加载）&一个开始匹配按钮

用 grids

<img src="./SpringBoot 框架课.assets/image-20240805161905572.png" alt="image-20240805161905572" style="zoom: 25%;" />



效果图

<img src="./SpringBoot 框架课.assets/image-20240806105125510.png" alt="image-20240806105125510" style="zoom: 25%;" />

<img src="./SpringBoot 框架课.assets/image-20240806105139354.png" alt="image-20240806105139354" style="zoom:25%;" />

代码如下

```html
<template>
    <div class="matchground">
        <div class="row">
            <div class="col-6">
                <div class="user-photo" style="text-align: center;">
                    <img :src="$store.state.user.photo" alt="">
                </div>

                <div class="user-username" >
                    {{ $store.state.user.username }}
                </div>
            </div>


            <div class="col-6">
                <div class="user-photo" style="text-align: center;">
                    <img :src="$store.state.pk.opponent_photo" alt="">
                </div>

                <div class="user-username" >
                    {{ $store.state.pk.opponent_username }}
                </div>
            </div>
            <div class="col-12" style="text-align: center; margin-top: 13%;">
                <button @click="click_match_btn_event" type="button" :class="'btn ' + match_btn_color + ' btn-lg ' + 'rounded-pill'" style="width: 20% ;">
                <i class="fas fa-sync-alt">{{ match_btn_info }}</i> 
                </button> 
            </div>  


        </div>
    </div>
</template>

<script>
import {ref} from "vue"


export default {
    setup(){
        let match_btn_info = ref("开始匹配");
        let match_btn_color = ref("btn-outline-light");
        const click_match_btn_event = () =>{
            if(match_btn_info.value === "开始匹配"){
                match_btn_info.value = "取消匹配";
                match_btn_color.value = "btn-outline-danger";
            }else{
                match_btn_info.value = "开始匹配";
                match_btn_color.value = "btn-outline-light";
            }
        }
        return{
            click_match_btn_event,
            match_btn_info,
            match_btn_color,
        }
    },
    

};
</script>



<style scoped>
div.matchground{
    width:50vw;
    height: 70vh;
    margin: 40px auto;
    background-color: rgba(50, 50, 50, 0.5);
}

div.user-photo{
    text-align: center;
}

div.user-photo > img{
    border-radius: 50%;
    width: 20vh;
    margin-top: 20%;
}

div.user-username{
    margin-top: 5%;
    text-align: center;
    font-size: 24px;
    color: white;
    font-weight: 800;
    text-shadow: 2px 2px 4px rgba(177, 49, 130, 0.5);
}



</style>
```

全局变量store/pk.js

```javascript


export default{
    state:{
        status: "matching",//matching匹配界面，playing对战界面
        socket: null,//建立的websocket链接
        opponent_username: "",//对手的名字
        opponent_photo: "",//对手的头像
    },
    getters:{

    },
    mutations:{
        //前端成功创建链接后，需要把信息更新到state中
        updateSocket(state, socket){
            state.socket = socket;
        },
        updateOpponent(state, opponent){
            state.opponent_username = opponent.username;
            state.opponent_photo = opponent.photo;
        }


    },
    actions:{

    },
    modules:{

    }
}
```



### 7匹配界面（后端）



#### 业务逻辑

- 前端点击开始匹配——发送建立 ws 链接请求——建立 ws 后放到匹配池（本节暂时在内存实现）

- 匹配池满两人——返回给 server——server 返回给 client

  - 可以设置 delay，为了让 rating 相近的人匹配上

- 操作：

  - 开始匹配——放入匹配池
  - 取消匹配——删除

- 前端：

  - 前端状态码正常为：stopmatching
  - 点击开始匹配：前端状态码startmatching
  - 点击取消匹配：前端状态码stopmatching
  - （每次改变状态，前端会 sendmessage 给后端）

- 后端：

  - 后端根据状态码设置“路由”，出发不同的函数（stopMatching()，startMatching() ）

  - 后端维护一个matching pool匹配池（>=2个人即开始配对）

  - 匹配成功

    - 从匹配池中解析出两个对象的信息（分别用 json 对象存）
    - 向前端主动发送 json 对象（要先 json 序列化）

    



#### 实现代码

后端代码

```java
package com.kob.backend.Consumer;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.Consumer.Utils.JwtAuthentication;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
//映射到哪个链接
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    //存储userid 和websocket 链接的映射，用于匹配成功后向对应前端返回信息
    //ConcurrentMap是线程安全的哈希表，存储所有链接的映射
    //static是所有实例可见的全局变量
    final private static ConcurrentMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
    //CopyOnWriteArraySet线程安全
    //final 声明为常量，（引用地址不可以改，值可以改）进一步保证线程安全
    final private static CopyOnWriteArraySet<User>  matchpool = new CopyOnWriteArraySet<>();//对战页面点击开始匹配后——用户进入匹配池

    private User user;//user对象信息需要存到 session 中
    private Session session = null;

    //websocket不是 spring 标准组件、注入需要特殊处理，不能简单Autowired
    //websocket 不是单例模式（线程安全：每个类同一时间只能有一个实例）
    private static UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper = userMapper;
    }//静态变量访问需要用类名

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session = session;
        System.out.println("Connected!");
        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);//获取新建立链接的用户 id

        if(this.user != null){
            users.put(userId, this);//新建立链接的 userId 和 websocket链接的映射
            System.out.println(users);
        }else{
            this.session.close();
            System.out.println("Invalid token!");
        }

    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("Disconnected!");
        //断开连接需要删除链接映射
        if(this.user == null){
            users.remove(this.user.getId());
            matchpool.remove(this.user);
        }
    }

    private void startMatching(){
        System.out.println(this.user.getUsername() + " start matching!");
        matchpool.add(this.user);
        System.out.println("matchpool" + matchpool);

        while(matchpool.size() >= 2){
            Iterator<User> it = matchpool.iterator();
            User a = it.next(), b = it.next();
            matchpool.remove(a);
            matchpool.remove(b);
            JSONObject respA = new JSONObject();
            respA.put("event", "match");
            respA.put("opponent_username", b.getUsername());
            respA.put("opponent_photo", b.getPhoto());
            //向前端返回信息
            users.get(a.getId()).sendMessage(respA.toJSONString());

            JSONObject respB = new JSONObject();
            respB.put("event", "match");
            respB.put("opponent_username", a.getUsername());
            respB.put("opponent_photo", a.getPhoto());
            //向前端返回信息
            users.get(b.getId()).sendMessage(respB.toJSONString());
            System.out.println("恭喜"+ a.getUsername() + b.getUsername() +    "成功匹配");
        }
    }
    private void stopMatching(){
        System.out.println("stop matching");
        matchpool.remove(this.user);
        System.out.println("matchpool" + matchpool);
    }
    @OnMessage
    public void onMessage(String message, Session session) {//收到的信息当作路由（交给哪个函数处理）
        // Server从Client接收到消息
        System.out.println("Receive Message!");
        JSONObject data = JSONObject.parseObject(message);
        String event = data.getString("event");
        if("start-matching".equals(event)){
            startMatching();
        }else if("stop-matching".equals(event)){
            stopMatching();
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    //server 向client 发信息
    public void sendMessage(String message){
        synchronized(this.session){//因为是异步的，所以要加锁（session的同步块：保证多线程访问的安全）
            try{
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e){//需要捕获一个 io 异常
                e.printStackTrace();//输出
            }
        }
    }
}
```

前端代码

```html
<template>
    <div>
        <PlayGround v-if="$store.state.pk.status === 'playing'"> </PlayGround>
        <MatchGround v-if="$store.state.pk.status === 'matching'"></MatchGround>
    </div>
    
</template>

<script>
import PlayGround from "@/components/PlayGround.vue"//对战区域的组件，playground 又引入了gameMap
import MatchGround from "@/components/MatchGround.vue"//匹配区组件
import { onMounted, onUnmounted } from "vue";//组件挂载之后执行的函数、组件被卸载执行的函数
import { useStore } from 'vuex'


export default{
    components:{
        PlayGround,
        MatchGround,
    },
    setup(){
        const store = useStore();
        const socketUrl = `ws://127.0.0.1:3002/websocket/${store.state.user.token}/`;//字符串中有${}表达式操作的话，需要用`，不能用引号

        let socket = null;

        onMounted(() => {//页面成功挂载

            store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
            //*begin:这段代码写在 setup中也可，不需要一定写在onMounted中
            socket = new WebSocket(socketUrl);
            store.commit("updateSocket", socket);//建立的ws链接更新到全局变量

            socket.onopen = () => {
                console.log("connected!");
            },
            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                if(data.event === "match"){//匹配成功
                    store.commit("updateOpponent",{
                        username: data.opponent_username,
                        photo: data.opponent_photo,
                    }),
                    setTimeout(() => {//匹配成功后2s后再跳转页面
                        store.commit("updateStatus", "playing")
                    },2000)
                }
            },
            socket.onclose = () => {
                console.log("disconnected");
                store.commit("updateStatus", "playing")
            }
            //* end
        });
        onUnmounted(() =>{
            socket.close();
        })
    }    




}   
</script>




<style scoped>

</style>
```





### 9地图同步生成

现在地图是分别在自己的前端生成的（不同步），需要改为在后端生成一幅一样的地图

（在后端再实现一遍地图生成的逻辑GameMap.js）





/Consumer/Utils/Game

```java
package com.kob.backend.Consumer.Utils;

import java.util.Random;

public class Game {
    final private Integer rows;
    final private Integer cols;
    final private Integer inner_walls_count;
    final private int[][] g;
    final private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public Game(Integer rows, Integer cols, Integer inner_walls_count){
        this.rows = rows;
        this.cols = cols;
        this.inner_walls_count = inner_walls_count;
        this.g = new int[rows][cols];//
    }

    public int[][] getG(){//返回棋盘
        return g;
    }

    //DFS 深度优先
    public boolean check_connectivity(int sx, int sy, int tx, int ty){//起点(sx,sy)到终点(tx,ty)是否存在路径
        if(sx == tx && sy == ty) return true;
        g[sx][sy] = 1;//已访问过（同时这里也会被标记为有墙，因为是全局变量，所以后面必须恢复）

        for(int i = 0; i < 4; ++i){//四个方向
            int x = sx + dx[i], y = sy + dy[i];//预访问
            if(x >= 0 && x < this.rows && y >= 0 && y < this.cols && g[x][y] == 0){//预访问点是否在棋盘范围内，且没有障碍物
                if(check_connectivity(x, y, tx, ty)){
                    g[sx][sy] = 0;//重置源点为无墙状态
                    return true;
                }
            }
        }
        g[sx][sy] = 0;//重置为未访问且无墙状态
        return false;
    }
    private boolean draw(){//画地图
        //清空之前生成的
        for(int i = 0; i < this.rows; ++i){
            for(int j = 0; j < this.cols; ++j){
                this.g[i][j] = 0;
            }
        }

        //给四周加上障碍物
        for(int r = 0; r < this.rows; ++r){
            g[r][0] = g[r][this.cols - 1] = 1;
        }
        for(int c = 0; c < this.cols; ++c){
            g[0][c] = g[this.rows - 1][c] = 1;
        }

        //随机生成障碍物
        Random random = new Random();
        for(int i = 0; i <  this.inner_walls_count / 2; ++i){
            for(int j = 0; j < 1000; ++j) {//防止随机到已放过障碍物的色块
                int r = random.nextInt(this.rows);
                int c = random.nextInt(this.cols);

                if(g[r][c] == 1 || g[this.rows - 1 - r][this.cols - 1 - c] == 1)//如果本位置or对称位置存在障碍物，则重新生成
                    continue;
                if(r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2)//左下右上（蛇生成位置）不能有障碍物
                    continue;

                g[r][c] = g[this.rows - 1 - r][this.cols - 1 - c] = 1;//对称生成
                break;
            }


        }

        return check_connectivity(this.rows - 2, 1, 1, this.cols - 2);
    }

    public void createMap(){//随机生成（合法）地图
        for(int i = 0; i < 1000; ++i){
            if(draw()){
                break;
            }
        }
    }
}
```

在pk.js中新设全局变量gamemap

```javascript
state:{
        status: "matching",//matching匹配界面，playing对战界面
        socket: null,//建立的websocket链接
        opponent_username: "",//对手的名字
        opponent_photo: "",//对手的头像
        gamemap: "",//后端生成的对战地图
    },
```

PkIndexView.vue后端发送消息时一起发送

```javascript
socket.onmessage = msg => {
    const data = JSON.parse(msg.data);
    if(data.event === "match"){//匹配成功
        store.commit("updateOpponent",{
            username: data.opponent_username,
            photo: data.opponent_photo,
        }),
        setTimeout(() => {//匹配成功后2s后再跳转页面
            store.commit("updateStatus", "playing")
        },2000)
        store.commit("updateGamemap", data.gamemap)
    }
},
```

GameMap.vue引入后端生成的地图的全局变量gamemap，传入GameMap.js

（这里在GameMap.js中的构造函数中，需要多定义一个传入的变量）

```javascript
import { useStore } from 'vuex';

export default{
    setup(){
        let parent = ref(null);//画布父类
        let canvas = ref(null);//画布
        const store = useStore();
        onMounted(() => {
            new GameMap(canvas.value.getContext("2d"), parent.value, store)     
        });
```

GameMap.js中需要接受参数，然后把地图传入渲染

```javascript
export class GameMap extends AcGameObject{
    constructor(ctx, parent, store){//构造函数，ctx 是画布，parent是画布的父元素——用来动态修改画布长宽
        super();//执行基类的构造函数
        
        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
    create_walls(){
        const g = this.store.state.pk.gamemap;//传入地图
        //画墙
        for(let r = 0; r < this.rows; ++r){
            for(let c = 0; c < this.cols; ++c){
                if(g[r][c]){
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }
    }
    //这里本来还有循环 1000 次生成地图的逻辑，也在后端实现了，需要删除
}
```



## 6.2微服务（同步）

主要实现了对战的同步问题、录像功能后端



### 1蛇位置的同步（后端）

解决：哪个玩家的哪条蛇在哪个位置（默认 a 在左下，b 在右上）

```java
//定义玩家类Player.java
package com.kob.backend.Consumer.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {//记录每个玩家的位置信息
    private Integer id;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps;//记录玩家的移动序列（每回合走动的方向）
}

//new实例Game.java
public Game(Integer rows, Integer cols, Integer inner_walls_count, Integer idA, Integer idB){
        this.rows = rows;
        this.cols = cols;
        this.inner_walls_count = inner_walls_count;
        this.g = new int[rows][cols];//

        playerA = new Player(idA, rows - 2, 1, new ArrayList<>());
        playerB = new Player(idB, 1, cols - 2, new ArrayList<>());

    }

public Player getPlayerA() {
        return playerA;
    }

public Player getPlayerB() {
        return playerB;
    }

//WebSocketServer.java创建地图、生成对象、传递信息
 Game game = new Game(13, 14, 20, a.getId(), b.getId());
            game.createMap();

            //地图信息（蛇的位置）
            JSONObject respGame = new JSONObject();
            respGame.put("a_id", game.getPlayerA().getId());
            respGame.put("a_sx", game.getPlayerA().getSx());
            respGame.put("a_sy", game.getPlayerA().getSy());
            respGame.put("b_id", game.getPlayerB().getId());
            respGame.put("b_sx", game.getPlayerB().getSx());
            respGame.put("b_sy", game.getPlayerB().getSy());
            respGame.put("map", game.getG());
						respA.put("game", respGame);//放入 A、B 中 sendMessage
						respB.put("game", respGame);
```



### 2 棋盘同步：蛇移动（后端）

#### 2.1通信思路

每局游戏都有三个棋盘，需要保持三个同步：1 个Server、2 个client



- 玩家对战：
  - Client1/2 分别给出下一回合的移动sendMessage 给 server
  - server 把对方的移动信息返回
- Bot 对战：
  - 服务器从本地获取 Bot 代码，获取 Bot 代码的输出
  - 广播给两个 client

<img src="./SpringBoot 框架课.assets/image-20240808095928293.png" alt="image-20240808095928293" style="zoom:33%;" />   



#### 2.2完整流程

![image-20240808102345043](./SpringBoot 框架课.assets/image-20240808102345043.png)



game 处理逻辑，不能单线程处理（否则只能顺序执行、且一个时刻只能开启一场游戏）

- 处理用户输入需要一个线程
- 判断逻辑也需要一个线程



<img src="./SpringBoot 框架课.assets/image-20240808110023355.png" alt="image-20240808110023355" style="zoom:50%;" />



需要把 game 改为支持多线程的类(game.java)

```
public class Game extends Thread
```

开启新线程的入口——重写方法——run

<img src="./SpringBoot 框架课.assets/image-20240808110321915.png" alt="image-20240808110321915" style="zoom:25%;" /> 

入口类

```
@Override
    public void run() {
        super.run();
    }
```

 WebSocketServer.java

```
game.start();//Tread的 api，进入一个新线程开始执行接下来的函数
```



#### 2.3变量读写问题

（一共涉及到 3 个线程，三个不同的地图的同步——AB 向 server 发并写入操作值，server 向 AB 同步对方的操作）

前端向后端传入操作，client12的线程会去写入server 线程的nextStep中，而server会去读取，并同步给 client12 的线程 ，然后传给前端（设计到锁的问题）

![image-20240808125505179](./SpringBoot 框架课.assets/image-20240808125505179.png)

game.java

```java
private ReentrantLock lock = new ReentrantLock(); 
public void setNextStepA(Integer nextStepA){
        lock.lock();
        try{
            this.nextStepA = nextStepA;
        } finally{//报不报异常都解锁，不会死锁
            lock.unlock();
        }
    }

    public void setNextStepB(Integer nextStepB){
        lock.lock();
        try{
            this.nextStepB = nextStepB;
        } finally{
            lock.unlock();
        }
    }
```





#### 2.4异常的执行顺序

[参考文档](https://blog.csdn.net/xingyu19911016/article/details/120487026)

- 如果 try 中没有异常，执行顺序为：try —> finally

- 如果 try 中有异常，执行顺序为：try —> catch —> finally

- 如果函数 a 是被函数 b 调用的：a 中抛出了异常，会在 a 执行 finally 后再返回，而不是 b 直接捕获

  - ```
    main(){
    	b();
    }
    a(){
    	try{
    		throw new Exception();
    	}
    	finally{}
    }
    
    b(){
    	try{
    		a();
    	} catch(Exception e){
    		
    	} finally{}
    }
    ```

  - 执行顺序b_try、a_try、a_finally、b_catch、b_finally

**含return 的语句**

- finally 中的代码总会执行
- 当 try、catch 中有 return 时，也会执行 finally（只有 try/catch 中有 return）
  - return 的时候，要注意返回值的类型，是否收到 finally 中代码的影响
    - 如果是 int 等基本类型，不会受 finally 中改变值的影响
      - 在从return跳转到前finally 前，会保存现场
      - 当try中带有return时，会先执行return前的代码，然后暂时保存需要return的信息，再执行finally中的代码，最后再通过return返回之前保存的信息。所以，这里方法返回的值是try中计算后的（而不是经过 finally 改变后的）
    - 如果是<List>等，会受 finally 中 add 的影响
      - 是引用类型，finally 中的改变会影响到
- try/catch和 finally 中都有 return 时
  - finally 中有 return 时，会直接在 finally 中退出，导致 try、catch 中的 return 失效

[参考文档](https://blog.csdn.net/Ronin_88/article/details/108239247)

```
try{}里有一个return语句，那么紧跟在这个try后的finally{}里的代码会不会被执行?什么时候被执行,在return前还是后?

1.若try{}中没有异常，则try{}中语句执行到return前一句，然后执行fianlly{}中的语句，最后回头执行try{}中的return语句。

2.若try{}中会产生异常，则在执行了会产生异常的语句之后就立马进入到catch{}语句块中，这时存在两种情况：

第一种情况:如果catch{}中没有return语句，则在执行完catch{}中的代码之后就执行fianlly{}中的语句，最后直接结束程序，并不会回到try{}中执行它的return语句。 

第二种情况:

如果catch{}中含有return语句，则在执行完catch{}中的return的前一语句时直接进入fianlly{}中执行代码，最后回头执行catch{}中的return语句，而不是执行try{}中的return语句。

3.若try{}/catch{}中有return，同时 finally中也有 return
会执行 try/catch 中的 return 之前就跳转到执行 finally，然后 finally 中直接return
```





#### 2.5前后端逻辑调试

gamemap.js

```javascript
    add_listening_events(){//获取用户输入信息
        this.ctx.canvas.focus();//canvas 聚焦，以获取用户输入信息

        const [snake0, snake1] = this.snakes;
        this.ctx.canvas.addEventListener("keydown", e => {//获取用户输入信息
            let d = -1;
            if(e.key === 'w') d = 0;
            else if(e.key === 'd') d = 1;
            else if(e.key === 's') d = 2;
            else if(e.key === 'a') d = 3;
            
            if(d >= 0){
                this.store.state.pk.socket.send(JSON.stringify({
                    event: "move",
                    direction: d,
                }))
            }
        });
    }
```



Player.java：蛇身体和长度的增加&获取蛇目前的身体的位置

```java
package com.kob.backend.Consumer.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {//记录每个玩家的位置信息
    private Integer id;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps;//记录玩家的移动序列（每回合走动的方向）


    private boolean check_tail_increasing(int steps){
        if(steps <= 10) return true;
        return steps % 3 == 1;
    }

    public List<Cell> getCells(){ //获取蛇当前身体的位置
        List<Cell> res = new ArrayList<>();
        //正常坐标int dx[] = {0, 1, 0, -1}, dy[] = {-1, 0, 1, 0};
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};//行列左边：上右下左
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for(int d: steps){
            x += dr[d];
            y += dc[d];
            res.add(new Cell(x, y));
            if(!check_tail_increasing(++ step)) //如果不是蛇的长度增加，那么蛇尾的位置就删除
                res.remove(0);
        }
        return res;
    }
}

```

Game.java判断逻辑移到后端

```java
    private boolean check_valid(List<Cell> cellsA, List<Cell> cellsB){//检测A蛇的移动是否合法
        int n = cellsA.size();
        Cell newCell = cellsA.get(n - 1);

        if(g[newCell.x][newCell.y] == 1) return false;//撞墙
        //撞自己
        for(int i = 0; i < n - 1; ++i){//cellA[n-1]处是新的蛇头
            if(newCell.x == cellsA.get(i).x && newCell.y == cellsA.get(i).y)
                return false;
        }
        //撞对手
        for(int i = 0; i < n - 1; ++i){
            if(newCell.x == cellsB.get(i).x && newCell.y == cellsB.get(i).y)
                return false;
        }

        //往回走
        return true;
    }

    private void judge(){//判断两名玩家下一步操作是否合法
        //先取出两条蛇的身体
        List<Cell> cellsA = playerA.getCells();
        List<Cell> cellsB = playerB.getCells();
        boolean valid_a = check_valid(cellsA, cellsB), valid_b = check_valid(cellsB, cellsA);

        if(!valid_a || !valid_b){
            status = "finished";
            if(!valid_a && !valid_b){
                loser = "all";
            }else if(!valid_a){
                loser = "A";
            }else{
                loser = "B";
            }
        }
    }

    private void sendAlLMessage(String message){//向两个client传递信息时调用的
        WebSocketServer.users.get(playerA.getId()).sendMessage(message);
        WebSocketServer.users.get(playerB.getId()).sendMessage(message);
    }

    private void sendMove(){//向两个 client 传递移动信息
        lock.lock();
        try{
            JSONObject resp = new JSONObject();
            resp.put("event", "move");
            resp.put("a_direction", nextStepA);
            resp.put("b_direction", nextStepB);
            sendAlLMessage(resp.toJSONString());
            nextStepA = nextStepB = null;//读取后清空操作
        } finally{
            lock.unlock();
        }

    }

    private void sendResult(){//向两个Client公布结果
        JSONObject resp = new JSONObject();
        resp.put("event", "result");
        resp.put("loser", loser);
        sendAlLMessage(resp.toJSONString());
        System.out.println(loser);
    }
    @Override
    public void run() {
        //蛇前10步每移1格增加1个身体长度，后面每3格增加1个身体长度，地图 13x14=182格，蛇最长长度为182
        //182x3 = 546，最多600次就可以结束
        for(int i = 0; i < 1000; ++i){
            if(nextStep()){//如果两条蛇的操作都获取到了
                judge();//判断两名玩家下一步操作是否合法
                if("playing".equals(status)){
                    sendMove();//向两个 client 同步移动信息（然后继续下一回合）
                }else{
                    sendResult();//向两个 client 公布对战结果
                    break;//游戏结束了
                }
            }else{//如果任意一条蛇的操作没获取到
                status = "finished";
                lock.lock();
                try{
                    if(nextStepA == null && nextStepB == null){//平局
                        loser = "all";
                    }else if (nextStepA == null){
                        loser = "A";
                    }else if(nextStepB == null){
                        loser = "B";
                    }else{//如果在if判断之后，加锁之前，a&b都刚好发出了结束信息，增加了判断边界
                        loser = "all";
                    }
                } finally{
                    lock.unlock();
                }
                sendResult();
                break;
            }
        }
    }
}
```





### 3对局结果(重启按钮)



#### 3.1显示出来

首先需要把按钮显示到页面，然后导入 pk 页面 

```html
<template>
    <div class="result-board">


    </div>

</template>


<script>




</script>


<style scoped>
div.result-board{
    width: 30vh;
    height: 30vh;
    background-color: rgba(241, 206, 9, 0.5);
    position: absolute;
    left: 42%;
    top: 29%;
}


</style>
```



#### 3.2展示

pk.js全局变量

```
loser: "none",//all,A,B
updateLoser.....
```

Pk.vue中

```html
<ResultBoard v-if="$store.state.pk.loser != 'none'"> </ResultBoard>

//输了
store.commit("updateLoser", data.loser);
```





#### 3.3输赢展示

ResultBoard.vue注意类型的问题

```html
<div class="result-board-text" v-if="$store.state.pk.loser === 'none'">
    Draw
</div>
<div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' && $store.state.pk.a_id === parseInt($store.state.user.id)">
    Lose
</div>
<div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' && $store.state.pk.a_id !== parseInt($store.state.user.id)">
    Win
</div>
<div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' && $store.state.pk.b_id === parseInt($store.state.user.id)">
    Lose
</div>
<div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' && $store.state.pk.b_id !== parseInt($store.state.user.id)">
    Win
</div>
<div class="result-board-text" v-else>
    Draw
</div>
```



#### 3.4再来一局



```html
<div class="result-board-btn" @click="restart" style="text-align: center; padding-top: 25%;">
    <button type="button" class="btn btn-outline-light btn-lg rounded-pill" style="background-color: orange;">
    <i class="fas fa-sync-alt">再来!</i> 
    </button>
</div>

<script>
import { useStore } from 'vuex';

export default{
    setup(){
    const store = useStore();

    const restart = () =>{
        store.commit("updateLoser", "none");
        store.commit("updateStatus", "matching");
        store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
    }


    return{
        restart,
    };
}

}




</script>
```









### 4录像功能

数据库新建表单如下（注入b_sy不小心打成了b_xy）

<img src="./SpringBoot 框架课.assets/image-20240815060639360.png" alt="image-20240815060639360" style="zoom:50%;" /> 



pojo层（注意数据库中的下划线需要改为驼峰命名）

```java
package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @TableId(type = IdType.AUTO)//主键自增
    private Integer id;
    private Integer aId;
    private Integer aSx;
    private Integer aSy;
    private Integer bId;
    private Integer bSx;
    private Integer bSy;
    private String aSteps;
    private String bSteps;
    private String map;
    private String loser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createtime;
}
```

Mapper

```java
package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backend.pojo.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
}
```

WebSocketServer.java注入数据库

```java
public static RecordMapper recordMapper;

@Autowired
public void setRecordMapper(RecordMapper recordMapper){
    WebSocketServer.recordMapper = recordMapper;
}
```

先写一个转化steps的辅助函数player.java

```java
public String getStepsString(){//把list的 steps转为字符串
        StringBuilder res = new StringBuilder();
        for(int d: steps){
            res.append(d);
        }
        return res.toString();
    }
```

把 Map 转为 String的辅助函数Game.java

```java
private String getMapString(){//把地图信息转为一维字符串
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                res.append(g[i][j]);
            }
        }
        return res.toString();
    }
```



接下来需要在对局结束的时候存入数据库

```java
private void saveToDatabase() {//保存对局记录到数据库
    Record record = new Record(
            null,//id数据库默认会填
            playerA.getId(),
            playerA.getSx(),
            playerA.getSy(),
            playerB.getId(),
            playerB.getSx(),
            playerB.getSy(),
            playerA.getStepsString(),
            playerB.getStepsString(),
            getMapString(),
            loser,
            new Date()
    );
    WebSocketServer.recordMapper.insert(record);
}
```

调用

```java
private void sendResult(){//向两个Client公布结果
        JSONObject resp = new JSONObject();
        resp.put("event", "result");
        resp.put("loser", loser);
        saveToDatabase();
        sendAlLMessage(resp.toJSONString());
        System.out.println("loser is " + loser);
    }
```



## 总结

- 到目前为止实现了：
  - client 和ws 后端的建立链接（简单匹配池模拟匹配系统）
  - 新线程开启游戏
  - 游戏判断逻辑（bot 执行代码部分尚未实现）
- 需要实现的：
  - 匹配系统的微服务
    - 用于实现多玩家匹配游戏时的匹配逻辑（尽量短时间&尽量 rank 分接近的匹配到一起）
  - bot 执行代码返回信息的微服务（玩家可以生成自己的 bot 代码，用来进行游戏）
- 接下来的一个标题实现：
  - 匹配系统的微服务



**下图是目前为止实现逻辑的总结**

 ![image-20240816031523962](./SpringBoot 框架课.assets/image-20240816031523962.png)







## 6.3 微服务实现（匹配系统）

用 spring cloud 实现 maching server的微服务

maching server （匹配系统后端）和 web server （网站后端）之间的通讯用 http







### 1微服务的理解

- 一个独立的程序处理一个复杂 or 独立的逻辑
  - 在原来的 springboot server 和 ws server 的基础上
  - 又起了一个新的springboot server 和 ws server用于专门处理
    - 玩家对战匹配的逻辑
- 命名：方便后面称呼
  - 原来的springboot server 和 ws server命名为web（网站后端）
  - 匹配系统的springboot server 和 ws server命名为matching（匹配系统后端）
- 逻辑：
  - web server 获取client 匹配请求后
    - 会转发给web ws server，并建立 ws 链接
  - 会向maching server发送http 请求
    - maching server 会开一个单独的Matching线程，进行一个匹配
  - Matching 线程逻辑：
    - 每隔 1s 扫描一次，当前的玩家，是否能匹配成功
    - 如果可以匹配成功则向web server返回信息（web ws server 会返回给 client）
      - 返回信息也是用 http
- 实现：
  - 微服务的具体实现用 spring cloud
    - 但是因为并发量不大：网关、负载均衡、调用等复杂逻辑用不到

![image-20240816033444393](./SpringBoot 框架课.assets/image-20240816033444393.png)





### 2 项目结构



- 创建一个新的项目，装两个子项目
  - web server（网站后端）spring boot +ws
  - maching server（匹配系统后端）spring cloud

<img src="./SpringBoot 框架课.assets/image-20240816033651956.png" alt="image-20240816033651956" style="zoom:33%;" /> 



#### 创建项目和依赖

新建项目（注意类型选 Maven，下图忘改了）

<img src="./SpringBoot 框架课.assets/image-20240816035351372.png" alt="image-20240816035351372" style="zoom: 33%;" /> 

添加依赖

<img src="./SpringBoot 框架课.assets/image-20240816035441517.png" alt="image-20240816035441517" style="zoom:33%;" /> 

删除 src 文件夹（父项目没有逻辑）



pom.xml中

```
<packaging>pom</packaging>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>2023.0.3</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>


//改
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

//为
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```

<img src="./SpringBoot 框架课.assets/image-20240816040426657.png" alt="image-20240816040426657" style="zoom:33%;" /> 



#### 创建子项目



spirngbackend 中

![image-20240816040630107](./SpringBoot 框架课.assets/image-20240816040630107.png)

创建matchingsystem匹配系统模块如图

<img src="./SpringBoot 框架课.assets/image-20240816040912600.png" alt="image-20240816040912600" style="zoom: 33%;" /> 



matchingsystem本质也是一个 springboot

在父级目录的 pom.xml中**剪切** spring web 的依赖到 matchingsystem 的 pom 中

```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```



#### 新项目分配端口

新的spring后端需要分配新的端口



新建文件application.properties

(web端口是 3002，这里设置 3001)

<img src="./SpringBoot 框架课.assets/image-20240816042101735.png" alt="image-20240816042101735" style="zoom:25%;" /> 

文件中写上

```
server.port=3001
```



#### 项目架构

新建软件包

<img src="./SpringBoot 框架课.assets/image-20240816042555473.png" alt="image-20240816042555473" style="zoom:25%;" /> 



#### 添加 spring 入口

修改Main.java为MatchingSystemApplication，并加上 spring 注解

```java
package com.kob.matchingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatchingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatchingSystemApplication.class, args);
    }
}
```



#### 导入之前项目

新建模块，导入之前 web 后端的项目(backendcloud->新建->模块)

<img src="./SpringBoot 框架课.assets/image-20240817024907852.png" alt="image-20240817024907852" style="zoom:33%;" /> 

1删除新项目中的 src、复制旧项目中的 src，backend->右键->粘贴

2 复制旧项目中 pom.xml中的 dependencise部分到新建项目的 pom 末尾（thymeleaf没用过，可以删了）







### 3定义和实现接口

Service.MatchingService定义增加和删除的两个方法

```java
package com.kob.matchingsystem.service;

public interface MatchingService {
    String addPlayer(Integer userId, Integer rating);
    String removePlayer(Integer userId);
}
```

Service.Impl.MatchingServiceImpl实现接口

```java
package com.kob.matchingsystem.service.impl;

import com.kob.matchingsystem.service.MatchingService;
import org.springframework.stereotype.Service;

@Service
public class MatchingServiceImpl implements MatchingService {
    @Override
    public String addPlayer(Integer userId, Integer rating) {
        System.out.println("add player" + userId + " " + rating);
        return "add player success";
    }

    @Override
    public String removePlayer(Integer userId) {
        System.out.println("remove player" + userId);
        return "remove player success";
    }
}
```

定义controller

```java
package com.kob.matchingsystem.controller;

import com.kob.matchingsystem.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MatchingController {
    @Autowired
    private MatchingService matchingService;

    @PostMapping("/player/add/")
    public String addPlayer(@RequestParam MultiValueMap<String, String> data){
        //正常 Map 是一个 key 对应一个 value，MultiValueMap 是一个 key 对应多个 value(一个 value list)
        //这里测试过，只能用MultiValueMap，否则会报错
        Integer userId = Integer.parseInt(Objects.requireNonNull(data.getFirst("userId")));//Objects.requireNonNull是确保不会返回null
        Integer rating = Integer.parseInt(Objects.requireNonNull(data.getFirst("rating")));
        return matchingService.addPlayer(userId, rating);
    }

    @PostMapping("/player/remove/")
    public String removePlayer(@RequestParam MultiValueMap<String, String> data){
        Integer userId = Integer.parseInt(Objects.requireNonNull(data.getFirst("userId")));
        return matchingService.removePlayer(userId);
    }
}

```





### 4权限控制

要求只能 web server的 http 请求访问matching server，不能其他访问，防止对matching server的伪造攻击

（Spring security 的**权限控制**）

spring cloud . Pom.xml中

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
            <version>3.3.1</version>
</dependency>
```



Matchingsystem.config.SecurityConfig

```java
package com.kob.matchingsystem.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends  WebSecurityConfigurerAdapter{
    @Autowired
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/player/add/").hasIpAddress("127.0.0.1")//公开链接位置，在这里加入即可
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
    }
}
```



### pom 文件

spring 版本过高的话，spring security 中的某个组件会报错（在高版本已经废弃）

故修改两个 pom.xml文件如下



pom.xml(backendcloud)外层的 pom

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.kob</groupId>
    <artifactId>backendcloud</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>backendcloud</name>
    <description>backendcloud</description>
    <modules>
        <module>matchingsystem</module>
    </modules>
    <packaging>pom</packaging>


    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>2.7.6</spring-boot.version>
    </properties>




    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>2021.0.3</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring-boot.version}</version>
                <configuration>
                    <mainClass>com.kob.backendcloud.BackendcloudApplication</mainClass>
                    <skip>true</skip>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

pom.xml(matchingsystem)内层的匹配系统的 pom

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.kob</groupId>
        <artifactId>backendcloud</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>

    <groupId>com.kob.matchingsystem</groupId>
    <artifactId>matchingsystem</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
            <version>2.7.1</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>


</project>
```



### 状态码

```
400：错误的请求（前端拼接参数有问题 or 参数类型不对：前端传 String 后端接受是 Integer...）
401：未授权（要求登录的页面，未登录就访问or 验证信息出错，服务器无法识别你的身份）
403：禁止访问（服务器理解了请求，但是拒绝处理）eg：url 在spring security 控制中没放行or不是放行ip
404：找不到文件（也有可能是后端配置问题，导致请求被拦截）
405：方法不允许（应该 post，使用了 get）
500：服务器内部错误
```





## 6.3匹配系统实现

微服务：匹配系统的实现（和上述的 6.3连在一起，因为比较重要，单开一个标题）



项目结构：

- backendcloud
  - backend（web 后端）
  - matchingsystem（匹配系统——微服务）
    - 后端，用的 spring 

 

### 1 后端请求交互

使用RestTemplate发送 http 请求进行backend和 matchingsysytem 后端之间的交互

```java
// backend.config.RestTemplateConfig.java

// RestTemplate用于发送http请求（可以在两个服务器间通讯）
@Configuration
public class RestTemplateConfig {
    @Bean//被Autowired注入的，会去找一个bean绑定的唯一函数
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

// backend.consumer.utils.WebSocketServer.java

// 注入RestTemplate(多线程的特殊注入方式)
private static RestTemplate restTemplate;
@Autowired  // 注意用@Autowired，需要在类前加@Component
private void setRestTemplate(RestTemplate restTemplate){
  WebsocketServer.restTemplate = restTemplate;
}

```

向后端发请求需要使用MultiValueMap

```java
//暂时用于服务器通信的常量
private final static String addPlayerUrl = "http://127.0.0.1:3001/player/add/";
private final static String removePlayerUrl = "http://127.0.0.1:3001/player/remove/";

private void startMatching(){//开启匹配，向匹配系统发请求开始匹配(add)
        System.out.println(this.user.getUsername() + " start matching!");
        //向匹配系统（微服务）的后端发请求：向匹配池加入一个玩家
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        data.add("rating", this.user.getRating().toString());
        restTemplate.postForObject(addPlayerUrl, data, String.class);//第三个参数是期望返回的格式，String.class标识返回的是字符串
    }

    private void stopMatching(){//停止匹配，向匹配系统发请求取消匹配（remove）
        System.out.println(this.user.getUsername() + " stop matching");
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        restTemplate.postForObject(removePlayerUrl, data, String.class);
    }
```

把数据库bot 中的 rating 换绑到 user 中

```
修改数据库表项、修改对应 pojo 层、修改 service 层（impl）
```



### 2匹配系统的逻辑

- 用一个线程，周期性每s轮询一次匹配池，是否有符合要求的玩家可以匹配
  - 符合要求：rating分差小于10 * 等待时间
  - 需要注意线程锁的问题
  - 还有remove/add player 访问匹配池（players），也需要加锁
- MatchingPool类
  - run：线程运行（特别注意锁的问题）
    - 死循环，每 sleep 1s，去执行函数
      - 1 increaseWaitingTime等待时间+1s
      - 2 matchPlayers查询有没有玩家可以匹配
  - matchPlayers函数
    - 遍历匹配玩家（需要考虑，等待时间长的优先匹配）
    - 匹配成功需要标记、且向 web 后端发送信息
  - sendResult函数（注意，这里 web 后端还需要实现接受信息的类）
    - 用restTemplate向 web 后端发匹配成功的信息
  - matchPlayer判断两名玩家是否匹配
    - 这里的逻辑是：两名玩家等待时间都满足分差才匹配，所以取min(waitingtime)
  - increasingWaitingTime
    - 等待时间+1s





### 3web 后端接受匹配成功

在matchingsystem匹配成功后，需要通过 sendResult 函数向 web后端发送匹配成功的信息——且 web 后端为两名对应的玩家开启游戏

在backend的 pk中实现，对应的service、impl、controller

- 注意：
  - 需要调用WebsockeServer.java中的startGame，这个函数需要改为public static
  - controller 中的链接需要加到SecurityConfig中
  - matchingsystem对应发送信息也需要：定义RestTemplate
    - 且注入进来（注入需要加@component）





### 4匹配的鲁棒性

#### 4.1链接断开but 匹配池还有玩家

如果链接断开，但是玩家还是在匹配池中：

- 如果匹配成功，matchingsystem会向web backend发送匹配成功开启游戏的请求
  - web backend在获取玩家信息的时候，因为 ws 链接断开了，所以没有玩家信息了
    - 这个时候获取玩家信息会报异常
    - 所以在所有users.get...前面加上判空操作（可以全局搜索）

```java
if(users.get(a.getId()) != null)
            users.get(a.getId()).game = game;//获取 a 的 ws 链接，再由链接获game实例
```





#### 4.2链接断开，又瞬间链上

如果链接断开，但是玩家还是在匹配池中、但是又马上链接上——刷新浏览器

- 匹配池中的玩家是不变的，断开链接后，马上建立一个新的链接
  - 所以不会为空，能够获取到 user 信息



- Websocket链接不是确保存活的（可以用心跳解决：周期性发送 keep-alive）
  - 如果关闭浏览器，可以使用信号传递，关闭链接
  - 但是如果断电、alt+f4之类的，没有传递信息，后端是不知道链接断开了的







## 6.4bot代码微服务

流程图

![image-20240826194507906](./SpringBoot 框架课.assets/image-20240826194507906.png)

自己总结的

![image-20240826210131796](./SpringBoot 框架课.assets/image-20240826210131796.png)

### 1 新建项目

新建模块botrunningsystem，导入matchingsystem 中的依赖



导入 java 代码编译的依赖，这里有坑，有版本问题，用下面的依赖joor-java-8

（后期需要运行多种代码，可以在服务器起 docker 运行）

```
<dependency>
            <groupId>org.jooq</groupId>
            <artifactId>joor-java-8</artifactId>
            <version>0.9.12</version>
</dependency>
```





### 2 接收信息

botrunningsystem需要接收到用户选择了 bot 出战的信息，然后运行 bot 代码

接收：userId、botCode、input（棋盘局面）



把matchingsystem中的RestTemplate 和SecurityConfig 复制过来，记得改服务的端口号



### 3botid 的传递

**增加 botid 的传递：**之前的信息传递中，没有 botid，先实现前端的交互



- 在前端 MatchingGround 中增加一个选择人出战还是 bot 出战的选择框（bootstrap-表单-select）

  - 增加信息的双向绑定
  - 点击开启匹配按钮后，需要传递信息到后端

  

  

**增加后端信息的传递**

- 后端需要接受开始匹配后传的 botid

  - Client传给 web 后端
    - backend-websocketserver-startMatching
  - web 后端传给匹配系统后端
    - matchingsystem的controller、service、serviceimpl
  - 匹配系统后端需要存下来 botid
    - MatchingPool-addPlayer
    - 存到 Player 中，需要在其中加上属性
  - 匹配系统后端传：匹配成功结果——给web 后端
    - MatchingPool-sendResult
  - web 后端接受也需要加上 botid
    - controller-pk-StarGamecontroller(service、impl)
    - 这里调用了 startGame：WebsocketServer-startGame

  

  

  到此：client——web 后端——匹配系统后端——匹配系统的匹配池——返回结果到 web 后端——web 后端开启游戏——现在需要 web 后端根据 botid 取出 bot 的代码

  

- 取出bot 代码：web 后端——websocketserver：注入 botMapper，并取出相应代码
  - 需要传到 Game 中：
    - Game和 Player 类中都需要增加userid参数
  - 从注入的 botMapper 中先取出 bot，再取出 bot 代码
    - 需要判空操作（因为如果是亲自出马，botid 就是-1）
  - 根据 botid 判断是人出马还是 bot 出马：
    - Game-nextStep-创建sendBotCode
  - sendBotCode 需要把 bot 代码传送：userid、botcode、input到 BotRunningSystem
- 传送的时候 input 需要被编码为字符串
  - 编码格式：地图#meSx#meSy#(me 操作序列)#opSx#opSy#(op 操作序列



到此：信息已成功传到 BotRunningSystem 微服务中

### 4 执行 Bot 代码（生产-消费者）

一个线程不断从队头取出代码，执行后放到队尾（如果有新的需要执行的代码也会放到队尾）

都是在 BotRunningSystem 中



**实现消费者线程：BotPool**

需要在中手动实现一个消息队列





**队列：Bot 类**

存 userid、botCode、input



**向队列加入元素的接口：**

写在 BotPool 中，应该在收到消息时调用BotRunningServiceImpl（还需要在这里动态开一个线程 BotPool）



**从队列中取出元素并执行代码的接口：**

- BotPool 中实现 consume

  

**同时在其中应该调用一个新的线程 Consumer：**控制每段代码的执行时间

在 Consumer 中的 run应该动态编译每段前端传过来的代码utils-BotInterface、且在 Bot 中实现接口

Reflect.comlile动态编译代码——create 创建代码类的实力——get 获取——随后赋值给了 botInterface

![image-20240826013206889](./SpringBoot 框架课.assets/image-20240826013206889.png)



### 5 动态编译代码

RunningSystem 需要接受Bot 的代码动态编译执行，把结果返回给 web 后端服务器（backend）

最后由backend 传送结果给 Game中的 nextStep，然后成功同步到前端

#### 5.1接收并编译代码



因为每个类只会编译一次，所以编译代码类的后面应该加上随机字符串uid



注意，这里需要在文件名的后面加上uid，也需要在定义的类名后面加上 uid（因为文件名和类名都是 bot）

如下图的两个地方

![image-20240826013706172](./SpringBoot 框架课.assets/image-20240826013706172.png)





Consumer 类的内容

```java
package com.kob.botrunningsystem.service.impl.utils;

import com.kob.botrunningsystem.utils.BotInterface;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class Consumer extends Thread{
    private Bot bot;
    private static RestTemplate restTemplate;
    private final static String receiveBotMoveUrl = "http://127.0.0.1:3002/pk/receive/bot/move/";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        Consumer.restTemplate = restTemplate;
    }

    public void startTimeOut(long timeout, Bot bot){
        this.bot = bot;
        this.start();  // 当前线程执行start后会开一个新的线程去执行run()函数，然后当前线程继续执行后面的代码

        try{                     // 当前线程继续执行到join,当前线程会阻塞timeout秒，再执行后面的操作
            this.join(timeout);  // 如果新开线程的run函数执行完毕，本线程会跳过阻塞，继续执行后面
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            this.interrupt();  //经过timeout秒，run如果还没执行完毕，就终结本线程
        }
    }

    private String addUid(String code, String uid){  // 在 code中的bot类名后面加上 uid
        int k = code.indexOf(" implements com.kob.botrunningsystem.utils.BotInterface");  // 获取插入位置
        return code.substring(0, k) + uid + code.substring(k);  // 在bot类名后面加上 uid
    }

    // run方法是线程执行的主体。当创建一个Thread对象并调用它的start方法时，JVM会在一个新的线程中执行这个对象的run方法
    @Override
    public void run() {
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString().substring(0, 8);  // 取前 8 位


        // botInterface获取了一个：编译java字符串代码的类的实例（相当于new一个类的实例：只不过这个类是字符串定义的）
        BotInterface botInterface = Reflect.compile(  // 编译代码的接口joor-java-8，接受文件名和代码内容两个参数
                "com.kob.botrunningsystem.utils.Bot" + uid,  // 重名类只会编译一次，（不同用户的bot代码不一样）所以类名后面需要加一个随机字符串
                addUid(bot.getBotCode(), uid)  // 传入了前端的 bot 代码，同时包名和类名都需要加上相同的 uid
        ).create().get();  // Reflect.compile编译传入的字符串形式 java 代码、并返回一个类，create和get创建并获取编译后的类的实例

        Integer direction = botInterface.nextMove(bot.getInput());
        System.out.println("move-direction: userid" + bot.getUserId() + " direction is" + direction);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", bot.getUserId().toString());
        data.add("direction", direction.toString());
        restTemplate.postForObject(receiveBotMoveUrl, data, String.class);
//        package com.kob.botrunningsystem.utils.Bot;  // 这里需要加上 uid
//
//        public class Bot implements com.kob.botrunningsystem.utils.BotInterface{  // 这里的 Bot 后面也需要加上 uid
//            @Override
//            public Integer nextMove(String input) {
//                return 0;
//            }
//        }
    }
}

```





#### 5.2 返回代码编译执行结果

在 Web 服务器中加上接受的 api：

```
Service-pk-ReceiveBotMoveService：接受 userid 和 direction
Service-impl-pkReceiveBotMoveImpl
Controller...
```

同时需要传给 Game，再传给 nextStep

（直接在ServiceImpl 中修改即可）



最后：在 BotRunningSystem 中的 Consumer 中用 RestTemplate 传递给刚才加上的 api 即可

![image-20240826194507906](./SpringBoot 框架课.assets/image-20240826194507906.png)



### 6蛇 ai 代码



蛇 ai 代码搜索一步：y 总

```java
package com.kob.botrunningsystem.utils;


import java.util.ArrayList;
import java.util.List;

// 这里用做编写 Bot的 ai 测试代码，不影响程序，编写后写入 bot 即可
public class Bot implements com.kob.botrunningsystem.utils.BotInterface{

    static class Cell{  // 蛇头坐标
        public int x, y;
        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private boolean check_tail_increasing(int steps){
        if(steps <= 10) return true;
        return steps % 3 == 1;
    }

    public List<Cell> getCells(int sx, int sy, String steps){ //获取蛇当前身体的位置，传入的steps是操作序列
        steps = steps.substring(1, steps.length() -1);  // 去掉编码中：玩家操作的的左右括号
        List<Cell> res = new ArrayList<>();
        //正常坐标int dx[] = {0, 1, 0, -1}, dy[] = {-1, 0, 1, 0};
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};//行列左边：上右下左
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for(int i = 0; i < steps.length(); ++i){
            int d = steps.charAt(i) - '0';
            x += dr[d];
            y += dc[d];
            res.add(new Cell(x, y));
            if(!check_tail_increasing(++ step)) //如果不是蛇的长度增加，那么蛇尾的位置就删除
                res.remove(0);
        }
        return res;
    }


    @Override
    public Integer nextMove(String input) {  // input是当前的地图信息（障碍物和蛇位置）
        String[] strs = input.split("#");  // 解码出来(地图、mesx,mesy,me操作,opsx,opsy,op操作)
        int[][] g = new int[13][14];
        for(int i = 0, k = 0; i < 13; ++i){  // 取出地图
            for(int j = 0; j < 14; ++j, ++k){
                if(strs[0].charAt(k) == '1'){
                    g[i][j] = 1;
                }
            }
        }

        // 计算身体位置
        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);
        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);

        List<Cell> aCells = getCells(aSx, aSy, strs[3]);
        List<Cell> bCells = getCells(bSx, bSy, strs[6]);

        // 把两条蛇身体也添加到障碍物中
        for(Cell c: aCells) g[c.x][c.y] = 1;
        for(Cell c: bCells) g[c.x][c.y] = 1;

        // 蛇下一步操作搜索
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};  // 行列
        for(int i = 0; i < 4; ++i){
            int x = aCells.get(aCells.size() - 1).x + dr[i];  // 蛇头的下一步可能的 x 坐标
            int y = aCells.get(aCells.size() - 1).y + dc[i];
            // 判断下一步是否合法
            if(x >= 0 && x < 13 && y >= 0 && y < 14 && g[x][y] == 0)
                return i;
        }

        return 0;
    }
}

```

ai蛇代码：搜索两步

```java
package com.kob.botrunningsystem.utils;


import java.util.ArrayList;
import java.util.List;

// 这里用做编写 Bot的 ai 测试代码，不影响程序，编写后写入 bot 即可
public class Bot implements com.kob.botrunningsystem.utils.BotInterface{

    static class Cell{  // 蛇头坐标
        public int x, y;
        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private boolean check_tail_increasing(int steps){
        if(steps <= 10) return true;
        return steps % 3 == 1;
    }

    public List<Cell> getCells(int sx, int sy, String steps){ //获取蛇当前身体的位置，传入的steps是操作序列
        steps = steps.substring(1, steps.length() -1);  // 去掉编码中：玩家操作的的左右括号
        List<Cell> res = new ArrayList<>();
        //正常坐标int dx[] = {0, 1, 0, -1}, dy[] = {-1, 0, 1, 0};
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};//行列左边：上右下左
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for(int i = 0; i < steps.length(); ++i){
            int d = steps.charAt(i) - '0';
            x += dr[d];
            y += dc[d];
            res.add(new Cell(x, y));
            if(!check_tail_increasing(++ step)) //如果不是蛇的长度增加，那么蛇尾的位置就删除
                res.remove(0);
        }
        return res;
    }


    @Override
    public Integer nextMove(String input) {  // input是当前的地图信息（障碍物和蛇位置）
        String[] strs = input.split("#");  // 解码出来(地图、mesx,mesy,me操作,opsx,opsy,op操作)
        int[][] g = new int[13][14];
        for(int i = 0, k = 0; i < 13; ++i){  // 取出地图
            for(int j = 0; j < 14; ++j, ++k){
                if(strs[0].charAt(k) == '1'){
                    g[i][j] = 1;
                }
            }
        }

        // 计算身体位置
        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);
        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);

        List<Cell> aCells = getCells(aSx, aSy, strs[3]);
        List<Cell> bCells = getCells(bSx, bSy, strs[6]);

        // 把两条蛇身体也添加到障碍物中
        for(Cell c: aCells) g[c.x][c.y] = 1;
        for(Cell c: bCells) g[c.x][c.y] = 1;

        // 蛇下一步操作搜索
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};  // 行列
        int aim_position[] = {0, 0, 0, 0};  // 记录下一步可走的位置，这个位置的下一步可以走的位置的数量(搜索两个位置)
        for(int i = 0; i < 4; ++i){
            int x = aCells.get(aCells.size() - 1).x + dr[i];  // 蛇头的下一步可能的 x 坐标
            int y = aCells.get(aCells.size() - 1).y + dc[i];
            // 判断下一步是否合法
            if(x >= 0 && x < 13 && y >= 0 && y < 14 && g[x][y] == 0){
                g[x][y] = 1;
                for(int j = 0; j < 4; ++j){
                    int xx = x + dr[j];  // 下下步可能的位置
                    int yy = y + dc[j];
                    if(xx >= 0 && xx < 13 && yy >= 0 && yy < 14 && g[xx][yy] == 0){
                        aim_position[i]++;
                    }
                }
                g[x][y] = 0;
            } else{
                aim_position[i] = -1;
            }
        }
        int max = 0;  // 可能位置的最大值
        int pos = 0;  // 最大值的位置
        for(int i = 0; i < 4; ++i){
            if(aim_position[i] > max){
                max = aim_position[i];
                pos = i;
            }
        }
        if(aim_position[pos] != -1)
            return pos;
        for(int i = 0; i < 4; ++i){
            if(aim_position[i] != -1)
                return i;
        }
        return 0;
    }
}

```











































50mins









![image-20240823215708294](./SpringBoot 框架课.assets/image-20240823215708294.png)



















































































































































































































































































































- 优化

  - 匹配成功后的聊天框
  - 注册成功后的跳转提示问题
  - 提示用户自己是哪条蛇 || 固定自己为左下角（坐标映射）
  - 删除 bot 增加提示：是否删除
  - 各个界面的 ui 优化
  - 项目简介页面
  - 地图创建的逻辑（笔记）
    - 连通性判断
  - 蛇运动的逻辑（笔记）
  - 登录、注册（选择密码可以显示和隐藏）
  - 好友对战功能（两个人可以进行多次对战）
  - 代码执行改进：目前bot 的 ai只支持 java 代码的编译和运行
    - 未来上线可以采用 docker 运行代码，可以支持多种语言
- 项目：自己写 oj
