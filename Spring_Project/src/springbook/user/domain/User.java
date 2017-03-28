package springbook.user.domain;

public class User {
	Level level;
	int login;
	int likes;
	String id;
	String name;
	String password;
	
	public User() {}
	
	public User(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.level = Level.BASIC;
		this.login = 0;
		this.likes = 0;
	}
	
	public User(String id, String name, String password, Level level, int login, int likes) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.level = level;
		this.login = login;
		this.likes = likes;
	}

	public Level getLevel() {
		return level;
	}

	public User setLevel(int level) {
		this.level = Level.valueOf(level);
		return this;
	}

	public int getLogin() {
		return login;
	}

	public User setLogin(int login) {
		this.login = login;
		return this;
	}

	public int getLikes() {
		return likes;
	}

	public User setLikes(int likes) {
		this.likes = likes;
		return this;
	}

	public String getId() {
		return id;
	}
	
	public User setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public User setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String toString() {
		return "User [level=" + level + ", login=" + login + ", likes=" + likes + ", id=" + id + ", name=" + name
				+ ", password=" + password + "]";
	}
}

