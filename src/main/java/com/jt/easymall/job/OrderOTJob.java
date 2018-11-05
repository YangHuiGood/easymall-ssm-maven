package com.jt.easymall.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jt.easymall.mapper.OrderMapper;

public class OrderOTJob extends QuartzJobBean{
	//当前任务执行者需要调用父类的这个方法来实现
	//定时触发任务是执行的代码
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//实现mapper的ot方法的调用
		//当前这个类，是需要配置绑定jobdetail trigger scheduler这些石英钟
		//获取spring的上下文对象applicationContext
		ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
		//用上下文对象获取orderMapper，执行删除超时订单的方法
		Date lastDay = new Date(new Date().getTime()-1000*60*60*24);
		applicationContext.getBean(OrderMapper.class).deleteOTOrders(lastDay);
		
		
	}

}
