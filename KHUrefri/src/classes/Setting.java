package classes;

public class Setting {
	private int id;

	private int freezer_temp;
	private int refrige_temp;
	private int freezer_brightness;
	private int refrige_brightness;
	private int hotwater_temp;
	private int coldwater_temp;	
	
	public Setting(int id, int freezer_temp, int refrige_temp, int freezer_brightness, int refrige_brightness,
			int hotwater_temp, int coldwater_temp) {
		super();
		this.id = id;
		this.freezer_temp = freezer_temp;
		this.refrige_temp = refrige_temp;
		this.freezer_brightness = freezer_brightness;
		this.refrige_brightness = refrige_brightness;
		this.hotwater_temp = hotwater_temp;
		this.coldwater_temp = coldwater_temp;
	}
	public Setting() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFreezer_temp() {
		return freezer_temp;
	}
	public void setFreezer_temp(int freezer_temp) {
		this.freezer_temp = freezer_temp;
	}
	public int getRefrige_temp() {
		return refrige_temp;
	}
	public void setRefrige_temp(int refrige_temp) {
		this.refrige_temp = refrige_temp;
	}
	public int getFreezer_brightness() {
		return freezer_brightness;
	}
	public void setFreezer_brightness(int freezer_brightness) {
		this.freezer_brightness = freezer_brightness;
	}
	public int getRefrige_brightness() {
		return refrige_brightness;
	}
	public void setRefrige_brightness(int refrige_brightness) {
		this.refrige_brightness = refrige_brightness;
	}
	public int getHotwater_temp() {
		return hotwater_temp;
	}
	public void setHotwater_temp(int hotwater_temp) {
		this.hotwater_temp = hotwater_temp;
	}
	public int getColdwater_temp() {
		return coldwater_temp;
	}
	public void setColdwater_temp(int coldwater_temp) {
		this.coldwater_temp = coldwater_temp;
	}

}