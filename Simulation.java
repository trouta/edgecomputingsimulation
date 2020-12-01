
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Simulation {
	
	//FORM LEVEL VARIABLES
	
	// dataRange is the range of random values, 0 to dataRange
	// dataAdditive is the minimum offset
	// ex: a dataRange of 50 and a dataAdditive of 25 yields a range of 25-75
	private static int dataRange = 50;
	private static int dataAdditive = 25;
	
	// frequencyRange is the range of random values, 0 to frequencyRange
	// frequencyAdditive is the minimum offset
	// ex: a frequencyRange of 50 and a frequencyAdditive of 50 yields a range of 50-100
	private static int frequencyRange = 50;
	private static int frequencyAdditive = 50;
	
	// edgeRange is the range of random values, 0 to edgeRange
	// edgeAdditive is the minimum offset
	// ex: a edgeRange of 200 and a edgeAdditive of 40 yields a range of 40-240
	private static int edgeRange = 200;
	private static int edgeAdditive = 40;
	
	// THE FOLLOWING VARIABLES ARE THE ONES YOU CHANGE FOR THE SIMULATION
	
	// cloudDivider is what you divide the edge bandwidth by to receive the cloud bandwidth
	// ex: with an edge bandwidth of 200 and a cloudDivider of 50, the cloud bandwidth would be 4
	private static double cloudDivider = 50.0;
	
	// numDevices is the number of devices in the system
	private static int numDevices = 250;
	
	// edgeStorageCapacity is the storage capacity in GB of the edge server
	private static double edgeStorageCapacity = 4125;
	
	// decimalPlaces is occasionally used in this program to round printed doubles to a set number of decimal places
	private static int decimalPlaces = 3;
		
	// GOAL METRICS
	
	// outputAccesses is the total data access frequency to the edge server
	private static double outputAccesses = 0;
	
	// outputEdgeTime is the time required to process data in the edge
	private static double outputEdgeTime = 0;
	
	// outputMaxUploadTime is the time required to process all data sources' uploads
	// hypothetically, this should always be the time required in the cloud (except for abnormal cases)
	private static double outputMaxUploadTime = 0;
	
	// outputNumEdgeDevices is the number of devices chosen to upload to the edge server
	private static double outputNumEdgeDevices = 0;
	
	// dataSizes is an array of numDevices length that contains all data sizes (1 per data source)
	private static double[] dataSizes = new double[numDevices];
	
	// frequencies is an array of numDevices length that contains all frequencies (1 per data source)
	private static double[] frequencies = new double[numDevices];
	
	// edgeBandwidths is an array of numDevices length that contains all edge bandwidths (1 per data source)
	private static double[] edgeBandwidths = new double[numDevices];
	
	// cloudBandwidths is an array of numDevices length that contains all cloud bandwidths (1 per data source)
	private static double[] cloudBandwidths = new double[numDevices];
	
	// edgeDevices is an ArrayList that holds all devices that are chosen to upload to the edge in a solution
	static ArrayList<String> edgeDevices = new ArrayList<String>();
	
	// cloudDevices is an ArrayList that holds all devices that are chosen to upload to the cloud in a solution
	static ArrayList<String> cloudDevices = new ArrayList<String>();
	
	// allDevices is an ArrayList that holds all devices in the system
	static ArrayList<Device> allDevices = new ArrayList<Device>();
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ROUND 1*************************************************************************************
			// createArrays procedure creates each devices and adds every device to the allDevices ArrayList,
			// as well as generates each metric for each device
			createArrays();
					
			//GOAL 1, MAXIMIZE TOTAL SUPPORTED DATA ACCESS FREQUENCY IN EDGE SERVER
			// run procedure solutionOne, passing in dataSizes and frequencies arrays
			solutionOne(dataSizes,frequencies);
			System.out.print("\nSolution1");
			// display output
			printData();
			// clear necessary arrays and variables for the next solution to begin
			clearData();
			
			//GOAL 2, MINIMIZE TOTAL UPLOADING TIME
			System.out.print("\nSolution2");
			// run procedure solutionTwo, passing in dataSizes, edgeBandwidths, and cloudBandwidths arrays
			solutionTwo(dataSizes,edgeBandwidths,cloudBandwidths);
			// display output
			printData();
			// clear necessary arrays and variables for the next solution to begin
			clearData();
					
			//GOAL 3, MAXIMIZE THE NUMBER OF DATA SOURCES' DATA STORED IN EDGE
			System.out.print("\nSolution3");
			// run procedure solutionThree, passing in dataSizes array
			solutionThree(dataSizes);
			// display output
			printData();
			// clear necessary arrays and variables for the next solution to begin
			clearData();
			
			// print out a few blank lines to make data easier to read
			System.out.print("\n\n\n");
		//ROUND 2*************************************************************************************
				// createArrays procedure creates each devices and adds every device to the allDevices ArrayList,
				// as well as generates each metric for each device
				createArrays();
						
				//GOAL 1, MAXIMIZE TOTAL SUPPORTED DATA ACCESS FREQUENCY IN EDGE SERVER
				// run procedure solutionOne, passing in dataSizes and frequencies arrays
				solutionOne(dataSizes,frequencies);
				System.out.print("\nSolution1");
				// display output
				printData();
				// clear necessary arrays and variables for the next solution to begin
				clearData();
				
				//GOAL 2, MINIMIZE TOTAL UPLOADING TIME
				System.out.print("\nSolution2");
				// run procedure solutionTwo, passing in dataSizes, edgeBandwidths, and cloudBandwidths arrays
				solutionTwo(dataSizes,edgeBandwidths,cloudBandwidths);
				// display output
				printData();
				// clear necessary arrays and variables for the next solution to begin
				clearData();
						
				//GOAL 3, MAXIMIZE THE NUMBER OF DATA SOURCES' DATA STORED IN EDGE
				System.out.print("\nSolution3");
				// run procedure solutionThree, passing in dataSizes array
				solutionThree(dataSizes);
				// display output
				printData();
				// clear necessary arrays and variables for the next solution to begin
				clearData();
				
				// print out a few blank lines to make data easier to read
				System.out.print("\n\n\n");
				
		//ROUND 3*************************************************************************************
				// createArrays procedure creates each devices and adds every device to the allDevices ArrayList,
				// as well as generates each metric for each device
				createArrays();
						
				//GOAL 1, MAXIMIZE TOTAL SUPPORTED DATA ACCESS FREQUENCY IN EDGE SERVER
				solutionOne(dataSizes,frequencies);
				System.out.print("\nSolution1");
				// display output
				printData();
				// clear necessary arrays and variables for the next solution to begin
				clearData();
				
				//GOAL 2, MINIMIZE TOTAL UPLOADING TIME
				System.out.print("\nSolution2");
				// run procedure solutionTwo, passing in dataSizes, edgeBandwidths, and cloudBandwidths arrays
				solutionTwo(dataSizes,edgeBandwidths,cloudBandwidths);
				// display output
				printData();
				// clear necessary arrays and variables for the next solution to begin
				clearData();
						
				//GOAL 3, MAXIMIZE THE NUMBER OF DATA SOURCES' DATA STORED IN EDGE
				System.out.print("\nSolution3");
				// run procedure solutionThree, passing in dataSizes array
				solutionThree(dataSizes);
				// display output
				printData();
				// clear necessary arrays and variables for the next solution to begin
				clearData();
		
	}//end main
	
	//SLN 1
	public static void solutionOne(double[] inputData, double[] inputFrequencies){
		// metric is an array of type double and size numDevices
		double[] metric = new double[numDevices];
		
		// dataStored is a double that adds the quantity of data sizes being stored in the edge server
		double dataStored = 0;
		
		// devNum is an integer to keep track of what device number we're looking at
		int devNum = 0;
		
		// bd is of the BigDecimal class and is used to round values
		BigDecimal bd;
		
		// totalAccesses is a sum of the total data access frequency to the edge server
		double totalAccesses = 0;
		
		// timeEdge is the cumulative processing time the edge requires to upload data source data
		double timeEdge = 0;
		
		// timeCloud is the cumulative processing time the cloud requires to upload data source data 
		double timeCloud = 0;
		
		// loop to run through all devices
		for(int i = 0;i<numDevices;i++){
			// create metric value of data size divided by frequency
			metric[i]=inputData[i]/inputFrequencies[i];
		}
		
		// sort the metric in ascending order (smallest to largest)
		Arrays.sort(metric);
		
		// loop to run through all devices
		for(int i = 0;i<numDevices;i++){
			// after we created the metric, we then lost the device number (which is the index in allDevices)
			// now, we find the device name for data storage purposes
			
			// loop to run through the number of total devices
			for (int j = 0;j<numDevices;j++){
				// if the device in the current index of our normal arrays (inputData, inputFrequencies)
				// matches the current metric, assign device number
				if(inputData[j]/inputFrequencies[j]==metric[i]){
					devNum = j;
					}
			}
			
			// set bd to round doubles to the predefined variable for decimalPlaces (currently 3)
			bd = new BigDecimal(metric[i]).setScale(decimalPlaces, RoundingMode.HALF_UP);
			
			// If the edge server won't exceed capacity with the addition of the new device
			if (dataStored+metric[i]*allDevices.get(devNum).getFrequency()<=edgeStorageCapacity) {
				
				// Add the new device to the return array of edge devices
				edgeDevices.add("Device #" + devNum + "; " + bd.doubleValue());
				
				// Update metrics by adding the accesses/time for current device to previous totals
				totalAccesses += allDevices.get(devNum).getFrequency();
				timeEdge += allDevices.get(devNum).getDataSize() / allDevices.get(devNum).getEdgeBandwidth();
				
				// Add the data stored to the edge server's used storage
				dataStored += allDevices.get(devNum).getDataSize();
			}
			// Else if adding device will exceed edge capacity, send device data to cloud
			else {
				// Add current device to cloudDevices return array
				cloudDevices.add("Device #" + devNum + "; " + bd.doubleValue());
				
				//Update metrics
				if(allDevices.get(devNum).getDataSize()/allDevices.get(devNum).getCloudBandwidth()>timeCloud){
					// add the device's cloud processing time to the previous timeCloud total
					timeCloud = allDevices.get(devNum).getDataSize() / allDevices.get(devNum).getCloudBandwidth();
				}
			}
			}
		
		//if no devices are stored in the cloud, then maximum upload time is edge total time
		if (timeCloud == 0){
			timeCloud=timeEdge;
		}
		
		//Set metrics, outputAccesses, outputMinUploadTime, outputMaxUploadTime, outputNumEdgeDevices
		outputAccesses = totalAccesses;
		outputEdgeTime = timeEdge;
		
		// if the time required to process in the cloud is greater than time in the edge
		if (timeCloud >= timeEdge){
			// the upload time is equal to the time required to process in the cloud
			outputMaxUploadTime = timeCloud;
		}
		// otherwise if edge will require more time
		else {
			// upload time is equal to the time required to process in the edge
			outputMaxUploadTime = timeEdge;
		}
		
		// the number of edge devices being stored is equal to the size of the edgeDevices array
		outputNumEdgeDevices = edgeDevices.size();
		
	}//end method
	
	
	//SLN 2
	public static void solutionTwo(double[] inputData, double[] inputEdgeBandwidths, double[] inputCloudBandwidths){
		// metric is an array of type double and size numDevices
		double[] metric = new double[numDevices];
		
		// dataStored is a double that adds the quantity of data sizes being stored in the edge server
		double dataStored = 0;
		
		// devNum is an integer to keep track of what device number we're looking at
		int devNum = 0;
		
		// bd is of the BigDecimal class and is used to round values
		BigDecimal bd;
		
		// totalAccesses is a sum of the total data access frequency to the edge server
		double totalAccesses = 0;
		
		// timeEdge is the cumulative processing time the edge requires to upload data source data
		double timeEdge = 0;
		
		// timeCloud is the cumulative processing time the cloud requires to upload data source data 
		double timeCloud = 0;
		
		// loop to run through all devices
		for(int i = 0;i<numDevices;i++){
			// set metric equal to the data divided by the edge bandwidth
			// this is the time required to upload to the edge
			metric[i]=inputData[i]/inputEdgeBandwidths[i];
		}
		
		// define a new array to serve as a metric for the time required to upload to the cloud
		double[] cloudmetric = new double[numDevices];
		
		// loop to run through all devices
		for(int i = 0;i<numDevices;i++){
			// set metric equal to the data divided by the cloud bandwidth
			// this is the time required to upload to the cloud
			cloudmetric[i]=inputData[i]/inputCloudBandwidths[i];
			}
		
		//sort both loops ascending; can't sort descending with primitive datatype so run back through loop
		Arrays.sort(metric);
		Arrays.sort(cloudmetric);
		
		for(int i = numDevices -1;i>=0;i--){
			// loop to run through the number of total devices
			for (int j = 0;j<numDevices;j++){
				// if the device in the current index of our normal arrays (inputData, inputEdgeBandwidths)
				// matches the current metric, assign device number
				if(inputData[j]/inputEdgeBandwidths[j]==metric[i]){
					devNum = j;
					}
			}
			
			// set bd to round doubles to the predefined variable for decimalPlaces (currently 3)
			bd = new BigDecimal(metric[i]).setScale(decimalPlaces, RoundingMode.HALF_UP);
			
			//If the edge server won't exceed capacity with the addition of the new device (base data)
			if (dataStored+metric[i]*allDevices.get(devNum).getEdgeBandwidth()<=edgeStorageCapacity) {
				
				//Add the new device to the return array of edge devices
				edgeDevices.add("Device #" + devNum + "; " + bd.doubleValue());
				
				// Update metrics
				totalAccesses+=allDevices.get(devNum).getFrequency();
				timeEdge += allDevices.get(devNum).getDataSize() / allDevices.get(devNum).getEdgeBandwidth();
				
				// Add the data stored to the edge server's used storage (base data)
				dataStored+=allDevices.get(devNum).getDataSize();
			}
			// Else if adding device will exceed edge capacity, send device data to cloud
			else {
				// Add device to cloudDevices return array
				cloudDevices.add("Device #" + devNum + "; " + bd.doubleValue());
				
				// Update metrics - add to timeCloud the amount of time device needs to upload to cloud
				if(allDevices.get(devNum).getDataSize()/allDevices.get(devNum).getCloudBandwidth()>timeCloud){
					timeCloud = allDevices.get(devNum).getDataSize() / allDevices.get(devNum).getCloudBandwidth();
				}
			}
			}
		
		//if no devices are stored in the cloud, then maximum upload time is edge total time
		if (timeCloud == 0){
			timeCloud=timeEdge;
		}
		
		//Set metrics, outputAccesses, outputMinUploadTime, outputMaxUploadTime, outputNumEdgeDevices
		outputAccesses = totalAccesses;
		outputEdgeTime = timeEdge;
		
		// if the time required to process in the cloud is greater than time in the edge
		if (timeCloud >= timeEdge){
			// the upload time is equal to the time required to process in the cloud
			outputMaxUploadTime = timeCloud;
		}
		// otherwise if edge will require more time
		else {
			// upload time is equal to the time required to process in the edge
			outputMaxUploadTime = timeEdge;
		}
		
		// the number of edge devices being stored is equal to the size of the edgeDevices array
		outputNumEdgeDevices = edgeDevices.size();
		
	}//end method
		
	
	//SLN 3
	public static void solutionThree(double[] inputData){
		// dataStored is a double that adds the quantity of data sizes being stored in the edge server
		double dataStored = 0;
		
		// devNum is an integer to keep track of what device number we're looking at
		int devNum = 0;
		
		// bd is of the BigDecimal class and is used to round values
		BigDecimal bd;
		
		// totalAccesses is a sum of the total data access frequency to the edge server
		double totalAccesses = 0;
		
		// timeEdge is the cumulative processing time the edge requires to upload data source data
		double timeEdge = 0;
		
		// timeCloud is the cumulative processing time the cloud requires to upload data source data 
		double timeCloud = 0;
		
		// array of numDevices length
		double[] compareDataSize = new double[numDevices];
		
		// this is passing the reference, not the value
		// we need to copy the data sizes to our secondary array so we can grab the device number after sorting
		for (int i = 0; i<numDevices;i++){
			compareDataSize[i]=inputData[i];
		}
		
		// sort inputData array by ascending (small to large) data size
		Arrays.sort(inputData);
		
		// loop to run through the number of total devices
		for(int i = 0;i<numDevices;i++){
			// if the device in the current index of our normal arrays (inputData)
			// matches the current metric, assign device number
			for (int j = 0;j<numDevices;j++){
				if(compareDataSize[j]==inputData[i]){
					devNum = j;
					}
			}
			
			// set bd to round doubles to the predefined variable for decimalPlaces (currently 3)
			bd = new BigDecimal(inputData[i]).setScale(decimalPlaces, RoundingMode.HALF_UP);
			
			//If the edge server won't exceed capacity with the addition of the new device
			if (dataStored+inputData[i]<=edgeStorageCapacity) {
				
				// Add the new device to the return array of edge devices
				edgeDevices.add("Device #" + devNum + "; " + bd.doubleValue());
				
				// Update metrics
				totalAccesses+=allDevices.get(devNum).getFrequency();
				timeEdge += allDevices.get(devNum).getDataSize() / allDevices.get(devNum).getEdgeBandwidth();
				
				// Add the data stored to the edge server's used storage
				dataStored += allDevices.get(devNum).getDataSize();
			}
			// Else if adding device will exceed edge capacity, send device data to cloud
			else {
				// Add the device to the cloudDevices return array
				cloudDevices.add("Device #" + devNum + "; " + bd.doubleValue());
				
				// Update metrics
				if(allDevices.get(devNum).getDataSize()/allDevices.get(devNum).getCloudBandwidth()>timeCloud){
					timeCloud = allDevices.get(devNum).getDataSize() / allDevices.get(devNum).getCloudBandwidth();
				}
			}
			//System.out.println("Data stored," + dataStored);
			}
		
		//if no devices are stored in the cloud, then maximum upload time is edge total time
		if (timeCloud == 0){
			timeCloud=timeEdge;
		}
		
		//Set metrics, outputAccesses, outputMinUploadTime, outputMaxUploadTime, outputNumEdgeDevices
		outputAccesses = totalAccesses;
		outputEdgeTime = timeEdge;
		
		// if the time required to process in the cloud is greater than time in the edge
		if (timeCloud >= timeEdge){
			// the upload time is equal to the time required to process in the cloud
			outputMaxUploadTime = timeCloud;
		}
		// otherwise if edge will require more time
		else {
			// upload time is equal to the time required to process in the edge
			outputMaxUploadTime = timeEdge;
		}
		
		// the number of edge devices being stored is equal to the size of the edgeDevices array
		outputNumEdgeDevices = edgeDevices.size();
	} //end method

	public static void createArrays(){
		// create new object rand in the Random class
		Random rand = new Random();
		
		// clear the array of previous devices
		allDevices.clear();
		
		
		// loop to run through the number of devices in system
		for(int i = 0;i<numDevices;i++){
					
			//Randomly generate statistics for device
			edgeBandwidths[i] = rand.nextDouble()*edgeRange+edgeAdditive;
			frequencies[i] = rand.nextDouble()*frequencyRange+frequencyAdditive;
			dataSizes[i] = rand.nextDouble()*dataRange+dataAdditive;
			cloudBandwidths[i]= edgeBandwidths[i]/cloudDivider;
			
			// Instantiate a new Device object with the newly generated statistics
			Device dev = new Device(i, dataSizes[i], frequencies[i], edgeBandwidths[i], cloudBandwidths[i]);
			
			// Add this new device to the allDevices array
			allDevices.add(dev);
		}
		
		// print out current edge capacity, number of devices, ranges, and additives for record purposes
		System.out.println("DECLARATION OF STATICS\nEdge server capacity," + edgeStorageCapacity + "\nNumber Devices," + numDevices);
		System.out.println("Data range," + (dataAdditive) + "-" + (dataRange+dataAdditive));
		System.out.println("Frequency range," + (frequencyAdditive) + "-" + (frequencyRange+frequencyAdditive));
		System.out.println("Edge range," + (edgeAdditive) + "-" + (edgeRange+edgeAdditive));
		System.out.println("Cloud range," + (edgeAdditive/cloudDivider) + "-" + ((edgeRange+edgeAdditive)/cloudDivider));
		
	}
	
	public static void printData() {
		
		// print out total data access frequency
		System.out.println("\nAccesses," + outputAccesses);
		
		// for testing purposes, print out edge processing time
		//System.out.println("Upload to edge time," + outputEdgeTime);
		
		// print out upload time
		System.out.println("Maximum upload time," + outputMaxUploadTime);
		
		// print out number of devices being stored in edge
		System.out.println("Number edge devices," + outputNumEdgeDevices);
	}
	
	public static void clearData(){
		// clear out all lists of configured devices and saved metrics
		edgeDevices.clear();
		cloudDevices.clear();
		outputAccesses = 0;
		outputEdgeTime = 0;
		outputMaxUploadTime = 0;
		outputNumEdgeDevices = 0;
	}
}

