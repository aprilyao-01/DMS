package setting;

public enum ThemeColour {
	Light("Light","LightStyle.css"),
	Dark("Dark", "DarkStyle.css");

	private String name;
	private String url;

	private ThemeColour(String name, String url){
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}
}
