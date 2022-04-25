<?php
/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/

namespace App\Http\Utilities;


class HttpUtil
{
	public static function get_client_ip()
    {
        $ipaddress = '';
        if (isset($_SERVER['HTTP_CLIENT_IP']))
            $ipaddress = $_SERVER['HTTP_CLIENT_IP'];
        else if (isset($_SERVER['HTTP_X_FORWARDED_FOR']))
            $ipaddress = $_SERVER['HTTP_X_FORWARDED_FOR'];
        else if (isset($_SERVER['HTTP_X_FORWARDED']))
            $ipaddress = $_SERVER['HTTP_X_FORWARDED'];
        else if (isset($_SERVER['HTTP_FORWARDED_FOR']))
            $ipaddress = $_SERVER['HTTP_FORWARDED_FOR'];
        else if (isset($_SERVER['HTTP_FORWARDED']))
            $ipaddress = $_SERVER['HTTP_FORWARDED'];
        else if (isset($_SERVER['REMOTE_ADDR']))
            $ipaddress = $_SERVER['REMOTE_ADDR'];
        else
            $ipaddress = null;

         //Get IP máy client
         if (($ipaddress === "::1" || $ipaddress === "127.0.0.1" || $ipaddress === "localhost") || empty($ipaddress)) {
            $ipaddress = "127.0.0.1";
        }
        return $ipaddress;
    }
}
