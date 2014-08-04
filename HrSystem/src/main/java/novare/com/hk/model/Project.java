package novare.com.hk.model;

//import java.text.SimpleDateFormat;

public class Project {
	private int id;
	private String client;
	private String project_name;
	private String start_date;
	private String end_date;
	private String searchquery;
	
	public String getSearchquery() {
		return searchquery;
	}
	public void setSearchquery(String searchquery) {
		this.searchquery = searchquery;
	}
	public String getClient() {
		return client;
	}
	public String getEnd_date() {
		/*if (end_date.equals(null))
		{
			end_date = null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy mm, dd");
		return formatter.format(this.end_date);*/
		return end_date;
			
	}
	public int getId() {
		return id;
	}
	public String getProject_name() {
		return project_name;
	}
	public String getStart_date() {
		/*if (start_date.equals(null))
		{
			start_date = null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy mm, dd");
		return formatter.format(this.start_date);*/
		return start_date;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
}
