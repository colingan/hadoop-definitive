package com.github.colingan.hadoop.login.v1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.github.colingan.hadoop.login.LoginRecordParser;
import com.github.colingan.hadoop.utils.IntPair;

/**
 * <p>
 * 客户周均登录天数统计的第一个mapper，该mapper输入为每天的客户登录次数数据， 输出key为： userid - week的IntPair对
 * 输出value为：intWritable 记录这个客户，这一周有登录就输出一个 1
 * </p>
 * 
 * @author ganjia
 * 
 */
public class UserLoginTimesPerWeekMapper extends
		Mapper<LongWritable, Text, IntPair, IntWritable> {

	private LoginRecordParser parser = new LoginRecordParser();

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		try {
			parser.parse(value);

			context.write(new IntPair(parser.getUserid(), parser.getWeek()),
					new IntWritable(1));
		} catch (Exception e) {
			// drop invalid record
		}
	}
}
