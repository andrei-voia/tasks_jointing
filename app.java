abstract class Tasks
{
	public abstract int execute();
	public abstract String toString();
}


//SIMPLE TASK
//
//
class SimpleTask extends Tasks
{
	private int executionTime;
	
	public SimpleTask(int executionTime)
	{
		this.executionTime=executionTime;
	}
	
	public int execute()
	{
		return executionTime;
	}
	
	public String toString()
	{
		return "SimpleTask("+executionTime+")";
	}
	
	public void changeTime(int changeTime)
	{
		executionTime=changeTime;
	}
}


//CONDITIONAL TASK
//
//
class ConditionalTask extends Tasks
{
	private Tasks t1, t2;
	
	public ConditionalTask(Tasks t1, Tasks t2)
	{
		this.t1=t1;
		this.t2=t2;
	}
	
	public int execute()
	{
		int milli=5;

		if((int)(Math.random()*2)==0) return milli + t1.execute();
		return milli + t2.execute();
	}
	
	public String toString()
	{
		return "ConditionalTask("+t1.toString()+","+t2.toString()+")";
	}
}


//BLOCK TASK
//
//
class BlockTask extends Tasks
{
	private Tasks[] task;
	private int taskNumber=0;
	private static final int MAX=10;
	
	public BlockTask()
	{
		task = new Tasks[MAX];
	}
	
	public int execute()
	{
		int milli=0;
		
		for(int i=0;i<taskNumber;i++) milli+=task[i].execute();
			
		return milli;
	}
	
	public String toString()
	{
		String string="BlockTask(";
		
		for(int i=0;i<taskNumber;i++) string+=task[i].toString();
		string+=")";
		
		return string;
	}
	
	public void insert(Tasks addTask)
	{
		if(taskNumber==task.length)
		{
			Tasks[] add = new Tasks[task.length+MAX];
			for(int i=0;i<task.length;i++) add[i]=task[i];
			task=add;
		}
		
		this.task[taskNumber]=addTask;
		taskNumber++;
	}
}


class Main
{
	public static void main(String args[])
	{
		SimpleTask s1 = new SimpleTask(155);
		SimpleTask s2 = new SimpleTask(5);
		SimpleTask s3 = new SimpleTask(4);
		BlockTask b1 = new BlockTask();
		b1.insert(s2);
		b1.insert(s3);
		ConditionalTask c1 = new ConditionalTask(s1,b1);

		System.out.println(c1.toString());
	}
}