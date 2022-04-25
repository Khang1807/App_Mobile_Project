<?php

/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/

namespace App\Http\Utilities;

use Illuminate\Support\Facades\Mail;

use App\Http\Utilities\CarbonDateTimeUtil as dateTimeUtil;
use App\Http\Utilities\LogsUtil as logsUtil;


use App\Mail\App\CandidateForgotPasswordEmail;
use App\Mail\App\CandidateAccountActivationEmail;

use App\Mail\AppPlus\CompanyAccountActivationEmail;
use App\Mail\AppPlus\CompanyForgotPasswordEmail;

use App\Mail\System\AdminForgotPasswordEmail;

class MailUtil
{
	public static function doSendMail($mailName, $toEmail, $mailData, $options = [])
	{
		//init variables
		$sendToAdmin = false;
		$sendCcAdmin = false;
		$sendBccAdmin = false;
		$lazySend = false;
		$lazyDuration = 1;
		$ccEmails = array();
		$bccEmails = array();

		if (array_key_exists('sendToAdmin', $options)) {
			$sendToAdmin = $options['sendToAdmin'];
		}
		if (array_key_exists('sendCcAdmin', $options)) {
			$sendCcAdmin = $options['sendCcAdmin'];
		}
		if (array_key_exists('sendBccAdmin', $options)) {
			$sendBccAdmin = $options['sendBccAdmin'];
		}
		if (array_key_exists('lazySend', $options)) {
			$lazySend = $options['lazySend'];
		}
		if (array_key_exists('lazyDuration', $options)) {
			$lazyDuration = $options['lazyDuration'];
		}
		if (array_key_exists('ccEmails', $options)) {
			$ccEmails = $options['ccEmails'];
		}
		if (array_key_exists('bccEmails', $options)) {
			$bccEmails = $options['bccEmails'];
		}

		try {
			$plannedSend = dateTimeUtil::getNow();
			if ($lazySend) {
				$plannedSend = dateTimeUtil::getNextDateTime($plannedSend, $lazyDuration, 'minute');
			}

			$mailTemplate = self::setMailTemplate($mailName, $mailData);

			if ($mailTemplate && $toEmail) {
				//Send To Customer
				$mail = Mail::to($toEmail);
				if (count($ccEmails) > 0) {
					$mail::cc($ccEmails);
				}
				if (count($bccEmails) > 0) {
					$mail::bcc($bccEmails);
				}
				// Sent
				$mail->later($plannedSend, $mailTemplate);
				if ($sendToAdmin) {

					$mailAdminTemplate = self::setMailTemplate($mailName, $mailData);
					$plannedSendToAdmin = dateTimeUtil::getNextDateTime($plannedSend, $lazyDuration + 5, 'minute');

					if ($mailAdminTemplate) {
						//Send To Admin
						$mailAdmin = Mail::to(config('mail.admin_to.address'), config('mail.admin_to.name'));

						$arrCcMails = array();
						$arrBccMails = array();

						for ($i = 1; $i <= 5; $i++) {
							if ($sendCcAdmin) {
								if (config('mail.admin_cc' . $i . '_to.address')) {
									array_push($arrCcMails, config('mail.admin_cc' . $i . '_to.address'));
								}
							}

							if ($sendBccAdmin) {
								if (config('mail.admin_bcc' . $i . '_to.address')) {
									array_push($arrBccMails, config('mail.admin_bcc' . $i . '_to.address'));
								}
							}
						}

						if (count($arrCcMails) > 0) {
							$mailAdmin->cc($arrCcMails);
						}
						if (count($arrBccMails) > 0) {
							$mailAdmin->bcc($arrBccMails);
						}

						$mailAdmin->later($plannedSendToAdmin, $mailAdminTemplate);
					}
				}
			}
		} catch (Exception $e) {
			logsUtil::storeLogs('error', $e);
		}
	}

	private static function setMailTemplate($mailName, $mailData)
	{
		if ($mailName === 'doCandidateRegister') {
			return new CandidateAccountActivationEmail($mailData);
		}
		if ($mailName === 'doCandidateForgotPassword') {
			return new CandidateForgotPasswordEmail($mailData);
		}
		if ($mailName === 'doCompanyRegister') {
			return new CompanyAccountActivationEmail($mailData);
		}
		if ($mailName === 'doCompanyForgotPassword') {
			return new CompanyForgotPasswordEmail($mailData);
		}

		if ($mailName === 'doAdminForgotPassword') {
			return new AdminForgotPasswordEmail($mailData);
		}



		/** ...... More Template */
		return null;
	}
}
