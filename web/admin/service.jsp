<%-- 
    Document   : service
    Created on : Aug 20, 2019, 12:41:32 AM
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
            <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="button">
                            <i class="fas fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>

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
                <li class="nav-item">
                    <a class="nav-link" href="/HomeStayProject/admin/admin.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Account</span>
                    </a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" >
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Service</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/HomeStayProject/admin/room.jsp">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Room</span></a>
                </li>
            </ul>

            <div id="content-wrapper">

                <div class="container-fluid">

                    <h2>Search Service</h2>
                    <form action="/HomeStayProject/MainServiceController" method="POST">
                        Service Name: <input type="text" name="txtSearch"/>
                        <input type="submit" name="action" value="Search"/>
                        <br/>
                        <a color="red" href="/HomeStayProject/admin/addService.jsp">Insert New Service</a>
                    </form>
                    <c:if test="${requestScope.SER != null}">
                        <c:if test="${not empty requestScope.SER}" var="listService">
                            <div class="card mb-3">
                                <div class="card-header">
                                    <i class="fas fa-table"></i>
                                    Service Table</div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>No</th>
                                                    <th>Service Name</th>
                                                    <th>Service Image</th>
                                                    <th>Service Description</th>
                                                    <th>Service Quantity</th>
                                                    <th>Service Price</th>
                                                    <th>Service Type</th>
                                                    <th>Delete</th>
                                                    <th>Edit</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.SER}" var="account" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>${account.serName}</td>
                                                        <td><img src="${account.serImage}"/></td>
                                                        <td>${account.servDescription}</td>
                                                        <td>${account.serQuantity}</td>
                                                        <td>${account.serPrice} VND</td>
                                                        <td>
                                                            <c:if test="${account.typeRomeService == 1}">Food</c:if>
                                                            <c:if test="${account.typeRomeService == 2}">Drink</c:if>
                                                            <c:if test="${account.typeRomeService == 3}">Service</c:if>

                                                            </td>

                                                            <td>
                                                            <c:url value="MainServiceController" var="DeleteLink">
                                                                <c:param name="action" value="Delete"/>
                                                                <c:param name="txtSearch" value="${param.txtSearch}"/>
                                                                <c:param name="id" value="${account.serName}"/>
                                                            </c:url>
                                                            <a href="${DeleteLink}">Delete</a>
                                                        </td>

                                                        <td>
                                                            <form action="/HomeStayProject/MainServiceController" method="POST">
                                                                <input type="hidden" name="id" value="${account.serName}"/>
                                                                <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                                                                <input type="submit" name="action" value="Edit"/>

                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </c:if>
                                    <c:if test="${!listService}">
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
                        <form action="/HomeStayProject/MainServiceController" method="POST">
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
        <script src="/HomeStayProject/vendor/jquery-easing/jquery.easing.min.js"></script>

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
