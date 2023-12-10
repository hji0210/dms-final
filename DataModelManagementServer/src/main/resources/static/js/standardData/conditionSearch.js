$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});


document.getElementById("word").addEventListener("change", function () {
    const attributeCheckbox = document.getElementById("attribute");
    const entityCheckbox = document.getElementById("entity");

    if (this.checked) {
        attributeCheckbox.removeAttribute("disabled");
        entityCheckbox.removeAttribute("disabled");
    } else {
        attributeCheckbox.setAttribute("disabled", "true");
        entityCheckbox.setAttribute("disabled", "true");
        attributeCheckbox.checked = false;
        entityCheckbox.checked = false;
    }
});

document.getElementById("domain").addEventListener("change", function () {
    const domainTypeList = document.getElementById("domainTypeList");
    const dataTypeList = document.getElementById("dataTypeList");

    if (this.checked) {
        domainTypeList.removeAttribute("disabled");
        dataTypeList.removeAttribute("disabled");
    } else {
        domainTypeList.setAttribute("disabled", "true");
        dataTypeList.setAttribute("disabled", "true");
        domainTypeList.value = "None";
        dataTypeList.value = "None";
    }
});
const wordCheckbox = document.getElementById("word");
const attributeCheckbox = document.getElementById("attribute");
const entityCheckbox = document.getElementById("entity");
const domainCheckbox = document.getElementById("domain");

const domainTypeSelect = document.getElementById("domainTypeList");
const dataTypeSelect = document.getElementById("dataTypeList");

const termCheckbox = document.getElementById("term");

document.getElementById("searchLabel").addEventListener("click", function () {
    const tableBody = document.querySelector('.table tbody');
    tableBody.innerHTML = '';

    const searchWord = document.getElementById("conditionSearch");
    const conditionState = {
        standardAreaName: selectElement.options[selectElement.selectedIndex].text,
        searchWord: searchWord.value,
        word: wordCheckbox.checked,
        attribute: wordCheckbox.checked ? attributeCheckbox.checked : false,
        entity: wordCheckbox.checked ? entityCheckbox.checked : false,
        domain: domainCheckbox.checked,
        domainType: domainCheckbox.checked ? domainTypeSelect.value : "None",
        dataType: domainCheckbox.checked ? dataTypeSelect.value : "Node",
        term: termCheckbox.checked,
    };
    sendCheckedStateToServer(conditionState);
});

function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

function confirmDelete(division, dicId) {
    if (division === 'word') {
        openWordDeleteModal(dicId);
    } else if (division === 'domain') {
        openDomainDeleteModal(dicId);
    } else if (division === 'term') {
        openTermDeleteModal(dicId);
    } else {
        console.error('알 수 없는 division:', division);
    }
}

function confirmEdit(division, dicId) {
    if (division === 'word') {
        openWordEditModal(dicId);
    } else if (division === 'domain') {
        openDomainEditModal(dicId);
    } else if (division === 'term') {
        openTermEditModal(dicId);
    } else {
        console.error('알 수 없는 division:', division);
    }
}


function openWordEditModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#editWord').modal('show');
    console.log("getWordAndTermInfo dicid = " + dicId);

    getWordInfo(dicId);


}


function handleStandardYn() {
    showAlert("비 표준어는 수정할 수 없습니다");
}

function openTermEditModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#editTerm').modal('show');
    getTermInfo(dicId);
}

document.getElementById('modalTermDomSearch').addEventListener('click', function () {
    openStdTermSelectDomainModal();
});

function openStdTermSelectDomainModal() {
    // 모달 내용에 dicId를 표시
    $('#stdSelectDomainListModal').modal('show');
}


function openDomainEditModal(dicId) {
    // 모달 내용에 domId 표시
    $('#editDomain').modal('show');
    console.log("getDomainInfo dicId = " +dicId);
    getDomainInfo(dicId);
}


function openWordDeleteModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#deleteWord').modal('show');
    $('#deleteWord .modal-body').text('정말 삭제하시겠습니까?');

    // 확인 버튼 클릭 시 deleteSingleWordAndTerm 함수 호출
    $('#deleteStdWordModalButton').off('click').on('click', function () {
        deleteSingleWord(dicId);


    });
}

function openTermDeleteModal(dicId) {


    // 모달 내용에 dicId를 표시
    $('#deleteTerm').modal('show');
    $('#deleteTerm .modal-body').text('정말 삭제하시겠습니까?');
    console.log("openTermDeleteModal= " + dicId);
    console.log("용어 삭제 모달 실행")
    $('#deleteStdTermModalButton').off('click').on('click', function () {
        deleteSingleTerm(dicId);
    });
}


function openDomainDeleteModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#deleteDomain').modal('show');
    $('#deleteDomain .modal-body').text("정말 삭제하시겠습니까?");

    $('#deleteDomainForStdModal').off('click').on('click', function() {
        deleteSingleDomain(dicId);
    });
}


function findLabelsByLogicalName(logicalName) {
    // logicalName을 기준으로 라벨을 찾는 함수
    const labelsContainers = document.querySelectorAll('.table tbody .word-fields');
    for (const labelsContainer of labelsContainers) {
        const labelsAbove = labelsContainer.querySelectorAll('.word-label');
        const logicalNameAbove = labelsAbove[0].textContent.split(':')[1].trim();
        if (logicalNameAbove === logicalName) {
            return labelsAbove;
        }
    }
    return [];
}

const tableBody = document.querySelector('.table tbody');

function sendCheckedStateToServer(checkedState) {
    showLoadingSpinner()
    console.log("전송할 데이터:", checkedState);

    sendAjaxRequest(
        "/dms/standardData/conditionSearch",
        checkedState,
        function (response) {
            console.log("서버 응답:", response);
            if (response && Array.isArray(response)) {
                tableBody.innerHTML = '';
                const maxItems = 100;  // 처음 100개만 렌더링
                let rowNumber = 1;
                let i = 0;
                for (i = 0; i < Math.min(response.length, maxItems); i++) {
                    const dataDTO = response[i];
                    const newRow = document.createElement('tr');
                    newRow.setAttribute('data-bs-toggle', 'collapse');
                    newRow.setAttribute('data-bs-target', `#r${dataDTO.dicId}`);
                    newRow.setAttribute('name', 'row');
                    let divisionText;
                    switch (dataDTO.division) {
                        case 'word':
                            divisionText = '단어';
                            break;
                        case 'term':
                            divisionText = '용어';
                            break;
                        case 'domain':
                            divisionText = '도메인';
                            break;
                        default:
                            divisionText = '';
                    }
                    newRow.innerHTML = ` 
                        <td data-toggle="tooltip" title="클릭해주세요!">${rowNumber++}</td>
                        <td data-toggle="tooltip" title="클릭해주세요!">${dataDTO.standardClassification || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${divisionText || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.logicalName || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.physicalName || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.domainGroup || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.domainType || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.domainName || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.logicalDataType || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.length || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.isStandard || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.attributeClassifier || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.entityClassifier || ""} </td>
                    `;

                    tableBody.appendChild(newRow);
                    // 이 시점에 ajax요청이 한 번 더 되어야함.
                    //만약 dataDTO.division이 "word"이고, isStandard가 "N"과 같다면
                    //sandajaxrequest를 한 번 더 함.
                    //url은 /dms/standardData/checkSynonym으로
                    //ajax요청시 요청할 때 담길 본문은 현재번째 dataDTO
                    //dataDTO와 완전히 똑같은 응답이 올 예정인데.
                    // 응답이온 dataDTO를 요청할 때 사용했던 dataDTO와 바꿈 ( 갱신 하라는 소리 )
                    if (dataDTO.division === 'word' && dataDTO.isStandard === 'N') {
                        // 추가 요청을 보내야 하는 조건이면 Ajax 요청 수행
                        const synonymCheckUrl = "/dms/standardData/checkSynonym";
                        sendAjaxRequest(
                            synonymCheckUrl,
                            dataDTO,
                            function (synonymResponse) {
                                console.log("동의어 확인 서버 응답:", synonymResponse);
                                // synonymResponse를 이용하여 필요한 작업을 진행
                                dataDTO.logicalName = synonymResponse.logicalName;
                                dataDTO.physicalName = synonymResponse.physicalName;
                                dataDTO.physicalFullName = synonymResponse.physicalFullName;
                                dataDTO.keyDomain = synonymResponse.keyDomain;
                                dataDTO.domainGroup = synonymResponse.domainGroup;
                                dataDTO.domainType = synonymResponse.domainType;
                                dataDTO.domainName = synonymResponse.domainName;
                                dataDTO.logicalDataType = synonymResponse.logicalDataType;
                                dataDTO.length = synonymResponse.length;
                                dataDTO.attributeClassifier = synonymResponse.attributeClassifier;
                                dataDTO.entityClassifier = synonymResponse.entityClassifier;
                                dataDTO.synonym = synonymResponse.synonym;
                                dataDTO.isStandard = synonymResponse.isStandard;
                                // - 한수찬
                                // 이 시점에 지금 나 "비동기는" 수정이 완료된.  DTO이다.
                                // 현재 시점에 내가 해야할 것은 원본 테이블에서 "나"의 "원본"이 속한  TR을 찾고
                                // 해당 TR을 찾는 다면
                                // 해당 TR의 자식 요소 집합을 모두 가져와서
                                // 특정 번째 필드 ( label ) 을 찾아서 업데이트를 한다. 325번 라인까지
                                // labelsContainer -> "나"의 원본이 속한 tr
                                // labelsConainer.querySelectorALL Object -> "나의" 원본이 속한 tr 에 속한 필드( label )의 집합.

                                const labelsContainer = document.querySelector(`#r${dataDTO.dicId} .word-fields`);

                                // 라벨 업데이트
                                labelsContainer.querySelectorAll('.word-label').forEach((label, index) => {
                                    switch (index) {
                                        case 0:
                                            label.textContent = `논리명: ${dataDTO.logicalName}`;
                                            break;
                                        case 1:
                                            label.textContent = `물리명: ${dataDTO.physicalName}`;
                                            break;
                                        case 2:
                                            label.textContent = `영문 풀 네임: ${dataDTO.physicalFullName}`;
                                            break;
                                        case 3:
                                            label.textContent = `동의어: ${dataDTO.synonym}`;
                                            break;
                                        case 4:
                                            label.textContent = `속성 분류어: ${dataDTO.attributeClassifier}`;
                                            break;
                                        case 5:
                                            label.textContent = `엔터티 분류어: ${dataDTO.entityClassifier}`;
                                            break;
                                        case 6:
                                            label.textContent = `표준 여부: ${dataDTO.isStandard}`;
                                            break;
                                    }
                                });
                                const labelsAbove = findLabelsByLogicalName(dataDTO.logicalName);
                                // 라벨 업데이트
                                labelsAbove.forEach((labelAbove, index) => {
                                    switch (index) {
                                        case 3:
                                            labelAbove.textContent = `동의어: ${dataDTO.synonym}`;
                                            break;
                                    }
                                });
                            },
                            function () {
                            }
                        );
                    }
                    const collapsedRow = document.createElement('tr');
                    collapsedRow.classList.add('collapse', 'accordion-collapse');
                    collapsedRow.setAttribute('id', `r${dataDTO.dicId}`);
                    collapsedRow.setAttribute('data-bs-parent', '.table');
                    collapsedRow.innerHTML = `
                        <td colspan="13" class="position-relative">
                         ${dataDTO.division === 'word' ?
                        `<div class="word-fields">
                <h4 class="mb-4">단어 상세정보</h4>

                <div class="row">
                        <label  class="word-label"> 논리명 : ${dataDTO.logicalName}</label>
                    </div>
                    <div class="row">
                        <label class="word-label"> 물리명 : ${dataDTO.physicalName}</label>
                    </div>
                    <div class="row">
                        <label  class="word-label"> 영문 풀 네임 : ${dataDTO.physicalFullName}</label>
                    </div>
                    <div class="row">
                        <label  class="word-label"> 동의어 : ${dataDTO.synonym || ""}</label>
                    </div>
                    <div class="row">
                        <label  class="word-label"> 속성 분류어 : ${dataDTO.attributeClassifier}</label>
                    </div>
                    <div class="row">
                        <label  class="word-label"> 엔터티 분류어 : ${dataDTO.entityClassifier}</label>
                    </div>
                    <div class="row">
                        <label  class="word-label"> 표준 여부 : ${dataDTO.isStandard}</label>
                    </div>
                    <div class="row">
                        <label  class="word-label"> 연관 용어 : ${dataDTO.relatedTerm}</label>
                    </div>
        </div>` :
                        (dataDTO.division === 'term' ?
                            `<div class="term-fields">
                <h4 class="mb-4">용어 상세정보</h4>

                <div class="row">
                    <label class="term-label"> 논리 용어명 : ${dataDTO.logicalName}</label>
                </div>
                <div class="row">
                    <label class="term-label"> 물리 용어명 : ${dataDTO.physicalName}</label>
                </div>
                <div class="row">
                    <label class="term-label"> 도메인명 : ${dataDTO.domainName}</label>
                </div>
                <div class="row">
                    <label class="term-label"> 논리 데이터 타입 : ${dataDTO.logicalDataType}</label>
                </div>
                <div class="row">
                    <label class="term-label"> 논리 구성 정보 : ${dataDTO.logicalName.replace(/\s/g, '+')}</label>
                <div class="row">
                    <label class="term-label"> 물리 구성 정보 : ${dataDTO.physicalName.replace(/\s/g, '+')}</label>
                </div>
                <div class="row">
                    <label class="term-label"> 설명 : ${dataDTO.description || ""}</label>
                </div>
            </div>` :
                            (dataDTO.division === 'domain' ?
                                `<div class="domain-fields">
                <h4 class="mb-4">도메인 상세정보</h4>

                    <div class="row">
                        <label class="domain-label"> 대표 도메인명 : ${dataDTO.keyDomain}</label>
                    </div>
                    <div class="row">
                        <label class="domain-label"> 도메인명 : ${dataDTO.domainName}</label>
                    </div>
                    <div class="row">
                        <label class="domain-label"> 데이터 포맷  : ${dataDTO.logicalDataType}</label>
                    </div>
                    <div class="row">
                        <label class="domain-label"> 도메인 그룹 : ${dataDTO.domainGroup || ""}</label>
                    </div>
                    <div class="row">
                        <label class="domain-label"> 도메인 유형  : ${dataDTO.domainType}</label>
                    </div>
                    <div class="row">
                        <label class="domain-label"> 길이  : ${dataDTO.length} . ${dataDTO.scale}</label>
                    </div>
                    <div class="row">
                        <label class="domain-label"> 데이터 범위  : ${dataDTO.dataMin} ~ ${dataDTO.dataMax} </label>
                    </div>
                    <div class="row">
                        <label class="domain-label"> 설명  : ${dataDTO.description || ""}</label>
                    </div>
                    <div class="row">
                        <label  class="word-label"> 연관 용어 : ${dataDTO.relatedTerm}</label>
                    </div>
                </div>` :
                                ''))}
                        <div class="d-flex justify-content-start">
                                <button class="btn btn-primary" data-dicId="${dataDTO.dicId}" data-division="${dataDTO.division}" onclick="confirmEdit('${dataDTO.division}', '${dataDTO.dicId}')">수정</button>
                                <button class="btn btn-secondary ms-2" data-dicId="${dataDTO.dicId}" data-division="${dataDTO.division}" onclick="confirmDelete('${dataDTO.division}', '${dataDTO.dicId}')">삭제</button>
                        </div>
                        </td>
                    `;

                    tableBody.appendChild(collapsedRow);

                }
                if (i === 100) {
                    showAlert("최대 100행까지 조회됩니다, 조건을 추가해주세요");
                } else {
                    showAlert("검색이 완료되었습니다.", "info");
                }
            }
            document.getElementById("conditionSearch").value = "";
            hideLoadingSpinner();
        },
        function () {
            handleAjaxError();
            document.getElementById("conditionSearch").value = "";
        }
    );
}

document.addEventListener("DOMContentLoaded", () => {
    const excelButton = document.getElementById('saveCollectionToExcel');

    excelButton.addEventListener("click", () => {
        logSelectedRows();
    });

    function extractSelectedRows() {
        const tableRows = document.getElementsByName("row");
        const selectedRows = [];

        tableRows.forEach(row => {
            const columns = row.querySelectorAll("td");

            const rowData = {
                standardClassification : columns[1] !== null ? columns[1].innerText : "",
                division : columns[2] !== null ? columns[2].innerText : "",
                logicalName : columns[3] !== null ? columns[3].innerText : "",
                physicalName : columns[4] !== null ? columns[4].innerText : "",
                domainGroup : columns[5] !== null ? columns[5].innerText : "",
                domainType : columns[6] !== null ? columns[6].innerText : "",
                domainName : columns[7] !== null ? columns[7].innerText : "",
                logicalDataType : columns[8] !== null ? columns[8].innerText : "",
                length : columns[9] !== null ? columns[9].innerText : "",
                isStandard : columns[10] !== null ? columns[10].innerText : "",
                attributeClassifier : columns[11] !== null ? columns[11].innerText : "",
                entityClassifier : columns[12] !== null ? columns[12].innerText : ""
            };

            selectedRows.push(rowData);
        })

        return selectedRows;
    }

    function logSelectedRows() {
        const selectedRows = extractSelectedRows();
        if (selectedRows.length > 0) {
            sendCollectionData(selectedRows);
        } else {
            showAlert("출력할 항목을 선택해 주세요.");
        }
    }
});

function sendCollectionData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/collection/download",
        contentType: "application/json",
        data: JSON.stringify(selectedRows),
        responseType: 'arraybuffer',
        success: function (response) {
            let byteCharacters = atob(response);
            let byteNumbers = new Array(byteCharacters.length);
            for (let i = 0; i < byteCharacters.length; i++) {
                byteNumbers[i] = byteCharacters.charCodeAt(i);
            }
            let byteArray = new Uint8Array(byteNumbers);
            let blob = new Blob([byteArray], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });

            let downloadLink = document.createElement("a");
            downloadLink.href = URL.createObjectURL(blob);
            downloadLink.download = "collection.xlsx";

            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        },
        error: function (error) {
            console.error(error);
        }
    });
}


