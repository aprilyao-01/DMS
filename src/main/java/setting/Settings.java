package setting;

/** SINGLETON design pattern */
public class Settings
{
	private ThemeColour themeColour;
	private boolean soundEffect;
	private boolean BGM;

	private static Settings currentSettings = null;

	public static Settings getCurrentSettings() {
		if(currentSettings == null){
			currentSettings = new Settings();
		}
		return currentSettings;
	}

	private Settings(){
		themeColour = ThemeColour.Light;
		soundEffect = true;
		BGM = true;
	}


	public ThemeColour getThemeColour() {
		return themeColour;
	}

	public void setThemeColour(ThemeColour themeColour) {
		this.themeColour = themeColour;
	}

	public boolean isSoundEffect() {
		return soundEffect;
	}

	public void setSoundEffect(boolean soundEffect) {
		this.soundEffect = soundEffect;
	}

	public boolean isBGM() {
		return BGM;
	}

	public void setBGM(boolean BGM) {
		this.BGM = BGM;
	}

	@Override
	public String toString()
	{
		return "Settings [Colour theme=" + themeColour + ", sound effect =" + soundEffect + ", BGM=" + BGM  + "]";
	}
}
