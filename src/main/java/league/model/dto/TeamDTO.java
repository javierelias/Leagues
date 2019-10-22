/**
 * 
 */
package league.model.dto;

import java.util.List;

/**
 * @author javier.elias
 *
 */
public class TeamDTO {

	private Long id;
	private AreaDTO area;
	private String name;
	private String shortName;
	private String tla;
	private String address;
	private String phone;
	private String website;
	private String email;
	private Long founded;
	private String clubColors;
	private String venue;

	private List<PlayerDTO> squad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AreaDTO getArea() {
		return area;
	}

	public void setArea(AreaDTO area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getTla() {
		return tla;
	}

	public void setTla(String tla) {
		this.tla = tla;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getFounded() {
		return founded;
	}

	public void setFounded(Long founded) {
		this.founded = founded;
	}

	public String getClubColors() {
		return clubColors;
	}

	public void setClubColors(String clubColors) {
		this.clubColors = clubColors;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public List<PlayerDTO> getSquad() {
		return squad;
	}

	public void setSquad(List<PlayerDTO> squad) {
		this.squad = squad;
	}

}
