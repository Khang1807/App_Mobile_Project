<?php

/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/

namespace App\Http\Utilities;

use Illuminate\Support\Carbon;
use Carbon\CarbonTimeZone;
use Illuminate\Support\Facades\Config;

use App\Http\Utilities\LogsUtil as logsUtil;

class CarbonDateTimeUtil
{

	public static function convertToDatetimeString($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));
				return $carbonDate->toDateTimeString();
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return "";
			}
		} else {
			return "";
		}
	}


	public static function formatCarbonDatetime($datetime, $withoutSecond = false)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$tempArrDate = explode("T", $datetime);
				$year = null;
				$month = null;
				$day = null;
				$hour = null;
				$minute = null;
				$second = null;

				if (count($tempArrDate) <= 1) {
					$tempArrDate = explode(" ", $datetime);

					$tempArrDateString = explode("-", $tempArrDate[0]);
					if (count($tempArrDateString) <= 1) {
						$tempArrDateString = explode("/", $tempArrDate[0]);
					}
					$year = $tempArrDateString[0];
					$month = $tempArrDateString[1];
					$day = $tempArrDateString[2];



					if (count($tempArrDate) <= 1) {
						$hour = "00";
						$minute = "00";
						$second = "00";
					} else {
						$tempArrTime = explode(".", $tempArrDate[1]);
						$arrTime = explode(":", $tempArrTime[0]);

						$hour = $arrTime[0];
						$minute = $arrTime[1];
						$second = $arrTime[2];
					}
				} else {
					$tempArrDateString = explode("-", $tempArrDate[0]);
					if (count($tempArrDateString) <= 1) {
						$tempArrDateString = explode("/", $tempArrDate[0]);
					}
					$year = $tempArrDateString[0];
					$month = $tempArrDateString[1];
					$day = $tempArrDateString[2];


					$tempArrTime = explode(".", $tempArrDate[1]);
					$arrTime = explode(":", $tempArrTime[0]);
					$hour = $arrTime[0];
					$minute = $arrTime[1];
					$second = $arrTime[2];
				}

				if ($year && $month && $day && $hour && $minute && $second) {
					$finalDate = Carbon::create($year, $month, $day, $hour, $minute, ($withoutSecond ? 0 : $second), Config::get('app.timezone'));
					$finalDate->setHour($hour);
					$finalDate->setMinute($minute);
					$finalDate->setSecond(($withoutSecond ? 0 : $second));
					return $finalDate;
				}

				return null;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		} else {
			return null;
		}
	}

	public static function getDatetime_From($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				return Carbon::create($datetime->year, $datetime->month, 1);
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		} else {
			return null;
		}
	}

	public static function getDatetime_To($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				return Carbon::create($datetime->year, $datetime->month + 1, 0);
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		} else {
			return null;
		}
	}

	public static function getDay($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));
				return $carbonDate->day;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}

		return null;
	}

	public static function getMonth($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));

				return $carbonDate->month;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}
		return null;
	}

	public static function getYear($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));
				return $carbonDate->year;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}
	}

	public static function getHour($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));
				return $carbonDate->hour;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}
	}

	public static function getMinute($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));

				return $carbonDate->minute;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}
	}

	public static function getSecond($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));

				return $carbonDate->second;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}
	}

	public static function getDayOfWeek($datetime)
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));

				return $carbonDate->dayOfWeek;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}
	}

	public static function getNow()
	{
		try {
			$carbonDate = Carbon::now(Config::get('app.timezone'));
			return $carbonDate;
		} catch (Exception $e) {
			logsUtil::storeLogs('error', $e);
			return null;
		}
		return null;
	}

	public static function getNowString()
	{
		try {
			$carbonDate = Carbon::now(Config::get('app.timezone'));
			return $carbonDate->year . '-' . $carbonDate->month . '-' . $carbonDate->day;
		} catch (Exception $e) {
			logsUtil::storeLogs('error', $e);
			return "";
		}
	}

	public static function getDateString($year = null, $month = null, $day = null)
	{
		try {
			$carbonDate = Carbon::create($year, $month, $day, 0, 0, 0, Config::get('app.timezone'));
			$month = $carbonDate->month;
			return $carbonDate->year . '-' . ($month < 10 ? '0' . $month : $month) . '-' . ($carbonDate->day < 10 ? '0' . $carbonDate->day : $carbonDate->day);
		} catch (Exception $e) {
			logsUtil::storeLogs('error', $e);
			return "";
		}
	}

	public static function setDateTime($year = null, $month = null, $day = null)
	{
		try {
			return Carbon::create($year, $month, $day, 0, 0, 0, Config::get('app.timezone'));
		} catch (Exception $e) {
			logsUtil::storeLogs('error', $e);
			return null;
		}
	}

	public static function setDateTimeByTime($time)
	{
		try {
			return Carbon::createFromTimestamp($time);
		} catch (Exception $e) {
			logsUtil::storeLogs('error', $e);
			return null;
		}
		return null;
	}


	public static function getNextDateTime($datetime, $duration, $type = 'seconds')
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));
				if ($type === "minutes") {
					$carbonDate = $carbonDate->addMinutes($duration);
				} else if ($type === "hours") {
					$carbonDate = $carbonDate->addHours($duration);
				} else if ($type === "days") {
					$carbonDate = $carbonDate->addDays($duration);
				} else if ($type === "weeks") {
					$carbonDate = $carbonDate->addWeeks($duration);
				} else if ($type === "years") {
					$carbonDate = $carbonDate->addYears($duration);
				} else {
					$carbonDate = $carbonDate->addSeconds($duration);
				}
				return $carbonDate;
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		} else {
			return null;
		}
	}

	public static function doCompare($currentDate, $comparsionDate, $type = 'equalTo')
	{
		$result = false;

		if ($type === 'equalTo') {
			$result = $currentDate->equalTo($comparsionDate);
		} else if ($type === 'notEqualTo') {
			$result = $currentDate->notEqualTo($comparsionDate);
		} else if ($type === 'greaterThan') {
			$result = $currentDate->greaterThan($comparsionDate);
		} else if ($type === 'greaterThanOrEqualTo') {
			$result = $currentDate->greaterThanOrEqualTo($comparsionDate);
		} else if ($type === 'lessThan') {
			$result = $currentDate->lessThan($comparsionDate);
		} else if ($type === 'lessThanOrEqualTo') {
			$result = $currentDate->lessThanOrEqualTo($comparsionDate);
		}

		return $result;
	}

	public static function doCompareOnlyDate($currentDate, $comparsionDate, $type = 'equalTo')
	{
		$result = false;

		$currentDate = Carbon::create($currentDate->year, $currentDate->month, $currentDate->day, 0, 0, 0, Config::get('app.timezone'));
		$comparsionDate = Carbon::create($comparsionDate->year, $comparsionDate->month, $comparsionDate->day, 0, 0, 0, Config::get('app.timezone'));

		if ($type === 'equalTo') {
			$result = $currentDate->equalTo($comparsionDate);
		} else if ($type === 'notEqualTo') {
			$result = $currentDate->notEqualTo($comparsionDate);
		} else if ($type === 'greaterThan') {
			$result = $currentDate->greaterThan($comparsionDate);
		} else if ($type === 'greaterThanOrEqualTo') {
			$result = $currentDate->greaterThanOrEqualTo($comparsionDate);
		} else if ($type === 'lessThan') {
			$result = $currentDate->lessThan($comparsionDate);
		} else if ($type === 'lessThanOrEqualTo') {
			$result = $currentDate->lessThanOrEqualTo($comparsionDate);
		}
		return $result;
	}

	public static function convertToDateTimeStringWithFormat($datetime, $format = 'Y-m-d H:i:s.u')
	{
		if ($datetime !== "" && $datetime !== null) {
			try {
				$carbonDate = new Carbon($datetime, Config::get('app.timezone'));
				return $carbonDate->format($format);
			} catch (Exception $e) {
				logsUtil::storeLogs('error', $e);
				return null;
			}
		}
		return null;
	}
}
