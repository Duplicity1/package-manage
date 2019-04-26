package com.fxdigital.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * data工具类
 */
public class DateUtils {
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// private final static SimpleDateFormat sdfYearMonth = new
	// SimpleDateFormat("yyyy-MM");

	private final static SimpleDateFormat SDFSIX = new SimpleDateFormat("yyMMdd");

	/** yyyy-MM-dd HH:mm:ss */
	public static String ptn_yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd */
	public static String ptn_yyyyMMdd = "yyyy-MM-dd";
	/** HH:mm:ss */
	public static String ptn_HHmmss = "HH:mm:ss";

	/** yyyy.MM.dd */
	public static String ptn_yyyyPMMPdd = "yyyy.MM.dd";
	/** MM.dd */
	public static String ptn_MMPdd = "MM.dd";
	/** yyyy年MM月 */
	public static String ptn_yyyyMM_c = "yyyy年MM月";
	/** MM月dd日 */
	public static String ptn_MMdd_c = "MM月dd日";
	/** yyyyMMddHHmmss */
	public static String ptn_yyyyMMddHHmmss_No_Seprator = "yyyyMMddHHmmss";

	/** yyyy年MM月dd日HH:mm:ss */
	public static String ptn_yyyyMMddHHmmss = "yyyy年MM月dd日HH:mm:ss";
	/** yyyy年MM月dd日HH:mm */
	public static String ptn_yyyyMMddHHmm = "yyyy年MM月dd日HH:mm";
	/** MM月dd日HH:mm */
	public static String ptn_MMddHHmm = "MM月dd日HH:mm";
	/** M月dd日HH:mm */
	public static String ptn_MddHHmm = "M月dd日HH:mm";
	/** H:mm */
	public static String ptn_Hmm = "H:mm";

	/** H:mm */
	public static String ptn_HHmm = "HH:mm";

	/**
	 * 获取YYMMDD格式
	 * 
	 * @return
	 */
	public static String getSix() {
		return SDFSIX.format(new Date());
	}

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取MM格式
	 * 
	 * @return
	 */
	public static String getMonth() {
		return sdfMonth.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	public static String getDay(long time) {
		return sdfDay.format(new Date(time));
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	public static String getTime(long time) {
		return sdfTime.format(new Date(time));
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             JMAG
	 */
	public static boolean compareDate(String s, String e) {
		if (formatDate(s) == null || formatDate(e) == null) {
			return false;
		}
		return formatDate(s).getTime() >= formatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @Method: formatDate
	 * @Description: TODO
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date) {
		DateFormat fmt = new SimpleDateFormat(ptn_yyyyMMdd);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title: compareDatetime
	 * @Description: TODO(日期时间比较，根据type相应 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @param type
	 *            (1:大于等于 2:大于)
	 * @return boolean
	 * @throws @author
	 *             JMAG
	 */
	public static boolean compareDatetime(String s, String e, int type) {
		if (s == null || e == null)
			return false;
		if (formatDatetime(s) == null || formatDatetime(e) == null) {
			return false;
		}
		if (type == 2) {
			return formatDatetime(s).getTime() > formatDatetime(e).getTime();
		}
		return formatDatetime(s).getTime() >= formatDatetime(e).getTime();
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             JMAG
	 */
	public static long compareDatetime2(String s, String e) {
		if (formatDatetime(s) == null || formatDatetime(e) == null) {
			return -1;
		}
		return formatDatetime(s).getTime() - formatDatetime(e).getTime();
	}

	/**
	 * 格式化日期时间
	 * 
	 * @Method: fomatDatetime
	 * @Description: TODO
	 * @param date
	 * @return
	 */
	public static Date formatDatetime(String date) {
		DateFormat fmt = new SimpleDateFormat(ptn_yyyyMMdd_HHmmss);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 校验日期是否合法
	 * 
	 * @Method: isValidDate
	 * @Description: TODO
	 * @param s
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * 比较前后时间
	 * 
	 * @Method: getDiffYear
	 * @Description: TODO
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// long aa = 0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * 时间相减得到天数
	 * 
	 * @Method: getDaySub
	 * @Description: TODO
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 获取指定天数后星期几
	 * 
	 * @Method: getWeekByASpecifiedDays
	 * @Description: TODO
	 * @param number
	 * @return
	 */
	public static String getWeekByASpecifiedDays(int number) {

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, number); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	/**
	 * 获取当前时间字符串
	 * 
	 * @Method: getNowStr
	 * @Description: TODO
	 * @param datePattern
	 * @return
	 */
	public static String getNowStr(String datePattern) {
		if (StringUtils.isEmpty(datePattern))
			datePattern = ptn_yyyyMMdd_HHmmss;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			Date dt = new Date();
			return sdf.format(dt);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 字符串转日期
	 * 
	 * @Method: strToDate
	 * @Description: TODO
	 * @param dateStr
	 * @param datePattern
	 * @return
	 */
	public static Date strToDate(String dateStr, String datePattern) {
		if (StringUtils.isEmpty(datePattern))
			datePattern = ptn_yyyyMMdd_HHmmss;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * 日期转字符串
	 * 
	 * @Method: dateToStr
	 * @Description: TODO
	 * @param date
	 * @param datePattern
	 * @return
	 */
	public static String dateToStr(Date date, String datePattern) {
		if (StringUtils.isEmpty(datePattern))
			datePattern = ptn_yyyyMMdd_HHmmss;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取指定数月1号
	 * 
	 * @Method: getDay1BySpecifiedMonths
	 * @Description: TODO
	 * @param number
	 * @return
	 */
	public static String getDay1BySpecifiedMonths(int number) {

		Calendar canlendar = Calendar.getInstance();
		canlendar.set(Calendar.DAY_OF_MONTH, 1);
		canlendar.add(Calendar.MONTH, number);
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 获取当前日期到指定N天的日期
	 * 
	 * @Method: getMonthDateBySpecified
	 * @Description: TODO
	 * @param number
	 * @return
	 */
	public static String getDateBySpecifiedDays(int number) {

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.DATE, number);
		return sdfTime.format(calendar.getTime());
	}

	/**
	 * 获取当前日期到指定N个月的日期
	 * 
	 * @Method: getMonthDateBySpecified
	 * @Description: TODO
	 * @param number
	 * @return
	 */
	public static String getDateBySpecifiedMonths(int number) {

		Calendar calendar = Calendar.getInstance();

		calendar.add(Calendar.MONTH, number);
		return sdfTime.format(calendar.getTime());
	}

	/**
	 * 获取时间戳
	 * 
	 * @Method: getTimestamp
	 * @Description: TODO
	 * @return
	 */
	public static String getTimestamp() {

		return String.valueOf(System.currentTimeMillis() / 1000);

	}

	/**
	 * 获取今天星期几
	 * 
	 * @Method: dayForWeek
	 * @Description: TODO
	 * @return
	 */
	public static int dayForWeek() {
		Date today = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 格式化日期
	 * 
	 * @Method: formatDatetime
	 * @Description: TODO
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date formatDatetime(String date, String format) {
		DateFormat fmt = new SimpleDateFormat(format);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 返回加SECs时间
	 * 
	 * @Method: resultTimeByAddMins
	 * @Description: TODO
	 * @param date
	 * @param mins
	 * @return
	 */
	public static String resultTimeByAddSecs(Date date, int secs) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, secs);
		return sdfTime.format(c.getTime());
	}

	/**
	 * 返回加分钟时间
	 * 
	 * @Method: resultTimeByAddMins
	 * @Description: TODO
	 * @param date
	 * @param mins
	 * @return
	 */
	public static String resultTimeByAddMins(Date date, int mins) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, mins);
		return sdfTime.format(c.getTime());
	}

	/**
	 * 返回加天数时间
	 * 
	 * @Method: resultTimeByAddDay
	 * @Description: TODO
	 * @param date
	 * @param day
	 * @return
	 */
	public static String resultTimeByAddDay(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, day);
		return sdfTime.format(c.getTime());
	}


	public static void main(String args[]) {

		System.out.println(resultTimeByAddSecs(new Date(), 10));

	}
}
