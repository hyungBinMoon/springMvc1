package hello.springMvc.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	
	private Long id;
	private String username;
	private int age;
	
	public Member() {  //기본 생성
	}
	
	public Member(String username, int age) { // 인자를 두 개 받는 생성
		this.username = username;
		this.age = age;
		 
	}
}
