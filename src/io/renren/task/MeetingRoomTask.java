package io.renren.task;

import io.renren.service.CahgMeetingRoomService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("meetingRoomTask")
public class MeetingRoomTask {
	@Autowired
	private CahgMeetingRoomService cahgMeetingRoomService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void meetingRoomUnuse() {
		//logger.info("我是meetingRoomUnuse方法，正在被执行");
		try {
			int total = cahgMeetingRoomService.queryRoomUsable();
			if (total >= 1) {
				cahgMeetingRoomService.updateRoomUsable();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
