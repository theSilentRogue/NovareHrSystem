package novare.com.hk.model;

public class Employee {

	private int id;
	private String fname;
	private String lname;
	private String department;
	private String status;
	private String start_date;
	private String date_resigned;
	private String position;
	private double cost;
	private String searchquery;
	private String filterStat;
	
	public String getFilterStat() {
		return filterStat;
	}
	public void setFilterStat(String filterStat) {
		this.filterStat = filterStat;
	}
	public String getSearchquery() {
		return searchquery;
	}
	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}
	public double getCost() {
		return cost;
	}
	public String getDate_resigned() {
		return date_resigned;
	}
	
	public String getDepartment() {
		return department;
	}
	public String getFname() {
		return fname;
	}
	public int getId() {
		return id;
	}
	public String getLname() {
		return lname;
	}
	public String getPosition() {
		return position;
	}
	public String getStart_date() {
		return start_date;
	}
	public String getStatus() {
		return status;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setDate_resigned(String date_resigned) {
		this.date_resigned = date_resigned;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
