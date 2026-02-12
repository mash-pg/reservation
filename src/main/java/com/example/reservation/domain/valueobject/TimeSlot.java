package com.example.reservation.domain.valueobject;

import java.time.LocalDateTime;

import com.example.reservation.domain.exception.InvalidTimeSlotException;

import lombok.Getter;

@Getter
public class TimeSlot {
	private final LocalDateTime startTime;
	private final LocalDateTime endTime;
	
	public TimeSlot(LocalDateTime startTime,LocalDateTime endTime) {
		if(startTime == null || endTime == null) {
			throw new InvalidTimeSlotException("開始時刻または終了時刻がnullです");	
		}
		//beforeは、指定された日付より前にあるかどうかを判定
		if(endTime.isBefore(startTime)) {
			throw new InvalidTimeSlotException(" 終了時刻が開始時刻より前になっています");
		}
		
		
		this.startTime = startTime;
		this.endTime = endTime;
	}

}
