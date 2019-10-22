/**
 * 
 */
package league.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author javier.elias
 *
 */
@Entity
@Table(name = "players")
public class Player {

	@Id
	@Column(name = "player_id", nullable = false)
	private Long id;

	@Column(name = "player_name")
	private String name;

	@Column(name = "player_position")
	private String position;

	@Column(name = "player_dateofbirth")
	private Date dateofbirth;

	@Column(name = "player_countryofbirth")
	private String countryofbirth;

	@Column(name = "player_nationality")
	private String nationality;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getCountryofbirth() {
		return countryofbirth;
	}

	public void setCountryofbirth(String countryofbirth) {
		this.countryofbirth = countryofbirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

}
