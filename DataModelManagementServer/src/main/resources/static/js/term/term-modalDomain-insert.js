function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}

function getModalDomainInsert() {
    return document.getElementById("modalDomainName").value.trim();
}

let termDomainInsertModal

const domainInsertButton = document.getElementById('domainInsertButton');
domainInsertButton.addEventListener('click', function () {
    termDomainInsertModal = new bootstrap.Modal(document.getElementById('termDomainInsertModal'));
    termDomainInsertModal.show();
});

// 중복 확인 버튼 클릭 이벤트 핸들러
document.getElementById("checkDuplicateButton").addEventListener("click", function () {
    checkDuplicate();
});

// Confirm 버튼 클릭 이벤트 핸들러
document.getElementById("confirmButton").addEventListener("click", function () {
    confirmButtonClick();
});

getDomainGroup();
//도메인 그룹
function getDomainGroup() {
    $.ajax({
        url: "/dms/single-domain/group",
        type: "POST",
        contentType: "application/json",
        data: {





        },
        success: function (data, status, xhr) {
            // data 배열을 순회하면서 각 요소의 domGrpId가 비어있지 않은 경우, select 요소에 새로운 option 추가
            for (let i = 0; i < data.length; i++) {
                // data[i]의 domGrpId가 비어있지 않은지 확인, // 조건을 만족하는 경우에만 아래의 작업을 수행
                if (data[i].domGrpId !== '') {
                    // 현재 data 요소의 domGrpId와 domGrpNm 값을 변수에 할당
                    let grpId = data[i].domGrpId;
                    let grpNm = data[i].domGrpNm;

                    $("select[name='domGroupId']").append("<option value='" + grpId + "'>" + grpNm + "</option>");
                }
            }
        }
    });
}
//도메인명 자동생성
function updateDomName() {
    $("#confirmButton").attr('disabled', true); // 입력하거나 셀렉트박스 바꿀때마다 비활성화
    let keyDomName = $("#keyDomName").val() != null ? $("#keyDomName").val() : '';
    let dataTypeCode = $("#dataTypeCode").val() != null ? $("#dataTypeCode").val() : '';
    let dataLen = $("#dataLen").val() != null ? $("#dataLen").val() : '';
    let dataScale = $("#dataScale").val() != null ? $("#dataScale").val() : '';

    // 콘솔 로그
    console.log("keyDomName: ", keyDomName);
    console.log("dataTypeCode: ", dataTypeCode);
    console.log("dataLen: ", dataLen);
    console.log("dataScale: ", dataScale);

    let domNameValue = keyDomName + dataTypeCode + dataLen;
    if (dataScale !== '') {
        domNameValue += "," + dataScale;
    }
    $("#domName").val(domNameValue);



}


$("#dataScale").off().on('input', function (e) { // 키보드의 i/o 입출력
    // keyup = 키보드의 키가 눌렷다 올라올때마다 // keydown은 키보드가 눌릴때마다
    let value = e.target.value;
    if (value < 1) $(this).val('');
    else if (value > 8) $(this).val(8);
    updateDomName();
});


$("#dataLen").off().on('input', function (e) {
    if($("#dataTypeCode").val() === 'NUM') {
        let v = e.target.value;
        if (v <= $("#dataScale").val()) {
            showAlert('데이터 길이는 데이터 소수점보다 커야합니다.');
            $(this).val('');
        }
    }
    updateDomName();
});

$("#keyDomName").on('input', updateDomName); //셋keyDomName, #dataLen, #dataScale중 하나라도 입력을 받으면 updateDomName함수가 계속 실행(inputbox에 입력할 떄)
$("#dataTypeCode").change(updateDomName);//dataTypeCode를 입력할 때마다  updateDomName 함수가 실행됨(option selectbox 선택시)

// 도메인명 가져오기
function getDomName() {
    return document.getElementById("domName").value.trim();
}
function getKeyDomName() {
    return document.getElementById("keyDomName").value.trim();
}
function getDataType() {
    return document.getElementById("dataTypeCode").value.trim();
}
function getDomType() {
    return document.getElementById("domTypeCode").value.trim();
}

// 도메인명이 비어 있는지 확인
function isKeyDomNameEmpty() {
    return getKeyDomName() === "";
}
function isDataTypeEmpty() {
    return getDataType() === "";
}
function isDomTypeEmpty() {
    return getDomType() === "";
}

// AJAX 요청을 보내는 공통 함수
function sendAjaxRequest(url, data, successCallback, errorCallback) {
    $.ajax({
        type: "POST",//post면 data이고, 컨트롤러에서 @RequestBody로 받는다, 안되면 contentType: "application/json" 추가한다 // get이면 data대신에 params이고, @RequestParam("keyName")으로 받는다.
        url: url,
        data: JSON.stringify(data),
        //params: {'domName' : domName} ==> @RequestParam("domName")



        contentType: "application/json",  // Content-Type을 application/json으로 설정
        success: successCallback,
        error: errorCallback,
    });
}

// 중복 확인 수행 함수
function checkDuplicate() {
    const domName = getDomName();
    if (isDataTypeEmpty()) {
        showAlert("데이터 타입을 입력하세요.");
        disableConfirmButton();
        return false;
    }
    if (isKeyDomNameEmpty()) {
        showAlert("대표 도메인명을 입력하세요.");
        disableConfirmButton();
        return false;
    }
    if (isDomTypeEmpty()) {
        showAlert("도메인 유형을 입력하세요.");
        disableConfirmButton();
        return false;
    }
    sendAjaxRequest(
        "/dms/single-domain/duplicateDomainRest",
        {
            domName: domName

        },
        function (response) {
            handleDuplicateCheckResponse(response);
        },
        function () {
            handleAjaxError();
        }
    );
}

// 중복 확인 응답 처리 함수
function handleDuplicateCheckResponse(response) {
    if (response) {
        showAlert("중복된 도메인명입니다. 다른 이름을 입력하세요.");
        disableConfirmButton();
    } else {
        showAlert("도메인명 사용이 가능합니다.", "success");
        enableConfirmButton();
    }
}

// 도메인 등록 버튼
function confirmButtonClick() {

    // const stdAreaId = $('#stdAreaId').val();
    const selectStandardArea = document.getElementById('selectStandardArea').value;
    const keyDomName = document.getElementById('keyDomName').value;
    // const stdAreaId = document.getElementById('stdAreaId').value;
    const domName = document.getElementById('domName').value;
    const domDescription = document.getElementById('domDescription').value;
    // const domDescription = $('#domDescription').val();
    // const domTypeCode = $('#domTypeCode').val();
    const domTypeCode = document.getElementById('domTypeCode').value;
    const domGroupId = document.getElementById('domGroupId').value;
    // const domGroupId = $('#domGroupId').val();
    const dataTypeCode = document.getElementById('dataTypeCode').value;

    // const dataTypeCode = $('#dataTypeCode').val();
    const dataLen = document.getElementById('dataLen').value;
    // const dataLen = $('#dataLen').val();
    const dataScale = document.getElementById('dataScale').value;
    // const dataScale = $('#dataScale').val();
    const dataMin = document.getElementById('dataMin').value;
    // const dataMin = $('#dataMin').val();
    const dataMax = document.getElementById('dataMax').value;
    // const dataMax = $('#dataMax').val();
    // const avalStartDate = $('#avalStartDate').val();
    // const avalEndDate = $('#avalEndDate').val();

    console.log('domName:', domName);
    console.log('keyDomName:', keyDomName);
    console.log('selectStandardArea:', selectStandardArea);
    console.log('domDescription:', domDescription);
    console.log('domTypeCode:', domTypeCode);
    console.log('domGroupId:', domGroupId);
    console.log('dataLen:', dataLen);
    console.log('dataScale:', dataScale);
    console.log('dataMin:', dataMin);
    console.log('dataMax:', dataMax);


    sendAjaxRequest(
        "/dms/single-domain/insertRest",
        {
            selectStandardArea: selectStandardArea,
            keyDomName: keyDomName,
            domName: domName,
            domDescription: domDescription,
            domTypeCode: domTypeCode,
            domGroupId: domGroupId,
            dataTypeCode: dataTypeCode,
            dataLen: dataLen,
            dataScale: dataScale,
            dataMin: dataMin,
            dataMax: dataMax,
        },
        function () {
            const domainInsertModal = document.getElementById('termDomainInsertModal'); // 여기에 모달의 ID를 지정해야 합니다.
            const domInsertModal = bootstrap.Modal.getInstance(domainInsertModal);
            domInsertModal.hide();
            handleConfirmSuccess();
        },
        function () {
            handleAjaxError();
        }
    );
}

// Confirm 성공 처리 함수
function handleConfirmSuccess() {
    showAlert("도메인 등록이 완료되었습니다.", "success");
    clearInputFields();
    disableConfirmButton();
}

// AJAX 오류 처리 함수
function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
    disableConfirmButton();
}

// Confirm 버튼 비활성화 함수
function disableConfirmButton() {
    document.getElementById("confirmButton").setAttribute("disabled", "true");
}

// Confirm 버튼 활성화 함수
function enableConfirmButton() {
    document.getElementById("confirmButton").removeAttribute("disabled");
}

// 입력 필드 초기화 함수
function clearInputFields() {
    document.getElementById("domName").value = "";
    disableConfirmButton();
}

// 도메인명 입력 이벤트 핸들러
document.getElementById("keyDomName").addEventListener("input", function () {
    disableConfirmButton();
});