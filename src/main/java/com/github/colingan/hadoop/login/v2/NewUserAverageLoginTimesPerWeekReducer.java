package com.github.colingan.hadoop.login.v2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.github.colingan.hadoop.login.Constants;
import com.github.colingan.hadoop.utils.IntPair;

public class NewUserAverageLoginTimesPerWeekReducer extends
		Reducer<IntWritable, IntPair, IntWritable, IntWritable> {

	@Override
	protected void reduce(IntWritable key, Iterable<IntPair> values,
			Context context) throws IOException, InterruptedException {
		Map<Integer, Integer> sumMap = new HashMap<Integer, Integer>(
				Constants.WEEKS_NUM);
		for (IntPair value : values) {
			Integer sum = sumMap.get(value.getFirst());
			if (sum == null) {
				sumMap.put(value.getFirst(), value.getSecond());
			} else {
				sumMap.put(value.getFirst(), sum + value.getSecond());
			}
		}

		int total = 0;
		for (Entry<Integer, Integer> entry : sumMap.entrySet()) {
			total += entry.getValue();
		}

		context.write(
				key,
				new IntWritable((int) Math.ceil((double) total
						/ Constants.WEEKS_NUM)));
	}
}
