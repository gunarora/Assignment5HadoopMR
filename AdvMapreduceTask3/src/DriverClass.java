// import required packages
import org.apache.hadoop.fs.Path;

import java.io.IOException;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; 
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

public class DriverClass extends Mapper<LongWritable, Text, Text, NullWritable> {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
Configuration conf=new Configuration(); // create object of Configuration class
		
		@SuppressWarnings("deprecation")
		Job job=new Job(conf, "TVDetailsTask"); // Create job
		
		//job configuration properties
		job.setJarByClass(DriverClass.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);		
		job.setMapperClass(MprClass.class);		
		job.setNumReduceTasks(0);
		//job.setReducerClass(Reducer.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		//FileOutputFormat.setOutputPath(job, new Path(args[1]));
		Path outputPath = new Path(args[1]);
		FileOutputFormat.setOutputPath(job, outputPath);
		outputPath.getFileSystem(conf).delete(outputPath, true);		
		job.waitForCompletion(true);
	}

}
