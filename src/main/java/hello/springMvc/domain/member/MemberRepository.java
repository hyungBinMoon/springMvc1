package hello.springMvc.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
	
	private static Map<Long, Member> store = new HashMap<>();  //Long = key,
	
	private static long sequence = 0L;
	
	private static MemberRepository instance = new MemberRepository(); // 싱글톤으로 생성 시, 

	public static MemberRepository getInstance() {
		return instance;
	}
	
	private MemberRepository() { 
		// 싱글톤 생성 시, private 로 막아야함.
	}
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	public List<Member> findAll(){
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}
}
