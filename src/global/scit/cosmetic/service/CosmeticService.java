package global.scit.cosmetic.service;
import java.util.List;
import java.util.Map;

import global.scit.cosmetic.dao.CosmeticDAO;
import global.scit.cosmetic.vo.CosMember;
import global.scit.cosmetic.vo.Product;
public class CosmeticService {
    // DAO 부르고
    CosmeticDAO dao = new CosmeticDAO();
    //로그인 ///이부분은 잘 몰?루
    public CosMember loginMember(String id ,String pw) {
        CosMember cosMember = new CosMember();
        cosMember.setUsrid(id);
        cosMember.setUsrpass(pw);
        cosMember = dao.loginMember(cosMember);
        return cosMember;
    }
    //신규회원가입
    public int insertMember(CosMember member) {
        int result =dao.insertMember(member);
        return result;
    }
    //회원수정
    public int updateMember(Map<String, Object> map) {
        int result =dao.updateMember(map);
        return result;
    }


    //회원조회
    public CosMember selectMember(String usrid) {
        CosMember member = dao.selectMember(usrid);
        return member;
    }
    //전체회원조회
    public List<CosMember> memberAll() {
        List<CosMember> list = dao.memberAll();
        return list;
    }
    //회원 탈퇴
    public int deleteMember(String usrid) {
        int result = dao.deleteMember(usrid);
        return result;
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
    public int updateProduct(Map<String, Object> map) {
        int result = dao.updateProduct(map);
        return result;
    }
    //화장품 삭제
    public int deleteProduct(String productid) {
        int result= dao.deleteProduct(productid);
        return result;
    }

    //화장품 전체조회
    public List<Product> productAll(){
        List<Product> list =dao.productAll();
        return list;
    }
}