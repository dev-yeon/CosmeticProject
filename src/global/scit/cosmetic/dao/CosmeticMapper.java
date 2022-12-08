package global.scit.cosmetic.dao;

import java.util.List;

import global.scit.cosmetic.vo.CosMember;
import global.scit.cosmetic.vo.Product;
import global.scit.cosmetic.vo.ProductInfo;


public interface CosmeticMapper {
	public int loginMember(CosMember member); //회원로그인 0:일반회원, 1: 관리자 
	public int insertMember(CosMember member); //신규회원가입
	public int updateMember(CosMember member); //회원수정
	
	public CosMember selectMember(String usrId);//회원 조회
	public int deleteMember(CosMember member);//회원 탈퇴
	public List<CosMember> memberAll(); //회원 전체조회 
	public int checklogin(String usrId,String usrPw); //회원 로그인 
	
		
	public Product selectProduct(String productid); //화장품 조회
	public int insertProduct(Product product); //화장품 등록
	public int updateProduct(Product product); //화장품 수정
	public int deleteProduct(String productid); //화장품 삭제
	
	public List<Product> productAll(); //화장품 전체조회		
	public CosMember deleteMember(String usrid);
}

/**
 * 	select
		c.usrid 
		,c.usrname
	    ,c.email	
		,c.skinproblem
		,p.productid
		,p.productname
		,p.producttype
		,p.productsolution
		from
		cosmember c ,
		 cosproduct p
		where
	c.skinproblem =p.productsolution
		and
		c.skinproblem = #{productsolution}
*/