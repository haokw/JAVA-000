学习笔记

提示了这个警告怎么解决？
```java
➜  Week_01 git:(main) ✗ javac -Xlint ReverseClassLoader.java
ReverseClassLoader.java:7: warning: [rawtypes] found raw type: Class
            Class Hello = new ReverseClassLoader().findClass("Hello");
            ^
  missing type arguments for generic class Class<T>
  where T is a type-variable:
    T extends Object declared in class Class
ReverseClassLoader.java:8: warning: [unchecked] unchecked call to getMethod(String,Class<?>...) as a member of the raw type Class
            Method hello = Hello.getMethod("hello");
                                          ^
2 warnings
```

变量声明修改 Class -> Class<?> 泛型
```java
            Class<?> Hello = new ReverseClassLoader().findClass("Hello");
```
