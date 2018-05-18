//import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.NullWritable;
//import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class UniqueListners {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		Configuration conf=new Configuration(); // create object of Configuration class
		if (args.length != 2) {
			System.err.println("Usage: uniquelisteners <in> <out>");
			System.exit(2);
		}
		
		//@SuppressWarnings("deprecation")
		@SuppressWarnings("deprecation")
		Job job=new Job(conf, "Unique Listners"); // Create job
		
		//job configuration properties
		job.setJarByClass(UniqueListners.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(MapperUnique.class);
		
		//job.setNumReduceTasks(0);
		
		job.setReducerClass(ReducerUnique.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
	    System.exit(job.waitForCompletion(true) ? 0 : 1);

	    
	    //Customized
	    //org.apache.hadoop.mapreduce.Counters counters = job.getCounters();
	    //System.out.println("No. of Invalid Records :"+ counters.findCounter(COUNTERS.INVALID_RECORD_COUNT1).getValue());
	}

}
