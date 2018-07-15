package summerIntern;
//import required classes
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
//import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMOreg;

public class ClassifyInstance{
		public double predictFutureload(int j,int k,int i) throws Exception
		{
			switch(i)
			{
				case 0:
					Host1 host1 = new Host1();
					return host1.run(j,k);
				case 1:
					Host2 host2 = new Host2();
					return host2.run(j,k);
					//break;
				case 2:
					Host3 host3 = new Host3();
					return host3.run(j,k);
					//break;
				default:
					Host2 host4 = new Host2();
					return host4.run(j,k);		
			}	
		}
}

class Host1 extends Thread
{
	public double run(int j,int k)  throws Exception
	{
		DataSource source = new DataSource("/home/n/testWeka.arff");
		Instances trainDataset = source.getDataSet();	
		//set class index to the last attribute
		trainDataset.setClassIndex(trainDataset.numAttributes()-1);

		//build model
		SMOreg smo = new SMOreg();
		smo.buildClassifier(trainDataset);
		//output model
		//System.out.println(smo);
		DataSource source1;
		switch(j)
		{
		case 0:
			 source1 = new DataSource("/home/n/testUnknownWeka.arff");break;
		case 1:
			 source1 = new DataSource("/home/n/testUnknownWeka2.arff");break;
		case 2:
			 source1 = new DataSource("/home/n/testUnknownWeka3.arff");break;
		default:
			 source1 = new DataSource("/home/n/testUnknownWeka4.arff");
		}
		
		Instances testDataset = source1.getDataSet();	
		//set class index to the last attribute
		testDataset.setClassIndex(testDataset.numAttributes()-1);

		
		double actualValue = testDataset.instance(testDataset.numInstances()-k).classValue();
		Instance newInst = testDataset.instance(testDataset.numInstances()-k);
		double predSMO = smo.classifyInstance(newInst);
		return predSMO;
	}
}	

class Host2 extends Thread
{
	public double run(int j,int k)  throws Exception
	{
		DataSource source = new DataSource("/home/n/testWeka.arff");
		Instances trainDataset = source.getDataSet();	
		//set class index to the last attribute
		trainDataset.setClassIndex(trainDataset.numAttributes()-1);

		//build model
		SMOreg smo = new SMOreg();
		smo.buildClassifier(trainDataset);
		//output model
		//System.out.println(smo);
		DataSource source1;
		switch(j)
		{
		case 0:
			 source1 = new DataSource("/home/n/testUnknownWeka5.arff");break;
		case 1:
			 source1 = new DataSource("/home/n/testUnknownWeka6.arff");break;
		case 2:
			 source1 = new DataSource("/home/n/testUnknownWeka7.arff");break;
		default:
			 source1 = new DataSource("/home/n/testUnknownWeka8.arff");
		}
		
		Instances testDataset = source1.getDataSet();	
		//set class index to the last attribute
		testDataset.setClassIndex(testDataset.numAttributes()-1);

		
		double actualValue = testDataset.instance(testDataset.numInstances()-k).classValue();
		Instance newInst = testDataset.instance(testDataset.numInstances()-k);
		double predSMO = smo.classifyInstance(newInst);
		return predSMO;
	}
}


class Host3 extends Thread
{
	public double run(int j,int k)  throws Exception
	{
		DataSource source = new DataSource("/home/n/testWeka.arff");
		Instances trainDataset = source.getDataSet();	
		//set class index to the last attribute
		trainDataset.setClassIndex(trainDataset.numAttributes()-1);

		//build model
		SMOreg smo = new SMOreg();
		smo.buildClassifier(trainDataset);
		//output model
		//System.out.println(smo);
		DataSource source1;
		switch(j)
		{
		case 0:
			 source1 = new DataSource("/home/n/testUnknownWeka9.arff");break;
		case 1:
			 source1 = new DataSource("/home/n/testUnknownWeka10.arff");break;
		case 2:
			 source1 = new DataSource("/home/n/testUnknownWeka11.arff");break;
		default:
			 source1 = new DataSource("/home/n/testUnknownWeka12.arff");
		}
		
		Instances testDataset = source1.getDataSet();	
		//set class index to the last attribute
		testDataset.setClassIndex(testDataset.numAttributes()-1);

		
		double actualValue = testDataset.instance(testDataset.numInstances()-k).classValue();
		Instance newInst = testDataset.instance(testDataset.numInstances()-k);
		double predSMO = smo.classifyInstance(newInst);
		return predSMO;
	}
}


class Host4 extends Thread
{
	public double run(int j,int k)  throws Exception
	{
		DataSource source = new DataSource("/home/n/testWeka.arff");
		Instances trainDataset = source.getDataSet();	
		//set class index to the last attribute
		trainDataset.setClassIndex(trainDataset.numAttributes()-1);

		//build model
		SMOreg smo = new SMOreg();
		smo.buildClassifier(trainDataset);
		//output model
		//System.out.println(smo);
		DataSource source1;
		switch(j)
		{
		case 0:
			 source1 = new DataSource("/home/n/testUnknownWeka13.arff");break;
		case 1:
			 source1 = new DataSource("/home/n/testUnknownWeka14.arff");break;
		case 2:
			 source1 = new DataSource("/home/n/testUnknownWeka15.arff");break;
		default:
			 source1 = new DataSource("/home/n/testUnknownWeka16.arff");
		}
		
		Instances testDataset = source1.getDataSet();	
		//set class index to the last attribute
		testDataset.setClassIndex(testDataset.numAttributes()-1);

		
		double actualValue = testDataset.instance(testDataset.numInstances()-k).classValue();
		Instance newInst = testDataset.instance(testDataset.numInstances()-k);
		double predSMO = smo.classifyInstance(newInst);
		return predSMO;
	}
}

