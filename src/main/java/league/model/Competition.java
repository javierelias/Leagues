/**
 * 
 */
package league.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author javier.elias
 *
 */
@Entity
@Table(name = "competitions")
public class Competition {

	@Id
	@Column(name = "competition_id", nullable = false)
	private Long id;

	@Column(name = "competition_name")
	private String name;

	@Column(name = "competition_code")
	private String code;

	@Column(name = "competition_areaname")
	private String areaname;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "competition_teams", joinColumns = @JoinColumn(name = "competition_id", referencedColumnName = "competition_id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "team_id"))
	private List<Team> teams;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
