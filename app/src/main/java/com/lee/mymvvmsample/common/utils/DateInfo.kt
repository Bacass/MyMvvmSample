package com.lee.mymvvmsample.common.utils

import java.text.SimpleDateFormat
import java.util.*

open class DateInfo {
    open fun systemTimeToDate(time: Long): Date {
        return Date(time)
    }

    open fun dateToSystemTime(date: Date): Long {
        return date.time
    }

    open fun dateToStringDate(date: Date): String {
        var transFormat = SimpleDateFormat("yyyy년MM월dd일")

        var result = transFormat.format(date)

        return result
    }

    open fun dateToStringTime(date: Date): String {
        var transFormat = SimpleDateFormat("a h:mm")

        var result = transFormat.format(date)

        result.replace("AM", "오전")
        result.replace("PM", "오후")

        return result
    }

    open fun getTodayTime(): String {
        var transFormat = SimpleDateFormat("yyyyMMdd HH:mm:ss")

        return transFormat.format(Date())
    }

    open fun getTodayDate04(): String {
        var transFormat = SimpleDateFormat("YYYY-MM-dd")
        return transFormat.format(Date())
    }

    open fun getTodayTime02(): String {
        var transFormat = SimpleDateFormat("YYYYMMddhhmmss")

        return transFormat.format(Date())
    }

    open fun getTodayTime03(): String {
        var transFormat = SimpleDateFormat("YYYY.MM.dd")

        return transFormat.format(Date())
    }

    open fun getTodayDate(): String {
        var transFormat = SimpleDateFormat("YYYYMMdd")
        return transFormat.format(Date())
    }

    /**
     * 임의의 날짜를 만들기 위해 사용.
     * hour 입력시 주의
     * 오전/오후 확실히 해서 입력해야 함.
     */
    open fun makeDate(
        year: Int,
        month: Int,
        date: Int,
        hour: Int,
        minute: Int,
        isAm: Boolean,
    ): Date {
        var cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month - 1) // 0 ~ 11 월
        cal.set(Calendar.DATE, date)
        cal.set(Calendar.HOUR, hour)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.AM_PM, if (isAm) 0 else 1) // 0:오전, 1:오후

        return Date(cal.timeInMillis)
    }

    /**
     * 오늘 날짜 비교
     * 전달받은 date 가 오늘인지를 확인한다.
     */
    open fun isToday(date: Date): Boolean {
        val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val currentTime = Date()
        var today = mSimpleDateFormat.format(currentTime) // 오늘날짜
//        //Log.d("DateInfo", "오늘날짜 today : $today")

        var param = mSimpleDateFormat.format(date) // 전달받은날짜
//        //Log.d("DateInfo", "전달받은날짜 param : $param")

        if (today.equals(param)) {
            return true
        }

        return false
    }

    open fun isYesterday(date: Date): Boolean {
        val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val yesterdate = Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000))

        var date01 = mSimpleDateFormat.format(yesterdate) // 오늘날짜
//        //Log.d("DateInfo", "어제날짜 date01 : $date01")

        var param = mSimpleDateFormat.format(date) // 전달받은날짜
//        //Log.d("DateInfo", "전달받은날짜 param : $param")

        if (date01.equals(param)) {
            return true
        }

        return false
    }

    open fun stringToDate(str: String): Date {
        val dt = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        return dt.parse(str)
    }

    open fun getToday(): Date {
        return Date()
    }

    /**
     * 현재 시간과 전달받은 날짜가 며칠 차이 나는지 체크한다.
     * basicDays : 날짜 차이
     */
    open fun getDates(
        date: String,
        basicDays: Int,
    ): Boolean {
        var daysValue = (1000 * 60 * 60 * 24) // 하루를 long으로 변환

        var writeDate = DateInfo().stringToDate(date) // 공지작성일

        var writeDateValue = writeDate.time // 공지작성일을 long으로 변환
        var todayValue = DateInfo().getToday().time // 지금 시간

        var isNew: Boolean = true

        var days = ((todayValue - writeDateValue) / daysValue)

        if (days > basicDays) {
            isNew = false
        }

        return isNew
    }

    /**
     * Int 타입을 mm:ss 로 표시
     */
    open fun getIntToTime(param: Int): String {
        var minute = param / 60 % 60
        var second = param % 60
        var mMinute = if (minute < 10) "0$minute" else minute.toString()
        var mSecond = if (second < 10) "0$second" else second.toString()

        return "$mMinute:$mSecond"
    }

    /**
     * 비디오/이미지파일에서 시간값을 얻어내기 위한 처리.
     * VID_19700107_022111.mp4 를 long 형의 시간값으로 변환한다.
     */
    open fun getFileToCreateTime(fileName: String): Long {
        val dt = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.KOREA)
        var date = dt.parse(fileName)

        // Timber.d("Real fileName: $fileName, time: ${date.time}, date:${dateToStringDate(systemTimeToDate(date.time))}")
        return date.time
    }
}
