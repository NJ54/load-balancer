package summerIntern;

import java.util.*;

public class checkForUnderUtilised 
{
	public boolean isUnderUtilised()
	{
	  
	  CDF cdf = new CDF();	//creating object of cdf class

	  double max_eror;
      double probability;
      Random rand = new Random();
      double Pred_Total_Util = rand.nextInt(25);
      double OverThreshold = 16.00;


      if(Pred_Total_Util >= OverThreshold)
      {
         max_eror = Pred_Total_Util - OverThreshold;
         probability = cdf.CumailativeProbability(max_eror);  //calling cumilative probability from the CDF class 
         probability = (probability + 1)/2;
         probability = 1 - probability;
      }
      else
      {
         max_eror = OverThreshold-Pred_Total_Util;
         probability = cdf.CumailativeProbability(max_eror);
         probability = (probability + 1)/2;         
      }

      probability = probability * 100;
      //System.out.println("probability : "+probability);


      //int random = rand.nextInt(50);
      //System.out.println("random : "+random);
      
      if(100 > probability)
         return true;
      else
         return false; 
	} 
         
}