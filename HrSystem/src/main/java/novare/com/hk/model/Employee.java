package novare.com.hk.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Allocation> allocations;
	
	@Column(name = "first_name")
	private String fname;
	
	@Column(name = "last_name")
	private String lname;
	
	private String department;
	private String status;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date date_resigned;
	
	private String position;
	private double cost;
	
	@Transient
	private String searchquery;
	
	@Transient
	private String filterStat;
	
	public List<Allocation> getAllocations() {
		return allocations;
	}
	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}
	
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
	public Date getDate_resigned() {
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
	public Date getStart_date() {
		return start_date;
	}
	public String getStatus() {
		return status;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setDate_resigned(Date date_resigned) {
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
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
