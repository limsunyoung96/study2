public class TestMyCalc {
	// 태영아빠 승주 숫자 2개 사이의 합을 구해서 결과를 리턴하는 메서드
	public int mysum(int a, int b) {
		int sum = 0;
		for (int i = 1; i < b; i++) {
			sum += i;
		}
		return sum;
	}

	public static void main(String[] args) {
		TestMyCalc calc = new TestMyCalc();
		int r = calc.mysum(3, 20);
		System.out.println("결과: " + r);
	}
}
