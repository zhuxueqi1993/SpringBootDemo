package test.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MyScheduled {
	/**
	 * Cron表达式参数分别表示：
	 * 秒（0~59） 例如0/5表示每5秒
	 * 分（0~59）
	 * 时（0~23）
	 * 日（0~31）的某天，需计算
	 * 月（0~11）
	 * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
	 */
	@Scheduled(cron = "0 0/5 * * * ?")
	public void SGo() {
		System.out.println("执行定时任务");
	}
}
