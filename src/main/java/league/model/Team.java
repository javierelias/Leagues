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
@Table(name = "teams")
public class Team {

	@Id
	@Column(name = "team_id", nullable = false)
	private Long id;

	@Column(name = "team_name")
	private String name;

	@Column(name = "team_tla")
	private String tla;

	@Column(name = "team_shortname")
	private String shortname;

	@Column(name = "team_areaname")
	private String areaname;

	@Column(name = "team_email")
	private String email;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "team_players", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "team_id"), inverseJoinColumns = @JoinColumn(name = "player_id", referencedColumnName = "player_id"))
	private List<Player> squad;

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

	public String getTla() {
		return tla;
	}

	public void setTla(String tla) {
		this.tla = tla;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Player> getSquad() {
		return squad;
	}

	public void setSquad(List<Player> squad) {
		this.squad = squad;
	}

}
