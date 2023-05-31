<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<div class="container myBorder">
    <form action='/transaction/add' method='post' modelAttribute="addTransaction">
        <h4> Add New Transaction:</h4>
        <table class='table table-hover table-responsive table-bordered rounded-lg'>
            <tr>
                <td><b>Name</b></td>
                <td><input type='text' name='name' class='form-control' size="50" required value="${transaction.name}"/>
                </td>
            </tr>
            <tr>
                <td><b>Transaction Type</b></td>
                <td><input type='text' name='transType' class='form-control' min="1950" max="2020" required
                           value="${transaction.transType}"/></td>
            </tr>
            <tr>
                <td><b>Amount</b></td>
                <td><input type='number' name='amount' class='form-control' required value="${transaction.amount}"/></td>
            </tr>
            <tr>
                <td><b>Transaction date</b></td>
                <td><input type='date' name='entryDate' class='form-control' required size="20"
                           value="${transaction.entryDate}"/></td>

            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" class="btn btn-primary">Save Transaction Information</button>
                </td>
            </tr>

        </table>
    </form>
</div>

<jsp:include page="footer.jsp"></jsp:include>