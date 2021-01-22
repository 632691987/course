package course.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.perf4j.aop.Profiled;

import course.service.Perf4Service;


/**
 * 

Perf4 的功能：
case1)   简单的停止查看机制来计算语句时间消耗输出。
case2)   命令行解析log文件产生汇总数据和图表。
case3)   简单的集成日志框架和门面框架。
case4)   自定义log4j和logback的appenders来产生数据和图表。
case5)   通过jmx查看性能指标，并根据阈值发送消息。
case6)   Web工程可以通过servlet来输出性能指标。
case7)   Perf4j可以和aop等切面框架整合起来输出性能指标。
case8)   Perf4j是一个可扩展的架构。

 */
@Path("/perf4")
@Produces(MediaType.APPLICATION_JSON)
public class Perf4Resource {
	
	private Perf4Service perf4Service;
	
	public Perf4Resource(Perf4Service perf4Service){
		this.perf4Service = perf4Service;
	}
	
	@GET
	@Path("/case1")
	@Profiled(logFailuresSeparately = false)
	public Response case1() throws InterruptedException{
		perf4Service.testPerf4Case1();
		return Response.accepted().build();
	}
	
	@GET
	@Path("/case2")
	@Profiled(logFailuresSeparately = false)
	public Response case2() throws InterruptedException{
		perf4Service.testPerf4Case2();
		return Response.accepted().build();
	}
	
	@GET
	@Path("/case3")
	@Profiled(logFailuresSeparately = false)
	public Response case3() throws InterruptedException{
		perf4Service.testPerf4Case3();
		return Response.accepted().build();
	}
	
	@GET
	@Path("/case4")
	@Profiled(logFailuresSeparately = false)
	public Response noTag() throws InterruptedException{
		Thread.sleep(1 * 1000);
		return Response.accepted().build();
	}
	
}
