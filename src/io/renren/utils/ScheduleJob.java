package io.renren.utils;

import io.renren.entity.ScheduleJobEntity;
import io.renren.entity.ScheduleJobLogEntity;
import io.renren.service.ScheduleJobLogService;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务
 * 
 * @author 
 * @email
 * @date
 */
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	/*说明：
	 * Java通过Executors提供四种线程池
	 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	 * newSingleThreadExecutor() 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
	 */
	private ExecutorService service = Executors.newSingleThreadExecutor();

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context
				.getMergedJobDataMap().get(ScheduleJobEntity.JOB_PARAM_KEY);

		// 获取spring bean
		ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils
				.getBean("scheduleJobLogService");

		// 数据库保存执行记录
		ScheduleJobLogEntity log = new ScheduleJobLogEntity();
		log.setJobId(scheduleJob.getJobId());
		log.setBeanName(scheduleJob.getBeanName());
		log.setMethodName(scheduleJob.getMethodName());
		log.setParams(scheduleJob.getParams());
		log.setCreateTime(new Date());

		// 任务开始时间
		long startTime = System.currentTimeMillis();

		try {
			// 执行任务
			logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId()); // 定时任务方法执行前
			ScheduleRunnable task = new ScheduleRunnable(
					scheduleJob.getBeanName(), scheduleJob.getMethodName(),
					scheduleJob.getParams());
			Future<?> future = service.submit(task);

			future.get();

			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int) times);
			// 任务状态 0：成功 1：失败
			log.setStatus(0);

			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时："
					+ times + "毫秒"); // 定时任务方法执行后
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);

			// 任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int) times);

			// 任务状态 0：成功 1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		} finally {
			scheduleJobLogService.save(log);
		}
	}
}
