package global.scit.cosmetic.ui;

import global.scit.cosmetic.service.CosmeticService;
import global.scit.cosmetic.vo.CosMember;
import global.scit.cosmetic.vo.Product;

import java.util.Scanner;

public class CosmeticUI {
	Scanner sc = new Scanner(System.in);
	CosmeticService service = new CosmeticService();
	CosMember c = new CosMember();

	public CosmeticUI() {
		boolean login = false;
		String choice = null;

		while (true) {

			startMenu();
			switch (choice) {

				case "1":
					login();
					break;

				case "2":
					insertMember();
					break;
				case "0":
					System.out.println("** 프로그램 종료합니다. ");
					System.exit(1);
					break;
				default:
					System.out.println("다시 선택해 주세요");
					sc.nextLine(); // 버퍼에 남은 데이터 싹지우기
					break;
			}//switch
			if (login) break;
		}//while

		// 일반회원메뉴
		while (c.getUsrrole() == 0 && login != false) {

			userMenu();
			choice = sc.next();
			switch (choice) {
				case "1":
					updateMember(); // 내 정보 수정
					break;
				case "2":
					selectProduct(); // 화장품 조회
					break;
				case "3":
					selectProduct(); // 화장품 전체조회
					break;
				case "4":
					deleteMember(); // 내 탈퇴
					break;
				case "0":
					System.out.println("** 프로그램 종료합니다. ");
					return;
				default:
					System.out.println("다시 선택해 주세요");
					sc.nextLine(); // 버퍼에 남은 데이터 싹지우기
					break;

			}//switch
		}//while

		while (c.getUsrrole() == 1 && login != false) {
			adminMenu();// 관리자회원메뉴
			choice = sc.next();
			switch (choice) {
				case "1" -> selectMember(); // 회원정보 조회
				case "2" -> updateMember(); // 회원정보 수정
				case "3" -> insertProduct(); // 화장품 등록
				case "4" -> updateProduct(); // 화장품 수정
				case "5" -> deleteProduct(); // 화장품 삭제
				case "6" -> selectProduct(); // 화장품 전체조회
				case "0" -> {
					System.out.println("** 프로그램 종료합니다. ");
					System.exit(1);
					return;
				}
				default -> {
					System.out.println("다시 선택해 주세요");
					sc.nextLine(); // 버퍼에 남은 데이터 싹지우기

				}
			}//switch

		}//while
	}// CosmeticUI()

	private void login() {

		CosMember cosMember = new CosMember();
		String usrid = null;
		String usrpass = null;
		sc.nextLine();
		boolean loginchance = false; // 최대 10번의 기회를 준다

		for (int i = 0; i <= 5; i++) {


			System.out.println("기존 회원 아이디를 입력하세요 : ");
			usrid = sc.next();
			System.out.println("기존 회원 패스워드 를 입력하세요 : ");
			usrpass = sc.next();

			if(service.selectMember(usrid)==null)
				System.out.println(" 해당하는 아이디가 없습니다. ");
			else if (c.getUsrid().equals(usrid)&&c.getPassword().equals(usrpass)) {
				System.out.println(usrid+" 님께서 로그인 하셨습니다.");
			}
				System.out.println("아이디와 비밀번호를 다시 확인해주세요.");
			}
		}


	private void deleteProduct() {
		String productid, answer;
		System.out.println(" \n화장품 삭제 ");
		System.out.println(" c> 제품 입력 : ");
		productid = sc.next();
		Product product = service.selectProduct(productid);

		// int result = service.deleteProduct(productid);

		if (product == null) {
			System.out.println("\n 해당하는 제품이 없습니다.");
			return;
		}
		System.out.println("정말 삭제하시겠습니까? (Y/n)");
		String answer1 = sc.next();
		if (!answer1.equals("Y")) {
			System.out.println("삭제 작업이 취소되었습니다.");
			return;
		}
		int result = service.deleteProduct(productid);
		if (result == 1)
			System.out.println("작업 완료");
		else
			System.out.println("삭제 작업 실패");
	}

	private void updateProduct() {
		String productid;
		String productname = null;
		String choice;
		int productprice = 0;
		int producttype = 0;
		int productsolution = 0;
		System.out.println("화장품의 정보를 수정합니다.");
		System.out.println("수정할 화장품의 아이디를 입력해주세요.");
		productid = sc.next();
		Product product = service.selectProduct(productid);
		System.out.println(product + " \n수정합니다. ");
		Product p = new Product(productid, productname, productprice, producttype, productsolution);

		if (product == null) {
			System.out.println(" 중복된 제품이 있습니다. ");
			return;
		}
		System.out.println(product + "를 수정합니다.");
		productUpdateMenu();
		while (true) {
			//Product p = new Product(productid, productname, productprice, producttype, productsolution);
			choice = sc.next();
			switch (choice) {

				case "1":// 1) 화장품 이름 수정

					System.out.println("화장품의 이름을 다시 입력해주세요.");
					productname = sc.next();
					@SuppressWarnings("null")
					Product p1 = new Product(productid, productname, 0, 1, 1);
					int result1 = service.insertProduct(p1);
					if (result1 == 1)
					{System.out.println(" 제품 이름 수정이 완료되었습니다.");}
					else
					{System.out.println("** 제품 이름 수정이 실패했습니다.");
						continue;}

					break;
				// 2) 화장품 가격 수정
				case "2":
					System.out.println("화장품의 가격을 다시 입력해주세요.");

					productprice = sc.nextInt();
					Product product2 = service.selectProduct(String.valueOf(productprice));
					Product p2 = new Product(productid, null, productprice, 1, 1);

					int result2 = service.insertProduct(p2);
					if (result2 == 1) {
						System.out.println(" 화장품의 가격 수정이 완료되었습니다.");
					} else {
						System.out.println("**화장품의 가격 수정을 실패했습니다.");
						continue;

					}
					break;
					// 3) 화장품 타입 수정
				case "3":
					while (true) {
					System.out.println(" 1) 세럼 2) 크림 3) 마스크 ");
					System.out.println();
					System.out.println("화장품의 타입을 다시 입력해주세요.");
					try {
						producttype = sc.nextInt();
						if (!(producttype >= 1 && producttype <= 3)) {
							throw new Exception(); // 내가 일부러 exception 발생시키는것.
						}
					} catch (Exception e) {
						System.out.println("오류 : 항목을 잘못 선택했습니다.");
						sc.nextLine();
						continue; // 타입 다시 입력받도록 간다.
					}
					break;// exception이 터지지 않으면 밖으로 빠져나가도록 해준다.
					}

				Product p3 = new Product(productid, productname = null, productprice = 0, producttype, productsolution = 1);

				int result3 = service.insertProduct(p3);
				if (result3 == 1) {
					System.out.println(" 타입수정이 완료되었습니다.");
				} else {
					System.out.println("** 타입 수정을 실패했습니다.");
					continue;
				}
					break;


				// 4) 화장품 기능 수정
				case "4":
					while (true) {
						System.out.println("1) 미백 2) 노화 3) 여드름");
						System.out.println();
						System.out.println("\n화장품의 주요 기능을 다시 입력해주세요.:");
						try {
							productsolution = sc.nextInt();
							if (!(productsolution >= 1 && productsolution <= 3)) {
								throw new Exception(); // 내가 일부러 exception 발생시키는것.
							}
						} catch (Exception e) {
							System.out.println("오류 : 항목을 잘못 선택했습니다.");
							sc.nextLine();
							continue; // 타입 다시 입력받도록 간다.
						}
						break; // exception이 터지지 않으면 밖으로 빠져나가도록 해준다.
					}//while
					Product p4 = new Product(productid, productname = null, productprice = 0, producttype = 0, productsolution);

					int result4 = service.insertProduct(p4);
					if (result4 == 1) {
						System.out.println(" 화장품의 주요기능 수정이 완료되었습니다.");
					} else {
						System.out.println("**화장품의 주요기능 수정을 실패했습니다.");

					}
					break;
					// 0) 이 전 메뉴로 가기
				case "0":
					return;
			}//switch


		}//while
	}//updateProduct()



	private void productUpdateMenu() {
		System.out.println("[화장품 정보 수정 메뉴]");
		System.out.println(" 1) 화장품 이름 수정 ");
		System.out.println(" 2) 화장품 가격 수정 ");
		System.out.println(" 3) 화장품 타입 수정 ");
		System.out.println(" 4) 화장품 기능 수정 ");
		System.out.println(" 0) 이 전 메뉴로 가기 ");
		System.out.println("\n수정할 번호를 입력해 주세요 : ");
	}

	private void insertProduct() {
		String productid;
		String productname;
		int productprice;
		int producttype;
		int productsolution;

		System.out.println(" [제품 등록] ");
		System.out.println(" > 제품 아이디 입력 ");
		productid = sc.next();
		Product product = service.selectProduct(productid);
		if (product == null) {
			System.out.println(" 해당 제품이 없습니다. ");
			return;
		}
		System.out.println(" > 제품명 입력 ");
		productname = sc.next();
		System.out.println(" > 제품 가격 입력 ");
		productprice = sc.nextInt();
		while (true) {
			System.out.println(" > 제품 타입 입력 ");
			System.out.println(" 1)크림형 2)세렴형 3)마스크형 ");
			try {
				producttype = sc.nextInt();
				if (!(producttype >= 1 && producttype <= 3)) {
					throw new Exception(); // 내가 일부러 exception 발생시키는것.
				}
			} catch (Exception e) {
				System.out.println("오류 : 항목을 잘못 선택했습니다.");
				sc.nextLine();
				continue; // 다시 입력받도록 간다.
			}
			break; // exception이 터지지 않으면 밖으로 빠져나가도록 해준다.
		}
		while (true) {
			System.out.println(" > 제품 목적 입력 ");
			System.out.println(" 1)미백 2)노화 3)여드름 ");
			try {
				productsolution = sc.nextInt();
				if (!(productsolution >= 1 && productsolution <= 3)) {
					throw new Exception(); // 내가 일부러 exception 발생시키는것.
				}
			} catch (Exception e) {
				System.out.println("오류 : 항목을 잘못 선택했습니다.");
				sc.nextLine();
				continue; // 다시 입력받도록 간다.
			}
			break; // exception이 터지지 않으면 밖으로 빠져나가도록 해준다.
		}
		Product p = new Product(productid, productname, productprice, producttype, productsolution);
		int result = service.insertProduct(p);
		if (result == 1)
			System.out.println(" 제품 등록이 완료되었습니다.");
		else
			System.out.println(" 제품 등록이 실패했습니다.");
	}

	private void selectMember() {
		String usrid;
		System.out.println(" [제품 정보 조회] ");
		System.out.println("> 제품 아이디 ");
		usrid = sc.next();
		CosMember member = service.selectMember(usrid);
		if (member == null) {
			System.out.println("해당 회원이 없습니다.");
			return;
		}
		System.out.println(member);
	}

	private void deleteMember() {
		String usrid, answer;
		System.out.println(" > 아이디 입력 ");
		usrid = sc.next();
		CosMember member = service.selectMember(usrid);
//int result =service.deleteMember(usrid);
		if (member == null) {
			System.out.println("해당하는 아이디가 없습니다.");
			return;
		}
		System.out.println("정말 삭제하시겠습니까? (Y/n)");
		answer = sc.next();
		if (!answer.equals("Y")) {
			System.out.println("삭제 작업이 취소되었습니다.");
			return;
		}
		int result = service.deleteMember(usrid);
		if (result == 1)
			System.out.println("작업 완료");
		else
			System.out.println("삭제 작업 실패");
	}

	private void selectProduct() {
		String productid = null;
		System.out.println(" [제품 정보 조회] ");
		System.out.println("> 제품 아이디 ");
		productid = sc.next();
		Product product = service.selectProduct(productid);
		if (product == null) {
			System.out.println("제품 정보가 없습니다.");
			return;
		}
		System.out.println(product);
	}

	private void updateMember() {
		String usrid = null;
		String usrname = null;
		int skinproblem = 1;
		System.out.println(" [정보 수정] ");
		System.out.println(" > 수정할 회원의 아이디 입력 ");
		usrid = sc.next();
		CosMember info = service.selectMember(usrid);
		if (info == null) {
			System.out.println(" 해당하는 아이디가 없습니다. ");
			return;
		}
		String password;
		System.out.println(" [비밀번호 수정] ");
		password = sc.next();
		System.out.println(" > 비밀번호 확인 ");
		String password1 = sc.next();
		if (!password1.equals(password)) {
			System.out.println("비밀 번호를 다시 입력해주세요");
			return;
		}

		while (true) {
			System.out.println(" >주요 관심사 선택");
			System.out.println(" 1) 미백 2) 노화 3) 여드름 ");
			System.out.println();

			skinproblem = sc.nextInt();

			if (!(skinproblem >= 1 && skinproblem <= 3))
				try {
					throw new Exception();
					// 내가 일부러 exception 발생시키는것.
				} catch (Exception e) {

					System.out.println("오류 : 항목을 잘못 선택했습니다.");
					sc.nextLine();
					continue; // 주요관심사를 다시 입력받도록 간다.
				}
			break; // exception이 터지지 않으면 밖으로 빠져나가도록 해준다.
		}
		CosMember m = new CosMember(usrid, password, null, skinproblem, 0);
		// CosMember(String usrid, String password, String name, int skinproblem, int
		// usrrole)
		int result = service.insertMember(m); // 아직 이 메소드를 안만들었기 때문에 그 전에는 빨간줄 표시.
		if (result == 1)
			System.out.println(" 회원 가입이 완료되었습니다.");
		else
			System.out.println("** 회원 가입을 실패했습니다.");

		CosMember member = new CosMember(usrid, password, null, skinproblem, 0);
//이름은 수정할 것이 없기 때문에 이름 부분은 null. usrrole도 기본값
		int result1 = service.updateMember(member);
		// 아직 이 메소드를 안만들었기 때문에 그 전에는 빨간줄 표시.
		if (result1 == 1)
			System.out.println(" 정보 수정이 완료되었습니다.");
		else
			System.out.println("** 정보 수정에 실패했습니다.");

	}

	private void insertMember() {
		String usrid = null;
		String password = null;
		String name = null;
		int skinproblem = 1;
		
		int userrole; /////////////// 이것을 어떻게 할 것인가!
		System.out.println(" [회원 가입] ");
		System.out.println(" > 아이디 입력 ");
		usrid = sc.next();
		CosMember info1 = service.selectMember(usrid);
		if (info1 != null) {
			System.out.println(" 중복된 아이디가 있습니다. ");
			return;
		}
		System.out.println(" > 비밀번호 입력 ");
		password = sc.next();
		System.out.println(" > 비밀번호 확인 ");
		password = sc.next();
		if (!password.equals(password)) {
			System.out.println("비밀 번호를 다시 입력해주세요");
			return;
		}
		
		CosMember info = service.selectMember(password);
		if(password.indexOf("A")==0)
			{CosMember m1 = new CosMember(usrid, password, name, skinproblem, 1);		     
		    int adresult = service.insertMember(m1);
		    if (adresult == 1)
				{System.out.println(" 관리자 가입이 완료되었습니다.");}
			else
				{System.out.println(" 회원 가입이 완료 되었습니다.");}
		    
		System.out.println(" > 이름 입력 ");
		name = sc.next();
		while (true) {
			System.out.println(" > 주요 관심사 목록");
			System.out.println(" 1) 미백 2) 노화 3) 여드름 ");
			System.out.println();
			System.out.println(" > 주요 관심사 선택");
			try {
				skinproblem = sc.nextInt();
				if (!(skinproblem >= 1 && skinproblem <= 3)) {
					throw new Exception(); // 내가 일부러 exception 발생시키는것.
				}
			} catch (Exception e) {
				System.out.println("오류 : 항목을 잘못 선택했습니다.");
				sc.nextLine();
				continue; // 주요관심사를 다시 입력받도록 간다.
			}
			break; // exception이 터지지 않으면 밖으로 빠져나가도록 해준다.
		}
		CosMember m = new CosMember(usrid, password, name, skinproblem, 0);
		
		// CosMember(String usrid, String password, String name, int skinproblem, int
		// usrrole)
		int result1 = service.insertMember(m); // 아직 이 메소드를 안만들었기 때문에 그 전에는 빨간줄 표시.
		if (result1 == 1)
			{System.out.println(" 회원 가입이 완료되었습니다.");}
		else
			{System.out.println("** 회원 가입을 실패했습니다.");}
			}
	}

	private void adminMenu() {
		System.out.println("-----------------------");
		System.out.println(" 관리자 메뉴 ");
		System.out.println(" 1)  회원 조회 ");
		System.out.println(" 2)  회원 정보 수정 ");
		System.out.println(" 3)  화장품 등록 ");
		System.out.println(" 4)  화장품 수정 ");
		System.out.println(" 5)  화장품 삭제 ");
		System.out.println(" 0)  종료 ");
		System.out.println("-----------------------");
	}

	private void userMenu() {
		System.out.println("-----------------------");
		System.out.println(" 회원 메뉴 ");
		System.out.println(" 1) 내 정보 수정 ");
		System.out.println(" 2) 화장품 조회 ");
		System.out.println(" 3) 화장품 전체조회 ");
		System.out.println(" 4) 회원 탈퇴 ");
		System.out.println(" 0)  종료 ");
		System.out.println("-----------------------");
		return;
	}

	private void startMenu() {
		System.out.println("-----------------------");
		System.out.println(" 초기 메뉴");
		System.out.println(" 1) 기존회원 로그인");
		System.out.println(" 2) 신규회원 가입");
		System.out.println(" 0) 종료");
		System.out.println("-----------------------");
	}
}