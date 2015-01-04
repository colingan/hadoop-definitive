package com.github.colingan.hadoop.login;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.Text;

public class LoginRecordParser {

	private int week;
	private int userid;

	private Calendar cal = Calendar.getInstance();
	private SimpleDateFormat format = new SimpleDateFormat(
			Constants.DATE_FORMAT);

	public void parse(String line) throws InvalidRecordException {
		try {
			String[] tokens = line.split(Constants.SPLITTER);
			Date d = format.parse(tokens[0]);
			cal.setTime(d);
			// -1 date, cause week starts from Sunday.
			cal.add(Calendar.DATE, -1);

			week = cal.get(Calendar.WEEK_OF_YEAR);

			userid = Integer.parseInt(tokens[1]);
		} catch (Exception e) {
			throw new InvalidRecordException(line, e);
		}
	}

	public void parse(Text record) throws InvalidRecordException {
		this.parse(record.toString());
	}

	public int getWeek() {
		return week;
	}

	public int getUserid() {
		return userid;
	}
}
