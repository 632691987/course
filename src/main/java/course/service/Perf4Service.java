package course.service;

import org.perf4j.LoggingStopWatch;
import org.perf4j.StopWatch;
import org.perf4j.log4j.Log4JStopWatch;
import org.springframework.stereotype.Service;

@Service
public class Perf4Service {
	
	/**
	 * 使用stopwatch, lap, 或者是构造函数中写入tag(例如 "codeBlock1")，
	 * 就可以写入到log 文件中的了，然后不管log文件还有没有其他无关重要的内容，都可以
	 * 使用下面的那句话解决：
	 * 
	 * 1) 下面的这一句是用来在 command 中看的：
	 * java -jar perf4j-0.9.16.jar times.log
	 * 
	 * 2) 下面的这一句是用来写到csv文件中的：
	 * java -jar perf4j-0.9.16.jar -f csv times.log
	 * 
	 * 3) 下面的这一句是用来写入到html文件中的：
	 * java -jar perf4j-0.9.16.jar --graph perfGraphs.html times.log
	 * 
	 * @throws InterruptedException
	 */
	public void testPerf4Case1() throws InterruptedException{
		testPerf4Case1Method1();
		testPerf4Case1Method2();
		testPerf4Case1Method3();
	}
	
	/**
	 * 
	 * 1) 保证 testPerf4Case2_middle 出现过四次。并且以stopWatch.start(); 作为testPerf4Case2_middle 结束
	 * 2) 保证testPerf4Case2_finish 出现过一次，已stopWatch.start(); 开始，stopWatch.stop("testPerf4Case2_finish");结束
	 * 
	 * 计算时间是从最上一次的lap 或者 start开始计时的
	 * 
	 */
	public void testPerf4Case2() throws InterruptedException {
		StopWatch stopWatch = new Log4JStopWatch();
		for( int i = 1 ; i < 5 ; i ++ ){
			Thread.sleep((long)(i * 1000L));
			stopWatch.lap("testPerf4Case2_middle");
		}
		Thread.sleep((long)(1 * 1000L));
		stopWatch.stop("testPerf4Case2_finish");
	}
	
	public void testPerf4Case3() throws InterruptedException {
		StopWatch stopWatch = new Log4JStopWatch();
		for( int i = 1 ; i < 5 ; i ++ ){
			Thread.sleep((long)(i * 1000L));
			stopWatch.lap("testPerf4Case3_middle");
		}
		Thread.sleep((long)(1 * 1000L));
		stopWatch.stop("testPerf4Case3_finish");
	}
	
	private void testPerf4Case1Method1() throws InterruptedException{
		StopWatch stopWatch = new LoggingStopWatch("codeBlock1");
		Thread.sleep((long)(Math.random() * 1000L));
		stopWatch.stop();
	}
	
	private void testPerf4Case1Method2() throws InterruptedException{
		//使用了 lap 这个函数，代表没有完
		StopWatch stopWatch = new LoggingStopWatch();
		Thread.sleep((long)(Math.random() * 1000L));
		stopWatch.lap("codeBlock3");
		Thread.sleep((long)(Math.random() * 1000L));
		stopWatch.lap("codeBlock4");
		Thread.sleep((long)(Math.random() * 1000L));
		stopWatch.lap("codeBlock5");
		Thread.sleep((long)(Math.random() * 1000L));
		stopWatch.stop("codeBlock6");
	}
	
	private void testPerf4Case1Method3() throws InterruptedException {
		StopWatch stopWatch = new LoggingStopWatch();
		try {
			while(true){
				long sleepTime = (long) (Math.random() * 1000L);
				Thread.sleep(sleepTime);
				if (sleepTime > 500L) {
					throw new Exception("Throwing exception");
				}
				stopWatch.stop("codeBlock2.success", "Sleep time was < 500 ms");
			}
		} catch (Exception e) {
			stopWatch.stop("codeBlock2.failure", "Exception was: " + e);
		}
	}
	
}
