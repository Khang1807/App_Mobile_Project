<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;
use Illuminate\Database\Eloquent\Model;
class Music extends Model
{
    

    protected $table = 'music';
	protected $primaryKey = 'musicId';
	// public $timestamps = false;

	// protected $casts = [
	// 	'candidate_id' => 'int',
	// 	'edu_qualification_id' => 'int',
	// 	'created_user' => 'int',
	// 	'updated_user' => 'int',
	// 	'status' => 'int'
	// ];

	// protected $dates = [
	// 	'edu_started_date',
	// 	'edu_graduated_date',
	// 	'created_date',
	// 	'updated_date'
	// ];

	protected $fillable = [
		'musicId',
		'musicName',
		'rating',
        'playlistId',
        'categoryId',
		'artistId',
		'imgUrl',
        'linkUrl',
		'liked'
	];

	
}
