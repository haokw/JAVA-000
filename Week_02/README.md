[TOC]

# 学习笔记

### 周四

```
1、使用 GCLogAnalysis.java 自己演练一遍串行/并行/CMS/G1的案例。

2、使用压测工具（wrk或sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

3、(选做)如果自己本地有可以运行的项目，可以按照2的方式进行演练。

根据上述自己对于1和2的演示，写一段对于不同 GC 的总结，提交到 Github。
```

#### 解答

```
java -XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis

-XX:+UseSerialGC
-XX:+UseParallelGC
-XX:+UseConcMarkSweepGC
-XX:+UseG1GC

-Xms512m -Xmx512m
-Xms1g -Xmx1g
-Xms2g -Xmx2g
-Xms4g -Xmx4g

wrk -c40 -d30s  http://localhost:8088/api/hello
```

##### SerialGC

```sh
➜  Week_02 git:(main) ✗ java -XX:+UseSerialGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:18:29.761-0800: [GC (Allocation Failure) 2020-10-25T19:18:29.761-0800: [DefNew: 139776K->17472K(157248K), 0.0278202 secs] 139776K->49112K(506816K), 0.0278542 secs] [Times: user=0.01 sys=0.02, real=0.02 secs]
2020-10-25T19:18:29.810-0800: [GC (Allocation Failure) 2020-10-25T19:18:29.810-0800: [DefNew: 157248K->17471K(157248K), 0.0331021 secs] 188888K->89981K(506816K), 0.0331373 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
2020-10-25T19:18:29.868-0800: [GC (Allocation Failure) 2020-10-25T19:18:29.868-0800: [DefNew: 157247K->17471K(157248K), 0.0267586 secs] 229757K->134119K(506816K), 0.0267904 secs] [Times: user=0.01 sys=0.01, real=0.03 secs]
2020-10-25T19:18:29.917-0800: [GC (Allocation Failure) 2020-10-25T19:18:29.917-0800: [DefNew: 157183K->17471K(157248K), 0.0271959 secs] 273831K->177285K(506816K), 0.0272298 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
2020-10-25T19:18:29.964-0800: [GC (Allocation Failure) 2020-10-25T19:18:29.964-0800: [DefNew: 157157K->17471K(157248K), 0.0297092 secs] 316971K->223365K(506816K), 0.0297470 secs] [Times: user=0.02 sys=0.01, real=0.03 secs]
2020-10-25T19:18:30.015-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.015-0800: [DefNew: 157247K->17471K(157248K), 0.0292972 secs] 363141K->269692K(506816K), 0.0293314 secs] [Times: user=0.02 sys=0.02, real=0.03 secs]
2020-10-25T19:18:30.060-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.060-0800: [DefNew: 157247K->17468K(157248K), 0.0296739 secs] 409468K->316985K(506816K), 0.0297100 secs] [Times: user=0.01 sys=0.01, real=0.03 secs]
2020-10-25T19:18:30.109-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.109-0800: [DefNew: 157244K->157244K(157248K), 0.0000159 secs]2020-10-25T19:18:30.109-0800: [Tenured: 299516K->266623K(349568K), 0.0353967 secs] 456761K->266623K(506816K), [Metaspace: 2725K->2725K(1056768K)], 0.0354613 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2020-10-25T19:18:30.165-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.165-0800: [DefNew: 139776K->17471K(157248K), 0.0074848 secs] 406399K->315511K(506816K), 0.0075197 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
2020-10-25T19:18:30.195-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.195-0800: [DefNew: 157247K->17468K(157248K), 0.0252366 secs] 455287K->357088K(506816K), 0.0252700 secs] [Times: user=0.01 sys=0.01, real=0.03 secs]
2020-10-25T19:18:30.239-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.239-0800: [DefNew: 157170K->157170K(157248K), 0.0000172 secs]2020-10-25T19:18:30.239-0800: [Tenured: 339620K->307916K(349568K), 0.0437477 secs] 496790K->307916K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0438087 secs] [Times: user=0.04 sys=0.00, real=0.05 secs]
2020-10-25T19:18:30.302-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.302-0800: [DefNew: 139776K->139776K(157248K), 0.0000162 secs]2020-10-25T19:18:30.302-0800: [Tenured: 307916K->324051K(349568K), 0.0439772 secs] 447692K->324051K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0440371 secs] [Times: user=0.05 sys=0.00, real=0.04 secs]
2020-10-25T19:18:30.367-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.367-0800: [DefNew: 139776K->139776K(157248K), 0.0000165 secs]2020-10-25T19:18:30.367-0800: [Tenured: 324051K->307906K(349568K), 0.0501643 secs] 463827K->307906K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0502281 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
2020-10-25T19:18:30.440-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.440-0800: [DefNew: 139254K->17470K(157248K), 0.0095949 secs] 447161K->354766K(506816K), 0.0096608 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-25T19:18:30.467-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.467-0800: [DefNew: 157246K->157246K(157248K), 0.0000161 secs]2020-10-25T19:18:30.467-0800: [Tenured: 337295K->335557K(349568K), 0.0389061 secs] 494542K->335557K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0389656 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2020-10-25T19:18:30.529-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.529-0800: [DefNew: 139776K->139776K(157248K), 0.0000210 secs]2020-10-25T19:18:30.529-0800: [Tenured: 335557K->340280K(349568K), 0.0442731 secs] 475333K->340280K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0443560 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
2020-10-25T19:18:30.596-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.596-0800: [DefNew: 139776K->139776K(157248K), 0.0000162 secs]2020-10-25T19:18:30.596-0800: [Tenured: 340280K->347434K(349568K), 0.0504926 secs] 480056K->347434K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0505724 secs] [Times: user=0.05 sys=0.01, real=0.05 secs]
2020-10-25T19:18:30.669-0800: [GC (Allocation Failure) 2020-10-25T19:18:30.669-0800: [DefNew: 139654K->139654K(157248K), 0.0000169 secs]2020-10-25T19:18:30.669-0800: [Tenured: 347434K->327001K(349568K), 0.0554443 secs] 487089K->327001K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0555098 secs] [Times: user=0.05 sys=0.00, real=0.06 secs]
执行结束!共生成对象次数:9484
Heap
 def new generation   total 157248K, used 6287K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,   4% used [0x00000007a0000000, 0x00000007a0623e58, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
  to   space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
 tenured generation   total 349568K, used 327001K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 349568K,  93% used [0x00000007aaaa0000, 0x00000007be9f65e0, 0x00000007be9f6600, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseSerialGC -Xms1g -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:19:18.447-0800: [GC (Allocation Failure) 2020-10-25T19:19:18.447-0800: [DefNew: 279616K->34944K(314560K), 0.0469142 secs] 279616K->79035K(1013632K), 0.0469470 secs] [Times: user=0.03 sys=0.02, real=0.05 secs]
2020-10-25T19:19:18.538-0800: [GC (Allocation Failure) 2020-10-25T19:19:18.538-0800: [DefNew: 314560K->34943K(314560K), 0.0641711 secs] 358651K->157413K(1013632K), 0.0642046 secs] [Times: user=0.04 sys=0.03, real=0.07 secs]
2020-10-25T19:19:18.648-0800: [GC (Allocation Failure) 2020-10-25T19:19:18.648-0800: [DefNew: 314559K->34942K(314560K), 0.0427201 secs] 437029K->220422K(1013632K), 0.0427737 secs] [Times: user=0.03 sys=0.01, real=0.05 secs]
2020-10-25T19:19:18.729-0800: [GC (Allocation Failure) 2020-10-25T19:19:18.729-0800: [DefNew: 314558K->34943K(314560K), 0.0511264 secs] 500038K->301886K(1013632K), 0.0511763 secs] [Times: user=0.04 sys=0.02, real=0.06 secs]
2020-10-25T19:19:18.822-0800: [GC (Allocation Failure) 2020-10-25T19:19:18.822-0800: [DefNew: 314559K->34943K(314560K), 0.0484187 secs] 581502K->380186K(1013632K), 0.0484523 secs] [Times: user=0.03 sys=0.02, real=0.05 secs]
2020-10-25T19:19:18.910-0800: [GC (Allocation Failure) 2020-10-25T19:19:18.910-0800: [DefNew: 314559K->34944K(314560K), 0.0560155 secs] 659802K->465763K(1013632K), 0.0560757 secs] [Times: user=0.03 sys=0.03, real=0.05 secs]
2020-10-25T19:19:19.007-0800: [GC (Allocation Failure) 2020-10-25T19:19:19.007-0800: [DefNew: 314560K->34943K(314560K), 0.0630831 secs] 745379K->547984K(1013632K), 0.0631252 secs] [Times: user=0.03 sys=0.02, real=0.07 secs]
2020-10-25T19:19:19.109-0800: [GC (Allocation Failure) 2020-10-25T19:19:19.109-0800: [DefNew: 314559K->34941K(314560K), 0.0502991 secs] 827600K->627226K(1013632K), 0.0503324 secs] [Times: user=0.03 sys=0.02, real=0.05 secs]
2020-10-25T19:19:19.198-0800: [GC (Allocation Failure) 2020-10-25T19:19:19.198-0800: [DefNew: 314557K->34943K(314560K), 0.0485578 secs] 906842K->702385K(1013632K), 0.0485931 secs] [Times: user=0.03 sys=0.02, real=0.05 secs]
执行结束!共生成对象次数:10403
Heap
 def new generation   total 314560K, used 280901K [0x0000000780000000, 0x0000000795550000, 0x0000000795550000)
  eden space 279616K,  87% used [0x0000000780000000, 0x000000078f0315d8, 0x0000000791110000)
  from space 34944K,  99% used [0x0000000793330000, 0x000000079554fff0, 0x0000000795550000)
  to   space 34944K,   0% used [0x0000000791110000, 0x0000000791110000, 0x0000000793330000)
 tenured generation   total 699072K, used 667441K [0x0000000795550000, 0x00000007c0000000, 0x00000007c0000000)
   the space 699072K,  95% used [0x0000000795550000, 0x00000007be11c640, 0x00000007be11c800, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseSerialGC -Xms2g -Xmx2g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:19:47.235-0800: [GC (Allocation Failure) 2020-10-25T19:19:47.235-0800: [DefNew: 559232K->69888K(629120K), 0.0941191 secs] 559232K->159309K(2027264K), 0.0941514 secs] [Times: user=0.06 sys=0.04, real=0.09 secs]
2020-10-25T19:19:47.414-0800: [GC (Allocation Failure) 2020-10-25T19:19:47.414-0800: [DefNew: 629120K->69887K(629120K), 0.1141280 secs] 718541K->292424K(2027264K), 0.1141641 secs] [Times: user=0.06 sys=0.05, real=0.11 secs]
2020-10-25T19:19:47.604-0800: [GC (Allocation Failure) 2020-10-25T19:19:47.604-0800: [DefNew: 629119K->69888K(629120K), 0.0919221 secs] 851656K->424816K(2027264K), 0.0919555 secs] [Times: user=0.05 sys=0.03, real=0.09 secs]
2020-10-25T19:19:47.770-0800: [GC (Allocation Failure) 2020-10-25T19:19:47.770-0800: [DefNew: 629120K->69888K(629120K), 0.0914500 secs] 984048K->552278K(2027264K), 0.0914839 secs] [Times: user=0.06 sys=0.04, real=0.09 secs]
执行结束!共生成对象次数:10108
Heap
 def new generation   total 629120K, used 520394K [0x0000000740000000, 0x000000076aaa0000, 0x000000076aaa0000)
  eden space 559232K,  80% used [0x0000000740000000, 0x000000075b7f2bc8, 0x0000000762220000)
  from space 69888K, 100% used [0x0000000762220000, 0x0000000766660000, 0x0000000766660000)
  to   space 69888K,   0% used [0x0000000766660000, 0x0000000766660000, 0x000000076aaa0000)
 tenured generation   total 1398144K, used 482390K [0x000000076aaa0000, 0x00000007c0000000, 0x00000007c0000000)
   the space 1398144K,  34% used [0x000000076aaa0000, 0x00000007881b5960, 0x00000007881b5a00, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseSerialGC -Xms4g -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:20:23.466-0800: [GC (Allocation Failure) 2020-10-25T19:20:23.466-0800: [DefNew: 1118528K->139775K(1258304K), 0.1608902 secs] 1118528K->240602K(4054528K), 0.1609221 secs] [Times: user=0.09 sys=0.07, real=0.16 secs]
2020-10-25T19:20:23.812-0800: [GC (Allocation Failure) 2020-10-25T19:20:23.812-0800: [DefNew: 1258303K->139776K(1258304K), 0.2581176 secs] 1359130K->397367K(4054528K), 0.2581758 secs] [Times: user=0.11 sys=0.09, real=0.26 secs]
执行结束!共生成对象次数:8533
Heap
 def new generation   total 1258304K, used 184330K [0x00000006c0000000, 0x0000000715550000, 0x0000000715550000)
  eden space 1118528K,   3% used [0x00000006c0000000, 0x00000006c2b82880, 0x0000000704450000)
  from space 139776K, 100% used [0x0000000704450000, 0x000000070ccd0000, 0x000000070ccd0000)
  to   space 139776K,   0% used [0x000000070ccd0000, 0x000000070ccd0000, 0x0000000715550000)
 tenured generation   total 2796224K, used 257591K [0x0000000715550000, 0x00000007c0000000, 0x00000007c0000000)
   the space 2796224K,   9% used [0x0000000715550000, 0x00000007250ddff0, 0x00000007250de000, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
```

##### ParallelGC

```sh
➜  Week_02 git:(main) ✗ java -XX:+UseParallelGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:21:47.460-0800: [GC (Allocation Failure) [PSYoungGen: 131584K->21483K(153088K)] 131584K->38693K(502784K), 0.0179240 secs] [Times: user=0.03 sys=0.11, real=0.01 secs]
2020-10-25T19:21:47.501-0800: [GC (Allocation Failure) [PSYoungGen: 153067K->21503K(153088K)] 170277K->76039K(502784K), 0.0281436 secs] [Times: user=0.03 sys=0.18, real=0.02 secs]
2020-10-25T19:21:47.549-0800: [GC (Allocation Failure) [PSYoungGen: 152996K->21491K(153088K)] 207531K->116874K(502784K), 0.0223600 secs] [Times: user=0.04 sys=0.11, real=0.03 secs]
2020-10-25T19:21:47.593-0800: [GC (Allocation Failure) [PSYoungGen: 152943K->21496K(153088K)] 248325K->157085K(502784K), 0.0212029 secs] [Times: user=0.05 sys=0.12, real=0.02 secs]
2020-10-25T19:21:47.634-0800: [GC (Allocation Failure) [PSYoungGen: 152949K->21499K(153088K)] 288539K->195546K(502784K), 0.0218996 secs] [Times: user=0.04 sys=0.12, real=0.02 secs]
2020-10-25T19:21:47.679-0800: [GC (Allocation Failure) [PSYoungGen: 153081K->21497K(80384K)] 327128K->234197K(430080K), 0.0220331 secs] [Times: user=0.04 sys=0.12, real=0.03 secs]
2020-10-25T19:21:47.708-0800: [GC (Allocation Failure) [PSYoungGen: 80376K->34290K(116736K)] 293076K->251520K(466432K), 0.0054608 secs] [Times: user=0.03 sys=0.01, real=0.01 secs]
2020-10-25T19:21:47.725-0800: [GC (Allocation Failure) [PSYoungGen: 92932K->45623K(116736K)] 310161K->268507K(466432K), 0.0075124 secs] [Times: user=0.04 sys=0.02, real=0.01 secs]
2020-10-25T19:21:47.741-0800: [GC (Allocation Failure) [PSYoungGen: 104503K->57241K(116736K)] 327387K->287725K(466432K), 0.0098196 secs] [Times: user=0.06 sys=0.02, real=0.01 secs]
2020-10-25T19:21:47.761-0800: [GC (Allocation Failure) [PSYoungGen: 116024K->39672K(116736K)] 346508K->303471K(466432K), 0.0176885 secs] [Times: user=0.03 sys=0.10, real=0.01 secs]
2020-10-25T19:21:47.787-0800: [GC (Allocation Failure) [PSYoungGen: 98416K->23225K(116736K)] 362214K->322841K(466432K), 0.0182919 secs] [Times: user=0.02 sys=0.11, real=0.02 secs]
2020-10-25T19:21:47.805-0800: [Full GC (Ergonomics) [PSYoungGen: 23225K->0K(116736K)] [ParOldGen: 299615K->237826K(349696K)] 322841K->237826K(466432K), [Metaspace: 2726K->2726K(1056768K)], 0.0322709 secs] [Times: user=0.25 sys=0.01, real=0.03 secs]
2020-10-25T19:21:47.849-0800: [GC (Allocation Failure) [PSYoungGen: 58805K->20562K(116736K)] 296631K->258389K(466432K), 0.0029171 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-25T19:21:47.859-0800: [GC (Allocation Failure) [PSYoungGen: 79319K->19399K(116736K)] 317145K->276528K(466432K), 0.0046580 secs] [Times: user=0.04 sys=0.00, real=0.01 secs]
2020-10-25T19:21:47.874-0800: [GC (Allocation Failure) [PSYoungGen: 78274K->18589K(116736K)] 335402K->294755K(466432K), 0.0046529 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
2020-10-25T19:21:47.890-0800: [GC (Allocation Failure) [PSYoungGen: 77184K->23775K(116736K)] 353350K->317967K(466432K), 0.0068335 secs] [Times: user=0.06 sys=0.00, real=0.00 secs]
2020-10-25T19:21:47.910-0800: [GC (Allocation Failure) [PSYoungGen: 82655K->19661K(116736K)] 376847K->336673K(466432K), 0.0124564 secs] [Times: user=0.03 sys=0.06, real=0.01 secs]
2020-10-25T19:21:47.923-0800: [Full GC (Ergonomics) [PSYoungGen: 19661K->0K(116736K)] [ParOldGen: 317012K->272912K(349696K)] 336673K->272912K(466432K), [Metaspace: 2726K->2726K(1056768K)], 0.0330562 secs] [Times: user=0.26 sys=0.01, real=0.03 secs]
2020-10-25T19:21:47.967-0800: [GC (Allocation Failure) [PSYoungGen: 58446K->20145K(116736K)] 331358K->293057K(466432K), 0.0027789 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2020-10-25T19:21:47.980-0800: [GC (Allocation Failure) [PSYoungGen: 79025K->19143K(116736K)] 351937K->310684K(466432K), 0.0046275 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
2020-10-25T19:21:47.995-0800: [GC (Allocation Failure) [PSYoungGen: 78023K->23247K(116736K)] 369564K->332540K(466432K), 0.0045849 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
2020-10-25T19:21:47.999-0800: [Full GC (Ergonomics) [PSYoungGen: 23247K->0K(116736K)] [ParOldGen: 309292K->292833K(349696K)] 332540K->292833K(466432K), [Metaspace: 2726K->2726K(1056768K)], 0.0352551 secs] [Times: user=0.27 sys=0.01, real=0.04 secs]
2020-10-25T19:21:48.057-0800: [GC (Allocation Failure) [PSYoungGen: 58880K->18089K(116736K)] 351713K->310922K(466432K), 0.0025023 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2020-10-25T19:21:48.073-0800: [GC (Allocation Failure) [PSYoungGen: 76817K->22800K(116736K)] 369650K->333185K(466432K), 0.0054599 secs] [Times: user=0.05 sys=0.00, real=0.00 secs]
2020-10-25T19:21:48.089-0800: [GC (Allocation Failure) [PSYoungGen: 81680K->20556K(116736K)] 392065K->352474K(466432K), 0.0114534 secs] [Times: user=0.03 sys=0.05, real=0.02 secs]
2020-10-25T19:21:48.101-0800: [Full GC (Ergonomics) [PSYoungGen: 20556K->0K(116736K)] [ParOldGen: 331918K->299391K(349696K)] 352474K->299391K(466432K), [Metaspace: 2726K->2726K(1056768K)], 0.0408135 secs] [Times: user=0.32 sys=0.01, real=0.04 secs]
2020-10-25T19:21:48.155-0800: [GC (Allocation Failure) [PSYoungGen: 58834K->22263K(116736K)] 358225K->321655K(466432K), 0.0031096 secs] [Times: user=0.03 sys=0.00, real=0.00 secs]
2020-10-25T19:21:48.171-0800: [GC (Allocation Failure) [PSYoungGen: 81143K->18327K(116736K)] 380535K->339101K(466432K), 0.0051837 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
2020-10-25T19:21:48.176-0800: [Full GC (Ergonomics) [PSYoungGen: 18327K->0K(116736K)] [ParOldGen: 320773K->302089K(349696K)] 339101K->302089K(466432K), [Metaspace: 2726K->2726K(1056768K)], 0.0362246 secs] [Times: user=0.25 sys=0.01, real=0.04 secs]
2020-10-25T19:21:48.228-0800: [GC (Allocation Failure) [PSYoungGen: 58880K->20173K(116736K)] 360969K->322263K(466432K), 0.0025967 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
2020-10-25T19:21:48.240-0800: [GC (Allocation Failure) [PSYoungGen: 79053K->19616K(116736K)] 381143K->341273K(466432K), 0.0053724 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
2020-10-25T19:21:48.245-0800: [Full GC (Ergonomics) [PSYoungGen: 19616K->0K(116736K)] [ParOldGen: 321657K->312300K(349696K)] 341273K->312300K(466432K), [Metaspace: 2726K->2726K(1056768K)], 0.0375855 secs] [Times: user=0.31 sys=0.01, real=0.04 secs]
2020-10-25T19:21:48.298-0800: [GC (Allocation Failure) [PSYoungGen: 58584K->20397K(116736K)] 370884K->332697K(466432K), 0.0034199 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]
2020-10-25T19:21:48.313-0800: [GC (Allocation Failure) [PSYoungGen: 79277K->39086K(115712K)] 391577K->351386K(465408K), 0.0054639 secs] [Times: user=0.04 sys=0.00, real=0.00 secs]
2020-10-25T19:21:48.330-0800: [GC (Allocation Failure) --[PSYoungGen: 97966K->97966K(115712K)] 410266K->447465K(465408K), 0.0175855 secs] [Times: user=0.06 sys=0.05, real=0.01 secs]
2020-10-25T19:21:48.348-0800: [Full GC (Ergonomics) [PSYoungGen: 97966K->0K(115712K)] [ParOldGen: 349498K->327842K(349696K)] 447465K->327842K(465408K), [Metaspace: 2726K->2726K(1056768K)], 0.0413802 secs] [Times: user=0.33 sys=0.00, real=0.04 secs]
执行结束!共生成对象次数:8071
Heap
 PSYoungGen      total 115712K, used 2563K [0x00000007b5580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 58880K, 4% used [0x00000007b5580000,0x00000007b5800f38,0x00000007b8f00000)
  from space 56832K, 0% used [0x00000007bc880000,0x00000007bc880000,0x00000007c0000000)
  to   space 57856K, 0% used [0x00000007b8f00000,0x00000007b8f00000,0x00000007bc780000)
 ParOldGen       total 349696K, used 327842K [0x00000007a0000000, 0x00000007b5580000, 0x00000007b5580000)
  object space 349696K, 93% used [0x00000007a0000000,0x00000007b4028930,0x00000007b5580000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseParallelGC -Xms1g -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:22:07.214-0800: [GC (Allocation Failure) [PSYoungGen: 262144K->43517K(305664K)] 262144K->86814K(1005056K), 0.0402374 secs] [Times: user=0.05 sys=0.25, real=0.04 secs]
2020-10-25T19:22:07.293-0800: [GC (Allocation Failure) [PSYoungGen: 305661K->43510K(305664K)] 348958K->162184K(1005056K), 0.0538269 secs] [Times: user=0.06 sys=0.34, real=0.05 secs]
2020-10-25T19:22:07.387-0800: [GC (Allocation Failure) [PSYoungGen: 305090K->43507K(305664K)] 423765K->231875K(1005056K), 0.0403476 secs] [Times: user=0.09 sys=0.20, real=0.04 secs]
2020-10-25T19:22:07.467-0800: [GC (Allocation Failure) [PSYoungGen: 305354K->43519K(305664K)] 493723K->305125K(1005056K), 0.0390571 secs] [Times: user=0.08 sys=0.22, real=0.04 secs]
2020-10-25T19:22:07.539-0800: [GC (Allocation Failure) [PSYoungGen: 305663K->43519K(305664K)] 567269K->377394K(1005056K), 0.0397918 secs] [Times: user=0.07 sys=0.19, real=0.04 secs]
2020-10-25T19:22:07.621-0800: [GC (Allocation Failure) [PSYoungGen: 305663K->43516K(160256K)] 639538K->452998K(859648K), 0.0418977 secs] [Times: user=0.09 sys=0.22, real=0.04 secs]
2020-10-25T19:22:07.681-0800: [GC (Allocation Failure) [PSYoungGen: 160252K->69217K(232960K)] 569734K->486584K(932352K), 0.0087073 secs] [Times: user=0.05 sys=0.02, real=0.01 secs]
2020-10-25T19:22:07.706-0800: [GC (Allocation Failure) [PSYoungGen: 185776K->89733K(232960K)] 603142K->516187K(932352K), 0.0132591 secs] [Times: user=0.08 sys=0.03, real=0.02 secs]
2020-10-25T19:22:07.740-0800: [GC (Allocation Failure) [PSYoungGen: 206096K->103082K(232960K)] 632550K->545518K(932352K), 0.0194482 secs] [Times: user=0.10 sys=0.06, real=0.01 secs]
2020-10-25T19:22:07.777-0800: [GC (Allocation Failure) [PSYoungGen: 219818K->77301K(232960K)] 662254K->576269K(932352K), 0.0303423 secs] [Times: user=0.05 sys=0.17, real=0.03 secs]
2020-10-25T19:22:07.823-0800: [GC (Allocation Failure) [PSYoungGen: 194037K->37302K(232960K)] 693005K->603893K(932352K), 0.0337089 secs] [Times: user=0.05 sys=0.20, real=0.03 secs]
2020-10-25T19:22:07.876-0800: [GC (Allocation Failure) [PSYoungGen: 154038K->39016K(232960K)] 720629K->639569K(932352K), 0.0188311 secs] [Times: user=0.04 sys=0.10, real=0.02 secs]
2020-10-25T19:22:07.895-0800: [Full GC (Ergonomics) [PSYoungGen: 39016K->0K(232960K)] [ParOldGen: 600552K->330655K(699392K)] 639569K->330655K(932352K), [Metaspace: 2726K->2726K(1056768K)], 0.0545856 secs] [Times: user=0.37 sys=0.03, real=0.05 secs]
2020-10-25T19:22:07.969-0800: [GC (Allocation Failure) [PSYoungGen: 116521K->37531K(232960K)] 447176K->368187K(932352K), 0.0049000 secs] [Times: user=0.05 sys=0.00, real=0.01 secs]
2020-10-25T19:22:07.991-0800: [GC (Allocation Failure) [PSYoungGen: 154267K->42060K(232960K)] 484923K->404992K(932352K), 0.0082980 secs] [Times: user=0.08 sys=0.00, real=0.00 secs]
2020-10-25T19:22:08.024-0800: [GC (Allocation Failure) [PSYoungGen: 158447K->37276K(232960K)] 521379K->438237K(932352K), 0.0088245 secs] [Times: user=0.07 sys=0.00, real=0.01 secs]
2020-10-25T19:22:08.051-0800: [GC (Allocation Failure) [PSYoungGen: 154012K->39478K(232960K)] 554973K->472859K(932352K), 0.0082643 secs] [Times: user=0.07 sys=0.00, real=0.00 secs]
执行结束!共生成对象次数:10506
Heap
 PSYoungGen      total 232960K, used 44319K [0x00000007aab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 116736K, 4% used [0x00000007aab00000,0x00000007aafba170,0x00000007b1d00000)
  from space 116224K, 33% used [0x00000007b8e80000,0x00000007bb50dab0,0x00000007c0000000)
  to   space 116224K, 0% used [0x00000007b1d00000,0x00000007b1d00000,0x00000007b8e80000)
 ParOldGen       total 699392K, used 433381K [0x0000000780000000, 0x00000007aab00000, 0x00000007aab00000)
  object space 699392K, 61% used [0x0000000780000000,0x000000079a7394d0,0x00000007aab00000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseParallelGC -Xms2g -Xmx2g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:22:20.382-0800: [GC (Allocation Failure) [PSYoungGen: 524800K->87031K(611840K)] 524800K->150191K(2010112K), 0.0697937 secs] [Times: user=0.08 sys=0.43, real=0.07 secs]
2020-10-25T19:22:20.539-0800: [GC (Allocation Failure) [PSYoungGen: 611831K->87038K(611840K)] 674991K->253064K(2010112K), 0.0876692 secs] [Times: user=0.10 sys=0.56, real=0.09 secs]
2020-10-25T19:22:20.698-0800: [GC (Allocation Failure) [PSYoungGen: 611838K->87036K(611840K)] 777864K->370410K(2010112K), 0.0669065 secs] [Times: user=0.14 sys=0.36, real=0.07 secs]
2020-10-25T19:22:20.841-0800: [GC (Allocation Failure) [PSYoungGen: 611836K->87036K(611840K)] 895210K->473824K(2010112K), 0.0634583 secs] [Times: user=0.15 sys=0.33, real=0.06 secs]
2020-10-25T19:22:20.984-0800: [GC (Allocation Failure) [PSYoungGen: 611836K->87037K(611840K)] 998624K->595181K(2010112K), 0.0708271 secs] [Times: user=0.14 sys=0.38, real=0.07 secs]
执行结束!共生成对象次数:10509
Heap
 PSYoungGen      total 611840K, used 248769K [0x0000000795580000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 524800K, 30% used [0x0000000795580000,0x000000079f371348,0x00000007b5600000)
  from space 87040K, 99% used [0x00000007b5600000,0x00000007baaff478,0x00000007bab00000)
  to   space 87040K, 0% used [0x00000007bab00000,0x00000007bab00000,0x00000007c0000000)
 ParOldGen       total 1398272K, used 508144K [0x0000000740000000, 0x0000000795580000, 0x0000000795580000)
  object space 1398272K, 36% used [0x0000000740000000,0x000000075f03c070,0x0000000795580000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseParallelGC -Xms4g -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:22:31.016-0800: [GC (Allocation Failure) [PSYoungGen: 1048576K->174585K(1223168K)] 1048576K->231930K(4019712K), 0.1108331 secs] [Times: user=0.12 sys=0.68, real=0.11 secs]
2020-10-25T19:22:31.305-0800: [GC (Allocation Failure) [PSYoungGen: 1223161K->174584K(1223168K)] 1280506K->376184K(4019712K), 0.1518939 secs] [Times: user=0.15 sys=0.96, real=0.15 secs]
执行结束!共生成对象次数:7990
Heap
 PSYoungGen      total 1223168K, used 216682K [0x000000076ab00000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 1048576K, 4% used [0x000000076ab00000,0x000000076d41c570,0x00000007aab00000)
  from space 174592K, 99% used [0x00000007b5580000,0x00000007bfffe3e8,0x00000007c0000000)
  to   space 174592K, 0% used [0x00000007aab00000,0x00000007aab00000,0x00000007b5580000)
 ParOldGen       total 2796544K, used 201599K [0x00000006c0000000, 0x000000076ab00000, 0x000000076ab00000)
  object space 2796544K, 7% used [0x00000006c0000000,0x00000006cc4dffa8,0x000000076ab00000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
```

##### ConcMarkSweepGC

```sh
➜  Week_02 git:(main) ✗ java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:24:38.747-0800: [GC (Allocation Failure) 2020-10-25T19:24:38.747-0800: [ParNew: 139776K->17468K(157248K), 0.0204348 secs] 139776K->43181K(506816K), 0.0205237 secs] [Times: user=0.04 sys=0.11, real=0.02 secs]
2020-10-25T19:24:38.789-0800: [GC (Allocation Failure) 2020-10-25T19:24:38.789-0800: [ParNew: 157244K->17471K(157248K), 0.0258866 secs] 182957K->91623K(506816K), 0.0259252 secs] [Times: user=0.06 sys=0.14, real=0.03 secs]
2020-10-25T19:24:38.840-0800: [GC (Allocation Failure) 2020-10-25T19:24:38.840-0800: [ParNew: 157247K->17471K(157248K), 0.0344052 secs] 231399K->142185K(506816K), 0.0344659 secs] [Times: user=0.31 sys=0.02, real=0.03 secs]
2020-10-25T19:24:38.897-0800: [GC (Allocation Failure) 2020-10-25T19:24:38.897-0800: [ParNew: 157247K->17469K(157248K), 0.0310962 secs] 281961K->190895K(506816K), 0.0311317 secs] [Times: user=0.27 sys=0.02, real=0.03 secs]
2020-10-25T19:24:38.951-0800: [GC (Allocation Failure) 2020-10-25T19:24:38.951-0800: [ParNew: 157245K->17470K(157248K), 0.0238280 secs] 330671K->227447K(506816K), 0.0238702 secs] [Times: user=0.21 sys=0.01, real=0.02 secs]
2020-10-25T19:24:38.975-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 209976K(349568K)] 230508K(506816K), 0.0001656 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:38.975-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:38.977-0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:38.977-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:38.977-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:38.977-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-25T19:24:38.996-0800: [GC (Allocation Failure) 2020-10-25T19:24:38.996-0800: [ParNew: 157246K->17470K(157248K), 0.0306656 secs] 367223K->271238K(506816K), 0.0307058 secs] [Times: user=0.25 sys=0.02, real=0.03 secs]
2020-10-25T19:24:39.046-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.046-0800: [ParNew: 157246K->17471K(157248K), 0.0326475 secs] 411014K->320075K(506816K), 0.0326827 secs] [Times: user=0.27 sys=0.02, real=0.03 secs]
2020-10-25T19:24:39.103-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.103-0800: [ParNew: 157247K->157247K(157248K), 0.0000558 secs]2020-10-25T19:24:39.103-0800: [CMS2020-10-25T19:24:39.103-0800: [CMS-concurrent-abortable-preclean: 0.003/0.126 secs] [Times: user=0.60 sys=0.04, real=0.13 secs]
 (concurrent mode failure): 302604K->250288K(349568K), 0.0458239 secs] 459851K->250288K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0460127 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2020-10-25T19:24:39.168-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.168-0800: [ParNew: 139776K->17471K(157248K), 0.0066853 secs] 390064K->294642K(506816K), 0.0067347 secs] [Times: user=0.06 sys=0.00, real=0.01 secs]
2020-10-25T19:24:39.175-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 277170K(349568K)] 297454K(506816K), 0.0002366 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.175-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:39.176-0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.176-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:39.176-0800: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.176-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-25T19:24:39.197-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.197-0800: [ParNew: 157247K->17470K(157248K), 0.0143909 secs] 434418K->335437K(506816K), 0.0144289 secs] [Times: user=0.12 sys=0.01, real=0.02 secs]
2020-10-25T19:24:39.234-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.234-0800: [ParNew: 157184K->157184K(157248K), 0.0000218 secs]2020-10-25T19:24:39.234-0800: [CMS2020-10-25T19:24:39.234-0800: [CMS-concurrent-abortable-preclean: 0.002/0.058 secs] [Times: user=0.18 sys=0.01, real=0.06 secs]
 (concurrent mode failure): 317967K->279416K(349568K), 0.0413621 secs] 475151K->279416K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0414423 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2020-10-25T19:24:39.297-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.297-0800: [ParNew: 139776K->17468K(157248K), 0.0071164 secs] 419192K->327182K(506816K), 0.0071697 secs] [Times: user=0.07 sys=0.00, real=0.01 secs]
2020-10-25T19:24:39.305-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 309713K(349568K)] 327571K(506816K), 0.0002098 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.305-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:39.306-0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.306-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:39.306-0800: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.306-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-25T19:24:39.323-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.323-0800: [ParNew: 157244K->157244K(157248K), 0.0000194 secs]2020-10-25T19:24:39.323-0800: [CMS2020-10-25T19:24:39.323-0800: [CMS-concurrent-abortable-preclean: 0.001/0.017 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
 (concurrent mode failure): 309713K->300304K(349568K), 0.0439978 secs] 466958K->300304K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0440588 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2020-10-25T19:24:39.388-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.388-0800: [ParNew: 139776K->17470K(157248K), 0.0123048 secs] 440080K->348099K(506816K), 0.0123434 secs] [Times: user=0.11 sys=0.01, real=0.02 secs]
2020-10-25T19:24:39.401-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 330628K(349568K)] 348468K(506816K), 0.0002724 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.401-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:39.402-0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.402-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:39.402-0800: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.402-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-25T19:24:39.402-0800: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.403-0800: [GC (CMS Final Remark) [YG occupancy: 26492 K (157248 K)]2020-10-25T19:24:39.403-0800: [Rescan (parallel) , 0.0002644 secs]2020-10-25T19:24:39.403-0800: [weak refs processing, 0.0000147 secs]2020-10-25T19:24:39.403-0800: [class unloading, 0.0003257 secs]2020-10-25T19:24:39.403-0800: [scrub symbol table, 0.0004265 secs]2020-10-25T19:24:39.404-0800: [scrub string table, 0.0001584 secs][1 CMS-remark: 330628K(349568K)] 357121K(506816K), 0.0012561 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.404-0800: [CMS-concurrent-sweep-start]
2020-10-25T19:24:39.404-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.404-0800: [CMS-concurrent-reset-start]
2020-10-25T19:24:39.405-0800: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.419-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.419-0800: [ParNew: 157246K->17471K(157248K), 0.0126233 secs] 447113K->350336K(506816K), 0.0126637 secs] [Times: user=0.10 sys=0.00, real=0.02 secs]
2020-10-25T19:24:39.432-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 332864K(349568K)] 353383K(506816K), 0.0001956 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.433-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:39.433-0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.433-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:39.434-0800: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.434-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-25T19:24:39.434-0800: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.434-0800: [GC (CMS Final Remark) [YG occupancy: 31403 K (157248 K)]2020-10-25T19:24:39.434-0800: [Rescan (parallel) , 0.0002267 secs]2020-10-25T19:24:39.434-0800: [weak refs processing, 0.0000167 secs]2020-10-25T19:24:39.434-0800: [class unloading, 0.0001837 secs]2020-10-25T19:24:39.434-0800: [scrub symbol table, 0.0003079 secs]2020-10-25T19:24:39.435-0800: [scrub string table, 0.0001550 secs][1 CMS-remark: 332864K(349568K)] 364268K(506816K), 0.0009512 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.435-0800: [CMS-concurrent-sweep-start]
2020-10-25T19:24:39.435-0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.435-0800: [CMS-concurrent-reset-start]
2020-10-25T19:24:39.436-0800: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.451-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.451-0800: [ParNew: 157247K->157247K(157248K), 0.0000197 secs]2020-10-25T19:24:39.451-0800: [CMS: 295662K->316092K(349568K), 0.0452578 secs] 452910K->316092K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0453370 secs] [Times: user=0.04 sys=0.00, real=0.04 secs]
2020-10-25T19:24:39.497-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 316092K(349568K)] 316380K(506816K), 0.0002376 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.497-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:39.498-0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.498-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:39.498-0800: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.498-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-25T19:24:39.523-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.523-0800: [ParNew: 139492K->139492K(157248K), 0.0000229 secs]2020-10-25T19:24:39.524-0800: [CMS2020-10-25T19:24:39.524-0800: [CMS-concurrent-abortable-preclean: 0.000/0.025 secs] [Times: user=0.02 sys=0.00, real=0.03 secs]
 (concurrent mode failure): 316092K->318694K(349568K), 0.0515858 secs] 455585K->318694K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0516814 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
2020-10-25T19:24:39.596-0800: [GC (Allocation Failure) 2020-10-25T19:24:39.596-0800: [ParNew: 139776K->139776K(157248K), 0.0000199 secs]2020-10-25T19:24:39.596-0800: [CMS: 318694K->326920K(349568K), 0.0476596 secs] 458470K->326920K(506816K), [Metaspace: 2726K->2726K(1056768K)], 0.0477296 secs] [Times: user=0.05 sys=0.00, real=0.05 secs]
2020-10-25T19:24:39.644-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 326920K(349568K)] 327182K(506816K), 0.0002398 secs] [Times: user=0.00 sys=0.01, real=0.00 secs]
2020-10-25T19:24:39.644-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:39.645-0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.645-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:39.645-0800: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:39.645-0800: [CMS-concurrent-abortable-preclean-start]
执行结束!共生成对象次数:9739
Heap
 par new generation   total 157248K, used 87610K [0x00000007a0000000, 0x00000007aaaa0000, 0x00000007aaaa0000)
  eden space 139776K,  62% used [0x00000007a0000000, 0x00000007a558ebb8, 0x00000007a8880000)
  from space 17472K,   0% used [0x00000007a8880000, 0x00000007a8880000, 0x00000007a9990000)
  to   space 17472K,   0% used [0x00000007a9990000, 0x00000007a9990000, 0x00000007aaaa0000)
 concurrent mark-sweep generation total 349568K, used 326920K [0x00000007aaaa0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:24:56.358-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.358-0800: [ParNew: 279616K->34942K(314560K), 0.0439731 secs] 279616K->93226K(1013632K), 0.0440214 secs] [Times: user=0.07 sys=0.26, real=0.05 secs]
2020-10-25T19:24:56.446-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.446-0800: [ParNew: 314558K->34942K(314560K), 0.0447308 secs] 372842K->173668K(1013632K), 0.0447698 secs] [Times: user=0.08 sys=0.26, real=0.05 secs]
2020-10-25T19:24:56.527-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.527-0800: [ParNew: 314558K->34944K(314560K), 0.0587483 secs] 453284K->259700K(1013632K), 0.0587871 secs] [Times: user=0.49 sys=0.03, real=0.06 secs]
2020-10-25T19:24:56.623-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.623-0800: [ParNew: 314560K->34942K(314560K), 0.0542390 secs] 539316K->343575K(1013632K), 0.0542767 secs] [Times: user=0.48 sys=0.03, real=0.05 secs]
2020-10-25T19:24:56.711-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.711-0800: [ParNew: 314558K->34940K(314560K), 0.0574943 secs] 623191K->430067K(1013632K), 0.0575347 secs] [Times: user=0.50 sys=0.03, real=0.05 secs]
2020-10-25T19:24:56.768-0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 395126K(699072K)] 435969K(1013632K), 0.0003214 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:56.769-0800: [CMS-concurrent-mark-start]
2020-10-25T19:24:56.772-0800: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
2020-10-25T19:24:56.772-0800: [CMS-concurrent-preclean-start]
2020-10-25T19:24:56.773-0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
2020-10-25T19:24:56.773-0800: [CMS-concurrent-abortable-preclean-start]
2020-10-25T19:24:56.805-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.805-0800: [ParNew: 314556K->34943K(314560K), 0.0493741 secs] 709683K->506628K(1013632K), 0.0494259 secs] [Times: user=0.43 sys=0.03, real=0.05 secs]
2020-10-25T19:24:56.902-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.902-0800: [ParNew: 314559K->34942K(314560K), 0.0504374 secs] 786244K->584707K(1013632K), 0.0504822 secs] [Times: user=0.46 sys=0.03, real=0.05 secs]
2020-10-25T19:24:56.991-0800: [GC (Allocation Failure) 2020-10-25T19:24:56.991-0800: [ParNew: 314558K->34942K(314560K), 0.0560024 secs] 864323K->663983K(1013632K), 0.0560398 secs] [Times: user=0.46 sys=0.04, real=0.05 secs]
2020-10-25T19:24:57.088-0800: [GC (Allocation Failure) 2020-10-25T19:24:57.088-0800: [ParNew: 314405K->314405K(314560K), 0.0000191 secs]2020-10-25T19:24:57.088-0800: [CMS2020-10-25T19:24:57.088-0800: [CMS-concurrent-abortable-preclean: 0.007/0.315 secs] [Times: user=1.52 sys=0.10, real=0.31 secs]
 (concurrent mode failure): 629040K->353913K(699072K), 0.0638363 secs] 943446K->353913K(1013632K), [Metaspace: 2726K->2726K(1056768K)], 0.0639062 secs] [Times: user=0.06 sys=0.00, real=0.07 secs]
执行结束!共生成对象次数:10293
Heap
 par new generation   total 314560K, used 224115K [0x0000000780000000, 0x0000000795550000, 0x0000000795550000)
  eden space 279616K,  80% used [0x0000000780000000, 0x000000078dadcc48, 0x0000000791110000)
  from space 34944K,   0% used [0x0000000791110000, 0x0000000791110000, 0x0000000793330000)
  to   space 34944K,   0% used [0x0000000793330000, 0x0000000793330000, 0x0000000795550000)
 concurrent mark-sweep generation total 699072K, used 353913K [0x0000000795550000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseConcMarkSweepGC -Xms2g -Xmx2g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:25:07.313-0800: [GC (Allocation Failure) 2020-10-25T19:25:07.313-0800: [ParNew: 559232K->69887K(629120K), 0.0650414 secs] 559232K->144153K(2027264K), 0.0650917 secs] [Times: user=0.11 sys=0.38, real=0.06 secs]
2020-10-25T19:25:07.467-0800: [GC (Allocation Failure) 2020-10-25T19:25:07.467-0800: [ParNew: 629119K->69888K(629120K), 0.0743970 secs] 703385K->267058K(2027264K), 0.0744353 secs] [Times: user=0.14 sys=0.44, real=0.08 secs]
2020-10-25T19:25:07.618-0800: [GC (Allocation Failure) 2020-10-25T19:25:07.618-0800: [ParNew: 629120K->69887K(629120K), 0.0888474 secs] 826290K->397146K(2027264K), 0.0888883 secs] [Times: user=0.80 sys=0.05, real=0.09 secs]
2020-10-25T19:25:07.786-0800: [GC (Allocation Failure) 2020-10-25T19:25:07.786-0800: [ParNew: 629119K->69886K(629120K), 0.0858989 secs] 956378K->526011K(2027264K), 0.0859388 secs] [Times: user=0.78 sys=0.05, real=0.09 secs]
2020-10-25T19:25:07.939-0800: [GC (Allocation Failure) 2020-10-25T19:25:07.939-0800: [ParNew: 629118K->69888K(629120K), 0.0859524 secs] 1085243K->654426K(2027264K), 0.0859908 secs] [Times: user=0.74 sys=0.05, real=0.09 secs]
执行结束!共生成对象次数:10598
Heap
 par new generation   total 629120K, used 92199K [0x0000000740000000, 0x000000076aaa0000, 0x000000076aaa0000)
  eden space 559232K,   3% used [0x0000000740000000, 0x00000007415c9e30, 0x0000000762220000)
  from space 69888K, 100% used [0x0000000766660000, 0x000000076aaa0000, 0x000000076aaa0000)
  to   space 69888K,   0% used [0x0000000762220000, 0x0000000762220000, 0x0000000766660000)
 concurrent mark-sweep generation total 1398144K, used 584538K [0x000000076aaa0000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
➜  Week_02 git:(main) ✗ java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:25:16.581-0800: [GC (Allocation Failure) 2020-10-25T19:25:16.581-0800: [ParNew: 681600K->85183K(766784K), 0.0853498 secs] 681600K->174412K(4109120K), 0.0853965 secs] [Times: user=0.14 sys=0.50, real=0.08 secs]
2020-10-25T19:25:16.783-0800: [GC (Allocation Failure) 2020-10-25T19:25:16.783-0800: [ParNew: 766783K->85184K(766784K), 0.0930908 secs] 856012K->310504K(4109120K), 0.0931276 secs] [Times: user=0.19 sys=0.50, real=0.09 secs]
2020-10-25T19:25:16.964-0800: [GC (Allocation Failure) 2020-10-25T19:25:16.964-0800: [ParNew: 766784K->85182K(766784K), 0.1062664 secs] 992104K->448994K(4109120K), 0.1063055 secs] [Times: user=0.88 sys=0.06, real=0.11 secs]
2020-10-25T19:25:17.164-0800: [GC (Allocation Failure) 2020-10-25T19:25:17.164-0800: [ParNew: 766782K->85183K(766784K), 0.1019776 secs] 1130594K->596011K(4109120K), 0.1020361 secs] [Times: user=0.92 sys=0.05, real=0.10 secs]
执行结束!共生成对象次数:10137
Heap
 par new generation   total 766784K, used 112829K [0x00000006c0000000, 0x00000006f4000000, 0x00000006f4000000)
  eden space 681600K,   4% used [0x00000006c0000000, 0x00000006c1aff610, 0x00000006e99a0000)
  from space 85184K,  99% used [0x00000006e99a0000, 0x00000006eeccff58, 0x00000006eecd0000)
  to   space 85184K,   0% used [0x00000006eecd0000, 0x00000006eecd0000, 0x00000006f4000000)
 concurrent mark-sweep generation total 3342336K, used 510828K [0x00000006f4000000, 0x00000007c0000000, 0x00000007c0000000)
 Metaspace       used 2732K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 296K, capacity 386K, committed 512K, reserved 1048576K
➜  Week_02 git:(main) ✗
```

##### G1GC

```sh
➜  Week_02 git:(main) ✗ java -XX:+UseG1GC -Xms512m -Xmx512m -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:27:27.789-0800: [GC pause (G1 Evacuation Pause) (young) 29M->7697K(512M), 0.0031893 secs]
2020-10-25T19:27:27.806-0800: [GC pause (G1 Evacuation Pause) (young) 43M->19M(512M), 0.0051442 secs]
2020-10-25T19:27:27.847-0800: [GC pause (G1 Evacuation Pause) (young) 108M->50M(512M), 0.0125571 secs]
2020-10-25T19:27:27.875-0800: [GC pause (G1 Evacuation Pause) (young) 121M->72M(512M), 0.0097870 secs]
2020-10-25T19:27:27.907-0800: [GC pause (G1 Evacuation Pause) (young) 169M->99M(512M), 0.0124953 secs]
2020-10-25T19:27:27.963-0800: [GC pause (G1 Evacuation Pause) (young) 246M->140M(512M), 0.0180883 secs]
2020-10-25T19:27:28.011-0800: [GC pause (G1 Evacuation Pause) (young) 299M->187M(512M), 0.0219253 secs]
2020-10-25T19:27:28.060-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 331M->226M(512M), 0.0139986 secs]
2020-10-25T19:27:28.074-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.074-0800: [GC concurrent-root-region-scan-end, 0.0001368 secs]
2020-10-25T19:27:28.074-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.076-0800: [GC concurrent-mark-end, 0.0017253 secs]
2020-10-25T19:27:28.076-0800: [GC remark, 0.0012588 secs]
2020-10-25T19:27:28.078-0800: [GC cleanup 240M->240M(512M), 0.0006091 secs]
2020-10-25T19:27:28.120-0800: [GC pause (G1 Evacuation Pause) (young)-- 415M->318M(512M), 0.0219464 secs]
2020-10-25T19:27:28.143-0800: [GC pause (G1 Evacuation Pause) (mixed) 323M->293M(512M), 0.0064696 secs]
2020-10-25T19:27:28.151-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 297M->294M(512M), 0.0012478 secs]
2020-10-25T19:27:28.152-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.152-0800: [GC concurrent-root-region-scan-end, 0.0001152 secs]
2020-10-25T19:27:28.152-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.153-0800: [GC concurrent-mark-end, 0.0008361 secs]
2020-10-25T19:27:28.153-0800: [GC remark, 0.0013780 secs]
2020-10-25T19:27:28.155-0800: [GC cleanup 304M->304M(512M), 0.0005907 secs]
2020-10-25T19:27:28.173-0800: [GC pause (G1 Evacuation Pause) (young) 428M->335M(512M), 0.0041977 secs]
2020-10-25T19:27:28.179-0800: [GC pause (G1 Evacuation Pause) (mixed) 349M->294M(512M), 0.0035985 secs]
2020-10-25T19:27:28.187-0800: [GC pause (G1 Evacuation Pause) (mixed) 323M->276M(512M), 0.0050707 secs]
2020-10-25T19:27:28.192-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 277M->276M(512M), 0.0017239 secs]
2020-10-25T19:27:28.194-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.194-0800: [GC concurrent-root-region-scan-end, 0.0001674 secs]
2020-10-25T19:27:28.194-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.196-0800: [GC concurrent-mark-end, 0.0013783 secs]
2020-10-25T19:27:28.196-0800: [GC remark, 0.0014599 secs]
2020-10-25T19:27:28.198-0800: [GC cleanup 284M->284M(512M), 0.0007550 secs]
2020-10-25T19:27:28.225-0800: [GC pause (G1 Evacuation Pause) (young)-- 427M->366M(512M), 0.0043108 secs]
2020-10-25T19:27:28.232-0800: [GC pause (G1 Evacuation Pause) (mixed) 377M->341M(512M), 0.0062341 secs]
2020-10-25T19:27:28.244-0800: [GC pause (G1 Evacuation Pause) (mixed) 371M->347M(512M), 0.0030263 secs]
2020-10-25T19:27:28.247-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 348M->348M(512M), 0.0015994 secs]
2020-10-25T19:27:28.249-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.249-0800: [GC concurrent-root-region-scan-end, 0.0001693 secs]
2020-10-25T19:27:28.249-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.250-0800: [GC concurrent-mark-end, 0.0011967 secs]
2020-10-25T19:27:28.250-0800: [GC remark, 0.0036001 secs]
2020-10-25T19:27:28.254-0800: [GC cleanup 354M->352M(512M), 0.0008767 secs]
2020-10-25T19:27:28.255-0800: [GC concurrent-cleanup-start]
2020-10-25T19:27:28.255-0800: [GC concurrent-cleanup-end, 0.0000155 secs]
2020-10-25T19:27:28.267-0800: [GC pause (G1 Evacuation Pause) (young) 411M->359M(512M), 0.0049594 secs]
2020-10-25T19:27:28.275-0800: [GC pause (G1 Evacuation Pause) (mixed) 385M->322M(512M), 0.0055722 secs]
2020-10-25T19:27:28.286-0800: [GC pause (G1 Evacuation Pause) (mixed) 348M->302M(512M), 0.0053142 secs]
2020-10-25T19:27:28.294-0800: [GC pause (G1 Evacuation Pause) (mixed) 328M->311M(512M), 0.0040066 secs]
2020-10-25T19:27:28.298-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 312M->310M(512M), 0.0022980 secs]
2020-10-25T19:27:28.301-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.301-0800: [GC concurrent-root-region-scan-end, 0.0001397 secs]
2020-10-25T19:27:28.301-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.302-0800: [GC concurrent-mark-end, 0.0012521 secs]
2020-10-25T19:27:28.302-0800: [GC remark, 0.0012452 secs]
2020-10-25T19:27:28.304-0800: [GC cleanup 319M->319M(512M), 0.0007384 secs]
2020-10-25T19:27:28.316-0800: [GC pause (G1 Evacuation Pause) (young) 406M->341M(512M), 0.0042933 secs]
2020-10-25T19:27:28.323-0800: [GC pause (G1 Evacuation Pause) (mixed) 360M->323M(512M), 0.0048516 secs]
2020-10-25T19:27:28.328-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 324M->324M(512M), 0.0016624 secs]
2020-10-25T19:27:28.330-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.330-0800: [GC concurrent-root-region-scan-end, 0.0001192 secs]
2020-10-25T19:27:28.330-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.331-0800: [GC concurrent-mark-end, 0.0010398 secs]
2020-10-25T19:27:28.331-0800: [GC remark, 0.0012927 secs]
2020-10-25T19:27:28.333-0800: [GC cleanup 331M->331M(512M), 0.0007882 secs]
2020-10-25T19:27:28.344-0800: [GC pause (G1 Evacuation Pause) (young) 410M->349M(512M), 0.0050036 secs]
2020-10-25T19:27:28.352-0800: [GC pause (G1 Evacuation Pause) (mixed) 367M->328M(512M), 0.0046510 secs]
2020-10-25T19:27:28.357-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 330M->328M(512M), 0.0015285 secs]
2020-10-25T19:27:28.359-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.359-0800: [GC concurrent-root-region-scan-end, 0.0001365 secs]
2020-10-25T19:27:28.359-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.360-0800: [GC concurrent-mark-end, 0.0009521 secs]
2020-10-25T19:27:28.360-0800: [GC remark, 0.0014865 secs]
2020-10-25T19:27:28.362-0800: [GC cleanup 335M->335M(512M), 0.0008574 secs]
2020-10-25T19:27:28.373-0800: [GC pause (G1 Evacuation Pause) (young) 411M->354M(512M), 0.0039357 secs]
2020-10-25T19:27:28.381-0800: [GC pause (G1 Evacuation Pause) (mixed) 372M->341M(512M), 0.0047563 secs]
2020-10-25T19:27:28.386-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 342M->341M(512M), 0.0015105 secs]
2020-10-25T19:27:28.387-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.387-0800: [GC concurrent-root-region-scan-end, 0.0001068 secs]
2020-10-25T19:27:28.387-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.388-0800: [GC concurrent-mark-end, 0.0009591 secs]
2020-10-25T19:27:28.389-0800: [GC remark, 0.0015743 secs]
2020-10-25T19:27:28.390-0800: [GC cleanup 347M->347M(512M), 0.0007204 secs]
2020-10-25T19:27:28.399-0800: [GC pause (G1 Evacuation Pause) (young) 400M->353M(512M), 0.0041138 secs]
2020-10-25T19:27:28.407-0800: [GC pause (G1 Evacuation Pause) (mixed) 377M->341M(512M), 0.0045285 secs]
2020-10-25T19:27:28.412-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 342M->341M(512M), 0.0016516 secs]
2020-10-25T19:27:28.413-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.413-0800: [GC concurrent-root-region-scan-end, 0.0001296 secs]
2020-10-25T19:27:28.414-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.415-0800: [GC concurrent-mark-end, 0.0010572 secs]
2020-10-25T19:27:28.415-0800: [GC remark, 0.0013714 secs]
2020-10-25T19:27:28.416-0800: [GC cleanup 347M->347M(512M), 0.0007925 secs]
2020-10-25T19:27:28.428-0800: [GC pause (G1 Evacuation Pause) (young) 409M->359M(512M), 0.0144951 secs]
2020-10-25T19:27:28.447-0800: [GC pause (G1 Evacuation Pause) (mixed) 382M->348M(512M), 0.0065341 secs]
2020-10-25T19:27:28.454-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 349M->348M(512M), 0.0014888 secs]
2020-10-25T19:27:28.455-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.455-0800: [GC concurrent-root-region-scan-end, 0.0000181 secs]
2020-10-25T19:27:28.455-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.456-0800: [GC concurrent-mark-end, 0.0011034 secs]
2020-10-25T19:27:28.456-0800: [GC remark, 0.0014053 secs]
2020-10-25T19:27:28.458-0800: [GC cleanup 357M->357M(512M), 0.0008238 secs]
2020-10-25T19:27:28.468-0800: [GC pause (G1 Evacuation Pause) (young) 419M->362M(512M), 0.0026950 secs]
2020-10-25T19:27:28.473-0800: [GC pause (G1 Evacuation Pause) (mixed) 384M->351M(512M), 0.0049721 secs]
2020-10-25T19:27:28.479-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 354M->351M(512M), 0.0018004 secs]
2020-10-25T19:27:28.481-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.481-0800: [GC concurrent-root-region-scan-end, 0.0001365 secs]
2020-10-25T19:27:28.481-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.482-0800: [GC concurrent-mark-end, 0.0011854 secs]
2020-10-25T19:27:28.482-0800: [GC remark, 0.0014395 secs]
2020-10-25T19:27:28.484-0800: [GC cleanup 359M->359M(512M), 0.0008494 secs]
2020-10-25T19:27:28.491-0800: [GC pause (G1 Evacuation Pause) (young) 402M->365M(512M), 0.0027181 secs]
2020-10-25T19:27:28.496-0800: [GC pause (G1 Evacuation Pause) (mixed) 386M->351M(512M), 0.0050326 secs]
2020-10-25T19:27:28.502-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 356M->351M(512M), 0.0016792 secs]
2020-10-25T19:27:28.504-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.504-0800: [GC concurrent-root-region-scan-end, 0.0001332 secs]
2020-10-25T19:27:28.504-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.506-0800: [GC concurrent-mark-end, 0.0014860 secs]
2020-10-25T19:27:28.506-0800: [GC remark, 0.0021403 secs]
2020-10-25T19:27:28.508-0800: [GC cleanup 361M->361M(512M), 0.0016145 secs]
2020-10-25T19:27:28.517-0800: [GC pause (G1 Evacuation Pause) (young) 402M->366M(512M), 0.0035496 secs]
2020-10-25T19:27:28.524-0800: [GC pause (G1 Evacuation Pause) (mixed) 387M->357M(512M), 0.0047275 secs]
2020-10-25T19:27:28.533-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 376M->361M(512M), 0.0019676 secs]
2020-10-25T19:27:28.535-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.535-0800: [GC concurrent-root-region-scan-end, 0.0001683 secs]
2020-10-25T19:27:28.535-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.536-0800: [GC concurrent-mark-end, 0.0010191 secs]
2020-10-25T19:27:28.536-0800: [GC remark, 0.0013219 secs]
2020-10-25T19:27:28.537-0800: [GC cleanup 370M->370M(512M), 0.0006665 secs]
2020-10-25T19:27:28.546-0800: [GC pause (G1 Evacuation Pause) (young) 405M->373M(512M), 0.0035761 secs]
2020-10-25T19:27:28.554-0800: [GC pause (G1 Evacuation Pause) (mixed) 398M->359M(512M), 0.0054198 secs]
2020-10-25T19:27:28.564-0800: [GC pause (G1 Evacuation Pause) (mixed) 385M->366M(512M), 0.0023119 secs]
2020-10-25T19:27:28.567-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 368M->366M(512M), 0.0015321 secs]
2020-10-25T19:27:28.568-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.568-0800: [GC concurrent-root-region-scan-end, 0.0001063 secs]
2020-10-25T19:27:28.568-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.570-0800: [GC concurrent-mark-end, 0.0010858 secs]
2020-10-25T19:27:28.570-0800: [GC remark, 0.0013739 secs]
2020-10-25T19:27:28.571-0800: [GC cleanup 373M->373M(512M), 0.0006708 secs]
2020-10-25T19:27:28.577-0800: [GC pause (G1 Evacuation Pause) (young) 416M->381M(512M), 0.0030848 secs]
2020-10-25T19:27:28.583-0800: [GC pause (G1 Evacuation Pause) (mixed) 405M->367M(512M), 0.0057607 secs]
2020-10-25T19:27:28.594-0800: [GC pause (G1 Evacuation Pause) (mixed) 390M->374M(512M), 0.0028134 secs]
2020-10-25T19:27:28.597-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 376M->374M(512M), 0.0014401 secs]
2020-10-25T19:27:28.599-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.599-0800: [GC concurrent-root-region-scan-end, 0.0001176 secs]
2020-10-25T19:27:28.599-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.600-0800: [GC concurrent-mark-end, 0.0011222 secs]
2020-10-25T19:27:28.600-0800: [GC remark, 0.0013698 secs]
2020-10-25T19:27:28.602-0800: [GC cleanup 388M->388M(512M), 0.0007532 secs]
2020-10-25T19:27:28.606-0800: [GC pause (G1 Evacuation Pause) (young) 405M->378M(512M), 0.0023433 secs]
2020-10-25T19:27:28.611-0800: [GC pause (G1 Evacuation Pause) (mixed) 404M->369M(512M), 0.0058220 secs]
2020-10-25T19:27:28.623-0800: [GC pause (G1 Evacuation Pause) (mixed) 394M->377M(512M), 0.0036647 secs]
2020-10-25T19:27:28.627-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 379M->377M(512M), 0.0015677 secs]
2020-10-25T19:27:28.629-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.629-0800: [GC concurrent-root-region-scan-end, 0.0001753 secs]
2020-10-25T19:27:28.629-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.631-0800: [GC concurrent-mark-end, 0.0015017 secs]
2020-10-25T19:27:28.631-0800: [GC remark, 0.0016727 secs]
2020-10-25T19:27:28.633-0800: [GC cleanup 383M->383M(512M), 0.0009821 secs]
2020-10-25T19:27:28.638-0800: [GC pause (G1 Evacuation Pause) (young) 405M->382M(512M), 0.0018248 secs]
2020-10-25T19:27:28.643-0800: [GC pause (G1 Evacuation Pause) (mixed) 409M->371M(512M), 0.0057598 secs]
2020-10-25T19:27:28.656-0800: [GC pause (G1 Evacuation Pause) (mixed) 398M->375M(512M), 0.0066276 secs]
2020-10-25T19:27:28.663-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 378M->377M(512M), 0.0021910 secs]
2020-10-25T19:27:28.665-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.666-0800: [GC concurrent-root-region-scan-end, 0.0001859 secs]
2020-10-25T19:27:28.666-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.667-0800: [GC concurrent-mark-end, 0.0012777 secs]
2020-10-25T19:27:28.667-0800: [GC remark, 0.0034304 secs]
2020-10-25T19:27:28.671-0800: [GC cleanup 385M->385M(512M), 0.0008444 secs]
2020-10-25T19:27:28.674-0800: [GC pause (G1 Evacuation Pause) (young) 404M->384M(512M), 0.0021732 secs]
2020-10-25T19:27:28.680-0800: [GC pause (G1 Evacuation Pause) (mixed) 410M->375M(512M), 0.0051612 secs]
2020-10-25T19:27:28.686-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 378M->375M(512M), 0.0017095 secs]
2020-10-25T19:27:28.688-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.688-0800: [GC concurrent-root-region-scan-end, 0.0001029 secs]
2020-10-25T19:27:28.688-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.689-0800: [GC concurrent-mark-end, 0.0010510 secs]
2020-10-25T19:27:28.689-0800: [GC remark, 0.0015796 secs]
2020-10-25T19:27:28.691-0800: [GC cleanup 380M->380M(512M), 0.0007644 secs]
2020-10-25T19:27:28.697-0800: [GC pause (G1 Evacuation Pause) (young) 407M->384M(512M), 0.0023806 secs]
2020-10-25T19:27:28.703-0800: [GC pause (G1 Evacuation Pause) (mixed) 408M->376M(512M), 0.0059636 secs]
2020-10-25T19:27:28.711-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 383M->377M(512M), 0.0022078 secs]
2020-10-25T19:27:28.713-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.713-0800: [GC concurrent-root-region-scan-end, 0.0002018 secs]
2020-10-25T19:27:28.713-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.715-0800: [GC concurrent-mark-end, 0.0012910 secs]
2020-10-25T19:27:28.715-0800: [GC remark, 0.0016033 secs]
2020-10-25T19:27:28.716-0800: [GC cleanup 386M->386M(512M), 0.0008900 secs]
2020-10-25T19:27:28.721-0800: [GC pause (G1 Evacuation Pause) (young) 406M->385M(512M), 0.0025705 secs]
2020-10-25T19:27:28.728-0800: [GC pause (G1 Evacuation Pause) (mixed) 412M->378M(512M), 0.0056411 secs]
2020-10-25T19:27:28.734-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 379M->379M(512M), 0.0018481 secs]
2020-10-25T19:27:28.736-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.736-0800: [GC concurrent-root-region-scan-end, 0.0001126 secs]
2020-10-25T19:27:28.736-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.737-0800: [GC concurrent-mark-end, 0.0011962 secs]
2020-10-25T19:27:28.737-0800: [GC remark, 0.0014243 secs]
2020-10-25T19:27:28.739-0800: [GC cleanup 387M->387M(512M), 0.0008846 secs]
2020-10-25T19:27:28.744-0800: [GC pause (G1 Evacuation Pause) (young) 408M->388M(512M), 0.0029874 secs]
2020-10-25T19:27:28.750-0800: [GC pause (G1 Evacuation Pause) (mixed) 416M->380M(512M), 0.0048631 secs]
2020-10-25T19:27:28.755-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 380M->380M(512M), 0.0015234 secs]
2020-10-25T19:27:28.757-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:28.757-0800: [GC concurrent-root-region-scan-end, 0.0001030 secs]
2020-10-25T19:27:28.757-0800: [GC concurrent-mark-start]
2020-10-25T19:27:28.759-0800: [GC concurrent-mark-end, 0.0013452 secs]
2020-10-25T19:27:28.759-0800: [GC remark, 0.0015093 secs]
2020-10-25T19:27:28.760-0800: [GC cleanup 389M->389M(512M), 0.0008420 secs]
执行结束!共生成对象次数:10357

➜  Week_02 git:(main) ✗ java -XX:+UseG1GC -Xms1g -Xmx1g -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:27:39.887-0800: [GC pause (G1 Evacuation Pause) (young) 63M->22M(1024M), 0.0077779 secs]
2020-10-25T19:27:39.910-0800: [GC pause (G1 Evacuation Pause) (young) 76M->41M(1024M), 0.0061664 secs]
2020-10-25T19:27:39.935-0800: [GC pause (G1 Evacuation Pause) (young) 100M->60M(1024M), 0.0091519 secs]
2020-10-25T19:27:39.970-0800: [GC pause (G1 Evacuation Pause) (young) 144M->90M(1024M), 0.0139309 secs]
2020-10-25T19:27:40.008-0800: [GC pause (G1 Evacuation Pause) (young) 194M->123M(1024M), 0.0113753 secs]
2020-10-25T19:27:40.086-0800: [GC pause (G1 Evacuation Pause) (young) 308M->182M(1024M), 0.0249478 secs]
2020-10-25T19:27:40.142-0800: [GC pause (G1 Evacuation Pause) (young) 326M->226M(1024M), 0.0151961 secs]
2020-10-25T19:27:40.249-0800: [GC pause (G1 Evacuation Pause) (young) 518M->310M(1024M), 0.0348106 secs]
2020-10-25T19:27:40.317-0800: [GC pause (G1 Evacuation Pause) (young) 530M->362M(1024M), 0.0232553 secs]
2020-10-25T19:27:40.393-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 640M->430M(1024M), 0.0279880 secs]
2020-10-25T19:27:40.421-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:40.421-0800: [GC concurrent-root-region-scan-end, 0.0001556 secs]
2020-10-25T19:27:40.421-0800: [GC concurrent-mark-start]
2020-10-25T19:27:40.424-0800: [GC concurrent-mark-end, 0.0026555 secs]
2020-10-25T19:27:40.424-0800: [GC remark, 0.0012661 secs]
2020-10-25T19:27:40.425-0800: [GC cleanup 449M->437M(1024M), 0.0009207 secs]
2020-10-25T19:27:40.426-0800: [GC concurrent-cleanup-start]
2020-10-25T19:27:40.426-0800: [GC concurrent-cleanup-end, 0.0000250 secs]
2020-10-25T19:27:40.480-0800: [GC pause (G1 Evacuation Pause) (young) 742M->489M(1024M), 0.0403386 secs]
2020-10-25T19:27:40.524-0800: [GC pause (G1 Evacuation Pause) (mixed) 512M->416M(1024M), 0.0093497 secs]
2020-10-25T19:27:40.534-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 418M->416M(1024M), 0.0014441 secs]
2020-10-25T19:27:40.535-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:40.536-0800: [GC concurrent-root-region-scan-end, 0.0001189 secs]
2020-10-25T19:27:40.536-0800: [GC concurrent-mark-start]
2020-10-25T19:27:40.537-0800: [GC concurrent-mark-end, 0.0012209 secs]
2020-10-25T19:27:40.537-0800: [GC remark, 0.0015727 secs]
2020-10-25T19:27:40.539-0800: [GC cleanup 428M->428M(1024M), 0.0010419 secs]
2020-10-25T19:27:40.612-0800: [GC pause (G1 Evacuation Pause) (young)-- 847M->635M(1024M), 0.0300348 secs]
2020-10-25T19:27:40.643-0800: [GC pause (G1 Evacuation Pause) (mixed) 641M->584M(1024M), 0.0071066 secs]
2020-10-25T19:27:40.651-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 585M->584M(1024M), 0.0015665 secs]
2020-10-25T19:27:40.652-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:40.652-0800: [GC concurrent-root-region-scan-end, 0.0000843 secs]
2020-10-25T19:27:40.652-0800: [GC concurrent-mark-start]
2020-10-25T19:27:40.654-0800: [GC concurrent-mark-end, 0.0013062 secs]
2020-10-25T19:27:40.654-0800: [GC remark, 0.0013096 secs]
2020-10-25T19:27:40.655-0800: [GC cleanup 593M->587M(1024M), 0.0008681 secs]
2020-10-25T19:27:40.656-0800: [GC concurrent-cleanup-start]
2020-10-25T19:27:40.656-0800: [GC concurrent-cleanup-end, 0.0000152 secs]
2020-10-25T19:27:40.704-0800: [GC pause (G1 Evacuation Pause) (young) 847M->652M(1024M), 0.0093165 secs]
2020-10-25T19:27:40.718-0800: [GC pause (G1 Evacuation Pause) (mixed) 679M->558M(1024M), 0.0062962 secs]
2020-10-25T19:27:40.734-0800: [GC pause (G1 Evacuation Pause) (mixed) 612M->493M(1024M), 0.0078571 secs]
2020-10-25T19:27:40.750-0800: [GC pause (G1 Evacuation Pause) (mixed) 547M->484M(1024M), 0.0061897 secs]
2020-10-25T19:27:40.757-0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 486M->485M(1024M), 0.0020199 secs]
2020-10-25T19:27:40.759-0800: [GC concurrent-root-region-scan-start]
2020-10-25T19:27:40.759-0800: [GC concurrent-root-region-scan-end, 0.0001637 secs]
2020-10-25T19:27:40.759-0800: [GC concurrent-mark-start]
2020-10-25T19:27:40.760-0800: [GC concurrent-mark-end, 0.0010808 secs]
2020-10-25T19:27:40.760-0800: [GC remark, 0.0015180 secs]
2020-10-25T19:27:40.762-0800: [GC cleanup 492M->489M(1024M), 0.0011669 secs]
2020-10-25T19:27:40.763-0800: [GC concurrent-cleanup-start]
2020-10-25T19:27:40.763-0800: [GC concurrent-cleanup-end, 0.0000326 secs]
2020-10-25T19:27:40.815-0800: [GC pause (G1 Evacuation Pause) (young)-- 856M->702M(1024M), 0.0070194 secs]
2020-10-25T19:27:40.824-0800: [GC pause (G1 Evacuation Pause) (mixed) 717M->626M(1024M), 0.0104731 secs]
执行结束!共生成对象次数:11252

➜  Week_02 git:(main) ✗ java -XX:+UseG1GC -Xms2g -Xmx2g -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:27:50.699-0800: [GC pause (G1 Evacuation Pause) (young) 124M->45M(2048M), 0.0148052 secs]
2020-10-25T19:27:50.742-0800: [GC pause (G1 Evacuation Pause) (young) 156M->76M(2048M), 0.0119943 secs]
2020-10-25T19:27:50.776-0800: [GC pause (G1 Evacuation Pause) (young) 190M->117M(2048M), 0.0135559 secs]
2020-10-25T19:27:50.815-0800: [GC pause (G1 Evacuation Pause) (young) 233M->152M(2048M), 0.0133075 secs]
2020-10-25T19:27:50.848-0800: [GC pause (G1 Evacuation Pause) (young) 263M->190M(2048M), 0.0141306 secs]
2020-10-25T19:27:50.903-0800: [GC pause (G1 Evacuation Pause) (young) 343M->232M(2048M), 0.0202389 secs]
2020-10-25T19:27:50.960-0800: [GC pause (G1 Evacuation Pause) (young) 412M->277M(2048M), 0.0208578 secs]
2020-10-25T19:27:51.057-0800: [GC pause (G1 Evacuation Pause) (young) 542M->344M(2048M), 0.0307346 secs]
2020-10-25T19:27:51.126-0800: [GC pause (G1 Evacuation Pause) (young) 553M->390M(2048M), 0.0220842 secs]
2020-10-25T19:27:51.191-0800: [GC pause (G1 Evacuation Pause) (young) 652M->453M(2048M), 0.0274599 secs]
2020-10-25T19:27:51.276-0800: [GC pause (G1 Evacuation Pause) (young) 751M->519M(2048M), 0.0329264 secs]
2020-10-25T19:27:51.441-0800: [GC pause (G1 Evacuation Pause) (young) 990M->626M(2048M), 0.0493785 secs]
2020-10-25T19:27:51.544-0800: [GC pause (G1 Evacuation Pause) (young) 965M->672M(2048M), 0.0237263 secs]
执行结束!共生成对象次数:11097

➜  Week_02 git:(main) ✗ java -XX:+UseG1GC -Xms4g -Xmx4g -XX:+PrintGC -XX:+PrintGCDateStamps GCLogAnalysis
正在执行...
2020-10-25T19:28:25.525-0800: [GC pause (G1 Evacuation Pause) (young) 204M->69M(4096M), 0.0329316 secs]
2020-10-25T19:28:25.590-0800: [GC pause (G1 Evacuation Pause) (young) 247M->133M(4096M), 0.0288948 secs]
2020-10-25T19:28:25.641-0800: [GC pause (G1 Evacuation Pause) (young) 311M->194M(4096M), 0.0302251 secs]
2020-10-25T19:28:25.701-0800: [GC pause (G1 Evacuation Pause) (young) 372M->248M(4096M), 0.0263082 secs]
2020-10-25T19:28:25.758-0800: [GC pause (G1 Evacuation Pause) (young) 426M->311M(4096M), 0.0298794 secs]
2020-10-25T19:28:25.812-0800: [GC pause (G1 Evacuation Pause) (young) 489M->376M(4096M), 0.0305448 secs]
2020-10-25T19:28:25.864-0800: [GC pause (G1 Evacuation Pause) (young) 554M->431M(4096M), 0.0246925 secs]
2020-10-25T19:28:25.912-0800: [GC pause (G1 Evacuation Pause) (young) 609M->488M(4096M), 0.0274131 secs]
2020-10-25T19:28:26.003-0800: [GC pause (G1 Evacuation Pause) (young) 756M->584M(4096M), 0.0477419 secs]
2020-10-25T19:28:26.090-0800: [GC pause (G1 Evacuation Pause) (young) 830M->661M(4096M), 0.0369031 secs]
2020-10-25T19:28:26.171-0800: [GC pause (G1 Evacuation Pause) (young) 963M->749M(4096M), 0.0469944 secs]
2020-10-25T19:28:26.275-0800: [GC pause (G1 Evacuation Pause) (young) 1101M->843M(4096M), 0.0490556 secs]
2020-10-25T19:28:26.398-0800: [GC pause (G1 Evacuation Pause) (young) 1271M->950M(4096M), 0.0586801 secs]
执行结束!共生成对象次数:11062
➜  Week_02 git:(main) ✗
```

```sh
➜  Week_02 git:(main) ✗ java -XX:+UseSerialGC -Xms4g -Xmx4g -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     3.65ms   20.90ms 294.55ms   97.46%
    Req/Sec    25.33k     7.57k   36.35k    73.31%
  1496139 requests in 30.03s, 178.62MB read
Requests/sec:  49813.99
Transfer/sec:      5.95MB
➜  Week_02 git:(main) ✗
```

```
➜  Week_02 git:(main) ✗ java -XX:+UseParallelGC -Xms4g -Xmx4g -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.50ms   12.76ms 249.38ms   97.10%
    Req/Sec    24.77k     7.91k   37.19k    65.05%
  1473843 requests in 30.01s, 175.96MB read
Requests/sec:  49105.27
Transfer/sec:      5.86MB
```

```
➜  Week_02 git:(main) ✗ java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     4.57ms   17.14ms 213.50ms   94.54%
    Req/Sec    22.08k     8.98k   35.73k    63.09%
  1313302 requests in 30.10s, 156.80MB read
Requests/sec:  43631.23
Transfer/sec:      5.21MB
```

```
➜  Week_02 git:(main) ✗ java -XX:+UseG1GC -Xms4g -Xmx4g -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     4.58ms   18.95ms 274.17ms   95.54%
    Req/Sec    19.60k     7.54k   36.39k    68.86%
  1160867 requests in 30.08s, 138.60MB read
Requests/sec:  38593.33
Transfer/sec:      4.61MB
```

```
➜  Week_02 git:(main) ✗ java -XX:+UseSerialGC -Xms512m -Xmx512m -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.00ms   24.10ms 320.30ms   96.20%
    Req/Sec    24.62k     8.49k   44.41k    74.19%
  1448011 requests in 30.07s, 172.88MB read
Requests/sec:  48162.65
Transfer/sec:      5.75MB
```

```
➜  Week_02 git:(main) ✗ java -XX:+UseParallelGC -Xms512m -Xmx512m -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.95ms   13.00ms 237.39ms   96.30%
    Req/Sec    21.41k     7.69k   32.91k    64.31%
  1267111 requests in 30.07s, 151.28MB read
Requests/sec:  42142.64
Transfer/sec:      5.03MB
```

```
➜  Week_02 git:(main) ✗ java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     2.34ms   10.93ms 208.49ms   97.06%
    Req/Sec    24.44k     7.66k   44.33k    71.40%
  1455985 requests in 30.09s, 173.83MB read
Requests/sec:  48394.52
Transfer/sec:      5.78MB
```

```
➜  Week_02 git:(main) ✗ java -XX:+UseG1GC -Xms512m -Xmx512m -jar gateway-server-0.0.1-SNAPSHOT.jar

➜  Week_02 git:(main) ✗ wrk -c40 -d30s  http://localhost:8088/api/hello
Running 30s test @ http://localhost:8088/api/hello
  2 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     3.06ms   12.83ms 216.68ms   96.00%
    Req/Sec    21.29k     7.17k   32.18k    67.56%
  1267925 requests in 30.03s, 151.38MB read
Requests/sec:  42224.86
Transfer/sec:      5.04MB
```

#### GC 实验数据分析

堆区内存越大，GC 次数越少

- SerialGC 平均在 30ms，内存在 4g 时，GC 达到 200ms
- ParallelGC 平均 20ms，内存在 4g 时，GC 达到 150ms
- ConcMarkSweepGC  内存 512m 时，10ms 以内，内存在 4g 时，只触发了 Young 区 ParNew GC 达到 100ms
- G1GC 平均 10 ms，内存在 4g 时，GC 达到 50ms

- SerialGC 压测，竟然也数据优秀
- ParallelGC 压测，吞吐量明显提升
- ConcMarkSweepGC 压测，吞吐量明显提升
- G1GC 压测，吞吐量明显下降

### 周六

```
1、（可选）运行课上的例子，以及Netty的例子，分析相关现象。

2、写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801，代码提交到 github。
```
