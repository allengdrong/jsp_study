import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 학생이름(키) - 점수(값)
		// key - value : Map
		// 생성은 클래스로 하고 있다. 타입은 인터페이스로 하고 있다. -> 모든 작업은 다 메서드를 이용
		Map<String, Integer> map = new HashMap<>();
		
		// 데이터 저장 - 동명이인이 있는 경우는 값이 덮어쓰기가 된다. 키는 중복을 허용하지 않는다.
		map.put("신용권", 85);
		map.put("홍길동", 90);
		map.put("동장군", 80);
		map.put("홍길동", 95);
		
		// 데이터의 갯수 - size()
		System.out.println("map 데이터의 갯수: " + map.size());
		
		// map은 순차적인 처리를 제공하지 않는다.
		// 키를 순차적인 처리가 가능한 set으로 전달할 수 있다. -> 모든 데이터를 출력해 보기
		Set<String> keySet = map.keySet();
		System.out.println(keySet);
		
		// 모든 데이터를 처리할 수 있도록 가져온 키셋을 Iterator(반복자) 객체로 만든다.
//		Iterator<String> keyIterator = keySet.iterator();
//		while(keyIterator.hasNext()) {
//			String key = keyIterator.next();
//			Integer value = map.get(key);
//			System.out.println("key = " + key + ", value = " + value);
//		}
//		for(String k : keySet) {
//			System.out.println(k + " = " + map.get(k));
//		}
		for (String k : map.keySet()) {
			System.out.println(k + " = " + map.get(k));
		}
		// map에서 하나의 데이터 삭제하기 - 키가 필요하다.
		map.remove("홍길동");
		for (String k : map.keySet()) {
			System.out.println(k + " = " + map.get(k));
		}
		
		// map의 모든 데이터 삭제하기
		map.clear();
			System.out.println(map.size());
			for (String k : map.keySet()) {
				System.out.println(k + " = " + map.get(k));
			}
	}

}
