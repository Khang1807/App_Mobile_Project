<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;
use Illuminate\Database\Eloquent\Model;
class Playlist extends Model
{
    

    protected $table = 'playlist';
	protected $primaryKey = 'playlistId';
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
		'score',
		'nameOfPlaylist',
		'playlistImg',
	];

	
}
