<%-- 
    Document   : admin
    Created on : Aug 19, 2019, 11:10:15 PM
    Author     : The Thong
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin - Dashboard</title>

        <!-- Custom fonts for this template-->
        <link href="/HomeStayProject/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <!-- Page level plugin CSS-->
        <link href="/HomeStayProject/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="/HomeStayProject/css/sb-admin.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

            <a class="navbar-brand mr-1" href="/HomeStayProject/admin/admin.jsp">Admin Management</a>

            <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
                <i class="fas fa-bars"></i>
            </button>

            <!-- Navbar Search -->
            <!--            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>-->

            <!-- Navbar -->
            <ul class="navbar-nav ml-auto ml-md-0">


                <li class="nav-item dropdown no-arrow">
                    <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-user-circle fa-fw"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                        <a class="dropdown-item" href="#">Edit Profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
                    </div>
                </li>
            </ul>

        </nav>

        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="sidebar navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" >
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Account</span>
                    </a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link" href="/HomeStayProject/admin/service.jsp">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Service</span>
                    </a>

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/HomeStayProject/admin/room.jsp">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Room</span></a>
                </li>
            </ul>

            <div id="content-wrapper">

                <div class="container-fluid">

                    <h2>Search</h2>
                    <form action="/HomeStayProject/MainController" method="POST">
                        Username: <input type="text" name="txtSearch"/>
                        <input type="submit" name="action" value="Search"/>
                    </form>
                    <c:if test="${requestScope.INFO != null}">
                        <c:if test="${not empty requestScope.INFO}" var="listAccount">
                            <div class="card mb-3">
                                <div class="card-header">
                                    <i class="fas fa-table"></i>
                                    Account Table</div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>No</th>
                                                    <th>Username</th>
                                                    <th>Address</th>
                                                    <th>Role</th>
                                                    <th>Delete</th>
                                                    <th>Edit</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.INFO}" var="account" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>${account.username}</td>
                                                        <td>${account.address}</td>
                                                        <td>${account.role}</td>
                                                        <td>
                                                            <c:url value="MainController" var="DeleteLink">
                                                                <c:param name="action" value="Delete"/>
                                                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                                <c:param name="id" value="${account.username}"/>
                                                            </c:url>
                                                            <a href="${DeleteLink}">Delete</a>
                                                        </td>
                                                        <td>
                                                            <form action="/HomeStayProject/MainController" method="POST">
                                                                <input type="hidden" name="id" value="${account.username}"/>
                                                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                                                <input type="submit" name="action" value="Edit"/>

                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </c:if>
                                    <c:if test="${!listAccount}">
                                        <font color="red">
                                        NO Record Found
                                        <font/>
                                    </c:if>
                                </c:if>

                            </div>
                        </div>

                    </div>



                </div>
                <!-- /.container-fluid -->

                <!-- Sticky Footer -->
                <footer class="sticky-footer">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright © Your Website 2019</span>
                        </div>
                    </div>
                </footer>

            </div>
            <!-- /.content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <form action="/HomeStayProject/MainController" method="POST">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                            <input class="btn btn-primary" type="submit" value="Logout" name="action">
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="/HomeStayProject/vendor/jquery/jquery.min.js"></script>
        <script src="/HomeStayProject/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Page level plugin JavaScript-->
        <script src="/HomeStayProject/vendor/chart.js/Chart.min.js"></script>
        <script src="/HomeStayProject/vendor/datatables/jquery.dataTables.js"></script>
        <script src="/HomeStayProject/vendor/datatables/dataTables.bootstrap4.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="/HomeStayProject/js/sb-admin.min.js"></script>

        <!-- Demo scripts for this page-->
        <script src="/HomeStayProject/js/demo/datatables-demo.js"></script>
        <script src="/HomeStayProject/js/demo/chart-area-demo.js"></script>

    </body>

</html>
