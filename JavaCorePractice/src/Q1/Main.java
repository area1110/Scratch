package Q1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		short selectOption;
		Scanner scanner = new Scanner(System.in);

		do {
			renderGUI();
			selectOption = scanner.nextShort();
			switch (selectOption) {
			case 1:
				break;

			case 2:
				break;
			case 3:
				break;
			default: scanner.close();
			}
		} while (selectOption != 4);
	}

	private static void renderGUI() {
		System.out.print("""
				Hệ thống Quản lý cán bộ
				-----------------------
				1. Thêm mới cán bộ
				2. Tìm kiếm
				3. Danh sách cán bộ

				4. Thoát
				-----------------------
				Chọn:
				""");
	}

}
