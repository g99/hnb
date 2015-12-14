<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="PAGESIZE" value="5"/>
<c:set var="GROUPSIZE" value="3"/>
<c:choose>
	<c:when test="${count%PAGESIZE eq 0}"> <!-- 딱 떨어지는 경우  -->
		<c:set var="totPage" value="${count/PAGESIZE}"></c:set>
	</c:when>
	<c:otherwise> <!-- 나머지가있는 경우 -->
		<c:set var="totPage" value="${count/PAGESIZE+1}"></c:set>
	</c:otherwise>	
</c:choose>
<c:set var="startPage" value="${pageNo - ((pageNo-1) % GROUPSIZE)}"></c:set>

<c:choose>
	<c:when test="${startPage + GROUPSIZE - 1 le totPage}">
		<c:set var="lastPage" value="${startPage + GROUPSIZE - 1}"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="lastPage" value="${totPage}"></c:set>
	</c:otherwise>
</c:choose>
<link rel="stylesheet" href="${css}/board.css" />

<div id="boardList">
<TABLE WIDTH=1000 HEIGHT=40 BORDER=0 CELLSPACING=1 CELLPADDING=1 ALIGN=CENTER>
	<TR BGCOLOR=#A0A0A0>
		<TD ALIGN=CENTER><FONT SIZE=4><B>회원목록</B></FONT></TD>
	</TR>
</TABLE>

 
<TABLE WIDTH=1000 BORDER=1 CELLSPACING=0 CELLPADDING=1 ALIGN=CENTER>
	<tr>
		<td colspan="5" style="text-align:right">총 회원수 : ${count} </td>
	</tr>
	<TR ALIGN=CENTER>
		<TD WIDTH=10%><B>번호</B></TD>
		<TD WIDTH=20%><B>아이디</B></TD>
		<TD WIDTH=20%><B>회원명</B></TD>
		<TD WIDTH=30%><B>이메일</B></TD>
		<TD WIDTH=18%><B>가입일</B></TD>
	</TR>
	<c:forEach items="${member}" var="user" varStatus="status">
	<TR>
		<TD WIDTH=10% ALIGN=CENTER>${status.index + 1}</TD>
		<TD WIDTH=20% ALIGN=CENTER>${user.id}</TD>
		<TD WIDTH=20% ALIGN=CENTER><A HREF="">${user.name}</A></TD>
		<TD WIDTH=30% ALIGN=CENTER>${user.email}</TD>
		<TD WIDTH=18% ALIGN=CENTER>${user.regdate}</TD>
	</TR>
	</c:forEach>
</TABLE>

<FORM NAME="BoardSearch" METHOD=POST action="BoardList.jsp">

<TABLE WIDTH=1000 HEIGHT=50 BORDER=0 CELLSPACING=1 CELLPADDING=1 ALIGN=CENTER>

	<TR>
		<TD ALIGN=LEFT WIDTH=100>
			<IMG SRC="${img}/btn_new.gif" onClick="javascript:location.replace('BoardWrite.jsp')"; STYLE=CURSOR:HAND>
		</TD>
		<TD WIDTH=320 ALIGN=CENTER>
			<%-- <IMG SRC="${img}/btn_bf_block.gif">&nbsp; --%>
			<c:if test="${startPage - GROUPSIZE gt 0}">
				<a href="${context}/member/boardList/${startpage - GROUPSIZE}">
					<IMG SRC="${img}/btn_bf_page.gif">&nbsp;
				</a>
			</c:if>   
			<c:forEach begin="${startPage}" end="${lastPage}" step="1" varStatus="status">
				<c:choose>
					<c:when test="${status.index eq pageNo}">
						<font style="color:red;">${status.index}</font>
					</c:when>
					<c:otherwise>
						<a href="${context}/member/boardList/${status.index}">${status.index}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>	
			<c:if test="${startPage - GROUPSIZE le totPage}">
				<a href="${context}/member/boardList/${startpage + GROUPSIZE}">
					<IMG SRC="${img}/btn_nxt_page.gif">&nbsp; 
				</a>
			</c:if>
			<%-- <IMG SRC="${img}/btn_nxt_block.gif"> --%>    	    		     
		</TD>
		<TD WIDTH=200 ALIGN=RIGHT>
			<SELECT NAME="column" SIZE=1>
				<OPTION VALUE="" SELECTED>선택</OPTION>
				<OPTION VALUE="UsrSubject">제목</OPTION>
				<OPTION VALUE="UsrContent">내용</OPTION>
			</SELECT> 
			<INPUT TYPE=TEXT NAME="key" SIZE=10 MAXLENGTH=20> 
			<IMG SRC="${img}/btn_search.gif" ALIGN=absmiddle STYLE=CURSOR:HAND>
		</TD>    
	</TR>
	
</TABLE>

</FORM>

</div>

<!-- 
전체 레코드수 - (현재페이지번호-1 * 한 페이지당 레코드수 + 현재게시물출력순서)
count - ( (nowpage-1) * rowPerPage + status.index) 
-->