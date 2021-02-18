package generic;

// 한개의 데이터를 저장할 수 있는 객체 - 제네릭 클래스 선언

// 클래스<T> -> 클래스 안에 있는 모든 T를 밖에서 선언한 타입으로 변경하겠다.
// 컴파일 할때 데이터의 타입을 체크했다라고 얘기한다.
public class Box<T> {

	// 밖에서 선언한 타입으로 바꿔지게 된다. -> 컴파일 할 때
	private T t;
	
	public T get() {return t;}
	public void set(T t) {this.t = t;}
}
