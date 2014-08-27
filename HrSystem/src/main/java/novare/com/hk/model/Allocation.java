package novare.com.hk.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Allocation {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	private double percent;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	private Date end_date;
	
	@Transient
	private String employee_name;
	
	@Transient
	private String project_name;
	
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
	private Date date_end;
	
	public Date getDate_end() {
		return date_end;
	}

	public void setDate_end(Date date_end) {
		this.date_end = date_end;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public String getEmployee_name() {
		return employee_name;
	}
	
	public Project getProject(){
		return project;
	}
	
	public String getProject_name() {
		return project_name;
	}
	
	public Date getEnd_date() {
		return end_date;
	}
	
	public int getId() {
		return id;
	}
	
	public double getPercent() {
		return percent;
	}
	
	public String getSearchquery() {
		return searchquery;
	}
	
	public Date getStart_date() {
		return start_date;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}
	
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}
