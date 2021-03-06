import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.study.free.vo.FreeBoardVO;

public class Q3 {
	public static void main(String[] args) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boNo", 21);
		paramMap.put("boTitle", "사랑해요 밀키스");
		paramMap.put("boWriter", "말자");
		FreeBoardVO vo = new FreeBoardVO();
		// paramMap에 저장된 정보를 vo 에 담는 처리 또는 함수 생성 후 호출

		Field[] allFields = vo.getClass().getDeclaredFields();
		for (Field field : allFields) {
			System.out.print("getName=> " + field.getName());
			field.setAccessible(true);
//			Object value = field.get(vo);
			System.out.println();
//			paramMap.put(field.getName(), field.get(obj)value)
//			Object value = paramMap.get(field.getName());
			System.out.println("value: " + paramMap.get(field.getName()));
			System.out.println("name: " + field.getType().getSimpleName());
			switch(field.getType().getSimpleName()) {
			case "int":
				field.set(field.getName(), (int)paramMap.get(field.getName()));
				break;
			case "String":
				field.set(field.getName(), (String)paramMap.get(field.getName()));
				break;
			}
//			field.set(field.getName(), paramMap.get(field.getName()));
			
//			paramMap.put(field.getName(), value);
			System.out.println(paramMap);
		}
		System.out.println(paramMap);
		System.out.printf("Info : %d %s %s\n", vo.getBoNo(), vo.getBoWriter(), vo.getBoTitle());
	}

}
