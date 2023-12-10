<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
-->
<!-- beautify ignore:start -->
<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-assets-path="../assets/"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>DMS : 표준 용어 관리 시스템</title>

    <meta name="description" content=""/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="/img/favicon/favicon.ico"/>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="/css/boxicons.css"/>

    <!-- Core CSS -->
    <link rel="stylesheet" href="/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="/css/demo.css"/>

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="/css/perfect-scrollbar.css"/>

    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="/js/config.js"></script>
</head>

<body>

<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">

        <!-- Menu -->
        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
            <div class="app-brand demo">
                <a href="/dms/standardData/page" class="app-brand-link">
              <span class="app-brand-logo demo">
                <svg
                        width="25"
                        viewBox="0 0 25 42"
                        version="1.1"
                        xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink"
                >
                  <defs>
                    <path
                            d="M13.7918663,0.358365126 L3.39788168,7.44174259 C0.566865006,9.69408886 -0.379795268,12.4788597 0.557900856,15.7960551 C0.68998853,16.2305145 1.09562888,17.7872135 3.12357076,19.2293357 C3.8146334,19.7207684 5.32369333,20.3834223 7.65075054,21.2172976 L7.59773219,21.2525164 L2.63468769,24.5493413 C0.445452254,26.3002124 0.0884951797,28.5083815 1.56381646,31.1738486 C2.83770406,32.8170431 5.20850219,33.2640127 7.09180128,32.5391577 C8.347334,32.0559211 11.4559176,30.0011079 16.4175519,26.3747182 C18.0338572,24.4997857 18.6973423,22.4544883 18.4080071,20.2388261 C17.963753,17.5346866 16.1776345,15.5799961 13.0496516,14.3747546 L10.9194936,13.4715819 L18.6192054,7.984237 L13.7918663,0.358365126 Z"
                            id="path-1"
                    ></path>
                    <path
                            d="M5.47320593,6.00457225 C4.05321814,8.216144 4.36334763,10.0722806 6.40359441,11.5729822 C8.61520715,12.571656 10.0999176,13.2171421 10.8577257,13.5094407 L15.5088241,14.433041 L18.6192054,7.984237 C15.5364148,3.11535317 13.9273018,0.573395879 13.7918663,0.358365126 C13.5790555,0.511491653 10.8061687,2.3935607 5.47320593,6.00457225 Z"
                            id="path-3"
                    ></path>
                    <path
                            d="M7.50063644,21.2294429 L12.3234468,23.3159332 C14.1688022,24.7579751 14.397098,26.4880487 13.008334,28.506154 C11.6195701,30.5242593 10.3099883,31.790241 9.07958868,32.3040991 C5.78142938,33.4346997 4.13234973,34 4.13234973,34 C4.13234973,34 2.75489982,33.0538207 2.37032616e-14,31.1614621 C-0.55822714,27.8186216 -0.55822714,26.0572515 -4.05231404e-15,25.8773518 C0.83734071,25.6075023 2.77988457,22.8248993 3.3049379,22.52991 C3.65497346,22.3332504 5.05353963,21.8997614 7.50063644,21.2294429 Z"
                            id="path-4"
                    ></path>
                    <path
                            d="M20.6,7.13333333 L25.6,13.8 C26.2627417,14.6836556 26.0836556,15.9372583 25.2,16.6 C24.8538077,16.8596443 24.4327404,17 24,17 L14,17 C12.8954305,17 12,16.1045695 12,15 C12,14.5672596 12.1403557,14.1461923 12.4,13.8 L17.4,7.13333333 C18.0627417,6.24967773 19.3163444,6.07059163 20.2,6.73333333 C20.3516113,6.84704183 20.4862915,6.981722 20.6,7.13333333 Z"
                            id="path-5"
                    ></path>
                  </defs>
                  <g id="g-app-brand" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                    <g id="Brand-Logo" transform="translate(-27.000000, -15.000000)">
                      <g id="Icon" transform="translate(27.000000, 15.000000)">
                        <g id="Mask" transform="translate(0.000000, 8.000000)">
                          <mask id="mask-2" fill="white">
                            <use xlink:href="#path-1"></use>
                          </mask>
                          <use fill="#696cff" xlink:href="#path-1"></use>
                          <g id="Path-3" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-3"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-3"></use>
                          </g>
                          <g id="Path-4" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-4"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-4"></use>
                          </g>
                        </g>
                        <g
                                id="Triangle"
                                transform="translate(19.000000, 11.000000) rotate(-300.000000) translate(-19.000000, -11.000000) "
                        >
                          <use fill="#696cff" xlink:href="#path-5"></use>
                          <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-5"></use>
                        </g>
                      </g>
                    </g>
                  </g>
                </svg>
              </span>
                    <span class="app-brand-text demo menu-text fw-bolder ms-2 text-uppercase">DMS</span>
                </a>

                <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                    <i class="bx bx-chevron-left bx-sm align-middle"></i>
                </a>
            </div>

            <div class="menu-inner-shadow"></div>
            <ul class="menu-inner py-1">
                <!-- Layouts -->
                <li class="menu-header small text-uppercase">
                    <span class="menu-header-text">데이터 표준 관리</span>
                </li>
                <li class="menu-item active">
                    <a href="/dms/standardData/page" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-home-circle"></i>
                        <div data-i18n="Analytics">표준 데이터 관리</div>
                    </a>
                </li>

                <li class="menu-item">
                    <a href="/dms/synonym/page" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-search"></i>
                        <div data-i18n="Search">동의어 조회</div>
                    </a>
                </li>


                <!-- Components -->
                <li class="menu-header small text-uppercase"><span class="menu-header-text">데이터 표준 등록</span></li>

                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings">표준 단어</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/single-word/form" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/bulk-word" class="menu-link">
                                <div data-i18n="Notifications">일괄등록</div>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings">도메인</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/single-domain/form" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/bulk-domain" class="menu-link">
                                <div data-i18n="Notifications">일괄등록</div>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings">용어</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/term" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/bulk-term" class="menu-link">
                                <div data-i18n="Notifications">일괄등록</div>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings">표준 분류 체계</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/standardArea/page" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/standardArea/managementPage" class="menu-link">
                                <div data-i18n="Notifications">관리</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/collectionBook/page" class="menu-link">
                                <div data-i18n="Notifications">출력</div>
                            </a>
                        </li>
                    </ul>
                </li>
                <!-- Dashboard -->
                <li class="menu-header small text-uppercase">
                    <span class="menu-header-text">대시보드</span>
                </li>
                <li class="menu-item ">
                    <a href="/dms/dashboard" class="menu-link">
                        <i class="menu-icon tf-icons bx bxs-pie-chart-alt"></i>
                        <div data-i18n="Chart">통계</div>
                    </a>
                </li>
            </ul>
        </aside>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">
            <!-- Navbar -->
            <nav
                    class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                    id="layout-navbar"
            >
                <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                    <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                        <i class="bx bx-menu bx-sm"></i>
                    </a>
                </div>

                <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                    <!-- Dropdown -->
                    <div class="input-group">
                        <label class="input-group-text" for="selectStandardArea">표준 분류 체계</label>
                        <select id="selectStandardArea" class="form-select color-dropdown"
                                onchange="handleSelectChange(this)">
                            <option value="" selected disabled hidden></option>
                            <c:forEach var="std" items="${sessionScope.stdList}">
                                <option value="${std.stdAreaNm}">${std.stdAreaNm}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- Dropdown -->

                </div>
            </nav>
            <!-- / Navbar -->

            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->
                <div class="container-xxl flex-grow-1 container-p-y">
                    <!-- Title -->
                    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-bold"> 데이터 표준 관리 / </span> 표준 데이터 관리 </h4>
                    <!-- Title -->

                    <!-- 여기에 내용 넣기 -->
                    <div class="card ">
                        <div class="row">
                            <div class="col-md">
                                <h5 class="card-header">표준 데이터 조건 검색</h5>
                            </div>
                            <div class="col-md mt-3 me-3">
                                <div class="input-group">
                                    <input class="form-control" type="search" placeholder="검색하기.."
                                           aria-label="Search" id="conditionSearch">
                                    <label style="cursor: pointer;" class="input-group-text" id="searchLabel"
                                           for="conditionSearch">검색</label>
                                </div>
                            </div>
                        </div>
                        <hr class="m-0">
                        <!-- Inline Checkboxes -->
                        <div class="card-body  both-scrollbars-example">
                            <div class="row gy-3">
                                <div class="col-md">
                                    <div class="form-check form-check-inline mt-3">
                                        <input class="form-check-input" type="checkbox" id="word" checked>
                                        <label class="form-check-label" for="word">단어</label>
                                    </div>
                                    <!-- "단어" 체크박스 아래에 추가 -->
                                    <div class="form-check mt-3">
                                        <input class="form-check-input" type="checkbox" id="attribute">
                                        <label class="form-check-label" for="attribute">속성 분류어</label>
                                        <!-- "속성 분류어"의 오른쪽에 추가 -->
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" id="entity">
                                            <label class="form-check-label" for="entity">엔터티 분류어</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md">
                                    <div class="form-check form-check-inline mt-3">
                                        <input class="form-check-input" type="checkbox" id="domain" checked>
                                        <label class="form-check-label" for="domain">도메인</label>
                                    </div>
                                    <div class="input-group mt-1">
                                        <label class="input-group-text" for="domainTypeList">도메인 유형</label>
                                        <select class="form-select" id="domainTypeList">
                                            <option selected="">None</option>
                                            <c:forEach var="domainType" items="${domainTypes}">
                                                <option value="${domainType}">${domainType}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md">
                                    <div class="form-check form-check-inline mt-3">
                                        <input class="form-check-input" type="checkbox" id="term" checked>
                                        <label class="form-check-label" for="term">용어</label>
                                    </div>
                                    <div class="input-group mt-1">
                                        <label class="input-group-text" for="dataTypeList">데이터 타입</label>
                                        <select class="form-select" id="dataTypeList">
                                            <option selected="">None</option>
                                            <c:forEach var="dataType" items="${dataTypes}">
                                                <option value="${dataType}">${dataType}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="table-responsive overflow-auto" style="height: 480px">
                                    <table class="table accordion text-nowrap hoverable">
                                        <thead style="position: sticky; top: 0; background-color: #ffffff; z-index: 1;">
                                        <tr>
                                            <th scope="col">번호</th>
                                            <th scope="col">표준 분류</th>
                                            <th scope="col">구분</th>
                                            <th scope="col">논리명</th>
                                            <th scope="col">물리명</th>
                                            <th scope="col">도메인 그룹</th>
                                            <th scope="col">도메인 유형</th>
                                            <th scope="col">도메인명</th>
                                            <th scope="col">논리데이터 타입</th>
                                            <th scope="col">길이</th>
                                            <th scope="col">표준 여부</th>
                                            <th scope="col">속성 분류어</th>
                                            <th scope="col">엔터티 분류어</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <!-- 버튼 -->
                                    <div class="card-body">
                                        <div class="d-grid gap-2 col-lg-6 mx-auto text-center">
                                            <div class="demo-inline-spacing">
                                                <button class="btn btn-secondary btn-lg" type="button"
                                                        id="saveCollectionToExcel">출력
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 버튼 -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Content -->
                    <!-- Modals -->
                    <div class="modal fade" id="editWord" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editWordTitle">단어 수정</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <input type="hidden" id="stdWordModalDicId" class="form-control">
                                                <input type="hidden" id="stdWordModalSTDYN" class="form-control">


                                                <label for="stdModalWordLogNm" class="input-group-text">논리명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="stdModalWordLogNm" class="form-control"
                                                       placeholder="입력..." onkeyup="characterCheck(this)"
                                                       onkeydown="characterCheck(this)">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdModalWordPhyNm" class="input-group-text">물리명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="stdModalWordPhyNm" class="form-control"
                                                       placeholder="입력..." onkeyup="characterCheck(this)"
                                                       onkeydown="characterCheck(this)">
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="stdModalWordPhyNm" id="modalTermWordValidation">중복확인</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdModalWordFllNm" class="input-group-text">영문 풀네임</label>
                                                <input type="text" id="stdModalWordFllNm" class="form-control"
                                                       placeholder="입력...">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdModalWordDescription" class="input-group-text">설
                                                    명</label>
                                                <input type="text" id="stdModalWordDescription" class="form-control"
                                                       placeholder="입력...">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="form-check" style="display: flex;">
                                                <input class="form-check-input" type="checkbox" value=""
                                                       id="stdModalWordEntClssYn"
                                                       unChecked/>
                                                <label class="form-check-label" for="stdModalWordEntClssYn">
                                                    엔티티 </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row g-2">
                                        <div class="col mb-0">
                                            <div class="form-check" style="display: flex;">
                                                <input class="form-check-input" type="checkbox" value=""
                                                       id="stdModalWordAttrClssYn"
                                                       unChecked/>
                                                <label class="form-check-label" for="stdModalWordAttrClssYn">
                                                    속성 </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="updateWordForStdModal">수정</button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        취소
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="editTerm" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editTermTitle">용어 수정</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <input type="hidden" id="stdTermModalDomId" class="form-control">
                                    <input type="hidden" id="stdTermModalDicId" class="form-control">
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalLogNm" class="input-group-text">논리명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="stdTermModalLogNm" class="form-control"
                                                       placeholder="입력..." readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalPhyNm" class="input-group-text">물리명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="stdTermModalPhyNm" class="form-control"
                                                       placeholder="입력..." readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalDomType" class="input-group-text">도메인 유형</label>
                                                <input type="text" id="stdTermModalDomType" class="form-control"
                                                       placeholder="입력..." readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalDomName" class="input-group-text">도메인 명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="stdTermModalDomName" class="form-control"
                                                       placeholder="입력..." readonly>
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="stdTermModalDomName" id="modalTermDomSearch">검 색</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalDomDataType"
                                                       class="input-group-text">논리데이터타입</label>
                                                <input type="text" id="stdTermModalDomDataType" class="form-control"
                                                       placeholder="입력..." readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalLogNmInfo"
                                                       class="input-group-text">논리구성정보</label>
                                                <input type="text" id="stdTermModalLogNmInfo" class="form-control"
                                                       placeholder="입력..." readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalPhyNmInfo"
                                                       class="input-group-text">물리구성정보</label>
                                                <input type="text" id="stdTermModalPhyNmInfo" class="form-control"
                                                       placeholder="입력..." readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdTermModalDesc"
                                                       class="input-group-text">설 명</label>
                                                <input type="text" id="stdTermModalDesc" class="form-control"
                                                       placeholder="입력...">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="updateTermForStdModal">수정
                                        </button>
                                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                            취소
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="stdSelectDomainListModal" tabindex="-1" style="display: none;"
                         aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="stdSelectDomainList">도메인 수정</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="stdModalDomainName" class="input-group-text">도메인 이름
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="stdModalDomainName" class="form-control"
                                                       placeholder="입력...">
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="stdModalDomainName"
                                                       id="modalTermDomainSearchLabel">조회</label>
                                            </div>
                                            <div class="table-responsive text-nowrap" id="termDomainTable">
                                                <table class="table">
                                                    <thead>
                                                    <tr class="text-nowrap">
                                                        <th>번호</th>
                                                        <th>대표도메인명</th>
                                                        <th>도메인명</th>
                                                        <th>도메인유형</th>
                                                        <th>논리데이터타입</th>
                                                        <th>데이터길이</th>
                                                        <th>소수점</th>
                                                        <th>ID</th>

                                                    </tr>
                                                    </thead>
                                                    <tbody id="stdModalTermDomainTableBody">
                                                    <!-- 여기에 스크립트 파일에서 생성된 tbody가 들어가야 해요 -->
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="confirmDomainNameButton">
                                        </button>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary">수정</button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        취소
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="editDomain" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editDomaain">도메인 수정</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div id="modal-alert-div" style="position: fixed; bottom: 10px; left: 50%; transform: translateX(-50%); z-index: 9999;"></div>
                                    <div class="content-backdrop fade"></div>
                                    <form id="editDomainForm">
                                        <input type="hidden" id="stdModalDomId"/>
                                        <div class="col-sm-16 mb-3 form-floating">
                                            <input type="text" class="form-control" id="stdModalkeyDomName"
                                                   name="keyDomName"
                                                   placeholder="입력..." onkeyup="characterCheck(this)" onkeydown="characterCheck(this)" aria-describedby="keyDomNameHelp">
                                            <label for="stdModalkeyDomName">대표 도메인</label>
                                        </div>
                                        <div class="col-sm-16 mb-3 form-floating">


                                            <input type="text" class="form-control" id="stdModalDomName" name="domName" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)"
                                                   placeholder="입력..." aria-describedby="domNameHelp" readonly>
                                            <label for="stdModalDomName">도메인명</label>
                                        </div>
                                        <div class="col-sm-5 mb-3">
                                            <button type="button" class="btn btn-primary" id="modalDomainValidation">중복확인
                                            </button>
                                        </div>
                                        <div class="col-sm-16 mb-3">
                                            <label class="form-label"></label>
                                            <select id="stdModaldomTypeCode" class="form-select color-dropdown">
                                                <option value="" selected disabled>도메인 유형</option>
                                                <option value="번호">번호</option>
                                                <option value="일반">일반</option>
                                                <option value="코드">코드</option>
                                            </select><label class="form-label"></label>
                                            <select class="form-select color-dropdown" id="stdModalDomGroupId" name="domGroupId">
                                            </select>

                                            <label class="form-label"></label>
                                            <select class="form-select color-dropdown" id="stdModalDomdataTypeCode" name="dataTypeCode">
                                                <option value="" selected disabled>논리 데이터 타입</option>
                                                <option value="CH">CHAR</option>
                                                <option value="NCH">NCHAR</option>
                                                <option value="VC">VARCHAR2</option>
                                                <option value="NVC">NVARCHAR2</option>
                                                <option value="INT">INT</option>
                                                <option value="TINT">TINYINT</option>
                                                <option value="SINT">SMALLINT</option>
                                                <option value="MINT">MEDIUMINT</option>
                                                <option value="BINT">BIGINT</option>
                                                <option value="INTG">INTEGER</option>
                                                <option value="DEC">DECIMAL</option>
                                                <option value="DB">DOUBLE</option>
                                                <option value="FL">FLOAT</option>
                                                <option value="RL">REAL</option>
                                                <option value="BIT">BIT</option>
                                                <option value="BN">BINARY</option>
                                                <option value="VBN">VARBINARY</option>
                                                <option value="RAW">RAW</option>
                                                <option value="NUMR">NUMERIC</option>
                                                <option value="BOOL">BOOLEAN</option>
                                                <option value="NUM">NUMBER</option>
                                                <option value="TM">TIME</option>
                                                <option value="TMS">TIMESTAMP</option>
                                                <option value="DT">DATE</option>
                                                <option value="DTM">DATETIME</option>
                                                <option value="YR">YEAR</option>
                                                <option value="TX">TEXT</option>
                                                <option value="TTX">TINYTEXT</option>
                                                <option value="MTX">MEDIUMTEXT</option>
                                                <option value="LTX">LONGTEXT</option>
                                                <option value="CL">CLOB</option>
                                                <option value="NCL">NCLOB</option>
                                                <option value="BL">BLOB</option>
                                            </select>
                                        </div>
                                        <div class="content-wrapper"></div>
                                        <div class="row">
                                            <div class="col-sm-5 mb-4 form-floating">
                                                <input type="number" class="form-control" id="stdModaldataLen" name="dataLen"
                                                       placeholder="입력..." aria-describedby="dataLenhelp">
                                                <label for="stdModaldataLen">&nbsp;&nbsp;&nbsp;&nbsp;데이터 길이</label>
                                            </div>
                                            <div class="col-sm-1"
                                                 style="line-height: 6; text-align: center; font-weight: bold;">.
                                            </div>
                                            <div class="col-sm-6 mb-3 form-floating">
                                                <input type="number" class="form-control" id="stdModalDataScale" name="dataScale"
                                                       placeholder="입력..." aria-describedby="dataScalehelp">
                                                <label for="stdModalDataScale">&nbsp;&nbsp;&nbsp;&nbsp;데이터 소수점</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-5 mb-4 form-floating">
                                                <input type="number" class="form-control" id="stdModalDataMin" name="dataMin"
                                                       placeholder="입력..." aria-describedby="dataMinhelp">
                                                <label for="stdModalDataMin">&nbsp;&nbsp;&nbsp;&nbsp;데이터 범위(최소)</label>
                                            </div>
                                            <div class="col-sm-1"
                                                 style="line-height: 6; text-align: center; font-weight: bold;">~
                                            </div>
                                            <div class="col-sm-6 mb-3 form-floating">
                                                <input type="number" class="form-control" id="stdModalDataMax" name="dataMax"
                                                       placeholder="입력..." aria-describedby="dataMaxhelp">
                                                <label for="stdModalDataMax">&nbsp;&nbsp;&nbsp;&nbsp;데이터 범위(최대)</label>
                                            </div>
                                        </div>
                                        <div class="mb-3 form-floating" style=" margin-top: 20px;">
                                            <input type="text" class="form-control" id="stdModaldomDescription"
                                                   placeholder="입력...">
                                            <label for="stdModaldomDescription">설명</label>
                                        </div>

                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="updateDomainForStdModal" disabled>수정</button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        취소
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- deleteModals -->
                    <div class="modal fade" id="deleteWord" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteWordTitle">단어 삭제</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="deleteStdWordModalButton">확인
                                    </button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        취소
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="deleteTerm" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteTermTitle">용어 삭제</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="deleteStdTermModalButton">확인
                                    </button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        취소
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal fade" id="deleteDomain" tabindex="-1" style="display: none;"
                         aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteDomainTitle" >도메인 삭제</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>

                                <div class="modal-body">
                                    <input type="hidden" id="stdModalDeleteDomId">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="deleteDomainForStdModal" >확인</button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                        취소
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Footer -->
                    <footer class="content-footer footer bg-footer-theme">
                        <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                        </div>
                    </footer>
                    <!-- / Footer -->
                    <div id="alertContainer"
                         style="position: fixed; bottom: 10px; left: 50%; transform: translateX(-50%); z-index: 9999;"></div>
                    <div class="spinner-border spinner-border-lg text-primary" role="status" id="loading-spinner"
                         style="position: fixed; top: 10px; left: 50%; z-index: 9999; display: none">
                        <span class="visually-hidden">Loading...</span>
                    </div>

                    <div class="content-backdrop fade"></div>
                </div>
            </div>
            <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
    </div>

    <!-- Overlay -->
    <div class="layout-overlay layout-menu-toggle"></div>
</div>
<!-- / Layout wrapper -->

<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->
<script src="/js/jquery.js"></script>
<script src="/js/popper.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/perfect-scrollbar.js"></script>

<script src="/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->

<!-- Main JS -->
<script src="/js/main.js"></script>

<!-- Page JS -->
<script src="/js/spinner.js"></script>
<script src="/js/showAlert.js"></script>
<script src="/js/sendAjaxRequest.js"></script>
<script src="/js/session/session.js"></script>
<script src="/js/standardData/conditionSearch.js"></script>
<script src="/js/standardData/getWordAndTermInfo.js"></script>
<script src="/js/standardData/domainSelectForTerm.js"></script>
<script src="/js/standardData/updateTermAndWord.js"></script>
<script src="/js/standardData/getWordAndTermInfo.js"></script>
<script src="/js/standardData/getDomainInfo.js"></script>
<script src="/js/standardData/updateDomain.js"></script>
<script src="/js/standardData/deleteDomain.js"></script>
<script src="/js/inputTagValidation.js"></script>


<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>