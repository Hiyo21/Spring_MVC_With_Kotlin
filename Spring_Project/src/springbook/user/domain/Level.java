package springbook.user.domain;

public enum Level {
	//Java���� Enum �����. 
	// #1. String ���ϰ�, �� String ���� �ش��ϴ� ���� pair�� �غ�.
	BASIC(1), SILVER(2), GOLD(3);
	
	// #2. ��ȣ ���� value�� ������ ������ ����
	public final int value;
	
	// #3. Constructor�� setter�� �����Ѵ�. (�ܺο��� �ڴ�� ������ ������ public����)
	Level(int value){
		this.value = value;
	}
	
	// #4. ���� ��� ���ؼ���  ������ getter�� ���ؼ� ���� ��´�.
	public int getIntValue(){
		return value;
	}
	
	// #5. Value ���� key���� string�� ��� ���� method�� �߰�. Java������ enum�� �ܼ��� string�� int�� pair���� �ƴϴ�.
	public static Level valueOf(int value){
		switch(value){
		case 1 : return BASIC;
		case 2 : return SILVER;
		case 3 : return GOLD;
		default : throw new AssertionError("Unknown Value : " + value );
		}
	}
	
}
