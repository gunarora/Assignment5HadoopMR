import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerUnique extends 
Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
	public void reduce(
			IntWritable userId,
			Iterable<IntWritable> trackIds,
			Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		Set<Integer> userIdSet = new HashSet<Integer>();
		for (IntWritable trackId : trackIds) {
			userIdSet.add(trackId.get());
		}
		IntWritable size = new IntWritable(userIdSet.size());
		context.write(userId, size);
	}
}
