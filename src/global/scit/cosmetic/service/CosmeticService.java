package global.scit.cosmetic.service;

import java.util.List;

import global.scit.cosmetic.dao.CosmeticDAO;
import global.scit.cosmetic.vo.CosMember;
import global.scit.cosmetic.vo.Product;
import global.scit.cosmetic.vo.ProductInfo;


public class CosmeticService {
	// DAO 부르고
	CosmeticDAO dao = new CosmeticDAO();
	
	CosMember cos = new CosMember();
	Product pos = new Product();
	
	//로그인 ///이부분은 잘 몰?루 
	public int loginMember(CosMember member) {
		
		int result =dao.loginMember(member);		
		return result;		
	}
	
	//신규회원가입
	public int insertMember(CosMember member) {
		int result =dao.insertMember(member);
		return result;		
	}

	//회원 한명 조회	
	public CosMember selectMember(String usrid) {
		CosMember cosmember = dao.selectMember(usrid);
		return cosmember;
	}
	//회원 전체조회
		public List <CosMember> memberAll(){
			List<CosMember> list =dao.memberAll();
			return list;
		}

	//회원수정
	public int updateMember(CosMember member) {
		int result =dao.updateMember(member);
		return result;
	}
	
	//회원 삭제
	public int deleteMember(String usrid) {
		int result = dao.deleteMember(usrid);		
		return 0;
	}
	//화장품 조회
	public Product selectProduct(String productid) {
		Product product = dao.selectProduct(productid);
		return product;
	}
	//화장품 등록
	public int insertProduct(Product product) {
		int result = dao.insertProduct(product);
		return result;
	}
	//화장품 수정
	public int updateProduct(Product product) {
		int result = dao.updateProduct(product);
		return result;
	}
	//화장품 삭제
	public int deleteProduct(String productid) {
		int result= dao.deleteProduct(productid);
		return 0;
	}
	
	//화장품 전체조회
	public List<Product> productAll(){
		List<Product> list =dao.productAll();
		return list;
	}


}
