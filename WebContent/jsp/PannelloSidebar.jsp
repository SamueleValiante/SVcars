<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pagina con layout affiancato</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/PannelloSidebar.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/visualizzaAnnunci.css">
</head>
<body>
    <div class="layout-container">
        <jsp:include page="SidebarSX.jsp" />
        <jsp:include page="VisualizzaAnnunci.jsp" />
    </div>
</body>
</html>