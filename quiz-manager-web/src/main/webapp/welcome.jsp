<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
//<%
//boolean auth = (Boolean) session.getAttribute("authenticated");
//if (! auth){
  //  response.sendRedirect("index.html");
//}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>

<script language="javascript">
function startquiz()
{
   document.welcome.action="/startquiz";
   document.welcoome.submit();
}
function edit()
{
   document.welcome.action="edit.jsp";
   document.welcome.submit();
}
</script>

<body>
<h1>Welcome, <%=session.getAttribute("userName") %></h1>
<div class="container">
		<form name="welcome" id="welcome" method="post">
			<div class="row">
			<button class="btn btn-primary" onclick="startquiz" type="button" >start quiz</button>
				<button class="btn btn-primary" onclick="edit" type="button">edit</button>
			</div>
		</form>
	</div>


</body>
</html>