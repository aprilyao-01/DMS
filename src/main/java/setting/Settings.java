package setting;

/** <h1> Class: {@link Settings} </h1>
 *
 *<p> This class implements SINGLETON design pattern
 * to define current setting in the program.
 * including:
 * 			<p>1. Colour theme {@link ThemeColour}
 * 			<p>2. Sound Effect {@link SoundEffect}
 *
 * @see controller.SettingsController
 * @see ThemeColour
 * @see SoundEffect
 * @version 1.0
 * @since 1.0
 * @author Siyu Yao
 * */

public class Settings
{
	private ThemeColour m_themeColour;
	private boolean m_soundEffect;
	private boolean m_bgm;

	// accessor methods
	public ThemeColour getThemeColour() {
		return m_themeColour;
	}
	public void setThemeColour(ThemeColour m_themeColour) {
		this.m_themeColour = m_themeColour;
	}
	public boolean isSoundEffect() {
		return m_soundEffect;
	}
	public void setSoundEffect(boolean m_soundEffect) {
		this.m_soundEffect = m_soundEffect;
	}
	public boolean ism_bgm() {
		return m_bgm;
	}
	public void setm_bgm(boolean m_bgm) {
		this.m_bgm = m_bgm;
	}

	private static Settings m_currentSettings = null;


	/** get the current settings, if it is first use, create a new instance
	 * @return the current setting
	 * */
	public static Settings getCurrentSettings() {
		if(m_currentSettings == null){
			m_currentSettings = new Settings();
		}
		return m_currentSettings;
	}

	private Settings(){
		m_themeColour = ThemeColour.Light;
		m_soundEffect = true;
		m_bgm = true;
	}


	@Override
	public String toString()
	{
		return "Settings [Colour theme=" + m_themeColour + ", sound effect =" + m_soundEffect + ", m_bgm=" + m_bgm  + "]";
	}
}
