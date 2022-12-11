package global.scit.cosmetic.dao;

import java.util.List;
import java.util.Map;

import global.scit.cosmetic.vo.CosMember;
import global.scit.cosmetic.vo.Product;


public interface CosmeticMapper {
	public CosMember loginMember(CosMember member); //회원로그인 0:일반회원, 1: 관리자
	public int insertMember(CosMember member); //신규회원가입
	public int updateMember(Map<String, Object> member); //회원수정
	public int  deleteMember(String usrid);// 회원탈퇴
	
	public CosMember selectMember(String usrId);//회원 조회
	public List<CosMember> memberAll(); //회원 전체조회

	public Product selectProduct(String productid); //화장품 조회
	public int insertProduct(Product product); //화장품 등록
	public int updateProduct(Map<String, Object> map); //화장품 수정
	public int deleteProduct(String productid); //화장품 삭제
	
	public List<Product> productAll(); //화장품 전체조회		

	List<Product> selectProductByProblem(int skinproblem); //화장품관심사별 출력
}
