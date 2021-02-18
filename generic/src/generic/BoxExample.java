package generic;

public class BoxExample {

	// JSP Project에서는 main()를 찾아 볼 수 없다.
	// Tomcat에서 가지고 있다. - 사용자에 대한 요구는 계속 체크하는 반복처리가 되어져 있다.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 제네릭해서 객체를 생성한다. - 문자열을 저장할 수 있는 Box 객체 선언
		// Box라는 클래스 밖에서 타입을 지정해서 사용할 수 있도록 코드 작성 -> 제네릭
		// Box클래스를 생성하면서 <String>로 선언을 하면 private T t; 코드가
		// private String t; 로 바꿔서 생성이 된다.
		Box<String> box1 = new Box<>();
		box1.set("Hello");
//		box1.set(1); // 오류
		
		// Box라는 클래스 밖에서 타입을 지정해서 사용할 수 있도록 코드 작성 -> 제네릭
		// Box클래스를 생성하면서 <Integer>로 선언을 하면 private T t; 코드가
		// private Integer t; 로 바꿔서 생성이 된다.
		Box<Integer> box2 = new Box<>();
//		box2.set("hello"); // 오류
		box2.set(2);
	}

}
