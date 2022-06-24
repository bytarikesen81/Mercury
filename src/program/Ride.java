package program;

import java.util.*;

//Surus sinifi
public class Ride {
	private int RideID;
	private String userID;
	private Driver driver;
	private String carClass;
	private String locFrom;
	private String locTo;
	private String timeStart;
	private String timeEnd;
	private String status;
	
	public Ride(int rideID, String userID, Driver driver, String car, String locFrom, String locTo, String timeStart,
			String timeEnd, String status) {
		this.RideID = rideID;
		this.userID = userID;
		this.driver = driver;
		this.carClass = car;
		this.locFrom = locFrom;
		this.locTo = locTo;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.status = status;
	}
	
	public int getRideID() {
		return RideID;
	}

	public void setRideID(int rideID) {
		RideID = rideID;
	}

	public String getUserID() {
		return this.userID;
	}

	public void setUser(String userID) {
		this.userID = userID;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public String getLocFrom() {
		return locFrom;
	}

	public void setLocFrom(String locFrom) {
		this.locFrom = locFrom;
	}

	public String getLocTo() {
		return locTo;
	}

	public void setLocTo(String locTo) {
		this.locTo = locTo;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public static double calcDistance(String from,String to) {
		int fromInd,toInd;
		float distances[][] = {
				{0,78,41},
				{84,0,56},
				{38,58,0},
		};
		switch(from) {
			case "Istanbul Havalimanı":
				fromInd = 0;
				break;
			case "Sabiha Gökçen Havalimanı":
				fromInd = 1;
				break;
			case "Atatürk Havalimanı":
				fromInd = 2;
				break;
			default:
				fromInd = 0;
			break;
		}
		switch(to) {
			case "Istanbul Havalimanı":
				toInd = 0;
				break;
			case "Sabiha Gökçen Havalimanı":
				toInd = 1;
				break;
			case "Atatürk Havalimanı":
				toInd = 2;
				break;
			default:
				toInd = 0;
			break;
		}
		return distances[fromInd][toInd];
	}
	
	public static double calcTime(double distance,String carClass) {
		double multiple;
			switch(carClass) {
				case "Ekonomi":
					multiple = 0.02;
					break;
				case "Standard":
					multiple = 0.016;
					break;
				case "VIP":
					multiple = 0.014;
					break;
				default:
					multiple = 0;
				break;
			}
		return multiple*distance;
	}
}
