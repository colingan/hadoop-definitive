package com.github.colingan.hadoop.login.v1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.github.colingan.hadoop.utils.IntPair;

/**
 * <p>
 * 客户周均登录天数第一个reducer，跟v1.UserLoginTimesPerWeekMapper配合使用， 对Mapper的每一个输出，进行sum操作
 * </p>
 * 
 * @author ganjia
 * 
 */
public class UserLoginTimesPerWeekReducer extends
		Reducer<IntPair, IntWritable, IntPair, IntWritable> {

	@Override
	protected void reduce(IntPair key, Iterable<IntWritable> values,
			Context context) throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : values) {
			sum += value.get();
		}

		context.write(key, new IntWritable(sum));
	}

}
