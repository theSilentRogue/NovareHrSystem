package novare.com.hk.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//import java.text.SimpleDateFormat;
@Entity
@Table(name = "projects")
public class Project {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToMany(mappedBy = "project")
	private List<Allocation> allocations;
	
	private String client;
	private String project_name;
	
	@Temporal(TemporalType.DATE)
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date end_date;
	
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
	
	/*@Transient
	private Date date_query;
	
	public Date getDate_query() {
		return date_query;
	}
	public void setDate_query(Date date_query) {
		this.date_query = date_query;
	}*/
	
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
