package global.scit.cosmetic.ui;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import global.scit.cosmetic.service.CosmeticService;
import global.scit.cosmetic.vo.CosMember;
import global.scit.cosmetic.vo.Product;

import java.util.*;
/**
 * CosmeticUI
 * 
 * USER ROLE 0: 회원 1: 관리자
 */
public class CosmeticUI {
    Scanner sc = new Scanner(System.in);
    CosmeticService service = new CosmeticService();
    CosMember login = null;

    Map<String, Object> map = new HashMap<String, Object>();

    public CosmeticUI() {
        String choice;

        while(login == null)
         { //일단 스타트 메뉴 실행
            startMenu();
            choice = sc.next();
            switch (choice) {
                case "1": //기존회원 로그인
                    login();
                    break;
                case "2":
                    insertMember();//신규회원 가입
                    break;
                case "0": {
                    System.out.println("** 프로그램 종료합니다. ");
                    System.exit(1);
                    break;
                }
                default: {
                    System.out.println("다시 선택해 주세요");
                    sc.nextLine(); // 버퍼에 남은 데이터 싹지우기
                }
            }//switch
        }//whilewhile(!login);
        // 일반회원메뉴
        if(login.getUsrrole() == 0 ){ // 일반 회원
            while (true) {
                userMenu();
                choice = sc.next();
                switch (choice) {
                    case "1":
                        updateMember();
                        break; // 내 정보 수정
                    case "2":
                        selectProduct(); // 화장품 조회
                        break;
                    case "3":
                        productAll(); // 화장품 전체조회
                        break;

                    case "4":
                        selectProductByProblem(); // 화장품 관심사별로 조회
                        break;
                    case "5":
                        deleteMember(); // 내 탈퇴
                        break;
                    case "0": {
                        System.out.println("** 프로그램 종료합니다. ");
                        return;
                    }
                    default: {
                        System.out.println("다시 선택해 주세요");
                        sc.nextLine(); // 버퍼에 남은 데이터 싹지우기
                    }
                }//switch
            }//while
        } else if (login.getUsrrole() == 1) { // 관리자
            while (true) {
                adminMenu();// 관리자회원메뉴
                choice = sc.next();
                switch (choice) {
                    case "1":
                        selectMember(); // 회원정보 조회
                        break;
                    case "2":
                        updateMember(); // 회원정보 수정
                        break;
                    case "3":
                        insertProduct(); // 화장품 등록
                        break;
                    case "4":
                        updateProduct(); // 화장품 수정
                        break;
                    case "5":
                        deleteProduct(); // 화장품 삭제
                        break;
                    case "6":
                        productAll(); // 화장품 전체조회
                        break;
                    case "7":
                        memberAll(); //회원 전체 조회
                        break;
                    case "0": {
                        System.out.println("** 프로그램 종료합니다. ");
                        System.exit(1);
                        return;
                    }
                    default: {
                        System.out.println("다시 선택해 주세요");
                        sc.nextLine(); // 버퍼에 남은 데이터 싹지우기
                    }
                }//switch
            }//while
        } else {
            System.out.println("");
        }
    }// CosmeticUI()

    private void selectProductByProblem() {
        System.out.println(" [ 나의 피부고민별로 화장품 조회] ");
        List<Product> list = service.selectProductByProblem(login.getSkinproblem());
        System.out.println(" ");
        if (list.size() == 0) {
            System.out.println("** 화장품정보가 없습니다.");
            return;
        }
        Iterator<Product> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    private void memberAll() {
        System.out.println(" [전체 회원 조회] ");
        List<CosMember> list = service.memberAll();
        if (list.size() == 0) {
            System.out.println("** 회원정보가 없습니다.");
            return;
        }
        Iterator<CosMember> iter = list.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    private void productAll() {
        System.out.println("\n [전체 상품 조회] ");
        List<Product> list = service.productAll();

        if (list.size() == 0) {
            System.out.println("** 상품정보가 없습니다.");
            return;
        }
        Iterator<Product> iter = list.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
    private void login() {
        String usrid;
        String usrpass;
        sc.nextLine();
        int logcount = 0;
        // 최대 10번의 기회를 준다

        for (int i = 0; i <= 10; i++) {
            ++logcount;
            System.out.println("기존 회원 아이디를 입력하세요 ::: ");
            usrid = sc.next();
            System.out.println("기존 회원 패스워드 를 입력하세요 : ");
            usrpass = sc.next();

            login = service.loginMember(usrid, usrpass);

            if (login == null)
                System.out.println(" 아이디 혹은 비밀번호를 확인하세요. ");
            else {
                System.out.println(usrid + " 님께서 로그인 하셨습니다.");
                break;
            }

        }
        if (logcount > 10) {
            System.out.println("아이디와 비밀번호를 다시 확인해주세요.");
            System.out.println("** 프로그램 종료합니다. ");
            System.exit(1);
        }
    }

    private void deleteProduct() {
        String productid, answer;
        System.out.println(" \n화장품 삭제 ");
        System.out.println(" c> 제품 입력 : ");
        productid = sc.next();
        Product product = service.selectProduct(productid);
        if (product == null) {
            System.out.println("\n 해당하는 제품이 없습니다.");
            return;
        }
        System.out.println("정말 삭제하시겠습니까? (Y/n)");
        answer = sc.next();
        if (!answer.equals("Y")) {
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
        String productname;
        String choice;
        int productprice = 0;
        int producttype = 0;
        int productsolution = 0;
        System.out.println("화장품의 정보를 수정합니다.");
        System.out.println("수정할 화장품의 아이디를 입력해주세요.");
        productid = sc.next();
        Product product = service.selectProduct(productid);
        if (product == null) {
            System.out.println(" 중복된 제품이 있습니다. ");
            return;
        }
        System.out.println(product + "를 수정합니다.");
        productUpdateMenu();
        Map<String, Object> map = new HashMap<>();
        map.put("productid", productid);
        String temp;
        while (true) {
            choice = sc.next();
            switch (choice) {
                case "1":// 1) 화장품 이름 수정
                    temp = "1";
                    System.out.println("수정할 화장품의 이름을 입력해주세요.");
                    productname = sc.next();
                    map.put("temp", temp);
                    map.put("productname", productname);
                    break;
// 2) 화장품 가격 수정
                case "2":
                    temp = "2";
                    System.out.println("수정할 화장품의 가격을 입력해주세요.");
                    productprice = sc.nextInt();
                    map.put("temp", temp);
                    map.put("productprice", productprice);
                    break;
// 3) 화장품 타입 수정
                case "3":
                    temp = "3";
                    System.out.println("수정할 화장품의 타입을 입력해주세요.");
                    producttype = sc.nextInt();
                    map.put("temp", temp);
                    map.put("producttype", producttype);
                    break;
// 4) 화장품 기능 수정
                case "4":
                    temp = "4";
                    System.out.println("수정할 화장품의 기능을 입력해주세요.");
                    productsolution = sc.nextInt();
                    map.put("temp", temp);
                    map.put("productsolution", productsolution);
                    break;
// 0) 이 전 메뉴로 가기
                case "5":
                    return;
            }// switch
            int result = service.updateProduct(map);
        } // while
    }
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
        if (product != null) {
            System.out.println(" 해당 제품이 이미 존재합니다. ");
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
        System.out.println(" [회원 정보 조회] ");
        System.out.println("> 회원 아이디 ");
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
        System.out.println(" > 내 아이디 입력 : ");
        usrid = sc.next();

        if (!login.getUsrid().equals(usrid)) {
            System.out.println("아이디가 일치하지 않습니다.");
            return;
        }
        System.out.println("정말 삭제하시겠습니까? (Y/n)");
        answer = sc.next();
        if (!answer.equals("Y")) {
            System.out.println("삭제 작업이 취소되었습니다.");
            return;
        }
        int result = service.deleteMember(usrid);
        if (result == 1) {
            System.out.println("작업 완료");
            System.exit(0);
        }
        else
            System.out.println("삭제 작업 실패");
    }
    private void selectProduct() {
        String productid;
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
        String usrid = login.getUsrid();
        String password = null;
        String newpassword = null;
        String newpasswordcheck = null;
        String usrname = null;
        String email;
        String choice;
        int skinproblem = 1;
        int usrrole = 1;

        System.out.println(" [정보 수정] ");
        System.out.println("나의 회원 정보를 수정합니다.");
        System.out.println("다시 로그인 해주세요.");

        System.out.println("ID 입력");
        String newId = sc.next();
        System.out.println("PW 입력");
        String newPw = sc.next();
        CosMember newMember = service.loginMember(newId, newPw);
        if(newMember == null) {
            System.out.println("ID나 PW를 확인해주세요.");
            return;
        } else if (!newMember.getUsrid().equals(login.getUsrid())) {
            System.out.println("ID나 PW를 확인해주세요.");
            return;
        }
        while (login != null) { //login 된 상태에서.
            Map<String,Object> map = new HashMap<>();
            //System.out.println(" > 수정할 회원의 아이디 입력 ");
           // usrid = sc.next();
            map.put("usrid", usrid); //아이디를 한번 더 입력하는게 아니라, 갖고있는 정보로 어쩌구.
            String temp;
            updatemenu(); //메뉴 띄우고
            choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println(" 1) 회원 이름 수정 ");
                    temp="1";
                    System.out.println(" > 수정할 회원의 이름 입력 ");
                    usrname = sc.next();
                    map.put("temp",temp);
                    map.put("usrname",usrname);
                    service.updateMember(map);
                    login.setUsrname(usrname);
                    System.out.println(" 변경된 정보 :"+service.selectMember(usrid));
                   return;

                case "2":
                    System.out.println("2) 비밀번호 수정 ");
                    temp="2";
                    System.out.println(" > 기존 비밀번호를 입력해주세요 ");
                    password = sc.next();
                    if (service.selectMember(usrid).getUsrpass().equals(login.getUsrpass())) //기존비번과 입력한 비번이 같을 때.
                    {
                        System.out.println(" > 새로 입력하실 비밀번호를 입력해주세요 ");
                        newpassword = sc.next();
                        System.out.println(" > 비밀번호 확인 ");
                        newpasswordcheck = sc.next();
                    } else if (!newpassword.equals(newpasswordcheck)) {
                        System.out.println("비밀 번호를 다시 입력해주세요");
                    }
                    map.put("temp",temp);
                    map.put("newpassword",newpassword);
                    service.updateMember(map);
                    login.setUsrpass(newpassword);
                    System.out.println("비밀번호 수정이 완료되었습니다. ");
                    return;
                case "3":
                    System.out.println("3) 이메일 수정 ");
                    temp="3";
                    System.out.print(" 기존 이메일 : " + service.selectMember(usrid).getEmail());
                    System.out.println("이메일을 다시 입력해주세요");
                    email = sc.next();
                    map.put("temp",temp);
                    map.put("email",email);
                    System.out.println("이메일 수정이 완료되었습니다. ");
                    service.updateMember(map);
                    login.setEmail(email);
                    System.out.println(" 변경된 정보 :"+service.selectMember(usrid));

                    return;
                case "4":
                    temp="4";
                    System.out.println(" 4) 피부고민 수정 ");
                    System.out.println(" >주요 관심사 선택");
                    System.out.println(" 1) 미백 2) 노화 3) 여드름 ");

                    skinproblem=sc.nextInt();

                    map.put("temp",temp);
                    map.put("skinproblem",skinproblem);
                    System.out.println("피부 고민 수정이 완료되었습니다. ");
                    service.updateMember(map);
                    login.setSkinproblem(skinproblem);
                    System.out.println(" 변경된 정보 :"+service.selectMember(usrid));
                    return;
                case "0":
                    System.out.println("이전메뉴로 이동합니다.");
                    return;
            }
        }//loginTrue
    }// updateMember()
    private void insertMember() {
        String usrid;
        String usrname = null;
        String password, repassword;
        String email = null;
        int skinproblem = 0;
        int userrole;
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
        repassword = sc.next();
        if (!repassword.equals(password)) {
            System.out.println("비밀 번호를 다시 입력해주세요");
            return;
        }
        System.out.println(" > 이름 입력 ");
        usrname = sc.next();
        System.out.println(" > 이메일 입력 ");
        email = sc.next();

        while (true) {
            System.out.println(" > 주요 관심사 목록");
            System.out.println(" 1) 미백 2) 노화 3) 여드름 ");
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
        CosMember m;
        if (password.indexOf("A") == 0) //password의 첫자가 A면 관리자 1
        {
            m = new CosMember(usrid, password, usrname, email, skinproblem, 1); //관리자
            int admresult = service.insertMember(m);
            if (admresult == 1) {
                System.out.println(" 관리자 가입이 완료되었습니다.");
                login = m;
            }
        } else if (password.indexOf("A") != 0) {
            m = new CosMember(usrid, password, usrname, email, skinproblem, 0); //회원
            int memresult = service.insertMember(m);
            if (memresult == 1) {
                System.out.println(" 맴버 가입이 완료되었습니다.");
            }
            login = m;
        } else {
            System.out.println(" 회원 가입 실패 하였습니다. ");
        }
    }
    private void updatemenu() {
        System.out.println("------- 수정 메뉴 -------");
        System.out.println(" 1) 회원 이름 수정 ");
        System.out.println(" 2) 비밀번호 수정 ");
        System.out.println(" 3) 이메일 수정 ");
        System.out.println(" 4) 피부고민 수정 ");
        System.out.println(" 0)  종료 ");
        System.out.println(">>>");
    }

    private void adminMenu() {
        System.out.println("------  관리자 메뉴  ------");
        System.out.println(" 1)  회원 정보 조회 ");
        System.out.println(" 2)  회원 정보 수정 ");
        System.out.println(" 3)  화장품 등록 ");
        System.out.println(" 4)  화장품 수정 ");
        System.out.println(" 5)  화장품 삭제 ");
        System.out.println(" 6)  화장품 전체조회 ");
        System.out.println(" 7)  회원  전체 조회 ");
        System.out.println(" 0)  종료 ");
        System.out.println(">>>");
    }
    private void userMenu() {
        System.out.println("------- 회원 메뉴 -------");
        System.out.println(" 회원 메뉴 ");
        System.out.println(" 1) 내 정보 수정 ");
        System.out.println(" 2) 화장품 조회 ");
        System.out.println(" 3) 화장품 전체조회 ");
        System.out.println(" 4) 화장품 관심사별로 조회  ");
        System.out.println(" 5) 회원 탈퇴 ");
        System.out.println(" 0)  종료 ");
        System.out.println(">>>");
    }
    private void startMenu() {
        System.out.println("-------- 초기 메뉴 ------");
        System.out.println(" 1) 기존회원 로그인");
        System.out.println(" 2) 신규회원 가입");
        System.out.println(" 0) 종료");
        System.out.println(">>>");
    }
}