import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.*;

public class MapperUnique extends  
Mapper<Object, Text, IntWritable, IntWritable> {
	public class LastFMConstants {
		public static final int USER_ID = 0;
		public static final int TRACK_ID = 1;
		public static final int IS_SHARED = 2;
		public static final int RADIO = 3;
		public static final int IS_SKIPPED = 4;
	}
	private final static IntWritable one = new IntWritable(1);
	IntWritable trackId = new IntWritable();
	IntWritable userId = new IntWritable();

	public void map(Object key, Text value,
			Mapper<Object, Text, IntWritable, IntWritable>.Context context) throws IOException, InterruptedException {
		String[] parts = value.toString().split("[|]");
		trackId.set(Integer.parseInt(parts[LastFMConstants.TRACK_ID]));
		userId.set(Integer.parseInt(parts[LastFMConstants.USER_ID]));
		if (parts.length == 5) {
			context.write(userId, one);
		}

	}
}




