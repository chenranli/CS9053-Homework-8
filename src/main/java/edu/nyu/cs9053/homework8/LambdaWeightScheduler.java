package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @param jobArray input job array
 * @param container a linked list that contains selected jobs.
 * @param p p[j] is the largest index i(which is smaller than j), such that job i is compatible with j
 */

public class LambdaWeightScheduler{
	private final ArrayList<? extends LambdaWeightJob> jobArray;
	private final Collection<LambdaWeightJob> container;
	//p[j] is the largest index i(which is smaller than j), such that job i is compatible with j.
	private final int[] p;
	
	public LambdaWeightScheduler(ArrayList<? extends LambdaWeightJob> jobArray){
		this.jobArray=jobArray;
		this.container=new LinkedList<LambdaWeightJob>();
		this.p=new int[jobArray.size()];
		Arrays.fill(p, -1);
	}
	
	/**
	 * @return the linked list that contains selected jobs
	 */
	public Collection<LambdaWeightJob> weightScheduler(){
		validJobArray(jobArray);//check input
		jobSort(jobArray);//sort job array.
		setP();//set value for array p.
		System.out.println("The largest cost is:"+computeOpt(jobArray.size()-1,jobArray,p));
		findJob(jobArray,p.length-1);//find jobs we should select.
		return container;
	}
	
	
	//sort job array according to finish time in ascending order.
	private static void jobSort(ArrayList<? extends LambdaWeightJob> jobArray){
		LambdaScheduler.jobSort(jobArray);
		
	}
	
	//helper method.set p value for each element in jobArray.
	private void setP(){
		for(int i=0;i<p.length;i++){
            for(int j=i-1;j>=0;j--){
                if(jobArray.get(i).getStartTime()>=jobArray.get(j).getFinishTime()){
                    p[i] = j;
                    break;
                }
            }
        }
	}
	
	/**
	 * @param index our current position in job array
	 * @param jobArray input job array
	 * @param p p[j] is the largest index i(which is smaller than j), such that job i is compatible with j
	 * 
	 * We compute optimal schedule using recursion,
	 * determining whether or not current job should be selected
	 */
	private static int computeOpt(int index,ArrayList<? extends LambdaWeightJob> jobArray,int[] p) {
		if(index==-1){
			return 0;
		}
		else{
			return Math.max(jobArray.get(index).getWeight()+computeOpt(p[index],jobArray,p),computeOpt(index-1,jobArray,p));
		}
	}
	
	
	/**
	 * @param jobArray input job array
	 * @param index the index of current job in job array. 
	 * We determine whether or not current job is selected from tail to start.
	 */
	private void findJob(ArrayList<? extends LambdaWeightJob> jobArray,int index){
		if(index==-1){
			return;
		}
		if(jobArray.get(index).getWeight()+computeOpt(p[index],jobArray,p)>computeOpt(index-1,jobArray,p)){
			container.add(jobArray.get(index));//add selected job to container.
			findJob(jobArray,p[index]);
		}
		else{
			findJob(jobArray,index-1);
		}
		
	}
	
	//check input
	private static void validJobArray(ArrayList<? extends LambdaJob> jobArray){
		if(jobArray==null||jobArray.size()==0){
			throw new NullPointerException("Job list cannot be null!");
		}
	}
	
}
