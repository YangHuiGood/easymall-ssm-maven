package com.jt.easymall.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jt.easymall.mapper.OrderMapper;

public class OrderOTJob extends QuartzJobBean{
	//��ǰ����ִ������Ҫ���ø�������������ʵ��
	//��ʱ����������ִ�еĴ���
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		//ʵ��mapper��ot�����ĵ���
		//��ǰ����࣬����Ҫ���ð�jobdetail trigger scheduler��ЩʯӢ��
		//��ȡspring�������Ķ���applicationContext
		ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap().get("applicationContext");
		//�������Ķ����ȡorderMapper��ִ��ɾ����ʱ�����ķ���
		Date lastDay = new Date(new Date().getTime()-1000*60*60*24);
		applicationContext.getBean(OrderMapper.class).deleteOTOrders(lastDay);
		
		
	}

}
