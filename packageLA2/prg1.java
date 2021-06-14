package packageLA2;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class prg1 {

	//MAPPER CODE	
		   
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	
	// the variable one holds the value 1 in the form of Hadoop's IntWritable Object
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		String val = value.toString();
		String[] dataset = val.split(",");
		if(dataset[5].matches("yes")) {
			output.collect(new Text(dataset[5]), one);
		}
		}
	}

	//REDUCER CODE	
	public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException { 
			int counter = 0;
			while(values.hasNext()) {
				counter += values.next().get();
			}
			
			output.collect(new Text("Employees eligible for pay raise: "), new IntWritable(counter));
		}
	}
		
	
	//DRIVER CODE
	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(prg1.class);
		conf.setJobName("Count the no. of employees eligible for pay raise");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);
		conf.setMapperClass(Map.class);
		conf.setCombinerClass(Reduce.class);
		conf.setReducerClass(Reduce.class);
		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class); // hadoop jar jarname classpath inputfolder outputfolder
		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));
		JobClient.runJob(conf);   
	}
	}

