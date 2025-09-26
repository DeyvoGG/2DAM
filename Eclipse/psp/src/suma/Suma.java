package suma;

public class Suma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Suma sum = new Suma();
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		System.out.println(sum.suma(n1, n2));
	}

	public int suma(int n1, int n2) {
		int resultado = 0;
		for (int i = n1; i < n2; i++) {
			resultado = resultado + i;
		}
		return resultado;
	}

}
