package setting;


/** <h1> Enum Class: {@link ThemeColour} </h1>
 *
 *<p> This enum class define the total color of the theme.<br>
 * When user choose Light, it will use LightStyle,
 * when user choose dark, it will use DarkStyle.
 *
 * @see Settings
 * @version 1.0
 * @since 1.0
 * @author Siyu Yao
 * */

public enum ThemeColour {
	Light("Light","LightStyle.css"),
	Dark("Dark", "DarkStyle.css");

	private String m_name;
	private String m_url;

	// accessor methods
	public String getName() {
		return m_name;
	}
	public String getUrl() {
		return m_url;
	}

	private ThemeColour(String m_name, String m_url){
		this.m_name = m_name;
		this.m_url = m_url;
	}
}
