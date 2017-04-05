package edu.nyu.cs9053.homework8;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

public class LambdaScheduler {
	private final ArrayList<? extends LambdaJob> jobArray;
	
	public LambdaScheduler(ArrayList<? extends LambdaJob> jobArray){
		this.jobArray=jobArray;
	}
	
	//find the maximum compatible container
	public Collection<LambdaJob> scheduler(){
		//check input.
		validJobArray(jobArray);
		//sort job array according to finish time in ascending order.
		jobSort(jobArray);
		//find and return the maximum container.
		return findMaxSet(jobArray);
	}
	
	
	//helper method.Use a linked list to represent the maximum container.
	private static Collection<LambdaJob> findMaxSet(ArrayList<? extends LambdaJob> jobArray){
		Collection<LambdaJob> container=new LinkedList<>();
		int finishTime=-1;
		for(LambdaJob job:jobArray){
			if(job.getStartTime()>=finishTime){
				finishTime=job.getFinishTime();
				container.add(job);
			}
		}
		return container;
	}
	
	//helper method.sort job array according to finish time in ascending order.
	public static void jobSort(ArrayList<? extends LambdaJob> jobArray){
		jobArray.sort(new Comparator<LambdaJob>(){
			@Override public int compare(LambdaJob first,LambdaJob second){
				return first.getFinishTime()-second.getFinishTime();
			}
		});
	}
	
	//check input.
	private static void validJobArray(ArrayList<? extends LambdaJob> jobArray){
		if(jobArray==null||jobArray.size()==0){
			throw new NullPointerException("Job list cannot be null!");
		}
	}
	
}
