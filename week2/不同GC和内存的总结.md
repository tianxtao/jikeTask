#### 不同GC和内存的总结：

1. 一般的，串行GC和并行GC，堆内存中年轻代约占1/3，老年代约占2/3，但是CMS和G1不一定。
2. 创建对象数量随着内存的大小 逐渐变大进而变小
3. 堆内存设置约小时，GC越频繁，甚至直接内存溢出。堆内存设置越大，GC次数越少，在测试程序里甚至不会出现Full GC，但相对应的每次GC的暂停时间比较长。
4. 串行GC，在gc日志可以看到，DefNew标识是一次young gc，Tenured标识一次old gc。发生full gc时 ，可能会同时清理young 区和old区，也可能只清理了old区。
5. 并行GC，在GC日志可以看到，PSYoungGen标识一次young gc，ParOldGen标识一次old gc。full gc发生时，会将young区清零，old区清理一部分。可以理解，发生full gc时，将young区存活的对象都提升到老年代，老年代也将不可达对象清理。
6. 同等内存大小情况下 串行gc 时间 几乎是 并行gc的二倍；
7. CMS GC, 在gc日志可以看到，在刚开始清理时，使用串行的ParNew gc开始清理，随后会有初始标记，并发标记等阶段性的处理日志，并且在阶段性处理日志中间还夹杂ParNew gc的清理日志。可以得出结论，young区使用串行的ParNew gc，老年代使用分阶段处理的CMS GC清理。在初始标记和最终清理有时间记录，说明这两个阶段会有stw暂停。
8.  通过相同参数的压测，相同堆内存下，CMS的吞吐量好于其他的gc。
9. G1 GC, 在日志可以看到，在开始记录的都是young区对象的压缩，并且有暂停时间，这时候也就是在年轻代模式转移暂停阶段。当young区的对象所占内存接近设置的总堆内存的45%时，开始有初始标记，root区扫描，并行标记的阶段性处理日志。再次标记之后，是清理阶段，清理阶段会有STW暂停。初始标记、root扫描、并发清理，再次标记，清理这几个阶段都有短暂的暂停时间记录，也说明并发清理阶段并不是完全并发的。在清理后或者再次标记后，也会出现young区的转移暂停和混合模式的转移暂停。