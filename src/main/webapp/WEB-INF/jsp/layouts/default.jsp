<!-- layout.jsp -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/jsp/fragments/head.jsp" %>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/fragments/header.jsp" %>
    <div class="content">
        <jsp:include page="${content}" />
    </div>
    <%@ include file="/WEB-INF/jsp/fragments/footer.jsp" %>
</body>
</html>
