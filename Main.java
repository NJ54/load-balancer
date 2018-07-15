package summerIntern;

import java.util.Scanner;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		int noOfHosts,noOfVMs;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter no of hosts : ");	
		noOfHosts = sc.nextInt();
		double over = 90;
		System.out.println("Enter No. of VM's in each host : ");
		noOfVMs = sc.nextInt();
		
		

		checkForOverUtilised check = new checkForOverUtilised();

		//System.out.println();

		int[] Hosts = new int[noOfHosts];   //this array is to mark which of the host is over utilised
		int[] VMs = new int[noOfVMs];		//to check whether each Vm is overloaded or not
		double[] time = new double[noOfHosts];
		int count = 0;
		
		int globalcount = 0;
		double initial = 0.0;
		double[] loadArray = new double[noOfHosts];
		
		ClassifyInstance classify = new ClassifyInstance();
		
		
		/******************		HOST AGENT 		***************************/
		
		for(int i=0;i<noOfHosts;i++)
		{
			System.out.println("Host : "+(i+1));
			globalcount = 0;			
			double avg = 0;
			for(int k=7;k>0;k--)
			{
				count = 0;
				double sum = 0;
				for(int j=0;j<noOfVMs;j++)
				{
					double val = classify.predictFutureload(j,k,i);
					
					//val = check.isOverUtilised(val);
					
					//WRITING THE PREDICTED DATA BACK TO THE FILE TO PREDICT NEXT VALUE
					
					sum = sum + val;
					//System.out.println(" k : "+k+" j : "+j+": val="+val+" sum:"+sum);					
					
				}
				
				System.out.println("Prediction : "+(7-k+1)+" CPU % : "+sum);
				if(sum >= 90)
					globalcount++;
				avg = avg + sum;
				//System.out.println(globalcount);
			}
			
			avg = avg/7;
			loadArray[i] = avg;
			System.out.println("Average workload : "+avg);
			if(globalcount >= 5)
				Hosts[i] = 1;		//if all vms are overutilised then the host is over utilised .. mark it as 1

		}
		
		checkForUnderUtilised under= new checkForUnderUtilised();
 
		int[] checkingUnderUtilised = new int[noOfHosts];   //this array is to check which host is underutilised

		for(int i=0;i<noOfHosts;i++)
		{
			count = 0;
			for(int j=0;j<noOfVMs;j++)
			{
				//if()
				if(Hosts[i] != 1 && under.isUnderUtilised())       //if the host is not over utilised and then check for underutilised  
					checkingUnderUtilised[i] = 1;				  //if it satisfies both the conditions then mark the host as underutilised
					
			}
		}
		
		
		/******************		GLOBAL AGENT 		***************************/
		System.out.println("");
		for(int i=0;i<noOfHosts;i++)
			if(Hosts[i] == 1)
				System.out.println("Host "+(i+1)+" is overloaded");
			else
				System.out.println("Host "+(i+1)+" is not overloaded");

		System.out.println("");
		for(int i=0;i<noOfHosts;i++)
			if(checkingUnderUtilised[i] == 1 )
				System.out.println("Host "+(i+1)+" is Underloaded");
			else
				System.out.println("Host "+(i+1)+" is not Underloaded");

		
		

		int[] completed = new int[noOfHosts];
		
		System.out.println("");
		double finish = Math.random();
		for(int i=0;i<noOfHosts;i++)
		{
			if(Hosts[i] == 1 && completed[i] != 1)		//this means the host is overloaded
			{
				for(int j=0;j<noOfHosts;j++) //this loop is to check the underloaded host among the hosts
				{
					if(checkingUnderUtilised[j] == 1) // this means that the host is underloaded
					{
						System.out.println("Host "+(i+1)+" is overloaded, so moving the workload to Host "+(j+1)+" which is underloaded");
						Hosts[j] =1;
						completed[j] = 1;   //marking the host as completed(utilised)
						completed[i] = 1;	//marking the host as completed(the load is distributed)
						checkingUnderUtilised[j] = 0;
						break;
					}

				}
			}
		}
		initial+=Math.random();
		double[] remain = new double[noOfHosts];
		double[] excess = new double[noOfHosts];
		//System.out.println("load Array...");
		for(int i = 0;i<noOfHosts;i++)
		{
			if(loadArray[i] <= over)
			{
					time[i] = loadArray[i]/10;
					remain[i] = over - loadArray[i];
			}
			else
			{
				time[i] = 9;
				excess[i] = loadArray[i]-over;
			}
		}
			
		
		
		System.out.println("");
		for(int i=0;i<noOfHosts;i++)
		{
			//System.out.println(loadArray[i]+ " " +time[i]);
			
			if(excess[i] > 0)
			{
				
				for(int j=0;j<noOfHosts;j++)
				{
					if(excess[i]>1 && remain[j] > 0 && remain[j]<excess[i])
					{
						
						System.out.println("Moving "+remain[j]+"% load from Host "+(i+1)+" to Host "+(j+1));
						excess[i]-=remain[j];
						remain[j] = 0;
						time[j] = 9;
					}
						
					else if(excess[i]>1 && remain[j] > 0 && remain[j]>excess[i])
					{
						
						System.out.println("Moving "+excess[i]+"% load from Host "+(i+1)+" to Host "+(j+1));
						time[j]+= excess[i]/10;
						remain[j]-=excess[i];
						excess[i] = 0;
						
					}
						
				}
			}
				
		}
		
		for(int i=0;i<noOfHosts;i++)
		{
			if(excess[i] > 0)
			{
				time[i]+= excess[i]/noOfHosts;
				remain[i] = 0;
				excess[i] = 0;
			}
		}
		
		System.out.println("");
		
		for(int i=0;i<noOfHosts;i++)
		{ 
			System.out.println("Host : "+(i+1)+" start time : "+(initial)+" Finish time :" +(time[i]+finish));
			
		}	
		
		
	}
}

