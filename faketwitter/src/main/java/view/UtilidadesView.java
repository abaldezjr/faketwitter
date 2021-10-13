package view;

import java.util.Scanner;

public class UtilidadesView {

	private Scanner teclado = new Scanner(System.in);

	public void titulo(String titulo) {
		System.out.println("----------------------------------");
		System.out.println("           " + titulo + "           ");
		System.out.println("----------------------------------");
	}

	public Integer menu(String titulo, String[] menu) {
		titulo(titulo);
		for (int cont = 0, index = 1; cont < menu.length; cont++, index++) {
			if (cont == (menu.length - 1)) {
				index = 0;
			}
			System.out.println(index + " - " + menu[cont]);
		}
		System.out.println("----------------------------------");
		return Integer.parseInt(leia(" -> "));
	}

	public String leia(String label) {
		System.out.print(label);
		return teclado.nextLine();
	}

}
