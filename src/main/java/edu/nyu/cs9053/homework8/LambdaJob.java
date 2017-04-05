package edu.nyu.cs9053.homework8;

public class LambdaJob{
	private final String name;
	private final int startTime;
	private final int finishTime;
	public LambdaJob(String name,int startTime,int finishTime){
		this.name=name;
		this.startTime=startTime;
		this.finishTime=finishTime;
	}
	
	public String getName(){
		return name;
	}
	public int getStartTime(){
		return startTime;
	}
	public int getFinishTime(){
		return finishTime;
	}

}
