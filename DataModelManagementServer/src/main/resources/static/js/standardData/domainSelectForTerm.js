function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}

function getStdTermModalDomainName() {
    return document.getElementById("stdModalDomainName").value.trim();
}



document.getElementById('modalTermDomainSearchLabel').addEventListener('click', function() {
    console.log("도메인리스트조회 실행= " + getStdTermDomainSearchModal);

    getStdTermDomainSearchModal();
});




// 기존의 getDomainList() 함수 정의
function getStdTermDomainSearchModal() {
    const stdAreaId = getStandardAreaName();
    const domainName = getStdTermModalDomainName();

    sendAjaxRequest(
        "/dms/single-domain/domainSearchRest",
        {
            stdAreaId: stdAreaId,
            domainName: domainName,
        },
        function (response) {
            generateStdTermDomainTable(response.domainList);
            handleStdTermDomainGetListSuccess();

        },
        function () {
            handleAjaxError();
        }
    );
}

function handleStdTermDomainGetListSuccess() {
    showAlert("도메인 검색이 완료 되었습니다.", "success");
}

function handleStdTermDomainSelectSuceess() {
    showAlert("도메인 선택이 완료 되었습니다.", "success");
}
function generateStdTermDomainTable(domainList) {
    const domainTableBody = document.getElementById('stdModalTermDomainTableBody');
    console.log(domainList); // 여기에 추가

    // 기존 테이블의 내용을 지우기
    domainTableBody.innerHTML = '';

    if (Array.isArray(domainList)) {
        domainList.forEach(function (domain, index) {
            const domainRow = document.createElement('tr');
            domainRow.innerHTML = `
                <td>${index + 1}</td>
                <td>${domain.keyDomName}</td>
                <td>${domain.domName}</td>
                <td>${domain.domTypeCode}</td>
                <td>${domain.dataTypeCode}</td>
                <td>${domain.dataLen}</td>
                <td>${domain.dataScale}</td>
                <td>${domain.domId}</td>
                <!-- Add any additional data items here -->
            `;
            domainRow.addEventListener('click', function() {
                // 클릭한 행의 domain.keyDomName 값을 가져와 termDomName input에 설정
                const stdTermDomIdInput = document.getElementById('stdTermModalDomId');
                stdTermDomIdInput.value = domain.domId;
                const stdTermDomNameInput = document.getElementById('stdTermModalDomName');
                stdTermDomNameInput.value = domain.domName;

                const stdTermDomTypeInput = document.getElementById('stdTermModalDomType');
                stdTermDomTypeInput.value = domain.domTypeCode;

                const stdTermDomDataTypeInput = document.getElementById('stdTermModalDomDataType');
                stdTermDomDataTypeInput.value = domain.dataTypeCode;





                // 버튼 클릭 이벤트 호출
                confirmDomainNameButton.click();
                // 모달 숨기기
                const domainSearchModal = document.getElementById('stdSelectDomainListModal'); // 여기에 모달의 ID를 지정해야 합니다.
                const stdSelectDomainListModal = bootstrap.Modal.getInstance(domainSearchModal);
                stdSelectDomainListModal.hide();
                handleStdTermDomainSelectSuceess();

                // domId 값과 함께 getDomId 함수 호출

            });
            domainTableBody.appendChild(domainRow);
        });
    }
}

// 버튼 숨기기


const confirmDomainNameButton = document.getElementById('confirmDomainNameButton');

confirmDomainNameButton.style.display = 'none';




