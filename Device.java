
public class Device {
	
	private int deviceNum;
	private double dataSize;
	private double frequency;
	private double edgeBandwidth;
	private double cloudBandwidth;
	
	/**
	 * Default constructor
	 */
	public Device(){
		deviceNum = 0;
		dataSize = 0;
		frequency = 0;
		edgeBandwidth = 0;
		cloudBandwidth = 0;
	}
	
	/*
	 * Secondary constructor
	 */
	public Device(int pDeviceNum, double pDataSize, double pFrequency, double pEdgeBandwidth, double pCloudBandwidth){
		deviceNum = pDeviceNum;
		dataSize = pDataSize;
		frequency = pFrequency;
		edgeBandwidth = pEdgeBandwidth;
		cloudBandwidth = pCloudBandwidth;
	}
	
	/*
	 * gets device number
	 * @return int deviceNum
	 */
	public int getDeviceNum(){
		return deviceNum;
	}
	
	/*
	 * set device number
	 * @param int pDeviceNum
	 */
	public void setDeviceNum(int pDeviceNum){
		deviceNum = pDeviceNum;
	}
	
	/*
	 * gets data size
	 * @return double dataSize
	 */
	public double getDataSize(){
		return dataSize;
	}
	
	/*
	 * set data size
	 * @param double pDataSize
	 */
	public void setDataSize(double pDataSize){
		dataSize = pDataSize;
	}
	
	/*
	 * get frequency
	 * @return double frequency
	 */
	public double getFrequency(){
		return frequency;
	}
	
	/*
	 * set frequency
	 * @param double pFrequency
	 */
	public void setFrequency(double pFrequency){
		frequency = pFrequency;
	}
	
	/*
	 * get edge bandwidth
	 * @return double edgeBandwidth
	 */
	public double getEdgeBandwidth(){
		return edgeBandwidth;
	}
	
	/*
	 * set edge bandwidth
	 * @param double pEdgeBandwidth
	 */
	public void setEdgeBandwidth(double pEdgeBandwidth){
		edgeBandwidth = pEdgeBandwidth;
	}
	
	/*
	 * get cloud bandwidth
	 * @return double cloudBandwidth
	 */
	public double getCloudBandwidth(){
		return cloudBandwidth;
	}
	
	/*
	 * set cloud bandwidth
	 * @param double pCloudBandwidth
	 */
	public void setCloudBandwidth(double pCloudBandwidth){
		cloudBandwidth = pCloudBandwidth;
	}
	
}
