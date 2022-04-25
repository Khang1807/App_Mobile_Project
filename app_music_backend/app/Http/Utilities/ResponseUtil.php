<?php
/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/
namespace App\Http\Utilities;

use \Illuminate\Http\Response;

use App\Http\Utilities\CarbonDateTimeUtil as dateTimeUtil;

class ResponseUtil
{
	private static $contentType = "application/json; charset=utf-8";
	private static $connection = "close";
	private static $keepAlive = "timeout=100, max=10000";
	private static $cacheControl = "no-cache, private";


	public static function respondedSuccess(String $message, $objData = NULL)
	{
		$responseData = array(
			"responseStatus" => Response::HTTP_OK,
			"statusText" => "OK",
			"timestamp" => dateTimeUtil::getNow(),
			"content" => array(
				"message" => $message,
				"datas" => $objData
			)
		);

		return response()->json($responseData, Response::HTTP_OK)
			->header("Cache-Control", self::$cacheControl)
			->header("Connection", self::$connection)
			->header("Content-Type", self::$contentType)
			->header("Keep-Alive", self::$keepAlive);
	}

	public static function respondedBadRequest(String $message, $objData = NULL)
	{
		$responseData = array(
			"responseStatus" => Response::HTTP_EXPECTATION_FAILED,
			"statusText" => "Opp, Something went wrong...!!!",
			"timestamp" => dateTimeUtil::getNow(),
			"content" => array(
				"message" => $message,
				"datas" => $objData
			)
		);

		return response()->json($responseData, Response::HTTP_OK)
			->header("Cache-Control", self::$cacheControl)
			->header("Connection", self::$connection)
			// ->header("Content-Encoding", self::$contentEncoding)
			->header("Content-Type", self::$contentType)
			->header("Keep-Alive", self::$keepAlive);
	}

	public static function respondedNotFound(String $message, $objData = NULL)
	{
		$responseData = array(
			"responseStatus" => Response::HTTP_NOT_FOUND,
			"statusText" => "Opp, Something have not found...!!!",
			"timestamp" => dateTimeUtil::getNow(),
			"content" => array(
				"message" => $message,
				"datas" => $objData
			)
		);

		return response()->json($responseData, Response::HTTP_OK)
			->header("Cache-Control", self::$cacheControl)
			->header("Connection", self::$connection)
			// ->header("Content-Encoding", self::$contentEncoding)
			->header("Content-Type", self::$contentType)
			->header("Keep-Alive", self::$keepAlive);
	}

	public static function respondedError(String $message, $objData = NULL)
	{
		$responseData = array(
			"responseStatus" => Response::HTTP_FORBIDDEN,
			"statusText" => "Server Forbidden!!!",
			"timestamp" => dateTimeUtil::getNow(),
			"content" => array(
				"message" => $message,
				"datas" => $objData
			)
		);

		return response()->json($responseData, Response::HTTP_OK)
			->header("Cache-Control", self::$cacheControl)
			->header("Connection", self::$connection)
			// ->header("Content-Encoding", self::$contentEncoding)
			->header("Content-Type", self::$contentType)
			->header("Keep-Alive", self::$keepAlive);
	}

	public static function respondedAccessExpired()
	{
		$responseData = array(
			"responseStatus" => Response::HTTP_UNAUTHORIZED,
			"statusText" => "Opp, Access has expired, let's login again, plese ...!!!",
			"timestamp" => dateTimeUtil::getNow(),
		);

		return response()->json($responseData, Response::HTTP_OK)
			->header("Cache-Control", self::$cacheControl)
			->header("Connection", self::$connection)
			// ->header("Content-Encoding", self::$contentEncoding)
			->header("Content-Type", self::$contentType)
			->header("Keep-Alive", self::$keepAlive);
	}

	public static function respondedAccessDenied()
	{
		$responseData = array(
			"responseStatus" => Response::HTTP_NOT_ACCEPTABLE,
			"statusText" => "Opp, You're not granted access to our system...!!!",
			"timestamp" => dateTimeUtil::getNow(),
		);

		return response()->json($responseData, Response::HTTP_OK)
			->header("Cache-Control", self::$cacheControl)
			->header("Connection", self::$connection)
			// ->header("Content-Encoding", self::$contentEncoding)
			->header("Content-Type", self::$contentType)
			->header("Keep-Alive", self::$keepAlive);
	}
}
