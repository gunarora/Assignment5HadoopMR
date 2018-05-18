// import packages
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import org.apache.hadoop.io.*;

public class MprClass extends Mapper<LongWritable, Text, Text, NullWritable> {
	@Override
	// overriding map method
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException 
	{
		String[] lineArray = value.toString().split("\\|"); // split on the basis of | delimiter
		String Sharedsong=lineArray[2]; // assigning value to companyName
		Text output ; // creating output variable of type Text
		//System.out.println(companyName + " " + productName);
		
			if(Sharedsong.equals("1")) // check whether companyName or productName is "NA"
			{
				output = value; // assigning the value to output
				context.write(output,NullWritable.get()); // writing the key,value to context
			}
	} 	 	
}
