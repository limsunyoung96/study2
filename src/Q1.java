import com.study.member.vo.MemberVO;

public class Q1 {

	public static void main(String[] args) {
		String str = "com.study.member.vo.MemberVO";
		// 위 str 변수에 있는 문자열을 사용해서 인스턴스를 생성
		try {
			Class clazz = Class.forName(str);
//			System.out.println("getName: " + clazz.getName());
//			System.out.println("getSimpleName: " + clazz.getSimpleName());
//			System.out.println("getPackage: " + clazz.getPackage().getName());

			try {
				MemberVO vo = (MemberVO) clazz.newInstance();
				vo.setMemName("말자");
				vo.setMemMail("malja1004@gmail.com");
				System.out.printf("객체 정보 : %s %s\n", vo.getMemName(), vo.getMemMail());
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
