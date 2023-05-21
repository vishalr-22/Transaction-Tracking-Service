<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container-fluid bg-light myBorder">
    <form action="/transaction/search" method="post">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-2 pt-3">
                <div class="form-group ">
                    <input type="number" placeholder="By year" name="schoolYear" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="number" placeholder="By campus" name="campus" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="number" placeholder="By grade level" name="gradeLevel" class="form-control">
                </div>
            </div>
            <div class="col-md-2 pt-3">
                <div class="form-group">
                    <input type="text" placeholder="By name" name="name" class="form-control">
                </div>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary btn-block" target=" ">Search</button>
            </div>
        </div>
    </form>
</div>
<div><br></div>

<div class="container-fluid bg-light myBorder">
    <h4>transactions Records:</h4>
    <table class="table table-bordered table-striped text-center">
        <thead>
        <tr class="text-center">
            <%--<th><b>transaction Id</b></th>--%>
            <th class="text-center"><b>School Year</b></th>
            <th class="text-center"><b>Campus</b></th>
            <th class="text-center"><b>Entry Date</b></th>
            <th class="text-center"><b>Grade Level</b></th>
            <th class="text-center"><b>Name</b></th>
            <th class="text-center" colspan='2'><b> Operation</b></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${list}" var="transaction">
            <tr>
                <%--<td><c:out value="${transaction.transactionId}"></c:out></td>--%>
                <td><c:out value="${transaction.name}"></c:out></td>
                <td><c:out value="${transaction.tran_type}"></c:out></td>
                <td><c:out value="${transaction.entry_date}"></c:out></td>
                <td><c:out value="${transaction.amount}"></c:out></td>
                <td>
                    <a href="/transaction/edit?transactionId=${transaction.transactionId}">
                        <button type="submit" class="btn btn-primary">Edit transaction</button>
                    </a>

                    <a href="/transaction/delete?transactionId=${transaction.transactionId}">
                        <button type="submit" class="btn btn-danger"
                                onclick="alert(' !! Warning !! transaction record will be deleted.')">Delete transaction
                        </button>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</div>
<div><br></div>