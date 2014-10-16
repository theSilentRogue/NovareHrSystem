package novare.com.hk.model;

//import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
@Table(name = "projects")
public class Project {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
	private List<Allocation> allocations;
	
	public List<Allocation> getAllocations() {
		return allocations;
	}
	public void setAllocations(List<Allocation> allocations) {
		this.allocations = allocations;
	}

	private String client;
	private String project_name;
	private String employee_name;
	
	@Transient
	private double percent;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date end_date;
	
	private long plannedheadcount;
	
	@Transient
	private double totalAllocation;
	
	private double dailycost;
	
	private String month;
	
	private String year;
	
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public long getPlannedheadcount() {
		return plannedheadcount;
	}
	public void setPlannedheadcount(long plannedheadcount) {
		this.plannedheadcount = plannedheadcount;
	}
	public double getTotalAllocation() {
		return totalAllocation;
	}
	public void setTotalAllocation(double totalallocation) {
		this.totalAllocation = totalallocation;
	}
	public double getDailycost() {
		return dailycost;
	}
	public void setDailycost(double dailycost) {
		this.dailycost = dailycost;
	}

	@Transient
	private String searchquery;
	
	@Transient
	@Temporal(TemporalType.DATE)
	private Date date_start;
	
	public Date getDate_start() {
		return date_start;
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	
	@Transient
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date date_end;
	
	public Date getDate_end() {
		return date_end;
	}
	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}
	
	public String getSearchquery() {
		return searchquery;
	}
	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}
	public String getClient() {
		return client;
	}
	public Date getEnd_date() {
		return end_date;
			
	}
	public int getId() {
		return id;
	}
	public String getProject_name() {
		return project_name;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
}
