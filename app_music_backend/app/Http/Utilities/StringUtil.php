<?php
/**
 *  @author   Vinh Nguyen <vinh.nguyen2303@outlook.com>
 *  @mobile: 0975578276
 *  @skype: enjoyvinh
 *
 *  @ Copyright Skyfire Solution 2021. All Rights Reserved.
 **/

namespace App\Http\Utilities;

use Str;
use Uuid;

class StringUtil
{
	public static function generateId()
	{
		$result = "sk" . Uuid::generate(5, 'skyfire' .  Str::random(24), Uuid::NS_DNS);

		$result .= Str::random(24);

		return $result;
    }

    public static function generateInvoiceNo()
	{
        $result = "HDSNS";

        $currentDate = getdate();

		$result .= $currentDate["year"] . $currentDate["mon"] . $currentDate["mday"] . $currentDate["0"];

		return $result;
	}

	public static function clearTrailingWhitespace($strMessage, $prefix = '-')
	{

		$result = "";

		if (empty($strMessage)) {
			return $result;
		}

		$result = trim($strMessage);

		$result = preg_replace('/\s+/', $prefix, $result);

		return $result;
	}

	public static function replaceTitle($strMessage, $arrParameter)
	{

		$result = "";

		if (empty($strMessage)) {
			return $result;
		}

		if (count($arrParameter) === 0) {
			$count = substr_count($strMessage, "$");

			for ($i = 0; $i < $count; $i++) {
				$result = str_replace("$" . $i, "", $strMessage);
			}
		} else {
			$i = 0;
			$result = $strMessage;
			foreach ($arrParameter as $value) {
				if (empty($value)) {
					$result = str_replace("$" . $i, "", $result);
				} else {
					$result = str_replace("$" . $i, $value, $result);
				}

				$i++;
			}
		}
		return $result;
	}

	/**
	 * Create a web friendly URL slug from a string.
	 *
	 * Although supported, transliteration is discouraged because
	 *     1) most web browsers support UTF-8 characters in URLs
	 *     2) transliteration causes a loss of information
	 *
	 * @param string $str
	 * @param array $options
	 * @return string
	 */
	public static function slugify($str)
	{

		if (empty($str)) {
			return null;
		}

		// Make sure string is in UTF-8 and strip invalid UTF-8 characters
		$str = mb_convert_encoding((string) $str, 'UTF-8', mb_list_encodings());

		$options = array(
			'delimiter' => '-',
			'limit' => null,
			'lowercase' => true,
			'replacements' => array(),
			'transliterate' => true,
		);

		$char_map = array(
			//arabic
			'أ' => 'a',
			'ب' => 'b',
			'ت' => 't',
			'ث' => 'th',
			'ج' => 'g',
			'ح' => 'h',
			'خ' => 'kh',
			'د' => 'd',
			'ذ' => 'th',
			'ر' => 'r',
			'ز' => 'z',
			'س' => 's',
			'ش' => 'sh',
			'ص' => 's',
			'ض' => 'd',
			'ط' => 't',
			'ظ' => 'th',
			'ع' => 'aa',
			'غ' => 'gh',
			'ف' => 'f',
			'ق' => 'k',
			'ك' => 'k',
			'ل' => 'l',
			'م' => 'm',
			'ن' => 'n',
			'ه' => 'h',
			'و' => 'o',
			'ي' => 'y',
			//austrian
			'Ä' => 'AE',
			'Ö' => 'OE',
			'Ü' => 'UE',
			'ß' => 'sz',
			'ä' => 'ae',
			'ö' => 'oe',
			'ü' => 'ue',
			//azerbaijani
			'Ə' => 'E',
			'Ç' => 'C',
			'Ğ' => 'G',
			'İ' => 'I',
			'Ş' => 'S',
			'Ö' => 'O',
			'Ü' => 'U',
			'ə' => 'e',
			'ç' => 'c',
			'ğ' => 'g',
			'ı' => 'i',
			'ş' => 's',
			'ö' => 'o',
			'ü' => 'u',
			//burmese
			'က' => 'k',
			'ခ' => 'kh',
			'ဂ' => 'g',
			'ဃ' => 'ga',
			'င' => 'ng',
			'စ' => 's',
			'ဆ' => 'sa',
			'ဇ' => 'z',
			'စျ' => 'za',
			'ည' => 'ny',
			'ဋ' => 't',
			'ဌ' => 'ta',
			'ဍ' => 'd',
			'ဎ' => 'da',
			'ဏ' => 'na',
			'တ' => 't',
			'ထ' => 'ta',
			'ဒ' => 'd',
			'ဓ' => 'da',
			'န' => 'n',
			'ပ' => 'p',
			'ဖ' => 'pa',
			'ဗ' => 'b',
			'ဘ' => 'ba',
			'မ' => 'm',
			'ယ' => 'y',
			'ရ' => 'ya',
			'လ' => 'l',
			'ဝ' => 'w',
			'သ' => 'th',
			'ဟ' => 'h',
			'ဠ' => 'la',
			'အ' => 'a',
			'ြ' => 'y',
			'ျ' => 'ya',
			'ွ' => 'w',
			'ြွ' => 'yw',
			'ျွ' => 'ywa',
			'ှ' => 'h',
			'ဧ' => 'e',
			'၏' => '-e',
			'ဣ' => 'i',
			'ဤ' => '-i',
			'ဉ' => 'u',
			'ဦ' => '-u',
			'ဩ' => 'aw',
			'သြော' => 'aw',
			'ဪ' => 'aw',
			'၍' => 'ywae',
			'၌' => 'hnaik',
			'၀' => '0',
			'၁' => '1',
			'၂' => '2',
			'၃' => '3',
			'၄' => '4',
			'၅' => '5',
			'၆' => '6',
			'၇' => '7',
			'၈' => '8',
			'၉' => '9',
			'္' => '',
			'့' => '',
			'း' => '',
			'ာ' => 'a',
			'ါ' => 'a',
			'ေ' => 'e',
			'ဲ' => 'e',
			'ိ' => 'i',
			'ီ' => 'i',
			'ို' => 'o',
			'ု' => 'u',
			'ူ' => 'u',
			'ေါင်' => 'aung',
			'ော' => 'aw',
			'ော်' => 'aw',
			'ေါ' => 'aw',
			'ေါ်' => 'aw',
			'်' => 'at',
			'က်' => 'et',
			'ိုက်' => 'aik',
			'ောက်' => 'auk',
			'င်' => 'in',
			'ိုင်' => 'aing',
			'ောင်' => 'aung',
			'စ်' => 'it',
			'ည်' => 'i',
			'တ်' => 'at',
			'ိတ်' => 'eik',
			'ုတ်' => 'ok',
			'ွတ်' => 'ut',
			'ေတ်' => 'it',
			'ဒ်' => 'd',
			'ိုဒ်' => 'ok',
			'ုဒ်' => 'ait',
			'န်' => 'an',
			'ာန်' => 'an',
			'ိန်' => 'ein',
			'ုန်' => 'on',
			'ွန်' => 'un',
			'ပ်' => 'at',
			'ိပ်' => 'eik',
			'ုပ်' => 'ok',
			'ွပ်' => 'ut',
			'န်ုပ်' => 'nub',
			'မ်' => 'an',
			'ိမ်' => 'ein',
			'ုမ်' => 'on',
			'ွမ်' => 'un',
			'ယ်' => 'e',
			'ိုလ်' => 'ol',
			'ဉ်' => 'in',
			'ံ' => 'an',
			'ိံ' => 'ein',
			'ုံ' => 'on',
			//czech
			'Č' => 'C',
			'Ď' => 'D',
			'Ě' => 'E',
			'Ň' => 'N',
			'Ř' => 'R',
			'Š' => 'S',
			'Ť' => 'T',
			'Ů' => 'U',
			'Ž' => 'Z',
			'č' => 'c',
			'ď' => 'd',
			'ě' => 'e',
			'ň' => 'n',
			'ř' => 'r',
			'š' => 's',
			'ť' => 't',
			'ů' => 'u',
			'ž' => 'z',
			//default
			'0' => '0',
			'1' => '1',
			'2' => '2',
			'3' => '3',
			'4' => '4',
			'5' => '5',
			'6' => '6',
			'7' => '7',
			'8' => '8',
			'9' => '9',
			'°' => '0',
			'¹' => '1',
			'²' => '2',
			'³' => '3',
			'⁴' => '4',
			'⁵' => '5',
			'⁶' => '6',
			'⁷' => '7',
			'⁸' => '8',
			'⁹' => '9',
			'₀' => '0',
			'₁' => '1',
			'₂' => '2',
			'₃' => '3',
			'₄' => '4',
			'₅' => '5',
			'₆' => '6',
			'₇' => '7',
			'₈' => '8',
			'₉' => '9',
			'æ' => 'ae',
			'ǽ' => 'ae',
			'À' => 'A',
			'Á' => 'A',
			'Â' => 'A',
			'Ã' => 'A',
			'Å' => 'AA',
			'Ǻ' => 'A',
			'Ă' => 'A',
			'Ǎ' => 'A',
			'Æ' => 'AE',
			'Ǽ' => 'AE',
			'à' => 'a',
			'á' => 'a',
			'â' => 'a',
			'ã' => 'a',
			'å' => 'aa',
			'ǻ' => 'a',
			'ă' => 'a',
			'ǎ' => 'a',
			'ª' => 'a',
			'@' => 'at',
			'Ĉ' => 'C',
			'Ċ' => 'C',
			'ĉ' => 'c',
			'ċ' => 'c',
			'©' => 'c',
			'Ð' => 'Dj',
			'Đ' => 'D',
			'ð' => 'dj',
			'đ' => 'd',
			'È' => 'E',
			'É' => 'E',
			'Ê' => 'E',
			'Ë' => 'E',
			'Ĕ' => 'E',
			'Ė' => 'E',
			'è' => 'e',
			'é' => 'e',
			'ê' => 'e',
			'ë' => 'e',
			'ĕ' => 'e',
			'ė' => 'e',
			'ƒ' => 'f',
			'Ĝ' => 'G',
			'Ġ' => 'G',
			'ĝ' => 'g',
			'ġ' => 'g',
			'Ĥ' => 'H',
			'Ħ' => 'H',
			'ĥ' => 'h',
			'ħ' => 'h',
			'Ì' => 'I',
			'Í' => 'I',
			'Î' => 'I',
			'Ï' => 'I',
			'Ĩ' => 'I',
			'Ĭ' => 'I',
			'Ǐ' => 'I',
			'Į' => 'I',
			'Ĳ' => 'IJ',
			'ì' => 'i',
			'í' => 'i',
			'î' => 'i',
			'ï' => 'i',
			'ĩ' => 'i',
			'ĭ' => 'i',
			'ǐ' => 'i',
			'į' => 'i',
			'ĳ' => 'ij',
			'Ĵ' => 'J',
			'ĵ' => 'j',
			'Ĺ' => 'L',
			'Ľ' => 'L',
			'Ŀ' => 'L',
			'ĺ' => 'l',
			'ľ' => 'l',
			'ŀ' => 'l',
			'Ñ' => 'N',
			'ñ' => 'n',
			'ŉ' => 'n',
			'Ò' => 'O',
			'Ó' => 'O',
			'Ô' => 'O',
			'Õ' => 'O',
			'Ō' => 'O',
			'Ŏ' => 'O',
			'Ǒ' => 'O',
			'Ő' => 'O',
			'Ơ' => 'O',
			'Ø' => 'OE',
			'Ǿ' => 'O',
			'Œ' => 'OE',
			'ò' => 'o',
			'ó' => 'o',
			'ô' => 'o',
			'õ' => 'o',
			'ō' => 'o',
			'ŏ' => 'o',
			'ǒ' => 'o',
			'ő' => 'o',
			'ơ' => 'o',
			'ø' => 'oe',
			'ǿ' => 'o',
			'º' => 'o',
			'œ' => 'oe',
			'Ŕ' => 'R',
			'Ŗ' => 'R',
			'ŕ' => 'r',
			'ŗ' => 'r',
			'Ŝ' => 'S',
			'Ș' => 'S',
			'ŝ' => 's',
			'ș' => 's',
			'ſ' => 's',
			'Ţ' => 'T',
			'Ț' => 'T',
			'Ŧ' => 'T',
			'Þ' => 'TH',
			'ţ' => 't',
			'ț' => 't',
			'ŧ' => 't',
			'þ' => 'th',
			'Ù' => 'U',
			'Ú' => 'U',
			'Û' => 'U',
			'Ü' => 'U',
			'Ũ' => 'U',
			'Ŭ' => 'U',
			'Ű' => 'U',
			'Ų' => 'U',
			'Ư' => 'U',
			'Ǔ' => 'U',
			'Ǖ' => 'U',
			'Ǘ' => 'U',
			'Ǚ' => 'U',
			'Ǜ' => 'U',
			'ù' => 'u',
			'ú' => 'u',
			'û' => 'u',
			'ü' => 'u',
			'ũ' => 'u',
			'ŭ' => 'u',
			'ű' => 'u',
			'ų' => 'u',
			'ư' => 'u',
			'ǔ' => 'u',
			'ǖ' => 'u',
			'ǘ' => 'u',
			'ǚ' => 'u',
			'ǜ' => 'u',
			'Ŵ' => 'W',
			'ŵ' => 'w',
			'Ý' => 'Y',
			'Ÿ' => 'Y',
			'Ŷ' => 'Y',
			'ý' => 'y',
			'ÿ' => 'y',
			'ŷ' => 'y',
			//esperanto
			'ĉ' => 'cx',
			'ĝ' => 'gx',
			'ĥ' => 'hx',
			'ĵ' => 'jx',
			'ŝ' => 'sx',
			'ŭ' => 'ux',
			'Ĉ' => 'CX',
			'Ĝ' => 'GX',
			'Ĥ' => 'HX',
			'Ĵ' => 'JX',
			'Ŝ' => 'SX',
			'Ŭ' => 'UX',
			//finnish
			'Ä' => 'A',
			'Ö' => 'O',
			'ä' => 'a',
			'ö' => 'o',
			//georgian
			'ა' => 'a',
			'ბ' => 'b',
			'გ' => 'g',
			'დ' => 'd',
			'ე' => 'e',
			'ვ' => 'v',
			'ზ' => 'z',
			'თ' => 't',
			'ი' => 'i',
			'კ' => 'k',
			'ლ' => 'l',
			'მ' => 'm',
			'ნ' => 'n',
			'ო' => 'o',
			'პ' => 'p',
			'ჟ' => 'zh',
			'რ' => 'r',
			'ს' => 's',
			'ტ' => 't',
			'უ' => 'u',
			'ფ' => 'f',
			'ქ' => 'k',
			'ღ' => 'gh',
			'ყ' => 'q',
			'შ' => 'sh',
			'ჩ' => 'ch',
			'ც' => 'ts',
			'ძ' => 'dz',
			'წ' => 'ts',
			'ჭ' => 'ch',
			'ხ' => 'kh',
			'ჯ' => 'j',
			'ჰ' => 'h',
			//german
			'Ä' => 'AE',
			'Ö' => 'OE',
			'Ü' => 'UE',
			'ß' => 'ss',
			'ä' => 'ae',
			'ö' => 'oe',
			'ü' => 'ue',
			//greek
			'ΑΥ' => 'AU',
			'Αυ' => 'Au',
			'ΟΥ' => 'OU',
			'Ου' => 'Ou',
			'ΕΥ' => 'EU',
			'Ευ' => 'Eu',
			'ΕΙ' => 'I',
			'Ει' => 'I',
			'ΟΙ' => 'I',
			'Οι' => 'I',
			'ΥΙ' => 'I',
			'Υι' => 'I',
			'ΑΎ' => 'AU',
			'Αύ' => 'Au',
			'ΟΎ' => 'OU',
			'Ού' => 'Ou',
			'ΕΎ' => 'EU',
			'Εύ' => 'Eu',
			'ΕΊ' => 'I',
			'Εί' => 'I',
			'ΟΊ' => 'I',
			'Οί' => 'I',
			'ΎΙ' => 'I',
			'Ύι' => 'I',
			'ΥΊ' => 'I',
			'Υί' => 'I',
			'αυ' => 'au',
			'ου' => 'ou',
			'ευ' => 'eu',
			'ει' => 'i',
			'οι' => 'i',
			'υι' => 'i',
			'αύ' => 'au',
			'ού' => 'ou',
			'εύ' => 'eu',
			'εί' => 'i',
			'οί' => 'i',
			'ύι' => 'i',
			'υί' => 'i',
			'Α' => 'A',
			'Β' => 'V',
			'Γ' => 'G',
			'Δ' => 'D',
			'Ε' => 'E',
			'Ζ' => 'Z',
			'Η' => 'I',
			'Θ' => 'Th',
			'Ι' => 'I',
			'Κ' => 'K',
			'Λ' => 'L',
			'Μ' => 'M',
			'Ν' => 'N',
			'Ξ' => 'X',
			'Ο' => 'O',
			'Π' => 'P',
			'Ρ' => 'R',
			'Σ' => 'S',
			'Τ' => 'T',
			'Υ' => 'I',
			'Φ' => 'F',
			'Χ' => 'Ch',
			'Ψ' => 'Ps',
			'Ω' => 'O',
			'Ά' => 'A',
			'Έ' => 'E',
			'Ή' => 'I',
			'Ί' => 'I',
			'Ό' => 'O',
			'Ύ' => 'I',
			'Ϊ' => 'I',
			'Ϋ' => 'I',
			'ϒ' => 'I',
			'α' => 'a',
			'β' => 'v',
			'γ' => 'g',
			'δ' => 'd',
			'ε' => 'e',
			'ζ' => 'z',
			'η' => 'i',
			'θ' => 'th',
			'ι' => 'i',
			'κ' => 'k',
			'λ' => 'l',
			'μ' => 'm',
			'ν' => 'n',
			'ξ' => 'x',
			'ο' => 'o',
			'π' => 'p',
			'ρ' => 'r',
			'ς' => 's',
			'σ' => 's',
			'τ' => 't',
			'υ' => 'i',
			'φ' => 'f',
			'χ' => 'ch',
			'ψ' => 'ps',
			'ω' => 'o',
			'ά' => 'a',
			'έ' => 'e',
			'ή' => 'i',
			'ί' => 'i',
			'ό' => 'o',
			'ύ' => 'i',
			'ϊ' => 'i',
			'ϋ' => 'i',
			'ΰ' => 'i',
			'ώ' => 'o',
			'ϐ' => 'v',
			'ϑ' => 'th',
			//hindi
			'अ' => 'a',
			'आ' => 'aa',
			'ए' => 'e',
			'ई' => 'ii',
			'ऍ' => 'ei',
			'ऎ' => 'ऎ',
			'ऐ' => 'ai',
			'इ' => 'i',
			'ओ' => 'o',
			'ऑ' => 'oi',
			'ऒ' => 'oii',
			'ऊ' => 'uu',
			'औ' => 'ou',
			'उ' => 'u',
			'ब' => 'B',
			'भ' => 'Bha',
			'च' => 'Ca',
			'छ' => 'Chha',
			'ड' => 'Da',
			'ढ' => 'Dha',
			'फ' => 'Fa',
			'फ़' => 'Fi',
			'ग' => 'Ga',
			'घ' => 'Gha',
			'ग़' => 'Ghi',
			'ह' => 'Ha',
			'ज' => 'Ja',
			'झ' => 'Jha',
			'क' => 'Ka',
			'ख' => 'Kha',
			'ख़' => 'Khi',
			'ल' => 'L',
			'ळ' => 'Li',
			'ऌ' => 'Li',
			'ऴ' => 'Lii',
			'ॡ' => 'Lii',
			'म' => 'Ma',
			'न' => 'Na',
			'ङ' => 'Na',
			'ञ' => 'Nia',
			'ण' => 'Nae',
			'ऩ' => 'Ni',
			'ॐ' => 'oms',
			'प' => 'Pa',
			'क़' => 'Qi',
			'र' => 'Ra',
			'ऋ' => 'Ri',
			'ॠ' => 'Ri',
			'ऱ' => 'Ri',
			'स' => 'Sa',
			'श' => 'Sha',
			'ष' => 'Shha',
			'ट' => 'Ta',
			'त' => 'Ta',
			'ठ' => 'Tha',
			'द' => 'Tha',
			'थ' => 'Tha',
			'ध' => 'Thha',
			'ड़' => 'ugDha',
			'ढ़' => 'ugDhha',
			'व' => 'Va',
			'य' => 'Ya',
			'य़' => 'Yi',
			'ज़' => 'Za',
			//latvian
			'Ā' => 'A',
			'Ē' => 'E',
			'Ģ' => 'G',
			'Ī' => 'I',
			'Ķ' => 'K',
			'Ļ' => 'L',
			'Ņ' => 'N',
			'Ū' => 'U',
			'ā' => 'a',
			'ē' => 'e',
			'ģ' => 'g',
			'ī' => 'i',
			'ķ' => 'k',
			'ļ' => 'l',
			'ņ' => 'n',
			'ū' => 'u',
			//norwegian
			'Æ' => 'AE',
			'Ø' => 'OE',
			'Å' => 'AA',
			'æ' => 'ae',
			'ø' => 'oe',
			'å' => 'aa',
			//polish
			'Ą' => 'A',
			'Ć' => 'C',
			'Ę' => 'E',
			'Ł' => 'L',
			'Ń' => 'N',
			'Ó' => 'O',
			'Ś' => 'S',
			'Ź' => 'Z',
			'Ż' => 'Z',
			'ą' => 'a',
			'ć' => 'c',
			'ę' => 'e',
			'ł' => 'l',
			'ń' => 'n',
			'ó' => 'o',
			'ś' => 's',
			'ź' => 'z',
			'ż' => 'z',
			//russian
			'Ъ' => '',
			'Ь' => '',
			'А' => 'A',
			'Б' => 'B',
			'Ц' => 'C',
			'Ч' => 'Ch',
			'Д' => 'D',
			'Е' => 'E',
			'Ё' => 'E',
			'Э' => 'E',
			'Ф' => 'F',
			'Г' => 'G',
			'Х' => 'H',
			'И' => 'I',
			'Й' => 'Y',
			'Я' => 'Ya',
			'Ю' => 'Yu',
			'К' => 'K',
			'Л' => 'L',
			'М' => 'M',
			'Н' => 'N',
			'О' => 'O',
			'П' => 'P',
			'Р' => 'R',
			'С' => 'S',
			'Ш' => 'Sh',
			'Щ' => 'Shch',
			'Т' => 'T',
			'У' => 'U',
			'В' => 'V',
			'Ы' => 'Y',
			'З' => 'Z',
			'Ж' => 'Zh',
			'ъ' => '',
			'ь' => '',
			'а' => 'a',
			'б' => 'b',
			'ц' => 'c',
			'ч' => 'ch',
			'д' => 'd',
			'е' => 'e',
			'ё' => 'e',
			'э' => 'e',
			'ф' => 'f',
			'г' => 'g',
			'х' => 'h',
			'и' => 'i',
			'й' => 'y',
			'я' => 'ya',
			'ю' => 'yu',
			'к' => 'k',
			'л' => 'l',
			'м' => 'm',
			'н' => 'n',
			'о' => 'o',
			'п' => 'p',
			'р' => 'r',
			'с' => 's',
			'ш' => 'sh',
			'щ' => 'shch',
			'т' => 't',
			'у' => 'u',
			'в' => 'v',
			'ы' => 'y',
			'з' => 'z',
			'ж' => 'zh',
			//swedish
			'Ä' => 'A',
			'Å' => 'a',
			'Ö' => 'O',
			'ä' => 'a',
			'å' => 'a',
			'ö' => 'o',
			//turkish
			'Ç' => 'C',
			'Ğ' => 'G',
			'İ' => 'I',
			'Ş' => 'S',
			'Ö' => 'O',
			'Ü' => 'U',
			'ç' => 'c',
			'ğ' => 'g',
			'ı' => 'i',
			'ş' => 's',
			'ö' => 'o',
			'ü' => 'u',
			//ukrainian
			'Ґ' => 'G',
			'І' => 'I',
			'Ї' => 'Ji',
			'Є' => 'Ye',
			'ґ' => 'g',
			'і' => 'i',
			'ї' => 'ji',
			'є' => 'ye',
			//vietnamese
			'ạ' => 'a',
			'ả' => 'a',
			'ầ' => 'a',
			'ấ' => 'a',
			'ậ' => 'a',
			'ẩ' => 'a',
			'ẫ' => 'a',
			'ằ' => 'a',
			'ắ' => 'a',
			'ặ' => 'a',
			'ẳ' => 'a',
			'ẵ' => 'a',
			'ẹ' => 'e',
			'ẻ' => 'e',
			'ẽ' => 'e',
			'ề' => 'e',
			'ế' => 'e',
			'ệ' => 'e',
			'ể' => 'e',
			'ễ' => 'e',
			'ị' => 'i',
			'ỉ' => 'i',
			'ọ' => 'o',
			'ỏ' => 'o',
			'ồ' => 'o',
			'ố' => 'o',
			'ộ' => 'o',
			'ổ' => 'o',
			'ỗ' => 'o',
			'ờ' => 'o',
			'ớ' => 'o',
			'ợ' => 'o',
			'ở' => 'o',
			'ỡ' => 'o',
			'ụ' => 'u',
			'ủ' => 'u',
			'ừ' => 'u',
			'ứ' => 'u',
			'ự' => 'u',
			'ử' => 'u',
			'ữ' => 'u',
			'ỳ' => 'y',
			'ỵ' => 'y',
			'ỷ' => 'y',
			'ỹ' => 'y',
			'Ạ' => 'A',
			'Ả' => 'A',
			'Ầ' => 'A',
			'Ấ' => 'A',
			'Ậ' => 'A',
			'Ẩ' => 'A',
			'Ẫ' => 'A',
			'Ằ' => 'A',
			'Ắ' => 'A',
			'Ặ' => 'A',
			'Ẳ' => 'A',
			'Ẵ' => 'A',
			'Ẹ' => 'E',
			'Ẻ' => 'E',
			'Ẽ' => 'E',
			'Ề' => 'E',
			'Ế' => 'E',
			'Ệ' => 'E',
			'Ể' => 'E',
			'Ễ' => 'E',
			'Ị' => 'I',
			'Ỉ' => 'I',
			'Ọ' => 'O',
			'Ỏ' => 'O',
			'Ồ' => 'O',
			'Ố' => 'O',
			'Ộ' => 'O',
			'Ổ' => 'O',
			'Ỗ' => 'O',
			'Ờ' => 'O',
			'Ớ' => 'O',
			'Ợ' => 'O',
			'Ở' => 'O',
			'Ỡ' => 'O',
			'Ụ' => 'U',
			'Ủ' => 'U',
			'Ừ' => 'U',
			'Ứ' => 'U',
			'Ự' => 'U',
			'Ử' => 'U',
			'Ữ' => 'U',
			'Ỳ' => 'Y',
			'Ỵ' => 'Y',
			'Ỷ' => 'Y',
			'Ỹ' => 'Y',

		);

		// Make custom replacements
		$str = preg_replace(array_keys($options['replacements']), $options['replacements'], $str);

		// Transliterate characters to ASCII
		if ($options['transliterate']) {
			$str = str_replace(array_keys($char_map), $char_map, $str);
		}

		// Replace non-alphanumeric characters with our delimiter
		$str = preg_replace('/[^\p{L}\p{Nd}]+/u', $options['delimiter'], $str);

		// Remove duplicate delimiters
		$str = preg_replace('/(' . preg_quote($options['delimiter'], '/') . '){2,}/', '$1', $str);

		// Truncate slug to max. characters
		$str = mb_substr($str, 0, ($options['limit'] ? $options['limit'] : mb_strlen($str, 'UTF-8')), 'UTF-8');

		// Remove delimiter from ends
		$str = trim($str, $options['delimiter']);

		return $options['lowercase'] ? mb_strtolower($str, 'UTF-8') : $str;
	}
}
