<?php

/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/

namespace App\Http\Utilities;

use Illuminate\Support\Facades\Log;

use App\Http\Utilities\CarbonDateTimeUtil as dateTimeUtil;

class LogsUtil
{
	public static function storeLogs($level = 'error', $anyValue = null)
	{
		$currentDate = dateTimeUtil::getNow();

		$logValue = $anyValue;

		if(is_object($anyValue)){
			$logValue = strval($anyValue);
		} else {
			$logValue = json_encode($anyValue);
		}

		Log::log($level, '' . config('app.name') . ' | ' . $currentDate . '\n ```' . $logValue . '```');
	}
}
