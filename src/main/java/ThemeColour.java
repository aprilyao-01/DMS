public enum ThemeColour {
	Light("Light","LightStyle.css"),
	Dark("Dark", "DarkStyle.css");

	private String name;
	private Object url;

	private ThemeColour(String name, Object url){
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public Object getUrl() {
		return url;
	}
}
