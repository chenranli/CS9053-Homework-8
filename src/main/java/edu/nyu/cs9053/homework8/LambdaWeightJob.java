package edu.nyu.cs9053.homework8;

public class LambdaWeightJob extends LambdaJob{
	private final int weight;
	public LambdaWeightJob(String name,int startTime,int finishTime,int weight){
		super(name,startTime,finishTime);
		this.weight=weight;
	}
	public int getWeight(){
		return weight;
	}
}
