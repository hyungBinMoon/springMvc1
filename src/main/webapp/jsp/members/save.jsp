<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="hello.springMvc.domain.member.Member" %>
<%@ page import="hello.springMvc.domain.member.MemberRepository" %>
<%
	//request, response 사용 가능
	MemberRepository memberRepository = MemberRepository.getInstance();

	System.out.println("MemberSaveServlet.service");
	String username = request.getParameter("username");
	int age = Integer.parseInt(request.getParameter("age")); 
	
	Member member = new Member(username, age);
	memberRepository.save(member);
%>    
    
    
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
success!
<ul>
	<li>id=<%=member.getId()%></li>
	<li>username=<%=member.getUsername() %></li>
	<li>age=<%=member.getAge() %></li>
</ul>
<a href="/index.html">main</a>
</body>
</html>