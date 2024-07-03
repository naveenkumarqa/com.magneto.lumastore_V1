package LumaStore.utilConstants;

public enum ConfigProp {

	URL("url"),
	USERNAME("username"),
	PASSWORD("password"),
	AUTHOR("author"),
	DEVICE("device"),
	CATEGORY("category"),
	BROWSER("browser"),
	SIZE("size"),
	COLOR("colour");

	private final String key;

	ConfigProp(String key)  {
		// Check if the key is null or empty
		if (key == null || key.isEmpty()) {
			throw new IllegalArgumentException("Invalid key provided");
		}
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}