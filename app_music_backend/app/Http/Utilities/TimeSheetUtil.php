<?php

/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/

namespace App\Http\Utilities;

use App\Http\Utilities\CarbonDateTimeUtil as dateTimeUtil;

class TimeSheetUtil
{
	public static function getWorkingTime($tempFrom, $tempTo)
	{
		$fromtime = strtotime($tempFrom);
		$totime = strtotime($tempTo);
		$workingTime = round((($totime - $fromtime) / 3600.0), 2);

		$lunchTime = self::getLunchTime($tempFrom, $tempTo);

		return ($workingTime - $lunchTime) * 3600.0;
	}

	public static function getStringWorkingTime($tempFrom, $tempTo)
	{
		$workingTime = self::getWorkingTime($tempFrom, $tempTo);
		return  gmdate("H:i", $workingTime);
	}

	public static function getLunchTime($tempFrom, $tempTo)
	{
		$lunchTime = 0;

		$from = dateTimeUtil::formatCarbonDatetime($tempFrom, true);
		$fromTime = strtotime(dateTimeUtil::convertToDatetimeString($from));
		$to = dateTimeUtil::formatCarbonDatetime($tempTo);
		$toTime = strtotime(dateTimeUtil::convertToDatetimeString($to));

		$tempLunchStartTime = dateTimeUtil::formatCarbonDatetime($tempFrom, true);
		$tempLunchStartTime->setHour(12);
		$tempLunchStartTime->setMinute(0);
		$tempLunchStartTime->setSecond(0);
		$lunchStartTime = strtotime(dateTimeUtil::convertToDatetimeString($tempLunchStartTime));

		$tempLunchEndTime = dateTimeUtil::formatCarbonDatetime($tempFrom, true);
		$tempLunchEndTime->setHour(13);
		$tempLunchEndTime->setMinute(0);
		$tempLunchEndTime->setSecond(0);
		$lunchEndTime = strtotime(dateTimeUtil::convertToDatetimeString($tempLunchEndTime));


		if ($from->hour < 12) {
			if ($to->hour < 12) {
				$lunchTime = 0;
			} else if ($to->hour == 12) {
				$lunchTime = ($toTime - $lunchStartTime) / 3600.0;
			} else if ($to->hour <= 24) {
				$lunchTime = 1;
			}
		} else if ($from->hour == 12) {
			if ($to->hour == 12) {
				$lunchTime = ($toTime - $fromTime) / 3600.0;
			} else if ($to->hour >= 13) {
				$lunchTime = ($lunchEndTime - $fromTime) / 3600.0;
			}
		}

		return $lunchTime;
	}

	public static function getExtraTime($workingTime)
	{
		$tempWorkingTime = $workingTime - 28800.0;
		$frac = 1;
		$r = $tempWorkingTime % $frac;
		$roundedTime = $tempWorkingTime + ($frac - $r);
		return $roundedTime <= 0 ? "00:00" : gmdate("H:i", $roundedTime);
	}

	public static function getLackTime($workingTime)
	{
		$tempWorkingTime = 28800.0 - $workingTime;
		$roundedTime = 0;
		if($tempWorkingTime >= 60){
			$frac = 60;
			$r = $tempWorkingTime % $frac;
			$roundedTime = $tempWorkingTime + ($frac - $r);
		}
		return $roundedTime <= 0 ? "00:00" : gmdate("H:i", $roundedTime);
	}
}
