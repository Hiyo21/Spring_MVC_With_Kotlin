package springbook.user.domain;

public enum Level {
	//Javaｿ｡ｼｭ Enum ｸｸｵ魍�. 
	// #1. String ｰｪﾇﾏｰ�, ｱﾗ String ｰｪｿ｡ ﾇﾘｴ酩ﾏｴﾂ ｰｪﾀﾇ pairｸｦ ﾁﾘｺ�.
	GOLD(3, null), SILVER(2, GOLD), BASIC(1, SILVER);
	
	// #2. ｰ�ﾈ｣ ｾﾈﾀﾇ valueｸｦ ﾀ惕衂ﾒ ｺｯｼｦ ｼｱｾ�
	public final int value;
	public Level nextLevel;
	
	// #3. Constructorｸｦ setterｷﾎ ｼｳﾁ､ﾇﾑｴﾙ. (ｿﾜｺﾎｿ｡ｼｭ ｸﾚｴ�ｷﾎ ｼｳﾁ､ﾀﾌ ｰ｡ｴﾉﾇﾑ publicｸｻｰ�)
	Level(int value, Level nextLevel){
		this.value = value;
		this.nextLevel = nextLevel;
	}
	
	
	// #4. ｰｪﾀｻ ｾ� ﾀｧﾇﾘｼｭｴﾂ  ｹｫﾁｶｰﾇ getterｸｦ ﾅ�ﾇﾘｼｭ ｰｪﾀｻ ｾﾂｴﾙ.
	public int getIntValue(){
		return value;
	}
	
	public Level getNextLevel(){
		return nextLevel;
	}
	
	// #5. Value ｸｻｰ� keyｰｪﾀﾇ stringﾀｻ ｾ� ﾀｧﾇﾑ methodｵｵ ﾃﾟｰ｡. Javaｿ｡ｼｭﾀﾇ enumﾀｺ ｴﾜｼ� stringｰ� intﾀﾇ pairｰｪﾀﾌ ｾﾆｴﾏｴﾙ.
	public static Level valueOf(int value){
		switch(value){
		case 1 : return BASIC;
		case 2 : return SILVER;
		case 3 : return GOLD;
		default : throw new AssertionError("Unknown Value : " + value );
		}
	}
	
}
