package springbook.user.domain;

public enum Level {
	//Java에서 Enum 만들기. 
	// #1. String 값하고, 그 String 값에 해당하는 값의 pair를 준비.
	BASIC(1), SILVER(2), GOLD(3);
	
	// #2. 괄호 안의 value를 저장할 변수를 선언
	public final int value;
	
	// #3. Constructor를 setter로 설정한다. (외부에서 멋대로 설정이 가능한 public말고)
	Level(int value){
		this.value = value;
	}
	
	// #4. 값을 얻기 위해서는  무조건 getter를 통해서 값을 얻는다.
	public int getIntValue(){
		return value;
	}
	
	// #5. Value 말고 key값의 string을 얻기 위한 method도 추가. Java에서의 enum은 단순히 string과 int의 pair값이 아니다.
	public static Level valueOf(int value){
		switch(value){
		case 1 : return BASIC;
		case 2 : return SILVER;
		case 3 : return GOLD;
		default : throw new AssertionError("Unknown Value : " + value );
		}
	}
	
}
