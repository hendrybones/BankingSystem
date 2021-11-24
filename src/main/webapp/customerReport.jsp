<%--
  Created by IntelliJ IDEA.
  User: mr bones
  Date: 11/19/2021
  Time: 12:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>customer report</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <style type="text/css">
        .jumbotron{
            min-height: 200px;
            padding:1rem;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand">Reports/Statuses for bank users</a>

        <!-- Links -->
        <ul class="nav navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Welcome ${username}</a>
            </li>
            <li class="nav-item">
                <form action="Logout"><button class="nav-link">Logout</button></form>
            </li>
        </ul>
    </nav>
</header>
<%
    if(session.getAttribute("username")==null){
        response.sendRedirect("login.jsp");
    }
%>
<%
    response.setHeader("Cache-control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");
%>
<div class="container">
    <br><br>
    <p class="font-weight-bolder text-left"><h4><u>Customer Collector Report</u></h4></p>
    <div class="container">
        <form action="customerCollectorReport.jsp" method="get">
            <div class="form-check form-check-inline col-md-4 float-right">
                <input class="form-check-input" type="radio" name="options" id="inlineRadio1" value="1">
                <label class="form-check-label" for="inlineRadio1">View Individual Collector Report</label>
            </div>
            <div class="form-check form-check-inline col-md-4 float-right">
                <input class="form-check-input" type="radio" name="options" id="inlineRadio2" value="2">
                <label class="form-check-label" for="inlineRadio2">Collectors report for Jewel loan</label>
            </div>
            <div class="form-check form-check-inline col-md-3 float-right">
                <input class="form-check-input" type="radio" name="options" id="inlineRadio3" value="3">
                <label class="form-check-label" for="inlineRadio2">Collectors report for Credit loan</label>
            </div>
            <br><br>
            <div class="form-row">
                <div class="col-md-4">
                    <div class="jumbotron">
                        <div class="container" >
                            <p class="lead">Collector Name</p>
                            <hr class="my-4">
                            <input type="text" name="cName" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="row">
                        <div class="form-group col-md-1"></div>
                        <div class="form-group col-md-4 float-right">
                            From: <input type="date" class="datepicker" data-date-format="mm/dd/yyyy" name="from">
                        </div>
                        <div class="form-group col-md-4 float-right">
                            To: <input type="date" class="datepicker" data-date-format="mm/dd/yyyy" name="to">
                        </div>
                    </div>
                    <div class="form-row"></div>
                    <div class="row">
                        <div class="col-4"></div>
                        <div class="form-row"></div>
                        <button type="submit" class="btn btn-dark">Generate Report</button>
                    </div>
                </div>
            </div>

        </form>
    </div>

    <%
        String s=request.getParameter("options");
        if(s==null) System.out.println("Select some option");
        else{
            String url="jdbc:mysql://localhost:3306/bankproject";
            String userName="root";
            String pwd="xxxxxx";
            String to=request.getParameter("to");
            String fromDate=request.getParameter("from");
            String cName=request.getParameter("cName");
            if(fromDate.equals("")) fromDate="1999-03-03";
            if(to.equals("")) to="2030-03-03";
            if(s.equals("1")){
                String sql="select borrowed_amount/no_of_installments as amount_collected,date_of_payment,(select name from borrower where borrower.b_id=loans.b_id) as customer,loans.b_id, collector_id, (select name from collector where collector.collector_id=loans.collector_id) as collector_name from installments left join loans on loans.loan_id=installments.loan_id where date_of_payment>\'"+fromDate+"\' and date_of_payment<\'"+to+"\' having collector_name like \'%"+cName+"%\' order by amount_paid desc;";
    %>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Amount Collected</th>
            <th scope="col">Date</th>
            <th scope="col">Customer</th>
            <th scope="col">Customer ID</th>
            <th scope="col">Collector</th>
            <th scope="col">Collector ID</th>
        </tr>
        </thead>
        <tbody>
        <%@page import="java.sql.*" %>
        <%
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(url,userName,pwd);
                    PreparedStatement st=con.prepareStatement(sql);
                    ResultSet rs=st.executeQuery();int i=1;
                    while(rs.next()){
                    }
                    con.close();
                }catch(Exception e){System.out.println(e);}}
        %>
        </tbody>
    </table>
    <%
        if(s.equals("2")){
            String sql="select sum(borrowed_amount/no_of_installments) as amount_collected, collector_id, (select name from collector where collector.collector_id=loans.collector_id) as collector_name from installments left join loans on loans.loan_id=installments.loan_id where loan_type=\"jewel\" group by collector_id having collector_name like \'%"+cName+"%\' order by amount_paid desc";
    %>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Total Amount Collected</th>
            <th scope="col">Collector Id</th>
            <th scope="col">Collector Name</th>
        </tr>
        </thead>
        <tbody>
        <%@page import="java.sql.*" %>
        <%    try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,userName,pwd);
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs=st.executeQuery();int i=1;
            while(rs.next()){
            }
            con.close();
        }catch(Exception e){System.out.println(e);}}
        %>
        </tbody>
    </table>
    <% } %>
</div>

</body>
</html>
