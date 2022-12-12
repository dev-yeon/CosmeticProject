package global.scit.cosmetic.dao;

import global.scit.cosmetic.vo.CosMember;
import global.scit.cosmetic.vo.Product;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Map;

public class CosmeticDAO {
    //1. 접속한다
    SqlSessionFactory factory = MybatisConfig.getSessionFactory(); // 접속할 수 있는 객체
    //회원로그인 0:일반회원, 1: 관리자
    public CosMember loginMember(CosMember member) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        CosMember result = mapper.loginMember(member);
        session.commit();
        return result;
    };
    //회원 가입.한 사람의 정보를 입력.
    public int insertMember(CosMember member) {
        SqlSession session = null; //연결하는 객채는 SqlSession

        session = factory.openSession(); //연결되면 session객체의 주소가 넘어가.
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        int result = mapper.insertMember(member);
        session.commit(); // 이 코드를 써줘야지 저장이 된다. 저장한 뒤에 close 해주는 것.
        return result;
    };

    //회원수정 --
    public int updateMember(Map<String, Object> map) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        int result = mapper.updateMember(map);
        session.commit();
        return result;
    };

    // 회원 탈퇴--
    public int deleteMember(String usrid) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        int result = mapper.deleteMember(usrid);
        session.commit();
        return result;
    };

    //회원조회 --
    public CosMember selectMember(String usrid) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        CosMember cosmember = mapper.selectMember(usrid);
        return cosmember;
    }
    //회원 전체조회 --
    public List<CosMember> memberAll() {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        List<CosMember> List = mapper.memberAll();
        return List;
    }
    //화장품 조회	--
    public Product selectProduct(String productid) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        Product product = mapper.selectProduct(productid);
        return product;
    };
    //화장품 등록--
    public int insertProduct(Product product) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        int result = mapper.insertProduct(product);
        session.commit();
        return result;
    };
    //화장품 수정
    public int updateProduct(Map<String, Object> map) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        int result = mapper.updateProduct(map);
        session.commit();
        return result;
    };
    //화장품 삭제
    public int deleteProduct(String productid) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        int result = mapper.deleteProduct(productid);
        session.commit();
        return result;
    };
    //화장품 전체조회
    public List<Product> productAll(){
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        List<Product> List = mapper.productAll();
        return List;
    }

    public List<Product> selectProductByProblem(int skinproblem) {
        SqlSession session = null;
        session = factory.openSession();
        CosmeticMapper mapper = session.getMapper(CosmeticMapper.class);
        List<Product> List = mapper.selectProductByProblem(skinproblem);
        return List;
    }
}